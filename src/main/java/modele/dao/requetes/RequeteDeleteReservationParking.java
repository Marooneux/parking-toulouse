package modele.dao.requetes;

import modele.ReservationParking;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RequeteDeleteReservationParking extends Requete<ReservationParking>{
    @Override
    public String requete() {
        return "DELETE FROM reservations_parking WHERE immatriculation = ? AND id_parking = ?";
    }

    @Override
    public void parametres(PreparedStatement statement, String... id) throws SQLException {
        statement.setString(1, id[0]);
        statement.setInt(2, Integer.parseInt(id[1]));
        // no timestamp match to avoid precision mismatch
    }

    @Override
    public void parametres(PreparedStatement statement, ReservationParking donnee) throws SQLException {
        statement.setString(1, donnee.getImmatriculation());
        statement.setInt(2, donnee.getParking().getId());
        // no timestamp match to avoid precision mismatch
    }
}
