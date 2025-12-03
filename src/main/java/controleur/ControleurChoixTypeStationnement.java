package controleur;


import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import vue.ChoixParking;
import vue.ChoixTypeStationnement;
import vue.SaisirDureeStationnement;

public class ControleurChoixTypeStationnement implements ActionListener {

	public enum Etat {
		PARKING, VOIRIE
	}

	private Etat etat;
	private ChoixTypeStationnement vue;

	public ControleurChoixTypeStationnement(ChoixTypeStationnement vue) {
		this.vue = vue;
		this.etat = null;
		vue.getBtnParking().addActionListener(this);
		vue.getBtnVoirie().addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();

		if (source == this.vue.getBtnParking()) {
			this.etat = Etat.PARKING;
		} else if (source == this.vue.getBtnVoirie()) {
			this.etat = Etat.VOIRIE;
		} else {
			return;
		}

		switch (this.etat) {
		case PARKING:
			ChoixParking parkingPage = new ChoixParking();
			new ControleurChoixParking(parkingPage);
			parkingPage.setVisible(true);
			this.vue.dispose();
			break;

		case VOIRIE:
			SaisirDureeStationnement voiriePage = new SaisirDureeStationnement();
			new ControleurSaisirDureeStationnement(voiriePage);
			voiriePage.setVisible(true);
			this.vue.dispose();
			break;
		}
	}

	public static void main(String[] args) {

		JFrame f = new JFrame();
		f.setLayout(new GridLayout(1, 1));

		ChoixTypeStationnement vue = new ChoixTypeStationnement();

		f.add(vue.getContentPane());

		new ControleurChoixTypeStationnement(vue);

		f.setTitle("ControleurChoixTypeStationnement");
		f.setSize(900, 600);
		f.setLocationRelativeTo(null);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
	}
}
