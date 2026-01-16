package test.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalTime;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import modele.ZoneVoirie;
import modele.dao.DaoZoneVoirie;
import modele.dao.MySQLDataSource;

public class TestDaoZoneVoirie {

	private static Connection cn;
	private DaoZoneVoirie dao;
	private ZoneVoirie zoneTest;

	@BeforeClass
	public static void initConnexion() {
		MySQLDataSource.creerAcces();
	}

	@Before
	public void setUp() throws SQLException {
		cn = MySQLDataSource.getConnexion();
		cn.setAutoCommit(false);
		this.dao = new DaoZoneVoirie();
		
		LocalTime debAm = LocalTime.of(9, 0);
		LocalTime finAm = LocalTime.of(12, 0);
		LocalTime debPm = LocalTime.of(14, 0);
		LocalTime finPm = LocalTime.of(19, 0);

		this.zoneTest = new ZoneVoirie(0, "rouge", 120.0, 180, debAm, finAm, debPm, finPm);
		this.dao.create(this.zoneTest);
	}

	@After
	public void tearDown() throws SQLException {
		if (cn != null) {
			cn.rollback();
			MySQLDataSource.deconnecter();
		}
		this.dao = null;
		this.zoneTest = null;
	}

	@Test
	public void testCreateAndFindById() throws SQLException {
		ZoneVoirie z = this.dao.findById(this.zoneTest.getId());
		assertNotNull(z);
		assertEquals("rouge", z.getCouleur());
		assertEquals(120.0, z.getTarifHoraire(), 0.01);
		assertEquals(180, z.getDureeMax());
	}

	@Test
	public void testUpdate() throws SQLException {
		this.zoneTest.setCouleur("orange");
		this.zoneTest.setTarifHoraire(150.0);
		this.zoneTest.setDureeMax(240);
		this.dao.update(this.zoneTest);

		ZoneVoirie z = this.dao.findById(this.zoneTest.getId());
		assertNotNull(z);
		assertEquals("orange", z.getCouleur());
		assertEquals(150.0, z.getTarifHoraire(), 0.01);
		assertEquals(240, z.getDureeMax());
	}

	@Test
	public void testDelete() throws SQLException {
		LocalTime t1 = LocalTime.of(8, 0);
		LocalTime t2 = LocalTime.of(20, 0);
		
		ZoneVoirie zTemp = new ZoneVoirie(0, "rouge", 50.0, 60, t1, t2, null, null);
		this.dao.create(zTemp);
		int idTemp = zTemp.getId();

		this.dao.delete(zTemp);
		ZoneVoirie z = this.dao.findById(idTemp);
		assertNull(z);
	}

	@Test
	public void testFindAll() throws SQLException {
		List<ZoneVoirie> zones = this.dao.findAll();
		assertNotNull(zones);
		assertTrue(zones.size() >= 1);
		boolean trouve = zones.stream().anyMatch(z -> z.getId() == this.zoneTest.getId());
		assertTrue(trouve);
	}
}