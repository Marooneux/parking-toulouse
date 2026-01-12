package modele.dao.requetes;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Time;

import modele.Parking;

public class RequeteInsertParking extends Requete<Parking> {

	@Override
	public String requete() {
		return "INSERT INTO parkings (nom, adresse, nombre_places_max, nb_places_occupees,hauteur_max, horaire_ouverture, horaire_fermeture, contient_places_moto, tarif) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
	}

	@Override
	public void parametres(PreparedStatement statement, Parking donnee) throws SQLException {
		statement.setString(1, donnee.getNom());
		statement.setString(2, donnee.getAdresse());
		statement.setInt(3, donnee.getNbPlacesMax());
		statement.setInt(4, donnee.getNbPlacesOccupees());
		statement.setDouble(5, donnee.getHauteur());
		statement.setTime(6, (donnee.getHeureOuverture() != null) ? Time.valueOf(donnee.getHeureOuverture()) : null);
		statement.setTime(7, (donnee.getHeureFermeture() != null) ? Time.valueOf(donnee.getHeureFermeture()) : null);
		statement.setBoolean(8, donnee.isContientPlacesMoto());
		statement.setDouble(9, donnee.getTarif());
	}
}
