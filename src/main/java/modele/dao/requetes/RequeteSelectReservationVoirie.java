package modele.dao.requetes;

import modele.ReservationVoirie;

public class RequeteSelectReservationVoirie extends Requete<ReservationVoirie> {

	@Override
	public String requete() {
		return "SELECT * FROM reservations_voirie";
	}
}
