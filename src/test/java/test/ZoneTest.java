package test.java.test;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import main.java.modele.Zone;

public class ZoneTest {

	private Zone zone;

	@Before
	public void setUp() {
		this.zone = new Zone(
				"Centre-ville",
				Zone.CouleurZone.ROUGE,
				2.5,
				3.0);
	}

	@Test
	public void testConstructeur() {
		assertEquals("Centre-ville", this.zone.getNom());
		assertEquals(Zone.CouleurZone.ROUGE, this.zone.getCouleur());
		assertEquals(2.5, this.zone.getTarifHoraire(), 0.0001);
		assertEquals(3.0, this.zone.getDureeMax(), 0.0001);
	}

	@Test
	public void testSetters() {
		this.zone.setNom("Gare");
		this.zone.setCouleur(Zone.CouleurZone.BLEU);
		this.zone.setTarifHoraire(1.8);
		this.zone.setDureeMax(2.0);

		assertEquals("Gare", this.zone.getNom());
		assertEquals(Zone.CouleurZone.BLEU, this.zone.getCouleur());
		assertEquals(1.8, this.zone.getTarifHoraire(), 0.0001);
		assertEquals(2.0, this.zone.getDureeMax(), 0.0001);
	}

	@Test
	public void testCouleurEnum() {
		Zone z = new Zone("Test", Zone.CouleurZone.JAUNE, 1.0, 1.0);

		assertEquals(Zone.CouleurZone.JAUNE, z.getCouleur());
	}
}
