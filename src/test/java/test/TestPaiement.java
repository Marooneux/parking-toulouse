package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.time.LocalDateTime;
import java.time.LocalTime;

import org.junit.Before;
import org.junit.Test;

import modele.Adresse;
import modele.Paiement;
import modele.Parking;
import modele.ReservationParking;

public class TestPaiement {

    // Minimal dummy Utilisateur so ReservationParking compiles
    private class DummyUtilisateur extends modele.Utilisateur {
        public DummyUtilisateur() {
            super(1, "Nom", "Prenom", "test@test.com", "password", null);
        }
    }

    private Paiement paiement;
    private ReservationParking reservation;

    @Before
    public void setUp() {

        Adresse adresse = new Adresse(1, "Rue Test", 31000, "Toulouse");

        Parking parking = new Parking(
                "Parking Test",
                100,
                2.0,
                LocalTime.of(8, 0),
                LocalTime.of(20, 0),
                false,
                adresse,
                2.0 // tarif = 2€/h
        );

        LocalDateTime arrivee = LocalDateTime.now().minusHours(1);
        LocalDateTime depart = LocalDateTime.now();

        reservation = new ReservationParking(
                arrivee,
                depart,
                parking,
                new DummyUtilisateur() // NOT the real Utilisateur class
        );

        reservation.setDateDepart(depart); // forces prixPaye calculation

        paiement = new Paiement(reservation, "CB");
    }

    @Test
    public void testMontant() {
        assertEquals(reservation.calculerPrixTotal(), paiement.getMontant(), 0.001);
    }

    @Test
    public void testEffectuerPaiement() {
        assertTrue(paiement.effectuerPaiement());
    }
}
