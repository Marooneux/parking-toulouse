package modele.dao.requetes;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import modele.Utilisateur;

public class RequeteSelectUtilisateurByEmail extends Requete<Utilisateur> {

    @Override
    public String requete() {
        return "SELECT * FROM utilisateurs WHERE email = ?";
    }

    @Override
    public void parametres(PreparedStatement statement, String... email) throws SQLException {
        statement.setString(1, email[0]);
    }
}
