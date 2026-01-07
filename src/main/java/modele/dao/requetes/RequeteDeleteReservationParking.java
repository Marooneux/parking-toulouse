package modele.dao.requetes;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import modele.ReservationParking;

public class RequeteDeleteReservationParking extends Requete<ReservationParking> {
	@Override
	public String requete() {
		return "DELETE FROM reservations_parking WHERE immatriculation = ? AND id_parking = ?";
	}

	@Override
	public void parametres(PreparedStatement statement, ReservationParking donnee) throws SQLException {
		statement.setString(1, donnee.getImmatriculation());
		statement.setInt(2, donnee.getParking().getId());
	}
}
