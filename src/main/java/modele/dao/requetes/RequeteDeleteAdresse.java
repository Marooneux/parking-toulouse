package modele.dao.requetes;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import modele.Adresse;

public class RequeteDeleteAdresse extends Requete<Adresse> {

	@Override
	public String requete() {
		return "DELETE FROM adresse WHERE id = ?";
	}

	@Override
	public void parametres(PreparedStatement statement, Adresse donnee) throws SQLException {
		statement.setInt(1, donnee.getId());
	}
}
