package test.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalTime;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import modele.Adresse;
import modele.LigneMetro;
import modele.Parking;
import modele.Proximite;
import modele.dao.DaoAdresse;
import modele.dao.DaoLigneMetro;
import modele.dao.DaoParking;
import modele.dao.DaoProximite;
import modele.dao.MySQLDataSource;

public class TestDaoProximite {

	private DaoProximite daoProximite;
	private DaoParking daoParking;
	private DaoLigneMetro daoLigneMetro;
	private DaoAdresse daoAdresse;
	private Connection cn;

	private Adresse adresseTest;
	private Parking parkingTest;
	private LigneMetro ligneTest;
	private Proximite proximiteTest;

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
		this.daoLigneMetro = new DaoLigneMetro();
		this.daoProximite = new DaoProximite();

		// créer une adresse pour le parking
		this.adresseTest = new Adresse(0, "1", "rue du Test", "31000", "Toulouse");
		this.daoAdresse.create(this.adresseTest);

		// créer un parking avec l'adresse
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

		// créer une ligne de métro
		this.ligneTest = new LigneMetro(0, "Ligne Test", "Bleu");
		this.daoLigneMetro.create(this.ligneTest);

		// créer la proximité
		this.proximiteTest = new Proximite(this.parkingTest, this.ligneTest, 300);
	}

	@After
	public void tearDown() throws SQLException {
		if (this.cn != null) {
			this.cn.rollback();
			MySQLDataSource.deconnecter();
		}

		this.daoAdresse = null;
		this.daoParking = null;
		this.daoLigneMetro = null;
		this.daoProximite = null;
		this.adresseTest = null;
		this.parkingTest = null;
		this.ligneTest = null;
		this.proximiteTest = null;
	}

	@Test
	public void testCreateAndFindById() throws SQLException {
		this.daoProximite.create(this.proximiteTest);

		Proximite p = this.daoProximite.findById(this.parkingTest.getId(), this.ligneTest.getId());
		assertNotNull(p);
		assertEquals(300, p.getDistanceMetres());
		assertEquals(this.parkingTest.getId(), p.getParking().getId());
		assertEquals(this.ligneTest.getId(), p.getLigneMetro().getId());
	}

	@Test
	public void testUpdate() throws SQLException {
		this.daoProximite.create(this.proximiteTest);

		this.proximiteTest.setDistanceMetres(500);
		this.daoProximite.update(this.proximiteTest);

		Proximite p = this.daoProximite.findById(this.parkingTest.getId(), this.ligneTest.getId());
		assertEquals(500, p.getDistanceMetres());
	}

	@Test
	public void testDelete() throws SQLException {
		this.daoProximite.create(this.proximiteTest);

		this.daoProximite.delete(this.proximiteTest);

		Proximite p = this.daoProximite.findById(this.parkingTest.getId(), this.ligneTest.getId());
		assertNull(p);
	}

	@Test
	public void testFindAll() throws SQLException {
		int avant = this.daoProximite.findAll().size();

		this.daoProximite.create(this.proximiteTest);

		List<Proximite> liste = this.daoProximite.findAll();
		assertEquals(avant + 1, liste.size());
	}
}
