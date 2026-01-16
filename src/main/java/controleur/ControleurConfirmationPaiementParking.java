package controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.time.LocalDateTime;

import javax.swing.JOptionPane;

import modele.ReservationParking;
import modele.dao.DaoReservationParking;
import vue.ConfirmationPaiementParking;

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
		LocalDateTime heureActuelle = LocalDateTime.now();
		reservation.setDateDepart(heureActuelle);
		reservation.setPrixPaye(prix);
		try {
			DaoReservationParking dao = new DaoReservationParking();
			dao.update(reservation);
			System.exit(0);
		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(vue, "Erreur lors de l'enregistrement du paiement.");
			ex.printStackTrace();
		}
	}
}
