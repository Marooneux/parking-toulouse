package controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import vue.ChoixParking;
import vue.ChoixTypeStationnement;
import vue.ChoixZone;

public class ControleurChoixTypeStationnement implements ActionListener {

    public enum Etat {
        PARKING, VOIRIE
    }

    private final ChoixTypeStationnement vue;
    private final Etat etat;

    public ControleurChoixTypeStationnement(ChoixTypeStationnement vue, Etat etat) {
        this.vue = vue;
        this.etat = etat;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        processEtat(etat);
    }

    private void processEtat(Etat etat) {
        switch (etat) {
            case PARKING:
                openParkingPage();
                break;
            case VOIRIE:
                openVoiriePage();
                break; 
        }
    }

    private void openParkingPage() {
        try {
            ChoixParking parkingPage = new ChoixParking();
            parkingPage.setVisible(true);
            vue.dispose();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void openVoiriePage() {
        try {
            ChoixZone voiriePage = new ChoixZone();
            voiriePage.setVisible(true);
            vue.dispose();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
