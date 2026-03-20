package modele.dao;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class DaoStatistique {

    private final Connection c;

    public DaoStatistique(Connection c) {
        this.c = c;
    }

    // ------------------------------
    //  MODEL FOR RECENT ACTIVITY
    // ------------------------------
    public static class RecentActivity {
        public LocalDateTime date;
        public String lieu;
        public String details;
        public int dureeMinutes;
        public double tarif;
    }

    // ------------------------------
    //  KPI: MONTANT DU MOIS 
    // ------------------------------
    public double getParkingMontantMois(int month, int year) throws SQLException {
        String sql =
            "SELECT COALESCE(SUM(p.tarif),0) AS total " + 
            "FROM reservations_parking r " + 
            "JOIN parkings p ON r.id_parking = p.id " + 
            "JOIN admins_parkings ap ON p.id = ap.id_parking " + 
            "WHERE MONTH(r.date_arrivee)=? AND YEAR(r.date_arrivee)=? " + 
            "AND ap.id_utilisateur = 3";

        try (PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, month);
            ps.setInt(2, year);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return rs.getDouble("total");
            }
        }
        return 0;
    }

    // ------------------------------
    //  KPI: TOTAL SESSIONS 
    // ------------------------------
    public int getParkingTotalSessions(int month, int year) throws SQLException {
        String sql =
            "SELECT COUNT(*) AS nb " +
            "FROM reservations_parking r " +
            "JOIN parkings p ON r.id_parking = p.id " +
            "JOIN admins_parkings ap ON p.id = ap.id_parking " +
            "WHERE MONTH(r.date_arrivee)=? AND YEAR(r.date_arrivee)=? " +
            "AND ap.id_utilisateur = 3";

        try (PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, month);
            ps.setInt(2, year);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return rs.getInt("nb");
            }
        }
        return 0;
    }



    // ------------------------------
    //  KPI: ZONE FAVORITE 
    // ------------------------------
    public String getParkingZoneFavorite(int month, int year) {
        return "Parking";
    }

    // ------------------------------
    //  KPI: OCCUPATION 
    // ------------------------------
    public int getParkingOccupation(int month, int year) throws SQLException {
        // 1. Get total sessions
        int sessions = getParkingTotalSessions(month, year);

        // 2. Number of days in the month
        java.time.YearMonth ym = java.time.YearMonth.of(year, month);
        int days = ym.lengthOfMonth();

        // 3. Compute percentage
        if (days == 0) return 0;
        return (int) Math.round((sessions * 100.0) / days);
    }


    // ------------------------------
    //  CHART: SESSIONS PER DAY 
    // ------------------------------
    public List<Integer> getParkingSessionsPerDay(int month, int year) throws SQLException {
    	String sql =
    		    "SELECT DAYOFWEEK(r.date_arrivee) AS d, COUNT(*) AS nb " +
    		    "FROM reservations_parking r " +
    		    "JOIN parkings p ON r.id_parking = p.id " +
    		    "JOIN admins_parkings ap ON p.id = ap.id_parking " +
    		    "WHERE MONTH(r.date_arrivee)=? AND YEAR(r.date_arrivee)=? " +
    		    "AND ap.id_utilisateur = 3 " +   // ✔️ parking managed by user 3
    		    "GROUP BY d";

        int[] days = new int[7]; // 1=Dim … 7=Sam

        try (PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, month);
            ps.setInt(2, year);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    int dow = rs.getInt("d");   // 1=Dim, 2=Lun, ...
                    int count = rs.getInt("nb");
                    days[dow - 1] = count;
                }
            }
        }

        // Convert to Lun..Dim order
        return List.of(days[1], days[2], days[3], days[4], days[5], days[6], days[0]);
    }


    // ------------------------------
    //  CHART: ZONE DISTRIBUTION 
    // ------------------------------
    public List<Integer> getParkingZoneDistribution(int month, int year) {
        return List.of(0, 0, 0, 0, 0);
    }

    // ------------------------------
    //  CHART: REVENUE TREND 
    // ------------------------------
    public List<Double> getParkingRevenueTrend(int month, int year) throws SQLException {
    	String sql =
    		    "SELECT WEEK(r.date_arrivee) AS w, SUM(p.tarif) AS total " +
    		    "FROM reservations_parking r " +
    		    "JOIN parkings p ON r.id_parking = p.id " +
    		    "JOIN admins_parkings ap ON p.id = ap.id_parking " +
    		    "WHERE MONTH(r.date_arrivee)=? AND YEAR(r.date_arrivee)=? " +
    		    "AND ap.id_utilisateur = 3 " +
    		    "GROUP BY w ORDER BY w";


        List<Double> trend = new ArrayList<>();

        try (PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, month);
            ps.setInt(2, year);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    trend.add(rs.getDouble("total"));
                }
            }
        }

        return trend.isEmpty() ? List.of(0.0) : trend;
    }

    // ------------------------------
    //  RECENT ACTIVITY 
    // ------------------------------
    public List<RecentActivity> getParkingRecentActivity(int month, int year) throws SQLException {
    	String sql =
    		    "SELECT r.date_arrivee AS date, p.nom AS lieu, " +
    		    "CONCAT('Parking ', p.nom) AS details, " +
    		    "TIMESTAMPDIFF(MINUTE, r.date_arrivee, COALESCE(r.date_depart, NOW())) AS duree, " +
    		    "p.tarif AS tarif " +
    		    "FROM reservations_parking r " +
    		    "JOIN parkings p ON r.id_parking = p.id " +
    		    "JOIN admins_parkings ap ON p.id = ap.id_parking " +
    		    "WHERE MONTH(r.date_arrivee)=? AND YEAR(r.date_arrivee)=? " +
    		    "AND ap.id_utilisateur = 3 " +   // ← correct filter
    		    "ORDER BY r.date_arrivee DESC LIMIT 5";


        List<RecentActivity> list = new ArrayList<>();

        try (PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, month);
            ps.setInt(2, year);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    RecentActivity a = new RecentActivity();
                    a.date = rs.getTimestamp("date").toLocalDateTime();
                    a.lieu = rs.getString("lieu");
                    a.details = rs.getString("details");
                    a.dureeMinutes = rs.getInt("duree");
                    a.tarif = rs.getDouble("tarif");
                    list.add(a);
                }
            }
        }

        return list;
    }
}
