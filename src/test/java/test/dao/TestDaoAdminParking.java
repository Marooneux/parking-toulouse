package test.dao;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalTime;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import modele.Utilisateur;
import modele.Utilisateur.Type;
import modele.Parking;
import modele.Adresse;
import modele.dao.DaoAdminParking;
import modele.dao.DaoParking;
import modele.dao.DaoUtilisateur;
import modele.dao.DaoAdresse;
import modele.dao.MySQLDataSource;
import modele.dao.requetes.RequeteInsertAdminParking.Association;

public class TestDaoAdminParking {

    private static Connection cn;

    private DaoAdminParking daoAdminParking;
    private DaoUtilisateur daoUtilisateur;
    private DaoParking daoParking;
    private DaoAdresse daoAdresse;

    private Utilisateur utilisateurTest;
    private Parking parkingTest;
    private Adresse adresseTest;
    private Association associationTest;

    @BeforeClass
    public static void initConnexion() {
        MySQLDataSource.creerAcces();
    }

    @Before
    public void setUp() throws SQLException {
        cn = MySQLDataSource.getConnexion();
        cn.setAutoCommit(false);

        this.daoAdminParking = new DaoAdminParking();
        this.daoUtilisateur = new DaoUtilisateur();
        this.daoParking = new DaoParking();
        this.daoAdresse = new DaoAdresse();

        // 1) Create address FIRST (fixes FK error)
        this.adresseTest = new Adresse(0, "Rue Test", 0, null);
        this.daoAdresse.create(this.adresseTest);

        // 2) Create user
        this.utilisateurTest = new Utilisateur(
                0,
                "Nom",
                "Prenom",
                "admin@test.com",
                "mdp",
                null,
                Type.PARKINGADMIN
        );
        this.daoUtilisateur.create(this.utilisateurTest);

        // 3) Create parking with REAL address ID
        this.parkingTest = new Parking(
                "Parking Test",
                100,
                0,
                2.0,
                LocalTime.of(8, 0),
                LocalTime.of(20, 0),
                true,
                this.adresseTest,
                2.5
        );
        this.daoParking.create(this.parkingTest);

        // 4) Create association object
        this.associationTest = new Association(
                this.utilisateurTest.getId(),
                this.parkingTest.getId()
        );
    }

    @After
    public void tearDown() throws SQLException {
        if (cn != null) {
            cn.rollback();
            MySQLDataSource.deconnecter();
        }
    }

    @Test
    public void testCreate() throws SQLException {
        this.daoAdminParking.create(this.associationTest);

        List<Association> liste = this.daoAdminParking.findAll();
        assertNotNull(liste);

        boolean found = liste.stream().anyMatch(a ->
                a.idUtilisateur == utilisateurTest.getId() &&
                a.idParking == parkingTest.getId()
        );

        assertTrue(found);
    }

    @Test
    public void testFindAll() throws SQLException {
        int avant = this.daoAdminParking.findAll().size();

        this.daoAdminParking.create(this.associationTest);

        List<Association> liste = this.daoAdminParking.findAll();
        assertEquals(avant + 1, liste.size());
    }

    @Test
    public void testCreerInstance() throws SQLException {
        this.daoAdminParking.create(this.associationTest);

        List<Association> liste = this.daoAdminParking.findAll();
        assertNotNull(liste);
        assertFalse(liste.isEmpty());

        Association a = liste.get(0);

        assertEquals(utilisateurTest.getId(), a.idUtilisateur);
        assertEquals(parkingTest.getId(), a.idParking);
    }
}
