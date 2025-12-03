package controleur;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import vue.ChoixParking;
import vue.ParkingPanel;
import vue.SaisirHeureArriveParking;

public class ControleurChoixParking implements ActionListener {

	public enum Etat {
		HEURE
	}

	private Etat etat;
	private ChoixParking vue;
	private JButton btnChoisirParking;

	public ControleurChoixParking(ChoixParking vue) {
		this.vue = vue;

		this.btnChoisirParking = vue.getBtnChoisirParking();
		this.btnChoisirParking.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();

		if (source == this.btnChoisirParking) {
			this.etat = Etat.HEURE;
		} else {
			return;
		}

		switch (this.etat) {
		case HEURE:
			ParkingPanel selectedParking = this.vue.getParkingSelectionne();
			if (selectedParking == null) {
				javax.swing.JOptionPane.showMessageDialog(this.vue, "Veuillez sélectionner un parking.");
				return;
			}

			SaisirHeureArriveParking next = new SaisirHeureArriveParking(selectedParking);
			new ControleurSaisirHeureArriveParking(next);
			next.setVisible(true);
			this.vue.dispose();
			break;
		}
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			ChoixParking vue = new ChoixParking();
			new ControleurChoixParking(vue);
			vue.setVisible(true);
		});
	}
}
