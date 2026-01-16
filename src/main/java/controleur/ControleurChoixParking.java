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
	private int idUser;

	public ControleurChoixParking(ChoixParking vue) {
		this(vue, 0);
	}

	public ControleurChoixParking(ChoixParking vue, int idUser) {
		this.vue = vue;
		this.idUser = idUser;
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

		vue.getItemAlpha().addActionListener(e -> {
			Collections.sort(listeAffichee, Comparator.comparing(Parking::getNom));
			afficherParkings();
		});

		vue.getItemFermeture().addActionListener(e -> {
			Collections.sort(this.listeAffichee, Comparator.comparing(Parking::getHoraireFermeture));
			this.afficherParkings();
		});
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