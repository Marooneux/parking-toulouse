package vue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class ChoixParking extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			try {
				ChoixParking frame = new ChoixParking();
				frame.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	public ChoixParking() {
		this.setTitle("Stationnement dans un Parking");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(600, 600);
		this.setLocationRelativeTo(null);

		this.contentPane = new JPanel(new BorderLayout(15, 15));
		this.contentPane.setBorder(new EmptyBorder(20, 20, 20, 20));
		this.contentPane.setBackground(new Color(250, 250, 250));
		this.setContentPane(this.contentPane);

		// HEADER
		JPanel header = new JPanel(new FlowLayout(FlowLayout.LEFT));
		header.setBackground(new Color(250, 250, 250));

		JLabel lblIcon = new JLabel("\uD83D\uDED1");
		lblIcon.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 28));
		header.add(lblIcon);

		JPanel texte = new JPanel();
		texte.setBackground(new Color(250, 250, 250));
		texte.setLayout(new BoxLayout(texte, BoxLayout.Y_AXIS));

		JLabel lblTitre = new JLabel("Stationnement dans un Parking");
		lblTitre.setFont(new Font("Segoe UI", Font.BOLD, 22));
		lblTitre.setForeground(new Color(40, 40, 40));

		JLabel lblSousTitre = new JLabel("Choisissez une place de parking disponible");
		lblSousTitre.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblSousTitre.setForeground(new Color(100, 100, 100));

		texte.add(lblTitre);
		texte.add(lblSousTitre);
		header.add(texte);

		this.contentPane.add(header, BorderLayout.NORTH);

		// LISTA DE PLACES
		ParkingPanel place1 = new ParkingPanel("Place Capitole", "Centre Ville, Toulouse", "09 - 10", "59/100", "10",
				"Ouvert");
		ParkingPanel place2 = new ParkingPanel("Place Capitoe", "Centre Ville, Toulouse", "09 - 10", "59/100", "10",
				"Fermé");

		List<ParkingPanel> p = new ArrayList<ParkingPanel>();
		p.add(place1);
		p.add(place2);
		ListeParkings listeParkings = new ListeParkings(p);
		this.contentPane.add(listeParkings, BorderLayout.CENTER);

		JPanel panel = new JPanel();
		this.contentPane.add(panel, BorderLayout.SOUTH);

		JPanel panelBtn = new JPanel();
		panelBtn.setBackground(Color.WHITE);

		JButton btnParking = new JButton("Choisir Parking");
		btnParking.setBackground(new Color(0, 128, 255));
		btnParking.setForeground(Color.WHITE);
		btnParking.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		btnParking.setFocusPainted(false);
		btnParking.setPreferredSize(new Dimension(160, 40));
		panelBtn.add(btnParking);
		panel.add(btnParking);
		btnParking.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					SaisirHeureArriveParking framePaiement = new SaisirHeureArriveParking();
					framePaiement.setVisible(true);
					ChoixParking.this.dispose();
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		});
	}

}
