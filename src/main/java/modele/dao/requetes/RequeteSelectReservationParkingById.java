package modele.dao.requetes;

import modele.ReservationParking;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RequeteSelectReservationParkingById extends Requete<ReservationParking> {
    @Override
    public String requete() {
        return "SELECT * FROM reservations_parking WHERE id_reservation = ?";
    }

    @Override
    public void parametres(PreparedStatement statement, String... id) throws SQLException {
        statement.setInt(1, Integer.parseInt(id[0]));
    }

    @Override
    public void parametres(PreparedStatement statement, ReservationParking donnee) throws SQLException {
        throw new UnsupportedOperationException("Use parametres with id for select by id");
    }
}
