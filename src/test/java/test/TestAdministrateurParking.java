package test;

import static org.junit.Assert.assertEquals;

import java.time.LocalTime;

import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import modele.AdministrateurParking;
import modele.Parking;

public class TestAdministrateurParking {

	private AdministrateurParking admin;
	private Parking parking;

	@BeforeEach
	public void setUp() {
		this.admin = new AdministrateurParking(1, "Bold", "Bat", "Bold.bat@example.com", "secret");
		this.parking = new Parking(1, "Parking Central", "Rue de Paris", 100, 1.8, LocalTime.of(9, 0),
				LocalTime.of(21, 0), true, 1.5);
	}

	@AfterEach
	void tearDown() throws Exception {
		this.admin = null;
		this.parking = null;
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
		assertEquals(3.0, this.parking.getTarif(), 0.001);
		assertEquals(80, this.parking.getNbPlacesMax());
		assertEquals(1.75, this.parking.getHauteur(), 0.01);
		assertEquals(LocalTime.of(6, 30), this.parking.getHeureOuverture());
		assertEquals(LocalTime.of(23, 0), this.parking.getHeureFermeture());
	}
}
