package vue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.function.Consumer;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import modele.Parking;

public class ChoixParking extends JPanel {

	private JPanel gridPanel;
	private JTextField txtRecherche;
	private JMenuItem itemAlpha;
	private JMenuItem itemPlaces;
	private JMenuItem itemFermeture;
	private JButton btnFilter;
	private JPopupMenu popupMenu;

	public ChoixParking() {
		this.initialize();
	}

	private void initialize() {
		this.setLayout(new BorderLayout(0, 0));
		this.setBackground(new Color(248, 249, 250));

		JPanel headerPanel = new JPanel(new BorderLayout());
		headerPanel.setBackground(new Color(248, 249, 250));
		headerPanel.setBorder(new EmptyBorder(40, 50, 30, 50));

		JPanel textContainer = new JPanel();
		textContainer.setLayout(new BoxLayout(textContainer, BoxLayout.Y_AXIS));
		textContainer.setBackground(new Color(248, 249, 250));

		JPanel titleRow = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
		titleRow.setBackground(new Color(248, 249, 250));

		JLabel iconCar = new JLabel("\uD83C\uDD7F\uFE0F");
		iconCar.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 32));

		JLabel lblTitle = new JLabel(" Démarrer le Stationnement");
		lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 26));
		lblTitle.setForeground(new Color(33, 37, 41));

		titleRow.add(iconCar);
		titleRow.add(lblTitle);

		JPanel subtitleRow = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
		subtitleRow.setBackground(new Color(248, 249, 250));

		JLabel lblSousTitre = new JLabel("Sélectionnez le parking souhaité.");
		lblSousTitre.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblSousTitre.setForeground(new Color(108, 117, 125));
		lblSousTitre.setBorder(new EmptyBorder(5, 0, 0, 0));

		subtitleRow.add(lblSousTitre);

		textContainer.add(titleRow);
		textContainer.add(subtitleRow);

		headerPanel.add(textContainer, BorderLayout.WEST);

		JPanel filterContainer = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		filterContainer.setBackground(new Color(248, 249, 250));

		this.txtRecherche = new JTextField(15);
		this.txtRecherche.setPreferredSize(new Dimension(200, 42));
		this.txtRecherche.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		this.txtRecherche.setBorder(BorderFactory.createCompoundBorder(
				new LineBorder(new Color(206, 212, 218), 1),
				new EmptyBorder(5, 10, 5, 10)));

		this.txtRecherche.setToolTipText("Rechercher par nom ou adresse...");

		filterContainer.add(this.txtRecherche);
		filterContainer.add(Box.createHorizontalStrut(10));

		btnFilter = this.createFilterButton();
		filterContainer.add(btnFilter);

		headerPanel.add(filterContainer, BorderLayout.EAST);

		this.add(headerPanel, BorderLayout.NORTH);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBorder(null);
		scrollPane.getVerticalScrollBar().setUnitIncrement(16);
		scrollPane.getViewport().setBackground(new Color(248, 249, 250));

		this.gridPanel = new JPanel();
		this.gridPanel.setBackground(new Color(248, 249, 250));
		this.gridPanel.setLayout(new GridLayout(0, 3, 25, 25));
		this.gridPanel.setBorder(new EmptyBorder(0, 50, 50, 50));

		JPanel wrapperPanel = new JPanel(new BorderLayout());
		wrapperPanel.setBackground(new Color(248, 249, 250));
		wrapperPanel.add(this.gridPanel, BorderLayout.NORTH);

		scrollPane.setViewportView(wrapperPanel);

		this.add(scrollPane, BorderLayout.CENTER);
	}

	private JButton createFilterButton() {
		JButton btn = new JButton("Trier par  \u25BC");
		btn.setFont(new Font("Segoe UI", Font.BOLD, 14));
		btn.setPreferredSize(new Dimension(140, 42));
		btn.setBackground(Color.WHITE);
		btn.setForeground(new Color(33, 37, 41));
		btn.setFocusPainted(false);
		btn.setBorder(BorderFactory.createCompoundBorder(
				new LineBorder(new Color(206, 212, 218), 1),
				new EmptyBorder(10, 20, 10, 20)));
		btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

		popupMenu = new JPopupMenu();
		popupMenu.setBackground(Color.WHITE);
		popupMenu.setBorder(new LineBorder(new Color(206, 212, 218), 1));

		this.itemAlpha = new JMenuItem("Nom (A-Z)");
		this.styleMenuItem(this.itemAlpha);

		this.itemPlaces = new JMenuItem("Places disponibles (Croissant)");
		this.styleMenuItem(this.itemPlaces);

		this.itemFermeture = new JMenuItem("Horaire de fermeture");
		this.styleMenuItem(this.itemFermeture);

		popupMenu.add(this.itemAlpha);
		popupMenu.add(this.itemPlaces);
		popupMenu.add(this.itemFermeture);

		return btn;
	}

	private void styleMenuItem(JMenuItem item) {
		item.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		item.setBackground(Color.WHITE);
		item.setForeground(new Color(33, 37, 41));
		item.setBorder(new EmptyBorder(10, 15, 10, 15));
	}

	public void viderGrille() {
		this.gridPanel.removeAll();
		this.gridPanel.revalidate();
		this.gridPanel.repaint();
	}

	public JTextField getTxtRecherche() {
		return this.txtRecherche;
	}

	public JMenuItem getItemAlpha() {
		return this.itemAlpha;
	}

	public JMenuItem getItemPlaces() {
		return this.itemPlaces;
	}

	public JMenuItem getItemFermeture() {
		return this.itemFermeture;
	}

	public JButton getBtnFilter() {
		return this.btnFilter;
	}

	public void showFilterPopup() {
		if (popupMenu != null && btnFilter != null) {
			popupMenu.show(btnFilter, 0, btnFilter.getHeight());
		}
	}

	public void addParking(Parking parking, Consumer<Parking> onSelect) {
		ParkingPanel card = new ParkingPanel(parking, onSelect);
		this.gridPanel.add(card);
		this.gridPanel.revalidate();
		this.gridPanel.repaint();
	}
}
