package modele;

import java.awt.Component;

import javax.swing.JOptionPane;

public class Validation {

    public static boolean requireNotEmpty(String value, String message, Component parent) {
        if (value == null || value.trim().isEmpty()) {
            JOptionPane.showMessageDialog(parent, message);
            return false;
        }
        return true;
    }

    public static boolean validatePlaque(String plaque, Component parent) {
        if (!plaque.matches("(?i)[A-Z]{2}-\\d{3}-[A-Z]{2}")) {
            JOptionPane.showMessageDialog(parent, "Format de plaque invalide. Exemple : AB-123-CD");
            return false;
        }
        return true;
    }
}

