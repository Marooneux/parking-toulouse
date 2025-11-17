package VUE;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class PlaceParking extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String nomLocalisation;
	private String horaires;
	private String placesDisponibles;
	private String tarif;

    public PlaceParking() {
        JPanel place1 = new JPanel();
        place1.setBackground(Color.WHITE);
        place1.setPreferredSize(new Dimension(260, 260));
        place1.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(230, 230, 230), 1, true),
                new EmptyBorder(15, 15, 15, 15)
        ));
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
        localisationField(body);

        // Horaire
        horairesField(body);

        // Nb Places
        placesField(body);

        // Tarif
        tarifField(body);
        
        this.add(place1, BorderLayout.CENTER);
    }

	private void localisationField(JPanel body) {
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
	}

	private void horairesField(JPanel body) {
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
	}

	private void placesField(JPanel body) {
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
	}

	private void tarifField(JPanel body) {
		JPanel tarif = new JPanel();
        tarif.setOpaque(false);
        tarif.setLayout(new BoxLayout(tarif, BoxLayout.Y_AXIS));

        JLabel lblTarif = new JLabel("Tarif");
        lblTarif.setFont(new Font("Segoe UI", Font.BOLD, 14));

        JLabel lblPrix = new JLabel("2.5€/H");
        lblPrix.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lblPrix.setForeground(new Color(70, 70, 70));

        tarif.add(lblTarif);
        tarif.add(lblPrix);
        body.add(tarif);
	}
}