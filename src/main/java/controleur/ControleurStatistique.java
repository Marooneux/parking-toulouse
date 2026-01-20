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

        vue.getBtnRefresh().addActionListener(this);
    }

    private void chargerDonnees() {
        try {
            int month = vue.getSelectedMonth();
            int year = vue.getSelectedYear();

            // KPI values 
            vue.setMontant(dao.getParkingMontantMois(month, year));
            vue.setSessions(dao.getParkingTotalSessions(month, year));
            vue.setOccupation(dao.getParkingOccupation(month, year));

            // Charts 
            vue.setSessionsPerDay(dao.getParkingSessionsPerDay(month, year));
            vue.setRevenueTrend(dao.getParkingRevenueTrend(month, year));

            // Recent activity 
            java.util.List<DaoStatistique.RecentActivity> raw = dao.getParkingRecentActivity(month, year);
            java.util.List<java.util.Map<String, Object>> list = new java.util.ArrayList<>();

            for (DaoStatistique.RecentActivity a : raw) {
                java.util.Map<String, Object> m = new java.util.HashMap<>();
                m.put("lieu", a.lieu);
                m.put("duree", formatDuree(a.dureeMinutes));
                m.put("prix", a.tarif + "€");
                m.put("date", a.date.toLocalDate().toString());
                list.add(m);
            }

            vue.setRecentActivity(list);

        } catch (SQLException e) {
            e.printStackTrace();
            vue.showError("Erreur lors du chargement des statistiques parking.");
        }
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vue.getBtnRefresh()) {
            chargerDonnees();
        }
    }

    // Format duration 
    private String formatDuree(int minutes) {
        if (minutes < 60) return minutes + "min";
        int h = minutes / 60;
        int m = minutes % 60;
        return m == 0 ? h + "h" : h + "h " + m + "min";
    }
    
    
}
