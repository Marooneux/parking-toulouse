package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.time.LocalTime;

import org.junit.Before;
import org.junit.Test;

import modele.Parking;
import modele.Voiture;

public class ParkingTest {

	private Parking parking;
	private Voiture voiture1;
	private Voiture voiture2;

	@Before
	public void setUp() {
		Parking.setTarif(2.5);
		this.parking = new Parking("Parking Central", "Rue Victor Hugo", 100, 1.8, LocalTime.of(9, 0),
				LocalTime.of(21, 0), true);
		this.voiture1 = new Voiture(150, "AB-123-CD", true, false);
		this.voiture2 = new Voiture(140, "EF-456-GH", false, true);
	}

	@Test
	public void testConstructor() {
		assertEquals("Parking Central", this.parking.getNom());
		assertEquals("Rue Victor Hugo", this.parking.getAdresse());
		assertEquals(2.5, Parking.getTarif(), 0.001);
		assertEquals(100, this.parking.getNbPlacesMax());
		assertEquals(0, this.parking.getNbPlacesOccupees());
		assertEquals(1.8, this.parking.getHauteur(), 0.01);
		assertEquals(LocalTime.of(9, 0), this.parking.getHeureOuverture());
		assertEquals(LocalTime.of(21, 0), this.parking.getHeureFermeture());
	}

	@Test
	public void testSetters() {
		this.parking.setNom("Parking Sud");
		this.parking.setAdresse("Boulevard Carnot");
		Parking.setTarif(3.0);
		this.parking.setNbPlacesMax(80);
		this.parking.setNbPlacesOccupees(10);
		this.parking.setHauteur(2.0);
		this.parking.setHeureOuverture(LocalTime.of(8, 0));
		this.parking.setHeureFermeture(LocalTime.of(22, 0));

		assertEquals("Parking Sud", this.parking.getNom());
		assertEquals("Boulevard Carnot", this.parking.getAdresse());
		assertEquals(3.0, Parking.getTarif(), 0.001);
		assertEquals(80, this.parking.getNbPlacesMax());
		assertEquals(10, this.parking.getNbPlacesOccupees());
		assertEquals(2.0, this.parking.getHauteur(), 0.01);
		assertEquals(LocalTime.of(8, 0), this.parking.getHeureOuverture());
		assertEquals(LocalTime.of(22, 0), this.parking.getHeureFermeture());
	}

	@Test
	public void testAjouterEtEnleverVoiture() {
		this.parking.ajouterVehicule(this.voiture1);
		this.parking.ajouterVehicule(this.voiture2);
		assertEquals(2, this.parking.getNbPlacesOccupees());

		this.parking.enleverVehicule(this.voiture1);
		assertEquals(1, this.parking.getNbPlacesOccupees());
	}

	@Test
	public void testPlacesOccupeesInitiallyZero() {
		assertEquals(0, this.parking.getNbPlacesOccupees());
	}

	@Test
	public void testEstOuvert() {
		assertTrue(this.parking.estOuvert(LocalTime.of(15, 0)));
		assertFalse(this.parking.estOuvert(LocalTime.of(6, 0)));
	}
}
