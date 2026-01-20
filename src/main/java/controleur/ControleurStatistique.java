package controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;

import modele.dao.DaoStatistique;
import modele.dao.MySQLDataSource;
import vue.adminParking.Statistique;

public class ControleurStatistique implements ActionListener {

    private final Statistique vue;
    private final DaoStatistique dao;

    public ControleurStatistique(Statistique vue) {
        this.vue = vue;

        MySQLDataSource.creerAcces();

        Connection c;
        try {
            c = MySQLDataSource.getConnexion();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Connexion impossible");
        }

        this.dao = new DaoStatistique(c);

        chargerDonnees();

        vue.addRefreshListener(this);
    }

    private void chargerDonnees() {
        try {
            int month = vue.getSelectedMonth();
            int year = vue.getSelectedYear();

            vue.setMontant(dao.getMontantMois(month, year));
            vue.setSessions(dao.getTotalSessions(month, year));
            vue.setZoneFavorite(dao.getZoneFavorite(month, year));
            vue.setOccupation(dao.getOccupation(month, year));

            vue.setSessionsPerDay(dao.getSessionsPerDay(month, year));
            vue.setRevenueTrend(dao.getRevenueTrend(month, year));
            vue.setZoneDistribution(dao.getZoneDistribution(month, year));
            
            vue.setRecentActivity(dao.getRecentActivity());

            
            vue.actualiserAffichage();

        } catch (SQLException e) {
            e.printStackTrace();
            vue.showError("Erreur lors du chargement des statistiques.");
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vue.getBtnRefresh()) {
            chargerDonnees();
        }
    }
}
