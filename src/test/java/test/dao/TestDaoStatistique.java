package test.dao;

import static org.junit.Assert.*;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import modele.Adresse;
import modele.Parking;
import modele.Utilisateur;
import modele.Utilisateur.Type;
import modele.dao.DaoAdresse;
import modele.dao.DaoParking;
import modele.dao.DaoUtilisateur;
import modele.dao.DaoStatistique;
import modele.dao.MySQLDataSource;

public class TestDaoStatistique {

    private static Connection cn;

    private DaoStatistique daoStat;
    private DaoParking daoParking;
    private DaoUtilisateur daoUtilisateur;
    private DaoAdresse daoAdresse;

    private Utilisateur adminUser;
    private Parking parkingTest;
    private Adresse adresseTest;

    @BeforeClass
    public static void initConnexion() {
        MySQLDataSource.creerAcces();
    }

    @Before
    public void setUp() throws Exception {
        cn = MySQLDataSource.getConnexion();
        cn.setAutoCommit(false);

        daoStat = new DaoStatistique(cn);
        daoParking = new DaoParking();
        daoUtilisateur = new DaoUtilisateur();
        daoAdresse = new DaoAdresse();

        // 1) Create Adresse
        adresseTest = new Adresse(0, "Rue Test", 0, null);
        daoAdresse.create(adresseTest);

        // 2) Create admin user with id = 3 (DAO hardcodes this)
        adminUser = new Utilisateur(3, "Admin", "Parking", "admin@test.com", "mdp", null, Type.PARKINGADMIN);
        daoUtilisateur.create(adminUser);

        // 3) Create Parking
        parkingTest = new Parking(
                "Parking Test",
                100,
                0,
                2.0,
                java.time.LocalTime.of(8, 0),
                java.time.LocalTime.of(20, 0),
                true,
                adresseTest,
                2.5
        );
        daoParking.create(parkingTest);

        // 4) Link admin to parking
        PreparedStatement ps = cn.prepareStatement(
                "INSERT INTO admins_parkings (id_utilisateur, id_parking) VALUES (?, ?)");
        ps.setInt(1, adminUser.getId());
        ps.setInt(2, parkingTest.getId());
        ps.executeUpdate();

        // 5) Insert reservations for statistics
        insertReservation(LocalDateTime.of(2024, 1, 5, 10, 0), LocalDateTime.of(2024, 1, 5, 12, 0));
        insertReservation(LocalDateTime.of(2024, 1, 10, 14, 0), LocalDateTime.of(2024, 1, 10, 15, 30));
        insertReservation(LocalDateTime.of(2024, 1, 15, 9, 0), LocalDateTime.of(2024, 1, 15, 11, 0));
    }

    private void insertReservation(LocalDateTime arrivee, LocalDateTime depart) throws Exception {
        PreparedStatement ps = cn.prepareStatement(
                "INSERT INTO reservations_parking (date_arrivee, date_depart, id_parking, id_utilisateur) VALUES (?, ?, ?, ?)");
        ps.setTimestamp(1, Timestamp.valueOf(arrivee));
        ps.setTimestamp(2, Timestamp.valueOf(depart));
        ps.setInt(3, parkingTest.getId());
        ps.setInt(4, adminUser.getId());
        ps.executeUpdate();
    }

    @After
    public void tearDown() throws Exception {
        if (cn != null) {
            cn.rollback();
            MySQLDataSource.deconnecter();
        }
    }

    // ---------------------------------------------------------
    // TESTS
    // ---------------------------------------------------------

    @Test
    public void testGetParkingMontantMois() throws Exception {
        double montant = daoStat.getParkingMontantMois(1, 2024);
        assertEquals(3 * parkingTest.getTarif(), montant, 0.0001);
    }

    @Test
    public void testGetParkingTotalSessions() throws Exception {
        int sessions = daoStat.getParkingTotalSessions(1, 2024);
        assertEquals(3, sessions);
    }

    @Test
    public void testGetParkingOccupation() throws Exception {
        int occupation = daoStat.getParkingOccupation(1, 2024);
        // January has 31 days → 3 sessions → 3/31 * 100 ≈ 10%
        assertEquals((int) Math.round(3 * 100.0 / 31), occupation);
    }

    @Test
    public void testGetParkingSessionsPerDay() throws Exception {
        List<Integer> sessions = daoStat.getParkingSessionsPerDay(1, 2024);
        assertEquals(7, sessions.size());
        assertTrue(sessions.stream().mapToInt(i -> i).sum() >= 3);
    }

    @Test
    public void testGetParkingRevenueTrend() throws Exception {
        List<Double> trend = daoStat.getParkingRevenueTrend(1, 2024);
        assertFalse(trend.isEmpty());
        assertTrue(trend.get(0) > 0);
    }

    @Test
    public void testGetParkingRecentActivity() throws Exception {
        List<DaoStatistique.RecentActivity> list = daoStat.getParkingRecentActivity(1, 2024);
        assertFalse(list.isEmpty());
        DaoStatistique.RecentActivity a = list.get(0);

        assertNotNull(a.date);
        assertNotNull(a.lieu);
        assertTrue(a.dureeMinutes > 0);
        assertTrue(a.tarif > 0);
    }
}
