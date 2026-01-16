package controleur;

import java.sql.SQLException;
import java.time.LocalDateTime;

import modele.ReservationParking;
import modele.dao.DaoReservationParking;

public class ControleurConfirmationPaiementParking {


	public ControleurConfirmationPaiementParking() {
	}
	
	public void departConfirme(ReservationParking reservation, double prix) {
		LocalDateTime heureActuelle = LocalDateTime.now();
		reservation.setDateDepart(heureActuelle);
		reservation.setPrixPaye(prix);
		try {
			DaoReservationParking dao = new DaoReservationParking();
			dao.update(reservation);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}