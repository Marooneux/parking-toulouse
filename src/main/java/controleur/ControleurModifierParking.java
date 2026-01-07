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
            // 1. Récupération des valeurs depuis la vue
            parking.setNom(vue.getNom());
            parking.setAdresse(vue.getAdresse());
            parking.setTarif(vue.getTarif());
            parking.setNbPlacesMax(vue.getPlacesMax());

            // 2. UPDATE en base
            daoParking.update(parking);

            // 3. Feedback utilisateur
            JOptionPane.showMessageDialog(
                    vue,
                    "Parking modifié avec succès",
                    "Succès",
                    JOptionPane.INFORMATION_MESSAGE
            );

            // 4. Retour à ChoixParking
            vue.dispose();
            ChoixParking vueChoix = new ChoixParking();
            new ControleurChoixParking(vueChoix);
            vueChoix.setVisible(true);

        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(
                    vue,
                    "Erreur lors de la modification",
                    "Erreur",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }
}
