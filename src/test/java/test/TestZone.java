package test;

import static org.junit.Assert.*;

import java.awt.Color;
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

    @Test
    public void testFormatageHorairesGratuit() {
        ZoneVoirie gratuite = new ZoneVoirie(2, "verte", 0.0, 60, null, null, null, null);
        assertEquals("Gratuit", gratuite.getHorairesAffiches());
    }

    @Test
    public void testFormatageHorairesSeulementAm() {
        ZoneVoirie amOnly = new ZoneVoirie(3, "jaune", 1.0, 60,
                LocalTime.of(9, 0), LocalTime.of(12, 0), null, null);
        String affichage = amOnly.getHorairesAffiches();
        assertNotNull(affichage);
        assertFalse(affichage.contains("/"));
    }

    @Test
    public void testMinsToHeuresSansMinutes() {
        // 180 minutes = 3 heures pile
        assertEquals("3 heures", zone.minsToHeures());
    }

    @Test
    public void testMinsToHeuresAvecMinutes() {
        zone.setDureeMax(185);
        assertEquals("3 heures 5 minutes", zone.minsToHeures());
    }

    @Test
    public void testConvertirCouleurRouge() {
        Color c = zone.convertirCouleur();
        assertEquals(new Color(255, 59, 48), c);
    }

    @Test
    public void testConvertirCouleurJaune() {
        zone.setCouleur("jaune");
        assertEquals(new Color(255, 204, 0), zone.convertirCouleur());
    }

    @Test
    public void testConvertirCouleurOrange() {
        zone.setCouleur("orange");
        assertEquals(new Color(255, 149, 0), zone.convertirCouleur());
    }

    @Test
    public void testConvertirCouleurVerte() {
        zone.setCouleur("verte");
        assertEquals(new Color(0, 128, 0), zone.convertirCouleur());
    }

    @Test
    public void testConvertirCouleurBleue() {
        zone.setCouleur("bleue");
        assertEquals(new Color(0, 122, 255), zone.convertirCouleur());
    }

    @Test
    public void testConvertirCouleurHex() {
        zone.setCouleur("#FF0000");
        assertEquals(Color.decode("#FF0000"), zone.convertirCouleur());
    }

    @Test
    public void testConvertirCouleurHexInvalide() {
        zone.setCouleur("#ZZZZZZ");
        assertEquals(Color.GRAY, zone.convertirCouleur());
    }

    @Test
    public void testConvertirCouleurNull() {
        zone.setCouleur(null);
        assertEquals(Color.GRAY, zone.convertirCouleur());
    }

    @Test
    public void testConvertirCouleurInconnue() {
        zone.setCouleur("violet");
        assertEquals(Color.GRAY, zone.convertirCouleur());
    }
}
