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
				1,
				"rouge",
				2.5,
				3.0);
	}

	@Test
	public void testConstructeur() {
		assertEquals("rouge", this.zone.getNom());
		assertEquals(2.5, this.zone.getTarifHoraire(), 0.0001);
		assertEquals(3.0, this.zone.getDureeMax(), 0.0001);
	}

	@Test
	public void testSetters() {
		this.zone.setNom("bleu");
		this.zone.setTarifHoraire(1.8);
		this.zone.setDureeMax(2.0);

		assertEquals("bleu", this.zone.getNom());
		assertEquals(1.8, this.zone.getTarifHoraire(), 0.0001);
		assertEquals(2.0, this.zone.getDureeMax(), 0.0001);
	}
}
