package modele.dao.requetes;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import modele.Proximite;

public class RequeteDeleteProximite extends Requete<Proximite> {

	@Override
	public String requete() {
		return "DELETE FROM est_proche_de WHERE id_parking = ? AND id_ligne_metro = ?";
	}

	@Override
	public void parametres(PreparedStatement statement, Proximite donnee) throws SQLException {
		statement.setInt(1, donnee.getParking().getId());
		statement.setInt(2, donnee.getLigneMetro().getId());
	}
}