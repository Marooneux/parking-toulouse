package modele.dao.requetes;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import modele.Utilisateur;

public class RequeteInsertUtilisateur extends RequeteUtilisateurBase {

	@Override
	public String requete() {
		return "INSERT INTO utilisateurs (nom, prenom, email, mdp, user_type) VALUES (?, ?, ?, ?, ?)";
	}

	@Override
	public void parametres(PreparedStatement statement, Utilisateur donnee) throws SQLException {
		setCommonParam(statement, donnee);
		
	}
}