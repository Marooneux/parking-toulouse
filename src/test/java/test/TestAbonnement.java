package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import modele.Abonnement;

public class TestAbonnement {

    private Abonnement abonnement;

    @Before
    public void setUp() {
        abonnement = new Abonnement(1, "Mensuel", "Accès illimité pendant 30 jours");
    }

    @Test
    public void testConstructor() {
        assertEquals(1, abonnement.getId());
        assertEquals("Mensuel", abonnement.getNom());
        assertEquals("Accès illimité pendant 30 jours", abonnement.getDescription());
    }

    @Test
    public void testSetters() {
        abonnement.setId(2);
        abonnement.setNom("Annuel");
        abonnement.setDescription("Accès illimité pendant 365 jours");

        assertEquals(2, abonnement.getId());
        assertEquals("Annuel", abonnement.getNom());
        assertEquals("Accès illimité pendant 365 jours", abonnement.getDescription());
    }

    @Test
    public void testEstValide_True() {
        Abonnement a = new Abonnement(3, "Hebdomadaire", "7 jours");
        assertTrue(a.estValide());
    }

    @Test
    public void testEstValide_False_NullNom() {
        Abonnement a = new Abonnement(4, null, "desc");
        assertFalse(a.estValide());
    }

    @Test
    public void testEstValide_False_BlankNom() {
        Abonnement a = new Abonnement(5, "   ", "desc");
        assertFalse(a.estValide());
    }
}
