package modele.dao.requetes;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import modele.ReservationVoirie;

public class RequeteInsertReservationVoirie extends Requete<ReservationVoirie> {

	@Override
	public String requete() {
		return "INSERT INTO reservations_voirie "
				+ "(immatriculation, type_vehicule, date_debut, duree_minutes, id_zone, id_utilisateur) "
				+ "VALUES (?, ?, ?, ?, ?, ?)";
	}

	@Override
	public void parametres(PreparedStatement statement, ReservationVoirie donnee) throws SQLException {
		statement.setString(1, donnee.getImmatriculation());
		statement.setString(2, donnee.getTypeVehicule());
		statement.setTimestamp(3, java.sql.Timestamp.valueOf(donnee.getDateDebut()));
		statement.setInt(4, donnee.getDureeMinutes());
		statement.setInt(5, donnee.getIdZone());
		statement.setInt(6, donnee.getIdUtilisateur());
	}
}