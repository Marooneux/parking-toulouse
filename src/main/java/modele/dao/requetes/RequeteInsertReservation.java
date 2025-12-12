package modele.dao.requetes;

import modele.Reservation;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RequeteInsertReservation extends Requete<Reservation> {
    @Override
    public String requete() {
        return "INSERT INTO reservations VALUES (?,?,?,?)";
    }

    @Override
    public void parametres(PreparedStatement statement, String ...id) throws SQLException {
        statement.setString(1, id[0]);
    };

    @Override
    public void parametres(PreparedStatement statement, Reservation donnee) throws SQLException {
        statement.setInt(1,10);
    };
}
