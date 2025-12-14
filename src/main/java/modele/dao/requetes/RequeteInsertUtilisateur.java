package modele.dao.requetes;

import modele.Utilisateur;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RequeteInsertUtilisateur extends Requete<Utilisateur> {
    @Override
    public String requete() {
        return "INSERT INTO utilisateurs (nom, prenom, email, mot_de_passe, id_abonnement) VALUES (?,?,?,?,?)";
    }

    @Override
    public void parametres(PreparedStatement ps, Utilisateur u) throws SQLException {
        ps.setString(1, u.getNom());
        ps.setString(2, u.getPrenom());
        ps.setString(3, u.getEmail());
        ps.setString(4, u.getMdp());
        ps.setInt(5, Integer.parseInt(u.getAbonnement()));
    }
}