package VUE;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.GridLayout;
import javax.swing.ImageIcon;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.BoxLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Color;

public class ChoixPlaceStationnement extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ChoixPlaceStationnement frame = new ChoixPlaceStationnement();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ChoixPlaceStationnement() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 506, 379);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.NORTH);
		
		JLabel lblTitre = new JLabel("Stationnement en voirie");
		lblTitre.setHorizontalAlignment(SwingConstants.LEFT);
		panel.add(lblTitre);
		
		JPanel listeParkings = new JPanel();
		listeParkings.setBackground(new Color(240, 240, 240));
		contentPane.add(listeParkings, BorderLayout.CENTER);
		listeParkings.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JPanel place1 = new JPanel();
		place1.setBackground(new Color(192, 192, 192));
		listeParkings.add(place1);
		GridBagLayout gbl_place1 = new GridBagLayout();
		gbl_place1.columnWidths = new int[] {108};
		gbl_place1.rowHeights = new int[] {44};
		gbl_place1.columnWeights = new double[]{0.0};
		gbl_place1.rowWeights = new double[]{0.0, 0.0};
		place1.setLayout(gbl_place1);
		
		JPanel header = new JPanel();
		header.setOpaque(false);
		GridBagConstraints gbc_header = new GridBagConstraints();
		gbc_header.anchor = GridBagConstraints.WEST;
		gbc_header.insets = new Insets(0, 0, 5, 0);
		gbc_header.gridx = 0;
		gbc_header.gridy = 0;
		place1.add(header, gbc_header);
		
		JLabel lblNomPlace = new JLabel("Place du Capitole");
		lblNomPlace.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNomPlace.setIcon(new ImageIcon("C:\\Users\\Etudiant\\Downloads\\location (1).png"));
		header.add(lblNomPlace);
		
		JPanel body = new JPanel();
		body.setOpaque(false);
		GridBagConstraints gbc_body = new GridBagConstraints();
		gbc_body.anchor = GridBagConstraints.NORTHWEST;
		gbc_body.gridx = 0;
		gbc_body.gridy = 1;
		place1.add(body, gbc_body);
		GridBagLayout gbl_body = new GridBagLayout();
		gbl_body.columnWidths = new int[]{122, 0};
		gbl_body.rowHeights = new int[]{34, 0, 0, 0, 0};
		gbl_body.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_body.rowWeights = new double[]{0.0, 1.0, 1.0, 1.0, Double.MIN_VALUE};
		body.setLayout(gbl_body);
		
		JPanel localisation = new JPanel();
		localisation.setOpaque(false);
		GridBagConstraints gbc_localisation = new GridBagConstraints();
		gbc_localisation.insets = new Insets(0, 0, 5, 0);
		gbc_localisation.anchor = GridBagConstraints.NORTHWEST;
		gbc_localisation.gridx = 0;
		gbc_localisation.gridy = 0;
		body.add(localisation, gbc_localisation);
		GridBagLayout gbl_localisation = new GridBagLayout();
		gbl_localisation.columnWidths = new int[]{0, 210, 0};
		gbl_localisation.rowHeights = new int[]{0, 0, 0};
		gbl_localisation.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		gbl_localisation.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		localisation.setLayout(gbl_localisation);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\Etudiant\\Downloads\\location (1).png"));
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 0;
		localisation.add(lblNewLabel, gbc_lblNewLabel);
		
		JLabel lblLocalisation = new JLabel("Localisation");
		lblLocalisation.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblLocalisation.setHorizontalAlignment(SwingConstants.LEFT);
		GridBagConstraints gbc_lblLocalisation = new GridBagConstraints();
		gbc_lblLocalisation.anchor = GridBagConstraints.WEST;
		gbc_lblLocalisation.insets = new Insets(0, 0, 5, 0);
		gbc_lblLocalisation.gridx = 1;
		gbc_lblLocalisation.gridy = 0;
		localisation.add(lblLocalisation, gbc_lblLocalisation);
		
		JLabel lblNomLocalisation = new JLabel("Centre Ville, Toulouse");
		lblNomLocalisation.setFont(new Font("Tahoma", Font.PLAIN, 16));
		GridBagConstraints gbc_lblNomLocalisation = new GridBagConstraints();
		gbc_lblNomLocalisation.anchor = GridBagConstraints.WEST;
		gbc_lblNomLocalisation.gridx = 1;
		gbc_lblNomLocalisation.gridy = 1;
		localisation.add(lblNomLocalisation, gbc_lblNomLocalisation);
		
		JPanel horaire = new JPanel();
		horaire.setOpaque(false);
		GridBagConstraints gbc_horaire = new GridBagConstraints();
		gbc_horaire.insets = new Insets(0, 0, 5, 0);
		gbc_horaire.fill = GridBagConstraints.BOTH;
		gbc_horaire.gridx = 0;
		gbc_horaire.gridy = 1;
		body.add(horaire, gbc_horaire);
		GridBagLayout gbl_horaire = new GridBagLayout();
		gbl_horaire.columnWidths = new int[]{0, 0, 0};
		gbl_horaire.rowHeights = new int[]{0, 0, 0, 0};
		gbl_horaire.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		gbl_horaire.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		horaire.setLayout(gbl_horaire);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon("C:\\Users\\Etudiant\\Downloads\\location (1).png"));
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1.gridx = 0;
		gbc_lblNewLabel_1.gridy = 0;
		horaire.add(lblNewLabel_1, gbc_lblNewLabel_1);
		
		JLabel lblHoraires = new JLabel("Horaire");
		lblHoraires.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblHoraires.setHorizontalAlignment(SwingConstants.LEFT);
		GridBagConstraints gbc_lblHoraires = new GridBagConstraints();
		gbc_lblHoraires.anchor = GridBagConstraints.WEST;
		gbc_lblHoraires.insets = new Insets(0, 0, 5, 0);
		gbc_lblHoraires.gridx = 1;
		gbc_lblHoraires.gridy = 0;
		horaire.add(lblHoraires, gbc_lblHoraires);
		
		JLabel lblHeure = new JLabel("09H00 - 19H00");
		lblHeure.setFont(new Font("Tahoma", Font.PLAIN, 16));
		GridBagConstraints gbc_lblHeure = new GridBagConstraints();
		gbc_lblHeure.insets = new Insets(0, 0, 5, 0);
		gbc_lblHeure.gridx = 1;
		gbc_lblHeure.gridy = 1;
		horaire.add(lblHeure, gbc_lblHeure);
		
		JLabel lblStatus = new JLabel("Ouvert");
		lblStatus.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblStatus.setHorizontalAlignment(SwingConstants.LEFT);
		GridBagConstraints gbc_lblStatus = new GridBagConstraints();
		gbc_lblStatus.anchor = GridBagConstraints.WEST;
		gbc_lblStatus.gridx = 1;
		gbc_lblStatus.gridy = 2;
		horaire.add(lblStatus, gbc_lblStatus);
		
		JPanel nbPlaces = new JPanel();
		nbPlaces.setOpaque(false);
		GridBagConstraints gbc_nbPlaces = new GridBagConstraints();
		gbc_nbPlaces.insets = new Insets(0, 0, 5, 0);
		gbc_nbPlaces.fill = GridBagConstraints.BOTH;
		gbc_nbPlaces.gridx = 0;
		gbc_nbPlaces.gridy = 2;
		body.add(nbPlaces, gbc_nbPlaces);
		GridBagLayout gbl_nbPlaces = new GridBagLayout();
		gbl_nbPlaces.columnWidths = new int[]{0, 0, 0};
		gbl_nbPlaces.rowHeights = new int[]{0, 0, 0};
		gbl_nbPlaces.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		gbl_nbPlaces.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		nbPlaces.setLayout(gbl_nbPlaces);
		
		JLabel lblNewLabel_4 = new JLabel("");
		lblNewLabel_4.setIcon(new ImageIcon("C:\\Users\\Etudiant\\Downloads\\location (1).png"));
		GridBagConstraints gbc_lblNewLabel_4 = new GridBagConstraints();
		gbc_lblNewLabel_4.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_4.gridx = 0;
		gbc_lblNewLabel_4.gridy = 0;
		nbPlaces.add(lblNewLabel_4, gbc_lblNewLabel_4);
		
		JLabel lblNbPlaces = new JLabel("Nombre des places");
		lblNbPlaces.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNbPlaces.setHorizontalAlignment(SwingConstants.LEFT);
		GridBagConstraints gbc_lblNbPlaces = new GridBagConstraints();
		gbc_lblNbPlaces.anchor = GridBagConstraints.WEST;
		gbc_lblNbPlaces.insets = new Insets(0, 0, 5, 0);
		gbc_lblNbPlaces.gridx = 1;
		gbc_lblNbPlaces.gridy = 0;
		nbPlaces.add(lblNbPlaces, gbc_lblNbPlaces);
		
		JLabel lblPlacesDisponibles = new JLabel("54/130");
		lblPlacesDisponibles.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblPlacesDisponibles.setHorizontalAlignment(SwingConstants.LEFT);
		GridBagConstraints gbc_lblPlacesDisponibles = new GridBagConstraints();
		gbc_lblPlacesDisponibles.anchor = GridBagConstraints.WEST;
		gbc_lblPlacesDisponibles.gridx = 1;
		gbc_lblPlacesDisponibles.gridy = 1;
		nbPlaces.add(lblPlacesDisponibles, gbc_lblPlacesDisponibles);
		
		JPanel tarif = new JPanel();
		tarif.setOpaque(false);
		GridBagConstraints gbc_tarif = new GridBagConstraints();
		gbc_tarif.fill = GridBagConstraints.BOTH;
		gbc_tarif.gridx = 0;
		gbc_tarif.gridy = 3;
		body.add(tarif, gbc_tarif);
		GridBagLayout gbl_tarif = new GridBagLayout();
		gbl_tarif.columnWidths = new int[]{22, 187, 0};
		gbl_tarif.rowHeights = new int[]{13, 0};
		gbl_tarif.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		gbl_tarif.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		tarif.setLayout(gbl_tarif);
		
		JLabel lblTarif = new JLabel("Tarif");
		lblTarif.setFont(new Font("Tahoma", Font.PLAIN, 14));
		GridBagConstraints gbc_lblTarif = new GridBagConstraints();
		gbc_lblTarif.anchor = GridBagConstraints.NORTHWEST;
		gbc_lblTarif.insets = new Insets(0, 0, 0, 5);
		gbc_lblTarif.gridx = 0;
		gbc_lblTarif.gridy = 0;
		tarif.add(lblTarif, gbc_lblTarif);
		
		JLabel lblNewLabel_3 = new JLabel("2.5€/H");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_lblNewLabel_3 = new GridBagConstraints();
		gbc_lblNewLabel_3.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblNewLabel_3.anchor = GridBagConstraints.NORTH;
		gbc_lblNewLabel_3.gridx = 1;
		gbc_lblNewLabel_3.gridy = 0;
		tarif.add(lblNewLabel_3, gbc_lblNewLabel_3);
		
		JPanel place1_1 = new JPanel();
		place1_1.setBackground(Color.LIGHT_GRAY);
		listeParkings.add(place1_1);
		GridBagLayout gbl_place1_1 = new GridBagLayout();
		gbl_place1_1.columnWidths = new int[]{108, 0};
		gbl_place1_1.rowHeights = new int[]{44, 0, 0};
		gbl_place1_1.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_place1_1.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		place1_1.setLayout(gbl_place1_1);
		
		JPanel header_1 = new JPanel();
		header_1.setOpaque(false);
		GridBagConstraints gbc_header_1 = new GridBagConstraints();
		gbc_header_1.anchor = GridBagConstraints.WEST;
		gbc_header_1.insets = new Insets(0, 0, 5, 0);
		gbc_header_1.gridx = 0;
		gbc_header_1.gridy = 0;
		place1_1.add(header_1, gbc_header_1);
		
		JLabel lblNomPlace_1 = new JLabel("Place du Capitole");
		lblNomPlace_1.setIcon(new ImageIcon("C:\\Users\\Etudiant\\Downloads\\location (1).png"));
		lblNomPlace_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		header_1.add(lblNomPlace_1);
		
		JPanel body_1 = new JPanel();
		body_1.setOpaque(false);
		GridBagConstraints gbc_body_1 = new GridBagConstraints();
		gbc_body_1.anchor = GridBagConstraints.NORTHWEST;
		gbc_body_1.gridx = 0;
		gbc_body_1.gridy = 1;
		place1_1.add(body_1, gbc_body_1);
		GridBagLayout gbl_body_1 = new GridBagLayout();
		gbl_body_1.columnWidths = new int[]{122, 0};
		gbl_body_1.rowHeights = new int[]{34, 0, 0, 0, 0};
		gbl_body_1.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_body_1.rowWeights = new double[]{0.0, 1.0, 1.0, 1.0, Double.MIN_VALUE};
		body_1.setLayout(gbl_body_1);
		
		JPanel localisation_1 = new JPanel();
		localisation_1.setOpaque(false);
		GridBagConstraints gbc_localisation_1 = new GridBagConstraints();
		gbc_localisation_1.anchor = GridBagConstraints.NORTHWEST;
		gbc_localisation_1.insets = new Insets(0, 0, 5, 0);
		gbc_localisation_1.gridx = 0;
		gbc_localisation_1.gridy = 0;
		body_1.add(localisation_1, gbc_localisation_1);
		GridBagLayout gbl_localisation_1 = new GridBagLayout();
		gbl_localisation_1.columnWidths = new int[]{0, 210, 0};
		gbl_localisation_1.rowHeights = new int[]{0, 0, 0};
		gbl_localisation_1.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		gbl_localisation_1.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		localisation_1.setLayout(gbl_localisation_1);
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon("C:\\Users\\Etudiant\\Downloads\\location (1).png"));
		GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
		gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_2.gridx = 0;
		gbc_lblNewLabel_2.gridy = 0;
		localisation_1.add(lblNewLabel_2, gbc_lblNewLabel_2);
		
		JLabel lblLocalisation_1 = new JLabel("Localisation");
		lblLocalisation_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblLocalisation_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		GridBagConstraints gbc_lblLocalisation_1 = new GridBagConstraints();
		gbc_lblLocalisation_1.anchor = GridBagConstraints.WEST;
		gbc_lblLocalisation_1.insets = new Insets(0, 0, 5, 0);
		gbc_lblLocalisation_1.gridx = 1;
		gbc_lblLocalisation_1.gridy = 0;
		localisation_1.add(lblLocalisation_1, gbc_lblLocalisation_1);
		
		JLabel lblNomLocalisation_1 = new JLabel("Centre Ville, Toulouse");
		lblNomLocalisation_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		GridBagConstraints gbc_lblNomLocalisation_1 = new GridBagConstraints();
		gbc_lblNomLocalisation_1.anchor = GridBagConstraints.WEST;
		gbc_lblNomLocalisation_1.gridx = 1;
		gbc_lblNomLocalisation_1.gridy = 1;
		localisation_1.add(lblNomLocalisation_1, gbc_lblNomLocalisation_1);
		
		JPanel horaire_1 = new JPanel();
		horaire_1.setOpaque(false);
		GridBagConstraints gbc_horaire_1 = new GridBagConstraints();
		gbc_horaire_1.fill = GridBagConstraints.BOTH;
		gbc_horaire_1.insets = new Insets(0, 0, 5, 0);
		gbc_horaire_1.gridx = 0;
		gbc_horaire_1.gridy = 1;
		body_1.add(horaire_1, gbc_horaire_1);
		GridBagLayout gbl_horaire_1 = new GridBagLayout();
		gbl_horaire_1.columnWidths = new int[]{0, 0, 0};
		gbl_horaire_1.rowHeights = new int[]{0, 0, 0, 0};
		gbl_horaire_1.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		gbl_horaire_1.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		horaire_1.setLayout(gbl_horaire_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("");
		lblNewLabel_1_1.setIcon(new ImageIcon("C:\\Users\\Etudiant\\Downloads\\location (1).png"));
		GridBagConstraints gbc_lblNewLabel_1_1 = new GridBagConstraints();
		gbc_lblNewLabel_1_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1_1.gridx = 0;
		gbc_lblNewLabel_1_1.gridy = 0;
		horaire_1.add(lblNewLabel_1_1, gbc_lblNewLabel_1_1);
		
		JLabel lblHoraires_1 = new JLabel("Horaire");
		lblHoraires_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblHoraires_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		GridBagConstraints gbc_lblHoraires_1 = new GridBagConstraints();
		gbc_lblHoraires_1.anchor = GridBagConstraints.WEST;
		gbc_lblHoraires_1.insets = new Insets(0, 0, 5, 0);
		gbc_lblHoraires_1.gridx = 1;
		gbc_lblHoraires_1.gridy = 0;
		horaire_1.add(lblHoraires_1, gbc_lblHoraires_1);
		
		JLabel lblHeure_1 = new JLabel("09H00 - 19H00");
		lblHeure_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		GridBagConstraints gbc_lblHeure_1 = new GridBagConstraints();
		gbc_lblHeure_1.insets = new Insets(0, 0, 5, 0);
		gbc_lblHeure_1.gridx = 1;
		gbc_lblHeure_1.gridy = 1;
		horaire_1.add(lblHeure_1, gbc_lblHeure_1);
		
		JLabel lblStatus_1 = new JLabel("Ouvert");
		lblStatus_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblStatus_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		GridBagConstraints gbc_lblStatus_1 = new GridBagConstraints();
		gbc_lblStatus_1.anchor = GridBagConstraints.WEST;
		gbc_lblStatus_1.gridx = 1;
		gbc_lblStatus_1.gridy = 2;
		horaire_1.add(lblStatus_1, gbc_lblStatus_1);
		
		JPanel nbPlaces_1 = new JPanel();
		nbPlaces_1.setOpaque(false);
		GridBagConstraints gbc_nbPlaces_1 = new GridBagConstraints();
		gbc_nbPlaces_1.fill = GridBagConstraints.BOTH;
		gbc_nbPlaces_1.insets = new Insets(0, 0, 5, 0);
		gbc_nbPlaces_1.gridx = 0;
		gbc_nbPlaces_1.gridy = 2;
		body_1.add(nbPlaces_1, gbc_nbPlaces_1);
		GridBagLayout gbl_nbPlaces_1 = new GridBagLayout();
		gbl_nbPlaces_1.columnWidths = new int[]{0, 0, 0};
		gbl_nbPlaces_1.rowHeights = new int[]{0, 0, 0};
		gbl_nbPlaces_1.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		gbl_nbPlaces_1.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		nbPlaces_1.setLayout(gbl_nbPlaces_1);
		
		JLabel lblNewLabel_4_1 = new JLabel("");
		lblNewLabel_4_1.setIcon(new ImageIcon("C:\\Users\\Etudiant\\Downloads\\location (1).png"));
		GridBagConstraints gbc_lblNewLabel_4_1 = new GridBagConstraints();
		gbc_lblNewLabel_4_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_4_1.gridx = 0;
		gbc_lblNewLabel_4_1.gridy = 0;
		nbPlaces_1.add(lblNewLabel_4_1, gbc_lblNewLabel_4_1);
		
		JLabel lblNbPlaces_1 = new JLabel("Nombre des places");
		lblNbPlaces_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblNbPlaces_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		GridBagConstraints gbc_lblNbPlaces_1 = new GridBagConstraints();
		gbc_lblNbPlaces_1.anchor = GridBagConstraints.WEST;
		gbc_lblNbPlaces_1.insets = new Insets(0, 0, 5, 0);
		gbc_lblNbPlaces_1.gridx = 1;
		gbc_lblNbPlaces_1.gridy = 0;
		nbPlaces_1.add(lblNbPlaces_1, gbc_lblNbPlaces_1);
		
		JLabel lblPlacesDisponibles_1 = new JLabel("54/130");
		lblPlacesDisponibles_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblPlacesDisponibles_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		GridBagConstraints gbc_lblPlacesDisponibles_1 = new GridBagConstraints();
		gbc_lblPlacesDisponibles_1.anchor = GridBagConstraints.WEST;
		gbc_lblPlacesDisponibles_1.gridx = 1;
		gbc_lblPlacesDisponibles_1.gridy = 1;
		nbPlaces_1.add(lblPlacesDisponibles_1, gbc_lblPlacesDisponibles_1);
		
		JPanel tarif_1 = new JPanel();
		tarif_1.setOpaque(false);
		GridBagConstraints gbc_tarif_1 = new GridBagConstraints();
		gbc_tarif_1.fill = GridBagConstraints.BOTH;
		gbc_tarif_1.gridx = 0;
		gbc_tarif_1.gridy = 3;
		body_1.add(tarif_1, gbc_tarif_1);
		GridBagLayout gbl_tarif_1 = new GridBagLayout();
		gbl_tarif_1.columnWidths = new int[]{22, 187, 0};
		gbl_tarif_1.rowHeights = new int[]{13, 0};
		gbl_tarif_1.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		gbl_tarif_1.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		tarif_1.setLayout(gbl_tarif_1);
		
		JLabel lblTarif_1 = new JLabel("Tarif");
		lblTarif_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		GridBagConstraints gbc_lblTarif_1 = new GridBagConstraints();
		gbc_lblTarif_1.anchor = GridBagConstraints.NORTHWEST;
		gbc_lblTarif_1.insets = new Insets(0, 0, 0, 5);
		gbc_lblTarif_1.gridx = 0;
		gbc_lblTarif_1.gridy = 0;
		tarif_1.add(lblTarif_1, gbc_lblTarif_1);
		
		JLabel lblNewLabel_3_1 = new JLabel("2.5€/H");
		lblNewLabel_3_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_3_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		GridBagConstraints gbc_lblNewLabel_3_1 = new GridBagConstraints();
		gbc_lblNewLabel_3_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblNewLabel_3_1.anchor = GridBagConstraints.NORTH;
		gbc_lblNewLabel_3_1.gridx = 1;
		gbc_lblNewLabel_3_1.gridy = 0;
		tarif_1.add(lblNewLabel_3_1, gbc_lblNewLabel_3_1);
		
		JPanel place1_2 = new JPanel();
		place1_2.setBackground(Color.LIGHT_GRAY);
		listeParkings.add(place1_2);
		GridBagLayout gbl_place1_2 = new GridBagLayout();
		gbl_place1_2.columnWidths = new int[]{108, 0};
		gbl_place1_2.rowHeights = new int[]{44, 0, 0};
		gbl_place1_2.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_place1_2.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		place1_2.setLayout(gbl_place1_2);
		
		JPanel header_2 = new JPanel();
		header_2.setOpaque(false);
		GridBagConstraints gbc_header_2 = new GridBagConstraints();
		gbc_header_2.anchor = GridBagConstraints.WEST;
		gbc_header_2.insets = new Insets(0, 0, 5, 0);
		gbc_header_2.gridx = 0;
		gbc_header_2.gridy = 0;
		place1_2.add(header_2, gbc_header_2);
		
		JLabel lblNomPlace_2 = new JLabel("Place du Capitole");
		lblNomPlace_2.setIcon(new ImageIcon("C:\\Users\\Etudiant\\Downloads\\location (1).png"));
		lblNomPlace_2.setFont(new Font("Tahoma", Font.PLAIN, 18));
		header_2.add(lblNomPlace_2);
		
		JPanel body_2 = new JPanel();
		body_2.setOpaque(false);
		GridBagConstraints gbc_body_2 = new GridBagConstraints();
		gbc_body_2.anchor = GridBagConstraints.NORTHWEST;
		gbc_body_2.gridx = 0;
		gbc_body_2.gridy = 1;
		place1_2.add(body_2, gbc_body_2);
		GridBagLayout gbl_body_2 = new GridBagLayout();
		gbl_body_2.columnWidths = new int[]{122, 0};
		gbl_body_2.rowHeights = new int[]{34, 0, 0, 0, 0};
		gbl_body_2.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_body_2.rowWeights = new double[]{0.0, 1.0, 1.0, 1.0, Double.MIN_VALUE};
		body_2.setLayout(gbl_body_2);
		
		JPanel localisation_2 = new JPanel();
		localisation_2.setOpaque(false);
		GridBagConstraints gbc_localisation_2 = new GridBagConstraints();
		gbc_localisation_2.anchor = GridBagConstraints.NORTHWEST;
		gbc_localisation_2.insets = new Insets(0, 0, 5, 0);
		gbc_localisation_2.gridx = 0;
		gbc_localisation_2.gridy = 0;
		body_2.add(localisation_2, gbc_localisation_2);
		GridBagLayout gbl_localisation_2 = new GridBagLayout();
		gbl_localisation_2.columnWidths = new int[]{0, 210, 0};
		gbl_localisation_2.rowHeights = new int[]{0, 0, 0};
		gbl_localisation_2.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		gbl_localisation_2.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		localisation_2.setLayout(gbl_localisation_2);
		
		JLabel lblNewLabel_5 = new JLabel("");
		lblNewLabel_5.setIcon(new ImageIcon("C:\\Users\\Etudiant\\Downloads\\location (1).png"));
		GridBagConstraints gbc_lblNewLabel_5 = new GridBagConstraints();
		gbc_lblNewLabel_5.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_5.gridx = 0;
		gbc_lblNewLabel_5.gridy = 0;
		localisation_2.add(lblNewLabel_5, gbc_lblNewLabel_5);
		
		JLabel lblLocalisation_2 = new JLabel("Localisation");
		lblLocalisation_2.setHorizontalAlignment(SwingConstants.LEFT);
		lblLocalisation_2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		GridBagConstraints gbc_lblLocalisation_2 = new GridBagConstraints();
		gbc_lblLocalisation_2.anchor = GridBagConstraints.WEST;
		gbc_lblLocalisation_2.insets = new Insets(0, 0, 5, 0);
		gbc_lblLocalisation_2.gridx = 1;
		gbc_lblLocalisation_2.gridy = 0;
		localisation_2.add(lblLocalisation_2, gbc_lblLocalisation_2);
		
		JLabel lblNomLocalisation_2 = new JLabel("Centre Ville, Toulouse");
		lblNomLocalisation_2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		GridBagConstraints gbc_lblNomLocalisation_2 = new GridBagConstraints();
		gbc_lblNomLocalisation_2.anchor = GridBagConstraints.WEST;
		gbc_lblNomLocalisation_2.gridx = 1;
		gbc_lblNomLocalisation_2.gridy = 1;
		localisation_2.add(lblNomLocalisation_2, gbc_lblNomLocalisation_2);
		
		JPanel horaire_2 = new JPanel();
		horaire_2.setOpaque(false);
		GridBagConstraints gbc_horaire_2 = new GridBagConstraints();
		gbc_horaire_2.fill = GridBagConstraints.BOTH;
		gbc_horaire_2.insets = new Insets(0, 0, 5, 0);
		gbc_horaire_2.gridx = 0;
		gbc_horaire_2.gridy = 1;
		body_2.add(horaire_2, gbc_horaire_2);
		GridBagLayout gbl_horaire_2 = new GridBagLayout();
		gbl_horaire_2.columnWidths = new int[]{0, 0, 0};
		gbl_horaire_2.rowHeights = new int[]{0, 0, 0, 0};
		gbl_horaire_2.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		gbl_horaire_2.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		horaire_2.setLayout(gbl_horaire_2);
		
		JLabel lblNewLabel_1_2 = new JLabel("");
		lblNewLabel_1_2.setIcon(new ImageIcon("C:\\Users\\Etudiant\\Downloads\\location (1).png"));
		GridBagConstraints gbc_lblNewLabel_1_2 = new GridBagConstraints();
		gbc_lblNewLabel_1_2.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1_2.gridx = 0;
		gbc_lblNewLabel_1_2.gridy = 0;
		horaire_2.add(lblNewLabel_1_2, gbc_lblNewLabel_1_2);
		
		JLabel lblHoraires_2 = new JLabel("Horaire");
		lblHoraires_2.setHorizontalAlignment(SwingConstants.LEFT);
		lblHoraires_2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		GridBagConstraints gbc_lblHoraires_2 = new GridBagConstraints();
		gbc_lblHoraires_2.anchor = GridBagConstraints.WEST;
		gbc_lblHoraires_2.insets = new Insets(0, 0, 5, 0);
		gbc_lblHoraires_2.gridx = 1;
		gbc_lblHoraires_2.gridy = 0;
		horaire_2.add(lblHoraires_2, gbc_lblHoraires_2);
		
		JLabel lblHeure_2 = new JLabel("09H00 - 19H00");
		lblHeure_2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		GridBagConstraints gbc_lblHeure_2 = new GridBagConstraints();
		gbc_lblHeure_2.insets = new Insets(0, 0, 5, 0);
		gbc_lblHeure_2.gridx = 1;
		gbc_lblHeure_2.gridy = 1;
		horaire_2.add(lblHeure_2, gbc_lblHeure_2);
		
		JLabel lblStatus_2 = new JLabel("Ouvert");
		lblStatus_2.setHorizontalAlignment(SwingConstants.LEFT);
		lblStatus_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		GridBagConstraints gbc_lblStatus_2 = new GridBagConstraints();
		gbc_lblStatus_2.anchor = GridBagConstraints.WEST;
		gbc_lblStatus_2.gridx = 1;
		gbc_lblStatus_2.gridy = 2;
		horaire_2.add(lblStatus_2, gbc_lblStatus_2);
		
		JPanel nbPlaces_2 = new JPanel();
		nbPlaces_2.setOpaque(false);
		GridBagConstraints gbc_nbPlaces_2 = new GridBagConstraints();
		gbc_nbPlaces_2.fill = GridBagConstraints.BOTH;
		gbc_nbPlaces_2.insets = new Insets(0, 0, 5, 0);
		gbc_nbPlaces_2.gridx = 0;
		gbc_nbPlaces_2.gridy = 2;
		body_2.add(nbPlaces_2, gbc_nbPlaces_2);
		GridBagLayout gbl_nbPlaces_2 = new GridBagLayout();
		gbl_nbPlaces_2.columnWidths = new int[]{0, 0, 0};
		gbl_nbPlaces_2.rowHeights = new int[]{0, 0, 0};
		gbl_nbPlaces_2.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		gbl_nbPlaces_2.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		nbPlaces_2.setLayout(gbl_nbPlaces_2);
		
		JLabel lblNewLabel_4_2 = new JLabel("");
		lblNewLabel_4_2.setIcon(new ImageIcon("C:\\Users\\Etudiant\\Downloads\\location (1).png"));
		GridBagConstraints gbc_lblNewLabel_4_2 = new GridBagConstraints();
		gbc_lblNewLabel_4_2.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_4_2.gridx = 0;
		gbc_lblNewLabel_4_2.gridy = 0;
		nbPlaces_2.add(lblNewLabel_4_2, gbc_lblNewLabel_4_2);
		
		JLabel lblNbPlaces_2 = new JLabel("Nombre des places");
		lblNbPlaces_2.setHorizontalAlignment(SwingConstants.LEFT);
		lblNbPlaces_2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		GridBagConstraints gbc_lblNbPlaces_2 = new GridBagConstraints();
		gbc_lblNbPlaces_2.anchor = GridBagConstraints.WEST;
		gbc_lblNbPlaces_2.insets = new Insets(0, 0, 5, 0);
		gbc_lblNbPlaces_2.gridx = 1;
		gbc_lblNbPlaces_2.gridy = 0;
		nbPlaces_2.add(lblNbPlaces_2, gbc_lblNbPlaces_2);
		
		JLabel lblPlacesDisponibles_2 = new JLabel("54/130");
		lblPlacesDisponibles_2.setHorizontalAlignment(SwingConstants.LEFT);
		lblPlacesDisponibles_2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		GridBagConstraints gbc_lblPlacesDisponibles_2 = new GridBagConstraints();
		gbc_lblPlacesDisponibles_2.anchor = GridBagConstraints.WEST;
		gbc_lblPlacesDisponibles_2.gridx = 1;
		gbc_lblPlacesDisponibles_2.gridy = 1;
		nbPlaces_2.add(lblPlacesDisponibles_2, gbc_lblPlacesDisponibles_2);
		
		JPanel tarif_2 = new JPanel();
		tarif_2.setOpaque(false);
		GridBagConstraints gbc_tarif_2 = new GridBagConstraints();
		gbc_tarif_2.fill = GridBagConstraints.BOTH;
		gbc_tarif_2.gridx = 0;
		gbc_tarif_2.gridy = 3;
		body_2.add(tarif_2, gbc_tarif_2);
		GridBagLayout gbl_tarif_2 = new GridBagLayout();
		gbl_tarif_2.columnWidths = new int[]{22, 187, 0};
		gbl_tarif_2.rowHeights = new int[]{13, 0};
		gbl_tarif_2.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		gbl_tarif_2.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		tarif_2.setLayout(gbl_tarif_2);
		
		JLabel lblTarif_2 = new JLabel("Tarif");
		lblTarif_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		GridBagConstraints gbc_lblTarif_2 = new GridBagConstraints();
		gbc_lblTarif_2.anchor = GridBagConstraints.NORTHWEST;
		gbc_lblTarif_2.insets = new Insets(0, 0, 0, 5);
		gbc_lblTarif_2.gridx = 0;
		gbc_lblTarif_2.gridy = 0;
		tarif_2.add(lblTarif_2, gbc_lblTarif_2);
		
		JLabel lblNewLabel_3_2 = new JLabel("2.5€/H");
		lblNewLabel_3_2.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_3_2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		GridBagConstraints gbc_lblNewLabel_3_2 = new GridBagConstraints();
		gbc_lblNewLabel_3_2.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblNewLabel_3_2.anchor = GridBagConstraints.NORTH;
		gbc_lblNewLabel_3_2.gridx = 1;
		gbc_lblNewLabel_3_2.gridy = 0;
		tarif_2.add(lblNewLabel_3_2, gbc_lblNewLabel_3_2);

	}
}
