package test;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import modele.LigneMetro;

public class TestLigneMetro {

    private LigneMetro ligne;

    @Before
    public void setUp() throws Exception {
        ligne = new LigneMetro(1, "Ligne A", "Rouge");
    }

    @After
    public void tearDown() throws Exception {
        ligne = null;
    }

    @Test
    public void testConstructor() {
        assertEquals(1, ligne.getId());
        assertEquals("Ligne A", ligne.getNom());
        assertEquals("Rouge", ligne.getCouleur());
    }

    @Test
    public void testSetters() {
        ligne.setId(2);
        ligne.setNom("Ligne B");
        ligne.setCouleur("Bleu");

        assertEquals(2, ligne.getId());
        assertEquals("Ligne B", ligne.getNom());
        assertEquals("Bleu", ligne.getCouleur());
    }
}
