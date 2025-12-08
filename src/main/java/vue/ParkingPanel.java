package vue;

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

public class ParkingPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private String nomPlace;
	private String nomLocalisation;
	private String horaires;
	private String placesDisponibles;
	private String tarif;
	private String status;

	public ParkingPanel(String nomPlace, String nomLocalisation, String horaires, String placesDisponibles,
			String tarif, String status) {
		JPanel place = new JPanel();
		place.setBackground(Color.WHITE);
		place.setPreferredSize(new Dimension(260, 260));
		place.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createLineBorder(new Color(230, 230, 230), 1, true),
				new EmptyBorder(15, 15, 15, 15)));
		place.setLayout(new BorderLayout());

		// TITRE CARD
		JPanel header = new JPanel(new FlowLayout(FlowLayout.LEFT));
		header.setOpaque(false);

		JLabel lblNomPlace = new JLabel(nomPlace);
		lblNomPlace.setFont(new Font("Segoe UI", Font.BOLD, 16));

		header.add(lblNomPlace);
		place.add(header, BorderLayout.NORTH);

		// CORP CARD
		JPanel body = new JPanel();
		body.setOpaque(false);
		body.setLayout(new BoxLayout(body, BoxLayout.Y_AXIS));
		body.setBorder(new EmptyBorder(10, 0, 0, 0));
		place.add(body, BorderLayout.CENTER);

		// Localisation
		JPanel localisation = this.localisationField(nomLocalisation);
		body.add(localisation);

		// Horaire
		JPanel horaire = this.horairesField(horaires, status);
		body.add(horaire);

		// Nb Places
		JPanel nbPlaces = this.placesField(placesDisponibles);
		body.add(nbPlaces);

		// Tarif
		JPanel tarifField = this.tarifField(tarif);
		body.add(tarifField);

		this.add(place, BorderLayout.CENTER);
	}

	public String getNomPlace() {
		return this.nomPlace;
	}

	public void setNomPlace(String nomPlace) {
		this.nomPlace = nomPlace;
	}

	public String getNomLocalisation() {
		return this.nomLocalisation;
	}

	public void setNomLocalisation(String nomLocalisation) {
		this.nomLocalisation = nomLocalisation;
	}

	public String getHoraires() {
		return this.horaires;
	}

	public void setHoraires(String horaires) {
		this.horaires = horaires;
	}

	public String getPlacesDisponibles() {
		return this.placesDisponibles;
	}

	public void setPlacesDisponibles(String placesDisponibles) {
		this.placesDisponibles = placesDisponibles;
	}

	public String getTarif() {
		return this.tarif;
	}

	public void setTarif(String tarif) {
		this.tarif = tarif;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	private JPanel localisationField(String Nomlocalisation) {
		JPanel localisation = new JPanel();
		localisation.setOpaque(false);
		localisation.setLayout(new BoxLayout(localisation, BoxLayout.Y_AXIS));

		JLabel lblLocalisation = new JLabel("Localisation");
		lblLocalisation.setFont(new Font("Segoe UI", Font.BOLD, 14));

		JLabel lblNomLocalisation = new JLabel(Nomlocalisation);
		lblNomLocalisation.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblNomLocalisation.setForeground(new Color(70, 70, 70));

		localisation.add(lblLocalisation);
		localisation.add(lblNomLocalisation);
		localisation.add(Box.createRigidArea(new Dimension(0, 8)));

		return localisation;
	}

	private JPanel horairesField(String horaires, String status) {
		JPanel horaire = new JPanel();
		horaire.setOpaque(false);
		horaire.setLayout(new BoxLayout(horaire, BoxLayout.Y_AXIS));

		JLabel lblHoraires = new JLabel("Horaires");
		lblHoraires.setFont(new Font("Segoe UI", Font.BOLD, 14));

		JLabel lblHeure = new JLabel(horaires);
		lblHeure.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblHeure.setForeground(new Color(70, 70, 70));

		JLabel lblStatus = new JLabel(status);
		lblStatus.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		lblStatus.setForeground(new Color(0, 130, 0));

		horaire.add(lblHoraires);
		horaire.add(lblHeure);
		horaire.add(lblStatus);
		horaire.add(Box.createRigidArea(new Dimension(0, 8)));

		return horaire;
	}

	private JPanel placesField(String places) {
		JPanel nbPlaces = new JPanel();
		nbPlaces.setOpaque(false);
		nbPlaces.setLayout(new BoxLayout(nbPlaces, BoxLayout.Y_AXIS));

		JLabel lblNbPlaces = new JLabel("Places Disponibles");
		lblNbPlaces.setFont(new Font("Segoe UI", Font.BOLD, 14));

		JLabel lblPlacesDisponibles = new JLabel(places);
		lblPlacesDisponibles.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblPlacesDisponibles.setForeground(new Color(70, 70, 70));

		nbPlaces.add(lblNbPlaces);
		nbPlaces.add(lblPlacesDisponibles);
		nbPlaces.add(Box.createRigidArea(new Dimension(0, 8)));

		return nbPlaces;
	}

	private JPanel tarifField(String tarification) {
		JPanel tarif = new JPanel();
		tarif.setOpaque(false);
		tarif.setLayout(new BoxLayout(tarif, BoxLayout.Y_AXIS));

		JLabel lblTarif = new JLabel("Tarif");
		lblTarif.setFont(new Font("Segoe UI", Font.BOLD, 14));

		JLabel lblPrix = new JLabel(tarification);
		lblPrix.setFont(new Font("Segoe UI", Font.BOLD, 14));
		lblPrix.setForeground(new Color(70, 70, 70));

		tarif.add(lblTarif);
		tarif.add(lblPrix);

		return tarif;
	}
}