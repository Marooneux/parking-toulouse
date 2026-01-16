package controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import modele.Utilisateur;
import modele.dao.DaoUtilisateur;
import modele.dao.MySQLDataSource;
import vue.ChoixParking;
import vue.ChoixTypeStationnement;
import vue.ChoixZone;
import vue.Profile;

public class ControleurChoixTypeStationnement implements ActionListener {

    public enum Etat {
        PARKING, VOIRIE
    }

    private final ChoixTypeStationnement vue;
    private final int idUser;

    public ControleurChoixTypeStationnement(ChoixTypeStationnement vue, int idUser) {
        this.vue = vue;
        this.idUser = idUser;

        this.vue.getProfileButton().addActionListener(this);
        this.vue.getBtnParking().addActionListener(this);
        this.vue.getBtnVoirie().addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vue.getProfileButton()) {
            openProfile(idUser);
            return;
        }
        if (e.getSource() == vue.getBtnParking()) {
            openParkingPage(idUser);
            return;
        }
        if (e.getSource() == vue.getBtnVoirie()) {
            openVoiriePage();
        }
    }

    private void openParkingPage(int idUser) {
        try {
            ChoixParking parkingPage = new ChoixParking();
            new ControleurChoixParking(parkingPage, idUser);
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
