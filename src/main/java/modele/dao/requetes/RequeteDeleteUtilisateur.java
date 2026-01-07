package modele.dao.requetes;

import modele.Utilisateur;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RequeteDeleteUtilisateur extends Requete<Utilisateur> {

    @Override
    public String requete() {
        return "DELETE FROM utilisateurs WHERE id = ?";
    }

    public void parametres(PreparedStatement ps, Utilisateur user) throws SQLException {
        ps.setInt(1, user.getId());
    }
}
