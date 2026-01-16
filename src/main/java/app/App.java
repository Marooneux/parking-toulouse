package app;

import javax.swing.SwingUtilities;

import utils.AuthManager;
import vue.LoginPage;
import vue.NavigationFrame;

public final class App {
    private App() {
        // Prevent instantiation
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            AuthManager.logout();
            NavigationFrame nav = NavigationFrame.getInstance();
            nav.setVisible(true);
            nav.showPage("Connexion", LoginPage::new, "Connexion");
        });
    }
}
