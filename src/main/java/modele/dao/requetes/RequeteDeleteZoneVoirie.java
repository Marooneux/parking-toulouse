package modele.dao.requetes;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import modele.ZoneVoirie;

public class RequeteDeleteZoneVoirie extends Requete<ZoneVoirie> {

	@Override
	public String requete() {
		return "DELETE FROM zones_voirie WHERE id_zone = ?";
	}

	@Override
	public void parametres(PreparedStatement statement, ZoneVoirie donnee) throws SQLException {
		statement.setInt(1, donnee.getId());
	}
}