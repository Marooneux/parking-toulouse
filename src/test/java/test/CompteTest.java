package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import modele.Compte;

// Minimal concrete subclass for testing
class TestCompte extends Compte {
	public TestCompte(String nom, String prenom, String email, String mdp) {
		super(nom, prenom, email, mdp);
	}
}

public class CompteTest {

	private TestCompte compte;

	@Before
	public void setUp() {
		this.compte = new TestCompte("Dupont", "Jean", "jean.dupont@example.com", "secret");
	}

	@Test
	public void testConstructorAndGetters() {
		assertEquals("Dupont", this.compte.getNom());
		assertEquals("Jean", this.compte.getPrenom());
		assertEquals("jean.dupont@example.com", this.compte.getEmail());
		assertEquals("secret", this.compte.getMdp());
	}

	@Test
	public void testSetters() {
		this.compte.setNom("Durand");
		this.compte.setPrenom("Paul");
		this.compte.setEmail("paul.durand@example.com");

		assertEquals("Durand", this.compte.getNom());
		assertEquals("Paul", this.compte.getPrenom());
		assertEquals("paul.durand@example.com", this.compte.getEmail());
	}

	@Test
	public void testSetMdpWithWrongOldPassword() {
		this.compte.setMdp("wrongOld", "noveauSecret");
		assertEquals("secret", this.compte.getMdp());
	}
}
