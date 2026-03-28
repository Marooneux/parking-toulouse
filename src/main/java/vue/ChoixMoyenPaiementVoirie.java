package vue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.FlowLayout;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.RenderingHints;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import modele.ZoneVoirie;
import ui.theme.DefaultTheme;

public class ChoixMoyenPaiementVoirie extends JPanel {

	private JButton btnCarte;
	private JButton btnVirement;
	private ZoneVoirie zone;
	private String immatriculation;
	private int duree;
	private double prix;

	public ChoixMoyenPaiementVoirie(ZoneVoirie zone2, String immatriculation, int duree, double prix) {
		this.zone = zone2;
		this.immatriculation = immatriculation;
		this.duree = duree;
		this.prix = prix;


		JPanel mainPanel = new JPanel(new BorderLayout());
		mainPanel.setBackground(DefaultTheme.BACKGROUND_COLOR);

		JPanel headerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 30));
		headerPanel.setOpaque(false);
		JLabel titleLabel = new JLabel("Choisissez votre moyen de paiement");
		titleLabel.setFont(DefaultTheme.FONT_TITLE);
		titleLabel.setForeground(DefaultTheme.TEXT_COLOR);
		headerPanel.add(titleLabel);

		JPanel cardsContainer = new JPanel(new GridLayout(1, 2, 40, 0));
		cardsContainer.setOpaque(false);
		cardsContainer.setBorder(new EmptyBorder(20, 60, 60, 60));

		btnCarte = buildPaymentButton("Payer par carte");
		btnCarte.setActionCommand("CARTE");
		JPanel cardCB = this.createCard(
				"Carte Bancaire",
				"Paiement immédiat par carte.",
				btnCarte,
				new IconCard());

		btnVirement = buildPaymentButton("Payer par virement");
		btnVirement.setActionCommand("VIREMENT");
		JPanel cardVirement = this.createCard(
				"Virement Bancaire",
				"Saisir IBAN pour prélèvement SEPA.",
				btnVirement,
				new IconBank());

		cardsContainer.add(cardCB);
		cardsContainer.add(cardVirement);

		mainPanel.add(headerPanel, BorderLayout.NORTH);
		mainPanel.add(cardsContainer, BorderLayout.CENTER);

		this.setLayout(new BorderLayout());
		this.add(mainPanel, BorderLayout.CENTER);
	}

	private JButton buildPaymentButton(String buttonText) {
		JButton btn = new JButton(buttonText);
		btn.setFont(DefaultTheme.FONT_BUTTON);
		btn.setBackground(DefaultTheme.BUTTON_COLOR);
		btn.setForeground(DefaultTheme.BUTTON_TEXT_COLOR);
		btn.setFocusPainted(false);
		btn.setBorder(new EmptyBorder(12, 25, 12, 25));
		btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btn.setBackground(DefaultTheme.BUTTON_COLOR.darker());
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btn.setBackground(DefaultTheme.BUTTON_COLOR);
			}
		});
		return btn;
	}

	private JPanel createCard(String title, String subtitle, JButton btn, Icon icon) {
		JPanel card = new JPanel();
		card.setLayout(new GridBagLayout());
		card.setBackground(DefaultTheme.BACKGROUND_CARD);
		card.setBorder(BorderFactory.createCompoundBorder(
				new LineBorder(new Color(220, 220, 220), 1),
				new EmptyBorder(30, 30, 30, 30)));

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		gbc.anchor = GridBagConstraints.CENTER;
		gbc.insets = new Insets(10, 0, 10, 0);

		JLabel lblIcon = new JLabel(icon);

		JLabel lblTitle = new JLabel(title);
		lblTitle.setFont(DefaultTheme.FONT_CARD_LABEL);
		lblTitle.setForeground(DefaultTheme.TEXT_COLOR);

		JLabel lblSubtitle = new JLabel(subtitle);
		lblSubtitle.setFont(DefaultTheme.FONT_FIELD);
		lblSubtitle.setForeground(DefaultTheme.SUBTEXT_COLOR);

		card.add(lblIcon, gbc);
		card.add(lblTitle, gbc);
		gbc.insets = new Insets(0, 0, 25, 0);
		card.add(lblSubtitle, gbc);
		gbc.insets = new Insets(10, 0, 0, 0);
		card.add(btn, gbc);

		return card;
	}

	public void addCarteListener(ActionListener listener) {
		btnCarte.addActionListener(listener);
	}

	public void addVirementListener(ActionListener listener) {
		btnVirement.addActionListener(listener);
	}

	public ZoneVoirie getZone() {
		return zone;
	}

	public String getImmatriculation() {
		return immatriculation;
	}

	public int getDuree() {
		return duree;
	}

	public double getPrix() {
		return prix;
	}

	private class IconCard implements Icon {
		@Override
		public void paintIcon(Component c, Graphics g, int x, int y) {
			Graphics2D g2 = (Graphics2D) g;
			g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			g2.setColor(DefaultTheme.TEXT_COLOR);
			g2.fillRoundRect(x, y + 10, 50, 35, 8, 8);
			g2.setColor(Color.WHITE);
			g2.fillRect(x, y + 18, 50, 6);
			g2.fillRect(x + 5, y + 32, 10, 6);
		}

		@Override
		public int getIconWidth() {
			return 50;
		}

		@Override
		public int getIconHeight() {
			return 60;
		}
	}

	private class IconBank implements Icon {
		@Override
		public void paintIcon(Component c, Graphics g, int x, int y) {
			Graphics2D g2 = (Graphics2D) g;
			g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			g2.setColor(DefaultTheme.TEXT_COLOR);
			int[] xPoints = { x + 25, x, x + 50 };
			int[] yPoints = { y, y + 15, y + 15 };
			g2.fillPolygon(xPoints, yPoints, 3);
			g2.fillRect(x + 5, y + 18, 40, 5);
			g2.fillRect(x + 8, y + 25, 6, 20);
			g2.fillRect(x + 22, y + 25, 6, 20);
			g2.fillRect(x + 36, y + 25, 6, 20);
			g2.fillRect(x + 2, y + 47, 46, 5);
		}

		@Override
		public int getIconWidth() {
			return 50;
		}

		@Override
		public int getIconHeight() {
			return 60;
		}
	}

}