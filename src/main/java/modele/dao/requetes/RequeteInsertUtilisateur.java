package modele.dao.requetes;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;

import modele.Utilisateur;

public class RequeteInsertUtilisateur extends Requete<Utilisateur> {

	@Override
	public String requete() {
		return "INSERT INTO utilisateurs (nom, prenom, email, mot_de_passe, id_abonnement) VALUES (?, ?, ?, ?, ?)";
	}

	@Override
	public void parametres(PreparedStatement statement, Utilisateur u) throws SQLException {
		statement.setString(1, u.getNom());
		statement.setString(2, u.getPrenom());
		statement.setString(3, u.getEmail());
		statement.setString(4, u.getMdp());
		if (u.getAbonnement() != null) {
			statement.setInt(5, u.getAbonnement().getId());
		} else {
			statement.setNull(5, Types.INTEGER);
		}
	}
}
