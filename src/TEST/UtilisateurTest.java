package TEST;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import MODELE.Utilisateur;

public class UtilisateurTest {

	@Test
	public void testConstructeurEtGetters() {
		Utilisateur u = new Utilisateur("Cumbane", "Claudio", "claudio.cumbane@mail.com", "secure123");

		assertEquals("Cumbane", u.getNom());
		assertEquals("Claudio", u.getPrenom());
		assertEquals("claudio.cumbane@mail.com", u.getEmail());
		assertEquals("secure123", u.getMdp());
	}

	@Test
	public void testSetters() {
		Utilisateur u = new Utilisateur("A", "B", "a@b.com", "123");

		u.setNom("Cumbane");
		u.setPrenom("Claudio");
		u.setEmail("claudio@mail.com");
		u.setMdp("123", "secure123");

		assertEquals("Cumbane", u.getNom());
		assertEquals("Claudio", u.getPrenom());
		assertEquals("claudio@mail.com", u.getEmail());
		assertEquals("secure123", u.getMdp());
	}

	@Test
	public void testLoginSuccess() {
		Utilisateur u = new Utilisateur("Wacker", "Luka", "luka@mail.com", "secure123");
		assertTrue(u.connectionValide("luka@mail.com", "secure123"));
	}

	@Test
	public void testLoginFail() {
		Utilisateur u = new Utilisateur("Munkh-Erdene", "Dulguun", "dulguun@mail.com", "secure123");
		assertFalse(u.connectionValide("dulguun@mail.com", "wrongpass"));
	}

	@Test
	public void testModifierProfil() {
		Utilisateur u = new Utilisateur("A", "B", "a@b.com", "123");
		u.modifierProfil("Nadiri", "Noam", "noam@mail.com", "123", "secure123");

		assertEquals("Nadiri", u.getNom());
		assertEquals("Noam", u.getPrenom());
		assertEquals("noam@mail.com", u.getEmail());
		assertEquals("secure123", u.getMdp());
	}

}
