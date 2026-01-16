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

import modele.Utilisateur;
import modele.Utilisateur.Type;
import modele.dao.DaoUtilisateur;
import modele.dao.MySQLDataSource;

public class TestDaoUtilisateur {

	private static Connection cn;
	private DaoUtilisateur daoUtilisateur;
	private Utilisateur utilisateur;

	@BeforeClass
	public static void initConnexion() {
		MySQLDataSource.creerAcces();
	}

	@Before
	public void setUp() throws SQLException {
		cn = MySQLDataSource.getConnexion();
		cn.setAutoCommit(false);

		this.daoUtilisateur = new DaoUtilisateur();
		this.utilisateur = new Utilisateur(0, "Dupont", "Jean", "j.dupont@example.com", "mdp123",
				Type.CLIENT);
		this.daoUtilisateur.create(this.utilisateur);
	}

	@After
	public void tearDown() throws SQLException {
		if (cn != null) {
			cn.rollback();
			MySQLDataSource.deconnecter();
		}
		this.daoUtilisateur = null;
		this.utilisateur = null;
	}

	@Test
	public void testCreateAndFindById() throws SQLException {
		assertTrue(this.utilisateur.getId() > 0);
		Utilisateur utilisateur2 = this.daoUtilisateur.findById(this.utilisateur.getId());
		assertNotNull(utilisateur2);
		assertEquals(this.utilisateur.getNom(), utilisateur2.getNom());
		assertEquals(this.utilisateur.getPrenom(), utilisateur2.getPrenom());
		assertEquals(this.utilisateur.getEmail(), utilisateur2.getEmail());
	}

	@Test
	public void testUpdate() throws SQLException {
		this.utilisateur.setNom("Durand");
		this.utilisateur.setPrenom("Paul");
		this.daoUtilisateur.update(this.utilisateur);

		Utilisateur u2 = this.daoUtilisateur.findById(this.utilisateur.getId());
		assertEquals("Durand", u2.getNom());
		assertEquals("Paul", u2.getPrenom());
	}

	@Test
	public void testDelete() throws SQLException {
		this.daoUtilisateur.delete(this.utilisateur);
		Utilisateur utilisateur2 = this.daoUtilisateur.findById(this.utilisateur.getId());
		assertNull(utilisateur2);
	}

	@Test
	public void testFindAll() throws SQLException {
		Utilisateur utilisateur2 = new Utilisateur(0, "Durand", "Paul", "p.durand@example.com", "mdp456",
				Type.CLIENT);
		this.daoUtilisateur.create(utilisateur2);

		List<Utilisateur> all = this.daoUtilisateur.findAll();
		assertTrue(all.size() >= 2);
		assertTrue(all.stream().anyMatch(u -> u.getId() == this.utilisateur.getId()));
		assertTrue(all.stream().anyMatch(u -> u.getId() == utilisateur2.getId()));
	}
}
