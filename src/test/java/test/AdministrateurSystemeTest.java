package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.time.LocalTime;

import org.junit.Before;
import org.junit.Test;

import modele.AdministrateurParking;
import modele.AdministrateurSysteme;
import modele.Parking;

public class AdministrateurSystemeTest {

	private AdministrateurSysteme adminSysteme;
	private Parking parking1;
	private Parking parking2;

	@Before
	public void setUp() {
		this.adminSysteme = new AdministrateurSysteme("Martin", "Paul", "paul.martin@example.com", "admin123");
		this.parking1 = new Parking("Parking Nord", "Rue Victor Hugo", 2.0, 50, 1.8, LocalTime.of(9, 0),
				LocalTime.of(21, 0));
		this.parking2 = new Parking("Parking Sud", "Boulevard Carnot", 3.0, 80, 1.75, LocalTime.of(6, 30),
				LocalTime.of(23, 0));
	}

	@Test
	public void testCreerCompteAdminParking() {
		AdministrateurParking adminParking = this.adminSysteme.creerCompteAdminParking("Dupont", "Jean",
				"jean.dupont@example.com", "secret");
		assertNotNull(adminParking);
		assertEquals("Dupont", adminParking.getNom());
		assertEquals("Jean", adminParking.getPrenom());
		assertEquals("jean.dupont@example.com", adminParking.getEmail());
		assertEquals("secret", adminParking.getMdp());
	}

	@Test
	public void testAjouterEtSupprimerParking_noInternalCheck() {
		this.adminSysteme.ajouterParking(this.parking1);
		this.adminSysteme.ajouterParking(this.parking2);
		this.adminSysteme.supprimerParking(this.parking1);
		this.adminSysteme.supprimerParking(this.parking2);
	}
}
