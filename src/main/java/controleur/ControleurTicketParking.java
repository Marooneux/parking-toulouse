package controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import vue.PaiementParking;
import vue.TicketParking;

public class ControleurTicketParking {

    private final TicketParking vue;

    public ControleurTicketParking(TicketParking vue) {
        this.vue = vue;
        attachListeners();
    }

    private void attachListeners() {
        vue.getBtnPaiement().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                allerAuPaiement();
            }
        });
    }

   

    private void allerAuPaiement() {
        try {
            PaiementParking paiement = new PaiementParking();
            paiement.setVisible(true);
            vue.dispose();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(vue, "Impossible d'ouvrir le paiement.");
            ex.printStackTrace();
        }
    }
}
