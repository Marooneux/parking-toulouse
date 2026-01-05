package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import modele.Utilisateur;

public class TestCompte {

	@Test
	public void testNouvelUtilisateur() {
		Utilisateur u = new Utilisateur(1, "Cumbane", "Claudio", "claudio.cumbane@mail.com", "secure123", null);
		assertEquals("Cumbane", u.getNom());
		assertEquals("Claudio", u.getPrenom());
		assertEquals("claudio.cumbane@mail.com", u.getEmail());
		assertEquals("secure123", u.getMdp());
	}

	@Test
	public void testModifierInformations() {
		Utilisateur u = new Utilisateur(1, "A", "B", "a@b.com", "123", null);

		u.setNom("Cumbane");
		u.setPrenom("Claudio");
		u.setEmail("claudio@mail.com");
		assertEquals("Cumbane", u.getNom());
		assertEquals("Claudio", u.getPrenom());
		assertEquals("claudio@mail.com", u.getEmail());
	}

	@Test
	public void testVerifierMdp() {
		Utilisateur u = new Utilisateur(1, "Wacker", "Luka", "luka@mail.com", "secure123", null);
		assertTrue(u.verifierMdp("secure123"));
		assertFalse(u.verifierMdp("secure"));
	}

	@Test
	public void testModifierMdpReussi() {
		Utilisateur u = new Utilisateur(1, "A", "B", "a@b.com", "123", null);
		u.setMdp("123", "secure123");
		assertFalse(u.verifierMdp("123"));
		assertTrue(u.verifierMdp("secure123"));
	}

	@Test
	public void testModifierMdpNonReussi() {
		Utilisateur u = new Utilisateur(1, "A", "B", "a@b.com", "123", null);
		u.setMdp("1234", "secure123");
		assertTrue(u.verifierMdp("123"));
		assertFalse(u.verifierMdp("secure123"));
	}
}
