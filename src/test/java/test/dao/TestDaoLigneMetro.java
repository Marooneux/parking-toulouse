package test.dao;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.sql.SQLException;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import modele.LigneMetro;
import modele.dao.DaoLigneMetro;
import modele.dao.MySQLDataSource;

class TestDaoLigneMetro {
	private DaoLigneMetro daoLigneMetro;
	private LigneMetro ligneMetro;

	@Before
	public void setUp() throws SQLException {
		MySQLDataSource.creerAcces("root", "claudio");
		this.daoLigneMetro = new DaoLigneMetro();
		this.ligneMetro = new LigneMetro(1, "NomTest", "CouleurTest");
		this.daoLigneMetro.create(this.ligneMetro);
	}

	@After
	public void tearDown() throws SQLException {
		this.daoLigneMetro.delete(this.ligneMetro);
		MySQLDataSource.deconnecter();
	}

	@Test
	@DisplayName("Test create")
	public void testCreate() throws SQLException {
		List<LigneMetro> lignesMetro = this.daoLigneMetro.findAll();
		assertEquals(1, lignesMetro.size());
		assertTrue(lignesMetro.stream().anyMatch(ligneMetro -> ligneMetro.getNom().equals("NomTest")));
	}

	@Test
	@DisplayName("Test update")
	public void testUpdate() throws SQLException {
		this.ligneMetro.setNom("NouveauNomTest");
		this.daoLigneMetro.update(this.ligneMetro);

		List<LigneMetro> lignesMetro = this.daoLigneMetro.findAll();
		assertTrue(lignesMetro.stream().anyMatch(ligneMetro -> ligneMetro.getNom().equals("TestUpdateModified")));
	}

	@Test
	@DisplayName("Test delete")
	public void testDelete() throws SQLException {
		this.daoLigneMetro.delete(this.ligneMetro);
		List<LigneMetro> lignesMetro = this.daoLigneMetro.findAll();
		assertFalse(lignesMetro.stream().anyMatch(ligneMetro -> ligneMetro.getNom().equals("TestDelete")));
	}

	@Test
	@DisplayName("Test findAll")
	public void testFindAll() throws SQLException {
		this.daoLigneMetro.delete(this.ligneMetro);
		List<LigneMetro> lignes = this.daoLigneMetro.findAll();
		assertNotNull(lignes, "La liste des lignes de métro ne doit pas être nulle");
		assertTrue("La liste peut être vide mais non nulle", lignes.size() >= 0);
	}
}
