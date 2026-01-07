package utils;

import modele.Utilisateur;
import modele.dao.AuthService;

import javax.swing.*;
import java.sql.SQLException;
import java.util.Arrays;

/**
 * Simple security context and authorization helper.
 */
public class AuthManager {
    private static Utilisateur currentUser;

    public static boolean login(String email, String mdp) {
        try {
            Utilisateur u = AuthService.authenticate(email, mdp);
            if (u != null) {
                currentUser = u;
                return true;
            }
            return false;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erreur de connexion: " + e.getMessage());
            return false;
        }
    }

    public static void logout() {
        currentUser = null;
    }

    public static Utilisateur getCurrentUser() {
        return currentUser;
    }

    public static boolean hasRole(String... allowed) {
        if (currentUser == null) return false;
        String type = currentUser.getType();
        return type != null && Arrays.stream(allowed).anyMatch(r -> r.equalsIgnoreCase(type));
    }

    /**
     * Show a message and return false if not authorized.
     */
    public static boolean ensureAuthorized(String... allowed) {
        if (!hasRole(allowed)) {
            JOptionPane.showMessageDialog(null, "Accès refusé: privilèges insuffisants");
            return false;
        }
        return true;
    }
}
