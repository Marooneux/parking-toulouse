package VUE;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import java.awt.FlowLayout;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.BoxLayout;
import javax.swing.BorderFactory;
import javax.swing.Box;

public class ChoixPlaceParking extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                ChoixPlaceParking frame = new ChoixPlaceParking();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public ChoixPlaceParking() {
        setTitle("Stationnement dans un Parking");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 600);
        setLocationRelativeTo(null);

        contentPane = new JPanel(new BorderLayout(15, 15));
        contentPane.setBorder(new EmptyBorder(20, 20, 20, 20));
        contentPane.setBackground(new Color(250, 250, 250));
        setContentPane(contentPane);

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

        contentPane.add(header, BorderLayout.NORTH);

        // LISTA DE PLACES
        PlaceParking place1 = new PlaceParking("Place Capitle", "Centre Ville, Toulouse", "09 - 10", "59/100", "10", "Ouvert");
        PlaceParking place2 = new PlaceParking("Place Capitle", "Centre Ville, oulouse", "09 - 10", "59/100", "10", "Fermé");
        
        List<PlaceParking> p = new ArrayList<PlaceParking>();
        p.add(place1);
        p.add(place2);
        ListePlacesParking listeParkings = new ListePlacesParking(p);
        contentPane.add(listeParkings, BorderLayout.CENTER);
        
        JPanel panel = new JPanel();
        contentPane.add(panel, BorderLayout.SOUTH);
        
        JPanel panelBtn = new JPanel();
        panelBtn.setBackground(Color.WHITE);

        JButton btnPayer = new JButton("Choisir Parking");
        btnPayer.setBackground(new Color(0, 128, 255));
        btnPayer.setForeground(Color.WHITE);
        btnPayer.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        btnPayer.setFocusPainted(false);
        btnPayer.setPreferredSize(new Dimension(160, 40));
        panelBtn.add(btnPayer);
        panel.add(btnPayer);
        
        
    }
}