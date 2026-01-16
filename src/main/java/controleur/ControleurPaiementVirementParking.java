package controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import vue.ConfirmationPaiementParking;
import vue.PaiementVirementParking;
import vue.NavigationFrame;

public class ControleurPaiementVirementParking implements ActionListener {

    private final PaiementVirementParking vue;

    public ControleurPaiementVirementParking(PaiementVirementParking vue) {
        this.vue = vue;
        this.vue.getBtnPayer().addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            ConfirmationPaiementParking confirmation = new ConfirmationPaiementParking(vue.getReservation(), vue.getPrix());
            new ControleurConfirmationPaiementParking(confirmation, vue.getReservation(), vue.getPrix());
            NavigationFrame.getInstance().showPage("parking-confirmation", () -> confirmation, "Paiement validé");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(vue, "Impossible d'ouvrir la confirmation de paiement.");
            ex.printStackTrace();
        }
    }
}
