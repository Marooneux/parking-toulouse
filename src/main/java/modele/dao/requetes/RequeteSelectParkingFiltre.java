package modele.dao.requetes;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import modele.Parking;

public class RequeteSelectParkingFiltre extends Requete<Parking> {
	
	@Override
	public String requete() {
		List<Parking> parkings = new ArrayList<>();
		String sql = "SELECT * FROM parkings";
		
		if (orderBy != null && !orderBy.isEmpty()) {
		    sql += " ORDER BY " + orderBy;
		}
		
		try (ResultSet rs = executeQuery(sql)) {
		    while (rs.next()) {
		        Parking p = new Parking(
		            rs.getString("nom"),
		            rs.getString("adresse"),
		            rs.getDouble("hauteur_max"),
		            rs.getInt("nb_places"),
		            rs.getDouble("tarif"),
		            rs.getTime("heure_debut").toLocalTime(),
		            rs.getTime("heure_fin").toLocalTime(),
		            rs.getBoolean("est_couvert")
		        );
		        p.setNbPlacesOccupees(rs.getInt("nb_places_occupees")); 
		        // Assurez-vous d'avoir l'ID si besoin: p.setId(rs.getInt("id_parking"));
		            parkings.add(p);
		        }
		    }
		    return parkings;
		}

    @Override
    public void parametres(PreparedStatement statement, String... id) throws SQLException {
        statement.setInt(1, Integer.parseInt(id[0]));
    }
    
}
