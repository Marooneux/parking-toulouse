package modele.dao.requetes;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import modele.ReservationVoirie;

public class RequeteDeleteReservationVoirie extends Requete<ReservationVoirie> {

	@Override
	public String requete() {
		return "DELETE FROM reservations_voirie WHERE immatriculation = ? AND id_zone = ?";
	}

	@Override
	public void parametres(PreparedStatement statement, ReservationVoirie donnee) throws SQLException {
		statement.setString(1, donnee.getImmatriculation());
		statement.setInt(2, donnee.getIdZone());
	}
}