package modele.dao.requetes;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import modele.Utilisateur;

public class RequeteDeleteUtilisateur extends Requete<Utilisateur> {

	@Override
	public String requete() {
		return "DELETE FROM utilisateurs WHERE id = ?";
	}

	@Override
	public void parametres(PreparedStatement statement, Utilisateur donnee) throws SQLException {
		statement.setInt(1, donnee.getId());
	}
}
