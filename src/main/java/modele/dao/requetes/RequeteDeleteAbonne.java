package modele.dao.requetes;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import modele.Abonne;

public class RequeteDeleteAbonne extends Requete<Abonne> {

	@Override
	public String requete() {
		return "DELETE FROM abonne WHERE id_utilisateur = ? AND id_abonnement = ?";
	}

	@Override
	public void parametres(PreparedStatement statement, Abonne donnee) throws SQLException {
		statement.setInt(1, donnee.getUtilisateur().getId());
		statement.setInt(2, donnee.getAbonnement().getId());
	}
}
