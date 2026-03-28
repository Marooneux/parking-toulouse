package test.dao;

import static org.junit.Assert.*;

import org.junit.Test;

import modele.dao.requetes.*;

public class TestRequetesSelect {

    @Test
    public void testRequeteSelectAbonne() {
        RequeteSelectAbonne r = new RequeteSelectAbonne();
        assertNotNull(r.requete());
        assertFalse(r.requete().isEmpty());
    }

    @Test
    public void testRequeteSelectAbonneById() {
        RequeteSelectAbonneById r = new RequeteSelectAbonneById();
        assertNotNull(r.requete());
        assertFalse(r.requete().isEmpty());
    }

    @Test
    public void testRequeteSelectAbonnement() {
        RequeteSelectAbonnement r = new RequeteSelectAbonnement();
        assertNotNull(r.requete());
        assertFalse(r.requete().isEmpty());
    }

    @Test
    public void testRequeteSelectAbonnementById() {
        RequeteSelectAbonnementById r = new RequeteSelectAbonnementById();
        assertNotNull(r.requete());
        assertFalse(r.requete().isEmpty());
    }

    @Test
    public void testRequeteSelectAdresse() {
        RequeteSelectAdresse r = new RequeteSelectAdresse();
        assertNotNull(r.requete());
        assertFalse(r.requete().isEmpty());
    }

    @Test
    public void testRequeteSelectAdresseById() {
        RequeteSelectAdresseById r = new RequeteSelectAdresseById();
        assertNotNull(r.requete());
        assertFalse(r.requete().isEmpty());
    }

    @Test
    public void testRequeteSelectActiveReservationParkingByUserId() {
        RequeteSelectActiveReservationParkingByUserId r = new RequeteSelectActiveReservationParkingByUserId();
        assertNotNull(r.requete());
        assertFalse(r.requete().isEmpty());
    }

    @Test
    public void testRequeteSelectCountReservations() {
        RequeteSelectCountReservations r = new RequeteSelectCountReservations();
        assertNotNull(r.requete());
        assertFalse(r.requete().isEmpty());
    }

    @Test
    public void testRequeteSelectLignesMetro() {
        RequeteSelectLignesMetro r = new RequeteSelectLignesMetro();
        assertNotNull(r.requete());
        assertFalse(r.requete().isEmpty());
    }

    @Test
    public void testRequeteSelectLignesMetroById() {
        RequeteSelectLignesMetroById r = new RequeteSelectLignesMetroById();
        assertNotNull(r.requete());
        assertFalse(r.requete().isEmpty());
    }

    @Test
    public void testRequeteSelectParking() {
        RequeteSelectParking r = new RequeteSelectParking();
        assertNotNull(r.requete());
        assertFalse(r.requete().isEmpty());
    }

    @Test
    public void testRequeteSelectParkingByAdminId() {
        RequeteSelectParkingByAdminId r = new RequeteSelectParkingByAdminId();
        assertNotNull(r.requete());
        assertFalse(r.requete().isEmpty());
    }

    @Test
    public void testRequeteSelectParkingById() {
        RequeteSelectParkingById r = new RequeteSelectParkingById();
        assertNotNull(r.requete());
        assertFalse(r.requete().isEmpty());
    }

    @Test
    public void testRequeteSelectProximite() {
        RequeteSelectProximite r = new RequeteSelectProximite();
        assertNotNull(r.requete());
        assertFalse(r.requete().isEmpty());
    }

    @Test
    public void testRequeteSelectProximiteById() {
        RequeteSelectProximiteById r = new RequeteSelectProximiteById();
        assertNotNull(r.requete());
        assertFalse(r.requete().isEmpty());
    }

    @Test
    public void testRequeteSelectReservationParking() {
        RequeteSelectReservationParking r = new RequeteSelectReservationParking();
        assertNotNull(r.requete());
        assertFalse(r.requete().isEmpty());
    }

    @Test
    public void testRequeteSelectReservationParkingById() {
        RequeteSelectReservationParkingById r = new RequeteSelectReservationParkingById();
        assertNotNull(r.requete());
        assertFalse(r.requete().isEmpty());
    }

    @Test
    public void testRequeteSelectReservationsParkingByUserId() {
        RequeteSelectReservationsParkingByUserId r = new RequeteSelectReservationsParkingByUserId();
        assertNotNull(r.requete());
        assertFalse(r.requete().isEmpty());
    }

    @Test
    public void testRequeteSelectReservationVoirie() {
        RequeteSelectReservationVoirie r = new RequeteSelectReservationVoirie();
        assertNotNull(r.requete());
        assertFalse(r.requete().isEmpty());
    }

    @Test
    public void testRequeteSelectReservationVoirieById() {
        RequeteSelectReservationVoirieById r = new RequeteSelectReservationVoirieById();
        assertNotNull(r.requete());
        assertFalse(r.requete().isEmpty());
    }

    @Test
    public void testRequeteSelectReservationsVoirieByUserId() {
        RequeteSelectReservationsVoirieByUserId r = new RequeteSelectReservationsVoirieByUserId();
        assertNotNull(r.requete());
        assertFalse(r.requete().isEmpty());
    }

    @Test
    public void testRequeteSelectUtilisateur() {
        RequeteSelectUtilisateur r = new RequeteSelectUtilisateur();
        assertNotNull(r.requete());
        assertFalse(r.requete().isEmpty());
    }

    @Test
    public void testRequeteSelectUtilisateurByEmail() {
        RequeteSelectUtilisateurByEmail r = new RequeteSelectUtilisateurByEmail();
        assertNotNull(r.requete());
        assertFalse(r.requete().isEmpty());
    }

    @Test
    public void testRequeteSelectUtilisateurById() {
        RequeteSelectUtilisateurById r = new RequeteSelectUtilisateurById();
        assertNotNull(r.requete());
        assertFalse(r.requete().isEmpty());
    }

    @Test
    public void testRequeteSelectVehiculeById() {
        RequeteSelectVehiculeById r = new RequeteSelectVehiculeById();
        assertNotNull(r.requete());
        assertFalse(r.requete().isEmpty());
    }

    @Test
    public void testRequeteSelectVehicules() {
        RequeteSelectVehicules r = new RequeteSelectVehicules();
        assertNotNull(r.requete());
        assertFalse(r.requete().isEmpty());
    }

    @Test
    public void testRequeteSelectVehiculesByUserId() {
        RequeteSelectVehiculesByUserId r = new RequeteSelectVehiculesByUserId();
        assertNotNull(r.requete());
        assertFalse(r.requete().isEmpty());
    }

    @Test
    public void testRequeteSelectZoneVoirie() {
        RequeteSelectZoneVoirie r = new RequeteSelectZoneVoirie();
        assertNotNull(r.requete());
        assertFalse(r.requete().isEmpty());
    }

    @Test
    public void testRequeteSelectZoneVoirieById() {
        RequeteSelectZoneVoirieById r = new RequeteSelectZoneVoirieById();
        assertNotNull(r.requete());
        assertFalse(r.requete().isEmpty());
    }
}
