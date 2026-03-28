package vue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;

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
import ui.theme.DefaultTheme;

public class ChoixParking extends JPanel {

	private JPanel gridPanel;
	private TemplateSaisie txtRecherche;
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
		this.setBackground(DefaultTheme.BACKGROUND_COLOR);

		JPanel headerPanel = new JPanel(new BorderLayout());
		headerPanel.setBackground(DefaultTheme.BACKGROUND_COLOR);
		headerPanel.setBorder(new EmptyBorder(40, 50, 30, 50));

		JPanel textContainer = new JPanel();
		textContainer.setLayout(new BoxLayout(textContainer, BoxLayout.Y_AXIS));
		textContainer.setBackground(DefaultTheme.BACKGROUND_COLOR);

		JPanel titleRow = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
		titleRow.setBackground(DefaultTheme.BACKGROUND_COLOR);

		JLabel iconCar = new JLabel("\uD83C\uDD7F\uFE0F");
		iconCar.setFont(DefaultTheme.FONT_ICON);

		JLabel lblTitle = new JLabel(" Démarrer le Stationnement");
		lblTitle.setFont(DefaultTheme.FONT_TITLE);
		lblTitle.setForeground(DefaultTheme.TEXT_COLOR);

		titleRow.add(iconCar);
		titleRow.add(lblTitle);

		JPanel subtitleRow = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
		subtitleRow.setBackground(DefaultTheme.BACKGROUND_COLOR);

		JLabel lblSousTitre = new JLabel("Sélectionnez le parking souhaité.");
		lblSousTitre.setFont(DefaultTheme.FONT_FIELD);
		lblSousTitre.setForeground(DefaultTheme.SUBTEXT_COLOR);
		lblSousTitre.setBorder(new EmptyBorder(5, 0, 0, 0));

		subtitleRow.add(lblSousTitre);

		textContainer.add(titleRow);
		textContainer.add(subtitleRow);

		headerPanel.add(textContainer, BorderLayout.WEST);

		JPanel filterContainer = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		filterContainer.setBackground(DefaultTheme.BACKGROUND_COLOR);

		this.txtRecherche = new TemplateSaisie("Recherche", "Rechercher...", false, false);
		this.txtRecherche.getField().setPreferredSize(new Dimension(200, 42));
		this.txtRecherche.getField().setFont(DefaultTheme.FONT_BODY);
		this.txtRecherche.getField().setBorder(BorderFactory.createCompoundBorder(
				new LineBorder(DefaultTheme.FIELD_BORDER_COLOR, 1),
				new EmptyBorder(5, 10, 5, 10)));

		this.txtRecherche.getField().setToolTipText("Rechercher par nom ou adresse...");

		filterContainer.add(this.txtRecherche);
		filterContainer.add(Box.createHorizontalStrut(10));

		btnFilter = this.createFilterButton();
		filterContainer.add(btnFilter);

		headerPanel.add(filterContainer, BorderLayout.EAST);

		this.add(headerPanel, BorderLayout.NORTH);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBorder(null);
		scrollPane.getVerticalScrollBar().setUnitIncrement(16);
		scrollPane.getViewport().setBackground(DefaultTheme.BACKGROUND_COLOR);

		this.gridPanel = new JPanel();
		this.gridPanel.setBackground(DefaultTheme.BACKGROUND_COLOR);
		this.gridPanel.setLayout(new GridLayout(0, 3, 25, 25));
		this.gridPanel.setBorder(new EmptyBorder(0, 50, 50, 50));

		JPanel wrapperPanel = new JPanel(new BorderLayout());
		wrapperPanel.setBackground(DefaultTheme.BACKGROUND_COLOR);
		wrapperPanel.add(this.gridPanel, BorderLayout.NORTH);

		scrollPane.setViewportView(wrapperPanel);

		this.add(scrollPane, BorderLayout.CENTER);
	}

	private JButton createFilterButton() {
		JButton btn = new JButton("Trier par  \u25BC");
		btn.setFont(DefaultTheme.FONT_BUTTON);
		btn.setPreferredSize(new Dimension(140, 42));
		btn.setBackground(Color.WHITE);
		btn.setForeground(DefaultTheme.TEXT_COLOR);
		btn.setFocusPainted(false);
		btn.setBorder(BorderFactory.createCompoundBorder(
				new LineBorder(DefaultTheme.FIELD_BORDER_COLOR, 1),
				new EmptyBorder(10, 20, 10, 20)));
		btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

		popupMenu = new JPopupMenu();
		popupMenu.setBackground(Color.WHITE);
		popupMenu.setBorder(new LineBorder(DefaultTheme.FIELD_BORDER_COLOR, 1));

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
		item.setFont(DefaultTheme.FONT_BODY);
		item.setBackground(Color.WHITE);
		item.setForeground(DefaultTheme.TEXT_COLOR);
		item.setBorder(new EmptyBorder(10, 15, 10, 15));
	}

	public void viderGrille() {
		this.gridPanel.removeAll();
		this.gridPanel.revalidate();
		this.gridPanel.repaint();
	}

	public JTextField getTxtRecherche() {
		return this.txtRecherche.getField();
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
