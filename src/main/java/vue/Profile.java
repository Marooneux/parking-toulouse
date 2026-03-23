package vue;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import controleur.ControleurProfile;
import modele.Utilisateur;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class Profile extends JPanel {

    private static final long serialVersionUID = 1L;

    private JLayeredPane layeredPane;
    private JPanel mainContentPanel;
    private JPanel sidebarPanel;
    private CardLayout cardLayout;
    private JButton btnToggle;
    private static final int SIDEBAR_WIDTH = 220;

    private JButton btnSidebarInfos;
    private JButton btnSidebarHistorique;
    private JButton btnSidebarVehicules;

    private JLabel lblValNom;
    private JLabel lblValPrenom;
    private JLabel lblValEmail;
    private JButton btnModifierInfos;

    private TemplateSaisie txtEditNom;
    private TemplateSaisie txtEditPrenom;
    private TemplateSaisie txtEditEmail;
    private TemplateSaisie txtEditMdp;
    private JButton btnEnregistrer;
    private JButton btnAnnulerEdit;
    private JButton btnRetour;
    
    private Utilisateur utilisateur;
    public Profile(Utilisateur utilisateur) {
    	this.utilisateur = utilisateur;
        initialize();
        new ControleurProfile(utilisateur, this);
    }

    private void initialize() {
        this.setLayout(new BorderLayout());
        this.setBackground(new Color(248, 249, 250));

        layeredPane = new JLayeredPane();
        this.add(layeredPane, BorderLayout.CENTER);

        // 1. Main Content (CardLayout)
        mainContentPanel = new JPanel();
        cardLayout = new CardLayout();
        mainContentPanel.setLayout(cardLayout);
        mainContentPanel.setBackground(new Color(248, 249, 250));

        // Ajout des "Cartes" (Vues)
        mainContentPanel.add(createInfosView(), "INFOS");
        mainContentPanel.add(createEditionView(), "EDITION");
        mainContentPanel.add(createHistoriqueView(), "HISTORIQUE");

        layeredPane.add(mainContentPanel, JLayeredPane.DEFAULT_LAYER);

        // 2. Sidebar
        sidebarPanel = createSidebar();
        sidebarPanel.setVisible(true);
        layeredPane.add(sidebarPanel, JLayeredPane.PALETTE_LAYER);

        // 3. Toggle Button
        createToggleBtn();

        createTopRightButton(); 

        // 4. Gestion redimensionnement
        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                updateLayoutBounds();
            }
        });
    }

    // --- GESTION DU LAYOUT (Sidebar) ---
    private void createToggleBtn() {
        btnToggle = new JButton("\u2630");
        btnToggle.setFocusPainted(false);
        btnToggle.setBorderPainted(false);
        btnToggle.setContentAreaFilled(false);
        btnToggle.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnToggle.setForeground(Color.LIGHT_GRAY);
        btnToggle.setBounds(10, 10, 50, 40);
        layeredPane.add(btnToggle, JLayeredPane.MODAL_LAYER);
    }

    private void updateLayoutBounds() {
        int w = this.getWidth();
        int h = this.getHeight();

        // Gestion de la sidebar (code existant)
        if (sidebarPanel.isVisible()) {
            sidebarPanel.setBounds(0, 0, SIDEBAR_WIDTH, h);
            mainContentPanel.setBounds(SIDEBAR_WIDTH, 0, w - SIDEBAR_WIDTH, h);
        } else {
            mainContentPanel.setBounds(0, 0, w, h);
        }

        int btnWidth = 200;
        int btnHeight = 40;
        int margin = 20;

        btnRetour.setBounds(w - btnWidth - margin, 10, btnWidth, btnHeight);

        mainContentPanel.revalidate();
        mainContentPanel.repaint();
    }

    public void toggleSidebarState() {
        boolean isVisible = sidebarPanel.isVisible();
        sidebarPanel.setVisible(!isVisible);
        btnToggle.setForeground(!isVisible ? Color.LIGHT_GRAY : new Color(33, 37, 41));
        updateLayoutBounds();
    }

    private JPanel createSidebar() {
        JPanel sidebar = new JPanel();
        sidebar.setLayout(new BoxLayout(sidebar, BoxLayout.Y_AXIS));
        sidebar.setBackground(new Color(33, 37, 41));
        sidebar.setBorder(new EmptyBorder(60, 10, 20, 10));

        JLabel lblMenu = new JLabel("MON COMPTE");
        lblMenu.setForeground(Color.LIGHT_GRAY);
        lblMenu.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lblMenu.setAlignmentX(Component.LEFT_ALIGNMENT);
        lblMenu.setBorder(new EmptyBorder(0, 10, 20, 0));
        sidebar.add(lblMenu);

        btnSidebarInfos = createMenuButton("Mon Profil");
        sidebar.add(btnSidebarInfos);

        sidebar.add(Box.createVerticalStrut(10));

        btnSidebarHistorique = createMenuButton("Historique");
        sidebar.add(btnSidebarHistorique);

        sidebar.add(Box.createVerticalStrut(10));

        btnSidebarVehicules = createMenuButton("Mes véhicules");
        sidebar.add(btnSidebarVehicules);

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

    // --- VUE 1 : INFOS (Lecture Seule) ---
    private JPanel createInfosView() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(248, 249, 250));

        // Header
        JPanel header = new JPanel(new FlowLayout(FlowLayout.LEFT));
        header.setBackground(new Color(248, 249, 250));
        header.setBorder(new EmptyBorder(40, 50, 20, 50));
        
        JLabel icon = new JLabel("\uD83D\uDC64 "); // Icone User
        icon.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 32));
        JLabel title = new JLabel("Mes Informations");
        title.setFont(new Font("Segoe UI", Font.BOLD, 26));
        title.setForeground(new Color(33, 37, 41));
        
        header.add(icon);
        header.add(title);
        panel.add(header, BorderLayout.NORTH);

        // Contenu
        JPanel content = new JPanel(new GridLayout(0, 1, 10, 20));
        content.setBackground(Color.WHITE);
        content.setBorder(new EmptyBorder(30, 50, 30, 50));

        // Utilisation de labels stylisés
        lblValNom = new JLabel(utilisateur.getNom());
        lblValPrenom = new JLabel(utilisateur.getPrenom());
        lblValEmail = new JLabel(utilisateur.getEmail());
        
        addInfoRow(content, "Nom", lblValNom);
        addInfoRow(content, "Prénom", lblValPrenom);
        addInfoRow(content, "Email", lblValEmail);
        
        // Wrapper pour centrer un peu le contenu
        JPanel wrapper = new JPanel(new BorderLayout());
        wrapper.setBackground(new Color(248, 249, 250));
        wrapper.setBorder(new EmptyBorder(0, 50, 50, 50));
        wrapper.add(content, BorderLayout.NORTH);
        
        panel.add(new JScrollPane(wrapper), BorderLayout.CENTER);

        // Bouton Modifier en bas
        btnModifierInfos = new JButton("Modifier mes informations");
        btnModifierInfos.setBackground(new Color(0, 123, 255));
        btnModifierInfos.setForeground(Color.WHITE);
        btnModifierInfos.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnModifierInfos.setPreferredSize(new Dimension(220, 40));
        btnModifierInfos.setFocusPainted(false);
        JPanel footer = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        footer.setBackground(new Color(248, 249, 250));
        footer.setBorder(new EmptyBorder(10, 50, 30, 50));
        footer.add(btnModifierInfos);
        panel.add(footer, BorderLayout.SOUTH);

        return panel;
    }

    private void addInfoRow(JPanel p, String label, JLabel valueLabel) {
        JPanel row = new JPanel(new BorderLayout());
        row.setBackground(Color.WHITE);
        row.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(230,230,230)));
        
        JLabel l = new JLabel(label);
        l.setFont(new Font("Segoe UI", Font.BOLD, 14));
        l.setForeground(Color.GRAY);
        l.setPreferredSize(new Dimension(100, 40));
        
        valueLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        
        row.add(l, BorderLayout.WEST);
        row.add(valueLabel, BorderLayout.CENTER);
        p.add(row);
    }

    // --- VUE 2 : EDITION (Formulaire) ---
    private JPanel createEditionView() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(248, 249, 250));

        // Header
        JPanel header = new JPanel(new FlowLayout(FlowLayout.LEFT));
        header.setBackground(new Color(248, 249, 250));
        header.setBorder(new EmptyBorder(40, 50, 20, 50));
        JLabel title = new JLabel("Modifier le profil");
        title.setFont(new Font("Segoe UI", Font.BOLD, 22));
        header.add(title);
        panel.add(header, BorderLayout.NORTH);

        // Formulaire
        JPanel form = new JPanel(new GridLayout(0, 2, 20, 20));
        form.setBackground(Color.WHITE);
        form.setBorder(new EmptyBorder(30, 30, 30, 30));

        txtEditNom = new TemplateSaisie("Nom", "Nom", false, false);
        txtEditPrenom = new TemplateSaisie("Prenom", "Prenom", false, false);
        txtEditEmail = new TemplateSaisie("Email", "email@exemple.fr", false, false);
        txtEditMdp = new TemplateSaisie("Mot de passe", "", true, false);

        addFormField(form, "Nom :", txtEditNom);
        addFormField(form, "Prénom :", txtEditPrenom);
        addFormField(form, "Email :", txtEditEmail);
        addFormField(form, "Mot de passe :", txtEditMdp);

        JPanel wrapper = new JPanel(new BorderLayout());
        wrapper.setBackground(new Color(248, 249, 250));
        wrapper.setBorder(new EmptyBorder(0, 50, 0, 50));
        wrapper.add(form, BorderLayout.NORTH);
        panel.add(new JScrollPane(wrapper), BorderLayout.CENTER);

        // Actions
        btnAnnulerEdit = new JButton("Annuler");
        btnAnnulerEdit.setBackground(new Color(108, 117, 125));
        btnAnnulerEdit.setForeground(Color.WHITE);
        
        btnEnregistrer = new JButton("Enregistrer");
        btnEnregistrer.setBackground(new Color(40, 167, 69));
        btnEnregistrer.setForeground(Color.WHITE);
        
        JPanel footer = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        footer.setBackground(new Color(248, 249, 250));
        footer.setBorder(new EmptyBorder(20, 50, 30, 50));
        footer.add(btnAnnulerEdit);
        footer.add(btnEnregistrer);
        panel.add(footer, BorderLayout.SOUTH);

        return panel;
    }
    
    private void addFormField(JPanel p, String label, JComponent field) {
        JLabel l = new JLabel(label);
        l.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        p.add(l);
        p.add(field);
    }

    // --- VUE 3 : HISTORIQUE ---
    private JPanel createHistoriqueView() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(248, 249, 250));
        
        JPanel header = new JPanel(new FlowLayout(FlowLayout.LEFT));
        header.setBackground(new Color(248, 249, 250));
        header.setBorder(new EmptyBorder(40, 50, 20, 50));
        JLabel title = new JLabel("Historique des activités");
        title.setFont(new Font("Segoe UI", Font.BOLD, 22));
        header.add(title);
        panel.add(header, BorderLayout.NORTH);

        HistoriquePanel panelHistorique = new HistoriquePanel(this.utilisateur);
        return panelHistorique;
        
    }

    // --- METHODES PUBLIQUES POUR LE CONTROLEUR ---

    public void showInfosTab() {
        cardLayout.show(mainContentPanel, "INFOS");
    }
    
    public void showEditionTab() {
        cardLayout.show(mainContentPanel, "EDITION");
    }
    
    public void showHistoriqueTab() {
        cardLayout.show(mainContentPanel, "HISTORIQUE");
    }

    // Remplir la vue INFO
    public void updateInfoDisplay(String nom, String prenom, String email) {
        lblValNom.setText(nom);
        lblValPrenom.setText(prenom);
        lblValEmail.setText(email);
    }

    // Pré-remplir la vue EDITION
    public void fillEditForm(String nom, String prenom, String email, String mdp) {
        txtEditNom.setText(nom);
        txtEditPrenom.setText(prenom);
        txtEditEmail.setText(email);
        txtEditMdp.setText("");
    }
    
    private void createTopRightButton() {
        btnRetour = new JButton("Menu principal");
        btnRetour.setBackground(new Color(0, 0, 0));
        btnRetour.setForeground(Color.WHITE);
        btnRetour.setFocusPainted(false);
        btnRetour.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnRetour.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        layeredPane.add(btnRetour, JLayeredPane.MODAL_LAYER);
    }

    // Getters Formulaire
    public String getNomInput() { return txtEditNom.getText(); }
    public String getPrenomInput() { return txtEditPrenom.getText(); }
    public String getEmailInput() { return txtEditEmail.getText(); }
    public String getMdpInput() { return new String(txtEditMdp.getPassword()); }

    // Listeners
    public void addEditListener(ActionListener l) { btnModifierInfos.addActionListener(l); }
    public void addSaveListener(ActionListener l) { btnEnregistrer.addActionListener(l); }
    public void addAnnulerEditListener(ActionListener l) { btnAnnulerEdit.addActionListener(l); }
    public void addMenuInfosListener(ActionListener l) { btnSidebarInfos.addActionListener(l); }
    public void addMenuHistoriqueListener(ActionListener l) { btnSidebarHistorique.addActionListener(l); }
    public void addMenuVehiculesListener(ActionListener l) { btnSidebarVehicules.addActionListener(l); }
    public void addRetourListener(ActionListener l) { btnRetour.addActionListener(l); }
    public void addToggleSidebarListener(ActionListener l) { btnToggle.addActionListener(l); }

    public JButton getBtnModifierInfos() { return btnModifierInfos; }
    public JButton getBtnEnregistrer() { return btnEnregistrer; }
    public JButton getBtnAnnulerEdit() { return btnAnnulerEdit; }
    public JButton getBtnSidebarInfos() { return btnSidebarInfos; }
    public JButton getBtnSidebarHistorique() { return btnSidebarHistorique; }
    public JButton getBtnSidebarVehicules() { return btnSidebarVehicules; }
    public JButton getBtnRetour() { return btnRetour; }
    public JButton getBtnToggle() { return btnToggle; }
    
    public void afficherMessage(String msg) {
        JOptionPane.showMessageDialog(this, msg);
    }
}