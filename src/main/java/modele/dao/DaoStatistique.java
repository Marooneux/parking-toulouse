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
    
    public static class RecentActivity {
        public LocalDateTime date;
        public String lieu;
        public String details;
        public int dureeMinutes;
        public double tarif;
    }

    public double getMontantMois(int month, int year) throws SQLException {

        String sqlParking =
            "SELECT COALESCE(SUM(p.tarif),0) AS total " +
            "FROM reservations_parking r " +
            "JOIN parkings p ON r.id_parking = p.id " +
            "WHERE MONTH(r.date_arrivee)=? AND YEAR(r.date_arrivee)=?";

        String sqlVoirie =
            "SELECT COALESCE(SUM(z.tarif_horaire),0) AS total " +
            "FROM reservations_voirie rv " +
            "JOIN zones_voirie z ON rv.id_zone = z.id " +
            "WHERE MONTH(rv.date_debut)=? AND YEAR(rv.date_debut)=?";

        double total = 0;

        try (PreparedStatement ps = c.prepareStatement(sqlParking)) {
            ps.setInt(1, month);
            ps.setInt(2, year);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) total += rs.getDouble("total");
            }
        }

        try (PreparedStatement ps = c.prepareStatement(sqlVoirie)) {
            ps.setInt(1, month);
            ps.setInt(2, year);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) total += rs.getDouble("total");
            }
        }

        return total;
    }


    public int getTotalSessions(int month, int year) throws SQLException {
        String sqlParking =
            "SELECT COUNT(*) AS nb FROM reservations_parking " +
            "WHERE MONTH(date_arrivee)=? AND YEAR(date_arrivee)=?";

        String sqlVoirie =
            "SELECT COUNT(*) AS nb FROM reservations_voirie " +
            "WHERE MONTH(date_debut)=? AND YEAR(date_debut)=?";

        int total = 0;

        try (PreparedStatement ps = c.prepareStatement(sqlParking)) {
            ps.setInt(1, month);
            ps.setInt(2, year);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) total += rs.getInt("nb");
            }
        }

        try (PreparedStatement ps = c.prepareStatement(sqlVoirie)) {
            ps.setInt(1, month);
            ps.setInt(2, year);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) total += rs.getInt("nb");
            }
        }

        return total;
    }

    public String getZoneFavorite(int month, int year) throws SQLException {
        String sql =
            "SELECT z.couleur, COUNT(*) AS nb " +
            "FROM reservations_voirie rv " +
            "JOIN zones_voirie z ON rv.id_zone = z.id " +
            "WHERE MONTH(rv.date_debut)=? AND YEAR(rv.date_debut)=? " +
            "GROUP BY z.couleur " +
            "ORDER BY nb DESC LIMIT 1";

        try (PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, month);
            ps.setInt(2, year);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return rs.getString("couleur");
            }
        }

        return "Aucune";
    }

    public int getOccupation(int month, int year) throws SQLException {
        String sql =
            "SELECT COUNT(*) AS nb FROM reservations_parking " +
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
    
    public List<Integer> getSessionsPerDay(int month, int year) throws SQLException {
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

    public List<Double> getRevenueTrend(int month, int year) throws SQLException {
        String sql =
            "SELECT WEEK(date_arrivee) AS w, SUM(p.tarif) AS total " +
            "FROM reservations_parking r " +
            "JOIN parkings p ON r.id_parking = p.id " +
            "WHERE MONTH(date_arrivee)=? AND YEAR(date_arrivee)=? " +
            "GROUP BY w ORDER BY w";

        List<Double> trend = new java.util.ArrayList<>();

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

    public List<Integer> getZoneDistribution(int month, int year) throws SQLException {
        String sql =
            "SELECT z.couleur, COUNT(*) AS nb " +
            "FROM reservations_voirie rv " +
            "JOIN zones_voirie z ON rv.id_zone = z.id " +
            "WHERE MONTH(rv.date_debut)=? AND YEAR(rv.date_debut)=? " +
            "GROUP BY z.couleur";

        int rouge=0, jaune=0, verte=0, orange=0, bleue=0;

        try (PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, month);
            ps.setInt(2, year);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    String color = rs.getString("couleur");
                    int nb = rs.getInt("nb");

                    switch (color.toLowerCase()) {
                        case "rouge": rouge = nb; break;
                        case "jaune": jaune = nb; break;
                        case "verte": verte = nb; break;
                        case "orange": orange = nb; break;
                        case "bleue": bleue = nb; break;
                    }
                }
            }
        }

        return List.of(rouge, jaune, verte, orange, bleue);
    }

    public List<RecentActivity> getRecentActivity() throws SQLException {

        String sql =
            "(SELECT r.date_arrivee AS date, p.nom AS lieu, " +
            "CONCAT('Parking ', p.nom) AS details, " +
            "TIMESTAMPDIFF(MINUTE, r.date_arrivee, COALESCE(r.date_depart, NOW())) AS duree, " +
            "p.tarif AS tarif " +
            "FROM reservations_parking r " +
            "JOIN parkings p ON r.id_parking = p.id) " +

            "UNION ALL " +

            "(SELECT rv.date_debut AS date, z.couleur AS lieu, " +
            "CONCAT('Zone ', z.couleur) AS details, " +
            "rv.duree_minutes AS duree, " +
            "z.tarif_horaire AS tarif " +
            "FROM reservations_voirie rv " +
            "JOIN zones_voirie z ON rv.id_zone = z.id) " +

            "ORDER BY date DESC LIMIT 5";

        List<RecentActivity> list = new ArrayList<>();

        try (PreparedStatement ps = c.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

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

        return list;
    }


}
