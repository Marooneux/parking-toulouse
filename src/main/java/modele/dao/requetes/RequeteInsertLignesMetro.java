package modele.dao.requetes;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import modele.LigneMetro;

public class RequeteInsertLignesMetro extends Requete<LigneMetro> {

	@Override
	public String requete() {
		return "INSERT INTO lignes_metro (nom, couleur) VALUES (?, ?)";
	}

	@Override
	public void parametres(PreparedStatement statement, LigneMetro donnee) throws SQLException {
		statement.setString(1, donnee.getNom());
		statement.setString(2, donnee.getCouleur());
	}
}
