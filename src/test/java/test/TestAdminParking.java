package test;

import static org.junit.Assert.*;

import java.time.LocalTime;

import org.junit.Before;
import org.junit.Test;

import modele.Adresse;
import modele.AdminParking;
import modele.Parking;
import modele.Utilisateur;
import modele.Utilisateur.Type;

public class TestAdminParking {

    private Utilisateur utilisateur;
    private Parking parking;
    private AdminParking adminParking;

    @Before
    public void setUp() {
        utilisateur = new Utilisateur(
                1,
                "Dupont",
                "Jean",
                "admin@parking.com",
                "password123",
                Type.PARKINGADMIN
        );

        parking = new Parking(
                "Parking Central",
                50,
                10,
                1.8,
                LocalTime.of(8, 0),
                LocalTime.of(22, 0),
                true,
                new Adresse(1, "123 Rue de la Paix", 75000, null),
                9.50
        );
        parking.setId(1);

        adminParking = new AdminParking(utilisateur, parking);
    }

    @Test
    public void testConstructor() {
        assertNotNull(adminParking);
        assertEquals(utilisateur, adminParking.getUtilisateur());
        assertEquals(parking, adminParking.getParking());
    }

    @Test
    public void testGetUtilisateur() {
        Utilisateur u = adminParking.getUtilisateur();
        assertNotNull(u);
        assertEquals("Dupont", u.getNom());
        assertEquals("Jean", u.getPrenom());
        assertEquals("admin@parking.com", u.getEmail());
        assertEquals(Type.PARKINGADMIN, u.getType());
    }

    @Test
    public void testGetParking() {
        Parking p = adminParking.getParking();
        assertNotNull(p);
        assertEquals("Parking Central", p.getNom());
        assertEquals("123 Rue de la Paix", p.getAdresse().getRue());
        assertEquals(50, p.getCapacite());
        assertEquals(9.50, p.getTarif(), 0.01);
    }

    @Test
    public void testSetUtilisateur() {
        Utilisateur newUser = new Utilisateur(
                2,
                "Martin",
                "Pierre",
                "newadmin@parking.com",
                "newpassword",
                Type.PARKINGADMIN
        );
        
        adminParking.setUtilisateur(newUser);
        
        assertEquals(newUser, adminParking.getUtilisateur());
        assertEquals("Martin", adminParking.getUtilisateur().getNom());
        assertEquals("Pierre", adminParking.getUtilisateur().getPrenom());
    }

    @Test
    public void testSetParking() {
        Parking newParking = new Parking(
                "Parking Secundaire",
                40,
                8,
                1.8,
                LocalTime.of(8, 0),
                LocalTime.of(22, 0),
                true,
                new Adresse(2, "456 Avenue de la Liberté", 75001, null),
                8.50
        );
        newParking.setId(2);
        
        adminParking.setParking(newParking);
        
        assertEquals(newParking, adminParking.getParking());
        assertEquals("Parking Secundaire", adminParking.getParking().getNom());
        assertEquals(40, adminParking.getParking().getCapacite());
    }

    @Test
    public void testSetUtilisateurWithNull() {
        adminParking.setUtilisateur(null);
        assertNull(adminParking.getUtilisateur());
    }

    @Test
    public void testSetParkingWithNull() {
        adminParking.setParking(null);
        assertNull(adminParking.getParking());
    }

    @Test
    public void testMultipleSetOperations() {
        Utilisateur user1 = new Utilisateur(3, "Durand", "Marie", "marie@test.com", "pass", Type.PARKINGADMIN);
        Utilisateur user2 = new Utilisateur(4, "Moreau", "Lucas", "lucas@test.com", "pass", Type.PARKINGADMIN);
        
        adminParking.setUtilisateur(user1);
        assertEquals(user1, adminParking.getUtilisateur());
        
        adminParking.setUtilisateur(user2);
        assertEquals(user2, adminParking.getUtilisateur());
        assertEquals("Moreau", adminParking.getUtilisateur().getNom());
    }
}
