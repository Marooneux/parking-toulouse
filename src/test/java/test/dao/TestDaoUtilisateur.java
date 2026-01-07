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

import modele.Abonnement;
import modele.Utilisateur;
import modele.dao.DaoAbonnement;
import modele.dao.DaoUtilisateur;
import modele.dao.MySQLDataSource;

public class TestDaoUtilisateur {

	private static Connection cn;
	private DaoUtilisateur daoUtilisateur;
	private DaoAbonnement daoAbonnement;
	private Abonnement abonnement;

	@BeforeClass
	public static void initConnexion() {
		MySQLDataSource.creerAcces("user", "password");
	}

	@Before
	public void setUp() throws SQLException {
		cn = MySQLDataSource.getConnexion();
		cn.setAutoCommit(false);

		this.daoUtilisateur = new DaoUtilisateur();
		this.daoAbonnement = new DaoAbonnement();
		this.abonnement = new Abonnement(0, "Premium", "Abonnement premium test");
		this.daoAbonnement.create(this.abonnement);
	}

	@After
	public void tearDown() throws SQLException {
		if (cn != null) {
			cn.rollback();
			MySQLDataSource.deconnecter();
		}
		this.daoUtilisateur = null;
		this.daoAbonnement = null;
	}

	@Test
	public void testCreateAndFindById() throws SQLException {
		Utilisateur u = new Utilisateur(0, "Dupont", "Jean", "j.dupont@example.com", "mdp123", this.abonnement);
		this.daoUtilisateur.create(u);
		assertTrue(u.getId() > 0);

		Utilisateur u2 = this.daoUtilisateur.findById(u.getId());
		assertNotNull(u2);
		assertEquals(u.getNom(), u2.getNom());
		assertEquals(u.getPrenom(), u2.getPrenom());
		assertEquals(u.getEmail(), u2.getEmail());
		assertEquals(u.getAbonnement().getId(), u2.getAbonnement().getId());
	}

	@Test
	public void testUpdate() throws SQLException {
		Utilisateur u = new Utilisateur(0, "Dupont", "Jean", "j.dupont@example.com", "mdp123", this.abonnement);
		this.daoUtilisateur.create(u);

		u.setNom("Durand");
		u.setPrenom("Paul");
		this.daoUtilisateur.update(u);

		Utilisateur u2 = this.daoUtilisateur.findById(u.getId());
		assertEquals("Durand", u2.getNom());
		assertEquals("Paul", u2.getPrenom());
	}

	@Test
	public void testDelete() throws SQLException {
		Utilisateur u = new Utilisateur(0, "Dupont", "Jean", "j.dupont@example.com", "mdp123", this.abonnement);
		this.daoUtilisateur.create(u);

		this.daoUtilisateur.delete(u);
		Utilisateur u2 = this.daoUtilisateur.findById(u.getId());
		assertNull(u2);
	}

	@Test
	public void testFindAll() throws SQLException {
		Utilisateur u1 = new Utilisateur(0, "Dupont", "Jean", "j.dupont@example.com", "mdp123", this.abonnement);
		Utilisateur u2 = new Utilisateur(0, "Durand", "Paul", "p.durand@example.com", "mdp456", this.abonnement);
		this.daoUtilisateur.create(u1);
		this.daoUtilisateur.create(u2);

		List<Utilisateur> all = this.daoUtilisateur.findAll();
		assertTrue(all.size() >= 2);
		assertTrue(all.stream().anyMatch(u -> u.getId() == u1.getId()));
		assertTrue(all.stream().anyMatch(u -> u.getId() == u2.getId()));
	}
}
