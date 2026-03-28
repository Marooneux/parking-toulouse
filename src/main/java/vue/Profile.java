package vue;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import controleur.ControleurProfile;
import modele.Utilisateur;
import ui.theme.DefaultTheme;

public class Profile extends JPanel {

	private static final long serialVersionUID = 8274881298851864047L;
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
		this.initialize();
		new ControleurProfile(utilisateur, this);
	}

	private void initialize() {
		this.setLayout(new BorderLayout());
		this.setBackground(DefaultTheme.BACKGROUND_COLOR);

		this.layeredPane = new JLayeredPane();
		this.add(this.layeredPane, BorderLayout.CENTER);

		// 1. Main Content (CardLayout)
		this.mainContentPanel = new JPanel();
		this.cardLayout = new CardLayout();
		this.mainContentPanel.setLayout(this.cardLayout);
		this.mainContentPanel.setBackground(DefaultTheme.BACKGROUND_COLOR);

		// Ajout des "Cartes" (Vues)
		this.mainContentPanel.add(this.createInfosView(), "INFOS");
		this.mainContentPanel.add(this.createEditionView(), "EDITION");
		this.mainContentPanel.add(this.createHistoriqueView(), "HISTORIQUE");

		this.layeredPane.add(this.mainContentPanel, JLayeredPane.DEFAULT_LAYER);

		// 2. Sidebar
		this.sidebarPanel = this.createSidebar();
		this.sidebarPanel.setVisible(true);
		this.layeredPane.add(this.sidebarPanel, JLayeredPane.PALETTE_LAYER);

		// 3. Toggle Button
		this.createToggleBtn();

		this.createTopRightButton();

		// 4. Gestion redimensionnement
		this.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {
				Profile.this.updateLayoutBounds();
			}
		});
	}

	// --- GESTION DU LAYOUT (Sidebar) ---
	private void createToggleBtn() {
		this.btnToggle = new JButton("\u2630");
		this.btnToggle.setFocusPainted(false);
		this.btnToggle.setBorderPainted(false);
		this.btnToggle.setContentAreaFilled(false);
		this.btnToggle.setCursor(new Cursor(Cursor.HAND_CURSOR));
		this.btnToggle.setForeground(Color.LIGHT_GRAY);
		this.btnToggle.setBounds(10, 10, 50, 40);
		this.layeredPane.add(this.btnToggle, JLayeredPane.MODAL_LAYER);
	}

	private void updateLayoutBounds() {
		int w = this.getWidth();
		int h = this.getHeight();

		// Gestion de la sidebar (code existant)
		if (this.sidebarPanel.isVisible()) {
			this.sidebarPanel.setBounds(0, 0, SIDEBAR_WIDTH, h);
			this.mainContentPanel.setBounds(SIDEBAR_WIDTH, 0, w - SIDEBAR_WIDTH, h);
		} else {
			this.mainContentPanel.setBounds(0, 0, w, h);
		}

		int btnWidth = 200;
		int btnHeight = 40;
		int margin = 20;

		this.btnRetour.setBounds(w - btnWidth - margin, 10, btnWidth, btnHeight);

		this.mainContentPanel.revalidate();
		this.mainContentPanel.repaint();
	}

	public void toggleSidebarState() {
		boolean isVisible = this.sidebarPanel.isVisible();
		this.sidebarPanel.setVisible(!isVisible);
		this.btnToggle.setForeground(!isVisible ? Color.LIGHT_GRAY : DefaultTheme.TEXT_COLOR);
		this.updateLayoutBounds();
	}

	private JPanel createSidebar() {
		JPanel sidebar = new JPanel();
		sidebar.setLayout(new BoxLayout(sidebar, BoxLayout.Y_AXIS));
		sidebar.setBackground(DefaultTheme.TEXT_COLOR);
		sidebar.setBorder(new EmptyBorder(60, 10, 20, 10));

		JLabel lblMenu = new JLabel("MON COMPTE");
		lblMenu.setForeground(Color.LIGHT_GRAY);
		lblMenu.setFont(DefaultTheme.FONT_BUTTON);
		lblMenu.setAlignmentX(Component.LEFT_ALIGNMENT);
		lblMenu.setBorder(new EmptyBorder(0, 10, 20, 0));
		sidebar.add(lblMenu);

		this.btnSidebarInfos = this.createMenuButton("Mon Profil");
		sidebar.add(this.btnSidebarInfos);

		sidebar.add(Box.createVerticalStrut(10));

		this.btnSidebarHistorique = this.createMenuButton("Historique");
		sidebar.add(this.btnSidebarHistorique);

		sidebar.add(Box.createVerticalStrut(10));

		this.btnSidebarVehicules = this.createMenuButton("Mes véhicules");
		sidebar.add(this.btnSidebarVehicules);

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
		btn.setFont(DefaultTheme.FONT_BODY);
		btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
		return btn;
	}

	// --- VUE 1 : INFOS (Lecture Seule) ---
	private JPanel createInfosView() {
		JPanel panel = new JPanel(new BorderLayout());
		panel.setBackground(DefaultTheme.BACKGROUND_COLOR);

		// Header
		JPanel header = new JPanel(new FlowLayout(FlowLayout.LEFT));
		header.setBackground(DefaultTheme.BACKGROUND_COLOR);
		header.setBorder(new EmptyBorder(40, 50, 20, 50));

		JLabel icon = new JLabel("\uD83D\uDC64 "); // Icone User
		icon.setFont(DefaultTheme.FONT_ICON);
		JLabel title = new JLabel("Mes Informations");
		title.setFont(DefaultTheme.FONT_TITLE);
		title.setForeground(DefaultTheme.TEXT_COLOR);

		header.add(icon);
		header.add(title);
		panel.add(header, BorderLayout.NORTH);

		// Contenu
		JPanel content = new JPanel(new GridLayout(0, 1, 10, 20));
		content.setBackground(Color.WHITE);
		content.setBorder(new EmptyBorder(30, 50, 30, 50));

		// Utilisation de labels stylisés
		this.lblValNom = new JLabel(this.utilisateur.getNom());
		this.lblValPrenom = new JLabel(this.utilisateur.getPrenom());
		this.lblValEmail = new JLabel(this.utilisateur.getEmail());

		this.addInfoRow(content, "Nom", this.lblValNom);
		this.addInfoRow(content, "Prénom", this.lblValPrenom);
		this.addInfoRow(content, "Email", this.lblValEmail);

		// Wrapper pour centrer un peu le contenu
		JPanel wrapper = new JPanel(new BorderLayout());
		wrapper.setBackground(DefaultTheme.BACKGROUND_COLOR);
		wrapper.setBorder(new EmptyBorder(0, 50, 50, 50));
		wrapper.add(content, BorderLayout.NORTH);

		panel.add(new JScrollPane(wrapper), BorderLayout.CENTER);

		// Bouton Modifier en bas
		this.btnModifierInfos = new JButton("Modifier mes informations");
		this.btnModifierInfos.setBackground(new Color(0, 123, 255));
		this.btnModifierInfos.setForeground(Color.WHITE);
		this.btnModifierInfos.setFont(DefaultTheme.FONT_BUTTON);
		this.btnModifierInfos.setPreferredSize(new Dimension(220, 40));
		this.btnModifierInfos.setFocusPainted(false);
		JPanel footer = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		footer.setBackground(DefaultTheme.BACKGROUND_COLOR);
		footer.setBorder(new EmptyBorder(10, 50, 30, 50));
		footer.add(this.btnModifierInfos);
		panel.add(footer, BorderLayout.SOUTH);

		return panel;
	}

	private void addInfoRow(JPanel p, String label, JLabel valueLabel) {
		JPanel row = new JPanel(new BorderLayout());
		row.setBackground(Color.WHITE);
		row.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(230, 230, 230)));

		JLabel l = new JLabel(label);
		l.setFont(DefaultTheme.FONT_BUTTON);
		l.setForeground(Color.GRAY);
		l.setPreferredSize(new Dimension(100, 40));

		valueLabel.setFont(DefaultTheme.FONT_LABEL_BIG);

		row.add(l, BorderLayout.WEST);
		row.add(valueLabel, BorderLayout.CENTER);
		p.add(row);
	}

	// --- VUE 2 : EDITION (Formulaire) ---
	private JPanel createEditionView() {
		JPanel panel = new JPanel(new BorderLayout());
		panel.setBackground(DefaultTheme.BACKGROUND_COLOR);

		// Header
		JPanel header = new JPanel(new FlowLayout(FlowLayout.LEFT));
		header.setBackground(DefaultTheme.BACKGROUND_COLOR);
		header.setBorder(new EmptyBorder(40, 50, 20, 50));
		JLabel title = new JLabel("Modifier le profil");
		title.setFont(DefaultTheme.FONT_HERO_TITLE);
		header.add(title);
		panel.add(header, BorderLayout.NORTH);

		// Formulaire
		JPanel form = new JPanel(new GridLayout(0, 2, 20, 20));
		form.setBackground(Color.WHITE);
		form.setBorder(new EmptyBorder(30, 30, 30, 30));

		this.txtEditNom = new TemplateSaisie("Nom", "Nom", false, false);
		this.txtEditPrenom = new TemplateSaisie("Prenom", "Prenom", false, false);
		this.txtEditEmail = new TemplateSaisie("Email", "email@exemple.fr", false, false);
		this.txtEditMdp = new TemplateSaisie("Mot de passe", "", true, false);

		this.addFormField(form, "Nom :", this.txtEditNom);
		this.addFormField(form, "Prénom :", this.txtEditPrenom);
		this.addFormField(form, "Email :", this.txtEditEmail);
		this.addFormField(form, "Mot de passe :", this.txtEditMdp);

		JPanel wrapper = new JPanel(new BorderLayout());
		wrapper.setBackground(DefaultTheme.BACKGROUND_COLOR);
		wrapper.setBorder(new EmptyBorder(0, 50, 0, 50));
		wrapper.add(form, BorderLayout.NORTH);
		panel.add(new JScrollPane(wrapper), BorderLayout.CENTER);

		// Actions
		this.btnAnnulerEdit = new JButton("Annuler");
		this.btnAnnulerEdit.setBackground(DefaultTheme.SUBTEXT_COLOR);
		this.btnAnnulerEdit.setForeground(Color.WHITE);

		this.btnEnregistrer = new JButton("Enregistrer");
		this.btnEnregistrer.setBackground(new Color(40, 167, 69));
		this.btnEnregistrer.setForeground(Color.WHITE);

		JPanel footer = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		footer.setBackground(DefaultTheme.BACKGROUND_COLOR);
		footer.setBorder(new EmptyBorder(20, 50, 30, 50));
		footer.add(this.btnAnnulerEdit);
		footer.add(this.btnEnregistrer);
		panel.add(footer, BorderLayout.SOUTH);

		return panel;
	}

	private void addFormField(JPanel p, String label, JComponent field) {
		JLabel l = new JLabel(label);
		l.setFont(DefaultTheme.FONT_BODY);
		p.add(l);
		p.add(field);
	}

	// --- VUE 3 : HISTORIQUE ---
	private JPanel createHistoriqueView() {
		JPanel panel = new JPanel(new BorderLayout());
		panel.setBackground(DefaultTheme.BACKGROUND_COLOR);

		JPanel header = new JPanel(new FlowLayout(FlowLayout.LEFT));
		header.setBackground(DefaultTheme.BACKGROUND_COLOR);
		header.setBorder(new EmptyBorder(40, 50, 20, 50));
		JLabel title = new JLabel("Historique des activités");
		title.setFont(DefaultTheme.FONT_HERO_TITLE);
		header.add(title);
		panel.add(header, BorderLayout.NORTH);

		HistoriquePanel panelHistorique = new HistoriquePanel(this.utilisateur);
		return panelHistorique;

	}

	// --- METHODES PUBLIQUES POUR LE CONTROLEUR ---

	public void showInfosTab() {
		this.cardLayout.show(this.mainContentPanel, "INFOS");
	}

	public void showEditionTab() {
		this.cardLayout.show(this.mainContentPanel, "EDITION");
	}

	public void showHistoriqueTab() {
		this.cardLayout.show(this.mainContentPanel, "HISTORIQUE");
	}

	// Remplir la vue INFO
	public void updateInfoDisplay(String nom, String prenom, String email) {
		this.lblValNom.setText(nom);
		this.lblValPrenom.setText(prenom);
		this.lblValEmail.setText(email);
	}

	// Pré-remplir la vue EDITION
	public void fillEditForm(String nom, String prenom, String email, String mdp) {
		this.txtEditNom.setText(nom);
		this.txtEditPrenom.setText(prenom);
		this.txtEditEmail.setText(email);
		this.txtEditMdp.setText("");
	}

	private void createTopRightButton() {
		this.btnRetour = new JButton("Menu principal");
		this.btnRetour.setBackground(Color.BLACK);
		this.btnRetour.setForeground(Color.WHITE);
		this.btnRetour.setFocusPainted(false);
		this.btnRetour.setFont(DefaultTheme.FONT_BUTTON);
		this.btnRetour.setCursor(new Cursor(Cursor.HAND_CURSOR));

		this.layeredPane.add(this.btnRetour, JLayeredPane.MODAL_LAYER);
	}

	// Getters Formulaire
	public String getNomInput() {
		return this.txtEditNom.getText();
	}

	public String getPrenomInput() {
		return this.txtEditPrenom.getText();
	}

	public String getEmailInput() {
		return this.txtEditEmail.getText();
	}

	public String getMdpInput() {
		return new String(this.txtEditMdp.getPassword());
	}

	// Listeners
	public void addEditListener(ActionListener l) {
		this.btnModifierInfos.addActionListener(l);
	}

	public void addSaveListener(ActionListener l) {
		this.btnEnregistrer.addActionListener(l);
	}

	public void addAnnulerEditListener(ActionListener l) {
		this.btnAnnulerEdit.addActionListener(l);
	}

	public void addMenuInfosListener(ActionListener l) {
		this.btnSidebarInfos.addActionListener(l);
	}

	public void addMenuHistoriqueListener(ActionListener l) {
		this.btnSidebarHistorique.addActionListener(l);
	}

	public void addMenuVehiculesListener(ActionListener l) {
		this.btnSidebarVehicules.addActionListener(l);
	}

	public void addRetourListener(ActionListener l) {
		this.btnRetour.addActionListener(l);
	}

	public void addToggleSidebarListener(ActionListener l) {
		this.btnToggle.addActionListener(l);
	}

	public JButton getBtnModifierInfos() {
		return this.btnModifierInfos;
	}

	public JButton getBtnEnregistrer() {
		return this.btnEnregistrer;
	}

	public JButton getBtnAnnulerEdit() {
		return this.btnAnnulerEdit;
	}

	public JButton getBtnSidebarInfos() {
		return this.btnSidebarInfos;
	}

	public JButton getBtnSidebarHistorique() {
		return this.btnSidebarHistorique;
	}

	public JButton getBtnSidebarVehicules() {
		return this.btnSidebarVehicules;
	}

	public JButton getBtnRetour() {
		return this.btnRetour;
	}

	public JButton getBtnToggle() {
		return this.btnToggle;
	}

	public void afficherMessage(String msg) {
		JOptionPane.showMessageDialog(this, msg);
	}
}