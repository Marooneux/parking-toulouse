package controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import modele.Parking;
import modele.dao.DaoParking;
import modele.dao.MySQLDataSource;
import vue.ChoixParking;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ControleurChoixParking implements ActionListener {
	private static final Logger LOGGER = Logger.getLogger(ControleurChoixParking.class.getName());


	private ChoixParking vue;
	private DaoParking daoParking;

	// Liste complète chargée au démarrage
	private List<Parking> listeComplete;
	// Liste actuellement affichée (filtrée et triée)
	private List<Parking> listeAffichee;
	public ControleurChoixParking(ChoixParking vue) {
		this.vue = vue;
		this.daoParking = new DaoParking();
		this.listeComplete = new ArrayList<>();
		this.listeAffichee = new ArrayList<>();

		MySQLDataSource.creerAcces();
		this.chargerDonneesInitiales();
		this.initialiserEcouteurs();
		this.afficherParkings();
		vue.setVisible(true);
	}

	private void initialiserEcouteurs() {
		vue.getTxtRecherche().addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				filtrerParkings();
			}
		});

		vue.getItemAlpha().addActionListener(this);
		vue.getItemFermeture().addActionListener(this);
		vue.getItemPlaces().addActionListener(this);
		vue.getBtnFilter().addActionListener(this);
	}

	private void chargerDonneesInitiales() {
		try {
			this.listeComplete = this.daoParking.findAll();
			// Au départ, on affiche tout
			this.listeAffichee = new ArrayList<>(this.listeComplete);
			this.afficherParkings();
		} catch (SQLException e) {
			LOGGER.log(Level.SEVERE, e.getMessage(), e);
		}
	}

	private void filtrerParkings() {
		String recherche = this.vue.getTxtRecherche().getText().toLowerCase().trim();

		this.listeAffichee.clear();

		if (recherche.isEmpty()) {
			this.listeAffichee.addAll(this.listeComplete);
		} else {
			for (Parking p : this.listeComplete) {
				if (p.getNom().toLowerCase().contains(recherche) ||
						p.getAdresse().getRue().toLowerCase().contains(recherche)) {

					this.listeAffichee.add(p);
				}
			}
		}
		this.afficherParkings();
	}

	private void afficherParkings() {
		this.vue.viderGrille();
		if (this.listeAffichee != null) {
			for (Parking p : this.listeAffichee) {
				this.vue.addParking(p, this::onParkingSelected);
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		if (source == vue.getBtnFilter()) {
			vue.showFilterPopup();
			return;
		}

		if (source == vue.getItemAlpha()) {
			Collections.sort(listeAffichee, Comparator.comparing(Parking::getNom));
			afficherParkings();
			return;
		}

		if (source == vue.getItemFermeture()) {
			Collections.sort(this.listeAffichee, Comparator.comparing(Parking::getHoraireFermeture));
			this.afficherParkings();
			return;
		}

		if (source == vue.getItemPlaces()) {
			Collections.sort(this.listeAffichee, Comparator.comparing(p -> p.getNbPlacesMax() - p.getNbPlacesOccupees()));
			this.afficherParkings();
		}
	}

	private void onParkingSelected(Parking parking) {
		new ControleurSaisirHeureArriveParking(parking);
	}
}