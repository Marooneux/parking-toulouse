package controleur;

import javax.swing.JOptionPane;

import modele.Parking;
import modele.dao.DaoParking;
import vue.adminParking.Accueil;
import vue.adminParking.ModifierParking;
import vue.NavigationFrame;

public class ControleurModifierParking {

    private ModifierParking vue;
    private Parking parking;
    private DaoParking daoParking;
    private int idAdmin;

    public ControleurModifierParking(ModifierParking vue, Parking parking, int idAdmin) {
        this.vue = vue;
        this.parking = parking;
        this.daoParking = new DaoParking();
        this.idAdmin = idAdmin;
        initListeners();
    }

    private void initListeners() {

        vue.getBtnAnnuler().addActionListener(e -> fermer());

        vue.getBtnValider().addActionListener(e -> valider());
    }

    private void fermer() {
     	 Accueil vueChoix = new Accueil(idAdmin);
     	 new ControleurAccueilAdminParking(vueChoix, idAdmin);
     	 NavigationFrame.getInstance().showPage("admin-home", () -> vueChoix, "Administration");
    }

    private void valider() {
    	try {
    		
    		parking.setNom(vue.getNom());
    		parking.setAdresse(vue.getAdresse());
    		parking.setTarif(vue.getTarif());
    		parking.setCapacite(vue.getPlacesMax());
    		
    		parking.setHauteurMax(vue.getHauteur());
    		parking.setHoraireOuverture(vue.getHeureOuverture());
            parking.setHoraireFermeture(vue.getHeureFermeture());
            parking.setContientPlacesMoto(vue.isContientPlacesMoto());
			
            daoParking.update(parking);
			
            JOptionPane.showMessageDialog(vue, "Parking modifié avec succès");
			
            Accueil vueChoix = new Accueil(idAdmin);
            new ControleurAccueilAdminParking(vueChoix, idAdmin);
            NavigationFrame.getInstance().showPage("admin-home", () -> vueChoix, "Administration");
            
    	} catch (Exception ex) {
    		ex.printStackTrace();
    		JOptionPane.showMessageDialog(vue, "Erreur lors de la modification");
    	}
    	
    }
    
}
