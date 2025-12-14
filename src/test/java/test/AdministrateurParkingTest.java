package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.time.LocalTime;

import org.junit.Before;
import org.junit.Test;

import modele.AdministrateurParking;
import modele.Parking;

public class AdministrateurParkingTest {

	private AdministrateurParking admin;
	private Parking parking;

	@Before
	public void setUp() {
		this.admin = new AdministrateurParking("Bold", "Bat", "Bold.bat@example.com", "secret");
		Parking.setTarif(2.5);
		this.parking = new Parking("Parking Central", "Rue de Paris", 100, 1.8, LocalTime.of(9, 0),
				LocalTime.of(21, 0), true);
	}

	@Test
	public void testConstructor() {
		assertEquals("Bold", this.admin.getNom());
		assertEquals("Bat", this.admin.getPrenom());
		assertEquals("Bold.bat@example.com", this.admin.getEmail());
		assertEquals("secret", this.admin.getMdp());
	}

	@Test
	public void testModifierInfoParking() {
		this.admin.modifierInfoParking(this.parking, "Parking Sud", "Avenue Toulouse", 3.0, 80, 1.75,
				LocalTime.of(6, 30), LocalTime.of(23, 0));

		assertEquals("Parking Sud", this.parking.getNom());
		assertEquals("Avenue Toulouse", this.parking.getAdresse());
		assertEquals(3.0, Parking.getTarif(), 0.001);
		assertEquals(80, this.parking.getNbPlacesMax());
		assertEquals(1.75, this.parking.getHauteur(), 0.01);
		assertEquals(LocalTime.of(6, 30), this.parking.getHeureOuverture());
		assertEquals(LocalTime.of(23, 0), this.parking.getHeureFermeture());
	}

	@Test
	public void testToString() {
		String result = this.admin.toString();
		assertTrue(result.contains("AdministrateurParking"));
		assertTrue(result.contains("nom =Bold"));
		assertTrue(result.contains("prenom =Bat"));
		assertTrue(result.contains("Bold.bat@example.com"));
	}
}
