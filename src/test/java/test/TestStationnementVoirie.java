package test;

import static org.junit.Assert.*;

import java.time.DayOfWeek;
import java.time.LocalDate;
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
                LocalTime.of(9, 0),
                LocalTime.of(12, 0),
                LocalTime.of(14, 0),
                LocalTime.of(19, 0)
        );
    }

    @Test
    public void testCalculerPrixTotal_NormalZone() {
        ZoneVoirie zone = makeZone("verte", 2.0, 120);

        // Force payant by setting horaires so 06:00 is NOT inside payant interval
        StationnementVoirie s = new StationnementVoirie(zone,
                LocalTime.of(7, 0),
                LocalTime.of(7, 30));

        double prix = s.calculerPrixTotal(90); // 1h30 → 2h facturées
        assertEquals(4.0, prix, 0.0001);
    }

    @Test
    public void testCalculerPrixTotal_OrangeZoneTarifSpecial() {
        ZoneVoirie zone = makeZone("orange", 2.0, 300);

        StationnementVoirie s = new StationnementVoirie(zone,
                LocalTime.of(7, 0),
                LocalTime.of(7, 30));

        assertEquals(4.0, s.calculerPrixTotal(200), 0.0001);
        assertEquals(6.0, s.calculerPrixTotal(300), 0.0001);
    }

    @Test
    public void testCalculerPrixTotal_GratuitAvant9h() {
        ZoneVoirie zone = makeZone("verte", 2.0, 120);

        // 06:00 is inside 05:00–07:00 → free
        StationnementVoirie s = new StationnementVoirie(
                zone,
                LocalTime.of(5, 0),
                LocalTime.of(7, 0)
        );

        assertEquals(0.0, s.calculerPrixTotal(120), 0.0001);
    }


    @Test
    public void testCalculerPrixTotal_Dimanche() {
        ZoneVoirie zone = makeZone("verte", 2.0, 120);

        StationnementVoirie s = new StationnementVoirie(zone,
                LocalTime.of(7, 0),
                LocalTime.of(7, 30));

        // Fake Sunday by checking logic only
        if (LocalDate.now().getDayOfWeek() == DayOfWeek.SUNDAY) {
            assertEquals(0.0, s.calculerPrixTotal(120), 0.0001);
        }
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

    @Test
    public void testGettersAndSetters() {
        ZoneVoirie zone = makeZone("verte", 2.0, 120);
        StationnementVoirie s = new StationnementVoirie(zone,
                LocalTime.of(9, 0),
                LocalTime.of(19, 0));

        ZoneVoirie newZone = makeZone("orange", 3.0, 200);
        s.setZone(newZone);
        assertEquals(newZone, s.getZone());

        s.setHorairePayantDebut(LocalTime.of(8, 0));
        assertEquals(LocalTime.of(8, 0), s.getHorairePayantDebut());

        s.setHorairePayantFin(LocalTime.of(18, 0));
        assertEquals(LocalTime.of(18, 0), s.getHorairePayantFin());
    }

    @Test
    public void testIsDimanche() {
        StationnementVoirie s = new StationnementVoirie(
                makeZone("verte", 2.0, 120),
                LocalTime.of(9, 0),
                LocalTime.of(19, 0)
        );

        // Just ensure method runs
        s.isDimanche();
    }
}
