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

public class ControleurAccueilAdminParking {

	private Accueil vue;
	private DaoParking daoParking;
	private int idAdmin;

	public ControleurAccueilAdminParking(Accueil vue, int idAdmin) {
		this.vue = vue;
		this.daoParking = new DaoParking();
		this.idAdmin = idAdmin;

		MySQLDataSource.creerAcces();

		this.chargerParkings();
		vue.setVisible(true);
	}

	private void chargerParkings() {
		try {
			this.vue.videListe();
			List<Parking> parkings = this.daoParking.findByAdminId(this.idAdmin);

			for (Parking p : parkings) {
				this.vue.addParking(p, this::onParkingSelected, this::ouvrirPageModification, this::supprimerParking);
			}

			this.vue.actualiserAffichage();
		} catch (SQLException e) {
			e.printStackTrace();
			System.err.println("Erreur lors du chargement des parkings. Vérifiez la connexion à la base de données.");
		}
	}

    public void ouvrirPageAjouter(int idAdmin) {
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
	private void onParkingSelected(Parking parking) {
		GestionParking vueSuivante = new GestionParking(parking);

		vueSuivante.setVisible(true);
	}

	private void supprimerParking(Parking parking) {
		int confirm = JOptionPane.showConfirmDialog(this.vue,
				"Voulez-vous vraiment supprimer ce parking ?",
				"Confirmation",
				JOptionPane.YES_NO_OPTION);
		if (confirm == JOptionPane.YES_OPTION) {
			try {
				this.daoParking.delete(parking);
				JOptionPane.showMessageDialog(this.vue, "Parking supprimé avec succès !");
				this.vue.dispose();
				Accueil vue = new Accueil(this.idAdmin);
				new ControleurAccueilAdminParking(vue, this.idAdmin);
				vue.setVisible(true);
			} catch (SQLException ex) {
				ex.printStackTrace();
				JOptionPane.showMessageDialog(this.vue, "Erreur lors de la suppression du parking.");
			}
		}
	}

}
