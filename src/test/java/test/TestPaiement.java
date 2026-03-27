package test;

import static org.junit.Assert.*;

import java.time.LocalDateTime;
import java.time.LocalTime;

import org.junit.Before;
import org.junit.Test;

import modele.Adresse;
import modele.Paiement;
import modele.Parking;
import modele.ReservationParking;
import modele.Utilisateur;

public class TestPaiement {

    private static class DummyUtilisateur extends Utilisateur {
        public DummyUtilisateur() {
            super(1, "Nom", "Prenom", "test@test.com", "password", null);
        }
    }

    private Paiement paiement;
    private ReservationParking reservation;
    private Parking parking;

    @Before
    public void setUp() {

        Adresse adresse = new Adresse(1, "Rue Test", 31000, "Toulouse");

        parking = new Parking(
                "Parking Test",
                100,
                0,
                2.0,
                LocalTime.of(8, 0),
                LocalTime.of(20, 0),
                false,
                adresse,
                2.0
        );

        LocalDateTime arrivee = LocalDateTime.now().minusHours(1);
        LocalDateTime depart = LocalDateTime.now();

        reservation = new ReservationParking(
                arrivee,
                depart,
                parking,
                new DummyUtilisateur()
        );

        // Force prix payé > 0
        reservation.setPrixPaye(reservation.calculerPrixTotal());

        paiement = new Paiement(reservation, "CB");
    }

    @Test
    public void testConstructorAndGetters() {
        assertNotNull(paiement.getReservation());
        assertEquals(reservation.calculerPrixTotal(), paiement.getMontant(), 0.001);
        assertNotNull(paiement.getDatePaiement());

    }

    @Test
    public void testEstValide_True() {
        assertTrue(paiement.estValide());
    }

    @Test
    public void testEstValide_False() {
        ReservationParking r2 = new ReservationParking(
                LocalDateTime.now(),
                LocalDateTime.now(),
                parking,
                new DummyUtilisateur()
        );

        r2.setPrixPaye(0);

        Paiement p2 = new Paiement(r2, "CB");

        assertFalse(p2.estValide());
    }

    @Test
    public void testEffectuerPaiement_Success() {
        assertTrue(paiement.effectuerPaiement());
    }

    @Test
    public void testEffectuerPaiement_Failure() {
        ReservationParking r2 = new ReservationParking(
                LocalDateTime.now(),
                LocalDateTime.now(),
                parking,
                new DummyUtilisateur()
        );

        r2.setPrixPaye(0);

        Paiement p2 = new Paiement(r2, "CB");

        assertFalse(p2.effectuerPaiement());
    }
}
