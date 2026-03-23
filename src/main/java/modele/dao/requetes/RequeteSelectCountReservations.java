package modele.dao.requetes;

import modele.Parking;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RequeteSelectCountReservations extends Requete<Parking> {
    @Override
    public String requete() {
        return "SELECT COUNT(*) FROM reservations_parking WHERE id_parking = ? AND date_depart IS NULL";
    }

    @Override
    public void parametres(PreparedStatement statement, String... id) throws SQLException {
        statement.setInt(1, Integer.parseInt(id[0]));
    }
}