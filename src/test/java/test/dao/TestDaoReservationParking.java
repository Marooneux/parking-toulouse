package test.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import modele.Adresse;
import modele.Parking;
import modele.ReservationParking;
import modele.Utilisateur;
import modele.Utilisateur.Type;
import modele.dao.DaoAdresse;
import modele.dao.DaoParking;
import modele.dao.DaoReservationParking;
import modele.dao.DaoUtilisateur;
import modele.dao.MySQLDataSource;

public class TestDaoReservationParking {

	private static Connection cn;
	private DaoParking daoParking;
	private DaoAdresse daoAdresse;
	private DaoUtilisateur daoUtilisateur;
	private DaoReservationParking daoReservation;

	private Adresse adresseTest;
	private Parking parkingTest;
	private Utilisateur utilisateurTest;
	private ReservationParking reservationTest;

	@BeforeClass
	public static void initConnexion() {
		MySQLDataSource.creerAcces();
	}

	@Before
	public void setUp() throws SQLException {
		cn = MySQLDataSource.getConnexion();
		cn.setAutoCommit(false);

		this.daoAdresse = new DaoAdresse();
		this.daoParking = new DaoParking();
		this.daoUtilisateur = new DaoUtilisateur();
		this.daoReservation = new DaoReservationParking();

		// Création d'une adresse
		this.adresseTest = new Adresse(0, "1", "rue du Test", "31000", "Toulouse");
		this.daoAdresse.create(this.adresseTest);

		// Création d'un parking avec l'adresse
		this.parkingTest = new Parking(
				"Parking Test",
				100,
				2.5,
				LocalTime.of(7, 0),
				LocalTime.of(23, 0),
				true,
				this.adresseTest,
				2.5);
		this.daoParking.create(this.parkingTest);

		// Création d'un utilisateur
		this.utilisateurTest = new Utilisateur(0, "Nom", "Prenom", "user@test.com", "password", Type.CLIENT);
		this.daoUtilisateur.create(this.utilisateurTest);

		// Création d'une réservation
		this.reservationTest = new ReservationParking(
				LocalDateTime.of(2026, 1, 7, 10, 0),
				null,
				this.parkingTest,
				this.utilisateurTest);
	}

	@After
	public void tearDown() throws SQLException {
		if (cn != null) {
			cn.rollback();
			MySQLDataSource.deconnecter();
		}
		this.daoAdresse = null;
		this.daoParking = null;
		this.daoUtilisateur = null;
		this.daoReservation = null;
		this.adresseTest = null;
		this.parkingTest = null;
		this.utilisateurTest = null;
		this.reservationTest = null;
	}

	@Test
	public void testCreateAndFindById() throws SQLException {
		this.daoReservation.create(this.reservationTest);

		ReservationParking r = this.daoReservation.findById(this.reservationTest.getId());
		assertNotNull(r);
		assertEquals(this.parkingTest.getId(), r.getParking().getId());
		assertEquals(this.utilisateurTest.getId(), r.getUtilisateur().getId());
	}

	@Test
	public void testUpdate() throws SQLException {
		this.daoReservation.create(this.reservationTest);

		LocalDateTime depart = LocalDateTime.of(2026, 1, 7, 12, 0);
		this.reservationTest.setDateDepart(depart);
		this.daoReservation.update(this.reservationTest);

		ReservationParking r = this.daoReservation.findById(this.reservationTest.getId());
		assertEquals(depart, r.getDateDepart());
	}

	@Test
	public void testDelete() throws SQLException {
		this.daoReservation.create(this.reservationTest);
		this.daoReservation.delete(this.reservationTest);

		ReservationParking r = this.daoReservation.findById(this.reservationTest.getId());
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
