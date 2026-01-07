package modele.dao.requetes;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import modele.ReservationVoirie;

public class RequeteUpdateReservationVoirie extends Requete<ReservationVoirie> {
	@Override
	public String requete() {
		return "UPDATE reservations_voirie "
				+ "SET type_vehicule = ?, date_debut = ?, duree_minutes = ?, id_utilisateur = ? "
				+ "WHERE immatriculation = ? AND id_zone = ?";
	}

	@Override
	public void parametres(PreparedStatement statement, ReservationVoirie donnee) throws SQLException {
		statement.setString(1, donnee.getTypeVehicule());
		statement.setTimestamp(2, java.sql.Timestamp.valueOf(donnee.getDateDebut()));
		statement.setInt(3, donnee.getDureeMinutes());
		statement.setInt(4, donnee.getIdUtilisateur());
		statement.setString(5, donnee.getImmatriculation());
		statement.setInt(6, donnee.getIdZone());
	}
}