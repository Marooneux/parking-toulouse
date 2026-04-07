package controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.time.LocalDateTime;

import javax.swing.JOptionPane;

import modele.ReservationVoirie;
import modele.dao.DaoReservationVoirie;
import modele.dao.MySQLDataSource;
import utils.AuthManager;
import vue.ConfirmationPaiementVoirie;
import vue.NavigationFrame;
import vue.TicketVoirie;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ControleurConfirmationPaiementVoirie implements ActionListener {
	private static final Logger LOGGER = Logger.getLogger(ControleurConfirmationPaiementVoirie.class.getName());


    private final ConfirmationPaiementVoirie vue;

    public ControleurConfirmationPaiementVoirie(ConfirmationPaiementVoirie vue) {
        this.vue = vue;
        this.vue.getBtnTerminer().addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (AuthManager.getCurrentUser() == null) {
            JOptionPane.showMessageDialog(vue, "Connexion requise pour enregistrer la réservation.");
            return;
        }

        ReservationVoirie reservation = new ReservationVoirie(0, LocalDateTime.now(), vue.getDuree(), vue.getZone(), AuthManager.getCurrentUser());
        try {
            MySQLDataSource.creerAcces();
            new DaoReservationVoirie().create(reservation);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(vue, "Erreur lors de l'enregistrement de la réservation.");
            LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
            return;
        }

        TicketVoirie ticket = new TicketVoirie(
                vue.getZone(),
                vue.getImmatriculation(),
                vue.getDuree(),
                vue.getMoyenPaiement());
        new ControleurTicketVoirie(ticket);
        String key = "voirie-ticket-" + vue.getImmatriculation() + "-" + vue.getDuree();
        NavigationFrame.getInstance().showPage(key, () -> ticket, "Ticket voirie", true);
    }
}
