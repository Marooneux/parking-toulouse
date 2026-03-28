package test.dao;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import modele.dao.DaoStatistique;
import modele.dao.DaoStatistique.RecentActivity;
import modele.dao.MySQLDataSource;

public class TestDaoStatistique {

	private static Connection cn;
	private DaoStatistique daoStatistique;

	@BeforeClass
	public static void initConnexion() {
		MySQLDataSource.creerAcces();
	}

	@Before
	public void setUp() throws SQLException {
		cn = MySQLDataSource.getConnexion();
		cn.setAutoCommit(false);
		this.daoStatistique = new DaoStatistique(cn);
	}

	@After
	public void tearDown() throws SQLException {
		if (cn != null) {
			cn.rollback();
			MySQLDataSource.deconnecter();
		}
		this.daoStatistique = null;
	}

	@Test
	public void testRecentActivityModel() {
		RecentActivity activity = new RecentActivity();
		assertNotNull(activity);
		assertNull(activity.getDate());
		assertNull(activity.getLieu());
		assertNull(activity.getDetails());
		assertEquals(0, activity.getDureeMinutes());
		assertEquals(0.0, activity.getTarif(), 0.01);
	}

	@Test
	public void testRecentActivitySettersAndGetters() {
		RecentActivity activity = new RecentActivity();
		
		activity.setLieu("Parking Central");
		assertEquals("Parking Central", activity.getLieu());
		
		activity.setDetails("Parking Central - 2 heures");
		assertEquals("Parking Central - 2 heures", activity.getDetails());
		
		activity.setDureeMinutes(120);
		assertEquals(120, activity.getDureeMinutes());
		
		activity.setTarif(15.50);
		assertEquals(15.50, activity.getTarif(), 0.01);
	}

	@Test
	public void testGetParkingMontantMoisReturnsNumber() throws SQLException {
		// Test that the method returns a valid number (not negative)
		double montant = daoStatistique.getParkingMontantMois(1, 2024);
		assertTrue(montant >= 0);
	}

	@Test
	public void testGetParkingMontantMoisWithValidMonthYear() throws SQLException {
		// Test with various months and years
		double montant12 = daoStatistique.getParkingMontantMois(12, 2024);
		assertTrue(montant12 >= 0);
		
		double montant6 = daoStatistique.getParkingMontantMois(6, 2024);
		assertTrue(montant6 >= 0);
	}

	@Test
	public void testGetParkingMontantMoisWithBoundaryMonths() throws SQLException {
		// Test with month 1
		double montant1 = daoStatistique.getParkingMontantMois(1, 2024);
		assertTrue(montant1 >= 0);
		
		// Test with month 12
		double montant12 = daoStatistique.getParkingMontantMois(12, 2024);
		assertTrue(montant12 >= 0);
	}

	@Test
	public void testGetParkingTotalSessionsReturnsNumber() throws SQLException {
		int sessions = daoStatistique.getParkingTotalSessions(1, 2024);
		assertTrue(sessions >= 0);
	}

	@Test
	public void testGetParkingTotalSessionsWithValidMonthYear() throws SQLException {
		int sessions6 = daoStatistique.getParkingTotalSessions(6, 2024);
		assertTrue(sessions6 >= 0);
		
		int sessions12 = daoStatistique.getParkingTotalSessions(12, 2024);
		assertTrue(sessions12 >= 0);
	}

	@Test
	public void testGetParkingTotalSessionsWithDifferentYears() throws SQLException {
		int sessions2023 = daoStatistique.getParkingTotalSessions(1, 2023);
		int sessions2024 = daoStatistique.getParkingTotalSessions(1, 2024);
		
		assertTrue(sessions2023 >= 0);
		assertTrue(sessions2024 >= 0);
	}

	@Test
	public void testGetParkingZoneFavoriteReturnsString() {
		String zone = daoStatistique.getParkingZoneFavorite(1, 2024);
		assertNotNull(zone);
		assertEquals("Parking", zone);
	}

	@Test
	public void testGetParkingZoneFavoriteConsistent() {
		String zone1 = daoStatistique.getParkingZoneFavorite(1, 2024);
		String zone2 = daoStatistique.getParkingZoneFavorite(6, 2024);
		
		assertEquals(zone1, zone2);
		assertEquals("Parking", zone1);
	}

	@Test
	public void testGetParkingOccupationReturnsValidPercentage() throws SQLException {
		int occupation = daoStatistique.getParkingOccupation(1, 2024);
		assertTrue("Occupation should be >= 0", occupation >= 0);
		assertTrue("Occupation should be <= 100", occupation <= 100);
	}

	@Test
	public void testGetParkingOccupationWithValidMonthYear() throws SQLException {
		int occ1 = daoStatistique.getParkingOccupation(1, 2024);
		int occ6 = daoStatistique.getParkingOccupation(6, 2024);
		int occ12 = daoStatistique.getParkingOccupation(12, 2024);
		
		assertTrue(occ1 >= 0 && occ1 <= 100);
		assertTrue(occ6 >= 0 && occ6 <= 100);
		assertTrue(occ12 >= 0 && occ12 <= 100);
	}

	@Test
	public void testGetParkingSessionsPerDayReturnsValidList() throws SQLException {
		List<Integer> sessions = daoStatistique.getParkingSessionsPerDay(1, 2024);
		assertNotNull(sessions);
		assertEquals(7, sessions.size()); // Should have 7 days of week
		
		for (Integer day : sessions) {
			assertTrue(day >= 0); // All values should be non-negative
		}
	}

	@Test
	public void testGetParkingSessionsPerDayForDifferentMonths() throws SQLException {
		List<Integer> sessions1 = daoStatistique.getParkingSessionsPerDay(1, 2024);
		List<Integer> sessions6 = daoStatistique.getParkingSessionsPerDay(6, 2024);
		
		assertEquals(7, sessions1.size());
		assertEquals(7, sessions6.size());
	}

	@Test
	public void testGetParkingZoneDistributionReturnsValidList() {
		List<Integer> distribution = daoStatistique.getParkingZoneDistribution(1, 2024);
		assertNotNull(distribution);
		assertEquals(5, distribution.size()); // Should have 5 zones
		
		for (Integer zone : distribution) {
			assertTrue(zone >= 0);
		}
	}

	@Test
	public void testGetParkingRevenueTrendReturnsValidList() throws SQLException {
		List<Double> trend = daoStatistique.getParkingRevenueTrend(1, 2024);
		assertNotNull(trend);
		assertTrue(trend.size() > 0); // Must have at least one value
		
		for (Double value : trend) {
			assertTrue(value >= 0);
		}
	}

	@Test
	public void testGetParkingRevenueTrendForDifferentMonths() throws SQLException {
		List<Double> trend1 = daoStatistique.getParkingRevenueTrend(1, 2024);
		List<Double> trend6 = daoStatistique.getParkingRevenueTrend(6, 2024);
		
		assertNotNull(trend1);
		assertNotNull(trend6);
		
		assertTrue(trend1.size() >= 1);
		assertTrue(trend6.size() >= 1);
		
		// All values should be non-negative
		trend1.forEach(v -> assertTrue(v >= 0));
		trend6.forEach(v -> assertTrue(v >= 0));
	}

	@Test
	public void testGetParkingRecentActivityReturnsValidList() throws SQLException {
		List<RecentActivity> activities = daoStatistique.getParkingRecentActivity(1, 2024);
		assertNotNull(activities);
		
		// Each activity should have valid data if any exist
		for (RecentActivity activity : activities) {
			assertNotNull(activity);
			assertTrue(activity.getDureeMinutes() >= 0);
			assertTrue(activity.getTarif() >= 0);
		}
	}

	@Test
	public void testGetParkingRecentActivityForDifferentMonths() throws SQLException {
		List<RecentActivity> activities1 = daoStatistique.getParkingRecentActivity(1, 2024);
		List<RecentActivity> activities6 = daoStatistique.getParkingRecentActivity(6, 2024);
		
		assertNotNull(activities1);
		assertNotNull(activities6);
		
		// Both should have at most 5 activities (LIMIT 5)
		assertTrue(activities1.size() <= 5);
		assertTrue(activities6.size() <= 5);
	}

	@Test
	public void testStatisticsConsistency() throws SQLException {
		// If there are sessions, montant should be >= 0
		int sessions = daoStatistique.getParkingTotalSessions(1, 2024);
		double montant = daoStatistique.getParkingMontantMois(1, 2024);
		
		if (sessions > 0) {
			assertTrue("Montant should be positive if sessions exist", montant >= 0);
		}
	}

	@Test
	public void testOccupationCalculation() throws SQLException {
		// Occupation should be calculated from total sessions and days in month
		int occupation = daoStatistique.getParkingOccupation(1, 2024);
		int sessions = daoStatistique.getParkingTotalSessions(1, 2024);
		
		// With 31 days in January: occupation = (sessions * 100) / 31
		int expectedOccupation = (int) Math.round((sessions * 100.0) / 31);
		assertEquals(expectedOccupation, occupation);
	}

	@Test
	public void testRecentActivityDataModel() {
		RecentActivity activity = new RecentActivity();
		
		String testLieu = "Test Parking";
		String testDetails = "Test Details";
		int testDuree = 45;
		double testTarif = 12.75;
		
		activity.setLieu(testLieu);
		activity.setDetails(testDetails);
		activity.setDureeMinutes(testDuree);
		activity.setTarif(testTarif);
		
		assertEquals(testLieu, activity.getLieu());
		assertEquals(testDetails, activity.getDetails());
		assertEquals(testDuree, activity.getDureeMinutes());
		assertEquals(testTarif, activity.getTarif(), 0.01);
	}
}
