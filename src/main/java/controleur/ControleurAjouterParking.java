package controleur;

import javax.swing.JOptionPane;

import modele.Adresse;
import modele.Parking;
import modele.dao.DaoAdminParking;
import modele.dao.DaoParking;
import modele.dao.requetes.RequeteInsertAdminParking;
import vue.adminParking.Accueil;
import vue.adminParking.AjouterParking;
import vue.NavigationFrame;

public class ControleurAjouterParking {

	private AjouterParking vue;
	private DaoParking daoParking;
	private DaoAdminParking daoAdminParking;
	private int idAdmin;

	public ControleurAjouterParking(AjouterParking vue, int idAdmin) {
		this.vue = vue;
		this.daoParking = new DaoParking();
		this.daoAdminParking = new DaoAdminParking();
		this.idAdmin = idAdmin;
		this.initListeners();
	}

	private void initListeners() {

		this.vue.getBtnAnnuler().addActionListener(e -> this.fermer());

		this.vue.getBtnValider().addActionListener(e -> this.valider());
	}

	private void fermer() {
		Accueil vueChoix = new Accueil(this.idAdmin);
		new ControleurAccueilAdminParking(vueChoix, this.idAdmin);
		NavigationFrame.getInstance().showPage("admin-home", () -> vueChoix, "Administration");
	}

	private void valider() {
		try {
			// Todo : Ajouter le bon adresse.
			Adresse adresse = null;
			Parking parking = new Parking(
					this.vue.getNom(),
					this.vue.getPlacesMax(),
					this.vue.getHauteur(),
					// this.vue.getPlacesOccupees(),
					this.vue.getHeureOuverture(),
					this.vue.getHeureFermeture(),
					this.vue.isContientPlacesMoto(),
					adresse,
					this.vue.getTarif()
			);

			System.out.println(this.idAdmin);
			this.daoParking.create(parking);
			RequeteInsertAdminParking.Association asso = new modele.dao.requetes.RequeteInsertAdminParking.Association(
					this.idAdmin, parking.getId());
			this.daoAdminParking.create(asso);

			JOptionPane.showMessageDialog(this.vue, "Parking ajouté avec succès");


			Accueil vueChoix = new Accueil(this.idAdmin);
			new ControleurAccueilAdminParking(vueChoix, this.idAdmin);
			NavigationFrame.getInstance().showPage("admin-home", () -> vueChoix, "Administration");

		} catch (Exception ex) {
			ex.printStackTrace();
			JOptionPane.showMessageDialog(this.vue, "Erreur lors de l'ajout");
		}

	}

}
