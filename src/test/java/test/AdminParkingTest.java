package test;
import static org.junit.Assert.*;

import java.time.LocalTime;

import org.junit.Test;


import modele.AdminParking;
import modele.Adresse;
import modele.Parking;
import modele.Utilisateur;
import modele.Utilisateur.Type;

public class AdminParkingTest {

    @Test
    public void testConstructorAndGetters() {
        Utilisateur u = new Utilisateur(1, "Doe", "John", "john@mail.com", "pass", null, Type.CLIENT);
        Parking p = new Parking(
                "Parking Central",
                100,
                0,
                1.8,
                LocalTime.of(9, 0),
                LocalTime.of(21, 0),
                true,
                new Adresse(0, "Rue Victor Hugo", 0, null),
                2.5);
        AdminParking ap = new AdminParking(u, p);

        assertEquals(u, ap.getUtilisateur());
        assertEquals(p, ap.getParking());
    }

    @Test
    public void testSetters() {
        Utilisateur u1 = new Utilisateur(1, "Doe", "John", "john@mail.com", "pass", null, Type.CLIENT);
        Parking p1 = new Parking(
                "Parking Central",
                100,
                0,
                1.8,
                LocalTime.of(9, 0),
                LocalTime.of(21, 0),
                true,
                new Adresse(0, "Rue Victor Hugo", 0, null),
                2.5);

        AdminParking ap = new AdminParking(u1, p1);

        Utilisateur u2 = new Utilisateur(2, "Smith", "Anna", "anna@mail.com", "pass2", null, Type.CLIENT);
        Parking p2 = new Parking(
                "Parking Central",
                100,
                0,
                1.8,
                LocalTime.of(9, 0),
                LocalTime.of(21, 0),
                true,
                new Adresse(0, "Rue Victor Hugo", 0, null),
                2.5);

        ap.setUtilisateur(u2);
        ap.setParking(p2);

        assertEquals(u2, ap.getUtilisateur());
        assertEquals(p2, ap.getParking());
    }
}
