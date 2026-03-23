package test;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import modele.LigneMetro;
import modele.Parking;
import modele.Proximite;

public class TestProximite {

    private Proximite proximite;
    private Parking parking;
    private LigneMetro ligne;

    @Before
    public void setUp() {
        parking = new Parking("Parking Test", 100, 2.0, null, null, false, null, 2.0);
        ligne = new LigneMetro(1, "Ligne A", "Rouge");

        proximite = new Proximite(parking, ligne, 150);
    }

    @Test
    public void testConstructor() {
        assertEquals(parking, proximite.getParking());
        assertEquals(ligne, proximite.getLigneMetro());
        assertEquals(150, proximite.getDistanceMetres());
    }

    @Test
    public void testSetters() {
        Parking p2 = new Parking("Parking B", 50, 1.8, null, null, false, null, 1.5);
        LigneMetro l2 = new LigneMetro(2, "Ligne B", "Bleu");

        proximite.setParking(p2);
        proximite.setLigneMetro(l2);
        proximite.setDistanceMetres(300);

        assertEquals(p2, proximite.getParking());
        assertEquals(l2, proximite.getLigneMetro());
        assertEquals(300, proximite.getDistanceMetres());
    }
}
