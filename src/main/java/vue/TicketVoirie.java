package vue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
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

import modele.ZoneVoirie;
import controleur.ControleurTicketVoirie;

public class TicketVoirie extends JPanel {

	private static final long serialVersionUID = 1L;
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
		
		this.couleur = convertirCouleur(this.zone.getCouleur());

		// 2. On applique la couleur au texte de la zone
		this.nomZone = new JLabel(this.zone.getCouleur());
		this.nomZone.setForeground(this.couleur); 


		this.setBackground(new Color(255, 255, 255));
		this.setBorder(new EmptyBorder(20, 20, 20, 20));
		this.setLayout(new BorderLayout(0, 0));

		JPanel panelHeader = new JPanel();
		panelHeader.setBackground(new Color(255, 255, 255));
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
		lblTitre.setForeground(new Color(33, 37, 41));
		lblTitre.setFont(new Font("Segoe UI", Font.BOLD, 24));
		panelTextHeader.add(lblTitre);

		JLabel lblSousTitre = new JLabel("Veuillez conserver ce récapitulatif jusqu'à votre départ");
		lblSousTitre.setForeground(new Color(108, 117, 125));
		lblSousTitre.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		panelTextHeader.add(lblSousTitre);

		JPanel panelCenterContainer = new JPanel();
		panelCenterContainer.setBackground(new Color(255, 255, 255));
		panelCenterContainer.setBorder(new EmptyBorder(20, 80, 10, 80));
		this.add(panelCenterContainer, BorderLayout.CENTER);
		panelCenterContainer.setLayout(new BorderLayout(0, 0));

		JPanel panelCard = new JPanel();
		panelCard.setBorder(new LineBorder(new Color(222, 226, 230), 1, true));
		panelCard.setBackground(new Color(255, 255, 255));
		panelCenterContainer.add(panelCard);
		panelCard.setLayout(new BorderLayout(0, 0));

		JPanel panelInfoGrid = new JPanel();
		panelInfoGrid.setBackground(new Color(255, 255, 255));
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
		panelFooter.setBackground(new Color(255, 255, 255));
		panelFooter.setBorder(new EmptyBorder(10, 0, 10, 0));
		this.add(panelFooter, BorderLayout.SOUTH);
		panelFooter.setLayout(new GridLayout(2, 1, 0, 10));

		JLabel lblWarning = new JLabel("Lorsque vous souhaitez partir, appuyer sur le bouton suivant");
		lblWarning.setHorizontalAlignment(SwingConstants.CENTER);
		lblWarning.setForeground(new Color(33, 37, 41));
		lblWarning.setFont(new Font("Segoe UI", Font.BOLD, 13));
		panelFooter.add(lblWarning);

		JPanel panelButtonContainer = new JPanel();
		panelButtonContainer.setBackground(Color.WHITE);
		panelFooter.add(panelButtonContainer);

		btnPaiement = new JButton("Confirmer le départ");
		panelButtonContainer.add(btnPaiement);
		btnPaiement.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnPaiement.setForeground(Color.WHITE);
		btnPaiement.setFont(new Font("Segoe UI", Font.BOLD, 16));
		btnPaiement.setBackground(new Color(0, 123, 255));
		btnPaiement.setFocusPainted(false);
		btnPaiement.setBorderPainted(false);
		btnPaiement.setPreferredSize(new Dimension(200, 45));

	}

	// --- Méthode pour récupérer la couleur (identique à celle de ChoixZone) ---
	private Color convertirCouleur(String nomCouleur) {
		if (nomCouleur == null) return Color.GRAY;
		String clef = nomCouleur.toLowerCase().trim();

		if (clef.startsWith("#")) {
			try { return Color.decode(clef); } catch (Exception e) { return Color.GRAY; }
		}
		
		switch (clef) {
			case "jaune": return new Color(255, 204, 0);
			case "orange": return new Color(255, 149, 0);
			case "rouge": return new Color(255, 59, 48);
			case "vert": case "verte": return new Color(0, 128, 0);
			case "bleu": case "bleue": case "blue": return new Color(0, 122, 255);
			default: return Color.GRAY;
		}
	}

	public JButton getBtnConfirmer() {
		return this.btnPaiement;
	}

	private void createInfoRow(JPanel parent, String label, JLabel lblVal) {
		JPanel row = new JPanel();
		row.setBackground(Color.WHITE);
		row.setLayout(new BorderLayout());

		JLabel lblKey = new JLabel(label);
		lblKey.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		lblKey.setForeground(new Color(100, 100, 100));

		lblVal.setFont(new Font("Segoe UI", Font.BOLD, 16));
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
			g2.setFont(new Font("Segoe UI", Font.BOLD, 25));
			String texte = "P";
			FontMetrics metrics = g2.getFontMetrics();

			int x = (this.getWidth() - metrics.stringWidth(texte)) / 2;
			int y = ((this.getHeight() - metrics.getHeight()) / 2) + metrics.getAscent();

			g2.drawString(texte, x, y);
		}
	};
}