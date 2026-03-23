package modele.dao.requetes;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import modele.ReservationParking;

public class RequeteSelectActiveReservationParkingByUserId extends Requete<ReservationParking> {

    @Override
    public String requete() {
        return "SELECT * FROM reservations_parking WHERE id_utilisateur = ? AND date_depart IS NULL ORDER BY date_arrivee DESC";
    }

    @Override
    public void parametres(PreparedStatement statement, String... id) throws SQLException {
        statement.setInt(1, Integer.parseInt(id[0]));
    }
}
