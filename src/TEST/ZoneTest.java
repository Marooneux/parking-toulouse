package TEST;

import MODELE.Zone;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class ZoneTest {

    private Zone zone;

    @Before
    public void setUp() {
        zone = new Zone(
                "Centre-ville",
                Zone.CouleurZone.ROUGE,
                2.5,
                3.0
        );
    }

    @Test
    public void testConstructeur() {
        assertEquals("Centre-ville", zone.getNom());
        assertEquals(Zone.CouleurZone.ROUGE, zone.getCouleur());
        assertEquals(2.5, zone.getTarifHoraire(), 0.0001);
        assertEquals(3.0, zone.getDureeMax(), 0.0001);
    }

    @Test
    public void testSetters() {
        zone.setNom("Gare");
        zone.setCouleur(Zone.CouleurZone.BLEU);
        zone.setTarifHoraire(1.8);
        zone.setDureeMax(2.0);

        assertEquals("Gare", zone.getNom());
        assertEquals(Zone.CouleurZone.BLEU, zone.getCouleur());
        assertEquals(1.8, zone.getTarifHoraire(), 0.0001);
        assertEquals(2.0, zone.getDureeMax(), 0.0001);
    }

    @Test
    public void testCouleurEnum() {
        Zone z = new Zone("Test", Zone.CouleurZone.JAUNE, 1.0, 1.0);

        assertEquals(Zone.CouleurZone.JAUNE, z.getCouleur());
    }
}
