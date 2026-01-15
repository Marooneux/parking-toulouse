package modele.dao.requetes;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import modele.Utilisateur;

public class RequeteUpdateUtilisateur extends Requete<Utilisateur> {

	@Override
	public String requete() {
		return "UPDATE utilisateurs SET nom = ?, prenom = ?, email = ?, mot_de_passe = ?, user_type = ? WHERE id = ?";
	}

	@Override
	public void parametres(PreparedStatement statement, Utilisateur donnee) throws SQLException {
		statement.setString(1, donnee.getNom());
		statement.setString(2, donnee.getPrenom());
		statement.setString(3, donnee.getEmail());
		statement.setString(4, donnee.getMdp());
		statement.setString(5, donnee.getType().name().toLowerCase());
		statement.setInt(6, donnee.getId());
	}
}
