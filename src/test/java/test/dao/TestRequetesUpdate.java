package test.dao;

import static org.junit.Assert.*;

import org.junit.Test;

import modele.dao.requetes.*;

public class TestRequetesUpdate {

    @Test
    public void testRequeteUpdateAbonne() {
        RequeteUpdateAbonne r = new RequeteUpdateAbonne();
        assertNotNull(r.requete());
        assertFalse(r.requete().isEmpty());
    }

    @Test
    public void testRequeteUpdateAbonnement() {
        RequeteUpdateAbonnement r = new RequeteUpdateAbonnement();
        assertNotNull(r.requete());
        assertFalse(r.requete().isEmpty());
    }

    @Test
    public void testRequeteUpdateAdresse() {
        RequeteUpdateAdresse r = new RequeteUpdateAdresse();
        assertNotNull(r.requete());
        assertFalse(r.requete().isEmpty());
    }

    @Test
    public void testRequeteUpdateLignesMetro() {
        RequeteUpdateLignesMetro r = new RequeteUpdateLignesMetro();
        assertNotNull(r.requete());
        assertFalse(r.requete().isEmpty());
    }

    @Test
    public void testRequeteUpdateParking() {
        RequeteUpdateParking r = new RequeteUpdateParking();
        assertNotNull(r.requete());
        assertFalse(r.requete().isEmpty());
    }

    @Test
    public void testRequeteUpdateProximite() {
        RequeteUpdateProximite r = new RequeteUpdateProximite();
        assertNotNull(r.requete());
        assertFalse(r.requete().isEmpty());
    }

    @Test
    public void testRequeteUpdateReservationParking() {
        RequeteUpdateReservationParking r = new RequeteUpdateReservationParking();
        assertNotNull(r.requete());
        assertFalse(r.requete().isEmpty());
    }

    @Test
    public void testRequeteUpdateReservationVoirie() {
        RequeteUpdateReservationVoirie r = new RequeteUpdateReservationVoirie();
        assertNotNull(r.requete());
        assertFalse(r.requete().isEmpty());
    }

    @Test
    public void testRequeteUpdateUtilisateur() {
        RequeteUpdateUtilisateur r = new RequeteUpdateUtilisateur();
        assertNotNull(r.requete());
        assertFalse(r.requete().isEmpty());
    }

    @Test
    public void testRequeteUpdateVehicule() {
        RequeteUpdateVehicule r = new RequeteUpdateVehicule();
        assertNotNull(r.requete());
        assertFalse(r.requete().isEmpty());
    }

    @Test
    public void testRequeteUpdateZoneVoirie() {
        RequeteUpdateZoneVoirie r = new RequeteUpdateZoneVoirie();
        assertNotNull(r.requete());
        assertFalse(r.requete().isEmpty());
    }

    @Test
    public void testRequeteUnsetVehiculesDefautByUserId() {
        RequeteUnsetVehiculesDefautByUserId r = new RequeteUnsetVehiculesDefautByUserId();
        assertNotNull(r.requete());
        assertFalse(r.requete().isEmpty());
    }
}
