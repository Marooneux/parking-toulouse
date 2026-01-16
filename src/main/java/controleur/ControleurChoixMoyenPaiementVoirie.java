package controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import modele.ZoneVoirie;
import vue.ChoixMoyenPaiementVoirie;
import vue.PaiementVirementVoirie;
import vue.PaiementVoirie;

public class ControleurChoixMoyenPaiementVoirie implements ActionListener {

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
                pagePaiementCB.setVisible(true);
                vue.dispose();
                return;
            }

            if ("VIREMENT".equals(command)) {
                PaiementVirementVoirie pageVirement = new PaiementVirementVoirie(zone, immatriculation, duree, prix);
                pageVirement.setVisible(true);
                vue.dispose();
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(vue, "Erreur lors de l'ouverture du paiement.");
            ex.printStackTrace();
        }
    }
}
