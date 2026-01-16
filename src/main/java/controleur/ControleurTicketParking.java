package controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import vue.ChoixMoyenPaiementParking;
import vue.TicketParking;

public class ControleurTicketParking implements ActionListener {

	private final TicketParking vue;

	public ControleurTicketParking(TicketParking vue) {
		this.vue = vue;
		this.vue.getBtnPaiement().addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		allerAuPaiement();
	}

	private void allerAuPaiement() {
		try {
			double prix = ControleurSaisirHeureArriveParking.calculerPrixTotal(vue.getParking(), vue.getHeureArrivee());
			new ChoixMoyenPaiementParking(vue.getReservation(), prix).setVisible(true);
			vue.dispose();
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(vue, "Impossible d'ouvrir le paiement.");
			ex.printStackTrace();
		}
	}
}
