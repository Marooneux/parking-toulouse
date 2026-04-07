package vue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.RenderingHints;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import controleur.ControleurTicketVoirie;
import modele.ZoneVoirie;
import ui.theme.DefaultTheme;

public class TicketVoirie extends JPanel {

	private static final long serialVersionUID = -2282953775737224594L;
	private ZoneVoirie zone;
	private String immatriculation;
	private int duree;
	private String moyenPaiement;
	private JLabel nomZone;
	private JButton btnPaiement;

	private Color couleur;

	public TicketVoirie(ZoneVoirie zone, String immatriculation, int duree, String moyenPaiement) {
		this.zone = zone;
		this.immatriculation = immatriculation;
		this.duree = duree;
		this.moyenPaiement = moyenPaiement;

		this.couleur = ZoneVoirie.convertirCouleur(this.zone.getCouleur());

		// 2. On applique la couleur au texte de la zone
		this.nomZone = new JLabel(this.zone.getCouleur());
		this.nomZone.setForeground(this.couleur);

		this.setBackground(Color.WHITE);
		this.setBorder(new EmptyBorder(20, 20, 20, 20));
		this.setLayout(new BorderLayout(0, 0));

		JPanel panelHeader = new JPanel();
		panelHeader.setBackground(Color.WHITE);
		this.add(panelHeader, BorderLayout.NORTH);
		panelHeader.setLayout(new BorderLayout(20, 0));

		// 3. Configuration du Rond P (défini plus bas)
		this.iconCircle.setOpaque(false);
		this.iconCircle.setPreferredSize(new Dimension(60, 60));
		panelHeader.add(this.iconCircle, BorderLayout.WEST);

		JPanel panelTextHeader = new JPanel();
		panelTextHeader.setBackground(Color.WHITE);
		panelHeader.add(panelTextHeader, BorderLayout.CENTER);
		panelTextHeader.setLayout(new GridLayout(2, 1, 0, 0));

		JLabel lblTitre = new JLabel("Récapitulatif de stationnement");
		lblTitre.setForeground(DefaultTheme.COLOR_TEXT_PRIMARY);
		lblTitre.setFont(DefaultTheme.FONT_TITLE_L);
		panelTextHeader.add(lblTitre);

		JLabel lblSousTitre = new JLabel("Veuillez conserver ce récapitulatif jusqu'à votre départ");
		lblSousTitre.setForeground(DefaultTheme.COLOR_TEXT_MUTED);
		lblSousTitre.setFont(DefaultTheme.FONT_BODY);
		panelTextHeader.add(lblSousTitre);

		JPanel panelCenterContainer = new JPanel();
		panelCenterContainer.setBackground(Color.WHITE);
		panelCenterContainer.setBorder(new EmptyBorder(20, 80, 10, 80));
		this.add(panelCenterContainer, BorderLayout.CENTER);
		panelCenterContainer.setLayout(new BorderLayout(0, 0));

		JPanel panelCard = new JPanel();
		panelCard.setBorder(new LineBorder(DefaultTheme.COLOR_BORDER_BUTTON, 1, true));
		panelCard.setBackground(Color.WHITE);
		panelCenterContainer.add(panelCard);
		panelCard.setLayout(new BorderLayout(0, 0));

		JPanel panelInfoGrid = new JPanel();
		panelInfoGrid.setBackground(Color.WHITE);
		panelInfoGrid.setBorder(new EmptyBorder(20, 30, 20, 30));
		panelCard.add(panelInfoGrid, BorderLayout.CENTER);
		panelInfoGrid.setLayout(new GridLayout(6, 1, 0, 10));

		this.createInfoRow(panelInfoGrid, "Numéro de Ticket :", new JLabel("#V-00001"));
		this.createInfoRow(panelInfoGrid, "Zone :", this.nomZone); // Ici le JLabel est déjà coloré
		this.createInfoRow(panelInfoGrid, "Immatriculation :", new JLabel(this.immatriculation));
		this.createInfoRow(panelInfoGrid, "Heure d'arrivée :", new JLabel(ControleurTicketVoirie.getHeureActuelle()));
		this.createInfoRow(panelInfoGrid, "Heure départ max :",
				new JLabel(ControleurTicketVoirie.calculerHeureDepart(this.duree)));
		this.createInfoRow(panelInfoGrid, "Moyen de paiement :", new JLabel(this.moyenPaiement));

		JPanel panelFooter = new JPanel();
		panelFooter.setBackground(Color.WHITE);
		panelFooter.setBorder(new EmptyBorder(10, 0, 10, 0));
		this.add(panelFooter, BorderLayout.SOUTH);
		panelFooter.setLayout(new GridLayout(2, 1, 0, 10));

		JLabel lblWarning = new JLabel("Lorsque vous souhaitez partir, appuyer sur le bouton suivant");
		lblWarning.setHorizontalAlignment(SwingConstants.CENTER);
		lblWarning.setForeground(DefaultTheme.COLOR_TEXT_PRIMARY);
		lblWarning.setFont(DefaultTheme.FONT_BUTTON);
		panelFooter.add(lblWarning);

		JPanel panelButtonContainer = new JPanel();
		panelButtonContainer.setBackground(Color.WHITE);
		panelFooter.add(panelButtonContainer);

		this.btnPaiement = new JButton("Confirmer le départ");
		panelButtonContainer.add(this.btnPaiement);
		this.btnPaiement.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		this.btnPaiement.setForeground(Color.WHITE);
		this.btnPaiement.setFont(DefaultTheme.FONT_TITLE_XS);
		this.btnPaiement.setBackground(new Color(0, 123, 255));
		this.btnPaiement.setFocusPainted(false);
		this.btnPaiement.setBorderPainted(false);
		this.btnPaiement.setPreferredSize(new Dimension(200, 45));

	}

	public JButton getBtnConfirmer() {
		return this.btnPaiement;
	}

	private void createInfoRow(JPanel parent, String label, JLabel lblVal) {
		JPanel row = new JPanel();
		row.setBackground(Color.WHITE);
		row.setLayout(new BorderLayout());

		JLabel lblKey = new JLabel(label);
		lblKey.setFont(DefaultTheme.FONT_LABEL);
		lblKey.setForeground(new Color(100, 100, 100));

		lblVal.setFont(DefaultTheme.FONT_TITLE_XS);
		lblVal.setHorizontalAlignment(SwingConstants.RIGHT);

		row.add(lblKey, BorderLayout.WEST);
		row.add(lblVal, BorderLayout.EAST);

		JPanel separator = new JPanel();
		separator.setPreferredSize(new Dimension(10, 1));
		separator.setBackground(new Color(245, 245, 245));
		row.add(separator, BorderLayout.SOUTH);

		parent.add(row);
	}

	// --- Le Rond P ---
	JPanel iconCircle = new JPanel() {
		private static final long serialVersionUID = 1L;

		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);

			Graphics2D g2 = (Graphics2D) g;
			g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

			// 4. On utilise la couleur calculée dans le constructeur
			g2.setColor(TicketVoirie.this.couleur);

			g2.fillOval(0, 0, this.getWidth(), this.getHeight());
			g2.setColor(Color.WHITE);
			g2.setFont(DefaultTheme.FONT_TITLE_XL);
			String texte = "P";
			FontMetrics metrics = g2.getFontMetrics();

			int x = (this.getWidth() - metrics.stringWidth(texte)) / 2;
			int y = ((this.getHeight() - metrics.getHeight()) / 2) + metrics.getAscent();

			g2.drawString(texte, x, y);
		}
	};
}