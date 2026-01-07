package modele.dao.requetes;

import modele.Utilisateur;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import utils.PasswordUtil;

public class RequeteInsertUtilisateur extends Requete<Utilisateur> {
    @Override
    public String requete() {
        return "INSERT INTO utilisateurs (nom, prenom, email, mdp) VALUES (?,?,?,?)";
    }

    @Override
    public void parametres(PreparedStatement ps, Utilisateur u) throws SQLException {
        ps.setString(1, u.getNom());
        ps.setString(2, u.getPrenom());
        ps.setString(3, u.getEmail());
        // Store password as BCrypt hash
        ps.setString(4, PasswordUtil.hashMdp(u.getMdp()));
    }
}