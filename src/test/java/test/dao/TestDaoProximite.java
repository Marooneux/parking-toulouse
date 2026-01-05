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
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import modele.LigneMetro;
import modele.Parking;
import modele.Proximite;
import modele.dao.DaoProximite;
import modele.dao.MySQLDataSource;

class TestDaoProximite {

	private DaoProximite daoProximite;
	private Proximite proximite;

	@Before
	public void setUp() throws SQLException {
		MySQLDataSource.creerAcces("root", "claudio");
		this.daoProximite = new DaoProximite();
		Parking parking = new Parking(1, "NomTest", "AdresseTest", 100, 2.0,
				Time.valueOf("08:00:00").toLocalTime(), Time.valueOf("18:30:00").toLocalTime(),
				true,
				1.5);
		LigneMetro ligneMetro = new LigneMetro(1, "NomTest", "CouleurTest");
		this.proximite = new Proximite(parking, ligneMetro, 100);
		this.daoProximite.create(this.proximite);
	}

	@After
	public void tearDown() throws SQLException {
		this.daoProximite.delete(this.proximite);
		MySQLDataSource.deconnecter();
	}

	@Test
	@DisplayName("Test create")
	public void testCreate() throws SQLException {
		List<Proximite> liste = this.daoProximite.findAll();
		assertEquals(1, liste.size());
		assertTrue(liste.stream().anyMatch(proximite -> proximite.getLigneMetro().getNom().equals("NomTest")));
	}

	@Test
	@DisplayName("Test update")
	public void testUpdate() throws SQLException {
		this.proximite.setDistanceMetres(150);
		this.daoProximite.update(this.proximite);

		List<Proximite> liste = this.daoProximite.findAll();
		assertTrue(liste.stream().anyMatch(proximite -> proximite.getDistanceMetres() == 150));
	}

	@Test
	@DisplayName("Test delete")
	public void testDelete() throws SQLException {
		this.daoProximite.delete(this.proximite);
		List<Proximite> liste = this.daoProximite.findAll();
		assertFalse(liste.stream().anyMatch(proximite -> proximite.getLigneMetro().equals("TestDelete")));
	}

	@Test
	@DisplayName("Test findAll")
	public void testFindAll() throws SQLException {
		this.daoProximite.delete(this.proximite);
		List<Proximite> liste = this.daoProximite.findAll();
		assertNotNull(liste, "La liste des proximités ne doit pas être nulle");
		assertTrue("La liste peut être vide mais non nulle", liste.size() >= 0);
	}
}
