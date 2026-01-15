package controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import vue.ChoixMoyenPaiementParking;
import vue.TicketParking;

public class ControleurTicketParking implements ActionListener {

    private final TicketParking vue;

    public ControleurTicketParking(TicketParking vue) {
        this.vue = vue;
        this.vue.getBtnPaiement().addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        allerAuPaiement();
    }

    private void allerAuPaiement() {
        try {
            // Calcul du prix à partir du ticket
            // Les champs nécessaires sont dans la vue; on reconstruit via labels si besoin.
            // Ici, on suppose que le contrôleur de saisie fournit le calcul:
            // Pour rester cohérent, la vue calculait via ControleurSaisirHeureArriveParking.
            // On déléguera ce calcul ici en utilisant le même contrôleur.
            // Note: la vue connaît le prix via calcul précédent; pour simplicité on réouvre le choix paiement.

            double prix = ControleurSaisirHeureArriveParking.calculerPrixTotal(vue.getParking(), vue.getHeureArrivee());
            new ChoixMoyenPaiementParking(prix).setVisible(true);
            vue.dispose();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(vue, "Impossible d'ouvrir le paiement.");
            ex.printStackTrace();
        }
    }
}
