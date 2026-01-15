package modele.dao.requetes;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import modele.LigneMetro;

public class RequeteUpdateLignesMetro extends Requete<LigneMetro> {

	@Override
	public String requete() {
		return "UPDATE lignes_metro SET nom = ?, couleur = ? WHERE id = ?";
	}

	@Override
	public void parametres(PreparedStatement statement, LigneMetro donnee) throws SQLException {
		statement.setString(1, donnee.getNom());
		statement.setString(2, donnee.getCouleur());
		statement.setInt(3, donnee.getId());
	}
}
