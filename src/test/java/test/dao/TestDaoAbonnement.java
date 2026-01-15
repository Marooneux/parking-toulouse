package test.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import modele.Abonnement;
import modele.dao.DaoAbonnement;
import modele.dao.MySQLDataSource;

public class TestDaoAbonnement {

	private static Connection cn;
	private DaoAbonnement daoAbonnement;
	private Abonnement abonnementTest;

	@BeforeClass
	public static void initConnexion() {
		MySQLDataSource.creerAcces("user", "password");
	}

	@Before
	public void setUp() throws SQLException {
		cn = MySQLDataSource.getConnexion();
		cn.setAutoCommit(false);

		this.daoAbonnement = new DaoAbonnement();
		this.abonnementTest = new Abonnement(0, "Premium", "Abonnement premium test");
	}

	@After
	public void tearDown() throws SQLException {
		if (cn != null) {
			cn.rollback();
			MySQLDataSource.deconnecter();
		}
		this.daoAbonnement = null;
		this.abonnementTest = null;
	}

	@Test
	public void testCreateAndFindById() throws SQLException {
		this.daoAbonnement.create(this.abonnementTest);
		assertNotNull(this.abonnementTest.getId());

		Abonnement a2 = this.daoAbonnement.findById(this.abonnementTest.getId());
		assertNotNull(a2);
		assertEquals(this.abonnementTest.getNom(), a2.getNom());
		assertEquals(this.abonnementTest.getDescription(), a2.getDescription());
	}

	@Test
	public void testUpdate() throws SQLException {
		this.daoAbonnement.create(this.abonnementTest);

		this.abonnementTest.setNom("Gold");
		this.abonnementTest.setDescription("Abonnement gold modifié");
		this.daoAbonnement.update(this.abonnementTest);

		Abonnement a2 = this.daoAbonnement.findById(this.abonnementTest.getId());
		assertEquals("Gold", a2.getNom());
		assertEquals("Abonnement gold modifié", a2.getDescription());
	}

	@Test
	public void testDelete() throws SQLException {
		this.daoAbonnement.create(this.abonnementTest);
		this.daoAbonnement.delete(this.abonnementTest);

		Abonnement a2 = this.daoAbonnement.findById(this.abonnementTest.getId());
		assertNull(a2);
	}

	@Test
	public void testFindAll() throws SQLException {
		int avant = this.daoAbonnement.findAll().size();

		this.daoAbonnement.create(this.abonnementTest);

		List<Abonnement> liste = this.daoAbonnement.findAll();
		assertEquals(avant + 1, liste.size());
	}
}
