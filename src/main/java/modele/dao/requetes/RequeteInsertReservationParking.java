package modele.dao.requetes;

import modele.ReservationParking;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RequeteInsertReservationParking extends Requete<ReservationParking> {
    @Override
    public String requete() {
        return "INSERT INTO reservations_parking (immatriculation, type_vehicule, date_arrivee, date_depart, id_parking, id_utilisateur) VALUES (?,?,?,?,?,?)";
    }

    @Override
    public void parametres(PreparedStatement statement, ReservationParking donnee) throws SQLException {
        statement.setString(1, donnee.getImmatriculation());
        statement.setString(2, "voiture");
        statement.setTimestamp(3, java.sql.Timestamp.valueOf(donnee.getDateArrivee()));
        java.time.LocalDateTime depart = donnee.getDateDepart();
        if (depart != null) {
            statement.setTimestamp(4, java.sql.Timestamp.valueOf(depart));
        } else {
            statement.setTimestamp(4, null);
        }
        statement.setInt(5, donnee.getParking().getId());
        statement.setInt(6, 1);
    };
}
