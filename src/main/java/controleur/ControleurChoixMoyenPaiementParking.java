package controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import modele.ReservationParking;
import vue.ChoixMoyenPaiementParking;
import vue.PaiementParking;
import vue.PaiementVirementParking;
import vue.NavigationFrame;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ControleurChoixMoyenPaiementParking implements ActionListener {

    private final ChoixMoyenPaiementParking vue;
    private final ReservationParking reservation;
    private final double prix;

    public ControleurChoixMoyenPaiementParking(ChoixMoyenPaiementParking vue, ReservationParking reservation) {
        this.vue = vue;
        this.reservation = reservation;
        this.prix = reservation.calculerPrixTotal();

        vue.addCarteListener(this);
        vue.addVirementListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        try {
            if ("CARTE".equals(command)) {
                PaiementParking pagePaiementCB = new PaiementParking(reservation);
                new ControleurPaiementParking(pagePaiementCB);
                NavigationFrame.getInstance().showPage("parking-paiement-carte", () -> pagePaiementCB, "Paiement par carte", true);
                return;
            }

            if ("VIREMENT".equals(command)) {
                PaiementVirementParking pageVirement = new PaiementVirementParking(reservation, prix);
                new ControleurPaiementVirementParking(pageVirement);
                NavigationFrame.getInstance().showPage("parking-paiement-virement", () -> pageVirement, "Paiement par virement", true);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(vue, "Erreur lors de l'ouverture du paiement.");
            LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
        }
    }
}
