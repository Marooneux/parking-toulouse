package controleur;

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
import vue.SaisirHeureArriveParking;

public class ControleurChoixParking {

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

		MySQLDataSource.creerAcces("root", "claudio");

		this.afficherParkings();
		vue.setVisible(true);
	}

	private void chargerDonneesInitiales() {
		try {
			this.listeComplete = this.daoParking.findAll();
			// Au départ, on affiche tout
			this.listeAffichee = new ArrayList<>(this.listeComplete);
			this.afficherParkings();
		} catch (SQLException e) {
			e.printStackTrace();
			System.err.println("Erreur de connexion");
		}
	}

	private void initialiserEcouteurs() {
		// Barre de recherche
		this.vue.getTxtRecherche().addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				ControleurChoixParking.this.filtrerParkings();
			}
		});

		// Options de tri
		this.vue.getItemAlpha().addActionListener(e -> {
			Collections.sort(this.listeAffichee, Comparator.comparing(Parking::getNom));
			this.afficherParkings();
		});

		this.vue.getItemPlaces().addActionListener(e -> {
			Collections.sort(this.listeAffichee,
					Comparator.comparingInt(p -> p.getCapacite() - p.getNbPlacesOccupees()));
			this.afficherParkings();
		});

		this.vue.getItemFermeture().addActionListener(e -> {
			Collections.sort(this.listeAffichee, Comparator.comparing(Parking::getHoraireFermeture));
			this.afficherParkings();
		});
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

	private void onParkingSelected(Parking parking) {
		SaisirHeureArriveParking vueSuivante = new SaisirHeureArriveParking(parking);
		vueSuivante.setVisible(true);
		this.vue.dispose();
	}

	public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater(() -> {
			ChoixParking vue = new ChoixParking();
			new ControleurChoixParking(vue);
		});
	}
}