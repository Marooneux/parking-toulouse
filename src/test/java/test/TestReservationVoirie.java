package test;

import static org.junit.Assert.assertEquals;

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
                300,          // durée max en minutes
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
    public void testCalculPrixTotal() {
        // 120 minutes = 2h → 2 * 2€ = 4€
        assertEquals(4.0, stationnement.calculerPrixTotal(120), 0.001);
    }
}
