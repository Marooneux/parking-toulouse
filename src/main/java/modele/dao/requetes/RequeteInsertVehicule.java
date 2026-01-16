package modele.dao.requetes;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import modele.Vehicule;

public class RequeteInsertVehicule extends Requete<Vehicule> {

    @Override
    public String requete() {
        return "INSERT INTO vehicules (immatriculation, type_vehicule, id_utilisateur) VALUES (?, ?, ?)";
    }

    @Override
    public void parametres(PreparedStatement statement, Vehicule donnee) throws SQLException {
        statement.setString(1, donnee.getImmatriculation());
        statement.setString(2, donnee.getType().name().toLowerCase());
        statement.setInt(3, donnee.getUtilisateur().getId());
    }
}
