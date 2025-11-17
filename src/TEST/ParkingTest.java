package TEST;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import MODELE.Parking;
import MODELE.Voiture;

public class ParkingTest {

	private Parking parking;
	private Voiture voiture1;
	private Voiture voiture2;

	@Before
	public void setUp() {
		this.parking = new Parking("Parking Central", "Rue Victor Hugo", 2.5, 100);
		this.voiture1 = new Voiture(150, "AB-123-CD", true, false);
		this.voiture2 = new Voiture(140, "EF-456-GH", false, true);
	}

	@Test
	public void testConstructorInitializesFields() {
		assertEquals("Parking Central", this.parking.getNom());
		assertEquals("Rue Victor Hugo", this.parking.getAdresse());
		assertEquals(2.5, this.parking.getTarifHoraire(), 0.001);
		assertEquals(100, this.parking.getNbPlacesDisponibles());
		assertFalse(this.parking.getOuvert());
	}

	@Test
	public void testSetters() {
		this.parking.setNom("Parking Sud");
		this.parking.setAdresse("Boulevard Carnot");
		this.parking.setTarifHoraire(3.0);
		this.parking.setNbPlacesDisponibles(80);
		this.parking.setOuvert(true);

		assertEquals("Parking Sud", this.parking.getNom());
		assertEquals("Boulevard Carnot", this.parking.getAdresse());
		assertEquals(3.0, this.parking.getTarifHoraire(), 0.001);
		assertEquals(80, this.parking.getNbPlacesDisponibles());
		assertTrue(this.parking.getOuvert());
	}

	@Test
	public void testAjouterEtEnleverVoiture() {
		this.parking.ajouterVoiture(this.voiture1);
		this.parking.ajouterVoiture(this.voiture2);

		assertEquals(this.voiture1, this.parking.getVehicule("AB-123-CD"));
		assertEquals(this.voiture2, this.parking.getVehicule("EF-456-GH"));

		this.parking.enleverVoiture(this.voiture1);
		assertNull(this.parking.getVehicule("AB-123-CD"));
		assertNotNull(this.parking.getVehicule("EF-456-GH"));
	}

	@Test
	public void testGetVehiculeNotFound() {
		assertNull(this.parking.getVehicule("ZZ-999-ZZ"));
	}
}
