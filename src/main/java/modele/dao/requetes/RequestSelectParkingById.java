package modele.dao.requetes;

import modele.Parking;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RequestSelectParkingById extends Requete<Parking> {

    @Override
    public String request() {
        return "SELECT * FROM parking WHERE id = ?";
    }

    @Override
    public void parameters(PreparedStatement statement, String ...id) {
        try {
            statement.setString(1, id[0]);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    };

    // ! A vérifier la clé du parking pour les requetes.
    @Override
    public void parameters(PreparedStatement statement, Parking donnee) {
        try {
            statement.setString(1, donnee.getNom());
        } catch (SQLException e) {
            e.printStackTrace();

        }
    }
}
