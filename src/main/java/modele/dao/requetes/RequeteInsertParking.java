package modele.dao.requetes;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Types;

import modele.Parking;

public class RequeteInsertParking extends Requete<Parking> {

	@Override
	public String requete() {
		return " INSERT INTO parkings (nom, capacite, hauteur_max, horaire_ouverture, horaire_fermeture, contient_places_moto, id_adresse) VALUES (?, ?, ?, ?, ?, ?, ?)";
	}

	@Override
	public void parametres(PreparedStatement statement, Parking donnee) throws SQLException {

		statement.setString(1, donnee.getNom());
		statement.setInt(2, donnee.getCapacite());

		if (donnee.getHauteurMax() != 0.0) {
			statement.setDouble(3, donnee.getHauteurMax());
		} else {
			statement.setNull(3, Types.DECIMAL);
		}

		if (donnee.getHoraireOuverture() != null) {
			statement.setTime(4, Time.valueOf(donnee.getHoraireOuverture()));
		} else {
			statement.setNull(4, Types.TIME);
		}

		if (donnee.getHoraireFermeture() != null) {
			statement.setTime(5, Time.valueOf(donnee.getHoraireFermeture()));
		} else {
			statement.setNull(5, Types.TIME);
		}

		statement.setBoolean(6, donnee.isContientPlacesMoto());
		statement.setInt(7, donnee.getAdresse().getId());
	}
}
