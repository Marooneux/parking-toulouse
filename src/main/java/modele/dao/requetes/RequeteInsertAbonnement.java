package modele.dao.requetes;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import modele.Abonnement;

public class RequeteInsertAbonnement extends Requete<Abonnement> {

	@Override
	public String requete() {
		return "INSERT INTO abonnements (nom, description) VALUES (?, ?)";
	}

	@Override
	public void parametres(PreparedStatement statement, Abonnement donnee) throws SQLException {
		statement.setString(1, donnee.getNom());
		statement.setString(2, donnee.getDescription());
	}
}
