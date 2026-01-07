package vue.adminParking;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import controleur.ControleurAccueilAdmin;

import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.time.LocalTime;
import java.util.function.Consumer;

import modele.Parking;

public class Accueil extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel gridPanel;
    private CardLayout cardLayout;
    private JPanel mainContentPanel;
    private JPanel sidebarPanel;
    private JButton btnToggle;
    private JLayeredPane layeredPane;
    private static final int SIDEBAR_WIDTH = 200;

    public static void main(String[] args) {
    	ControleurAccueilAdmin frameChoixParking = new ControleurAccueilAdmin(new Accueil());
    }

    public Accueil() {
        initialize();
    }

    private void initialize() {
        setTitle("Stationnement");
        setBounds(100, 100, 1300, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        layeredPane = new JLayeredPane();
        setContentPane(layeredPane);

        mainContentPanel = new JPanel();
        cardLayout = new CardLayout();
        mainContentPanel.setLayout(cardLayout);
        mainContentPanel.setBackground(new Color(248, 249, 250));

        JPanel viewParkings = createParkingView();
        mainContentPanel.add(viewParkings, "PARKINGS");

        JPanel viewStats = createStatsView();
        mainContentPanel.add(viewStats, "STATS");

        layeredPane.add(mainContentPanel, JLayeredPane.DEFAULT_LAYER);

        sidebarPanel = createSidebar();
        sidebarPanel.setVisible(true);
        layeredPane.add(sidebarPanel, JLayeredPane.PALETTE_LAYER);

        btnToggle = new JButton("\u2630"); 
        btnToggle.setFont(new Font("Segoe UI", Font.PLAIN, 24));
        btnToggle.setFocusPainted(false);
        btnToggle.setBorderPainted(false);
        btnToggle.setContentAreaFilled(false);
        btnToggle.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnToggle.setForeground(Color.LIGHT_GRAY); 
        btnToggle.setBounds(10, 10, 50, 40);
        
        btnToggle.addActionListener(e -> toggleSidebarState());
       
        layeredPane.add(btnToggle, JLayeredPane.MODAL_LAYER);

        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                updateLayoutBounds();
            }
        });
    }

    private void updateLayoutBounds() {
        int w = getContentPane().getWidth();
        int h = getContentPane().getHeight();
        
        if (sidebarPanel.isVisible()) {
            sidebarPanel.setBounds(0, 0, SIDEBAR_WIDTH, h);
            mainContentPanel.setBounds(SIDEBAR_WIDTH, 0, w - SIDEBAR_WIDTH, h);
        } else {
            mainContentPanel.setBounds(0, 0, w, h);
        }
        
        mainContentPanel.revalidate();
        mainContentPanel.repaint();
    }

    private void toggleSidebarState() {
        boolean isVisible = sidebarPanel.isVisible();
        sidebarPanel.setVisible(!isVisible);

        // Changement de couleur du bouton selon le fond (Sombre si menu ouvert, Clair si menu fermé)
        if (!isVisible) {
            // On va ouvrir le menu
            btnToggle.setForeground(Color.LIGHT_GRAY);
        } else {
            // On va fermer le menu (le bouton sera sur fond blanc)
            btnToggle.setForeground(new Color(33, 37, 41));
        }
        
        // On recalcule les positions immédiatement
        updateLayoutBounds();
    }

    private JPanel createSidebar() {
        JPanel sidebar = new JPanel();
        sidebar.setLayout(new BoxLayout(sidebar, BoxLayout.Y_AXIS));
        sidebar.setBackground(new Color(33, 37, 41));
        sidebar.setBorder(new EmptyBorder(60, 10, 20, 10)); 

        JLabel lblMenu = new JLabel("MENU");
        lblMenu.setForeground(Color.LIGHT_GRAY);
        lblMenu.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lblMenu.setAlignmentX(Component.LEFT_ALIGNMENT);
        lblMenu.setBorder(new EmptyBorder(0, 10, 20, 0));
        sidebar.add(lblMenu);

        JButton btnParkings = createMenuButton("Liste Parkings");
        btnParkings.addActionListener(e -> {
            cardLayout.show(mainContentPanel, "PARKINGS");
        });
        sidebar.add(btnParkings);

        sidebar.add(Box.createVerticalStrut(10));

        JButton btnStats = createMenuButton("Statistiques");
        btnStats.addActionListener(e -> {
            cardLayout.show(mainContentPanel, "STATS");
            // toggleSidebarState(); 
        });
        sidebar.add(btnStats);

        sidebar.add(Box.createVerticalGlue());

        return sidebar;
    }

    private JButton createMenuButton(String text) {
        JButton btn = new JButton(text);
        btn.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        btn.setAlignmentX(Component.LEFT_ALIGNMENT);
        btn.setBackground(new Color(52, 58, 64));
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setBorderPainted(false);
        btn.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return btn;
    }

    private JPanel createHeaderPanel(String icon, String title, String subtitle) {
        JPanel headerPanel = new JPanel();
        headerPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
        headerPanel.setBackground(new Color(248, 249, 250));
        headerPanel.setBorder(new EmptyBorder(40, 70, 30, 50));

        JPanel textContainer = new JPanel();
        textContainer.setLayout(new BoxLayout(textContainer, BoxLayout.Y_AXIS));
        textContainer.setBackground(new Color(248, 249, 250));

        JPanel titleLine = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        titleLine.setBackground(new Color(248, 249, 250));

        JLabel iconLabel = new JLabel(icon);
        iconLabel.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 32));

        JLabel lblTitle = new JLabel(title);
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 26));
        lblTitle.setForeground(new Color(33, 37, 41));

        titleLine.add(iconLabel);
        titleLine.add(lblTitle);

        JLabel lblSubtitle = new JLabel(subtitle);
        lblSubtitle.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lblSubtitle.setForeground(new Color(108, 117, 125));
        lblSubtitle.setBorder(new EmptyBorder(5, 5, 0, 0));

        textContainer.add(titleLine);
        textContainer.add(lblSubtitle);

        headerPanel.add(textContainer);

        return headerPanel;
    }

    private JPanel createParkingView() {
        JPanel panel = new JPanel(new BorderLayout(0, 0));
        panel.setBackground(new Color(248, 249, 250));

        panel.add(createHeaderPanel("\uD83C\uDD7F\uFE0F ", "Gérer vos parkings", "Sélectionnez le parking à gérer."), BorderLayout.NORTH);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBorder(null);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        scrollPane.getViewport().setBackground(new Color(248, 249, 250));

        gridPanel = new JPanel();
        gridPanel.setBackground(new Color(248, 249, 250));
        // GridLayout s'adaptera automatiquement à la nouvelle largeur
        gridPanel.setLayout(new GridLayout(0, 3, 25, 25));
        gridPanel.setBorder(new EmptyBorder(0, 50, 50, 50));

        scrollPane.setViewportView(gridPanel);
        panel.add(scrollPane, BorderLayout.CENTER);

        return panel;
    }

    private JPanel createStatsView() {
        JPanel panel = new JPanel(new BorderLayout(0, 0));
        panel.setBackground(new Color(248, 249, 250));

        panel.add(createHeaderPanel("\uD83D\uDCCA ", "Statistiques", "Visualisez l'occupation des parkings."), BorderLayout.NORTH);

        JPanel contentStats = new JPanel();
        contentStats.setBackground(new Color(248, 249, 250));
        contentStats.add(new JLabel("Contenu des statistiques..."));

        panel.add(contentStats, BorderLayout.CENTER);

        return panel;
    }

    public void addParking(Parking parking, Consumer<Parking> onSelect) {
        AdminParkingPanel card = new AdminParkingPanel(parking, onSelect);
        gridPanel.add(card);
        gridPanel.revalidate();
        gridPanel.repaint();
    }
}