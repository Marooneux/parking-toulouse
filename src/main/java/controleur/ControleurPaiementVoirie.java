package controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import vue.ConfirmationPaiementVoirie;
import vue.PaiementVoirie;
import vue.NavigationFrame;

public class ControleurPaiementVoirie implements ActionListener {

    private final PaiementVoirie vue;

    public ControleurPaiementVoirie(PaiementVoirie vue) {
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
                    "Carte bancaire");
            new ControleurConfirmationPaiementVoirie(confirmation);
            String key = "voirie-confirmation-" + vue.getImmatriculation() + "-" + vue.getDuree();
            NavigationFrame.getInstance().showPage(key, () -> confirmation, "Paiement validé");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(vue, "Impossible d'ouvrir la confirmation de paiement.");
            ex.printStackTrace();
        }
    }
}
