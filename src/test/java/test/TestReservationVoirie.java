package test;

import static org.junit.Assert.*;

import java.time.LocalTime;

import org.junit.Before;
import org.junit.Test;

import modele.StationnementVoirie;
import modele.ZoneVoirie;

public class TestReservationVoirie {

    private StationnementVoirie stationnement;
    private ZoneVoirie zone;

    @Before
    public void setUp() {
        zone = new ZoneVoirie(
                1,
                "orange",
                2.0,          // tarif horaire
                300,          // durée max
                LocalTime.of(9, 0),
                LocalTime.of(12, 0),
                LocalTime.of(14, 0),
                LocalTime.of(18, 0)
        );

        stationnement = new StationnementVoirie(
                zone,
                LocalTime.of(9, 0),
                LocalTime.of(19, 0)
        );
    }

    @Test
    public void testHorairesToString() {
        assertEquals("09:00 - 19:00", stationnement.horairesToString());
    }

    @Test
    public void testCouleurZoneToString() {
        assertEquals("Zone orange", stationnement.couleurZoneToString());
    }

    @Test
    public void testTarifToString() {
        assertEquals("2.0€/h", stationnement.tarifToString());
    }

    @Test
    public void testCalculPrixTotal_Normal() {
        // 120 minutes = 2h → 2 * 2€ = 4€
        assertEquals(4.0, stationnement.calculerPrixTotal(120), 0.001);
    }

    @Test
    public void testCalculPrixTotal_MinutesRounding() {
        // 61 minutes → billed as 2 hours → 4€
        assertEquals(4.0, stationnement.calculerPrixTotal(61), 0.001);
    }

    @Test
    public void testCalculPrixTotal_OrangeSpecial_4euros() {
        // 200 minutes → special rule → 4€
        assertEquals(4.0, stationnement.calculerPrixTotal(200), 0.001);
    }

    @Test
    public void testCalculPrixTotal_OrangeSpecial_6euros() {
        // 300 minutes → special rule → 6€
        assertEquals(6.0, stationnement.calculerPrixTotal(300), 0.001);
    }

    @Test
    public void testDureeMaxToString() {
        assertEquals("5.0 heures 0.0 minutes", stationnement.dureeMaxToString());
    }
}
