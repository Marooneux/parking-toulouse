package modele.dao.requetes;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;

import modele.ReservationParking;

public class RequeteInsertReservationParking extends Requete<ReservationParking> {

	@Override
	public String requete() {
		return "INSERT INTO reservations_parking (date_arrivee, date_depart, id_parking, id_utilisateur) VALUES (?, ?, ?, ?)";
	}

	@Override
	public void parametres(PreparedStatement statement, ReservationParking donnee) throws SQLException {
		statement.setTimestamp(1, Timestamp.valueOf(donnee.getDateArrivee()));

		if (donnee.getDateDepart() != null) {
			statement.setTimestamp(2, Timestamp.valueOf(donnee.getDateDepart()));
		} else {
			statement.setNull(2, Types.TIMESTAMP);
		}

		statement.setInt(3, donnee.getParking().getId());
		statement.setInt(4, donnee.getUtilisateur().getId());
	}
}
