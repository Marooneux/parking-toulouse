package CONTROLEUR;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import VUE.ChoixPlaceStationnement;
import VUE.ChoixTypeStationnement;

public class ChoixTypeStationnementControleur {
	private ChoixTypeStationnement vue;

	public ChoixTypeStationnementControleur(ChoixTypeStationnement vue) {
		this.vue = vue;

		// Récupération des boutons depuis la vue
		JButton btnParking = vue.getBtnParking();
		JButton btnVoirie = vue.getBtnVoirie();

		// Action Parking
		btnParking.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(vue, "Ouverture de la recherche Parking...");
				ChoixPlaceStationnement parkingVue = new ChoixPlaceStationnement();
				parkingVue.setVisible(true);
				vue.dispose(); // fermer la fenêtre actuelle
			}
		});

		// Action Voirie
		btnVoirie.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(vue, "Ouverture de la recherche Voirie...");
				// Ici tu pourrais ouvrir la Vue correspondante (ex: SaisirHeureArriveParking)
			}
		});
	}

}
