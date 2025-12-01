package vue;

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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.BoxLayout;
import javax.swing.BorderFactory;
import javax.swing.Box;

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
        ParkingPanel place1 = new ParkingPanel("Place Capitole", "Centre Ville, Toulouse", "09 - 10", "59/100", "10", "Ouvert");
        ParkingPanel place2 = new ParkingPanel("Place Capitoe", "Centre Ville, Toulouse", "09 - 10", "59/100", "10", "Fermé");
        
        List<ParkingPanel> p = new ArrayList<ParkingPanel>();
        p.add(place1);
        p.add(place2);
        ListeParkings listeParkings = new ListeParkings(p);
        contentPane.add(listeParkings, BorderLayout.CENTER);
        
        JPanel panel = new JPanel();
        contentPane.add(panel, BorderLayout.SOUTH);
        
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
            public void actionPerformed(ActionEvent e) {
                try {
                    SaisirHeureArriveParking framePaiement = new SaisirHeureArriveParking();
                    framePaiement.setVisible(true);
                    dispose();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
        }
       
        
    }
