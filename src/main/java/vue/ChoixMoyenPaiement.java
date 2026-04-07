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

import ui.theme.DefaultTheme;

public class ChoixMoyenPaiement extends JPanel {

	private static final long serialVersionUID = 1L;
	private JButton btnCarte;
	private JButton btnVirement;

	public ChoixMoyenPaiement() {
		JPanel mainPanel = new JPanel(new BorderLayout());
		mainPanel.setBackground(DefaultTheme.COLOR_BG_PAGE);

		JPanel headerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 30));
		headerPanel.setOpaque(false);
		JLabel titleLabel = new JLabel("Choisissez votre moyen de paiement");
		titleLabel.setFont(DefaultTheme.FONT_TITLE_XL);
		titleLabel.setForeground(DefaultTheme.COLOR_TEXT_PRIMARY);
		headerPanel.add(titleLabel);

		JPanel cardsContainer = new JPanel(new GridLayout(1, 2, 40, 0));
		cardsContainer.setOpaque(false);
		cardsContainer.setBorder(new EmptyBorder(20, 60, 60, 60));

		this.btnCarte = this.buildPaymentButton("Payer par carte");
		this.btnCarte.setActionCommand("CARTE");
		JPanel cardCB = this.createCard(
				"Carte Bancaire",
				"Paiement immédiat par carte.",
				this.btnCarte,
				new IconCard());

		this.btnVirement = this.buildPaymentButton("Payer par virement");
		this.btnVirement.setActionCommand("VIREMENT");
		JPanel cardVirement = this.createCard(
				"Virement Bancaire",
				"Saisir IBAN pour prélèvement SEPA.",
				this.btnVirement,
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
		btn.setBackground(DefaultTheme.COLOR_PRIMARY);
		btn.setForeground(DefaultTheme.COLOR_ON_PRIMARY);
		btn.setFocusPainted(false);
		btn.setBorder(new EmptyBorder(12, 25, 12, 25));
		btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
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
		return btn;
	}

	private JPanel createCard(String title, String subtitle, JButton btn, Icon icon) {
		JPanel card = new JPanel();
		card.setLayout(new GridBagLayout());
		card.setBackground(DefaultTheme.COLOR_BG_SURFACE);
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
		lblTitle.setForeground(DefaultTheme.COLOR_TEXT_PRIMARY);

		JLabel lblSubtitle = new JLabel(subtitle);
		lblSubtitle.setFont(DefaultTheme.FONT_BODY);
		lblSubtitle.setForeground(DefaultTheme.COLOR_TEXT_MUTED);

		card.add(lblIcon, gbc);
		card.add(lblTitle, gbc);
		gbc.insets = new Insets(0, 0, 25, 0);
		card.add(lblSubtitle, gbc);
		gbc.insets = new Insets(10, 0, 0, 0);
		card.add(btn, gbc);

		return card;
	}

	public void addCarteListener(ActionListener listener) {
		this.btnCarte.addActionListener(listener);
	}

	public void addVirementListener(ActionListener listener) {
		this.btnVirement.addActionListener(listener);
	}

	private class IconCard implements Icon {
		@Override
		public void paintIcon(Component c, Graphics g, int x, int y) {
			Graphics2D g2 = (Graphics2D) g;
			g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			g2.setColor(DefaultTheme.COLOR_TEXT_PRIMARY);
			g2.fillRoundRect(x, y + 10, 50, 35, 8, 8);
			g2.setColor(Color.WHITE);
			g2.fillRect(x, y + 18, 50, 6);
			g2.fillRect(x + 5, y + 32, 10, 6);
		}

		@Override
		public int getIconWidth() { return 50; }

		@Override
		public int getIconHeight() { return 60; }
	}

	private class IconBank implements Icon {
		@Override
		public void paintIcon(Component c, Graphics g, int x, int y) {
			Graphics2D g2 = (Graphics2D) g;
			g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			g2.setColor(DefaultTheme.COLOR_TEXT_PRIMARY);
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
		public int getIconWidth() { return 50; }

		@Override
		public int getIconHeight() { return 60; }
	}
}
