package test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import modele.Utilisateur;
import modele.Utilisateur.Type;
import utils.AuthManager;

public class TestAuthManager {

    @Before
    public void setUp() {
        // Ensure no user is logged in before each test
        AuthManager.logout();
    }

    @After
    public void tearDown() {
        // Clean up after each test
        AuthManager.logout();
    }

    @Test
    public void testInitialStateNoUser() {
        AuthManager.logout();
        assertNull(AuthManager.getCurrentUser());
    }

    @Test
    public void testGetCurrentUserAfterLogout() {
        AuthManager.logout();
        assertNull(AuthManager.getCurrentUser());
    }

    @Test
    public void testHasRoleWithNoUser() {
        AuthManager.logout();
        assertFalse(AuthManager.hasRole("CLIENT"));
        assertFalse(AuthManager.hasRole("ADMIN", "CLIENT"));
        assertFalse(AuthManager.hasRole("PARKINGADMIN"));
    }

    @Test
    public void testHasRoleWithEmptyRoles() {
        AuthManager.logout();
        assertFalse(AuthManager.hasRole());
    }

    @Test
    public void testHasRoleWithNullRoles() {
        AuthManager.logout();
        boolean result = AuthManager.hasRole((String[]) null);
        assertFalse(result);
    }

    @Test
    public void testEnsureAuthorizedWithNoUser() {
        AuthManager.logout();
        assertFalse(AuthManager.ensureAuthorized("CLIENT"));
    }

    @Test
    public void testEnsureAuthorizedWithEmptyRoles() {
        AuthManager.logout();
        assertFalse(AuthManager.ensureAuthorized());
    }

    @Test
    public void testLoginWithEmptyEmail() {
        // Test with null email - should return false
        boolean result = AuthManager.login(null, "password");
        assertFalse(result);
        assertNull(AuthManager.getCurrentUser());
    }

    @Test
    public void testLoginWithBlankEmail() {
        // Test with blank email - should return false
        boolean result = AuthManager.login("   ", "password");
        assertFalse(result);
        assertNull(AuthManager.getCurrentUser());
    }

    @Test
    public void testLoginWithEmptyPassword() {
        // Even though password is technically validated elsewhere,
        // this tests the flow when AuthService gets called
        boolean result = AuthManager.login("test@test.com", "");
        // Result depends on database, but user should not be set on failure
        if (!result) {
            assertNull(AuthManager.getCurrentUser());
        }
    }

    @Test
    public void testLogoutClearsCurrentUser() {
        // Manually create a mock user state (in real scenario would use login)
        // For now, just test that logout sets currentUser to null
        AuthManager.logout();
        assertNull(AuthManager.getCurrentUser());
    }

    @Test
    public void testMultipleLoginAttempts() {
        // First attempt with invalid credentials
        boolean result1 = AuthManager.login("invalid@test.com", "wrongpass");
        
        // After failed login, no user should be set
        if (!result1) {
            assertNull(AuthManager.getCurrentUser());
        }
        
        // Logout to ensure clean state
        AuthManager.logout();
        assertNull(AuthManager.getCurrentUser());
    }

    @Test
    public void testHasRoleWithMultipleRoles() {
        AuthManager.logout();
        // When no user is logged in, hasRole should return false
        // even with multiple role parameters
        assertFalse(AuthManager.hasRole("ADMIN", "PARKINGADMIN", "SYSADMIN"));
    }

    @Test
    public void testHasRoleCaseSensitivity() {
        AuthManager.logout();
        // hasRole should handle case-insensitive comparisons
        // But with no user, should still return false
        assertFalse(AuthManager.hasRole("client"));
        assertFalse(AuthManager.hasRole("CLIENT"));
        assertFalse(AuthManager.hasRole("Client"));
    }

    @Test
    public void testLoginWithInvalidCredentials() {
        // Try login with clearly invalid email format (no @ symbol processing here)
        boolean result = AuthManager.login("notanemail", "password");
        // Based on implementation, it will try to authenticate but fail
        // After failed login, user should be null
        if (!result) {
            assertNull(AuthManager.getCurrentUser());
        }
    }

    @Test
    public void testLogoutMultipleTimes() {
        // Calling logout multiple times should be safe
        AuthManager.logout();
        AuthManager.logout();
        AuthManager.logout();
        assertNull(AuthManager.getCurrentUser());
    }

    @Test
    public void testLoginEdgeCaseWithSpecialCharacters() {
        // Test email with special characters
        boolean result = AuthManager.login("test+special@test.com", "password");
        // Result depends on database, just ensure state is consistent
        if (!result) {
            assertNull(AuthManager.getCurrentUser());
        }
    }

    @Test
    public void testEnsureAuthorizedReturnsFalseWhenNoUser() {
        AuthManager.logout();
        boolean result = AuthManager.ensureAuthorized("CLIENT");
        assertFalse(result);
    }

    @Test
    public void testEnsureAuthorizedWithMultipleAllowedRoles() {
        AuthManager.logout();
        boolean result = AuthManager.ensureAuthorized("ADMIN", "PARKINGADMIN");
        assertFalse(result);
    }
}
