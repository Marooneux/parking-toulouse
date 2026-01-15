package modele.dao.requetes;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

import modele.ReservationVoirie;

public class RequeteInsertReservationVoirie extends Requete<ReservationVoirie> {

	@Override
	public String requete() {
		return "INSERT INTO reservations_voirie "
				+ "(date_debut, duree_minutes, id_zone, id_utilisateur) "
				+ "VALUES (?, ?, ?, ?)";
	}

	@Override
	public void parametres(PreparedStatement statement, ReservationVoirie donnee) throws SQLException {
		statement.setTimestamp(1, Timestamp.valueOf(donnee.getDateDebut()));
		statement.setInt(2, donnee.getDureeMinutes());
		statement.setInt(3, donnee.getZone().getId());
		statement.setInt(4, donnee.getUtilisateur().getId());
	}
}