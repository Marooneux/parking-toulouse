package modele.dao.requetes;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;

import modele.Utilisateur;

public class RequeteUpdateUtilisateur extends Requete<Utilisateur> {

	@Override
	public String requete() {
		return "UPDATE utilisateurs SET nom = ?, prenom = ?, email = ?, mot_de_passe = ?, id_abonnement = ?, type = ? WHERE id_utilisateur = ?";
	}

	@Override
	public void parametres(PreparedStatement statement, Utilisateur donnee) throws SQLException {
		statement.setString(1, donnee.getNom());
		statement.setString(2, donnee.getPrenom());
		statement.setString(3, donnee.getEmail());
		statement.setString(4, donnee.getMdp());
		if (donnee.getAbonnement() != null) {
			statement.setInt(5, donnee.getAbonnement().getId());
		} else {
			statement.setNull(5, Types.INTEGER);
		}
		statement.setString(6, donnee.getType().name().toLowerCase());
		statement.setInt(7, donnee.getId());
	}
}
