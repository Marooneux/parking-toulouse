package modele.dao.requetes;

import modele.ReservationParking;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RequeteSelectReservationById extends Requete<ReservationParking> {
    @Override
    public String requete() {
        return "SELECT * FROM Reservation WHERE ReservationID = ?";
    }

    @Override
    public void parametres(PreparedStatement statement, String... id) throws SQLException {
        statement.setString(1, id[0]);
    }

    @Override
    public void parametres(PreparedStatement statement, ReservationParking donnee) throws SQLException {
        statement.setInt(1, 10);
    }
}
