package controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.List;

import javax.swing.JOptionPane;

import modele.Parking;
import modele.ReservationParking;
import modele.Vehicule;
import modele.dao.DaoReservationParking;
import modele.dao.DaoVehicule;
import modele.dao.MySQLDataSource;
import utils.AuthManager;
import vue.NavigationFrame;
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
        this.prefillPlaque();

        NavigationFrame.getInstance().showPage("parking-arrivee", () -> this.vue, "Démarrer le stationnement");
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
            if (AuthManager.getCurrentUser() == null) {
                JOptionPane.showMessageDialog(vue, "Vous devez être connecté pour réserver.");
                return;
            }

            ReservationParking reservation = new ReservationParking(dateArrivee, null, parking, AuthManager.getCurrentUser());

            try {
                MySQLDataSource.creerAcces();
                new DaoReservationParking().create(reservation);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(vue, "Erreur lors de l'enregistrement de la réservation.");
                ex.printStackTrace();
                return;
            }

            TicketParking ticket = new TicketParking(reservation, plaque, heure);
            new ControleurTicketParking(ticket);
            NavigationFrame.getInstance().showPage("parking-ticket", () -> ticket, "Ticket parking");

        } catch (DateTimeParseException ex) {
            JOptionPane.showMessageDialog(vue, "Format heure invalide (HH:mm).");
        }
    }

    private void prefillPlaque() {
        try {
            if (AuthManager.getCurrentUser() == null) {
                return;
            }
            MySQLDataSource.creerAcces();
            DaoVehicule daoVehicule = new DaoVehicule();
            int userId = AuthManager.getCurrentUser().getId();
            List<Vehicule> vehicules = daoVehicule.findByUserId(userId);
            Vehicule vehicule = vehicules.isEmpty() ? null : vehicules.getFirst();
            if (vehicule != null) {
                vue.getPlaque().setText(vehicule.getImmatriculation());
            }
        } catch (Exception ignored) {
            // Pré-remplissage non bloquant
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
