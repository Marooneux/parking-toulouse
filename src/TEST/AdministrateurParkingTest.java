package TEST;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import MODELE.AdministrateurParking;
import MODELE.Parking;

public class AdministrateurParkingTest {

	private AdministrateurParking admin;
	private Parking parking;

	@Before
	public void setUp() {
		this.admin = new AdministrateurParking("Bold", "Bat", "Bold.bat@example.com", "secret");
		this.parking = new Parking("Parking Central", "Rue de Paris", 2.5, 100);
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
		this.admin.modifierInfoParking(this.parking, "Parking Sud", "Avenue Toulouse", 3.0, 80);

		assertEquals("Parking Sud", this.parking.getNom());
		assertEquals("Avenue Toulouse", this.parking.getAdresse());
		assertEquals(3.0, this.parking.getTarif(), 0.001);
		assertEquals(80, this.parking.getNbPlacesDisponibles());
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
