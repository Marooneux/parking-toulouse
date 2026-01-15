package modele.dao.requetes;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import modele.LigneMetro;

public class RequeteDeleteLignesMetro extends Requete<LigneMetro> {

	@Override
	public String requete() {
		return "DELETE FROM lignes_metro WHERE id = ?";
	}

	@Override
	public void parametres(PreparedStatement statement, LigneMetro donnee) throws SQLException {
		statement.setInt(1, donnee.getId());
	}
}
