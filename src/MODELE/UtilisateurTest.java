package MODELE;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class UtilisateurTest {

	@Test
	public void testConstructeurEtGetters() {
		Utilisateur u = new Utilisateur(1, "Cumbane", "Claudio", "claudio.cumbane@mail.com", "secure123",
				"utilisateur");

		assertEquals(1, u.getId());
		assertEquals("Cumbane", u.getNom());
		assertEquals("Claudio", u.getPrenom());
		assertEquals("claudio.cumbane@mail.com", u.getEmail());
		assertEquals("secure123", u.getMotDePasse());
		assertEquals("utilisateur", u.getRole());
	}

	@Test
	public void testSetters() {
		Utilisateur u = new Utilisateur(1, "A", "B", "a@b.com", "123", "utilisateur");

		u.setNom("Cumbane");
		u.setPrenom("Claudio");
		u.setEmail("claudio@mail.com");
		u.setMotDePasse("secure123");
		u.setRole("admin");

		assertEquals("Cumbane", u.getNom());
		assertEquals("Claudio", u.getPrenom());
		assertEquals("claudio@mail.com", u.getEmail());
		assertEquals("secure123", u.getMotDePasse());
		assertEquals("admin", u.getRole());
	}

	@Test
	public void testLoginSuccess() {
		Utilisateur u = new Utilisateur(1, "Wacker", "Luka", "luka@mail.com", "secure123", "utilisateur");
		assertTrue(u.logIn("luka@mail.com", "secure123"));
	}

	@Test
	public void testLoginFail() {
		Utilisateur u = new Utilisateur(1, "Munkh-Erdene", "Dulguun", "dulguun@mail.com", "secure123", "utilisateur");
		assertFalse(u.logIn("dulguun@mail.com", "wrongpass"));
	}

	@Test
	public void testModifierProfil() {
		Utilisateur u = new Utilisateur(1, "A", "B", "a@b.com", "123", "utilisateur");
		u.modifierProfil("Nadiri", "Noam", "noam@mail.com", "secure123");

		assertEquals("Nadiri", u.getNom());
		assertEquals("Noam", u.getPrenom());
		assertEquals("noam@mail.com", u.getEmail());
		assertEquals("secure123", u.getMotDePasse());
	}

}
