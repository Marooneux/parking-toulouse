package modele.dao.requetes;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import modele.ReservationVoirie;

public class RequeteDeleteReservationVoirie extends Requete<ReservationVoirie> {

	@Override
	public String requete() {
		return "DELETE FROM reservations_voirie WHERE id = ?";
	}

	@Override
	public void parametres(PreparedStatement statement, ReservationVoirie donnee) throws SQLException {
		statement.setInt(1, donnee.getId());
	}
}