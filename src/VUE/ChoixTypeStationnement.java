package VUE;

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
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class ChoixTypeStationnement extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;

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
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 450);
        setLocationRelativeTo(null);

        contentPane = new JPanel();
        contentPane.setBackground(Color.WHITE);
        contentPane.setBorder(new EmptyBorder(20, 20, 20, 20));
        contentPane.setLayout(new BorderLayout(20, 20));
        setContentPane(contentPane);

        // HEADER
        JPanel header = EnteteDeLaFenetre();

        contentPane.add(header, BorderLayout.NORTH);

        // Partie central
        JPanel centre = new JPanel();
        centre.setBackground(Color.WHITE);
        centre.setLayout(new GridLayout(1, 2, 20, 0));
        contentPane.add(centre, BorderLayout.CENTER);

        // Card 1 - Parking
        JButton btnParking = cardParking(centre);

        // Card 2 Voirie
        JButton btnVoirie = cardVoirie(centre);

        // Listeners
        btnParking.addActionListener(e -> {});
        btnVoirie.addActionListener(e -> {});
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
		JPanel cardParking = crerCard();
        centre.add(cardParking);

        JLabel lblParkingTitre = new JLabel("Stationnement Parking");
        lblParkingTitre.setFont(new Font("Segoe UI", Font.BOLD, 16));

        JLabel lblParkingInfo = new JLabel("Stationner dans un parking au choix");
        lblParkingInfo.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lblParkingInfo.setForeground(new Color(80, 80, 80));

        JButton btnParking = crerButton("Trouver un parking");

        cardParking.add(lblParkingTitre);
        cardParking.add(lblParkingInfo);
        cardParking.add(Box.createRigidArea(new Dimension(0, 10)));
        cardParking.add(btnParking);
		return btnParking;
	}

	private JButton cardVoirie(JPanel centre) {
		JPanel cardVoirie = crerCard();
        centre.add(cardVoirie);

        JLabel lblVoirieTitre = new JLabel("Stationnement en Voirie");
        lblVoirieTitre.setFont(new Font("Segoe UI", Font.BOLD, 16));

        JLabel lblVoirieInfo = new JLabel("Stationner en voirie dans une zone au choix");
        lblVoirieInfo.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lblVoirieInfo.setForeground(new Color(80, 80, 80));

        JButton btnVoirie = crerButton("Trouver un emplacement");

        cardVoirie.add(lblVoirieTitre);
        cardVoirie.add(lblVoirieInfo);
        cardVoirie.add(Box.createRigidArea(new Dimension(0, 10)));
        cardVoirie.add(btnVoirie);
		return btnVoirie;
	}

    // Metodes utilitaires pour controleur

    private JPanel crerCard() {
        JPanel card = new JPanel();
        card.setBackground(Color.WHITE);
        card.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(220, 220, 220), 1, true),
                new EmptyBorder(20, 20, 20, 20)
        ));
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
}