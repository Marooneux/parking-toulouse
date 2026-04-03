package controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;

import javax.swing.JOptionPane;

import modele.ZoneVoirie;
import modele.ReservationVoirie;
import modele.Vehicule;
import modele.dao.DaoReservationVoirie;
import modele.dao.DaoVehicule;
import modele.dao.MySQLDataSource;
import vue.ChoixMoyenPaiement;
import vue.NavigationFrame;
import vue.PaiementCarte;
import vue.PaiementVirement;
import vue.SaisirDureeStationnement;
import vue.TicketVoirie;
import utils.AuthManager;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ControleurSaisirDureeStationnement implements ActionListener {
	private static final Logger LOGGER = Logger.getLogger(ControleurSaisirDureeStationnement.class.getName());


	private final ZoneVoirie zone;
	private final SaisirDureeStationnement vue;

	public ControleurSaisirDureeStationnement(ZoneVoirie zone, SaisirDureeStationnement vue) {
		this.zone = zone;
		this.vue = vue;
		this.vue.getBtnConfirmer().addActionListener(this);
		this.prefillPlaque();

		NavigationFrame.getInstance().showPage("voirie-duree", () -> this.vue, "Démarrer le stationnement");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String immatriculation = vue.getImmatriculation();
		String dureeStr = vue.getDureeSaisie();
		if (!validerChamps(immatriculation, dureeStr)) {
			return;
		}

		int dureeMinutes;
		try {
			dureeMinutes = Integer.parseInt(dureeStr);
		} catch (NumberFormatException ex) {
			JOptionPane.showMessageDialog(vue, "Veuillez entrer une durée en minutes uniquement.");
			return;
		}

		if (dureeMinutes > zone.getDureeMax()) {
			JOptionPane.showMessageDialog(vue, "La durée saisie dépasse la durée maximum de cette zone.");
			return;
		}

		double prix = calculerPrixTotal(zone, dureeMinutes);
		if (prix == 0) {
			ouvrirTicket(zone, immatriculation, dureeMinutes);
		} else {
			ouvrirPaiement(zone, immatriculation, dureeMinutes, prix);
		}
	}

	public static double calculerPrixTotal(ZoneVoirie zone, int duree) {
		if (LocalDate.now().getDayOfWeek() == DayOfWeek.SUNDAY || ("rouge".equals(zone.getCouleur()) && duree <= 30)) {
			return 0;
		}
		double prixTotal = 0;
		int heures = duree / 60;
		int minutes = duree % 60;
		if (minutes > 0) {
			prixTotal += zone.getTarifHoraire();
		}
		prixTotal += heures * zone.getTarifHoraire();
		if ("orange".equals(zone.getCouleur())) {
			if (duree > 180 && duree < 240) {
				prixTotal = 4;
			} else if (duree > 240) {
				prixTotal = 6;
			}
		}
		return prixTotal;
	}

	private boolean validerChamps(String immatriculation, String duree) {
		if (immatriculation == null || immatriculation.trim().isEmpty()) {
			JOptionPane.showMessageDialog(vue, "Veuillez saisir votre plaque d'immatriculation avant de payer.");
			return false;
		}
		if (duree == null || duree.trim().isEmpty()) {
			JOptionPane.showMessageDialog(vue, "Veuillez saisir une durée avant de payer.");
			return false;
		}
		return true;
	}
	
    private void prefillPlaque() {
        try {
            if (AuthManager.getCurrentUser() == null) {
                return;
            }
            MySQLDataSource.creerAcces();
            DaoVehicule daoVehicule = new DaoVehicule();
            int userId = AuthManager.getCurrentUser().getId();
            List<Vehicule> vehicules = daoVehicule.findByUserId(userId);
            Vehicule vehicule = vehicules.isEmpty() ? null : vehicules.getFirst();
            if (vehicule != null) {
                vue.getPlaque().setText(vehicule.getImmatriculation());
            }
        } catch (Exception ignored) {
            // Pré-remplissage non bloquant
        }
    }

	public static void ouvrirPaiement(ZoneVoirie zone, String immatriculation, int intDuree, double prix) {
		String[][] recap = {
			{"Zone", zone.getCouleur()},
			{"Immatriculation", immatriculation},
			{"Duree", intDuree + " min"},
			{"Montant", String.format("%.2f €", prix)}
		};
		ChoixMoyenPaiement choix = new ChoixMoyenPaiement();
		new ControleurChoixMoyenPaiement(choix,
			() -> {
				PaiementCarte page = new PaiementCarte(prix);
				new ControleurPaiementVoirie(page, page.getBtnPayer(), zone, immatriculation, intDuree, prix, "Carte bancaire");
				NavigationFrame.getInstance().showPage("voirie-paiement-carte-" + immatriculation + "-" + intDuree, () -> page, "Paiement par carte", true);
			},
			() -> {
				PaiementVirement page = new PaiementVirement(prix, recap);
				new ControleurPaiementVoirie(page, page.getBtnPayer(), zone, immatriculation, intDuree, prix, "Virement bancaire");
				NavigationFrame.getInstance().showPage("voirie-paiement-virement-" + immatriculation + "-" + intDuree, () -> page, "Paiement par virement", true);
			}
		);
		NavigationFrame.getInstance().showPage("voirie-choix-paiement-" + immatriculation + "-" + intDuree, () -> choix, "Choisir le paiement");
	}

	public static void ouvrirTicket(ZoneVoirie zone, String immatriculation, int intDuree) {
		if (AuthManager.getCurrentUser() == null) {
			JOptionPane.showMessageDialog(null, "Connexion requise pour enregistrer la réservation.");
			return;
		}

		ReservationVoirie reservation = new ReservationVoirie(0, java.time.LocalDateTime.now(), intDuree, zone, AuthManager.getCurrentUser());
		try {
			MySQLDataSource.creerAcces();
			new DaoReservationVoirie().create(reservation);
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, "Erreur lors de l'enregistrement de la réservation.");
			LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
			return;
		}

		TicketVoirie ticket = new TicketVoirie(zone, immatriculation, intDuree, "Gratuit");
		new ControleurTicketVoirie(ticket);
		String key = "voirie-ticket-" + immatriculation + "-" + intDuree;
		NavigationFrame.getInstance().showPage(key, () -> ticket, "Ticket voirie");
	}
}
