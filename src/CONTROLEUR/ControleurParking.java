package CONTROLEUR;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import ENUM.TypeStationnement;
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

		// Cas 1 : Vue ChoixTypeStationnement
		// Ici on attache les listeners aux boutons "Parking" et "Voirie"
		if (vue instanceof ChoixTypeStationnement choixVue) {
			choixVue.getBtnParking().addActionListener(e -> this.handleAction(TypeStationnement.CHOIXPARKING));
			choixVue.getBtnVoirie().addActionListener(e -> this.handleAction(TypeStationnement.CHOIXVOIRIE));
		}

		// Cas 2 : Vue SaisirDureeStationnement
		// On vérifie que la durée saisie n’est pas vide avant de passer au paiement
		if (vue instanceof SaisirDureeStationnement dureeVue) {
			dureeVue.getBtnPayment().addActionListener(e -> {
				String duree = dureeVue.getTextField().getText();
				if (duree == null || duree.trim().isEmpty()) {
					JOptionPane.showMessageDialog(dureeVue, "Veuillez saisir une durée avant de payer.");
					return; // On arrête si champ vide
				}
				this.handleAction(TypeStationnement.PAIEMENT);
			});
		}

		// Cas 3 : Vue SaisirHeureArriveParking
		// On vérifie que l’heure saisie n’est pas vide avant de passer au paiement
		if (vue instanceof SaisirHeureArriveParking heureVue) {
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

	// Méthode centrale : gère la navigation entre les vues selon l’action choisie
	private void handleAction(TypeStationnement action) {
		switch (action) {
		case CHOIXPARKING -> {
			// L’utilisateur a choisi "Parking" → on ouvre la vue ChoixPlaceParking
			ChoixPlaceParking parkingVue = new ChoixPlaceParking();
			new ControleurParking(parkingVue); // Attacher le contrôleur à la nouvelle vue
			parkingVue.setVisible(true);
			this.vue.dispose(); // Fermer l’ancienne vue
		}
		case CHOIXVOIRIE -> {
			// L’utilisateur a choisi "Voirie" → on ouvre la vue SaisirDureeStationnement
			SaisirDureeStationnement dureeVue = new SaisirDureeStationnement();
			new ControleurParking(dureeVue);
			dureeVue.setVisible(true);
			this.vue.dispose();
		}
		case HEURE -> {
			// L’utilisateur doit saisir une heure d’arrivée → on ouvre la vue
			// correspondante
			SaisirHeureArriveParking heureVue = new SaisirHeureArriveParking();
			new ControleurParking(heureVue);
			heureVue.setVisible(true);
			this.vue.dispose();
		}
		case PAIEMENT -> {
			// Après saisie correcte (durée ou heure), on ouvre la page Paiement
			Paiement paiementVue = new Paiement();
			paiementVue.setVisible(true);
			this.vue.dispose();
		}
		default -> System.out.println("Action inconnue : " + action);
		}
	}
}
