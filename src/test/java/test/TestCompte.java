package test;

import static org.junit.Assert.*;
import org.junit.Test;

import modele.Compte;

public class TestCompte {

    // Simple concrete class because Compte is abstract
    private static class FakeCompte extends Compte {
        public FakeCompte(String nom, String prenom, String email, String mdp) {
            super(nom, prenom, email, mdp);
        }
    }

    @Test
    public void testConstructorAndGetters() {
        Compte c = new FakeCompte("Doe", "John", "john@mail.com", "secret");

        assertEquals("Doe", c.getNom());
        assertEquals("John", c.getPrenom());
        assertEquals("john@mail.com", c.getEmail());
        assertEquals("secret", c.getMdp());
    }

    @Test
    public void testSetters() {
        Compte c = new FakeCompte("Doe", "John", "john@mail.com", "secret");

        c.setNom("Smith");
        c.setPrenom("Anna");
        c.setEmail("anna@mail.com");

        assertEquals("Smith", c.getNom());
        assertEquals("Anna", c.getPrenom());
        assertEquals("anna@mail.com", c.getEmail());
    }


    @Test
    public void testSetMdp() {
        Compte c = new FakeCompte("Doe", "John", "john@mail.com", "oldpass");

        // wrong old password → should NOT change
        c.setMdp("wrong", "newpass");
        assertEquals("oldpass", c.getMdp());

        // correct old password → should change
        c.setMdp("oldpass", "newpass");
        assertEquals("newpass", c.getMdp());
    }
}
