package controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

import javax.swing.JOptionPane;

import modele.Parking;
import vue.SaisirHeureArriveParking;
import vue.TicketParking;

public class ControleurSaisirHeureArriveParking implements ActionListener {

    public enum Etat {
        ATTENTE_HEURE, DEMARRER_STATIONNEMENT
    }

    private Etat etat;
    private final SaisirHeureArriveParking vue;
    private final DateTimeFormatter formatHeure = DateTimeFormatter.ofPattern("HH:mm");
    
    
    
    public ControleurSaisirHeureArriveParking(SaisirHeureArriveParking vue) {
        this.vue = vue;
        this.etat = Etat.ATTENTE_HEURE;

        /*
        if (vue.parking != null) {
            vue.getLblParkingInfo().setText(vue.parking.getNom());
        } else {
            vue.getLblParkingInfo().setText("Aucun parking sélectionné");
        }

        // Attach controller as listener
        vue.getBtnPayment().addActionListener(this);
        vue.getBtnMaintenant().addActionListener(this);
        */
    }

    
    public static double calculerPrixTotal(Parking parking, String strHeureArrivee) {
    	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
    	LocalTime heureArrivee = LocalTime.parse(strHeureArrivee, formatter);
    	
    	long minutesGarees = ChronoUnit.MINUTES.between(heureArrivee, LocalTime.now());
    	long nbQuartsHeure = (long) Math.ceil(minutesGarees / 15.0);
    	return nbQuartsHeure*parking.getTarif();
    }
    
    
    
    
    
    @Override
    public void actionPerformed(ActionEvent e) {
        Object src = e.getSource();
        /*
        if (src == vue.getBtnMaintenant()) {
            handleBtnMaintenant();
            return;
        }

        if (src == vue.getBtnPayment()) {
            handleBtnPayment();
        }
        */
    }

    private void handleBtnMaintenant() {
        LocalTime now = LocalTime.now();
        vue.getTextField().setText(now.format(formatHeure));
    }

    private void handleBtnPayment() {
        if (!verifierPlaque() || !verifierHeure()) {
            return;
        }

        etat = Etat.DEMARRER_STATIONNEMENT;
        ouvrirTicket();
    }

    private boolean verifierHeure() {
        String txt = vue.getTextField().getText().trim();
        LocalTime saisie;

        try {
            saisie = LocalTime.parse(txt, formatHeure);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(vue, "Format invalide. Exemple : 14:30");
            return false;
        }

        if (saisie.isAfter(LocalTime.now())) {
            JOptionPane.showMessageDialog(vue, "L'heure ne peut pas être supérieure à maintenant.");
            return false;
        }

        return true;
    }

    private boolean verifierPlaque() {
        /*String plaque = vue.getPlaque().getText().trim();

        if (plaque.isEmpty()) {
            JOptionPane.showMessageDialog(vue, "La plaque ne peut pas être vide.");
            return false;
        }

        // Format: AB-123-CD
        if (!plaque.matches("(?i)[A-Z]{2}-\\d{3}-[A-Z]{2}")) {
            JOptionPane.showMessageDialog(vue, "Format de plaque invalide. Exemple : AB-123-CD");
            return false;
        } */

        return true;
    }

    private void ouvrirTicket() {
    	/*
        String plaque = vue.getPlaque().getText().trim();
        String heure = vue.getTextField().getText().trim();
        String nomParking = vue.getLblParkingInfo().getText();

        TicketParking ticket = new TicketParking();
        ticket.remplirInfos("#P-00001", nomParking, plaque, heure, "Carte Bancaire");
        ticket.setVisible(true);

        vue.dispose();
        */
    }
}
