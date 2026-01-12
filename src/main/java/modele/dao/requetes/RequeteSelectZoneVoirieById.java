package modele.dao.requetes;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import modele.ZoneVoirie;

public class RequeteSelectZoneVoirieById extends Requete<ZoneVoirie> {

	@Override
	public String requete() {
		return "SELECT * FROM zones_voirie WHERE id_zone = ?";
	}

	@Override
	public void parametres(PreparedStatement statement, String... id) throws SQLException {
		statement.setInt(1, Integer.parseInt(id[0]));
	}
}