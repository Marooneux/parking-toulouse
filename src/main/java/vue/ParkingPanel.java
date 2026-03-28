package vue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;
import java.util.function.Consumer;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import modele.Parking;
import ui.theme.DefaultTheme;

public class ParkingPanel extends JPanel {

	private static final long serialVersionUID = -1637927391985610743L;

	private JButton btnModifier;

	private Color normalBorder = new Color(230, 230, 230);
	private Color hoverBorder = new Color(100, 100, 100);

	public ParkingPanel(Parking parking, Consumer<Parking> onClick) {

		this.setLayout(new BorderLayout());
		this.setBackground(Color.WHITE);
		this.setPreferredSize(new Dimension(300, 260));
		this.setBorder(BorderFactory.createCompoundBorder(
				new LineBorder(this.normalBorder, 1),
				new EmptyBorder(20, 20, 20, 20)));
		this.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

		this.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (onClick != null) {
					onClick.accept(parking);
				}
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				ParkingPanel.this.setBorder(BorderFactory.createCompoundBorder(
						new LineBorder(ParkingPanel.this.hoverBorder, 1),
						new EmptyBorder(20, 20, 20, 20)));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				ParkingPanel.this.setBorder(BorderFactory.createCompoundBorder(
						new LineBorder(ParkingPanel.this.normalBorder, 1),
						new EmptyBorder(20, 20, 20, 20)));
			}
		});

		JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
		topPanel.setOpaque(false);

		CircleIcon pIcon = new CircleIcon("P");
		topPanel.add(pIcon);

		JLabel space = new JLabel();
		space.setPreferredSize(new Dimension(15, 1));
		topPanel.add(space);

		JLabel lblName = new JLabel("<html>" + parking.getNom() + "</html>");
		lblName.setFont(DefaultTheme.FONT_TITLE_ALT);
		lblName.setForeground(DefaultTheme.TEXT_COLOR);
		topPanel.add(lblName);

		this.add(topPanel, BorderLayout.NORTH);

		JPanel centerPanel = new JPanel();
		centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
		centerPanel.setOpaque(false);
		centerPanel.setBorder(new EmptyBorder(15, 0, 15, 0));

		centerPanel.add(this.createDetailRow("📍", parking.getAdresse() != null ? parking.getAdresse().getRue() : ""));
		centerPanel.add(Box.createVerticalStrut(8));

		String horaireText = (parking.getHoraireOuverture().equals(parking.getHoraireFermeture()))
				? "24h / 24"
				: parking.getHoraireOuverture() + " - " + parking.getHoraireFermeture();
		centerPanel.add(this.createDetailRow("🕒", horaireText));

		centerPanel.add(Box.createVerticalStrut(8));
		centerPanel.add(
				this.createDetailRow("🚗", (parking.getCapacite() - parking.getNbPlacesOccupees()) + "/"
						+ parking.getCapacite() + " places"));

		centerPanel.add(Box.createVerticalStrut(8));
		centerPanel.add(this.createDetailRow("📏", "Max " + (parking.getHauteurMax()) + "m"));

		this.add(centerPanel, BorderLayout.CENTER);

		JPanel bottomPanel = new JPanel(new BorderLayout());
		bottomPanel.setOpaque(false);

		JPanel borderTop = new JPanel();
		borderTop.setBackground(new Color(240, 240, 240));
		borderTop.setPreferredSize(new Dimension(100, 1));
		bottomPanel.add(borderTop, BorderLayout.NORTH);

		JLabel lblTarifLabel = new JLabel("Tarif");
		lblTarifLabel.setFont(DefaultTheme.FONT_BOLD);
		lblTarifLabel.setForeground(Color.GRAY);
		lblTarifLabel.setBorder(new EmptyBorder(10, 0, 0, 0));

		JLabel lblPrice = new JLabel(String.format("%.2f€/h", parking.getTarif()));
		lblPrice.setFont(DefaultTheme.FONT_CARD_LABEL);
		lblPrice.setForeground(DefaultTheme.TEXT_COLOR);
		lblPrice.setBorder(new EmptyBorder(5, 0, 0, 0));

		bottomPanel.add(lblTarifLabel, BorderLayout.WEST);
		bottomPanel.add(lblPrice, BorderLayout.EAST);
		JPanel actionsPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 0, 0));
		actionsPanel.setOpaque(false);

		bottomPanel.add(actionsPanel, BorderLayout.SOUTH);

		this.add(bottomPanel, BorderLayout.SOUTH);
	}

	private JPanel createDetailRow(String icon, String text) {
		JPanel row = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
		row.setOpaque(false);

		JLabel lblIcon = new JLabel(icon);
		lblIcon.setFont(DefaultTheme.FONT_ICON_SMALL);
		lblIcon.setPreferredSize(new Dimension(25, 20));
		lblIcon.setForeground(Color.GRAY);

		JLabel lblText = new JLabel(text);
		lblText.setFont(DefaultTheme.FONT_LABEL_SMALL);
		lblText.setForeground(new Color(73, 80, 87));

		row.add(lblIcon);
		row.add(lblText);
		return row;
	}

	class CircleIcon extends JComponent {

		private static final long serialVersionUID = -440563882095950824L;
		private String text;

		public CircleIcon(String text) {
			this.text = text;
			this.setPreferredSize(new Dimension(45, 45));
		}

		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			Graphics2D g2 = (Graphics2D) g;
			g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

			g2.setColor(new Color(52, 58, 64));
			g2.fill(new Ellipse2D.Double(0, 0, 45, 45));

			g2.setColor(Color.WHITE);
			g2.setFont(DefaultTheme.FONT_HERO_TITLE);
			FontMetrics fm = g2.getFontMetrics();
			int x = (this.getWidth() - fm.stringWidth(this.text)) / 2;
			int y = ((this.getHeight() - fm.getHeight()) / 2) + fm.getAscent();
			g2.drawString(this.text, x, y - 2);
		}
	}

	public JButton getBtnModifier() {
		return this.btnModifier;
	}
}
