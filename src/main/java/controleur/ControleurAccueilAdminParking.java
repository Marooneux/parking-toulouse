package controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JOptionPane;

import modele.Parking;
import modele.dao.DaoParking;
import modele.dao.MySQLDataSource;
import modele.Utilisateur.Type;
import utils.AuthManager;
import vue.LoginPage;
import vue.NavigationFrame;
import vue.adminParking.Accueil;
import vue.adminParking.AjouterParking;
import vue.adminParking.ModifierParking;

public class ControleurAccueilAdminParking implements ActionListener {

	private final Accueil vue;
	private final DaoParking daoParking;
	private final int idAdmin;

	public ControleurAccueilAdminParking(Accueil vue, int idAdmin) {
		this.vue = vue;
		this.daoParking = new DaoParking();
		this.idAdmin = idAdmin;

		if (!AuthManager.ensureAuthorized(Type.SYSADMIN.name(), Type.PARKINGADMIN.name())) {
			NavigationFrame.getInstance().showPage("login", LoginPage::new, "Connexion");
			return;
		}

		MySQLDataSource.creerAcces();

		registerListeners();
		chargerParkings();
		NavigationFrame.getInstance().showPage("admin-home", () -> this.vue, "Administration");
	}

	private void registerListeners() {
		vue.addToggleListener(this);
		vue.addMenuParkingsListener(this);
		vue.addMenuStatsListener(this);
		vue.addAjouterListener(this);
		//vue.addRetourListener(this);
		
		vue.addShowListener(new java.awt.event.ComponentAdapter() {
	        @Override
	        public void componentShown(java.awt.event.ComponentEvent e) {
	            chargerParkings(); // Recharge la liste à chaque affichage
	        }
	    });
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
		NavigationFrame.getInstance().showPage("admin-add", () -> vueAjout, "Ajouter un parking");
	}
    
	private void ouvrirPageModification(Parking parking) {
		ModifierParking vueModif = new ModifierParking(parking);
		new ControleurModifierParking(vueModif, parking, idAdmin);
		String key = "admin-edit-" + parking.getId();
		NavigationFrame.getInstance().showPage(key, () -> vueModif, "Modifier le parking");
	}

	private void onParkingSelected(Parking parking) {
		vue.afficherFormulaireEdition(parking);
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
				chargerParkings();
				vue.showParkings();
			} catch (SQLException ex) {
				ex.printStackTrace();
				JOptionPane.showMessageDialog(this.vue, "Erreur lors de la suppression du parking.");
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		if (source == vue.getBtnValiderAjout()) {
			ouvrirPageAjouter(idAdmin);
			return;
		}
		if (source == vue.getBtnEnregistrerModification()
				|| source == vue.getBtnSidebarParkings()
				|| source == vue.getBtnRetourListe()) {
			vue.showParkings();
			return;
		}
		if (source == vue.getBtnSidebarStats()) {
			vue.showStats();
			return;
		}
		if (source == vue.getBtnToggle()) {
			vue.toggleSidebar();
		}
	}
}
