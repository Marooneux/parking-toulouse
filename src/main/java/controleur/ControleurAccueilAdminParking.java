package controleur;

import java.sql.SQLException; 
import java.util.List;

import javax.swing.JOptionPane;

import modele.Parking;
import modele.dao.DaoParking;
import modele.dao.MySQLDataSource;
import vue.adminParking.Accueil;
import vue.adminParking.AjouterParking;
import vue.adminParking.GestionParking;
import vue.adminParking.ModifierParking;
import vue.SaisirHeureArriveParking;

public class ControleurAccueilAdminParking{

    private Accueil vue;
    private DaoParking daoParking;
    private int idAdmin;

    public ControleurAccueilAdminParking(Accueil vue, int idAdmin) {
        this.vue = vue;
        this.daoParking = new DaoParking();
        this.idAdmin = idAdmin;

        MySQLDataSource.creerAcces("root", "admin"); 

        chargerParkings();
        vue.setVisible(true);
    }

    private void chargerParkings() {
        try {
            vue.videListe();
            List<Parking> parkings = daoParking.findByAdminId(idAdmin);
            
            for (Parking p : parkings) {
                vue.addParking(p, this::onParkingSelected, this::ouvrirPageModification, this::supprimerParking);
            }

            vue.actualiserAffichage();
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Erreur lors du chargement des parkings. Vérifiez la connexion à la base de données.");
        }
    }
    

    private void onParkingSelected(Parking parking) {
        GestionParking vueSuivante = new GestionParking(parking);

        vueSuivante.setVisible(true);
    }
    
    public void ouvrirPageAjouter(int idAdmin) {
    	System.out.println(idAdmin);
    	AjouterParking vueAjout = new AjouterParking(idAdmin);
    	new ControleurAjouterParking(vueAjout, idAdmin);
    	vueAjout.setVisible(true);
    }
    
    private void ouvrirPageModification(Parking parking) {
        ModifierParking vueModif = new ModifierParking(parking);  // nouvelle page pour modification
        new ControleurModifierParking(vueModif, parking, idAdmin);                 // crée son contrôleur
        vueModif.setVisible(true);
        vue.dispose();
    }

    private void supprimerParking(Parking parking) {
        int confirm = JOptionPane.showConfirmDialog(vue,
                "Voulez-vous vraiment supprimer ce parking ?",
                "Confirmation",
                JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            try {
                daoParking.delete(parking);
                JOptionPane.showMessageDialog(vue, "Parking supprimé avec succès !");
                vue.dispose();
                Accueil vue = new Accueil(idAdmin);
                new ControleurAccueilAdminParking(vue, idAdmin);
                vue.setVisible(true);
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(vue, "Erreur lors de la suppression du parking.");
            }
        }
    }

}
