package controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import vue.ChoixMoyenPaiementParking;
import vue.PaiementVirementParking;

public class ControleurChoixMoyenPaiementParkingVirement implements ActionListener {
    private final ChoixMoyenPaiementParking vue;

    public ControleurChoixMoyenPaiementParkingVirement(ChoixMoyenPaiementParking vue) {
        this.vue = vue;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            PaiementVirementParking pageVirement = new PaiementVirementParking(vue.getPrix());
            pageVirement.setVisible(true);
            vue.dispose();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}