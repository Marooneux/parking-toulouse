package test.dao;

import static org.junit.Assert.*;

import org.junit.Test;

import modele.dao.requetes.*;

public class TestRequetesDelete {

    @Test
    public void testRequeteDeleteAbonne() {
        RequeteDeleteAbonne r = new RequeteDeleteAbonne();
        assertNotNull(r.requete());
        assertFalse(r.requete().isEmpty());
    }

    @Test
    public void testRequeteDeleteAbonnement() {
        RequeteDeleteAbonnement r = new RequeteDeleteAbonnement();
        assertNotNull(r.requete());
        assertFalse(r.requete().isEmpty());
    }

    @Test
    public void testRequeteDeleteAdresse() {
        RequeteDeleteAdresse r = new RequeteDeleteAdresse();
        assertNotNull(r.requete());
        assertFalse(r.requete().isEmpty());
    }

    @Test
    public void testRequeteDeleteLignesMetro() {
        RequeteDeleteLignesMetro r = new RequeteDeleteLignesMetro();
        assertNotNull(r.requete());
        assertFalse(r.requete().isEmpty());
    }

    @Test
    public void testRequeteDeleteParking() {
        RequeteDeleteParking r = new RequeteDeleteParking();
        assertNotNull(r.requete());
        assertFalse(r.requete().isEmpty());
    }

    @Test
    public void testRequeteDeleteProximite() {
        RequeteDeleteProximite r = new RequeteDeleteProximite();
        assertNotNull(r.requete());
        assertFalse(r.requete().isEmpty());
    }

    @Test
    public void testRequeteDeleteReservationParking() {
        RequeteDeleteReservationParking r = new RequeteDeleteReservationParking();
        assertNotNull(r.requete());
        assertFalse(r.requete().isEmpty());
    }

    @Test
    public void testRequeteDeleteReservationVoirie() {
        RequeteDeleteReservationVoirie r = new RequeteDeleteReservationVoirie();
        assertNotNull(r.requete());
        assertFalse(r.requete().isEmpty());
    }

    @Test
    public void testRequeteDeleteUtilisateur() {
        RequeteDeleteUtilisateur r = new RequeteDeleteUtilisateur();
        assertNotNull(r.requete());
        assertFalse(r.requete().isEmpty());
    }

    @Test
    public void testRequeteDeleteZoneVoirie() {
        RequeteDeleteZoneVoirie r = new RequeteDeleteZoneVoirie();
        assertNotNull(r.requete());
        assertFalse(r.requete().isEmpty());
    }

    @Test
    public void testRequeteDeleteVehicule() {
        RequeteDeleteVehicule r = new RequeteDeleteVehicule();
        assertNotNull(r.requete());
        assertFalse(r.requete().isEmpty());
    }
}
