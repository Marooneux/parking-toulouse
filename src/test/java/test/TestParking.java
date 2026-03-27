package test;

import static org.junit.Assert.*;

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
                2.5
        );
        this.parking.setId(1);
        this.parking.setNbPlacesOccupees(50);
    }

    @Test
    public void testConstructor() {
        assertEquals("Parking Central", parking.getNom());
        assertEquals("Rue Victor Hugo", parking.getAdresse().getRue());
        assertEquals(2.5, parking.getTarif(), 0.001);
        assertEquals(100, parking.getCapacite());
        assertEquals(50, parking.getNbPlacesOccupees());
        assertEquals(1.8, parking.getHauteurMax(), 0.01);
        assertEquals(LocalTime.of(9, 0), parking.getHoraireOuverture());
        assertEquals(LocalTime.of(21, 0), parking.getHoraireFermeture());
    }

    @Test
    public void testSetters() {
        parking.setNom("Parking Sud");
        parking.getAdresse().setRue("Boulevard Carnot");
        parking.setTarif(2.0);
        parking.setCapacite(80);
        parking.setNbPlacesOccupees(10);
        parking.setHauteurMax(2.0);
        parking.setHoraireOuverture(LocalTime.of(8, 0));
        parking.setHoraireFermeture(LocalTime.of(22, 0));

        assertEquals("Parking Sud", parking.getNom());
        assertEquals("Boulevard Carnot", parking.getAdresse().getRue());
        assertEquals(2.0, parking.getTarif(), 0.001);
        assertEquals(80, parking.getCapacite());
        assertEquals(10, parking.getNbPlacesOccupees());
        assertEquals(2.0, parking.getHauteurMax(), 0.01);
        assertEquals(LocalTime.of(8, 0), parking.getHoraireOuverture());
        assertEquals(LocalTime.of(22, 0), parking.getHoraireFermeture());
    }

    @Test
    public void testAdresseStringSetter() {
        parking.setAdresse("Some Street");

        assertNotNull(parking.getAdresse());
        assertEquals(0, parking.getAdresse().getId());
        assertNull(parking.getAdresse().getRue());
        assertEquals(0, parking.getAdresse().getCodePostal());
        assertNull(parking.getAdresse().getVille());
    }

    @Test
    public void testHeureOuvertureEtFermetureAliases() {
        assertEquals(parking.getHoraireOuverture(), parking.getHeureOuverture());
        assertEquals(parking.getHoraireFermeture(), parking.getHeureFermeture());
    }

    @Test
    public void testAjouterEtEnleverVoiture() {
        assertEquals(50, parking.getNbPlacesOccupees());
        parking.ajouterNbPlacesOccupes(1);
        assertEquals(51, parking.getNbPlacesOccupees());
        parking.enleverNbPlacesOccupes(1);
        assertEquals(50, parking.getNbPlacesOccupees());
    }

    @Test
    public void testOccupancyBoundaries() {
        parking.setNbPlacesOccupees(0);
        parking.enleverNbPlacesOccupes(1); // should not go negative
        assertEquals(0, parking.getNbPlacesOccupees());

        parking.setNbPlacesOccupees(100);
        parking.ajouterNbPlacesOccupes(1); // should not exceed capacity
        assertEquals(100, parking.getNbPlacesOccupees());
    }

    @Test
    public void testEstOuvert() {
        assertTrue(parking.estOuvertApres(LocalTime.of(15, 0)));
        assertFalse(parking.estOuvertApres(LocalTime.of(6, 0)));
    }
}
