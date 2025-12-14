package modele.dao.requetes;

import modele.ReservationParking;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RequeteUpdateReservationParking extends Requete<ReservationParking> {
    @Override
    public String requete() {
        return "UPDATE reservations_parking SET immatriculation = ?, date_depart = ? WHERE id_parking = ?";
    }

    @Override
    public void parametres(PreparedStatement statement, String... id) throws SQLException {
        // new immatriculation, date_depart, where id_parking
        statement.setString(1, id[0]);
        statement.setTimestamp(2, java.sql.Timestamp.valueOf(id[1]));
        statement.setInt(3, Integer.parseInt(id[2]));
    }

    @Override
    public void parametres(PreparedStatement statement, ReservationParking donnee) throws SQLException {
        statement.setString(1, donnee.getImmatriculation());
        java.time.LocalDateTime depart = donnee.getDateDepart();
        if (depart != null) {
            statement.setTimestamp(2, java.sql.Timestamp.valueOf(depart));
        } else {
            statement.setTimestamp(2, null);
        }
        statement.setInt(3, donnee.getParking().getId());
    }
}
