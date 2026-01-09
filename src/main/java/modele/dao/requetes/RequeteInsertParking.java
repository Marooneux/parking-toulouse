package modele.dao.requetes;

import modele.Parking;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;

public class RequeteInsertParking extends Requete<Parking> {
    @Override
    public String requete() {
        return "INSERT INTO parkings (nom, adresse, tarif, nombre_places_max, hauteur_max, horaire_ouverture, horaire_fermeture, contient_places_moto) VALUES (?, ?,?,?,?,?,?,?)";
    }

    @Override
    public void parametres(PreparedStatement statement, Parking p) throws SQLException {
        statement.setString(1, p.getNom());
        statement.setString(2, p.getAdresse());
        statement.setDouble(3, p.getTarif());
        statement.setInt(4, p.getNbPlacesMax());
        statement.setDouble(5, p.getHauteur());
        statement.setTime(6, p.getHeureOuverture() != null ? Time.valueOf(p.getHeureOuverture()) : null);
        statement.setTime(7, p.getHeureFermeture() != null ? Time.valueOf(p.getHeureFermeture()) : null);
        statement.setBoolean(8, p.isContientPlacesMoto());
		
    }
}
