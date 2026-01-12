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

import modele.Parking;
import modele.dao.DaoParking;
import modele.dao.MySQLDataSource;

public class TestDaoParking {

	private DaoParking daoParking;
	private Connection cn;
	private Parking parkingTest;

	@BeforeClass
	public static void initConnexion() {
		MySQLDataSource.creerAcces("user", "password");
	}

	@Before
	public void setUp() throws SQLException {
		this.cn = MySQLDataSource.getConnexion();
		this.cn.setAutoCommit(false);

		this.daoParking = new DaoParking();

		this.parkingTest = new Parking(
				0,
				"Parking Test",
				"1 rue du Test",
				120,
				50,
				2.20,
				LocalTime.of(7, 0),
				LocalTime.of(23, 0),
				true,
				2.50);

		this.parkingTest.setNbPlacesOccupees(10);
	}

	@After
	public void tearDown() throws SQLException {
		if (this.cn != null) {
			this.cn.rollback();
			MySQLDataSource.deconnecter();
		}

		this.daoParking = null;
		this.parkingTest = null;
	}

	@Test
	public void testCreateAndFindById() throws SQLException {
		this.daoParking.create(this.parkingTest);

		assertTrue(this.parkingTest.getId() > 0);

		Parking p = this.daoParking.findById(this.parkingTest.getId());
		assertNotNull(p);
		assertEquals("Parking Test", p.getNom());
		assertEquals(10, p.getNbPlacesOccupees());
		assertEquals(2.50, p.getTarif(), 0.01);
	}

	@Test
	public void testUpdate() throws SQLException {
		this.daoParking.create(this.parkingTest);

		this.parkingTest.setNbPlacesOccupees(40);
		this.parkingTest.setTarif(3.00);

		this.daoParking.update(this.parkingTest);

		Parking p = this.daoParking.findById(this.parkingTest.getId());
		assertEquals(40, p.getNbPlacesOccupees());
		assertEquals(3.00, p.getTarif(), 0.01);
	}

	@Test
	public void testDelete() throws SQLException {
		this.daoParking.create(this.parkingTest);

		int id = this.parkingTest.getId();
		this.daoParking.delete(this.parkingTest);

		Parking p = this.daoParking.findById(id);
		assertNull(p);
	}

	@Test
	public void testFindAll() throws SQLException {
		int avant = this.daoParking.findAll().size();

		this.daoParking.create(this.parkingTest);

		List<Parking> parkings = this.daoParking.findAll();
		assertEquals(avant + 1, parkings.size());
	}
}
