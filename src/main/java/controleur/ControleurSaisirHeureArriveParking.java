package controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;

import javax.swing.JOptionPane;

import modele.Parking;
import modele.ReservationParking;
import vue.SaisirHeureArriveParking;
import vue.TicketParking;

public class ControleurSaisirHeureArriveParking implements ActionListener {

    private final SaisirHeureArriveParking vue;
    private final Parking parking;
    private final DateTimeFormatter formatHeure = DateTimeFormatter.ofPattern("HH:mm");

    public ControleurSaisirHeureArriveParking(Parking parking) {
        this.parking = parking;
        this.vue = new SaisirHeureArriveParking(parking);

        this.vue.addConfirmerListener(this);
        this.vue.getBtnMaintenant().addActionListener(e -> remplirHeureActuelle());

        this.vue.setVisible(true);
    }

    private void remplirHeureActuelle() {
        String now = LocalTime.now().format(formatHeure);
        vue.getTextFieldHeure().setText(now);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vue.getBtnConfirmer()) {
            valider();
        }
    }

    private void valider() {
        String plaque = vue.getPlaque().getText().trim();
        String heure = vue.getTextFieldHeure().getText().trim();

        if (plaque.isEmpty()) {
            JOptionPane.showMessageDialog(vue, "La plaque est obligatoire.");
            return;
        }

        if (!plaque.matches("(?i)[A-Z]{2}-\\d{3}-[A-Z]{2}")) {
            JOptionPane.showMessageDialog(vue, "Format de plaque invalide. Exemple : AB-123-CD");
            return;
        }

        if (heure.isEmpty()) {
            JOptionPane.showMessageDialog(vue, "L'heure d'arrivée est obligatoire.");
            return;
        }

        try {
            LocalTime heureArrivee = LocalTime.parse(heure, formatHeure);

            if (heureArrivee.isAfter(LocalTime.now())) {
                JOptionPane.showMessageDialog(vue, "L'heure doit être antérieure à maintenant.");
                return;
            }

            LocalDateTime dateArrivee = LocalDateTime.of(LocalDate.now(), heureArrivee);
            ReservationParking reservation = new ReservationParking(dateArrivee, null, parking, null);

            new TicketParking(reservation, plaque, heure).setVisible(true);
            vue.dispose();

        } catch (DateTimeParseException ex) {
            JOptionPane.showMessageDialog(vue, "Format heure invalide (HH:mm).");
        }
    }

    // Calculate price based on quarter-hour increments
    public static double calculerPrixTotal(Parking parking, String strHeureArrivee) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        LocalTime heureArrivee = LocalTime.parse(strHeureArrivee, formatter);

        long minutesGarees = ChronoUnit.MINUTES.between(heureArrivee, LocalTime.now());
        long nbQuartsHeure = (long) Math.ceil(minutesGarees / 15.0);
        return nbQuartsHeure * parking.getTarif();
    }
}
