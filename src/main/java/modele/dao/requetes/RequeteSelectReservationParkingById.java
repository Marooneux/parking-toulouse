package modele.dao.requetes;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import modele.ReservationParking;

public class RequeteSelectReservationParkingById extends Requete<ReservationParking> {

	@Override
	public String requete() {
		return "SELECT * FROM reservations_parking WHERE id = ?";
	}

	@Override
	public void parametres(PreparedStatement statement, String... id) throws SQLException {
		statement.setInt(1, Integer.parseInt(id[0]));
	}
}
