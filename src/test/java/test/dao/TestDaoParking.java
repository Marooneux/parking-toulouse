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

import modele.Adresse;
import modele.Parking;
import modele.dao.DaoAdresse;
import modele.dao.DaoParking;
import modele.dao.MySQLDataSource;

public class TestDaoParking {

	private DaoParking daoParking;
	private DaoAdresse daoAdresse;
	private Connection cn;
	private Parking parkingTest;
	private Adresse adresseTest;

	@BeforeClass
	public static void initConnexion() {
		MySQLDataSource.creerAcces();
	}

	@Before
	public void setUp() throws SQLException {
		this.cn = MySQLDataSource.getConnexion();
		this.cn.setAutoCommit(false);

		this.daoAdresse = new DaoAdresse();
		this.daoParking = new DaoParking();

		this.adresseTest = new Adresse(0, "1", "rue du Test", "31000", "Toulouse");
		this.daoAdresse.create(this.adresseTest);

		this.parkingTest = new Parking(
				"Parking Test",
				120,
				2.20,
				LocalTime.of(7, 0),
				LocalTime.of(23, 0),
				true,
				this.adresseTest,
				2.5);
	}

	@After
	public void tearDown() throws SQLException {
		if (this.cn != null) {
			this.cn.rollback();
			MySQLDataSource.deconnecter();
		}
		this.daoParking = null;
		this.daoAdresse = null;
		this.parkingTest = null;
		this.adresseTest = null;
	}

	@Test
	public void testCreateAndFindById() throws SQLException {
		this.daoParking.create(this.parkingTest);
		assertTrue(this.parkingTest.getId() > 0);

		Parking p = this.daoParking.findById(this.parkingTest.getId());
		assertNotNull(p);
		assertEquals("Parking Test", p.getNom());
		assertEquals(120, p.getCapacite());
		assertEquals(2.20, p.getHauteurMax(), 0.01);
		assertEquals("rue du Test", p.getAdresse().getRue());
	}

	@Test
	public void testUpdate() throws SQLException {
		this.daoParking.create(this.parkingTest);

		this.parkingTest.setCapacite(150);
		this.parkingTest.setHauteurMax(2.50);

		// Mettre à jour l'adresse avant de mettre à jour le parking
		this.parkingTest.getAdresse().setRue("Rue Modifiee");
		this.daoAdresse.update(this.parkingTest.getAdresse());

		this.daoParking.update(this.parkingTest);

		Parking p = this.daoParking.findById(this.parkingTest.getId());
		assertEquals(150, p.getCapacite());
		assertEquals(2.50, p.getHauteurMax(), 0.01);
		assertEquals("Rue Modifiee", p.getAdresse().getRue());
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
