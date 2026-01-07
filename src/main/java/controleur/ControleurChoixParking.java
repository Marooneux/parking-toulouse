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

        MySQLDataSource.creerAcces("root", "admin");

        chargerDonneesInitiales();
        initialiserEcouteurs();
        vue.setVisible(true);
    }

    private void chargerDonneesInitiales() {
        try {
            this.listeComplete = daoParking.findAll();
            // Au départ, on affiche tout
            this.listeAffichee = new ArrayList<>(listeComplete);
            afficherParkings();
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Erreur de connexion");
        }
    }
    
    private void initialiserEcouteurs() {
        // Barre de recherche
        vue.getTxtRecherche().addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                filtrerParkings();
            }
        });

        // Options de tri
        vue.getItemAlpha().addActionListener(e -> {
            Collections.sort(listeAffichee, Comparator.comparing(Parking::getNom));
            afficherParkings();
        });

        vue.getItemPlaces().addActionListener(e -> {
            Collections.sort(listeAffichee, Comparator.comparingInt(p -> p.getNbPlacesMax() - p.getNbPlacesOccupees()));
            afficherParkings();
        });

        vue.getItemFermeture().addActionListener(e -> {
            Collections.sort(listeAffichee, Comparator.comparing(Parking::getHeureFermeture));
            afficherParkings();
        });
    }

    private void filtrerParkings() {
        String recherche = vue.getTxtRecherche().getText().toLowerCase().trim();
        
        listeAffichee.clear();
        
        if (recherche.isEmpty()) {
            listeAffichee.addAll(listeComplete);
        } else {
            for (Parking p : listeComplete) {
                if (p.getNom().toLowerCase().contains(recherche) || 
                    p.getAdresse().toLowerCase().contains(recherche)) {
                    listeAffichee.add(p);
                }
            }
        }
        afficherParkings();
    }

    private void afficherParkings() {
        vue.viderGrille();
        if (listeAffichee != null) {
            for (Parking p : listeAffichee) {
                vue.addParking(p, this::onParkingSelected);
            }
        }
    }

    private void onParkingSelected(Parking parking) {
        SaisirHeureArriveParking vueSuivante = new SaisirHeureArriveParking(parking);
        vueSuivante.setVisible(true);
        vue.dispose();
    }

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(() -> {
            ChoixParking vue = new ChoixParking();
            new ControleurChoixParking(vue);
        });
    }
}