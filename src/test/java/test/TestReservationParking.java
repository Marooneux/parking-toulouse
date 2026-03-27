package test;

import static org.junit.Assert.*;

import java.time.LocalDateTime;
import java.time.LocalTime;

import org.junit.Before;
import org.junit.Test;

import modele.Adresse;
import modele.Parking;
import modele.ReservationParking;
import modele.Utilisateur;

public class TestReservationParking {

    private class DummyUtilisateur extends Utilisateur {
        public DummyUtilisateur() {
            super(1, "Nom", "Prenom", "mail@test.com", "pwd", null);
        }
    }

    private ReservationParking reservation;
    private Parking parking;

    @Before
    public void setUp() {
        parking = new Parking(
                "Parking Capitole",
                150,
                0,
                1.8,
                LocalTime.of(9, 0),
                LocalTime.of(21, 0),
                true,
                new Adresse(1, "Rue du Capitole", 31000, "Toulouse"),
                1.5
        );

        reservation = new ReservationParking(
                LocalDateTime.of(2025, 11, 10, 12, 32, 35),
                null,
                parking,
                new DummyUtilisateur()
        );
    }

    @Test
    public void testConstructorAndGetters() {
        assertEquals(LocalDateTime.of(2025, 11, 10, 12, 32, 35), reservation.getDateArrivee());
        assertNull(reservation.getDateDepart());
        assertEquals(parking, reservation.getParking());
        assertNotNull(reservation.getUtilisateur());
    }

    @Test
    public void testSetters() {
        LocalDateTime newArrivee = LocalDateTime.of(2025, 11, 10, 13, 0);
        LocalDateTime newDepart = LocalDateTime.of(2025, 11, 10, 14, 0);

        reservation.setId(42);
        reservation.setDateArrivee(newArrivee);
        reservation.setDateDepart(newDepart);

        Parking newParking = parking;
        reservation.setParking(newParking);

        DummyUtilisateur newUser = new DummyUtilisateur();
        reservation.setUtilisateur(newUser);

        reservation.setPrixPaye(999); // ignored, recalculates internally

        assertEquals(42, reservation.getId());
        assertEquals(newArrivee, reservation.getDateArrivee());
        assertEquals(newDepart, reservation.getDateDepart());
        assertEquals(newParking, reservation.getParking());
        assertEquals(newUser, reservation.getUtilisateur());
        assertEquals(reservation.calculerPrixTotal(), reservation.getPrixPaye(), 0.001);
    }

    @Test
    public void testDateArriveeToString() {
        assertEquals("12:32", reservation.dateArriveeToString());
    }

    @Test
    public void testDateArriveeToString_Null() {
        reservation.setDateArrivee(null);
        assertEquals("", reservation.dateArriveeToString());
    }

    @Test
    public void testPrixCalcul_2Heures() {
        reservation.setDateDepart(reservation.getDateArrivee().plusHours(2));
        double expected = 2 * parking.getTarif();
        assertEquals(expected, reservation.calculerPrixTotal(), 0.001);
    }

    @Test
    public void testPrixCalcul_1Minute() {
        reservation.setDateDepart(reservation.getDateArrivee().plusMinutes(1));
        double expected = 0.25 * parking.getTarif();
        assertEquals(expected, reservation.calculerPrixTotal(), 0.001);
    }

    @Test
    public void testPrixCalcul_16Minutes() {
        reservation.setDateDepart(reservation.getDateArrivee().plusMinutes(16));
        double expected = 0.5 * parking.getTarif();
        assertEquals(expected, reservation.calculerPrixTotal(), 0.001);
    }

    @Test
    public void testPrixCalcul_ZeroMinutes() {
        reservation.setDateDepart(reservation.getDateArrivee());
        double expected = 0.25 * parking.getTarif();
        assertEquals(expected, reservation.calculerPrixTotal(), 0.001);
    }

    @Test
    public void testPrixCalcul_NegativeDuration() {
        reservation.setDateDepart(reservation.getDateArrivee().minusMinutes(10));
        double expected = 0.25 * parking.getTarif();
        assertEquals(expected, reservation.calculerPrixTotal(), 0.001);
    }

    @Test
    public void testPrixCalcul_NoParking() {
        reservation.setParking(null);
        assertEquals(0.0, reservation.calculerPrixTotal(), 0.001);
    }
}
