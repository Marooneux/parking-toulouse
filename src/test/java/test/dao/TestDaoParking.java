package test.dao;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.sql.SQLException;
import java.sql.Time;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import modele.Parking;
import modele.dao.DaoParking;
import modele.dao.Iterateur;
import modele.dao.MySQLDataSource;

public class TestDaoParking {

	private DaoParking daoParking;
	private Parking parking;

	@Before
	public void setUp() throws SQLException {
		MySQLDataSource.creerAcces("root", "claudio");
		this.daoParking = new DaoParking();
		this.parking = new Parking(1, "NomTest", "AdresseTest", 100, 2.0,
				Time.valueOf("08:00:00").toLocalTime(),
				Time.valueOf("18:30:00").toLocalTime(),
				true,
				1.5);
		this.daoParking.create(this.parking);
	}

	@After
	public void tearDown() throws SQLException {
		this.daoParking.delete(this.parking);
		MySQLDataSource.deconnecter();
	}

	@Test
	@DisplayName("Test create")
	public void testCreate() throws SQLException {
		List<Parking> parkings = this.daoParking.findAll();
		assertEquals(1, parkings.size());
		assertTrue(parkings.stream().anyMatch(parking -> parking.getNom().equals("NomTest")));
	}

	@Test
	@DisplayName("Test update")
	public void testUpdate() throws SQLException {
		this.parking.setNom("NouveauNomTest");
		this.daoParking.update(this.parking);

		List<Parking> parkings = this.daoParking.findAll();
		assertTrue(parkings.stream().anyMatch(parking -> parking.getNom().equals("TestUpdateModified")));
	}

	@Test
	@DisplayName("Test delete")
	public void testDelete() throws SQLException {
		this.daoParking.delete(this.parking);
		List<Parking> parkings = this.daoParking.findAll();
		assertFalse(parkings.stream().anyMatch(parking -> parking.getNom().equals("TestDelete")));
	}

	@Test
	@DisplayName("Test findAll")
	public void testFindAll() throws SQLException {
		this.daoParking.delete(this.parking);
		List<Parking> parkings = this.daoParking.findAll();
		assertNotNull(parkings, "La liste des parkings ne doit pas être nulle");
		assertTrue("La liste peut être vide mais non nulle", parkings.size() >= 0);
	}

	@Test
	@DisplayName("Test findAllIte / Iterateur")
	public void testIterateur() throws SQLException {
		Iterateur<Parking> ite = this.daoParking.findAllIte();
		if (ite != null) {
			while (ite.hasNext()) {
				Parking p = ite.next();
				assertNotNull(p, "Chaque parking retourné par l'iterateur ne doit pas être null");
			}
		}
	}
}
