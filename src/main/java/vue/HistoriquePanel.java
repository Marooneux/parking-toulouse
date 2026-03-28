package vue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import controleur.ControleurHistorique;
import modele.ReservationParking;
import modele.ReservationVoirie;
import modele.Utilisateur;
import modele.ZoneVoirie;
import ui.theme.DefaultTheme;

// 1. On hérite de JPanel au lieu de JFrame
public class HistoriquePanel extends JPanel {

	private static final long serialVersionUID = -8712846502063931578L;
	private JPanel reservationsPanel;
	private JButton loadMoreButton;

	// 2. Le constructeur prend l'Utilisateur pour savoir QUI afficher
	public HistoriquePanel(Utilisateur utilisateur) {
		// Configuration du JPanel
		this.setLayout(new BorderLayout(15, 15));
		this.setBorder(new EmptyBorder(20, 20, 20, 20));
		this.setBackground(DefaultTheme.BACKGROUND_COLOR);

		this.initHeader();
		this.initReservationsList();
		this.initLoadMoreButton();

		new ControleurHistorique(this, utilisateur.getId());
	}

	private void initHeader() {
		JPanel header = new JPanel(new BorderLayout());
		header.setBackground(DefaultTheme.BACKGROUND_COLOR);

		JLabel title = new JLabel("Historique des réservations");
		title.setFont(DefaultTheme.FONT_HERO_TITLE);
		title.setForeground(DefaultTheme.TEXT_COLOR);

		JLabel subtitle = new JLabel("Retrouvez vos stationnements passés et en cours");
		subtitle.setFont(DefaultTheme.FONT_BODY);
		subtitle.setForeground(Color.GRAY);

		JPanel textPanel = new JPanel();
		textPanel.setBackground(DefaultTheme.BACKGROUND_COLOR);
		textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.Y_AXIS));
		textPanel.add(title);
		textPanel.add(Box.createVerticalStrut(5));
		textPanel.add(subtitle);

		header.add(textPanel, BorderLayout.CENTER);
		this.add(header, BorderLayout.NORTH);
	}

	private void initReservationsList() {
		this.reservationsPanel = new JPanel();
		this.reservationsPanel.setLayout(new BoxLayout(this.reservationsPanel, BoxLayout.Y_AXIS));
		this.reservationsPanel.setBackground(DefaultTheme.BACKGROUND_COLOR);

		JScrollPane scrollPane = new JScrollPane(this.reservationsPanel);
		scrollPane.setBorder(null);
		scrollPane.getViewport().setBackground(DefaultTheme.BACKGROUND_COLOR);
		scrollPane.getVerticalScrollBar().setUnitIncrement(16);

		this.add(scrollPane, BorderLayout.CENTER);
	}

	private void initLoadMoreButton() {
		this.loadMoreButton = new JButton("Rafraîchir");
		this.loadMoreButton.setBackground(Color.WHITE);
		this.loadMoreButton.setFocusPainted(false);
		this.loadMoreButton.setFont(DefaultTheme.FONT_HERO_HINT);
		this.loadMoreButton.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200)));
		this.loadMoreButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		this.loadMoreButton.setPreferredSize(new Dimension(200, 35));

		JPanel panel = new JPanel();
		panel.setBackground(DefaultTheme.BACKGROUND_COLOR);
		panel.add(this.loadMoreButton);

		this.add(panel, BorderLayout.SOUTH);
	}

	public void addReloadListener(java.awt.event.ActionListener listener) {
		this.loadMoreButton.addActionListener(listener);
	}

	public void afficherHistorique(List<ReservationParking> reservationsParking,
			List<ReservationVoirie> reservationsVoirie) {
		this.reservationsPanel.removeAll();

		if (reservationsParking.isEmpty() && reservationsVoirie.isEmpty()) {
			JLabel empty = new JLabel("Aucun historique trouvé.");
			empty.setFont(new Font("Segoe UI", Font.ITALIC, 14));
			empty.setForeground(Color.GRAY);
			empty.setAlignmentX(Component.CENTER_ALIGNMENT);
			this.reservationsPanel.add(Box.createVerticalStrut(20));
			this.reservationsPanel.add(empty);
		}

		for (ReservationParking r : reservationsParking) {
			this.reservationsPanel.add(this.createReservationCardParking(r));
			this.reservationsPanel.add(Box.createRigidArea(new Dimension(0, 12)));
		}

		for (ReservationVoirie r : reservationsVoirie) {
			this.reservationsPanel.add(this.createReservationCardVoirie(r));
			this.reservationsPanel.add(Box.createRigidArea(new Dimension(0, 12)));
		}

		this.reservationsPanel.revalidate();
		this.reservationsPanel.repaint();
	}

	private JPanel createReservationCardParking(ReservationParking r) {
		JPanel card = new JPanel(new BorderLayout(10, 10));
		card.setBackground(Color.WHITE);
		card.setMaximumSize(new Dimension(Integer.MAX_VALUE, 100));
		card.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createLineBorder(new Color(220, 220, 220)),
				new EmptyBorder(12, 12, 12, 12)));

		// --- ICONE ---
		JLabel icon = new JLabel("\uD83D\uDE97");
		icon.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 24));
		card.add(icon, BorderLayout.WEST);

		// --- INFO CENTRE ---
		JPanel infoPanel = new JPanel();
		infoPanel.setBackground(Color.WHITE);
		infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));

		JLabel name = new JLabel(r.getParking().getNom());
		name.setFont(new Font("Segoe UI", Font.BOLD, 15));

		String adrStr = (r.getParking().getAdresse() != null) ? r.getParking().getAdresse().getRue()
				: "Adresse inconnue";
		JLabel address = new JLabel(adrStr);
		address.setFont(DefaultTheme.FONT_HERO_HINT);
		address.setForeground(Color.GRAY);

		// Dates
		JLabel dates = new JLabel(
				"Du " + r.getDateArrivee() + (r.getDateDepart() != null ? " au " + r.getDateDepart() : " (En cours)"));
		dates.setFont(DefaultTheme.FONT_BODY_SMALL);
		dates.setForeground(new Color(100, 100, 150));

		infoPanel.add(name);
		infoPanel.add(address);
		infoPanel.add(Box.createVerticalStrut(5));
		infoPanel.add(dates);
		card.add(infoPanel, BorderLayout.CENTER);

		// --- PRIX & ACTION ---
		JPanel rightPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
		rightPanel.setBackground(Color.WHITE);

		double prix = (r.getDateDepart() != null) ? r.calculerPrixTotal() : 0;
		JLabel price = new JLabel(r.getDateDepart() == null ? "En cours" : String.format("%.2f €", prix));
		price.setFont(DefaultTheme.FONT_BUTTON);
		price.setForeground(new Color(40, 167, 69));

		rightPanel.add(price);
		card.add(rightPanel, BorderLayout.EAST);

		card.addMouseListener(new java.awt.event.MouseAdapter() {
			@Override
			public void mouseEntered(java.awt.event.MouseEvent evt) {
				card.setBackground(new Color(250, 250, 250));
			}

			@Override
			public void mouseExited(java.awt.event.MouseEvent evt) {
				card.setBackground(Color.WHITE);
			}
		});

		return card;
	}

	private JPanel createReservationCardVoirie(ReservationVoirie r) {
		JPanel card = new JPanel(new BorderLayout(10, 10));
		card.setBackground(Color.WHITE);
		card.setMaximumSize(new Dimension(Integer.MAX_VALUE, 100));
		card.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createLineBorder(new Color(220, 220, 220)),
				new EmptyBorder(12, 12, 12, 12)));

		JLabel icon = new JLabel("\uD83D\uDEA7");
		icon.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 24));
		card.add(icon, BorderLayout.WEST);

		JPanel infoPanel = new JPanel();
		infoPanel.setBackground(Color.WHITE);
		infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));

		ZoneVoirie zone = r.getZone();
		JLabel name = new JLabel("Zone voirie " + (zone != null ? zone.getCouleur() : "?"));
		name.setFont(new Font("Segoe UI", Font.BOLD, 15));

		JLabel dates = new JLabel("Début " + r.getDateDebut() + " | Durée " + r.getDureeMinutes() + " min");
		dates.setFont(DefaultTheme.FONT_BODY_SMALL);
		dates.setForeground(new Color(100, 100, 150));

		infoPanel.add(name);
		infoPanel.add(Box.createVerticalStrut(5));
		infoPanel.add(dates);
		card.add(infoPanel, BorderLayout.CENTER);

		JPanel rightPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
		rightPanel.setBackground(Color.WHITE);
		JLabel price = new JLabel("Durée " + r.getDureeMinutes() + " min");
		price.setFont(DefaultTheme.FONT_BUTTON);
		price.setForeground(new Color(0, 123, 255));
		rightPanel.add(price);
		card.add(rightPanel, BorderLayout.EAST);

		card.addMouseListener(new java.awt.event.MouseAdapter() {
			@Override
			public void mouseEntered(java.awt.event.MouseEvent evt) {
				card.setBackground(new Color(250, 250, 250));
			}

			@Override
			public void mouseExited(java.awt.event.MouseEvent evt) {
				card.setBackground(Color.WHITE);
			}
		});

		return card;
	}
}