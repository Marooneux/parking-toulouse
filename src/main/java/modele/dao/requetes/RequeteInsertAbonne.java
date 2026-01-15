package modele.dao.requetes;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import modele.Abonne;

public class RequeteInsertAbonne extends Requete<Abonne> {

	@Override
	public String requete() {
		return "INSERT INTO abonne (id_utilisateur, id_abonnement, est_actif) VALUES (?, ?, ?)";
	}

	@Override
	public void parametres(PreparedStatement statement, Abonne donnee) throws SQLException {
		statement.setInt(1, donnee.getUtilisateur().getId());
		statement.setInt(2, donnee.getAbonnement().getId());
		statement.setBoolean(3, donnee.isEstActif());
	}
}
