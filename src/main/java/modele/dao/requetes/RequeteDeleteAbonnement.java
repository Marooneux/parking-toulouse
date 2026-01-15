package modele.dao.requetes;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import modele.Abonnement;

public class RequeteDeleteAbonnement extends Requete<Abonnement> {

	@Override
	public String requete() {
		return "DELETE FROM abonnements WHERE id = ?";
	}

	@Override
	public void parametres(PreparedStatement statement, Abonnement donnee) throws SQLException {
		statement.setInt(1, donnee.getId());
	}
}
