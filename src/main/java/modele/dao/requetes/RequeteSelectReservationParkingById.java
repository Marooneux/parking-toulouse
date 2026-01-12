package modele.dao.requetes;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import modele.ReservationParking;

public class RequeteSelectReservationParkingById extends Requete<ReservationParking> {

	@Override
	public String requete() {
		return "SELECT * FROM reservations_parking WHERE immatriculation = ? AND id_parking = ?";
	}

	@Override
	public void parametres(PreparedStatement statement, String... id) throws SQLException {
		statement.setString(1, id[0]);
		statement.setInt(2, Integer.parseInt(id[1]));
	}
}