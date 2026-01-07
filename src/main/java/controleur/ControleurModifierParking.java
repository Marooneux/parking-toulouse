package controleur;

import javax.swing.JOptionPane;

import modele.Parking;
import modele.dao.DaoParking;
import vue.ChoixParking;
import vue.ModifierParking;

public class ControleurModifierParking {

    private ModifierParking vue;
    private Parking parking;
    private DaoParking daoParking;

    public ControleurModifierParking(ModifierParking vue, Parking parking) {
        this.vue = vue;
        this.parking = parking;
        this.daoParking = new DaoParking();
        initListeners();
    }

    private void initListeners() {

        vue.getBtnAnnuler().addActionListener(e -> fermer());

        vue.getBtnValider().addActionListener(e -> valider());
    }

    private void fermer() {
    	 vue.dispose(); // ferme la page modifier

    	    ChoixParking vueChoix = new ChoixParking();
    	    new ControleurChoixParking(vueChoix);
    	    vueChoix.setVisible(true);
    }

    private void valider() {
    	try {
    		System.out.println("UPDATE parking ID = " + parking.getId());
    		parking.setNom(vue.getNom());
    		parking.setAdresse(vue.getAdresse());
    		parking.setTarif(vue.getTarif());
    		parking.setNbPlacesMax(vue.getPlacesMax());
    		
    		parking.setHauteur(vue.getHauteur());
    		parking.setHeureOuverture(vue.getHeureOuverture());
            parking.setHeureFermeture(vue.getHeureFermeture());
            parking.setContientPlacesMoto(vue.isContientPlacesMoto());
            
            daoParking.update(parking);
            
            JOptionPane.showMessageDialog(vue, "Parking modifié avec succès");
    		
            vue.dispose();
            ChoixParking vueChoix = new ChoixParking();
            new ControleurChoixParking(vueChoix);
            vueChoix.setVisible(true);
            
    	} catch (Exception ex) {
    		ex.printStackTrace();
    		JOptionPane.showMessageDialog(vue, "Erreur lors de la modification");
    	}
    	
    }
    
}
