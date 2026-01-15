package controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import modele.StationnementVoirie;
import vue.SaisirDureeStationnement;

/**
 * Contrôleur dédié au bouton de confirmation dans SaisirDureeStationnement.
 * Choisit entre ouvrir le paiement ou générer un ticket gratuit selon la zone et la durée.
 */
public class ControleurSaisirDureeStationnementVue implements ActionListener {

    private final SaisirDureeStationnement vue;
    private final StationnementVoirie zone;

    public ControleurSaisirDureeStationnementVue(SaisirDureeStationnement vue, StationnementVoirie zone) {
        this.vue = vue;
        this.zone = zone;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String strDuree = vue.getDureeSaisie();
        String immatriculation = vue.getPlaqueSaisie();

        if (immatriculation == null || immatriculation.trim().isEmpty()) {
            JOptionPane.showMessageDialog(vue, "Veuillez saisir votre plaque d'immatriculation avant de payer.");
            return;
        }
        if (strDuree == null || strDuree.trim().isEmpty()) {
            JOptionPane.showMessageDialog(vue, "Veuillez saisir une durée avant de payer.");
            return;
        }
        int intDuree;
        try {
            intDuree = Integer.parseInt(strDuree);
            if (intDuree > zone.getDureeMax()) {
                JOptionPane.showMessageDialog(vue, "La durée saisie est supérieure à la durée maximum de cette zone.");
                return;
            } else {
                double prix = ControleurSaisirDureeStationnement.calculerPrixTotal(zone, intDuree);
                if (prix == 0) {
                    ControleurSaisirDureeStationnement.ouvrirTicket(zone, immatriculation, intDuree);
                    vue.dispose();
                } else {
                    ControleurSaisirDureeStationnement.ouvrirPaiement(zone, immatriculation, intDuree, prix);
                    vue.dispose();
                }
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(vue, "Veuillez entrer une durée en minutes uniquement.");
        }
    }
}
