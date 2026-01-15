package modele.dao.requetes;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import modele.Abonne;

public class RequeteSelectAbonneById extends Requete<Abonne> {

	@Override
	public String requete() {
		return "SELECT * FROM abonne WHERE id_utilisateur = ? AND id_abonnement = ?";
	}

	@Override
	public void parametres(PreparedStatement statement, String... donnee) throws SQLException {
		statement.setInt(1, Integer.parseInt(donnee[0]));
		statement.setInt(2, Integer.parseInt(donnee[1]));
	}
}
