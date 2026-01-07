package test;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import modele.ZoneVoirie;

public class TestZone {

	private ZoneVoirie zone;

	@Before
	public void setUp() {
		this.zone = new ZoneVoirie(
				"Peripherique",
				"Blanc",
				2.5,
				3.0);
	}

	@Test
	public void testZone() {
		assertEquals("Peripherique", this.zone.getNom());
		assertEquals("Blanc", this.zone.getZone());
		assertEquals(2.5, this.zone.getTarifHoraire(), 0.0001);
		assertEquals(3.0, this.zone.getDureeMax(), 0.0001);
	}

	@Test
	public void testModifierZone() {
		this.zone.setNom("Gare");
		this.zone.setZone("Bleu");
		this.zone.setTarifHoraire(1.8);
		this.zone.setDureeMax(2.0);

		assertEquals("Gare", this.zone.getNom());
		assertEquals("Bleu", this.zone.getZone());
		assertEquals(1.8, this.zone.getTarifHoraire(), 0.0001);
		assertEquals(2.0, this.zone.getDureeMax(), 0.0001);
	}

	@Test
	public void testCouleurEnum() {
		ZoneVoirie zone = new ZoneVoirie("Test", "Jaune", 1.0, 1.0);

		assertEquals("Jaune", zone.getZone());
	}
}
