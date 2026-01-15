package modele.dao.requetes;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import modele.Utilisateur;

public class RequeteSelectUtilisateurById extends Requete<Utilisateur> {

	@Override
	public String requete() {
		return "SELECT * FROM utilisateurs WHERE id = ?";
	}

	@Override
	public void parametres(PreparedStatement statement, String... id) throws SQLException {
		statement.setInt(1, Integer.parseInt(id[0]));
	}
}
