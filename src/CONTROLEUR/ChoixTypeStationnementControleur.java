package CONTROLEUR;

import javax.swing.JButton;

import VUE.ChoixPlaceParking;
import VUE.ChoixTypeStationnement;
import VUE.SaisirDureeStationnement;

public class ChoixTypeStationnementControleur {

	private ChoixTypeStationnement vue;

	public ChoixTypeStationnementControleur(ChoixTypeStationnement vue) {
		this.vue = vue;

		// Récupération des boutons exposés par la vue
		JButton btnParking = vue.getBtnParking();
		JButton btnVoirie = vue.getBtnVoirie();

		btnParking.addActionListener(e -> {
			ChoixPlaceParking parkingPage = new ChoixPlaceParking();
			parkingPage.setVisible(true);
			vue.dispose();
		});

		btnVoirie.addActionListener(e -> {
			SaisirDureeStationnement dureePage = new SaisirDureeStationnement();
			dureePage.setVisible(true);
			vue.dispose();
		});

	}
}
