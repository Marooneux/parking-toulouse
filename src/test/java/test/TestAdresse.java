package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import modele.Adresse;

public class TestAdresse {

    private Adresse adresse;

    @Before
    public void setUp() {
        adresse = new Adresse(
                10,                 // numéro
                "Rue du Test",      // rue
                31000,              // code postal
                "Toulouse"          // ville
        );
    }

    @Test
    public void testConstructor() {
        assertEquals(10, adresse.getNumero());
        assertEquals("Rue du Test", adresse.getRue());
        assertEquals(31000, adresse.getCodePostal());
        assertEquals("Toulouse", adresse.getVille());
        assertEquals(0, adresse.getId()); // default value
    }

    @Test
    public void testSetters() {
        adresse.setId(5);
        adresse.setNumero(20);
        adresse.setRue("Avenue Nouvelle");
        adresse.setCodePostal(75000);
        adresse.setVille("Paris");

        assertEquals(5, adresse.getId());
        assertEquals(20, adresse.getNumero());
        assertEquals("Avenue Nouvelle", adresse.getRue());
        assertEquals(75000, adresse.getCodePostal());
        assertEquals("Paris", adresse.getVille());
    }
}
