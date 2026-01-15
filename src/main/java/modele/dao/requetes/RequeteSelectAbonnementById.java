package modele.dao.requetes;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import modele.Abonnement;

public class RequeteSelectAbonnementById extends Requete<Abonnement> {

	@Override
	public String requete() {
		return "SELECT * FROM abonnements WHERE id = ?";
	}

	@Override
	public void parametres(PreparedStatement statement, String... id) throws SQLException {
		statement.setInt(1, Integer.parseInt(id[0]));
	}
}