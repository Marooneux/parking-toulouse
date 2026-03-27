package test;

import static org.junit.Assert.*;

import java.time.LocalTime;

import org.junit.Test;

import modele.StationnementVoirie;
import modele.ZoneVoirie;

public class TestStationnementVoirie {

    private ZoneVoirie makeZone(String couleur, double tarif, int dureeMax) {
        return new ZoneVoirie(
                1,
                couleur,
                tarif,
                dureeMax,
                LocalTime.of(9, 0),   // début matin
                LocalTime.of(12, 0),  // fin matin
                LocalTime.of(14, 0),  // début après-midi
                LocalTime.of(19, 0)   // fin après-midi
        );
    }

    @Test
    public void testCalculerPrixTotal_NormalZone() {
        ZoneVoirie zone = makeZone("verte", 2.0, 120);
        StationnementVoirie s = new StationnementVoirie(zone,
                LocalTime.of(9, 0),
                LocalTime.of(19, 0));

        // 90 minutes = 1h + 30min → 2€ + 2€ = 4€
        double prix = s.calculerPrixTotal(90);
        assertEquals(4.0, prix, 0.0001);
    }

    @Test
    public void testCalculerPrixTotal_OrangeZoneTarifSpecial() {
        ZoneVoirie zone = makeZone("orange", 2.0, 300);
        StationnementVoirie s = new StationnementVoirie(zone,
                LocalTime.of(9, 0),
                LocalTime.of(19, 0));

        // 200 minutes → special rule → 4€
        assertEquals(4.0, s.calculerPrixTotal(200), 0.0001);

        // 300 minutes → special rule → 6€
        assertEquals(6.0, s.calculerPrixTotal(300), 0.0001);
    }

    @Test
    public void testHorairesToString() {
        ZoneVoirie zone = makeZone("verte", 2.0, 120);
        StationnementVoirie s = new StationnementVoirie(zone,
                LocalTime.of(9, 0),
                LocalTime.of(19, 0));

        assertEquals("09:00 - 19:00", s.horairesToString());
    }

    @Test
    public void testCouleurZoneToString() {
        ZoneVoirie zone = makeZone("orange", 2.0, 120);
        StationnementVoirie s = new StationnementVoirie(zone,
                LocalTime.of(9, 0),
                LocalTime.of(19, 0));

        assertEquals("Zone orange", s.couleurZoneToString());
    }

    @Test
    public void testDureeMaxToString() {
        ZoneVoirie zone = makeZone("verte", 2.0, 150); // 2h30
        StationnementVoirie s = new StationnementVoirie(zone,
                LocalTime.of(9, 0),
                LocalTime.of(19, 0));

        assertEquals("2.5 heures 30.0 minutes", s.dureeMaxToString());
    }

    @Test
    public void testTarifToString() {
        ZoneVoirie zone = makeZone("verte", 2.0, 120);
        StationnementVoirie s = new StationnementVoirie(zone,
                LocalTime.of(9, 0),
                LocalTime.of(19, 0));

        assertEquals("2.0€/h", s.tarifToString());
    }

    @Test
    public void testTarifToString_Gratuit() {
        ZoneVoirie zone = makeZone("bleue", 0.0, 120);
        StationnementVoirie s = new StationnementVoirie(zone,
                LocalTime.of(9, 0),
                LocalTime.of(19, 0));

        assertEquals("Gratuit", s.tarifToString());
    }
}
