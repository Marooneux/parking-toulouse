package CONTROLEUR;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import VUE.ChoixParking;
import VUE.ChoixTypeStationnement;
import VUE.PaiementParking;
import VUE.PaiementVoirie;
import VUE.SaisirDureeStationnement;
import VUE.SaisirHeureArriveParking;

public class ControleurParking {
	private JFrame vue;

	private enum TypeStationnement {
		CONNECTION, HEURE, PAIEMENT, PAIEMENTVOIRIE
	}

	public ControleurParking(JFrame vue) {
		this.vue = vue;


	}

	// Gère la navigation entre les vues selon l’action choisie
	private void handleAction(TypeStationnement action) {
		switch (action) {
		default: {
			System.out.println("Action inconnue : " + action);
			break;
		}
		}
	}
}
