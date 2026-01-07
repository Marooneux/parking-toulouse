package test.dao;

import modele.Utilisateur;
import modele.dao.DaoUtilisateur;
import modele.dao.MySQLDataSource;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DaoUtilisateurTest {

    private DaoUtilisateur daoUtilisateur;
    private Utilisateur user;

    @Before
    public void setUp() {
        this.daoUtilisateur = new DaoUtilisateur();
        MySQLDataSource.creerAcces("root", "claudio");
        String nom = "Joao";
        String prenom = "Alberto";
        String email = "alberto.joao@gmail.com";
        String passwd = utils.PasswordUtil.hashMdp("Alberto123");
        String user_type = "parkingadmin";
        this.user = new Utilisateur(nom, prenom, email, passwd, user_type);
    }

    @Test
    public void insertUtilisateur() throws SQLException {
        daoUtilisateur.create(user);
        List<Utilisateur> utilisateurs =  daoUtilisateur.findAll();
        assertTrue(utilisateurs.stream().anyMatch(u -> u.getEmail().equals(this.user.getEmail())));

        daoUtilisateur.delete(user);
    }

    @Test
    public void deleteUtilisateur() throws SQLException {
        daoUtilisateur.create(user);
        List<Utilisateur> utilisateurs = daoUtilisateur.findAll();
        System.out.println("id = " + user.getId());
        daoUtilisateur.delete(user);
        assertFalse(!utilisateurs.stream().anyMatch(u -> u.getEmail().equals(this.user.getEmail())));
    }
}
