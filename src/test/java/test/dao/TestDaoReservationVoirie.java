package test.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import modele.ReservationVoirie;
import modele.Utilisateur;
import modele.ZoneVoirie;
import modele.dao.DaoReservationVoirie;
import modele.dao.DaoUtilisateur;
import modele.dao.DaoZoneVoirie;
import modele.dao.MySQLDataSource;

public class TestDaoReservationVoirie {

	private static Connection cn;
	private DaoReservationVoirie daoRes;
	private DaoUtilisateur daoUser;
	private DaoZoneVoirie daoZone;
	private Utilisateur utilisateur;
	private ZoneVoirie zone;
	private ReservationVoirie reservation;

	@BeforeClass
	public static void initConnexion() {
		MySQLDataSource.creerAcces("user", "password");
	}

	@Before
	public void setUp() throws SQLException {
		cn = MySQLDataSource.getConnexion();
		cn.setAutoCommit(false);
		this.daoRes = new DaoReservationVoirie();
		this.daoUser = new DaoUtilisateur();
		this.daoZone = new DaoZoneVoirie();

		this.utilisateur = new Utilisateur(0, "Dupont", "Jean", "jean.dupont@test.com", "mdp123", null);
		this.daoUser.create(this.utilisateur);

		this.zone = new ZoneVoirie(0, "Zone Test", 2.5, 120);
		this.daoZone.create(this.zone);

		this.reservation = new ReservationVoirie(
				"AB-123-CD",
				"Voiture",
				LocalDateTime.now(),
				60,
				this.zone.getId(),
				this.utilisateur.getId());
		this.daoRes.create(this.reservation);
	}

	@After
	public void tearDown() throws SQLException {
		if (cn != null) {
			cn.rollback();
			MySQLDataSource.deconnecter();
		}
		this.daoRes = null;
		this.daoUser = null;
		this.daoZone = null;
		this.utilisateur = null;
		this.zone = null;
		this.reservation = null;
	}

	@Test
	public void testCreateAndFindById() throws Exception {
		ReservationVoirie r = this.daoRes.findById(this.reservation.getImmatriculation());
		assertNotNull(r);
		assertEquals(this.reservation.getImmatriculation(), r.getImmatriculation());
		assertEquals(this.reservation.getIdUtilisateur(), r.getIdUtilisateur());
		assertEquals(this.reservation.getIdZone(), r.getIdZone());
	}

	@Test
	public void testUpdate() throws Exception {
		this.reservation.setDureeMinutes(90);
		this.daoRes.update(this.reservation);
		ReservationVoirie r = this.daoRes.findById(this.reservation.getImmatriculation());
		assertEquals(90, r.getDureeMinutes());
	}

	@Test
	public void testFindAll() throws Exception {
		List<ReservationVoirie> list = this.daoRes.findAll();
		assertTrue(list.size() > 0);
	}

	@Test
	public void testDelete() throws Exception {
		this.daoRes.delete(this.reservation);
		ReservationVoirie r = this.daoRes.findById(this.reservation.getImmatriculation());
		assertNull(r);
		this.reservation = null;
	}
}
