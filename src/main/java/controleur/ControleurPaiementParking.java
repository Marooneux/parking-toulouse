package controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.time.LocalDateTime;

import javax.swing.JOptionPane;

import modele.ReservationParking;
import modele.Utilisateur;
import modele.dao.DaoReservationParking;
import modele.dao.MySQLDataSource;
import utils.AuthManager;
import vue.ConfirmationPaiementParking;
import vue.PaiementParking;
import vue.NavigationFrame;

public class ControleurPaiementParking implements ActionListener {

    private final PaiementParking vue;

    public ControleurPaiementParking(PaiementParking vue) {
        this.vue = vue;
        this.vue.getBtnPayer().addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            if (!finaliserReservation()) {
                return;
            }
            ConfirmationPaiementParking confirmation = new ConfirmationPaiementParking(vue.getReservation(), vue.getPrix());
            new ControleurConfirmationPaiementParking(confirmation, vue.getReservation(), vue.getPrix());
            NavigationFrame.getInstance().showPage("parking-confirmation", () -> confirmation, "Paiement validé");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(vue, "Impossible d'ouvrir la confirmation de paiement.");
            ex.printStackTrace();
        }
    }

    private boolean finaliserReservation() {
        ReservationParking reservation = vue.getReservation();
        if (reservation == null) {
            JOptionPane.showMessageDialog(vue, "Réservation introuvable.");
            return false;
        }

        if (reservation.getDateDepart() == null) {
            reservation.setDateDepart(LocalDateTime.now());
        }
        reservation.setPrixPaye(vue.getPrix());

        try {
            MySQLDataSource.creerAcces();
            DaoReservationParking dao = new DaoReservationParking();
            if (reservation.getId() == 0) {
                Utilisateur user = AuthManager.getCurrentUser();
                if (user != null) {
                    reservation.setUtilisateur(user);
                }
                dao.create(reservation);
            } else {
                dao.update(reservation);
            }
            return true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(vue, "Erreur lors de l'enregistrement du paiement.");
            ex.printStackTrace();
            return false;
        }
    }
}
