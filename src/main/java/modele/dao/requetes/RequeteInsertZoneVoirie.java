package modele.dao.requetes;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import modele.ZoneVoirie;

public class RequeteInsertZoneVoirie extends Requete<ZoneVoirie> {

	@Override
	public String requete() {
		return "INSERT INTO zones_voirie(nom, tarif_horaire, duree_max) VALUES(?, ?, ?)";
	}

	@Override
	public void parametres(PreparedStatement statement, ZoneVoirie donnee) throws SQLException {
		statement.setString(1, donnee.getNom());
		statement.setDouble(2, donnee.getTarifHoraire());
		statement.setDouble(3, donnee.getDureeMax());
	}
}