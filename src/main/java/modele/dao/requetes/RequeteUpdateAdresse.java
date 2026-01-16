package modele.dao.requetes;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import modele.Adresse;

public class RequeteUpdateAdresse extends Requete<Adresse> {

	@Override
	public String requete() {
		return "UPDATE adresse SET numero = ?, rue = ?, code_postal = ?, ville = ? WHERE id = ?";
	}

	@Override
	public void parametres(PreparedStatement statement, Adresse donnee) throws SQLException {
		statement.setInt(1, donnee.getNumero());
		statement.setString(2, donnee.getRue());
		statement.setInt(3, donnee.getCodePostal());
		statement.setString(4, donnee.getVille());
		statement.setInt(5, donnee.getId());
	}
}
