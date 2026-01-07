package modele.dao.requetes;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import modele.Parking;

public class RequeteSelectParkingByAdminId extends Requete<Parking> {
    
    @Override
    public String requete() {
        return "SELECT p.* FROM parkings p " +
                "JOIN admins_parkings ap ON p.id_parking = ap.id_parking " +
                "JOIN utilisateurs u ON u.id_utilisateur = ap.id_utilisateur " +
                "WHERE u.id_utilisateur = ? AND u.adminParking = 'adminParking'";
    }

    @Override
    public void parametres(PreparedStatement statement, String... id) throws SQLException {
        statement.setInt(1, Integer.parseInt(id[0]));
    }


}
