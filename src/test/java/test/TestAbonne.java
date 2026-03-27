package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import modele.Abonne;
import modele.Abonnement;
import modele.Utilisateur;

public class TestAbonne {

    private Utilisateur utilisateur;
    private Abonnement abonnement;
    private Abonne abonne;

    @Before
    public void setUp() {
        utilisateur = new Utilisateur(
                1,
                "Nom",
                "Prenom",
                "test@test.com",
                "password",
                null
        );

        abonnement = new Abonnement(
                1,
                "Mensuel",
                "Accès illimité pendant 30 jours"
        );

        abonne = new Abonne(utilisateur, abonnement, true);
    }

    @Test
    public void testConstructor() {
        assertEquals(utilisateur, abonne.getUtilisateur());
        assertEquals(abonnement, abonne.getAbonnement());
        assertTrue(abonne.isEstActif());
    }

    @Test
    public void testSetUtilisateur() {
        Utilisateur u2 = new Utilisateur(
                2,
                "New",
                "User",
                "new@test.com",
                "pwd",
                null
        );

        abonne.setUtilisateur(u2);
        assertEquals(u2, abonne.getUtilisateur());
    }

    @Test
    public void testSetAbonnement() {
        Abonnement a2 = new Abonnement(2, "Annuel", "365 jours");
        abonne.setAbonnement(a2);
        assertEquals(a2, abonne.getAbonnement());
    }

    @Test
    public void testSetEstActif() {
        abonne.setEstActif(false);
        assertFalse(abonne.isEstActif());
    }
}
