package controleur;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import vue.ChoixTypeStationnement;
import vue.PaiementVoirie;
import vue.SaisirDureeStationnement;
import vue.SaisirHeureArriveParking;

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
