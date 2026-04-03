package modele.dao.requetes;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import modele.ReservationParking;

public class RequeteInsertReservationParking extends RequeteReservationParkingBase {

    @Override
    public String requete() {
        return "INSERT INTO reservations_parking (date_arrivee, date_depart, id_parking, id_utilisateur) VALUES (?, ?, ?, ?)";
    }

    @Override
    public void parametres(PreparedStatement statement, ReservationParking donnee) throws SQLException {
    	setCommonParams(statement, donnee);
    }
}
