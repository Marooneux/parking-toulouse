package modele.dao.requetes;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;

import modele.ReservationParking;

public class RequeteUpdateReservationParking extends RequeteReservationParkingBase {

    @Override
    public String requete() {
        return "UPDATE reservations_parking SET date_arrivee = ?, date_depart = ?, id_parking = ?, id_utilisateur = ? WHERE id = ?";
    }

    @Override
    public void parametres(PreparedStatement statement, ReservationParking donnee) throws SQLException {
    	setCommonParams(statement, donnee);
    	statement.setInt(5, donnee.getId());
    }
}
