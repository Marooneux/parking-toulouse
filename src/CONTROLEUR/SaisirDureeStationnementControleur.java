package CONTROLEUR;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import VUE.Paiement;
import VUE.SaisirDureeStationnement;

public class SaisirDureeStationnementControleur {

	private SaisirDureeStationnement vue;

	public SaisirDureeStationnementControleur(SaisirDureeStationnement vue) {
		this.vue = vue;
		// Récupération du bouton "Payer"
		JButton btnPayment = vue.getBtnPayment();
		btnPayment.addActionListener(e -> {
			// Ici pour récupérer la durée saisie
			String duree = vue.getTextField().getText();
			// Vérification
			if (duree == null || duree.isEmpty()) {
				JOptionPane.showMessageDialog(vue, "Veuillez saisir une durée avant de payer.");
				return;
			}
			// Ouvrir la page Paiement
			Paiement paiementPage = new Paiement();
			paiementPage.setVisible(true);
			// Fermer la fenêtre actuelle
			vue.dispose();
		});
	}
}
