package controleur;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import vue.PaiementVoirie;
import vue.SaisirDureeStationnement;

public class ControleurSaisirDureeStationnement implements ActionListener {

	public enum Etat {
		ATTENTE_DUREE, PAIEMENT
	}

	private Etat etat;
	private SaisirDureeStationnement vue;

	public ControleurSaisirDureeStationnement(SaisirDureeStationnement vue) {
		this.vue = vue;
		this.etat = Etat.ATTENTE_DUREE;

		vue.getBtnConfirmer().addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch (this.etat) {
		case ATTENTE_DUREE:
			String duree = this.vue.getTextField().getText();
			if (duree == null || duree.trim().isEmpty()) {
				JOptionPane.showMessageDialog(this.vue, "Veuillez saisir une durée avant de payer.");
				return;
			}
			this.etat = Etat.PAIEMENT;
		case PAIEMENT:
			PaiementVoirie paiement = new PaiementVoirie();
			paiement.setVisible(true);
			this.vue.dispose();
			break;
		}
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			SaisirDureeStationnement vue = new SaisirDureeStationnement();
			new ControleurSaisirDureeStationnement(vue);
			vue.setVisible(true);
		});
	}
}
