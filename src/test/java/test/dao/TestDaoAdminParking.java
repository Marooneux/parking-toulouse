package test.dao;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalTime;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import modele.Adresse;
import modele.Parking;
import modele.Utilisateur;
import modele.Utilisateur.Type;
import modele.dao.DaoAdminParking;
import modele.dao.DaoAdresse;
import modele.dao.DaoParking;
import modele.dao.DaoUtilisateur;
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

        // Create test address first
        this.adresseTest = new Adresse(0, "123 Test Street", 75000, "Paris");
        this.daoAdresse.create(this.adresseTest);

        // Create test user (admin type)
        this.utilisateurTest = new Utilisateur(
                0,
                "Admin",
                "Parking",
                "admin.parking@test.com",
                "password123",
                Type.PARKINGADMIN
        );
        this.daoUtilisateur.create(this.utilisateurTest);

        // Create test parking with the address
        this.parkingTest = new Parking(
                "Test Parking",
                50,
                10,
                1.8,
                LocalTime.of(8, 0),
                LocalTime.of(22, 0),
                true,
                this.adresseTest,
                10.50
        );
        this.daoParking.create(this.parkingTest);

        // Create association
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
        this.daoAdminParking = null;
        this.daoUtilisateur = null;
        this.daoParking = null;
        this.daoAdresse = null;
        this.utilisateurTest = null;
        this.parkingTest = null;
        this.adresseTest = null;
        this.associationTest = null;
    }

    @Test
    public void testCreateAssociation() {
        // Should not throw exception
        assertNotNull(this.associationTest);
        assertEquals(this.utilisateurTest.getId(), this.associationTest.idUtilisateur);
        assertEquals(this.parkingTest.getId(), this.associationTest.idParking);
    }

    @Test
    public void testCreateAdminParkingAssociation() throws SQLException {
        // Create the association
        this.daoAdminParking.create(this.associationTest);
        
        // Verify the association was created (check IDs are valid)
        assertTrue(this.utilisateurTest.getId() > 0);
        assertTrue(this.parkingTest.getId() > 0);
    }

    @Test
    public void testMultipleAdminsForParking() throws SQLException {
        // Create another admin user
        Utilisateur admin2 = new Utilisateur(
                0,
                "Admin2",
                "Parking",
                "admin2.parking@test.com",
                "password456",
                Type.PARKINGADMIN
        );
        this.daoUtilisateur.create(admin2);

        // Create associations for both admins to same parking
        Association assoc1 = new Association(this.utilisateurTest.getId(), this.parkingTest.getId());
        Association assoc2 = new Association(admin2.getId(), this.parkingTest.getId());

        this.daoAdminParking.create(assoc1);
        this.daoAdminParking.create(assoc2);

        assertTrue(this.utilisateurTest.getId() > 0);
        assertTrue(admin2.getId() > 0);
        assertTrue(this.parkingTest.getId() > 0);
    }

    @Test
    public void testAdminWithMultipleParkings() throws SQLException {
        // Create another address
        Adresse adresse2 = new Adresse(0, "456 Test Avenue", 75001, "Paris");
        this.daoAdresse.create(adresse2);

        // Create another parking with the new address
        Parking parking2 = new Parking(
                "Test Parking 2",
                30,
                5,
                1.8,
                LocalTime.of(8, 0),
                LocalTime.of(22, 0),
                true,
                adresse2,
                8.75
        );
        this.daoParking.create(parking2);

        // Create associations for same admin with multiple parkings
        Association assoc1 = new Association(this.utilisateurTest.getId(), this.parkingTest.getId());
        Association assoc2 = new Association(this.utilisateurTest.getId(), parking2.getId());

        this.daoAdminParking.create(assoc1);
        this.daoAdminParking.create(assoc2);

        assertTrue(this.utilisateurTest.getId() > 0);
        assertEquals(this.utilisateurTest.getId(), assoc1.idUtilisateur);
        assertEquals(this.utilisateurTest.getId(), assoc2.idUtilisateur);
    }

    @Test
    public void testFindAllReturnsEmpty() throws SQLException {
        // findAll should return empty list as it's not fully implemented
        assertTrue(this.daoAdminParking.findAll().isEmpty());
    }

    @Test
    public void testAssociationWithValidIds() throws SQLException {
        Association assoc = new Association(1, 1);
        assertEquals(1, assoc.idUtilisateur);
        assertEquals(1, assoc.idParking);
    }

    @Test
    public void testAssociationWithLargeIds() throws SQLException {
        Association assoc = new Association(999999, 888888);
        assertEquals(999999, assoc.idUtilisateur);
        assertEquals(888888, assoc.idParking);
    }

    @Test
    public void testAssociationWithZeroIds() throws SQLException {
        // Edge case: zero as ID might be invalid in practice
        Association assoc = new Association(0, 0);
        assertEquals(0, assoc.idUtilisateur);
        assertEquals(0, assoc.idParking);
    }

    @Test
    public void testCreateMultipleAssociationsSequentially() throws SQLException {
        // Create first association
        Association assoc1 = new Association(this.utilisateurTest.getId(), this.parkingTest.getId());
        this.daoAdminParking.create(assoc1);

        // Create second association with different user
        Utilisateur user2 = new Utilisateur(0, "User2", "Admin2", "user2@test.com", "pass2", Type.PARKINGADMIN);
        this.daoUtilisateur.create(user2);
        Association assoc2 = new Association(user2.getId(), this.parkingTest.getId());
        this.daoAdminParking.create(assoc2);

        // Both should be created successfully
        assertTrue(assoc1.idUtilisateur > 0);
        assertTrue(assoc2.idUtilisateur > 0);
    }
}
