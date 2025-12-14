package modele.dao.requetes;

import modele.Parking;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Time;

public class RequeteUpdateParking extends Requete<Parking> {

	@Override
	public String requete() {
		return "UPDATE parkings SET nom = ?, adresse = ?, nombre_places_max = ?, hauteur_max = ?, horaire_ouverture = ?, horaire_fermeture = ?, contient_places_moto = ? WHERE id_parking = ?";
	}

	@Override
	public void parametres(PreparedStatement statement, Parking p) throws SQLException {
		statement.setString(1, p.getNom());
		statement.setString(2, p.getAdresse());
		statement.setInt(3, p.getNbPlacesMax());
		statement.setDouble(4, p.getHauteur());
		statement.setTime(5, p.getHeureOuverture() != null ? Time.valueOf(p.getHeureOuverture()) : null);
		statement.setTime(6, p.getHeureFermeture() != null ? Time.valueOf(p.getHeureFermeture()) : null);
		statement.setBoolean(7, p.isContientPlacesMoto());
		statement.setInt(8, p.getId());
	}

}
