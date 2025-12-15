package controleur;

import java.sql.SQLException;
import java.util.List;

import modele.Parking;
import modele.dao.DaoParking;
import modele.dao.MySQLDataSource;
import vue.ChoixParking;
import vue.SaisirHeureArriveParking;

public class ControleurChoixParking {

    private ChoixParking vue;
    private DaoParking daoParking;

    public ControleurChoixParking(ChoixParking vue) {
        this.vue = vue;
        this.daoParking = new DaoParking();

        MySQLDataSource.creerAcces("root", "$iutinfo"); 

        chargerParkings();
    }

    private void chargerParkings() {
        try {
            System.out.println("Tentative chargement parkings...");
            List<Parking> parkings = daoParking.findAll();
            System.out.println("Nombre de parkings trouvés = " + parkings.size());

            for (Parking p : parkings) {
                System.out.println("Parking: " + p.getNom());
                vue.addParking(p, this::onParkingSelected);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Erreur lors du chargement des parkings. Vérifiez la connexion à la base de données.");
        }
    }

    private void onParkingSelected(Parking parking) {
        SaisirHeureArriveParking vueSuivante =
                new SaisirHeureArriveParking(parking);

        vueSuivante.setVisible(true);
        vue.dispose();
    }

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(() -> {
            ChoixParking vue = new ChoixParking();
            new ControleurChoixParking(vue);
            vue.setVisible(true);
        });
    }
}
