package test.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import modele.LigneMetro;
import modele.dao.DaoLigneMetro;
import modele.dao.MySQLDataSource;

public class TestDaoLigneMetro {

	private DaoLigneMetro daoLigneMetro;
	private Connection cn;
	private LigneMetro ligneTest;

	@BeforeClass
	public static void initConnexion() {
		MySQLDataSource.creerAcces("user", "password");
	}

	@Before
	public void setUp() throws SQLException {
		this.cn = MySQLDataSource.getConnexion();
		this.cn.setAutoCommit(false);

		this.daoLigneMetro = new DaoLigneMetro();

		this.ligneTest = new LigneMetro(0, "Ligne Test", "Bleu");
	}

	@After
	public void tearDown() throws SQLException {
		if (this.cn != null) {
			this.cn.rollback();
			MySQLDataSource.deconnecter();
		}

		this.daoLigneMetro = null;
		this.ligneTest = null;
	}

	@Test
	public void testCreateAndFindById() throws SQLException {
		this.daoLigneMetro.create(this.ligneTest);

		assertTrue(this.ligneTest.getId() > 0);

		LigneMetro l = this.daoLigneMetro.findById(this.ligneTest.getId());
		assertNotNull(l);
		assertEquals("Ligne Test", l.getNom());
		assertEquals("Bleu", l.getCouleur());
	}

	@Test
	public void testUpdate() throws SQLException {
		this.daoLigneMetro.create(this.ligneTest);

		this.ligneTest.setNom("Ligne Modifiée");
		this.ligneTest.setCouleur("Rouge");

		this.daoLigneMetro.update(this.ligneTest);

		LigneMetro l = this.daoLigneMetro.findById(this.ligneTest.getId());
		assertEquals("Ligne Modifiée", l.getNom());
		assertEquals("Rouge", l.getCouleur());
	}

	@Test
	public void testDelete() throws SQLException {
		this.daoLigneMetro.create(this.ligneTest);

		int id = this.ligneTest.getId();
		this.daoLigneMetro.delete(this.ligneTest);

		LigneMetro l = this.daoLigneMetro.findById(id);
		assertNull(l);
	}

	@Test
	public void testFindAll() throws SQLException {
		int avant = this.daoLigneMetro.findAll().size();

		this.daoLigneMetro.create(this.ligneTest);

		List<LigneMetro> lignes = this.daoLigneMetro.findAll();
		assertEquals(avant + 1, lignes.size());
	}
}
