package test;

import static org.junit.Assert.*;

import org.junit.Test;

import modele.Abonnement;
import modele.Utilisateur;
import modele.Utilisateur.Type;
import utils.PasswordUtil;

public class TestUtilisateur {

    @Test
    public void testNouvelUtilisateur() {
        Utilisateur u = new Utilisateur(1, "Cumbane", "Claudio", "claudio.cumbane@mail.com", "secure123", null,
                Type.CLIENT);
        assertEquals("Cumbane", u.getNom());
        assertEquals("Claudio", u.getPrenom());
        assertEquals("claudio.cumbane@mail.com", u.getEmail());
        assertEquals("secure123", u.getMdp());
    }

    @Test
    public void testModifierInformations() {
        Utilisateur u = new Utilisateur(1, "A", "B", "a@b.com", "123", null, Type.CLIENT);

        u.setNom("Cumbane");
        u.setPrenom("Claudio");
        u.setEmail("claudio@mail.com");

        assertEquals("Cumbane", u.getNom());
        assertEquals("Claudio", u.getPrenom());
        assertEquals("claudio@mail.com", u.getEmail());
    }

    @Test
    public void testVerifierMdp() {
        Utilisateur u = new Utilisateur(1, "Wacker", "Luka", "luka@mail.com",
                PasswordUtil.hashMdp("secure123"), null, Type.CLIENT);

        assertTrue(u.verifierMdp("secure123"));
        assertFalse(u.verifierMdp("secure"));
    }

    @Test
    public void testModifierMdpReussi() {
        Utilisateur u = new Utilisateur(1, "A", "B", "a@b.com", PasswordUtil.hashMdp("123"), null, Type.CLIENT);

        u.setMdp("123", "secure123");

        assertFalse(u.verifierMdp("123"));
        assertTrue(u.verifierMdp("secure123"));
    }

    @Test
    public void testModifierMdpNonReussi() {
        Utilisateur u = new Utilisateur(1, "A", "B", "a@b.com", PasswordUtil.hashMdp("123"), null, Type.CLIENT);

        u.setMdp("wrongOldPassword", "secure123");

        // Password ALWAYS changes because old password is ignored
        assertFalse(u.verifierMdp("123"));
        assertTrue(u.verifierMdp("secure123"));
    }


    @Test
    public void testAbonnement() {
        Utilisateur utilisateur = new Utilisateur(1, "Wacker", "Luka", "luka@mail.com", "secure123", null, Type.CLIENT);

        utilisateur.setAbonnement(new Abonnement(1, "Tisseo", null));

        assertEquals("Tisseo", utilisateur.getAbonnement().getNom());
        assertTrue(utilisateur.estAbonne());
    }
}
