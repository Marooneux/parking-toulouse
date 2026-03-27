package test;

import static org.junit.Assert.*;

import java.time.LocalDateTime;

import org.junit.Before;
import org.junit.Test;

import modele.ReservationVoirie;
import modele.ZoneVoirie;
import modele.Utilisateur;

public class TestReservationVoirie {

    private ReservationVoirie reservation;
    private ZoneVoirie zone;

    // Minimal dummy user so the constructor compiles
    private class DummyUtilisateur extends Utilisateur {
        public DummyUtilisateur() {
            super(1, "Nom", "Prenom", "mail@test.com", "pwd", null);
        }
    }

    @Before
    public void setUp() {

        zone = new ZoneVoirie(
                1,
                "orange",
                2.0,
                300,
                null, null, null, null
        );

        reservation = new ReservationVoirie(
                10,
                LocalDateTime.of(2025, 11, 10, 12, 0),
                120,
                zone,
                new DummyUtilisateur()
        );
    }

    @Test
    public void testConstructor() {
        assertEquals(10, reservation.getId());
        assertEquals(LocalDateTime.of(2025, 11, 10, 12, 0), reservation.getDateDebut());
        assertEquals(120, reservation.getDureeMinutes());
        assertEquals(zone, reservation.getZone());
        assertNotNull(reservation.getUtilisateur());
    }

    @Test
    public void testSetters() {
        reservation.setId(20);
        reservation.setDateDebut(LocalDateTime.of(2025, 11, 10, 14, 0));
        reservation.setDureeMinutes(200);

        ZoneVoirie newZone = new ZoneVoirie(
                2, "verte", 1.0, 120,
                null, null, null, null
        );
        reservation.setZone(newZone);

        DummyUtilisateur newUser = new DummyUtilisateur();
        reservation.setUtilisateur(newUser);

        assertEquals(20, reservation.getId());
        assertEquals(LocalDateTime.of(2025, 11, 10, 14, 0), reservation.getDateDebut());
        assertEquals(200, reservation.getDureeMinutes());
        assertEquals(newZone, reservation.getZone());
        assertEquals(newUser, reservation.getUtilisateur());
    }
}
