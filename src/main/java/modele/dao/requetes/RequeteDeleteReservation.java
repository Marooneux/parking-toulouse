package modele.dao.requetes;

import modele.ReservationParking;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RequeteDeleteReservation extends Requete<ReservationParking>{
    @Override
    public String requete() {
        return "DELETE FROM reservation" +
                "WHERE idreservation = ?";
    }

    @Override
    public void parametres(PreparedStatement statement, String... id) throws SQLException {
        statement.setString(1, id[0]);
    }

    @Override
    public void parametres(PreparedStatement statement, ReservationParking id) throws SQLException {
        statement.setInt(1, 10);
    }
}
