package vue.adminParking;

import javax.swing.*; 
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.ActionListener;
import java.time.LocalTime;
import java.util.function.Consumer;

import controleur.ControleurAccueilAdminParking;
import controleur.ControleurStatistique;
import modele.Parking;
import ui.theme.DefaultTheme;
import vue.TemplateSaisie;

public class Accueil extends JPanel {

    private static final long serialVersionUID = 1L;

    private JLayeredPane layeredPane;
    private JPanel mainContentPanel;
    private JPanel sidebarPanel;
    private CardLayout cardLayout;
    private JButton btnToggle;
    private static final int SIDEBAR_WIDTH = 200;
    private Statistique statsPanel;

    private JPanel gridPanel;
    private JButton btnAjouter;
    private JButton btnSidebarParkings;
    private JButton btnSidebarStats;
    private JButton btnRetourListe;

    private TemplateSaisie txtNom;
    private TemplateSaisie txtAdresse;
    private TemplateSaisie txtTarif;
    private TemplateSaisie txtHauteur;
    private TemplateSaisie txtPlacesMax;
    private TemplateSaisie txtHeureOuverture;
    private TemplateSaisie txtHeureFermeture;
    private JCheckBox chkMoto;
    
    private JButton btnEnregistrerModification;
    
    private Parking parkingEnEdition;

    public Accueil(int idAdmin) {
    	initialize();
    	new ControleurAccueilAdminParking(this, idAdmin);
    	//statistique
    	new ControleurStatistique(statsPanel);
    }

    private void initialize() {
    	
        this.setLayout(new BorderLayout());
        this.setBackground(DefaultTheme.BACKGROUND_COLOR);
        this.setPreferredSize(new Dimension(1500, 800));
        
        layeredPane = new JLayeredPane();
        layeredPane.setLayout(null);
        this.add(layeredPane, BorderLayout.CENTER);

        mainContentPanel = new JPanel();
        cardLayout = new CardLayout();
        mainContentPanel.setLayout(cardLayout);
        mainContentPanel.setBackground(DefaultTheme.BACKGROUND_COLOR);

        JPanel viewParkings = createParkingView();
        mainContentPanel.add(viewParkings, "PARKINGS");

        JPanel viewStats = createStatsView();
        mainContentPanel.add(viewStats, "STATS");
        
        // Initialisation de la vue d'édition
        JPanel viewEdition = createEditionView();
        mainContentPanel.add(viewEdition, "EDITION");

        layeredPane.add(mainContentPanel, JLayeredPane.DEFAULT_LAYER);

        sidebarPanel = createSidebar();
        sidebarPanel.setVisible(true);
        layeredPane.add(sidebarPanel, JLayeredPane.PALETTE_LAYER);

        createToggleBtn();

        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                updateLayoutBounds();
            }
        });

        updateLayoutBounds();
    }

    private void createToggleBtn() {
        btnToggle = new JButton("\u2630");
        btnToggle.setFont(DefaultTheme.FONT_SYMBOL);
        btnToggle.setFocusPainted(false);
        btnToggle.setBorderPainted(false);
        btnToggle.setContentAreaFilled(false);
        btnToggle.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnToggle.setForeground(Color.LIGHT_GRAY);
        btnToggle.setBounds(10, 10, 50, 40);
        btnToggle.setMargin(new Insets(0, 0, 0, 0));
        layeredPane.add(btnToggle, JLayeredPane.MODAL_LAYER);
    }

    private void updateLayoutBounds() {
        int w = this.getWidth();
        int h = this.getHeight();

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
        btnToggle.setForeground(!isVisible ? Color.LIGHT_GRAY : DefaultTheme.BACKGROUND_HERO);
        updateLayoutBounds();
    }

    private JPanel createSidebar() {
        JPanel sidebar = new JPanel();
        sidebar.setLayout(new BoxLayout(sidebar, BoxLayout.Y_AXIS));
        sidebar.setBackground(DefaultTheme.BACKGROUND_HERO);
        sidebar.setBorder(new EmptyBorder(60, 10, 20, 10));

        JLabel lblMenu = new JLabel("MENU");
        lblMenu.setForeground(Color.LIGHT_GRAY);
        lblMenu.setFont(DefaultTheme.FONT_BUTTON);
        lblMenu.setAlignmentX(Component.LEFT_ALIGNMENT);
        lblMenu.setBorder(new EmptyBorder(0, 10, 20, 0));
        sidebar.add(lblMenu);

        btnSidebarParkings = UIButtons.sidebar("Liste Parkings");
        sidebar.add(btnSidebarParkings);

        sidebar.add(Box.createVerticalStrut(10));

        btnSidebarStats = UIButtons.sidebar("Statistiques");
        sidebar.add(btnSidebarStats);

        sidebar.add(Box.createVerticalGlue());
        return sidebar;
    }

    private JPanel createParkingView() {
        JPanel panel = new JPanel(new BorderLayout(0, 0));
        panel.setBackground(DefaultTheme.BACKGROUND_COLOR);

        JPanel headerPanel = new JPanel();
        headerPanel.setLayout(new BoxLayout(headerPanel, BoxLayout.Y_AXIS));
        headerPanel.setBackground(DefaultTheme.BACKGROUND_COLOR);
        headerPanel.setBorder(new EmptyBorder(25, 70, 30, 50));

        JPanel titleContainer = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        titleContainer.setBackground(DefaultTheme.BACKGROUND_COLOR);
        
        JLabel iconCar = new JLabel("\uD83C\uDD7F\uFE0F "); 
        iconCar.setFont(DefaultTheme.FONT_ICON);
        
        JLabel lblTitle = new JLabel("Gestion des Parkings");
        lblTitle.setFont(DefaultTheme.FONT_TITLE);
        lblTitle.setForeground(DefaultTheme.TEXT_COLOR);

        titleContainer.add(iconCar);
        titleContainer.add(lblTitle);

        JLabel lblSubtitle = new JLabel("Gérez vos parkings");
        lblSubtitle.setFont(DefaultTheme.FONT_BODY);
        lblSubtitle.setForeground(new Color(108, 117, 125));
        lblSubtitle.setBorder(new EmptyBorder(5, 5, 0, 0));

        headerPanel.add(titleContainer);
        headerPanel.add(lblSubtitle);
        panel.add(headerPanel, BorderLayout.NORTH);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBorder(null);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        scrollPane.getViewport().setBackground(DefaultTheme.BACKGROUND_COLOR);

        gridPanel = new JPanel();
        gridPanel.setBackground(DefaultTheme.BACKGROUND_COLOR);
        gridPanel.setLayout(new GridLayout(0, 3, 25, 25));
        gridPanel.setBorder(new EmptyBorder(0, 50, 50, 50));

        scrollPane.setViewportView(gridPanel);
        panel.add(scrollPane, BorderLayout.CENTER);

        btnAjouter = UIButtons.primary("Ajouter un parking");
        btnAjouter.setPreferredSize(new Dimension(150, 30));
        

        JPanel actions = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        actions.setBackground(DefaultTheme.BACKGROUND_COLOR);
        actions.setBorder(new EmptyBorder(10, 50, 30, 50));
        actions.add(btnAjouter);

        panel.add(actions, BorderLayout.SOUTH);

        return panel;
    }

    private JPanel createEditionView() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(DefaultTheme.BACKGROUND_COLOR);
        panel.setBorder(new EmptyBorder(40, 60, 40, 60));

        JLabel lblTitre = new JLabel("Modifier le parking");
        lblTitre.setFont(DefaultTheme.FONT_HERO_TITLE);
        lblTitre.setAlignmentX(Component.LEFT_ALIGNMENT);
        panel.add(lblTitre);
        panel.add(Box.createVerticalStrut(20));

        // Initialisation des champs
        txtNom = new TemplateSaisie("Nom du parking", "", false, false);
        txtAdresse = new TemplateSaisie("Adresse", "", false, false);
        txtTarif = new TemplateSaisie("Tarif horaire", "", false, false);
        txtHauteur = new TemplateSaisie("Hauteur max", "", false, false);
        txtPlacesMax = new TemplateSaisie("Places max", "", false, false);
        txtHeureOuverture = new TemplateSaisie("Heure ouverture", "", false, false);
        txtHeureFermeture = new TemplateSaisie("Heure fermeture", "", false, false);
        chkMoto = new JCheckBox("Accepte les motos");
        chkMoto.setBackground(DefaultTheme.BACKGROUND_COLOR);

        // Construction du formulaire
        panel.add(UIForm.row("Nom du parking :", txtNom));
        panel.add(UIForm.row("Adresse :", txtAdresse));
        panel.add(UIForm.row("Tarif Horaire :", txtTarif));
        panel.add(UIForm.row("Hauteur Max :", txtHauteur));
        panel.add(UIForm.row("Places Max :", txtPlacesMax));
        panel.add(UIForm.row("Heure Ouverture :", txtHeureOuverture));
        panel.add(UIForm.row("Heure Fermeture :", txtHeureFermeture));

        
        JPanel pnlMoto = new JPanel(new FlowLayout(FlowLayout.LEFT));
        pnlMoto.setBackground(DefaultTheme.BACKGROUND_COLOR);
        pnlMoto.add(chkMoto);
        pnlMoto.setAlignmentX(Component.LEFT_ALIGNMENT);
        pnlMoto.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        panel.add(pnlMoto);

        panel.add(Box.createVerticalStrut(20));

        // Boutons
        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        btnPanel.setBackground(DefaultTheme.BACKGROUND_COLOR);
        btnPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        btnPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));

        btnRetourListe = UIButtons.danger("Annuler");
        btnEnregistrerModification = UIButtons.primary("Enregistrer");

        btnPanel.add(btnRetourListe);
        btnPanel.add(btnEnregistrerModification);
        panel.add(btnPanel);

        return panel;
    }


    public void addParking(Parking parking, Consumer<Parking> onSelect, Consumer<Parking> onModify, Consumer<Parking> onDelete) {
        AdminParkingPanel panelInfo = new AdminParkingPanel(parking, onSelect);

        JPanel container = new JPanel(new BorderLayout());
        container.setBackground(Color.WHITE);
        container.add(panelInfo, BorderLayout.CENTER);

        JButton btnSupprimer = UIButtons.danger("Supprimer");
        btnSupprimer.setFocusPainted(false);
        btnSupprimer.setPreferredSize(new Dimension(100, 30));
        
        JButton btnModifier = UIButtons.primary("Modifier");
        btnModifier.setFocusPainted(false);
        btnModifier.setPreferredSize(new Dimension(100, 30));

        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        bottomPanel.setBackground(Color.WHITE);
        bottomPanel.add(btnSupprimer);
        bottomPanel.add(btnModifier);
        
        container.add(bottomPanel, BorderLayout.SOUTH);

        btnModifier.addActionListener(e -> {
            afficherFormulaireEdition(parking);
            if(onModify != null) onModify.accept(parking);
        });
        
        btnSupprimer.addActionListener(e -> onDelete.accept(parking));

        gridPanel.add(container);
    }

    public void afficherFormulaireEdition(Parking parking) {
        // Not yet implemented
    }

    private JPanel createStatsView() {
        statsPanel = new Statistique();
        return statsPanel;
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
    public JButton getBtnSidebarParkings() { return btnSidebarParkings; }
    public JButton getBtnSidebarStats() { return btnSidebarStats; }
    public JButton getBtnRetourListe() { return btnRetourListe; }
    public JButton getBtnToggle() { return btnToggle; }

    // Getters Formulaire
    public String getNom() { return txtNom.getText().trim(); }
    public String getAdresse() { return txtAdresse.getText().trim(); }
    public double getTarif() { return Double.parseDouble(txtTarif.getText().trim()); }
    public double getHauteur() { return Double.parseDouble(txtHauteur.getText().trim()); }
    public int getPlacesMax() { return Integer.parseInt(txtPlacesMax.getText().trim()); }
    public LocalTime getHeureOuverture() { return LocalTime.parse(txtHeureOuverture.getText()); }
    public LocalTime getHeureFermeture() { return LocalTime.parse(txtHeureFermeture.getText()); }
    public boolean isContientPlacesMoto() { return chkMoto.isSelected(); }

    // Exposed listeners
    public void addToggleListener(ActionListener listener) { btnToggle.addActionListener(listener); }
    public void addMenuParkingsListener(ActionListener listener) { btnSidebarParkings.addActionListener(listener); }
    public void addMenuStatsListener(ActionListener listener) { btnSidebarStats.addActionListener(listener); }
    public void addAjouterListener(ActionListener listener) { btnAjouter.addActionListener(listener); }
    public void addRetourListener(ActionListener listener) { 
        if(btnRetourListe != null) btnRetourListe.addActionListener(listener); 
    }
    public void addShowListener(java.awt.event.ComponentListener l) {
        this.addComponentListener(l);
    }

    public void showParkings() { cardLayout.show(mainContentPanel, "PARKINGS"); }
    public void showStats() { cardLayout.show(mainContentPanel, "STATS"); }
    public void showEdition() { cardLayout.show(mainContentPanel, "EDITION"); }
    public void toggleSidebar() { toggleSidebarState(); }
}