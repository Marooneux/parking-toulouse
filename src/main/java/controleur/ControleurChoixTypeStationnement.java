package controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import vue.ChoixParking;
import vue.ChoixTypeStationnement;
import vue.ChoixZone;

public class ControleurChoixTypeStationnement {

    public enum Etat {
        PARKING, VOIRIE
    }

    private ChoixTypeStationnement vue;
    private Etat etat;

    public ControleurChoixTypeStationnement(ChoixTypeStationnement vue) {
        this.vue = vue;
        this.etat = null;

        /*
        vue.getParkingButton().addActionListener(new ButtonListener(Etat.PARKING));
        vue.getVoirieButton().addActionListener(new ButtonListener(Etat.VOIRIE));
        */
    }

    private class ButtonListener implements ActionListener {
        private Etat buttonEtat;

        public ButtonListener(Etat etat) {
            this.buttonEtat = etat;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            etat = buttonEtat;
            processEtat();
        }
    }

    private void processEtat() {
        switch (etat) {
            case PARKING:
                openParkingPage();
                break;
            case VOIRIE:
                openVoiriePage();
                break; 
        }
    }

    public static void openParkingPage() {
        try {
            ChoixParking parkingPage = new ChoixParking();
            parkingPage.setVisible(true);
            //vue.dispose();
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
