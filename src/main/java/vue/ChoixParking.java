package vue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.function.Consumer;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

import modele.Parking;

public class ChoixParking extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel gridPanel;

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> new ChoixParking().setVisible(true));
	}

	public ChoixParking() {
		this.initialize();
	}

	private void initialize() {
		this.setTitle("Stationnement");
		this.setBounds(100, 100, 1200, 750);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.getContentPane().setBackground(new Color(248, 249, 250));
		this.getContentPane().setLayout(new BorderLayout(0, 0));

		JPanel headerPanel = new JPanel();
		headerPanel.setLayout(new BoxLayout(headerPanel, BoxLayout.Y_AXIS));
		headerPanel.setBackground(new Color(248, 249, 250));
		headerPanel.setBorder(new EmptyBorder(40, 50, 30, 50));

		JPanel titleContainer = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
		titleContainer.setBackground(new Color(248, 249, 250));

		JPanel subtitleContainer = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
		subtitleContainer.setBackground(new Color(248, 249, 250));

		JLabel iconCar = new JLabel("\uD83C\uDD7F\uFE0F");
		iconCar.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 32));

		JLabel lblTitle = new JLabel("Démarrer le Stationnement");
		lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 26));
		lblTitle.setForeground(new Color(33, 37, 41));

		titleContainer.add(iconCar);
		titleContainer.add(lblTitle);

		JLabel lblSousTitre = new JLabel("Sélectionnez le parking souhaité.");
		lblSousTitre.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblSousTitre.setForeground(new Color(108, 117, 125));
		lblSousTitre.setAlignmentX(Component.LEFT_ALIGNMENT);
		lblSousTitre.setBorder(new EmptyBorder(10, 0, 0, 0));
		subtitleContainer.add(lblSousTitre);

		headerPanel.add(titleContainer);
		headerPanel.add(subtitleContainer);

		this.getContentPane().add(headerPanel, BorderLayout.NORTH);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBorder(null);
		scrollPane.getVerticalScrollBar().setUnitIncrement(16);
		scrollPane.getViewport().setBackground(new Color(248, 249, 250));

		this.gridPanel = new JPanel();
		this.gridPanel.setBackground(new Color(248, 249, 250));
		this.gridPanel.setLayout(new GridLayout(0, 3, 25, 25));
		this.gridPanel.setBorder(new EmptyBorder(0, 50, 50, 50));

		scrollPane.setViewportView(this.gridPanel);
		this.getContentPane().add(scrollPane, BorderLayout.CENTER);

	}

	public void addParking(Parking parking, Consumer<Parking> onSelect) {
		ParkingPanel card = new ParkingPanel(parking, onSelect);
		this.gridPanel.add(card);
		this.gridPanel.revalidate();
		this.gridPanel.repaint();
	}
}
