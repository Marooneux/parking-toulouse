package controleur;

import java.sql.SQLException; 
import java.util.List;

import javax.swing.JOptionPane;

import modele.Parking;
import modele.dao.DaoParking;
import modele.dao.MySQLDataSource;
import vue.adminParking.Accueil;
import vue.adminParking.GestionParking;
import vue.SaisirHeureArriveParking;

public class ControleurAccueilAdmin{

    private Accueil vue;
    private DaoParking daoParking;

    public ControleurAccueilAdmin(Accueil vue) {
        this.vue = vue;
        this.daoParking = new DaoParking();

        MySQLDataSource.creerAcces("root", "admin"); 

        chargerParkings();
        vue.setVisible(true);
    }

    private void chargerParkings() {
        try {
            List<Parking> parkings = daoParking.findByAdminId(1);

            for (Parking p : parkings) {
                vue.addParking(p, this::onParkingSelected);
            }


        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Erreur lors du chargement des parkings. Vérifiez la connexion à la base de données.");
        }
    }
    

    private void onParkingSelected(Parking parking) {
        GestionParking vueSuivante = new GestionParking(parking);

        vueSuivante.setVisible(true);
    }
    

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(() -> {
            Accueil vue = new Accueil();
            new ControleurAccueilAdmin(vue);
            vue.setVisible(true);
        });
    }
}
