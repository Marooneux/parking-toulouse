package modele.dao.requetes;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import modele.Vehicule;

public class RequeteDeleteVehicule extends Requete<Vehicule> {

    @Override
    public String requete() {
        return "DELETE FROM vehicules WHERE id = ?";
    }

    @Override
    public void parametres(PreparedStatement statement, Vehicule donnee) throws SQLException {
        statement.setInt(1, donnee.getId());
    }
}
