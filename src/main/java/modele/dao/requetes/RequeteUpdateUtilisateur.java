package modele.dao.requetes;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import modele.Utilisateur;

public class RequeteUpdateUtilisateur extends Requete<Utilisateur> {

	@Override
	public String requete() {
		return "UPDATE utilisateurs SET nom = ?, prenom = ?, email = ?, mot_de_passe = ?, id_abonnement = ? WHERE id_utilisateur = ?";
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
			statement.setNull(5, java.sql.Types.INTEGER);
		}
		statement.setInt(6, u.getId());
	}
}
