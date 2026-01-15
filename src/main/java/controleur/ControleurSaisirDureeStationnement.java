package controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;

import javax.swing.JOptionPane;

import modele.ZoneVoirie;
import vue.ChoixMoyenPaiementVoirie;
import vue.SaisirHeureArriveParking;
import vue.TicketVoirie;

public class ControleurSaisirDureeStationnement implements ActionListener {

	public enum Etat {
		ATTENTE_DUREE, PAIEMENT
	}

	private Etat etat;
	private SaisirHeureArriveParking vue;

	public ControleurSaisirDureeStationnement(SaisirHeureArriveParking vue) {
		this.vue = vue;
		this.etat = Etat.ATTENTE_DUREE;
		vue.getBtnConfirmer().addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch (this.etat) {
		case ATTENTE_DUREE:
			if (!this.verifierDuree()) {
				return;
			}
			this.etat = Etat.PAIEMENT;
			break;
		case PAIEMENT:
			break;
		}
	}

	public static double calculerPrixTotal(ZoneVoirie zone, int duree) {
		LocalTime actuel = LocalTime.now();
		// Rajouter les deux horaires dans la table Zone? (
		if /*
			 * actuel.isAfter(zone.getHorairePayantFin()) ||
			 * actuel.isBefore(zone.getHorairePayantDebut())) ||
			 */
		(LocalDate.now().getDayOfWeek() == DayOfWeek.SUNDAY
				|| (zone.getNom() == "rouge" && duree <= 30)) {
			return 0;
		}
		double prixTotal = 0;
		int heures = duree / 60;
		int minutes = duree % 60;
		if (minutes > 0) {
			prixTotal += zone.getTarifHoraire();
		}
		prixTotal += heures * zone.getTarifHoraire();
		if (zone.getNom() == "orange") {
			if (duree > 180 && duree < 240) {
				prixTotal = 4;
			} else if (duree > 240) {
				prixTotal = 6;
			}
		}
		return prixTotal;
	}

	private boolean verifierDuree() {
		String duree = this.vue.getTextField().getText().trim();
		if (duree.isEmpty()) {
			JOptionPane.showMessageDialog(this.vue, "Veuillez saisir une durée avant de payer.");
			return false;
		}
		return true;
	}

	public static void ouvrirPaiement(ZoneVoirie zone, String immatriculation, int intDuree, double prix) {
		ChoixMoyenPaiementVoirie frameChoixPaiementVoirie = new ChoixMoyenPaiementVoirie(zone, immatriculation,
				intDuree, prix);
		frameChoixPaiementVoirie.setVisible(true);
	}

	public static void ouvrirTicket(ZoneVoirie zone, String immatriculation, int intDuree) {
		TicketVoirie frameTicketVoirie = new TicketVoirie(zone, immatriculation, intDuree, "Gratuit");
		frameTicketVoirie.setVisible(true);
	}
}
