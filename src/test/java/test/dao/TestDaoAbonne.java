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

import modele.Abonne;
import modele.Abonnement;
import modele.Utilisateur;
import modele.Utilisateur.Type;
import modele.dao.DaoAbonne;
import modele.dao.DaoAbonnement;
import modele.dao.DaoUtilisateur;
import modele.dao.MySQLDataSource;

public class TestDaoAbonne {

	private static Connection cn;
	private DaoAbonne daoAbonne;
	private DaoUtilisateur daoUtilisateur;
	private DaoAbonnement daoAbonnement;

	private Utilisateur utilisateurTest;
	private Abonnement abonnementTest;
	private Abonne abonneTest;

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
		this.daoAbonne = new DaoAbonne();

		this.utilisateurTest = new Utilisateur(0, "Nom", "Prenom", "user@test.com", "mdp", Type.CLIENT);
		this.daoUtilisateur.create(this.utilisateurTest);

		this.abonnementTest = new Abonnement(0, "Premium", "Abonnement premium test");
		this.daoAbonnement.create(this.abonnementTest);

		this.abonneTest = new Abonne(this.utilisateurTest, this.abonnementTest, true);
	}

	@After
	public void tearDown() throws SQLException {
		if (cn != null) {
			cn.rollback();
			MySQLDataSource.deconnecter();
		}
		this.daoAbonne = null;
		this.daoUtilisateur = null;
		this.daoAbonnement = null;
		this.utilisateurTest = null;
		this.abonnementTest = null;
		this.abonneTest = null;
	}

	@Test
	public void testCreateAndFindById() throws SQLException {
		this.daoAbonne.create(this.abonneTest);
		assertNotNull(this.abonneTest.getUtilisateur());
		assertNotNull(this.abonneTest.getAbonnement());

		Abonne a2 = this.daoAbonne.findById(this.utilisateurTest.getId(), this.abonnementTest.getId());
		assertNotNull(a2);
		assertEquals(this.utilisateurTest.getId(), a2.getUtilisateur().getId());
		assertEquals(this.abonnementTest.getId(), a2.getAbonnement().getId());
		assertEquals(true, a2.isEstActif());
	}

	@Test
	public void testUpdate() throws SQLException {
		this.daoAbonne.create(this.abonneTest);

		this.abonneTest.setEstActif(false);
		this.daoAbonne.update(this.abonneTest);

		Abonne a2 = this.daoAbonne.findById(this.utilisateurTest.getId(), this.abonnementTest.getId());
		assertEquals(false, a2.isEstActif());
	}

	@Test
	public void testDelete() throws SQLException {
		this.daoAbonne.create(this.abonneTest);

		this.daoAbonne.delete(this.abonneTest);

		Abonne a2 = this.daoAbonne.findById(this.utilisateurTest.getId(), this.abonnementTest.getId());
		assertNull(a2);
	}

	@Test
	public void testFindAll() throws SQLException {
		int avant = this.daoAbonne.findAll().size();

		this.daoAbonne.create(this.abonneTest);

		List<Abonne> liste = this.daoAbonne.findAll();
		assertEquals(avant + 1, liste.size());
	}
}
