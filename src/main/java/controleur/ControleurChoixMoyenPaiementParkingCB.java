package controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import vue.ChoixMoyenPaiementParking;
import vue.PaiementParking;

public class ControleurChoixMoyenPaiementParkingCB implements ActionListener {
    private final ChoixMoyenPaiementParking vue;

    public ControleurChoixMoyenPaiementParkingCB(ChoixMoyenPaiementParking vue) {
        this.vue = vue;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            PaiementParking pagePaiementCB = new PaiementParking(vue.getPrix());
            pagePaiementCB.setVisible(true);
            vue.dispose();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}