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
            "WHERE MONTH(r.date_arrivee)=? AND YEAR(r.date_arrivee)=?";

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
            "FROM reservations_parking " +
            "WHERE MONTH(date_arrivee)=? AND YEAR(date_arrivee)=?";

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
        String sql =
            "SELECT COUNT(*) AS nb " +
            "FROM reservations_parking " +
            "WHERE MONTH(date_arrivee)=? AND YEAR(date_arrivee)=?";

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
    //  CHART: SESSIONS PER DAY 
    // ------------------------------
    public List<Integer> getParkingSessionsPerDay(int month, int year) throws SQLException {
        String sql =
            "SELECT DAYOFWEEK(date_arrivee) AS d, COUNT(*) AS nb " +
            "FROM reservations_parking " +
            "WHERE MONTH(date_arrivee)=? AND YEAR(date_arrivee)=? " +
            "GROUP BY d";

        int[] days = new int[7]; // 1=Dim … 7=Sam

        try (PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, month);
            ps.setInt(2, year);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    int dow = rs.getInt("d");
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
            "SELECT WEEK(date_arrivee) AS w, SUM(p.tarif) AS total " +
            "FROM reservations_parking r " +
            "JOIN parkings p ON r.id_parking = p.id " +
            "WHERE MONTH(date_arrivee)=? AND YEAR(date_arrivee)=? " +
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
            "WHERE MONTH(r.date_arrivee)=? AND YEAR(r.date_arrivee)=? " +
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
