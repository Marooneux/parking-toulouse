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

import modele.Adresse;
import modele.dao.DaoAdresse;
import modele.dao.MySQLDataSource;

public class TestDaoAdresse {

	private static Connection cn;
	private DaoAdresse daoAdresse;
	private Adresse adresseTest;

	@BeforeClass
	public static void initConnexion() {
		MySQLDataSource.creerAcces();
	}

	@Before
	public void setUp() throws SQLException {
		cn = MySQLDataSource.getConnexion();
		cn.setAutoCommit(false);

		this.daoAdresse = new DaoAdresse();
		this.adresseTest = new Adresse(2, "Rue de Test", 31000, "Toulouse");
	}

	@After
	public void tearDown() throws SQLException {
		if (cn != null) {
			cn.rollback();
			MySQLDataSource.deconnecter();
		}
		this.daoAdresse = null;
		this.adresseTest = null;
	}

	@Test
	public void testCreateAndFindById() throws SQLException {
		this.daoAdresse.create(this.adresseTest);
		assertNotNull(this.adresseTest.getId());

		Adresse a2 = this.daoAdresse.findById(this.adresseTest.getId());
		assertNotNull(a2);
		assertEquals(this.adresseTest.getNumero(), a2.getNumero());
		assertEquals(this.adresseTest.getRue(), a2.getRue());
		assertEquals(this.adresseTest.getCodePostal(), a2.getCodePostal());
		assertEquals(this.adresseTest.getVille(), a2.getVille());
	}

	@Test
	public void testUpdate() throws SQLException {
		this.daoAdresse.create(this.adresseTest);

		this.adresseTest.setRue("Nouvelle Rue");
		this.adresseTest.setVille("Paris");
		this.daoAdresse.update(this.adresseTest);

		Adresse a2 = this.daoAdresse.findById(this.adresseTest.getId());
		assertEquals("Nouvelle Rue", a2.getRue());
		assertEquals("Paris", a2.getVille());
	}

	@Test
	public void testDelete() throws SQLException {
		this.daoAdresse.create(this.adresseTest);
		this.daoAdresse.delete(this.adresseTest);

		Adresse a2 = this.daoAdresse.findById(this.adresseTest.getId());
		assertNull(a2);
	}

	@Test
	public void testFindAll() throws SQLException {
		int avant = this.daoAdresse.findAll().size();

		this.daoAdresse.create(this.adresseTest);

		List<Adresse> liste = this.daoAdresse.findAll();
		assertEquals(avant + 1, liste.size());
	}
}
