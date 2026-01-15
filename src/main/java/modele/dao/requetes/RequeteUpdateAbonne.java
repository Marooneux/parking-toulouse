package modele.dao.requetes;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import modele.Abonne;

public class RequeteUpdateAbonne extends Requete<Abonne> {

	@Override
	public String requete() {
		return "UPDATE abonne SET est_actif = ? WHERE id_utilisateur = ? AND id_abonnement = ?";
	}

	@Override
	public void parametres(PreparedStatement statement, Abonne donnee) throws SQLException {
		statement.setBoolean(1, donnee.isEstActif());
		statement.setInt(2, donnee.getUtilisateur().getId());
		statement.setInt(3, donnee.getAbonnement().getId());
	}
}
