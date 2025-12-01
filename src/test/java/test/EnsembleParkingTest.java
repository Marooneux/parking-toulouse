package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.time.LocalTime;

import org.junit.Before;
import org.junit.Test;

import modele.EnsembleParking;
import modele.Parking;

public class EnsembleParkingTest {

	private EnsembleParking ensemble;
	private Parking parking1;
	private Parking parking2;

	@Before
	public void setUp() {
		this.ensemble = new EnsembleParking();
		this.parking1 = new Parking("Parking Nord", "Rue Victor Hugo", 2.0, 50, 1.8, LocalTime.of(9, 0), LocalTime.of(21, 0));
		this.parking2 = new Parking("Parking Sud", "Boulevard Carnot", 3.0, 80, 1.9, LocalTime.of(8, 0), LocalTime.of(21, 0));
	}

	@Test
	public void testAjouterParking() {
		this.ensemble.ajouterParking(this.parking1);
		this.ensemble.ajouterParking(this.parking2);

		assertTrue(this.ensemble.getParkings().contains(this.parking1));
		assertTrue(this.ensemble.getParkings().contains(this.parking2));
		assertEquals(2, this.ensemble.getParkings().size());
	}

	@Test
	public void testRetirerParking() {
		this.ensemble.ajouterParking(this.parking1);
		this.ensemble.ajouterParking(this.parking2);

		this.ensemble.retirerParking(this.parking1);

		assertFalse(this.ensemble.getParkings().contains(this.parking1));
		assertTrue(this.ensemble.getParkings().contains(this.parking2));
		assertEquals(1, this.ensemble.getParkings().size());
	}

	@Test
	public void testGetParkingsInitiallyEmpty() {
		assertTrue(this.ensemble.getParkings().isEmpty());
	}
}
