package test.dao;

import static org.junit.Assert.*;

import org.junit.Test;

import modele.dao.requetes.*;

public class TestRequetesInsert {

    @Test
    public void testRequeteInsertAbonne() {
        RequeteInsertAbonne r = new RequeteInsertAbonne();
        assertNotNull(r.requete());
        assertFalse(r.requete().isEmpty());
    }

    @Test
    public void testRequeteInsertAbonnement() {
        RequeteInsertAbonnement r = new RequeteInsertAbonnement();
        assertNotNull(r.requete());
        assertFalse(r.requete().isEmpty());
    }

    @Test
    public void testRequeteInsertAdminParking() {
        RequeteInsertAdminParking r = new RequeteInsertAdminParking();
        assertNotNull(r.requete());
        assertFalse(r.requete().isEmpty());
    }

    @Test
    public void testRequeteInsertAdresse() {
        RequeteInsertAdresse r = new RequeteInsertAdresse();
        assertNotNull(r.requete());
        assertFalse(r.requete().isEmpty());
    }

    @Test
    public void testRequeteInsertLignesMetro() {
        RequeteInsertLignesMetro r = new RequeteInsertLignesMetro();
        assertNotNull(r.requete());
        assertFalse(r.requete().isEmpty());
    }

    @Test
    public void testRequeteInsertParking() {
        RequeteInsertParking r = new RequeteInsertParking();
        assertNotNull(r.requete());
        assertFalse(r.requete().isEmpty());
    }

    @Test
    public void testRequeteInsertProximite() {
        RequeteInsertProximite r = new RequeteInsertProximite();
        assertNotNull(r.requete());
        assertFalse(r.requete().isEmpty());
    }

    @Test
    public void testRequeteInsertReservationParking() {
        RequeteInsertReservationParking r = new RequeteInsertReservationParking();
        assertNotNull(r.requete());
        assertFalse(r.requete().isEmpty());
    }

    @Test
    public void testRequeteInsertReservationVoirie() {
        RequeteInsertReservationVoirie r = new RequeteInsertReservationVoirie();
        assertNotNull(r.requete());
        assertFalse(r.requete().isEmpty());
    }

    @Test
    public void testRequeteInsertUtilisateur() {
        RequeteInsertUtilisateur r = new RequeteInsertUtilisateur();
        assertNotNull(r.requete());
        assertFalse(r.requete().isEmpty());
    }

    @Test
    public void testRequeteInsertVehicule() {
        RequeteInsertVehicule r = new RequeteInsertVehicule();
        assertNotNull(r.requete());
        assertFalse(r.requete().isEmpty());
    }

    @Test
    public void testRequeteInsertZoneVoirie() {
        RequeteInsertZoneVoirie r = new RequeteInsertZoneVoirie();
        assertNotNull(r.requete());
        assertFalse(r.requete().isEmpty());
    }
}
