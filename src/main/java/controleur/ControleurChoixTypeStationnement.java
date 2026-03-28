package controleur;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.sql.SQLException;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JOptionPane;
import javax.swing.BorderFactory;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import java.time.format.DateTimeFormatter;

import modele.ReservationParking;
import modele.Utilisateur;
import modele.Vehicule;
import modele.dao.DaoReservationParking;
import modele.dao.DaoVehicule;
import modele.dao.DaoUtilisateur;
import modele.dao.MySQLDataSource;
import vue.ChoixParking;
import vue.ChoixTypeStationnement;
import vue.ChoixZone;
import vue.LoginPage;
import vue.NavigationFrame;
import vue.Profile;
import vue.HistoriquePanel;
import vue.TicketParking;
import utils.AuthManager;

public class ControleurChoixTypeStationnement implements ActionListener {

    public enum Etat {
        PARKING, VOIRIE
    }

    private final ChoixTypeStationnement vue;
    private final int idUser;
    private ReservationParking activeReservation;
    private boolean ticketListenerAttached;

    public ControleurChoixTypeStationnement(ChoixTypeStationnement vue, int idUser) {
        this.vue = vue;
        this.idUser = idUser;

        this.vue.getProfileButton().addActionListener(this);
        this.vue.getBtnParking().addActionListener(this);
        this.vue.getBtnVoirie().addActionListener(this);

        this.vue.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentShown(ComponentEvent e) {
                chargerEtatStationnement();
            }
        });

		chargerEtatStationnement();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vue.getProfileButton()) {
            showProfileMenu();
            return;
        }
        if (e.getSource() == vue.getBtnParking()) {
            openParkingPage();
            return;
        }
        if (e.getSource() == vue.getBtnVoirie()) {
            openVoiriePage();
        }
    }

    private void openParkingPage() {
        try {
        	if (chargerEtatStationnement()) {
        		JOptionPane.showMessageDialog(vue, "Vous êtes déjà garé. Quittez d'abord le parking.");
        		return;
        	}
            NavigationFrame.getInstance().showPage("Parkings",
                    () -> {
                        ChoixParking p = new ChoixParking();
                        new ControleurChoixParking(p);
                        return p;
                    },
                    "Parkings",
                    true);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    private void openVoiriePage() {
        try {
        	if (chargerEtatStationnement()) {
        		JOptionPane.showMessageDialog(vue, "Vous êtes déjà garé. Quittez d'abord le parking.");
        		return;
        	}
            NavigationFrame.getInstance().showPage("Voirie",
                    ChoixZone::new,
                    "Voirie");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private boolean chargerEtatStationnement() {
        activeReservation = null;
        Utilisateur current = AuthManager.getCurrentUser();
        if (current == null) {
            vue.cacherTicketActif();
            return false;
        }
        try {
            MySQLDataSource.creerAcces();
            DaoReservationParking dao = new DaoReservationParking();
            activeReservation = dao.findActiveByUserId(current.getId());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        if (activeReservation == null) {
            vue.cacherTicketActif();
            return false;
        }

        String plaque = getPlaqueUtilisateur(current);
        String heureArrivee = activeReservation.getDateArrivee() != null
                ? activeReservation.getDateArrivee().toLocalTime().format(DateTimeFormatter.ofPattern("HH:mm"))
                : "";
        vue.afficherTicketActif(activeReservation.getParking().getNom(), plaque, heureArrivee);
        if (!ticketListenerAttached) {
            vue.getBtnVoirTicket().addActionListener(ev -> ouvrirTicketActif());
            ticketListenerAttached = true;
        }
        return true;
    }

    private void ouvrirTicketActif() {
        if (activeReservation == null) {
            JOptionPane.showMessageDialog(vue, "Aucun ticket actif.");
            return;
        }
        String plaque = getPlaqueUtilisateur(AuthManager.getCurrentUser());
        //String heureArrivee = activeReservation.getDateArrivee() != null
        //        ? activeReservation.getDateArrivee().toLocalTime().format(DateTimeFormatter.ofPattern("HH:mm"))
        //        : ""
        TicketParking ticket = new TicketParking(activeReservation, plaque, false);
        new ControleurTicketParking(ticket);
        NavigationFrame.getInstance().showPage("parking-ticket", () -> ticket, "Ticket parking", true);
    }

    private String getPlaqueUtilisateur(Utilisateur user) {
        if (user == null) {
            return "";
        }
        try {
            MySQLDataSource.creerAcces();
            DaoVehicule daoVehicule = new DaoVehicule();
            java.util.List<Vehicule> vehicules = daoVehicule.findByUserId(user.getId());
            if (!vehicules.isEmpty()) {
                return vehicules.getFirst().getImmatriculation();
            }
        } catch (Exception ex) {
            // Ignore and fallback
        }
        return "";
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

    private Utilisateur safeFetchUser(int idUser) throws SQLException {
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
