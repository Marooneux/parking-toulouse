package controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import modele.ReservationParking;
import modele.Utilisateur;
import modele.dao.DaoReservationParking;
import modele.dao.MySQLDataSource;
import utils.AuthManager;
import vue.ChoixTypeStationnement;
import vue.NavigationFrame;
import vue.ChoixMoyenPaiementParking;
import vue.TicketParking;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ControleurTicketParking implements ActionListener {
	private static final Logger LOGGER = Logger.getLogger(ControleurTicketParking.class.getName());


	private final TicketParking vue;

	public ControleurTicketParking(TicketParking vue) {
		this.vue = vue;
		this.vue.getBtnPaiement().addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (vue.isConfirmationRequise()) {
			confirmerTicket();
			return;
		}
		allerAuPaiement();
	}

	private void confirmerTicket() {
		Utilisateur user = AuthManager.getCurrentUser();
		if (user == null) {
			JOptionPane.showMessageDialog(vue, "Vous devez être connecté pour confirmer.");
			return;
		}

		try {
			MySQLDataSource.creerAcces();
			DaoReservationParking dao = new DaoReservationParking();
			ReservationParking active = dao.findActiveByUserId(user.getId());
			if (active != null) {
				JOptionPane.showMessageDialog(vue, "Vous êtes déjà garé. Quittez d'abord le parking.");
				return;
			}

			ReservationParking reservation = vue.getReservation();
			reservation.setUtilisateur(user);
			dao.create(reservation);

			NavigationFrame.getInstance().showPage(
					"Stationnement",
					() -> new ChoixTypeStationnement(user.getId()),
					"Stationnement",
					true);
		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(vue, "Erreur lors de la confirmation du ticket.");
			LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
		}
	}

	private void allerAuPaiement() {
		try {
			double prix = ControleurSaisirHeureArriveParking.calculerPrixTotal(vue.getParking(), vue.getHeureArrivee());
			ChoixMoyenPaiementParking choix = new ChoixMoyenPaiementParking(vue.getReservation(), prix);
			new ControleurChoixMoyenPaiementParking(choix, vue.getReservation());
			NavigationFrame.getInstance().showPage("parking-choix-paiement", () -> choix, "Choisir le paiement", true);
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(vue, "Impossible d'ouvrir le paiement.");
			LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
		}
	}
}
