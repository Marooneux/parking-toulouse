package controleur;

import java.sql.SQLException; 
import java.util.List;

import javax.swing.JOptionPane;

import modele.Parking;
import modele.dao.DaoParking;
import modele.dao.MySQLDataSource;
import vue.ChoixParking;
import vue.SaisirHeureArriveParking;
import vue.ModifierParking;


public class ControleurChoixParking {

    private ChoixParking vue;
    private DaoParking daoParking;

    public ControleurChoixParking(ChoixParking vue) {
        this.vue = vue;
        this.daoParking = new DaoParking();

        MySQLDataSource.creerAcces("root", "$iutinfo"); 

        chargerParkings();
        vue.setVisible(true);
    }

    private void chargerParkings() {
        try {
            //System.out.println("Tentative chargement parkings...");
            List<Parking> parkings = daoParking.findAll();
            //System.out.println("Nombre de parkings trouvés = " + parkings.size());

            for (Parking p : parkings) {
                //System.out.println("Parking: " + p.getNom());
            	vue.addParking(p, this::onParkingSelected, 
            			this::ouvrirPageModification, 
            			this::supprimerParking );
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
    
    private void ouvrirPageModification(Parking parking) {
        ModifierParking vueModif = new ModifierParking(parking);  // nouvelle page pour modification
        new ControleurModifierParking(vueModif, parking);                 // crée son contrôleur
        vueModif.setVisible(true);
        vue.dispose();
    }
    
    

    // not finished
    private void supprimerParking(Parking parking) {
        int confirm = JOptionPane.showConfirmDialog(vue,
                "Voulez-vous vraiment supprimer ce parking ?",
                "Confirmation",
                JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            try {
                daoParking.delete(parking); // assuming Parking has getId() and DaoParking has delete(id)  
                // remove from UI
                JOptionPane.showMessageDialog(vue, "Parking supprimé avec succès !");
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(vue, "Erreur lors de la suppression du parking.");
            }
        }
    }
    //---
    

    
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(() -> {
            ChoixParking vue = new ChoixParking();
            new ControleurChoixParking(vue);
            vue.setVisible(true);
        });
    }
}
