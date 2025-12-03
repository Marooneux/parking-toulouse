package controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import javax.swing.JOptionPane;

import vue.SaisirHeureArriveParking;
import vue.TicketParking;

public class ControleurSaisirHeureArriveParking implements ActionListener {

    public enum Etat {
        ATTENTE_HEURE, DEMARRER_STATIONNEMENT
    }

    private Etat etat;
    private SaisirHeureArriveParking vue;

    public ControleurSaisirHeureArriveParking(SaisirHeureArriveParking vue) {
        this.vue = vue;
        this.etat = Etat.ATTENTE_HEURE;

        // attach listeners
        vue.getBtnPayment().addActionListener(this);
        vue.getBtnMaintenant().addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vue.getBtnMaintenant()) {
            // fill current time
            LocalTime now = LocalTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
            vue.getTextField().setText(now.format(formatter));
            return;
        }

        if (etat == Etat.ATTENTE_HEURE) {
            String heure = vue.getTextField().getText();
            if (heure == null || heure.trim().isEmpty()) {
                JOptionPane.showMessageDialog(vue, "Veuillez saisir l'heure avant de continuer.");
                return;
            }
            etat = Etat.DEMARRER_STATIONNEMENT;
        }

        if (etat == Etat.DEMARRER_STATIONNEMENT) {
            // move to ticket page
            TicketParking ticketPage = new TicketParking();
            ticketPage.setVisible(true);
            vue.dispose();
        }
    }
}
