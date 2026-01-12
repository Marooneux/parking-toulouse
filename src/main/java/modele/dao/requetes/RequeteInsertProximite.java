package modele.dao.requetes;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import modele.Proximite;

public class RequeteInsertProximite extends Requete<Proximite> {

	@Override
	public String requete() {
		return "INSERT INTO est_proche_de (id_parking, id_ligne_metro, distance_metres) VALUES (?, ?, ?)";
	}

	@Override
	public void parametres(PreparedStatement statement, Proximite donnee) throws SQLException {
		statement.setInt(1, donnee.getParking().getId());
		statement.setInt(2, donnee.getLigneMetro().getId());
		statement.setInt(3, donnee.getDistanceMetres());
	}
}