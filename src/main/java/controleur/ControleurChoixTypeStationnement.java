package controleur;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JOptionPane;
import javax.swing.BorderFactory;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import modele.Utilisateur;
import modele.dao.DaoUtilisateur;
import modele.dao.MySQLDataSource;
import vue.ChoixParking;
import vue.ChoixTypeStationnement;
import vue.ChoixZone;
import vue.LoginPage;
import vue.NavigationFrame;
import vue.Profile;
import vue.HistoriquePanel;
import utils.AuthManager;

public class ControleurChoixTypeStationnement implements ActionListener {

    public enum Etat {
        PARKING, VOIRIE
    }

    private final ChoixTypeStationnement vue;
    private final int idUser;

    public ControleurChoixTypeStationnement(ChoixTypeStationnement vue, int idUser) {
        this.vue = vue;
        this.idUser = idUser;

        this.vue.getProfileButton().addActionListener(this);
        this.vue.getBtnParking().addActionListener(this);
        this.vue.getBtnVoirie().addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vue.getProfileButton()) {
            showProfileMenu();
            return;
        }
        if (e.getSource() == vue.getBtnParking()) {
            openParkingPage(idUser);
            return;
        }
        if (e.getSource() == vue.getBtnVoirie()) {
            openVoiriePage();
        }
    }

    private void openParkingPage(int idUser) {
        try {
            NavigationFrame.getInstance().showPage("Parkings",
                    () -> {
                        ChoixParking p = new ChoixParking();
                        new ControleurChoixParking(p, idUser);
                        return p;
                    },
                    "Parkings");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    private void openVoiriePage() {
        try {
            NavigationFrame.getInstance().showPage("Voirie",
                    () -> new ChoixZone(),
                    "Voirie");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public void openProfile(int idUser) {
        try {
            Utilisateur user = safeFetchUser(idUser);
            if (user == null) {
                JOptionPane.showMessageDialog(vue, "Profil introuvable");
                return;
            }

            NavigationFrame.getInstance().showPage("Profil",
                    () -> new Profile(user),
                    "Profil");
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(vue, "Erreur lors de l'ouverture du profil");
        }
    }

    private void openProfileHistorique(int idUser) {
        try {
            Utilisateur user = safeFetchUser(idUser);
            if (user == null) {
                JOptionPane.showMessageDialog(vue, "Utilisateur introuvable");
                return;
            }

            NavigationFrame.getInstance().showPage("Historique",
                    () -> new HistoriquePanel(user),
                    "Historique");
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(vue, "Erreur lors de l'ouverture de l'historique");
        }
    }

    private Utilisateur safeFetchUser(int idUser) throws Exception {
        Utilisateur current = AuthManager.getCurrentUser();
        if (current != null && current.getId() == idUser) {
            return current;
        }
        MySQLDataSource.creerAcces();
        DaoUtilisateur dao = new DaoUtilisateur();
        return dao.findById(idUser);
    }

    private void logout() {
        AuthManager.logout();
        NavigationFrame.getInstance().showPage("login", LoginPage::new, "Connexion");
    }

    private void showProfileMenu() {
        Color borderColor = new Color(206, 212, 218);
        Color textColor = new Color(33, 37, 41);

        JPopupMenu menu = new JPopupMenu();
        menu.setBackground(Color.WHITE);
        menu.setBorder(new LineBorder(borderColor, 1));

        JMenuItem profilItem = new JMenuItem("Mon profil");
        styleMenuItem(profilItem, textColor);
        profilItem.addActionListener(ev -> openProfile(idUser));
        menu.add(profilItem);

        JMenuItem historiqueItem = new JMenuItem("Historique des parkings");
        styleMenuItem(historiqueItem, textColor);
        historiqueItem.addActionListener(ev -> openProfileHistorique(idUser));
        menu.add(historiqueItem);

        JMenuItem logoutItem = new JMenuItem("Déconnexion");
        styleMenuItem(logoutItem, textColor);
        logoutItem.addActionListener(ev -> logout());
        menu.add(logoutItem);

        menu.show(vue.getProfileButton(), 0, vue.getProfileButton().getHeight());
    }

    private void styleMenuItem(JMenuItem item, Color textColor) {
        item.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        item.setBackground(Color.WHITE);
        item.setForeground(textColor);
        item.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(new Color(255, 255, 255), 0),
                new EmptyBorder(10, 15, 10, 15)));
    }
    
}
