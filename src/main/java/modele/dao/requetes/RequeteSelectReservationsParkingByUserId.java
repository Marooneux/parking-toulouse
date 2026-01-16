package modele.dao.requetes;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import modele.ReservationParking;

public class RequeteSelectReservationsParkingByUserId extends Requete<ReservationParking> {


    @Override
    public String requete() {
        return "SELECT * FROM reservations_parking where id_utilisateur = ?";
    }
    
    @Override
    public void parametres(PreparedStatement statement, String... id) throws SQLException {
        statement.setInt(1, Integer.parseInt(id[0]));
    }
}
