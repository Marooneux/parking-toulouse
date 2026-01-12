package modele.dao.requetes;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import modele.Proximite;

public class RequeteSelectProximiteById extends Requete<Proximite> {

	@Override
	public String requete() {
		return "SELECT * FROM est_proche_de WHERE id_parking = ? AND id_ligne_metro = ?";
	}

	@Override
	public void parametres(PreparedStatement statement, String... id) throws SQLException {
		statement.setInt(1, Integer.parseInt(id[0]));
		statement.setInt(2, Integer.parseInt(id[1]));
	}
}
