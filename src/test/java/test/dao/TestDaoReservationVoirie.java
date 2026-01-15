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
import modele.Utilisateur.Type;
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

		this.utilisateur = new Utilisateur(0, "Dupont", "Jean", "jean.dupont@test.com", "mdp123", Type.CLIENT);
		this.daoUser.create(this.utilisateur);

		this.zone = new ZoneVoirie(0, "Zone Test", 2.5, 120);
		this.daoZone.create(this.zone);

		this.reservation = new ReservationVoirie(0, LocalDateTime.now(), 60, this.zone, this.utilisateur);
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
	public void testCreateAndFindById() throws SQLException {
		ReservationVoirie r = this.daoRes.findById(this.reservation.getId());
		assertNotNull(r);
		assertEquals(this.reservation.getId(), r.getId());
		assertEquals(this.reservation.getUtilisateur().getId(), r.getUtilisateur().getId());
		assertEquals(this.reservation.getZone().getId(), r.getZone().getId());
		assertEquals(this.reservation.getDureeMinutes(), r.getDureeMinutes());
	}

	@Test
	public void testUpdate() throws SQLException {
		this.reservation.setDureeMinutes(90);
		this.daoRes.update(this.reservation);

		ReservationVoirie r = this.daoRes.findById(this.reservation.getId());
		assertEquals(90, r.getDureeMinutes());
	}

	@Test
	public void testFindAll() throws SQLException {
		List<ReservationVoirie> list = this.daoRes.findAll();
		assertTrue(list.size() > 0);
		assertTrue(list.stream().anyMatch(r -> r.getId() == this.reservation.getId()));
	}

	@Test
	public void testDelete() throws SQLException {
		this.daoRes.delete(this.reservation);
		ReservationVoirie r = this.daoRes.findById(this.reservation.getId());
		assertNull(r);
		this.reservation = null;
	}
}
