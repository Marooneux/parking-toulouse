package controleur;

import javax.swing.JOptionPane;

import com.sun.nio.sctp.Association;

import modele.Parking;
import modele.dao.DaoAdminParking;
import modele.dao.DaoParking;
import modele.dao.requetes.RequeteInsertAdminParking;
import vue.adminParking.Accueil;
import vue.adminParking.AjouterParking;

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
        initListeners();
    }

    private void initListeners() {

        vue.getBtnAnnuler().addActionListener(e -> fermer());

        vue.getBtnValider().addActionListener(e -> valider());
    }

    private void fermer() {
    	 vue.dispose(); // ferme la page modifier

    	 Accueil vueChoix = new Accueil(idAdmin);
    	 new ControleurAccueilAdminParking(vueChoix, idAdmin);
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
    		
    		
    		System.out.println(idAdmin);
            daoParking.create(parking);
            RequeteInsertAdminParking.Association asso = new modele.dao.requetes.RequeteInsertAdminParking.Association(idAdmin, parking.getId());
            daoAdminParking.create(asso);
            
            JOptionPane.showMessageDialog(vue, "Parking ajouté avec succès");
    		
            vue.dispose();
            Accueil vueChoix = new Accueil(idAdmin);
            new ControleurAccueilAdminParking(vueChoix, idAdmin);
            vueChoix.setVisible(true);
            
    	} catch (Exception ex) {
    		ex.printStackTrace();
    		JOptionPane.showMessageDialog(vue, "Erreur lors de l'ajout");
    	}
    	
    }
    
}
