package modele.dao.requetes;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import modele.Vehicule;

public class RequeteUnsetVehiculesDefautByUserId extends Requete<Vehicule> {

    @Override
    public String requete() {
        return "UPDATE vehicules SET est_defaut = FALSE WHERE id_utilisateur = ?";
    }

    @Override
    public void parametres(PreparedStatement statement, Vehicule donnee) throws SQLException {
        statement.setInt(1, donnee.getUtilisateur().getId());
    }

    @Override
    public void parametres(PreparedStatement statement, String... id) throws SQLException {
        statement.setInt(1, Integer.parseInt(id[0]));
    }
}
