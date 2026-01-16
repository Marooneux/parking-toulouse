package modele.dao.requetes;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import modele.Utilisateur;

public class RequeteInsertUtilisateur extends Requete<Utilisateur> {

	@Override
	public String requete() {
		return "INSERT INTO utilisateurs (nom, prenom, email, mdp, user_type) VALUES (?, ?, ?, ?, ?)";
	}

	@Override
	public void parametres(PreparedStatement statement, Utilisateur donnee) throws SQLException {
		statement.setString(1, donnee.getNom());
		statement.setString(2, donnee.getPrenom());
		statement.setString(3, donnee.getEmail());
		statement.setString(4, donnee.getMdp());
		statement.setString(5, donnee.getType().name().toLowerCase());
	}
}