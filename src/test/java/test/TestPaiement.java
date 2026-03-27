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

    // Minimal real Utilisateur
    private static class DummyUtilisateur extends Utilisateur {
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
                0,
                2.0,                       // tarif horaire
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

        // Force prix payé > 0 so paiement is valid
        reservation.setPrixPaye(reservation.calculerPrixTotal());

        paiement = new Paiement(reservation, "CB");
    }

    @Test
    public void testMontant() {
        assertEquals(reservation.calculerPrixTotal(), paiement.getMontant(), 0.001);
    }

    @Test
    public void testEffectuerPaiement() {
        assertTrue(paiement.estValide());
        assertTrue(paiement.effectuerPaiement());
    }
}
