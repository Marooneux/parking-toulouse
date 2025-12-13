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

	public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();

        // Update state based on button clicked
        if (source == vue.getBtnParking()) {
            etat = Etat.PARKING;
        } else if (source == vue.getBtnVoirie()) {
            etat = Etat.VOIRIE;
        } else {
            return;
        }

        // Take action based on state
        switch (etat) {
            case PARKING :
            	openParkingPage();
            	break;
            case VOIRIE :
            	openVoiriePage();
            	break;
        }
    }

    private void openParkingPage() {
        ChoixParking parkingPage = new ChoixParking();
        new ControleurChoixParking(parkingPage);
        parkingPage.setVisible(true);
        vue.dispose();
    }

    private void openVoiriePage() {
        SaisirDureeStationnement voiriePage = new SaisirDureeStationnement();
        new ControleurSaisirDureeStationnement(voiriePage);
        voiriePage.setVisible(true);
        vue.dispose();
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
