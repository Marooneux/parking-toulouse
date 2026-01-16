package controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import vue.NavigationFrame;
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
			ChoixMoyenPaiementParking choix = new ChoixMoyenPaiementParking(vue.getReservation(), prix);
			new ControleurChoixMoyenPaiementParking(choix, vue.getReservation(), prix);
			NavigationFrame.getInstance().showPage("parking-choix-paiement", () -> choix, "Choisir le paiement");
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(vue, "Impossible d'ouvrir le paiement.");
			ex.printStackTrace();
		}
	}
}
