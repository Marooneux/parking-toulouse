package modele.dao.requetes;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import modele.ReservationVoirie;

public class RequeteSelectReservationVoirieById extends Requete<ReservationVoirie> {

	@Override
	public String requete() {
		return "SELECT * FROM reservations_voirie WHERE immatriculation = ?";
	}

	@Override
	public void parametres(PreparedStatement statement, String... id) throws SQLException {
		statement.setString(1, id[0]);
	}
}
