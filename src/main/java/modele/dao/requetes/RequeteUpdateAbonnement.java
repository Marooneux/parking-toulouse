package modele.dao.requetes;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import modele.Abonnement;

public class RequeteUpdateAbonnement extends Requete<Abonnement> {

	@Override
	public String requete() {
		return "UPDATE abonnements SET nom = ?, description = ? WHERE id = ?";
	}

	@Override
	public void parametres(PreparedStatement statement, Abonnement donnee) throws SQLException {
		statement.setString(1, donnee.getNom());
		statement.setString(2, donnee.getDescription());
		statement.setInt(3, donnee.getId());
	}
}
