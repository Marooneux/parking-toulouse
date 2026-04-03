package controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import modele.ReservationParking;
import modele.Utilisateur;
import modele.dao.DaoReservationParking;
import modele.dao.MySQLDataSource;
import utils.AuthManager;
import java.time.format.DateTimeFormatter;

import vue.ChoixMoyenPaiement;
import vue.ChoixTypeStationnement;
import vue.NavigationFrame;
import vue.PaiementCarte;
import vue.PaiementVirement;
import vue.TicketParking;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ControleurTicketParking implements ActionListener {
	private static final Logger LOGGER = Logger.getLogger(ControleurTicketParking.class.getName());


	private final TicketParking vue;

	public ControleurTicketParking(TicketParking vue) {
		this.vue = vue;
		this.vue.getBtnPaiement().addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (vue.isConfirmationRequise()) {
			confirmerTicket();
			return;
		}
		allerAuPaiement();
	}

	private void confirmerTicket() {
		Utilisateur user = AuthManager.getCurrentUser();
		if (user == null) {
			JOptionPane.showMessageDialog(vue, "Vous devez être connecté pour confirmer.");
			return;
		}

		try {
			MySQLDataSource.creerAcces();
			DaoReservationParking dao = new DaoReservationParking();
			ReservationParking active = dao.findActiveByUserId(user.getId());
			if (active != null) {
				JOptionPane.showMessageDialog(vue, "Vous êtes déjà garé. Quittez d'abord le parking.");
				return;
			}

			ReservationParking reservation = vue.getReservation();
			reservation.setUtilisateur(user);
			dao.create(reservation);

			NavigationFrame.getInstance().showPage(
					"Stationnement",
					() -> new ChoixTypeStationnement(user.getId()),
					"Stationnement",
					true);
		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(vue, "Erreur lors de la confirmation du ticket.");
			LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
		}
	}

	private void allerAuPaiement() {
		try {
			ReservationParking reservation = vue.getReservation();
			double prix = reservation.calculerPrixTotal();
			DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
			String[][] recap = {
				{"Parking", reservation.getParking() != null ? reservation.getParking().getNom() : "Parking"},
				{"Arrivee", reservation.getDateArrivee() != null ? fmt.format(reservation.getDateArrivee()) : "-"},
				{"Depart", reservation.getDateDepart() != null ? fmt.format(reservation.getDateDepart()) : "-"},
				{"Montant", String.format("%.2f €", prix)}
			};
			ChoixMoyenPaiement choix = new ChoixMoyenPaiement();
			new ControleurChoixMoyenPaiement(choix,
				() -> {
					PaiementCarte page = new PaiementCarte(prix);
					new ControleurPaiementParking(page, page.getBtnPayer(), prix, reservation);
					NavigationFrame.getInstance().showPage("parking-paiement-carte", () -> page, "Paiement par carte", true);
				},
				() -> {
					PaiementVirement page = new PaiementVirement(prix, recap);
					new ControleurPaiementParking(page, page.getBtnPayer(), prix, reservation);
					NavigationFrame.getInstance().showPage("parking-paiement-virement", () -> page, "Paiement par virement", true);
				}
			);
			NavigationFrame.getInstance().showPage("parking-choix-paiement", () -> choix, "Choisir le paiement", true);
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(vue, "Impossible d'ouvrir le paiement.");
			LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
		}
	}
}
