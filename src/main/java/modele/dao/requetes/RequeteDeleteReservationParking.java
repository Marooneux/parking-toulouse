package modele.dao.requetes;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import modele.ReservationParking;

public class RequeteDeleteReservationParking extends Requete<ReservationParking> {

	@Override
	public String requete() {
		return "DELETE FROM reservations_parking WHERE id = ?";
	}

	@Override
	public void parametres(PreparedStatement statement, ReservationParking donnee) throws SQLException {
		statement.setInt(1, donnee.getId());
	}
}
