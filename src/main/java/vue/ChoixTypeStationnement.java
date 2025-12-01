package vue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class ChoixTypeStationnement extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JButton btnParking;
	private JButton btnVoirie;

	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			try {
				ChoixTypeStationnement frame = new ChoixTypeStationnement();
				frame.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	public ChoixTypeStationnement() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(900, 600);
		this.setLocationRelativeTo(null);

		this.contentPane = new JPanel();
		this.contentPane.setBackground(Color.WHITE);
		this.contentPane.setBorder(new EmptyBorder(20, 20, 20, 20));
		this.contentPane.setLayout(new BorderLayout(20, 20));
		this.setContentPane(this.contentPane);

		// HEADER
		JPanel header = this.EnteteDeLaFenetre();

		this.contentPane.add(header, BorderLayout.NORTH);

		// Partie central
		JPanel centre = new JPanel();
		centre.setBackground(Color.WHITE);
		centre.setLayout(new GridLayout(1, 2, 20, 0));
		this.contentPane.add(centre, BorderLayout.CENTER);

		this.btnParking = this.crerButton("Trouver un parking");
		this.btnVoirie = this.crerButton("Trouver un emplacement");

		// Card 1 - Parking
		JButton btnParking = this.cardParking(centre);
		btnParking.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                	ChoixParking frameChoixParking = new ChoixParking();
                	frameChoixParking.setVisible(true);
                    dispose();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

		// Card 2 Voirie
		JButton btnVoirie = this.cardVoirie(centre);
		btnVoirie.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                	SaisirDureeStationnement frameVoirie = new SaisirDureeStationnement();
                	frameVoirie.setVisible(true);
                    dispose();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

		// Listeners
		btnParking.addActionListener(e -> {
		});
		btnVoirie.addActionListener(e -> {
		});
	}

	private JPanel EnteteDeLaFenetre() {
		JPanel header = new JPanel(new FlowLayout(FlowLayout.LEFT));
		header.setBackground(Color.WHITE);

		JLabel lblIcon = new JLabel("🅿️");
		lblIcon.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 32));
		header.add(lblIcon);

		JPanel texte = new JPanel();
		texte.setBackground(Color.WHITE);
		texte.setLayout(new BoxLayout(texte, BoxLayout.Y_AXIS));

		JLabel lblTitre = new JLabel("Choisissez votre type de stationnement");
		lblTitre.setFont(new Font("Segoe UI", Font.BOLD, 22));
		lblTitre.setForeground(new Color(40, 40, 40));

		JLabel lblSousTitre = new JLabel("Sélectionnez une option pour continuer");
		lblSousTitre.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblSousTitre.setForeground(new Color(100, 100, 100));

		texte.add(lblTitre);
		texte.add(lblSousTitre);
		header.add(texte);
		return header;
	}

	private JButton cardParking(JPanel centre) {
		JPanel cardParking = this.crerCard();
		centre.add(cardParking);

		JLabel lblParkingTitre = new JLabel("Stationnement Parking");
		lblParkingTitre.setFont(new Font("Segoe UI", Font.BOLD, 16));

		JLabel lblParkingInfo = new JLabel("Stationner dans un parking au choix");
		lblParkingInfo.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblParkingInfo.setForeground(new Color(80, 80, 80));

		cardParking.add(lblParkingTitre);
		cardParking.add(lblParkingInfo);
		cardParking.add(Box.createRigidArea(new Dimension(0, 10)));
		cardParking.add(this.btnParking);
		return this.btnParking;
	}

	private JButton cardVoirie(JPanel centre) {
		JPanel cardVoirie = this.crerCard();
		centre.add(cardVoirie);

		JLabel lblVoirieTitre = new JLabel("Stationnement en Voirie");
		lblVoirieTitre.setFont(new Font("Segoe UI", Font.BOLD, 16));

		JLabel lblVoirieInfo = new JLabel("Stationner en voirie dans une zone au choix");
		lblVoirieInfo.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblVoirieInfo.setForeground(new Color(80, 80, 80));

		cardVoirie.add(lblVoirieTitre);
		cardVoirie.add(lblVoirieInfo);
		cardVoirie.add(Box.createRigidArea(new Dimension(0, 10)));
		cardVoirie.add(this.btnVoirie);
		return this.btnVoirie;
	}

	// Metodes utilitaires pour controleur

	private JPanel crerCard() {
		JPanel card = new JPanel();
		card.setBackground(Color.WHITE);
		card.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createLineBorder(new Color(220, 220, 220), 1, true), new EmptyBorder(20, 20, 20, 20)));
		card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
		return card;
	}

	private JButton crerButton(String texte) {
		JButton btn = new JButton(texte);
		btn.setForeground(Color.WHITE);
		btn.setBackground(new Color(0, 128, 255));
		btn.setFocusPainted(false);
		btn.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		btn.setAlignmentX(CENTER_ALIGNMENT);
		btn.setPreferredSize(new Dimension(180, 40));
		return btn;
	}

	public JButton getBtnParking() {
		return this.btnParking;
	}

	public JButton getBtnVoirie() {
		return this.btnVoirie;
	}

}