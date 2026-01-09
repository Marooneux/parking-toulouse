package controleur;

import javax.swing.JOptionPane;

import modele.Parking;
import modele.dao.DaoParking;
import vue.adminParking.Accueil;
import vue.adminParking.AjouterParking;

public class ControleurAjouterParking {

    private AjouterParking vue;
    private DaoParking daoParking;

    public ControleurAjouterParking(AjouterParking vue) {
        this.vue = vue;
        this.daoParking = new DaoParking();
        initListeners();
    }

    private void initListeners() {

        vue.getBtnAnnuler().addActionListener(e -> fermer());

        vue.getBtnValider().addActionListener(e -> valider());
    }

    private void fermer() {
    	 vue.dispose(); // ferme la page modifier

    	 Accueil vueChoix = new Accueil();
    	 new ControleurAccueilAdminParking(vueChoix);
    	 vueChoix.setVisible(true);
    }

    private void valider() {
    	try {
    		Parking parking = new Parking(
    				vue.getNom(), 
    				vue.getAdresse(), 
    				vue.getTarif(), 
    				vue.getPlacesMax(), 
    				vue.getHauteur(),
    				vue.getHeureOuverture(),
    				vue.getHeureFermeture(),
    				vue.isContientPlacesMoto()
    				);

            daoParking.create(parking);
            
            JOptionPane.showMessageDialog(vue, "Parking ajouté avec succès");
    		
            vue.dispose();
            Accueil vueChoix = new Accueil();
            new ControleurAccueilAdminParking(vueChoix);
            vueChoix.setVisible(true);
            
    	} catch (Exception ex) {
    		ex.printStackTrace();
    		JOptionPane.showMessageDialog(vue, "Erreur lors de l'ajout");
    	}
    	
    }
    
}
