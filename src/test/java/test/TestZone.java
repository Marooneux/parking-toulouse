package test;

import static org.junit.Assert.*;

import java.time.LocalTime;

import org.junit.Before;
import org.junit.Test;

import modele.ZoneVoirie;

public class TestZone {

    private ZoneVoirie zone;

    @Before
    public void setUp() {
        zone = new ZoneVoirie(
                1,
                "rouge",
                2.5,
                180,
                LocalTime.of(9, 0),
                LocalTime.of(12, 0),
                LocalTime.of(14, 0),
                LocalTime.of(19, 0)
        );
    }

    @Test
    public void testConstructeur() {
        assertEquals(1, zone.getId());
        assertEquals("rouge", zone.getCouleur());
        assertEquals(2.5, zone.getTarifHoraire(), 0.0001);
        assertEquals(180, zone.getDureeMax());
        assertEquals(LocalTime.of(9, 0), zone.getDebutAm());
        assertEquals(LocalTime.of(12, 0), zone.getFinAm());
        assertEquals(LocalTime.of(14, 0), zone.getDebutPm());
        assertEquals(LocalTime.of(19, 0), zone.getFinPm());
    }

    @Test
    public void testSetters() {
        zone.setId(5);
        zone.setCouleur("bleu");
        zone.setTarifHoraire(1.8);
        zone.setDureeMax(120);
        zone.setDebutAm(LocalTime.of(8, 30));
        zone.setFinAm(LocalTime.of(11, 45));
        zone.setDebutPm(LocalTime.of(13, 15));
        zone.setFinPm(LocalTime.of(18, 0));

        assertEquals(5, zone.getId());
        assertEquals("bleu", zone.getCouleur());
        assertEquals(1.8, zone.getTarifHoraire(), 0.0001);
        assertEquals(120, zone.getDureeMax());
        assertEquals(LocalTime.of(8, 30), zone.getDebutAm());
        assertEquals(LocalTime.of(11, 45), zone.getFinAm());
        assertEquals(LocalTime.of(13, 15), zone.getDebutPm());
        assertEquals(LocalTime.of(18, 0), zone.getFinPm());
    }

    @Test
    public void testFormatageHoraires() {
        String affichage = zone.getHorairesAffiches();
        assertNotNull(affichage);
        assertFalse(affichage.isEmpty());
    }
}
