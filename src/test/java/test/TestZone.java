package test;

import static org.junit.Assert.assertEquals;

import java.time.LocalTime;

import org.junit.Before;
import org.junit.Test;

import modele.ZoneVoirie;

public class TestZone {
	private ZoneVoirie zone;

	@Before
	public void setUp() {
		LocalTime debAm = LocalTime.of(9, 0);
		LocalTime finAm = LocalTime.of(12, 0);
		LocalTime debPm = LocalTime.of(14, 0);
		LocalTime finPm = LocalTime.of(19, 0);

		this.zone = new ZoneVoirie(
				1, 
				"rouge", 
				2.5, 
				180,
				debAm, finAm, debPm, finPm
		);
	}

	@Test
	public void testConstructeur() {
		assertEquals("rouge", this.zone.getCouleur());
		assertEquals(2.5, this.zone.getTarifHoraire(), 0.0001);
		assertEquals(180, this.zone.getDureeMax()); 
		assertEquals(LocalTime.of(9, 0), this.zone.getDebutAm());
		assertEquals(LocalTime.of(19, 0), this.zone.getFinPm());
	}

	@Test
	public void testSetters() {
		this.zone.setCouleur("bleu");
		this.zone.setTarifHoraire(1.8);
		this.zone.setDureeMax(120);
		this.zone.setDebutAm(LocalTime.of(8, 30));

		assertEquals("bleu", this.zone.getCouleur());
		assertEquals(1.8, this.zone.getTarifHoraire(), 0.0001);
		assertEquals(120, this.zone.getDureeMax());
		assertEquals(LocalTime.of(8, 30), this.zone.getDebutAm());
	}
	
	@Test
	public void testFormatageHoraires() {
		String affichage = this.zone.getHorairesAffiches();
		assertEquals(false, affichage.isEmpty());
	}
}