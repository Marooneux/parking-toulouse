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
        switch (etat) {
            case ATTENTE_DUREE :
                if (!verifierDuree()) return;
                etat = Etat.PAIEMENT;
                ouvrirPaiement();
                break;
            case PAIEMENT :
            	ouvrirPaiement();
            	break;
        }
    }

    private boolean verifierDuree() {
        String duree = vue.getTextField().getText().trim();
        if (duree.isEmpty()) {
            JOptionPane.showMessageDialog(vue, "Veuillez saisir une durée avant de payer.");
            return false;
        }
        return true;
    }

    private void ouvrirPaiement() {
        new PaiementVoirie().setVisible(true);
        vue.dispose();
    }
	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			SaisirDureeStationnement vue = new SaisirDureeStationnement();
			new ControleurSaisirDureeStationnement(vue);
			vue.setVisible(true);
		});
	}
}
