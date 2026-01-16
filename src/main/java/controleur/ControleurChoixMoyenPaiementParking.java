package controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import modele.ReservationParking;
import vue.ChoixMoyenPaiementParking;
import vue.PaiementParking;
import vue.PaiementVirementParking;

public class ControleurChoixMoyenPaiementParking implements ActionListener {

    private final ChoixMoyenPaiementParking vue;
    private final ReservationParking reservation;
    private final double prix;

    public ControleurChoixMoyenPaiementParking(ChoixMoyenPaiementParking vue, ReservationParking reservation, double prix) {
        this.vue = vue;
        this.reservation = reservation;
        this.prix = prix;

        vue.addCarteListener(this);
        vue.addVirementListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        try {
            if ("CARTE".equals(command)) {
                PaiementParking pagePaiementCB = new PaiementParking(reservation, prix);
                pagePaiementCB.setVisible(true);
                vue.dispose();
                return;
            }

            if ("VIREMENT".equals(command)) {
                PaiementVirementParking pageVirement = new PaiementVirementParking(reservation, prix);
                pageVirement.setVisible(true);
                vue.dispose();
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(vue, "Erreur lors de l'ouverture du paiement.");
            ex.printStackTrace();
        }
    }
}
