package controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import modele.ZoneVoirie;
import vue.ChoixMoyenPaiementVoirie;
import vue.PaiementVirementVoirie;
import vue.PaiementVoirie;
import vue.NavigationFrame;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ControleurChoixMoyenPaiementVoirie implements ActionListener {
	private static final Logger LOGGER = Logger.getLogger(ControleurChoixMoyenPaiementVoirie.class.getName());


    private final ChoixMoyenPaiementVoirie vue;
    private final ZoneVoirie zone;
    private final String immatriculation;
    private final int duree;
    private final double prix;

    public ControleurChoixMoyenPaiementVoirie(ChoixMoyenPaiementVoirie vue, ZoneVoirie zone, String immatriculation, int duree, double prix) {
        this.vue = vue;
        this.zone = zone;
        this.immatriculation = immatriculation;
        this.duree = duree;
        this.prix = prix;

        vue.addCarteListener(this);
        vue.addVirementListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        try {
            if ("CARTE".equals(command)) {
                PaiementVoirie pagePaiementCB = new PaiementVoirie(zone, immatriculation, duree, prix);
                new ControleurPaiementVoirie(pagePaiementCB);
                String key = "voirie-paiement-carte-" + immatriculation + "-" + duree;
                NavigationFrame.getInstance().showPage(key, () -> pagePaiementCB, "Paiement par carte", true);
                return;
            }

            if ("VIREMENT".equals(command)) {
                PaiementVirementVoirie pageVirement = new PaiementVirementVoirie(zone, immatriculation, duree, prix);
                new ControleurPaiementVirementVoirie(pageVirement);
                String key = "voirie-paiement-virement-" + immatriculation + "-" + duree;
                NavigationFrame.getInstance().showPage(key, () -> pageVirement, "Paiement par virement", true);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(vue, "Erreur lors de l'ouverture du paiement.");
            LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
        }
    }
}
