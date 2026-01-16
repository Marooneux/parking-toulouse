package vue.adminParking;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.time.LocalTime;
import java.util.function.Consumer;

import controleur.ControleurAccueilAdminParking;
import modele.Parking;

public class Accueil extends JFrame {

    private static final long serialVersionUID = 1L;

    private JLayeredPane layeredPane;
    private JPanel mainContentPanel;
    private JPanel sidebarPanel;
    private CardLayout cardLayout;
    private JButton btnToggle;
    private static final int SIDEBAR_WIDTH = 200;

    private JPanel gridPanel;
    private JButton btnAjouter;

    private JTextField txtNom;
    private JTextField txtAdresse;
    private JTextField txtTarif;
    private JTextField txtHauteur;
    private JTextField txtPlacesMax;
    private JTextField txtHeureOuverture;
    private JTextField txtHeureFermeture;
    private JCheckBox chkMoto;
    
    private JButton btnEnregistrerModification;
    private JButton btnRetourListe;
    
    private Parking parkingEnEdition;
    private int idAdmin;
    private ControleurAccueilAdminParking controleur;

    public static void main(String[] args) {
    	new Accueil(3);
    }

    public Accueil(int idAdmin) {
    	this.idAdmin = idAdmin;
    	initialize();
    	this.controleur = new ControleurAccueilAdminParking(this, idAdmin);
    }

    private void initialize() {
        setTitle("Stationnement - Administration");
        setBounds(100, 100, 1350, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        layeredPane = new JLayeredPane();
        setContentPane(layeredPane);

        mainContentPanel = new JPanel();
        cardLayout = new CardLayout();
        mainContentPanel.setLayout(cardLayout);
        mainContentPanel.setBackground(new Color(248, 249, 250));

        JPanel viewParkings = createParkingView();
        mainContentPanel.add(viewParkings, "PARKINGS");

        JPanel viewEdition = createEditionView();
        mainContentPanel.add(viewEdition, "EDITION");

        JPanel viewStats = createStatsView();
        mainContentPanel.add(viewStats, "STATS");

        layeredPane.add(mainContentPanel, JLayeredPane.DEFAULT_LAYER);

        sidebarPanel = createSidebar();
        sidebarPanel.setVisible(true);
        layeredPane.add(sidebarPanel, JLayeredPane.PALETTE_LAYER);

        // Bouton Toggle Menu
        createToggleBtn();

        // Gestion du redimensionnement
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                updateLayoutBounds();
            }
        });
    }

    private void createToggleBtn() {
        btnToggle = new JButton("\u2630");
        btnToggle.setFont(new Font("Segoe UI Symbol", Font.BOLD, 30));
        btnToggle.setFocusPainted(false);
        btnToggle.setBorderPainted(false);
        btnToggle.setContentAreaFilled(false);
        btnToggle.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnToggle.setForeground(Color.LIGHT_GRAY);
        btnToggle.setBounds(10, 10, 50, 40);
        btnToggle.setMargin(new Insets(0, 0, 0, 0));
        btnToggle.addActionListener(e -> toggleSidebarState());
        layeredPane.add(btnToggle, JLayeredPane.MODAL_LAYER);
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
        btnToggle.setForeground(!isVisible ? Color.LIGHT_GRAY : new Color(33, 37, 41));
        updateLayoutBounds();
    }

    // --- BARRE LATERALE ---
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
        btnParkings.addActionListener(e -> cardLayout.show(mainContentPanel, "PARKINGS"));
        sidebar.add(btnParkings);

        sidebar.add(Box.createVerticalStrut(10));

        JButton btnStats = createMenuButton("Statistiques");
        btnStats.addActionListener(e -> cardLayout.show(mainContentPanel, "STATS"));
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

    private JPanel createParkingView() {
        JPanel panel = new JPanel(new BorderLayout(0, 0));
        panel.setBackground(new Color(248, 249, 250));

        JPanel headerPanel = new JPanel();
        headerPanel.setLayout(new BoxLayout(headerPanel, BoxLayout.Y_AXIS));
        headerPanel.setBackground(new Color(248, 249, 250));
        headerPanel.setBorder(new EmptyBorder(40, 70, 30, 50));

        JPanel titleContainer = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        titleContainer.setBackground(new Color(248, 249, 250));
        
        JLabel iconCar = new JLabel("\uD83C\uDD7F\uFE0F "); 
        iconCar.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 32));
        
        JLabel lblTitle = new JLabel("Gestion des Parkings");
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 26));
        lblTitle.setForeground(new Color(33, 37, 41));

        titleContainer.add(iconCar);
        titleContainer.add(lblTitle);

        JLabel lblSubtitle = new JLabel("Gérez vos parkings");
        lblSubtitle.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lblSubtitle.setForeground(new Color(108, 117, 125));
        lblSubtitle.setBorder(new EmptyBorder(5, 5, 0, 0));

        headerPanel.add(titleContainer);
        headerPanel.add(lblSubtitle);
        panel.add(headerPanel, BorderLayout.NORTH);

        // Grille Scrollable
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBorder(null);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        scrollPane.getViewport().setBackground(new Color(248, 249, 250));

        gridPanel = new JPanel();
        gridPanel.setBackground(new Color(248, 249, 250));
        gridPanel.setLayout(new GridLayout(0, 3, 25, 25));
        gridPanel.setBorder(new EmptyBorder(0, 50, 50, 50));

        scrollPane.setViewportView(gridPanel);
        panel.add(scrollPane, BorderLayout.CENTER);

        JButton btnAjouter = new JButton("Ajouter un parking");
        btnAjouter.setFocusPainted(false);
        btnAjouter.setBackground(new Color(0, 0, 0));
        btnAjouter.setForeground(Color.WHITE);
        btnAjouter.setPreferredSize(new Dimension(150, 30));
        btnAjouter.addActionListener(e -> {
            controleur.ouvrirPageAjouter(idAdmin);
            this.dispose();
           
        });

        JPanel actions = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        actions.setBackground(new Color(248, 249, 250));
        actions.setBorder(new EmptyBorder(10, 50, 30, 50));
        actions.add(btnAjouter);

        panel.add(actions, BorderLayout.SOUTH);

        return panel;
    }

    public void addParking(Parking parking, Consumer<Parking> onSelect, Consumer<Parking> onModify, Consumer<Parking> onDelete) {
        // Utilisation de AdminParkingPanel ou ParkingPanel selon votre projet
        // Ici on suppose un panel simple qui affiche les infos
        AdminParkingPanel panelInfo = new AdminParkingPanel(parking, onSelect);

        JPanel container = new JPanel(new BorderLayout());
        container.setBackground(Color.WHITE);
        container.add(panelInfo, BorderLayout.CENTER);

        // Boutons Modifier / Supprimer
        JButton btnSupprimer = new JButton("Supprimer");
        btnSupprimer.setFocusPainted(false);
        btnSupprimer.setBackground(new Color(220, 53, 69)); // Rouge
        btnSupprimer.setForeground(Color.WHITE);
        btnSupprimer.setPreferredSize(new Dimension(100, 30));
        
        JButton btnModifier = new JButton("Modifier");
        btnModifier.setFocusPainted(false);
        btnModifier.setBackground(new Color(0, 128, 255)); // Bleu
        btnModifier.setForeground(Color.WHITE);
        btnModifier.setPreferredSize(new Dimension(100, 30));

        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        bottomPanel.setBackground(Color.WHITE);
        bottomPanel.add(btnSupprimer);
        bottomPanel.add(btnModifier);
        
        container.add(bottomPanel, BorderLayout.SOUTH);

        // Listeners
        btnModifier.addActionListener(e -> {
            // Remplir le formulaire et changer de vue
            afficherFormulaireEdition(parking);
            // Appeler le consumer si logique supplémentaire requise
            if(onModify != null) onModify.accept(parking);
        });
        
        btnSupprimer.addActionListener(e -> onDelete.accept(parking));

        gridPanel.add(container);
    }

    // --- VUE EDITION (Formulaire) ---
    private JPanel createEditionView() {
        JPanel panel = new JPanel(new BorderLayout(0, 0));
        panel.setBackground(new Color(248, 249, 250));
        
        // Header simple
        JPanel header = new JPanel(new FlowLayout(FlowLayout.LEFT));
        header.setBackground(new Color(248, 249, 250));
        header.setBorder(new EmptyBorder(20, 50, 20, 50));
        JLabel title = new JLabel("Modifier les informations");
        title.setFont(new Font("Segoe UI", Font.BOLD, 22));
        header.add(title);
        panel.add(header, BorderLayout.NORTH);

        // Formulaire
        JPanel form = new JPanel(new GridLayout(0, 2, 10, 20));
        form.setBackground(Color.WHITE);
        form.setBorder(new EmptyBorder(20, 20, 20, 20));

        txtNom = new JTextField();
        txtAdresse = new JTextField();
        txtTarif = new JTextField();
        txtHauteur = new JTextField();
        txtPlacesMax = new JTextField();
        txtHeureOuverture = new JTextField();
        txtHeureFermeture = new JTextField();
        chkMoto = new JCheckBox("Places moto");
        chkMoto.setBackground(Color.WHITE);

        addFormField(form, "Nom", txtNom);
        addFormField(form, "Adresse", txtAdresse);
        addFormField(form, "Tarif (€/h)", txtTarif);
        addFormField(form, "Hauteur max (m)", txtHauteur);
        addFormField(form, "Places max", txtPlacesMax);
        addFormField(form, "Heure Ouverture", txtHeureOuverture);
        addFormField(form, "Heure Fermeture", txtHeureFermeture);
        form.add(new JLabel("Options"));
        form.add(chkMoto);

        JPanel wrapper = new JPanel(new BorderLayout());
        wrapper.setBackground(new Color(248, 249, 250));
        wrapper.setBorder(new EmptyBorder(0, 50, 0, 50));
        wrapper.add(form, BorderLayout.NORTH);
        
        panel.add(new JScrollPane(wrapper), BorderLayout.CENTER);

        // Actions du formulaire
        btnRetourListe = new JButton("Retour");
        btnEnregistrerModification = new JButton("Enregistrer");
        btnEnregistrerModification.setBackground(new Color(40, 167, 69));
        btnEnregistrerModification.setForeground(Color.WHITE);

        JPanel actions = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        actions.setBackground(new Color(248, 249, 250));
        actions.add(btnRetourListe);
        actions.add(btnEnregistrerModification);
        panel.add(actions, BorderLayout.SOUTH);

        btnRetourListe.addActionListener(e -> cardLayout.show(mainContentPanel, "PARKINGS"));

        return panel;
    }

    private void addFormField(JPanel panel, String label, JComponent component) {
        JLabel lbl = new JLabel(label);
        lbl.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        panel.add(lbl);
        panel.add(component);
    }

    public void afficherFormulaireEdition(Parking parking) {
        this.parkingEnEdition = parking;
        txtNom.setText(parking.getNom());
        txtAdresse.setText(parking.getAdresse() != null ? parking.getAdresse().getRue() : "");
        txtTarif.setText(String.valueOf(parking.getTarif()));
        txtHauteur.setText(String.valueOf(parking.getHauteur()));
        txtPlacesMax.setText(String.valueOf(parking.getNbPlacesMax()));
        txtHeureOuverture.setText(parking.getHeureOuverture() != null ? parking.getHeureOuverture().toString() : "00:00");
        txtHeureFermeture.setText(parking.getHeureFermeture() != null ? parking.getHeureFermeture().toString() : "23:59");
        chkMoto.setSelected(parking.isContientPlacesMoto());
        
        cardLayout.show(mainContentPanel, "EDITION");
    }

    private JPanel createStatsView() {
        JPanel panel = new JPanel();
        panel.setBackground(new Color(248, 249, 250));
        panel.add(new JLabel("Statistiques à implémenter..."));
        return panel;
    }

    public void videListe() {
        if (this.gridPanel != null) {
            this.gridPanel.removeAll();
        }
    }
    
    public void actualiserAffichage() {
        if (this.gridPanel != null) {
            this.gridPanel.revalidate();
            this.gridPanel.repaint();
        }
    }

    // Getters
    public JButton getBtnValiderAjout() { return btnAjouter; }
    public JButton getBtnEnregistrerModification() { return btnEnregistrerModification; }
    public Parking getParkingEnEdition() { return parkingEnEdition; }

    // Getters Formulaire
    public String getNom() { return txtNom.getText().trim(); }
    public String getAdresse() { return txtAdresse.getText().trim(); }
    public double getTarif() { return Double.parseDouble(txtTarif.getText().trim()); }
    public double getHauteur() { return Double.parseDouble(txtHauteur.getText().trim()); }
    public int getPlacesMax() { return Integer.parseInt(txtPlacesMax.getText().trim()); }
    public LocalTime getHeureOuverture() { return LocalTime.parse(txtHeureOuverture.getText()); }
    public LocalTime getHeureFermeture() { return LocalTime.parse(txtHeureFermeture.getText()); }
    public boolean isContientPlacesMoto() { return chkMoto.isSelected(); }
}