package vue;

import java.awt.*;
import java.util.List;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import modele.Utilisateur;
import modele.ReservationParking;
import modele.ReservationVoirie;
import modele.ZoneVoirie;
import controleur.ControleurHistorique;

// 1. On hérite de JPanel au lieu de JFrame
public class HistoriquePanel extends JPanel {

    private static final String FONT_SEGOE_UI = FONT_SEGOE_UI;

    private JPanel reservationsPanel;
    private JButton loadMoreButton;
    // 2. Le constructeur prend l'Utilisateur pour savoir QUI afficher
    public HistoriquePanel(Utilisateur utilisateur) {
        // Configuration du JPanel
        setLayout(new BorderLayout(15, 15));
        setBorder(new EmptyBorder(20, 20, 20, 20));
        setBackground(new Color(248, 249, 250));

        initHeader();
        initReservationsList();
        initLoadMoreButton();

        new ControleurHistorique(this, utilisateur.getId());
    }

    private void initHeader() {
        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(new Color(248, 249, 250));

        JLabel title = new JLabel("Historique des réservations");
        title.setFont(new Font(FONT_SEGOE_UI, Font.BOLD, 22));
        title.setForeground(new Color(33, 37, 41));

        JLabel subtitle = new JLabel("Retrouvez vos stationnements passés et en cours");
        subtitle.setFont(new Font(FONT_SEGOE_UI, Font.PLAIN, 14));
        subtitle.setForeground(Color.GRAY);

        JPanel textPanel = new JPanel();
        textPanel.setBackground(new Color(248, 249, 250));
        textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.Y_AXIS));
        textPanel.add(title);
        textPanel.add(Box.createVerticalStrut(5));
        textPanel.add(subtitle);

        header.add(textPanel, BorderLayout.CENTER);
        add(header, BorderLayout.NORTH);
    }

    private void initReservationsList() {
        reservationsPanel = new JPanel();
        reservationsPanel.setLayout(new BoxLayout(reservationsPanel, BoxLayout.Y_AXIS));
        reservationsPanel.setBackground(new Color(248, 249, 250));

        JScrollPane scrollPane = new JScrollPane(reservationsPanel);
        scrollPane.setBorder(null);
        scrollPane.getViewport().setBackground(new Color(248, 249, 250));
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);

        add(scrollPane, BorderLayout.CENTER);
    }

    private void initLoadMoreButton() {
        loadMoreButton = new JButton("Rafraîchir");
        loadMoreButton.setBackground(Color.WHITE);
        loadMoreButton.setFocusPainted(false);
        loadMoreButton.setFont(new Font(FONT_SEGOE_UI, Font.PLAIN, 12));
        loadMoreButton.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200)));
        loadMoreButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        loadMoreButton.setPreferredSize(new Dimension(200, 35));

        JPanel panel = new JPanel();
        panel.setBackground(new Color(248, 249, 250));
        panel.add(loadMoreButton);

        add(panel, BorderLayout.SOUTH);
    }

    public void addReloadListener(java.awt.event.ActionListener listener) {
        loadMoreButton.addActionListener(listener);
    }

    public void afficherHistorique(List<ReservationParking> reservationsParking, List<ReservationVoirie> reservationsVoirie) {
        reservationsPanel.removeAll();

        if (reservationsParking.isEmpty() && reservationsVoirie.isEmpty()) {
            JLabel empty = new JLabel("Aucun historique trouvé.");
            empty.setFont(new Font(FONT_SEGOE_UI, Font.ITALIC, 14));
            empty.setForeground(Color.GRAY);
            empty.setAlignmentX(Component.CENTER_ALIGNMENT);
            reservationsPanel.add(Box.createVerticalStrut(20));
            reservationsPanel.add(empty);
        }

        for (ReservationParking r : reservationsParking) {
            reservationsPanel.add(createReservationCardParking(r));
            reservationsPanel.add(Box.createRigidArea(new Dimension(0, 12)));
        }

        for (ReservationVoirie r : reservationsVoirie) {
            reservationsPanel.add(createReservationCardVoirie(r));
            reservationsPanel.add(Box.createRigidArea(new Dimension(0, 12)));
        }

        reservationsPanel.revalidate();
        reservationsPanel.repaint();
    }

    private JPanel createReservationCardParking(ReservationParking r) {
        JPanel card = new JPanel(new BorderLayout(10, 10));
        card.setBackground(Color.WHITE);
        card.setMaximumSize(new Dimension(Integer.MAX_VALUE, 100));
        card.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(220, 220, 220)),
                new EmptyBorder(12, 12, 12, 12)
        ));

        // --- ICONE ---
        JLabel icon = new JLabel("\uD83D\uDE97");
        icon.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 24));
        card.add(icon, BorderLayout.WEST);

        // --- INFO CENTRE ---
        JPanel infoPanel = new JPanel();
        infoPanel.setBackground(Color.WHITE);
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));

        JLabel name = new JLabel(r.getParking().getNom());
        name.setFont(new Font(FONT_SEGOE_UI, Font.BOLD, 15));
        
        String adrStr = (r.getParking().getAdresse() != null) ? r.getParking().getAdresse().getRue() : "Adresse inconnue";
        JLabel address = new JLabel(adrStr);
        address.setFont(new Font(FONT_SEGOE_UI, Font.PLAIN, 12));
        address.setForeground(Color.GRAY);

        // Dates
        JLabel dates = new JLabel("Du " + r.getDateArrivee() + (r.getDateDepart() != null ? " au " + r.getDateDepart() : " (En cours)"));
        dates.setFont(new Font(FONT_SEGOE_UI, Font.PLAIN, 11));
        dates.setForeground(new Color(100, 100, 150));

        infoPanel.add(name);
        infoPanel.add(address);
        infoPanel.add(Box.createVerticalStrut(5));
        infoPanel.add(dates);
        card.add(infoPanel, BorderLayout.CENTER);

        // --- PRIX & ACTION ---
        JPanel rightPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
        rightPanel.setBackground(Color.WHITE);

        double prix = (r.getDateDepart() != null) ? r.calculerPrixTotal() : 0;
        JLabel price = new JLabel(r.getDateDepart() == null ? "En cours" : String.format("%.2f €", prix));
        price.setFont(new Font(FONT_SEGOE_UI, Font.BOLD, 14));
        price.setForeground(new Color(40, 167, 69));

        rightPanel.add(price);
        card.add(rightPanel, BorderLayout.EAST);
        
        card.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                card.setBackground(new Color(250, 250, 250));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                card.setBackground(Color.WHITE);
            }
        });

        return card;
    }

    private JPanel createReservationCardVoirie(ReservationVoirie r) {
        JPanel card = new JPanel(new BorderLayout(10, 10));
        card.setBackground(Color.WHITE);
        card.setMaximumSize(new Dimension(Integer.MAX_VALUE, 100));
        card.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(220, 220, 220)),
                new EmptyBorder(12, 12, 12, 12)
        ));

        JLabel icon = new JLabel("\uD83D\uDEA7");
        icon.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 24));
        card.add(icon, BorderLayout.WEST);

        JPanel infoPanel = new JPanel();
        infoPanel.setBackground(Color.WHITE);
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));

        ZoneVoirie zone = r.getZone();
        JLabel name = new JLabel("Zone voirie " + (zone != null ? zone.getCouleur() : "?"));
        name.setFont(new Font(FONT_SEGOE_UI, Font.BOLD, 15));

        JLabel dates = new JLabel("Début " + r.getDateDebut() + " | Durée " + r.getDureeMinutes() + " min");
        dates.setFont(new Font(FONT_SEGOE_UI, Font.PLAIN, 11));
        dates.setForeground(new Color(100, 100, 150));

        infoPanel.add(name);
        infoPanel.add(Box.createVerticalStrut(5));
        infoPanel.add(dates);
        card.add(infoPanel, BorderLayout.CENTER);

        JPanel rightPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
        rightPanel.setBackground(Color.WHITE);
        JLabel price = new JLabel("Durée " + r.getDureeMinutes() + " min");
        price.setFont(new Font(FONT_SEGOE_UI, Font.BOLD, 14));
        price.setForeground(new Color(0, 123, 255));
        rightPanel.add(price);
        card.add(rightPanel, BorderLayout.EAST);

        card.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                card.setBackground(new Color(250, 250, 250));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                card.setBackground(Color.WHITE);
            }
        });

        return card;
    }
}