package modele.dao.requetes;

import modele.Parking;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RequeteSelectParkingById extends Requete<Parking> {
    @Override
    public String requete() {
        return "SELECT * FROM parkings WHERE id_parking = ?";
    }

    @Override
    public void parametres(PreparedStatement statement, String... id) throws SQLException {
        statement.setInt(1, Integer.parseInt(id[0]));
    }
}
