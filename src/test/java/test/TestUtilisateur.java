package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import modele.Utilisateur;

class TestUtilisateur {

	@Test
	void testAbonnement() {
		Utilisateur utilisateur = new Utilisateur(1, "Wacker", "Luka", "luka@mail.com", "secure123", null);
		utilisateur.setAbonnement("Tisseo");
		assertEquals(utilisateur.getAbonnement(), "Tisseo");
		assertTrue(utilisateur.estAbonne());
	}

}
