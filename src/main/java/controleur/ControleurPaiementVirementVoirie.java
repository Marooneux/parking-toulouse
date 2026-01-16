package controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import vue.ConfirmationPaiementVoirie;
import vue.PaiementVirementVoirie;

public class ControleurPaiementVirementVoirie implements ActionListener {

    private final PaiementVirementVoirie vue;

    public ControleurPaiementVirementVoirie(PaiementVirementVoirie vue) {
        this.vue = vue;
        this.vue.getBtnPayer().addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            ConfirmationPaiementVoirie confirmation = new ConfirmationPaiementVoirie(
                    vue.getZone(),
                    vue.getImmatriculation(),
                    vue.getDuree(),
                    vue.getPrix(),
                    "Virement bancaire");
            confirmation.setVisible(true);
            vue.dispose();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(vue, "Impossible d'ouvrir la confirmation de paiement.");
            ex.printStackTrace();
        }
    }
}
