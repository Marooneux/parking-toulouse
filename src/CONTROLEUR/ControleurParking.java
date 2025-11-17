package CONTROLEUR;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import VUE.ChoixPlaceParking;
import VUE.ChoixTypeStationnement;
import VUE.Paiement;
import VUE.SaisirDureeStationnement;
import VUE.SaisirHeureArriveParking;

public class ControleurParking {
	private JFrame vue;

	private enum TypeStationnement {
		CONNECTION, CHOIXPARKING, CHOIXVOIRIE, HEURE, PAIEMENT
	}

	public ControleurParking(JFrame vue) {
		this.vue = vue;

		// Attache les listeners aux boutons "Parking" et "Voirie"
		if (vue instanceof ChoixTypeStationnement) {
			ChoixTypeStationnement choixVue = (ChoixTypeStationnement) vue;
			choixVue.getBtnParking().addActionListener(e -> this.handleAction(TypeStationnement.CHOIXPARKING));
			choixVue.getBtnVoirie().addActionListener(e -> this.handleAction(TypeStationnement.CHOIXVOIRIE));
		}

		// Vérifie que la durée saisie n’est pas vide avant de passer au paiement
		if (vue instanceof SaisirDureeStationnement) {
			SaisirDureeStationnement dureeVue = (SaisirDureeStationnement) vue;
			dureeVue.getBtnPayment().addActionListener(e -> {
				String duree = dureeVue.getTextField().getText();
				if (duree == null || duree.trim().isEmpty()) {
					JOptionPane.showMessageDialog(dureeVue, "Veuillez saisir une durée avant de payer.");
					return;
				}
				this.handleAction(TypeStationnement.PAIEMENT);
			});
		}

		// Vérifie que l’heure saisie n’est pas vide avant de passer au paiement
		if (vue instanceof SaisirHeureArriveParking) {
			SaisirHeureArriveParking heureVue = (SaisirHeureArriveParking) vue;
			heureVue.getBtnPayment().addActionListener(e -> {
				String heure = heureVue.getTextField().getText();
				if (heure == null || heure.trim().isEmpty()) {
					JOptionPane.showMessageDialog(heureVue, "Veuillez saisir une heure avant de payer.");
					return;
				}
				this.handleAction(TypeStationnement.PAIEMENT);
			});
		}
	}

	// Gère la navigation entre les vues selon l’action choisie
	private void handleAction(TypeStationnement action) {
		switch (action) {
		case CHOIXPARKING: {
			ChoixPlaceParking parkingVue = new ChoixPlaceParking();
			new ControleurParking(parkingVue);
			parkingVue.setVisible(true);
			this.vue.dispose();
			break;
		}
		case CHOIXVOIRIE: {
			SaisirDureeStationnement dureeVue = new SaisirDureeStationnement();
			new ControleurParking(dureeVue);
			dureeVue.setVisible(true);
			this.vue.dispose();
			break;
		}
		case HEURE: {
			SaisirHeureArriveParking heureVue = new SaisirHeureArriveParking();
			new ControleurParking(heureVue);
			heureVue.setVisible(true);
			this.vue.dispose();
			break;
		}
		case PAIEMENT: {
			Paiement paiementVue = new Paiement();
			paiementVue.setVisible(true);
			this.vue.dispose();
			break;
		}
		default: {
			System.out.println("Action inconnue : " + action);
			break;
		}
		}
	}
}
