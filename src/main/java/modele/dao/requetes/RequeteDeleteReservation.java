package modele.dao.requetes;

import modele.Reservation;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RequeteDeleteReservation extends Requete<Reservation>{
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
    public void parametres(PreparedStatement statement, Reservation id) throws SQLException {
        statement.setInt(1, 10);
    }
}
