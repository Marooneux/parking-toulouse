package controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import modele.Utilisateur;
import modele.dao.DaoUtilisateur;
import modele.dao.MySQLDataSource;
import vue.ChoixParking;
import vue.ChoixTypeStationnement;
import vue.ChoixZone;
import vue.ModifierProfile;
import vue.Profile;

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
            //processEtat();
        }
    }

    private void processEtat(int idUser) {
        switch (etat) {
            case PARKING:
                openParkingPage(idUser);
                break;
            case VOIRIE:
                openVoiriePage();
                break;
        }
    }

    private void openParkingPage(int idUser) {
        try {
            ChoixParking parkingPage = new ChoixParking(idUser);
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
    
    public void openProfile(int idUser) {
        try {
            MySQLDataSource.creerAcces();
        	
        	DaoUtilisateur dao = new DaoUtilisateur();
    		Utilisateur user = dao.findById(idUser);
    		
            Profile profile = new Profile(user);
            profile.setVisible(true);
            vue.dispose();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
}
