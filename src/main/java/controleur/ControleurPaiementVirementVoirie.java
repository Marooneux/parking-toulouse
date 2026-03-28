package controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import vue.ConfirmationPaiementVoirie;
import vue.PaiementVirementVoirie;
import vue.NavigationFrame;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ControleurPaiementVirementVoirie implements ActionListener {
	private static final Logger LOGGER = Logger.getLogger(ControleurPaiementVirementVoirie.class.getName());


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
            new ControleurConfirmationPaiementVoirie(confirmation);
            String key = "voirie-confirmation-" + vue.getImmatriculation() + "-" + vue.getDuree();
            NavigationFrame.getInstance().showPage(key, () -> confirmation, "Paiement validé", true);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(vue, "Impossible d'ouvrir la confirmation de paiement.");
            LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
        }
    }
}
