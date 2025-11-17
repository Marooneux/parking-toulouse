package CONTROLEUR;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import VUE.Paiement;
import VUE.SaisirHeureArriveParking;

public class SaisirHeureArriveParkingControleur {
	private SaisirHeureArriveParking vue;

	public SaisirHeureArriveParkingControleur(SaisirHeureArriveParking vue) {
		this.vue = vue;
		JButton btnPaiement = vue.getBtnPayment();

		btnPaiement.addActionListener(e -> {
			String heure = vue.getTextField().getText();
			if (heure == null || heure.isEmpty()) {
				JOptionPane.showMessageDialog(vue, "Veuillez saisir heure avant de payer.");
				return;
			}
			Paiement paiementPage = new Paiement();
			paiementPage.setVisible(true);
			vue.dispose();
		});
	}
}
