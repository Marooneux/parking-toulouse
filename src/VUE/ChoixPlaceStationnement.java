// Código redesenhado com o mesmo design gráfico do primeiro arquivo, sem refatoração estrutural
// Apenas estilização visual aplicada

package VUE;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.BoxLayout;
import javax.swing.BorderFactory;
import javax.swing.Box;

public class ChoixPlaceStationnement extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                ChoixPlaceStationnement frame = new ChoixPlaceStationnement();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public ChoixPlaceStationnement() {
        setTitle("Stationnement en Voirie");
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

        JLabel lblTitre = new JLabel("Stationnement en Voirie");
        lblTitre.setFont(new Font("Segoe UI", Font.BOLD, 22));
        lblTitre.setForeground(new Color(40, 40, 40));

        JLabel lblSousTitre = new JLabel("Choisissez une place de stationnement disponible");
        lblSousTitre.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lblSousTitre.setForeground(new Color(100, 100, 100));

        texte.add(lblTitre);
        texte.add(lblSousTitre);
        header.add(texte);

        contentPane.add(header, BorderLayout.NORTH);

        // LISTA DE PLACES
        JPanel listeParkings = new JPanel();
        listeParkings.setBackground(new Color(250, 250, 250));
        listeParkings.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20));
        contentPane.add(listeParkings, BorderLayout.CENTER);

        // Geração de uma carta (sem mudar o código interno original)
        placeParking(listeParkings);
    }


    private void placeParking(JPanel listeParkings) {
        JPanel place1 = new JPanel();
        place1.setBackground(Color.WHITE);
        place1.setPreferredSize(new Dimension(260, 260));
        place1.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(230, 230, 230), 1, true),
                new EmptyBorder(15, 15, 15, 15)
        ));
        listeParkings.add(place1);
        place1.setLayout(new BorderLayout());

        // TITRE CARD
        JPanel header = new JPanel(new FlowLayout(FlowLayout.LEFT));
        header.setOpaque(false);

        JLabel lblNomPlace = new JLabel("Place du Capitole");
        lblNomPlace.setFont(new Font("Segoe UI", Font.BOLD, 16));

        header.add(lblNomPlace);
        place1.add(header, BorderLayout.NORTH);

        // CORP CARD
        JPanel body = new JPanel();
        body.setOpaque(false);
        body.setLayout(new BoxLayout(body, BoxLayout.Y_AXIS));
        body.setBorder(new EmptyBorder(10, 0, 0, 0));
        place1.add(body, BorderLayout.CENTER);

        // Localisation
        JPanel localisation = new JPanel();
        localisation.setOpaque(false);
        localisation.setLayout(new BoxLayout(localisation, BoxLayout.Y_AXIS));

        JLabel lblLocalisation = new JLabel("Localisation");
        lblLocalisation.setFont(new Font("Segoe UI", Font.BOLD, 14));

        JLabel lblNomLocalisation = new JLabel("Centre Ville, Toulouse");
        lblNomLocalisation.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lblNomLocalisation.setForeground(new Color(70, 70, 70));

        localisation.add(lblLocalisation);
        localisation.add(lblNomLocalisation);
        localisation.add(Box.createRigidArea(new Dimension(0, 8)));
        body.add(localisation);

        // Horaire
        JPanel horaire = new JPanel();
        horaire.setOpaque(false);
        horaire.setLayout(new BoxLayout(horaire, BoxLayout.Y_AXIS));

        JLabel lblHoraires = new JLabel("Horaires");
        lblHoraires.setFont(new Font("Segoe UI", Font.BOLD, 14));

        JLabel lblHeure = new JLabel("09H00 - 19H00");
        lblHeure.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lblHeure.setForeground(new Color(70, 70, 70));

        JLabel lblStatus = new JLabel("Ouvert");
        lblStatus.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        lblStatus.setForeground(new Color(0, 130, 0));

        horaire.add(lblHoraires);
        horaire.add(lblHeure);
        horaire.add(lblStatus);
        horaire.add(Box.createRigidArea(new Dimension(0, 8)));
        body.add(horaire);

        // Nb Places
        JPanel nbPlaces = new JPanel();
        nbPlaces.setOpaque(false);
        nbPlaces.setLayout(new BoxLayout(nbPlaces, BoxLayout.Y_AXIS));

        JLabel lblNbPlaces = new JLabel("Places Disponibles");
        lblNbPlaces.setFont(new Font("Segoe UI", Font.BOLD, 14));

        JLabel lblPlacesDisponibles = new JLabel("54 / 130");
        lblPlacesDisponibles.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lblPlacesDisponibles.setForeground(new Color(70, 70, 70));

        nbPlaces.add(lblNbPlaces);
        nbPlaces.add(lblPlacesDisponibles);
        nbPlaces.add(Box.createRigidArea(new Dimension(0, 8)));
        body.add(nbPlaces);

        // Tarif
        JPanel tarif = new JPanel();
        tarif.setOpaque(false);
        tarif.setLayout(new BoxLayout(tarif, BoxLayout.Y_AXIS));

        JLabel lblTarif = new JLabel("Tarif");
        lblTarif.setFont(new Font("Segoe UI", Font.BOLD, 14));

        JLabel lblPrix = new JLabel("2.5€/H");
        lblPrix.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lblPrix.setForeground(new Color(70, 70, 70));

        tarif.add(lblTarif);
        tarif.add(lblPrix);
        body.add(tarif);
    }
}