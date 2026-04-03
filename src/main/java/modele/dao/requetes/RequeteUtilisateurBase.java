package modele.dao.requetes;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import modele.Utilisateur;

public abstract class RequeteUtilisateurBase extends Requete<Utilisateur>{
	
	
	protected void setCommonParam(PreparedStatement statement, Utilisateur donnee) throws SQLException {
		statement.setString(1, donnee.getNom());
		statement.setString(2, donnee.getPrenom());
		statement.setString(3, donnee.getEmail());
		statement.setString(4, donnee.getMdp());
		statement.setString(5, donnee.getType().name().toLowerCase());
	}

}
