package test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Test;

import utils.AuthManager;

public class TestAuthManager {

    @After
    public void tearDown() {
        AuthManager.logout();
    }

    @Test
    public void testGetCurrentUser_ApresLogout_EstNull() {
        AuthManager.logout();
        assertNull(AuthManager.getCurrentUser());
    }

    @Test
    public void testHasRole_SansUtilisateurConnecte_RetourneFalse() {
        AuthManager.logout();
        assertFalse(AuthManager.hasRole("ABONNE"));
        assertFalse(AuthManager.hasRole("PARKINGADMIN", "SYSADMIN"));
    }

    @Test
    public void testEnsureAuthorized_SansUtilisateurConnecte_RetourneFalse() {
        AuthManager.logout();
        assertFalse(AuthManager.ensureAuthorized("ABONNE"));
    }
}
