package modele.dao.requetes;

import modele.ReservationVoirie;

public class RequeteSelectReservationsVoirie extends Requete<ReservationVoirie> {
	@Override
	public String requete() {
		return "SELECT * FROM reservations_voirie";
	}
}
