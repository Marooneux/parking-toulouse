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
import vue.ChoixTypeStationnement;
import vue.ConfirmationPaiementParking;
import vue.NavigationFrame;

public class ControleurConfirmationPaiementParking implements ActionListener {

	private final ConfirmationPaiementParking vue;
	private final ReservationParking reservation;
	private final double prix;

	public ControleurConfirmationPaiementParking(ConfirmationPaiementParking vue, ReservationParking reservation, double prix) {
		this.vue = vue;
		this.reservation = reservation;
		this.prix = prix;
		this.vue.getBtnTerminer().addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		departConfirme();
	}

	private void departConfirme() {
		if (reservation.getDateDepart() == null) {
			LocalDateTime heureActuelle = LocalDateTime.now();
			reservation.setDateDepart(heureActuelle);
		}
		reservation.setPrixPaye(prix);
		try {
			DaoReservationParking dao = new DaoReservationParking();
			MySQLDataSource.creerAcces();

			if (reservation.getId() == 0) {
				if (AuthManager.getCurrentUser() != null) {
					reservation.setUtilisateur(AuthManager.getCurrentUser());
				}
				dao.create(reservation);
			} else {
				dao.update(reservation);
			}
	        Utilisateur user = AuthManager.getCurrentUser();
	        if (user == null) {
	            JOptionPane.showMessageDialog(vue, "Session expirée, veuillez vous reconnecter.");
	            return;
	        }
	        NavigationFrame.getInstance().showPage(
	                "Stationnement",
	                () -> new ChoixTypeStationnement(user.getId()),
	                "Stationnement",
	                true);
		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(vue, "Erreur lors de l'enregistrement du paiement.");
			ex.printStackTrace();
		}
	}
}
