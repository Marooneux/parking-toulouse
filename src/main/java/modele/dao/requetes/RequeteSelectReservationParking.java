package modele.dao.requetes;

import modele.ReservationParking;

public class RequeteSelectReservationParking extends Requete<ReservationParking> {

	@Override
	public String requete() {
		return "SELECT * FROM reservations_parking";
	}
}