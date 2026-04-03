package modele.dao.requetes;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import modele.Utilisateur;

public class RequeteUpdateUtilisateur extends RequeteUtilisateurBase {

	@Override
	public String requete() {
		return "UPDATE utilisateurs SET nom = ?, prenom = ?, email = ?, mdp = ?, user_type = ? WHERE id = ?";
	}

	@Override
	public void parametres(PreparedStatement statement, Utilisateur donnee) throws SQLException {
		setCommonParam(statement, donnee);
		statement.setInt(6, donnee.getId());
	}
}
