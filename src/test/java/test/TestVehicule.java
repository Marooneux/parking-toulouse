package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import modele.Utilisateur;
import modele.Vehicule;
import modele.Vehicule.TypeVehicule;

public class TestVehicule {

    private Vehicule vehicule;
    private Utilisateur utilisateur;

    @Before
    public void setUp() {
        utilisateur = new Utilisateur(1, "Nom", "Prenom", "mail@test.com", "mdp", null);
        vehicule = new Vehicule(10, "AB-123-CD", TypeVehicule.NORMAL, utilisateur);
    }

    @Test
    public void testConstructorAndGetters() {
        assertEquals(10, vehicule.getId());
        assertEquals("AB-123-CD", vehicule.getImmatriculation());
        assertEquals(TypeVehicule.NORMAL, vehicule.getType());
        assertEquals(utilisateur, vehicule.getUtilisateur());
    }

    @Test
    public void testSetters() {
        Utilisateur newUser = new Utilisateur(2, "New", "User", "new@test.com", "pwd", null);

        vehicule.setId(20);
        vehicule.setImmatriculation("ZZ-999-ZZ");
        vehicule.setType(TypeVehicule.ELECTRIQUE);
        vehicule.setUtilisateur(newUser);

        assertEquals(20, vehicule.getId());
        assertEquals("ZZ-999-ZZ", vehicule.getImmatriculation());
        assertEquals(TypeVehicule.ELECTRIQUE, vehicule.getType());
        assertEquals(newUser, vehicule.getUtilisateur());
    }

    @Test
    public void testImmatriculationValide_True() {
        assertTrue(Vehicule.immatriculationValide("AB-123-CD"));
        assertTrue(Vehicule.immatriculationValide("XY-000-ZZ"));
    }

    @Test
    public void testImmatriculationValide_False() {
        assertFalse(Vehicule.immatriculationValide("A-123-CD"));
        assertFalse(Vehicule.immatriculationValide("AB-12-CD"));
        assertFalse(Vehicule.immatriculationValide("AB123CD"));
        assertFalse(Vehicule.immatriculationValide("ab-123-cd"));
        assertFalse(Vehicule.immatriculationValide("AB-1234-CD"));
        assertFalse(Vehicule.immatriculationValide(""));
    }

    @Test(expected = NullPointerException.class)
    public void testImmatriculationValide_Null() {
        Vehicule.immatriculationValide(null);
    }
}
