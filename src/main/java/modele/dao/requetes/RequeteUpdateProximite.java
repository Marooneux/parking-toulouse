package modele.dao.requetes;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import modele.Proximite;

public class RequeteUpdateProximite extends Requete<Proximite> {

	@Override
	public String requete() {
		return "UPDATE est_proche_de SET distance_metres = ? WHERE id_parking = ? AND id_ligne_metro = ?";
	}

	@Override
	public void parametres(PreparedStatement statement, Proximite donnee) throws SQLException {
		statement.setInt(1, donnee.getDistanceMetres());
		statement.setInt(2, donnee.getParking().getId());
		statement.setInt(3, donnee.getLigneMetro().getId());
	}
}