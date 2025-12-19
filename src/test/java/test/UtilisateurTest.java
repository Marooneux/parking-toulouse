package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import modele.Utilisateur;

public class UtilisateurTest {

	@Test
	public void testConstructeurEtGetters() {
		Utilisateur u = new Utilisateur(1, "Cumbane", "Claudio", "claudio.cumbane@mail.com", "secure123", null);

		assertEquals("Cumbane", u.getNom());
		assertEquals("Claudio", u.getPrenom());
		assertEquals("claudio.cumbane@mail.com", u.getEmail());
		assertEquals("secure123", u.getMdp());
	}

	@Test
	public void testSetters() {
		Utilisateur u = new Utilisateur(1, "A", "B", "a@b.com", "123", null);

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
		Utilisateur u = new Utilisateur(1, "Wacker", "Luka", "luka@mail.com", "secure123", null);
		assertTrue(u.connectionValide("luka@mail.com", "secure123"));
	}

	@Test
	public void testLoginFail() {
		Utilisateur u = new Utilisateur(1, "Munkh-Erdene", "Dulguun", "dulguun@mail.com", "secure123", null);
		assertFalse(u.connectionValide("dulguun@mail.com", "wrongpass"));
	}

	@Test
	public void testModifierProfil() {
		Utilisateur u = new Utilisateur(1, "A", "B", "a@b.com", "123", null);
		u.setNom("Nadiri");
		u.setPrenom("Noam");
		u.setEmail("noam@mail.com");
		u.setMdp("123", "secure123");

		assertEquals("Nadiri", u.getNom());
		assertEquals("Noam", u.getPrenom());
		assertEquals("noam@mail.com", u.getEmail());
		assertTrue(u.verifierMdp("secure123"));
	}

}
