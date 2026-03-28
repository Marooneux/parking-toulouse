package test.dao;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import modele.Utilisateur;
import modele.Utilisateur.Type;
import modele.Vehicule;
import modele.Vehicule.TypeVehicule;
import modele.dao.DaoUtilisateur;
import modele.dao.DaoVehicule;
import modele.dao.MySQLDataSource;

public class TestDaoVehicule {

    private static Connection cn;

    private DaoVehicule daoVehicule;
    private DaoUtilisateur daoUtilisateur;

    private Utilisateur utilisateurTest;
    private Vehicule vehiculeTest;

    @BeforeClass
    public static void initConnexion() {
        MySQLDataSource.creerAcces();
    }

    @Before
    public void setUp() throws SQLException {
        cn = MySQLDataSource.getConnexion();
        cn.setAutoCommit(false);

        daoVehicule = new DaoVehicule();
        daoUtilisateur = new DaoUtilisateur();

        // 1) Create a test user
        utilisateurTest = new Utilisateur(
                0,
                "Nom",
                "Prenom",
                "vehicule@test.com",
                "mdp",
                null,
                Type.CLIENT
        );
        daoUtilisateur.create(utilisateurTest);

        // 2) Create a test vehicle
        vehiculeTest = new Vehicule(
                0,
                "AA-123-BB",
                TypeVehicule.ELECTRIQUE,
                utilisateurTest
        );
        daoVehicule.create(vehiculeTest);
    }

    @After
    public void tearDown() throws SQLException {
        if (cn != null) {
            cn.rollback();
            MySQLDataSource.deconnecter();
        }
    }

    // ---------------------------------------------------------
    // TESTS
    // ---------------------------------------------------------

    @Test
    public void testCreate() throws SQLException {
        assertTrue(vehiculeTest.getId() > 0);

        Vehicule v = daoVehicule.findById(vehiculeTest.getId());
        assertNotNull(v);
        assertEquals("AA-123-BB", v.getImmatriculation());
        assertEquals(TypeVehicule.ELECTRIQUE, v.getType());
        assertEquals(utilisateurTest.getId(), v.getUtilisateur().getId());
    }

    @Test
    public void testUpdate() throws SQLException {
        vehiculeTest.setImmatriculation("BB-456-CC");
        daoVehicule.update(vehiculeTest);

        Vehicule v = daoVehicule.findById(vehiculeTest.getId());
        assertEquals("BB-456-CC", v.getImmatriculation());
    }

    @Test
    public void testFindAll() throws SQLException {
        List<Vehicule> liste = daoVehicule.findAll();
        assertNotNull(liste);

        boolean found = liste.stream()
                .anyMatch(v -> v.getId() == vehiculeTest.getId());
        assertTrue(found);
    }

    @Test
    public void testFindByUserId() throws SQLException {
        List<Vehicule> liste = daoVehicule.findByUserId(utilisateurTest.getId());
        assertNotNull(liste);
        assertEquals(1, liste.size());

        Vehicule v = liste.get(0);
        assertEquals(vehiculeTest.getId(), v.getId());
    }

    @Test
    public void testDelete() throws SQLException {
        daoVehicule.delete(vehiculeTest);

        Vehicule v = daoVehicule.findById(vehiculeTest.getId());
        assertNull(v);
    }
}
