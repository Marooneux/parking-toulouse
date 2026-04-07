package vue;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import controleur.ControleurChoixTypeStationnement;
import ui.theme.DefaultTheme;

public class ChoixTypeStationnement extends JPanel {

	private static final long serialVersionUID = -6935867236118057702L;
	private JButton profileButton;
	private JButton btnParking;
	private JButton btnVoirie;
	private JPanel activeTicketPanel;
	private JLabel activeTicketDetails;
	private JButton btnVoirTicket;

	public ChoixTypeStationnement() {
		this(0);
	}

	public ChoixTypeStationnement(int idUser) {
		this.setLayout(new BorderLayout());
		this.setBackground(DefaultTheme.COLOR_BG_PAGE);

		JPanel mainPanel = new JPanel(new BorderLayout());
		mainPanel.setBackground(DefaultTheme.COLOR_BG_PAGE);

		JPanel headerPanel = new JPanel(new BorderLayout());
		headerPanel.setOpaque(false);
		headerPanel.setBorder(new EmptyBorder(25, 40, 5, 40));

		JLabel titleLabel = new JLabel(" Choisissez votre type de stationnement");
		titleLabel.setFont(DefaultTheme.FONT_TITLE_L);
		titleLabel.setForeground(DefaultTheme.COLOR_TEXT_PRIMARY);
		titleLabel.setIcon(new IconP());

		this.profileButton = new JButton("Mon Profil");
		this.profileButton.setFont(DefaultTheme.FONT_BUTTON);
		this.profileButton.setForeground(DefaultTheme.COLOR_TEXT_PRIMARY);
		this.profileButton.setBackground(Color.WHITE);
		this.profileButton.setBorder(new LineBorder(new Color(220, 220, 220), 1));
		this.profileButton.setFocusPainted(false);
		this.profileButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		this.profileButton.setIcon(new IconProfile());
		this.profileButton.setIconTextGap(10);

		this.profileButton.setMargin(new Insets(5, 15, 5, 15));

		headerPanel.add(titleLabel, BorderLayout.WEST);
		headerPanel.add(this.profileButton, BorderLayout.EAST);

		JPanel cardsContainer = new JPanel(new GridLayout(1, 2, 30, 0));
		cardsContainer.setOpaque(false);
		cardsContainer.setBorder(new EmptyBorder(20, 40, 40, 40));

		JPanel cardParking = this.createCard(
				"Stationnement Parking",
				"Stationner dans un parking sécurisé au choix.",
				"Trouver un parking",
				true);

		JPanel cardVoirie = this.createCard(
				"Stationnement en Voirie",
				"Stationner en voirie dans une zone au choix.",
				"Trouver un emplacement",
				false);

		cardsContainer.add(cardParking);
		cardsContainer.add(cardVoirie);

		JPanel contentPanel = new JPanel();
		contentPanel.setOpaque(false);
		contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));

		this.activeTicketPanel = this.createActiveTicketPanel();
		this.activeTicketPanel.setVisible(false);
		contentPanel.add(this.activeTicketPanel);
		contentPanel.add(Box.createVerticalStrut(10));
		contentPanel.add(cardsContainer);

		mainPanel.add(headerPanel, BorderLayout.NORTH);
		mainPanel.add(contentPanel, BorderLayout.CENTER);

		this.add(mainPanel, BorderLayout.CENTER);

		new ControleurChoixTypeStationnement(this, idUser);
	}

	private JPanel createCard(String title, String subtitle, String buttonText, boolean isParking) {
		JPanel card = new JPanel();
		card.setLayout(new GridBagLayout());
		card.setBackground(DefaultTheme.COLOR_BG_SURFACE);

		card.setBorder(BorderFactory.createCompoundBorder(
				new LineBorder(new Color(220, 220, 220), 1),
				new EmptyBorder(30, 30, 30, 30)));

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		gbc.anchor = GridBagConstraints.CENTER;
		gbc.insets = new Insets(5, 0, 5, 0);

		JLabel lblTitle = new JLabel(title);
		lblTitle.setFont(DefaultTheme.FONT_TITLE_S);
		lblTitle.setForeground(DefaultTheme.COLOR_TEXT_PRIMARY);

		JLabel lblSubtitle = new JLabel(subtitle);
		lblSubtitle.setFont(DefaultTheme.FONT_BODY);
		lblSubtitle.setForeground(DefaultTheme.COLOR_TEXT_MUTED);

		JButton btn = new JButton(buttonText);
		btn.setFont(DefaultTheme.FONT_BUTTON);
		btn.setBackground(DefaultTheme.COLOR_PRIMARY);
		btn.setForeground(DefaultTheme.COLOR_ON_PRIMARY);
		btn.setFocusPainted(false);
		btn.setBorder(new EmptyBorder(10, 20, 10, 20));
		btn.setCursor(new Cursor(Cursor.HAND_CURSOR));

		if (isParking) {
			this.btnParking = btn;
		} else {
			this.btnVoirie = btn;
		}

		btn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btn.setBackground(DefaultTheme.COLOR_PRIMARY.darker());
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btn.setBackground(DefaultTheme.COLOR_PRIMARY);
			}
		});

		card.add(lblTitle, gbc);
		gbc.insets = new Insets(5, 0, 20, 0);
		card.add(lblSubtitle, gbc);
		card.add(btn, gbc);

		return card;
	}

	public JButton getProfileButton() {
		return this.profileButton;
	}

	public JButton getBtnParking() {
		return this.btnParking;
	}

	public JButton getBtnVoirie() {
		return this.btnVoirie;
	}

	public JButton getBtnVoirTicket() {
		return this.btnVoirTicket;
	}

	public void afficherTicketActif(String parkingNom, String plaque, String heureArrivee) {
		String nom = (parkingNom == null || parkingNom.isBlank()) ? "Parking" : parkingNom;
		String immat = (plaque == null || plaque.isBlank()) ? "Inconnue" : plaque;
		String heure = (heureArrivee == null || heureArrivee.isBlank()) ? "?" : heureArrivee;
		this.activeTicketDetails.setText(nom + " | " + immat + " | Arrivee " + heure);
		this.activeTicketPanel.setVisible(true);
		this.revalidate();
		this.repaint();
	}

	public void cacherTicketActif() {
		this.activeTicketPanel.setVisible(false);
		this.revalidate();
		this.repaint();
	}

	private JPanel createActiveTicketPanel() {
		JPanel panel = new JPanel(new BorderLayout(10, 10));
		panel.setBackground(Color.WHITE);
		panel.setBorder(BorderFactory.createCompoundBorder(
				new LineBorder(new Color(220, 220, 220), 1),
				new EmptyBorder(15, 20, 15, 20)));
		panel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 90));
		panel.setAlignmentX(Component.CENTER_ALIGNMENT);

		JPanel textPanel = new JPanel();
		textPanel.setOpaque(false);
		textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.Y_AXIS));

		JLabel title = new JLabel("Stationnement en cours");
		title.setFont(DefaultTheme.FONT_TITLE_XS);
		title.setForeground(DefaultTheme.COLOR_TEXT_PRIMARY);

		this.activeTicketDetails = new JLabel(" ");
		this.activeTicketDetails.setFont(DefaultTheme.FONT_BODY_S);
		this.activeTicketDetails.setForeground(DefaultTheme.COLOR_TEXT_MUTED);

		textPanel.add(title);
		textPanel.add(Box.createVerticalStrut(5));
		textPanel.add(this.activeTicketDetails);

		this.btnVoirTicket = new JButton("Voir le ticket");
		this.btnVoirTicket.setFont(DefaultTheme.FONT_BUTTON);
		this.btnVoirTicket.setBackground(DefaultTheme.COLOR_PRIMARY);
		this.btnVoirTicket.setForeground(DefaultTheme.COLOR_ON_PRIMARY);
		this.btnVoirTicket.setFocusPainted(false);
		this.btnVoirTicket.setBorder(new EmptyBorder(8, 15, 8, 15));
		this.btnVoirTicket.setCursor(new Cursor(Cursor.HAND_CURSOR));

		panel.add(textPanel, BorderLayout.CENTER);
		panel.add(this.btnVoirTicket, BorderLayout.EAST);

		return panel;
	}

	private class IconP implements Icon {
		@Override
		public void paintIcon(Component c, Graphics g, int x, int y) {
			Graphics2D g2 = (Graphics2D) g;
			g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			g2.setColor(DefaultTheme.COLOR_TEXT_PRIMARY);
			g2.setStroke(new BasicStroke(2));
			g2.drawRoundRect(x, y, 30, 30, 10, 10);
			g2.setFont(DefaultTheme.FONT_CARD_LABEL);
			g2.drawString("P", x + 10, y + 23);
		}

		@Override
		public int getIconWidth() {
			return 35;
		}

		@Override
		public int getIconHeight() {
			return 35;
		}
	}

	private class IconProfile implements Icon {
		@Override
		public void paintIcon(Component c, Graphics g, int x, int y) {
			Graphics2D g2 = (Graphics2D) g;
			g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			g2.setColor(DefaultTheme.COLOR_TEXT_PRIMARY);

			g2.fillOval(x + 6, y + 2, 12, 12);
			g2.fillArc(x + 2, y + 16, 20, 14, 0, 180);
		}

		@Override
		public int getIconWidth() {
			return 24;
		}

		@Override
		public int getIconHeight() {
			return 24;
		}
	}
}