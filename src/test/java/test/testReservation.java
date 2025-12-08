package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import java.time.LocalDateTime;
import java.time.LocalTime;

import org.junit.Test;

import main.java.modele.Parking;
import main.java.modele.Reservation;

public class testReservation {

	@Test
	public void testGetters() {
		Parking p = new Parking("Parking Capitole", "1 rue du Capitole", 1.5, 150, 1.8, LocalTime.of(9, 0),
				LocalTime.of(21, 0));
		Reservation r = new Reservation("AA-000-AA", p, LocalDateTime.of(2025, 11, 10, 12, 32, 35));

		assertEquals("AA-000-AA", r.getImmatriculation());
		assertEquals(p, r.getParking());
		assertEquals(LocalDateTime.of(2025, 11, 10, 12, 32, 35), r.getDateArrivee());
		assertEquals(null, r.getDateDepart());
		assertFalse(r.estPayee());
	}

	@Test
	public void testSetters() {
		Parking p = new Parking("Parking Capitole", "1 rue du Capitole", 1.5, 150, 1.8, LocalTime.of(9, 0),
				LocalTime.of(21, 0));
		Parking p2 = new Parking("Parking Capitole 2", "2 rue du Capitole", 2, 100, 1.9, LocalTime.of(8, 0),
				LocalTime.of(21, 0));
		Reservation r = new Reservation("AA-000-AA", p, LocalDateTime.of(2025, 11, 10, 12, 32, 35));

		r.setImmatriculation("BB-001-BB");
		r.setParking(p2);
		r.setDateArrivee(LocalDateTime.of(2025, 11, 10, 13, 00, 01));
		r.setDateDepart(LocalDateTime.of(2025, 11, 10, 14, 14, 57));
		r.setEstPayee(true);

		assertEquals("BB-001-BB", r.getImmatriculation());
		assertEquals(p2, r.getParking());
		assertEquals(LocalDateTime.of(2025, 11, 10, 13, 00, 01), r.getDateArrivee());
		assertEquals(LocalDateTime.of(2025, 11, 10, 14, 14, 57), r.getDateDepart());
		assertTrue(r.estPayee());
	}

	@Test
	public void testExceptionsDateDepart() {
		Parking p = new Parking("Parking Capitole", "1 rue du Capitole", 1.5, 150, 1.8, LocalTime.of(9, 0),
				LocalTime.of(21, 0));
		Reservation r = new Reservation("AA-000-AA", p, LocalDateTime.of(2025, 11, 10, 12, 32, 35));

		// Vérification pour dateDepart = null
		IllegalArgumentException e1 = assertThrows(
				IllegalArgumentException.class, () -> r.setDateDepart(null));

		// Vérification pour dateDepart postérieur à dateArrivee
		IllegalArgumentException e2 = assertThrows(
				IllegalArgumentException.class, () -> r.setDateDepart(LocalDateTime.of(2025, 11, 9, 11, 00, 00)));

		assertTrue(e1.getMessage().contains("La date de départ ne doit pas être 'null'"));
		assertTrue(e2.getMessage().contains("La date de départ doit être postérieure à la date d'arrivée"));
	}

	@Test
	public void testConfirmerReservation() {
		Parking p = new Parking("Parking Capitole", "1 rue du Capitole", 1.5, 150, 1.8, LocalTime.of(9, 0),
				LocalTime.of(21, 0));
		Reservation r = new Reservation("AA-000-AA", p, LocalDateTime.of(2025, 11, 10, 12, 32, 35));

		assertEquals(r.toString(), "Réservation confirmée au parking Parking Capitole. "
				+ "Arrivée : 2025-11-10T12:32:35. Prix horaire : 1.5€");
	}

	@Test
	public void testCalculerPrixHoraire() {
		Parking p = new Parking("Parking Capitole", "1 rue du Capitole", 1.5, 150, 1.8, LocalTime.of(9, 0),
				LocalTime.of(21, 0));
		Reservation r = new Reservation("AA-000-AA", p, LocalDateTime.of(2025, 11, 10, 12, 32, 35));

		r.setDateDepart(LocalDateTime.of(2025, 11, 10, 14, 44, 37));
		assertEquals(13.5, r.calculerPrixTotal(), 0.01);

		r.setDateDepart(LocalDateTime.of(2025, 11, 10, 13, 47, 35));
		assertEquals(7.5, r.calculerPrixTotal(), 0.01);

		r.setDateDepart(LocalDateTime.of(2025, 11, 11, 22, 14, 19));
		assertEquals(202.5, r.calculerPrixTotal(), 0.01);
	}

}
