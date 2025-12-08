package modele.dao.requetes;

import modele.Parking;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RequesteSelectParkingById extends Requete<Parking> {

    @Override
    public String requete() {
        return "SELECT * FROM parking WHERE id = ?";
    }

    @Override
    public void parametres(PreparedStatement statement, String ...id) throws SQLException {
        statement.setString(1, id[0]);
    };

    // ! A vérifier la clé du parking pour les requetes.
    @Override
    public void parametres(PreparedStatement statement, Parking donnee) throws SQLException {
        statement.setString(1, donnee.getNom());
    }
}
