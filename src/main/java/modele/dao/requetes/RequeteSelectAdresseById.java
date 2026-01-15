package modele.dao.requetes;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import modele.Adresse;

public class RequeteSelectAdresseById extends Requete<Adresse> {

	@Override
	public String requete() {
		return "SELECT * FROM adresse WHERE id = ?";
	}

	@Override
	public void parametres(PreparedStatement statement, String... id) throws SQLException {
		statement.setInt(1, Integer.parseInt(id[0]));
	}
}
