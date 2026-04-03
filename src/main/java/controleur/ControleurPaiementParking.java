package controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.time.LocalDateTime;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import modele.ReservationParking;
import modele.Utilisateur;
import modele.dao.DaoReservationParking;
import modele.dao.MySQLDataSource;
import utils.AuthManager;
import vue.ConfirmationPaiementParking;
import vue.NavigationFrame;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ControleurPaiementParking implements ActionListener {
	private static final Logger LOGGER = Logger.getLogger(ControleurPaiementParking.class.getName());

	private final JPanel vue;
	private final double prix;
	private final ReservationParking reservation;

	public ControleurPaiementParking(JPanel vue, JButton btnPayer, double prix, ReservationParking reservation) {
		this.vue = vue;
		this.prix = prix;
		this.reservation = reservation;
		btnPayer.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			if (!finaliserReservation()) {
				return;
			}
			ConfirmationPaiementParking confirmation = new ConfirmationPaiementParking(reservation);
			new ControleurConfirmationPaiementParking(confirmation, reservation, prix);
			NavigationFrame.getInstance().showPage("parking-confirmation", () -> confirmation, "Paiement validé", true);
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(vue, "Impossible d'ouvrir la confirmation de paiement.");
			LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
		}
	}

	private boolean finaliserReservation() {
		if (reservation == null) {
			JOptionPane.showMessageDialog(vue, "Réservation introuvable.");
			return false;
		}

		if (reservation.getDateDepart() == null) {
			reservation.setDateDepart(LocalDateTime.now());
		}
		reservation.setPrixPaye(prix);

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
			LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
			return false;
		}
	}
}
