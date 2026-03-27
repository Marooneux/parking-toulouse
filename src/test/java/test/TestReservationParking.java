package test;

import static org.junit.Assert.assertEquals;

import java.time.LocalDateTime;
import java.time.LocalTime;

import org.junit.Before;
import org.junit.Test;

import modele.Adresse;
import modele.Parking;
import modele.ReservationParking;

public class TestReservationParking {

    private class DummyUtilisateur extends modele.Utilisateur {
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
    public void testGetters() {
        assertEquals(LocalDateTime.of(2025, 11, 10, 12, 32, 35), reservation.getDateArrivee());
        assertEquals(null, reservation.getDateDepart());
        assertEquals(parking, reservation.getParking());
    }

    @Test
    public void testSetters() {
        LocalDateTime newArrivee = LocalDateTime.of(2025, 11, 10, 13, 0);
        LocalDateTime newDepart = LocalDateTime.of(2025, 11, 10, 14, 0);

        reservation.setDateArrivee(newArrivee);
        reservation.setDateDepart(newDepart);

        assertEquals(newArrivee, reservation.getDateArrivee());
        assertEquals(newDepart, reservation.getDateDepart());
    }

    @Test
    public void testPrixCalcul() {
        reservation.setDateDepart(reservation.getDateArrivee().plusHours(2));
        double expected = 2 * parking.getTarif();
        assertEquals(expected, reservation.calculerPrixTotal(), 0.001);
    }
}
