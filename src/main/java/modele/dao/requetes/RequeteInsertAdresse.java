package modele.dao.requetes;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import modele.Adresse;

public class RequeteInsertAdresse extends Requete<Adresse> {

	@Override
	public String requete() {
		return "INSERT INTO adresse (numero, rue, code_postal, ville) VALUES (?, ?, ?, ?)";
	}

	@Override
	public void parametres(PreparedStatement statement, Adresse donnee) throws SQLException {
		statement.setString(1, donnee.getNumero());
		statement.setString(2, donnee.getRue());
		statement.setString(3, donnee.getCodePostal());
		statement.setString(4, donnee.getVille());
	}
}
