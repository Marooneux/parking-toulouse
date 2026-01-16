package vue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import modele.ZoneVoirie;
public class PaiementVirementVoirie extends JPanel {

	private final Color BACKGROUND_COLOR = new Color(248, 249, 250);
	private final Color BUTTON_COLOR = new Color(13, 110, 253);
	private ZoneVoirie zone;
	private String immatriculation;
	private int duree;
	private double prix;
	private JButton btnPayer;

	public PaiementVirementVoirie(ZoneVoirie zone2, String immatriculation, int duree, double prix) {
		this.zone = zone2;
		this.immatriculation = immatriculation;
		this.duree = duree;
		this.prix = prix;

		JPanel mainPanel = new JPanel(new GridBagLayout());
		mainPanel.setBackground(this.BACKGROUND_COLOR);

		JPanel formCard = new JPanel();
		formCard.setLayout(new BoxLayout(formCard, BoxLayout.Y_AXIS));
		formCard.setBackground(Color.WHITE);
		formCard.setBorder(BorderFactory.createCompoundBorder(
				new LineBorder(new Color(220, 220, 220), 1),
				new EmptyBorder(40, 40, 40, 40)));

		JLabel title = new JLabel("Détails du compte");
		title.setFont(new Font("Segoe UI", Font.BOLD, 22));
		title.setAlignmentX(Component.LEFT_ALIGNMENT);

		formCard.add(title);
		formCard.add(Box.createVerticalStrut(30));

		this.addField(formCard, "Nom et Prénom");
		this.addField(formCard, "IBAN");

		btnPayer = new JButton("Payer " + prix + "€");
		btnPayer.setFont(new Font("Segoe UI", Font.BOLD, 14));
		btnPayer.setBackground(this.BUTTON_COLOR);
		btnPayer.setForeground(Color.WHITE);
		btnPayer.setFocusPainted(false);
		btnPayer.setBorder(new EmptyBorder(12, 0, 12, 0));
		btnPayer.setMaximumSize(new Dimension(Integer.MAX_VALUE, 45));
		btnPayer.setCursor(new Cursor(Cursor.HAND_CURSOR));

		btnPayer.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnPayer.setBackground(PaiementVirementVoirie.this.BUTTON_COLOR.darker());
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnPayer.setBackground(PaiementVirementVoirie.this.BUTTON_COLOR);
			}
		});

		formCard.add(Box.createVerticalStrut(30));
		formCard.add(btnPayer);

		mainPanel.add(formCard);
		this.setLayout(new BorderLayout());
		this.add(mainPanel, BorderLayout.CENTER);
	}

	private void addField(JPanel panel, String labelText) {
		JLabel label = new JLabel(labelText);
		label.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		label.setAlignmentX(Component.LEFT_ALIGNMENT);

		JTextField field = new JTextField();
		field.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		field.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
		field.setBorder(BorderFactory.createCompoundBorder(
				new LineBorder(new Color(200, 200, 200)),
				new EmptyBorder(5, 10, 5, 10)));
		field.setAlignmentX(Component.LEFT_ALIGNMENT);

		panel.add(label);
		panel.add(Box.createVerticalStrut(5));
		panel.add(field);
		panel.add(Box.createVerticalStrut(20));
	}

	public JButton getBtnPayer() {
		return this.btnPayer;
	}

	public ZoneVoirie getZone() {
		return this.zone;
	}

	public String getImmatriculation() {
		return this.immatriculation;
	}

	public int getDuree() {
		return this.duree;
	}

	public double getPrix() {
		return this.prix;
	}
}