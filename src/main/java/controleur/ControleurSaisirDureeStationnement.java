package controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.DayOfWeek;
import java.time.LocalDate;

import javax.swing.JOptionPane;

import modele.ZoneVoirie;
import vue.ChoixMoyenPaiementVoirie;
import vue.NavigationFrame;
import vue.SaisirDureeStationnement;
import vue.TicketVoirie;

public class ControleurSaisirDureeStationnement implements ActionListener {

	private final ZoneVoirie zone;
	private final SaisirDureeStationnement vue;

	public ControleurSaisirDureeStationnement(ZoneVoirie zone, SaisirDureeStationnement vue) {
		this.zone = zone;
		this.vue = vue;
		this.vue.getBtnConfirmer().addActionListener(this);

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

	public static void ouvrirPaiement(ZoneVoirie zone, String immatriculation, int intDuree, double prix) {
		ChoixMoyenPaiementVoirie choix = new ChoixMoyenPaiementVoirie(zone, immatriculation,
				intDuree, prix);
		new ControleurChoixMoyenPaiementVoirie(choix, zone, immatriculation, intDuree, prix);
		String key = "voirie-choix-paiement-" + immatriculation + "-" + intDuree;
		NavigationFrame.getInstance().showPage(key, () -> choix, "Choisir le paiement");
	}

	public static void ouvrirTicket(ZoneVoirie zone, String immatriculation, int intDuree) {
		TicketVoirie ticket = new TicketVoirie(zone, immatriculation, intDuree, "Gratuit");
		new ControleurTicketVoirie(ticket);
		String key = "voirie-ticket-" + immatriculation + "-" + intDuree;
		NavigationFrame.getInstance().showPage(key, () -> ticket, "Ticket voirie");
	}
}
