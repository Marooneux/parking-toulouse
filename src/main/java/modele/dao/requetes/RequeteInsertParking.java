package modele.dao.requetes;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Time;

import modele.Parking;

public class RequeteInsertParking extends Requete<Parking> {

	@Override
	public String requete() {
		return "insert into Parking values(?,?,?,?,?,?,?,?)";
	}

	@Override
	public void parametres(PreparedStatement statement, Parking donnee) throws SQLException {
		statement.setString(2, donnee.getNom());
		statement.setString(3, donnee.getAdresse());
		statement.setDouble(4, donnee.getTarif());
		statement.setInt(5, donnee.getNbPlacesTotales());
		statement.setInt(6, donnee.getNbPlacesOccupees());
		statement.setDouble(7, donnee.getHauteur());
		statement.setTime(8, Time.valueOf(donnee.getHeureOuverture()));
		statement.setTime(9, Time.valueOf(donnee.getHeureOuverture()));
	};

}
