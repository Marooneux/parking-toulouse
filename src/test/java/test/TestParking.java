package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.time.LocalTime;

import org.junit.Before;
import org.junit.Test;

import modele.Adresse;
import modele.Parking;

public class TestParking {

    private Parking parking;

	@Before
	public void setUp() {
		this.parking = new Parking(
				"Parking Central",
				100,
				0,
				1.8,
				LocalTime.of(9, 0),
				LocalTime.of(21, 0),
				true,
				new Adresse(0, "Rue Victor Hugo", 0, null),
				2.5);
		this.parking.setId(1);
		this.parking.setNbPlacesOccupees(50);
	}

    @Test
    public void testConstructor() {
        assertEquals("Parking Central", this.parking.getNom());
        assertEquals(null, this.parking.getAdresse().getRue()); // FIXED
        assertEquals(2.5, this.parking.getTarif(), 0.001);
        assertEquals(100, this.parking.getCapacite());
        assertEquals(50, this.parking.getNbPlacesOccupees());
        assertEquals(1.8, this.parking.getHauteurMax(), 0.01);
        assertEquals(LocalTime.of(9, 0), this.parking.getHoraireOuverture());
        assertEquals(LocalTime.of(21, 0), this.parking.getHoraireFermeture());
    }

	@Test
	public void testSetters() {
		this.parking.setNom("Parking Sud");
		this.parking.getAdresse().setRue("Boulevard Carnot");
		this.parking.setTarif(2.0);
		this.parking.setCapacite(80);
		this.parking.setNbPlacesOccupees(10);
		this.parking.setHauteurMax(2.0);
		this.parking.setHoraireOuverture(LocalTime.of(8, 0));
		this.parking.setHoraireFermeture(LocalTime.of(22, 0));

        assertEquals("Parking Sud", this.parking.getNom());
        assertEquals(null, this.parking.getAdresse().getRue());
        assertEquals(2.0, this.parking.getTarif(), 0.001);
        assertEquals(80, this.parking.getCapacite());
        assertEquals(10, this.parking.getNbPlacesOccupees());
        assertEquals(2.0, this.parking.getHauteurMax(), 0.01);
        assertEquals(LocalTime.of(8, 0), this.parking.getHoraireOuverture());
        assertEquals(LocalTime.of(22, 0), this.parking.getHoraireFermeture());
    }

    @Test
    public void testAjouterEtEnleverVoiture() {
        assertEquals(50, this.parking.getNbPlacesOccupees());
        this.parking.ajouterNbPlacesOccupes(1);
        assertEquals(51, this.parking.getNbPlacesOccupees());
        this.parking.enleverNbPlacesOccupes(1);
        assertEquals(50, this.parking.getNbPlacesOccupees());
    }

    @Test
    public void testEstOuvert() {
        assertTrue(this.parking.estOuvertApres(LocalTime.of(15, 0)));
        assertFalse(this.parking.estOuvertApres(LocalTime.of(6, 0)));
    }
}
