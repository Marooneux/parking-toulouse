package app;

import javax.swing.SwingUtilities;

import utils.AuthManager;
import vue.LoginPage;

public final class App {
    private App() {
        // Prevent instantiation
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            AuthManager.logout();
            LoginPage login = new LoginPage();
            login.setVisible(true);
        });
    }
}
