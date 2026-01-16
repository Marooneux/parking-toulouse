package modele.dao.requetes;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import modele.Vehicule;

public class RequeteUpdateVehicule extends Requete<Vehicule> {

    @Override
    public String requete() {
        return "UPDATE vehicules SET immatriculation = ?, type_vehicule = ?, id_utilisateur = ? WHERE id = ?";
    }

    @Override
    public void parametres(PreparedStatement statement, Vehicule donnee) throws SQLException {
        statement.setString(1, donnee.getImmatriculation());
        statement.setString(2, donnee.getType().name().toLowerCase());
        statement.setInt(3, donnee.getUtilisateur().getId());
        statement.setInt(4, donnee.getId());
    }
}
