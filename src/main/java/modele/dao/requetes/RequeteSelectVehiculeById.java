package modele.dao.requetes;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import modele.Vehicule;

public class RequeteSelectVehiculeById extends Requete<Vehicule> {

    @Override
    public String requete() {
        return "SELECT * FROM vehicules WHERE id = ?";
    }

    @Override
    public void parametres(PreparedStatement statement, String... id) throws SQLException {
        statement.setInt(1, Integer.parseInt(id[0]));
    }
}
