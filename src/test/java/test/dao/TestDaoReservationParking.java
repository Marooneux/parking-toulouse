package test.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import modele.Parking;
import modele.ReservationParking;
import modele.Utilisateur;
import modele.dao.DaoParking;
import modele.dao.DaoReservationParking;
import modele.dao.DaoUtilisateur;
import modele.dao.MySQLDataSource;

public class TestDaoReservationParking {

	private static Connection cn;
	private DaoParking daoParking;
	private DaoUtilisateur daoUtilisateur;
	private DaoReservationParking daoReservation;

	private Parking parkingTest;
	private Utilisateur utilisateurTest;
	private ReservationParking reservationTest;

	@BeforeClass
	public static void initConnexion() {
		MySQLDataSource.creerAcces("user", "password");
	}

	@Before
	public void setUp() throws SQLException {
		cn = MySQLDataSource.getConnexion();
		cn.setAutoCommit(false);

		this.daoParking = new DaoParking();
		this.daoUtilisateur = new DaoUtilisateur();
		this.daoReservation = new DaoReservationParking();

		this.parkingTest = new Parking(0, "Parking Test", "1 rue du Test", 100, 2.5,
				java.time.LocalTime.of(7, 0), java.time.LocalTime.of(23, 0), true, 2.5);
		this.daoParking.create(this.parkingTest);

		this.utilisateurTest = new Utilisateur(0, "Nom", "Prenom", "user@test.com", "password", null);
		this.daoUtilisateur.create(this.utilisateurTest);

		this.reservationTest = new ReservationParking("AB-123-CD", this.parkingTest,
				LocalDateTime.of(2026, 1, 7, 10, 0),
				this.utilisateurTest.getId());
	}

	@After
	public void tearDown() throws SQLException {
		if (cn != null) {
			cn.rollback();
			MySQLDataSource.deconnecter();
		}
		this.daoParking = null;
		this.daoUtilisateur = null;
		this.daoReservation = null;
		this.parkingTest = null;
		this.utilisateurTest = null;
		this.reservationTest = null;
	}

	@Test
	public void testCreateAndFindById() throws SQLException {
		this.daoReservation.create(this.reservationTest);

		ReservationParking r = this.daoReservation.findById(this.reservationTest.getImmatriculation(),
				this.parkingTest.getId());
		assertNotNull(r);
		assertEquals("AB-123-CD", r.getImmatriculation());
		assertEquals(this.parkingTest.getId(), r.getParking().getId());
		assertEquals(this.utilisateurTest.getId(), r.getIdUtilisateur());
	}

	@Test
	public void testUpdate() throws SQLException {
		this.daoReservation.create(this.reservationTest);

		// modifier la date de départ
		LocalDateTime depart = LocalDateTime.of(2026, 1, 7, 12, 0);
		this.reservationTest.setDateDepart(depart);
		this.daoReservation.update(this.reservationTest);

		ReservationParking r = this.daoReservation.findById(this.reservationTest.getImmatriculation(),
				this.parkingTest.getId());
		assertEquals(depart, r.getDateDepart());
	}

	@Test
	public void testDelete() throws SQLException {
		this.daoReservation.create(this.reservationTest);

		this.daoReservation.delete(this.reservationTest);

		ReservationParking r = this.daoReservation.findById(this.reservationTest.getImmatriculation(),
				this.parkingTest.getId());
		assertNull(r);
	}

	@Test
	public void testFindAll() throws SQLException {
		int avant = this.daoReservation.findAll().size();

		this.daoReservation.create(this.reservationTest);

		List<ReservationParking> liste = this.daoReservation.findAll();
		assertEquals(avant + 1, liste.size());
	}
}
