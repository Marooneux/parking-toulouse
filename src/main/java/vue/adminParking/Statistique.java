package vue.adminParking;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import modele.dao.DaoStatistique.RecentActivity;

import java.awt.*;
import java.awt.event.ActionListener;
import java.time.Year;
import java.util.List;
import java.util.stream.IntStream;

public class Statistique extends JPanel {

    private static final long serialVersionUID = 1L;

    private JComboBox<Integer> cbMonth;
    private JComboBox<Integer> cbYear;
    private JButton btnRefresh;

    private JLabel lblMontantValue;
    private JLabel lblMontantTrend;
    private JLabel lblSessionsValue;
    private JLabel lblSessionsTrend;
    private JLabel lblZoneValue;
    private JLabel lblOccupationValue;
    private JLabel lblOccupationTrend;

    private JPanel activityList;

    // Chart data
    private List<Integer> sessionsPerDay = List.of(3, 5, 4, 6, 2, 2, 1);
    private List<Double> revenueTrend = List.of(20.0, 28.0, 35.0, 45.5);
    private List<Integer> zoneDistribution = List.of(43, 22, 13, 13, 9);

    public Statistique() {

        setLayout(new BorderLayout());
        setBackground(new Color(245, 247, 250));

        JPanel content = new JPanel(new BorderLayout());
        content.setBackground(new Color(245, 247, 250));

        // HEADER
        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(new Color(245, 247, 250));
        header.setBorder(new EmptyBorder(25, 40, 10, 40));

        JPanel titlePanel = new JPanel();
        titlePanel.setLayout(new BoxLayout(titlePanel, BoxLayout.Y_AXIS));
        titlePanel.setBackground(new Color(245, 247, 250));

        JLabel title = new JLabel("Statistiques");
        title.setFont(new Font("Segoe UI", Font.BOLD, 30));
        title.setForeground(new Color(25, 28, 35));

        JLabel subtitle = new JLabel("Vue d’ensemble de l’activité des parkings");
        subtitle.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        subtitle.setForeground(new Color(120, 125, 130));

        titlePanel.add(title);
        titlePanel.add(Box.createVerticalStrut(4));
        titlePanel.add(subtitle);

        header.add(titlePanel, BorderLayout.WEST);

        // FILTERS
        JPanel filters = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
        filters.setBackground(new Color(245, 247, 250));

        cbMonth = new JComboBox<>();
        for (int m = 1; m <= 12; m++) cbMonth.addItem(m);

        cbYear = new JComboBox<>();
        int currentYear = Year.now().getValue();
        IntStream.rangeClosed(currentYear - 5, currentYear + 1).forEach(cbYear::addItem);
        cbYear.setSelectedItem(currentYear);

        btnRefresh = new JButton("Actualiser");
        btnRefresh.setBackground(new Color(0, 123, 255));
        btnRefresh.setForeground(Color.WHITE);
        btnRefresh.setFocusPainted(false);
        btnRefresh.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnRefresh.setPreferredSize(new Dimension(120, 32));

        filters.add(cbMonth);
        filters.add(cbYear);
        filters.add(btnRefresh);

        header.add(filters, BorderLayout.EAST);
        content.add(header, BorderLayout.NORTH);

        // CENTER
        JPanel center = new JPanel(new BorderLayout());
        center.setBackground(new Color(245, 247, 250));
        content.add(center, BorderLayout.CENTER);

        // KPI CARDS
        JPanel kpiPanel = new JPanel(new GridLayout(1, 4, 20, 20));
        kpiPanel.setBorder(new EmptyBorder(10, 40, 20, 40));
        kpiPanel.setBackground(new Color(245, 247, 250));

        JPanel montantCard = createKpiCard("Montant du mois", "€0.00", "↓ 0%");
        lblMontantValue = (JLabel) montantCard.getClientProperty("valueLabel");
        lblMontantTrend = (JLabel) montantCard.getClientProperty("trendLabel");

        JPanel sessionsCard = createKpiCard("Total sessions", "0", "↑ 0%");
        lblSessionsValue = (JLabel) sessionsCard.getClientProperty("valueLabel");
        lblSessionsTrend = (JLabel) sessionsCard.getClientProperty("trendLabel");

        JPanel zoneCard = createSimpleKpiCard("Zone favorite", "-");
        lblZoneValue = (JLabel) zoneCard.getClientProperty("valueLabel");

        JPanel occupationCard = createKpiCard("Occupation moyenne", "0%", "↑ 0%");
        lblOccupationValue = (JLabel) occupationCard.getClientProperty("valueLabel");
        lblOccupationTrend = (JLabel) occupationCard.getClientProperty("trendLabel");

        kpiPanel.add(montantCard);
        kpiPanel.add(sessionsCard);
        kpiPanel.add(zoneCard);
        kpiPanel.add(occupationCard);

        center.add(kpiPanel, BorderLayout.NORTH);
        // CHARTS
        JPanel chartsRow = new JPanel(new GridLayout(1, 3, 20, 20));
        chartsRow.setBorder(new EmptyBorder(0, 40, 20, 40));
        chartsRow.setBackground(new Color(245, 247, 250));

        chartsRow.add(wrapChartPanel(new BarChartPanel(), "Sessions par jour"));
        chartsRow.add(wrapChartPanel(new PieChartPanel(), "Répartition par zone"));
        chartsRow.add(wrapChartPanel(new LineChartPanel(), "Revenus du mois"));

        center.add(chartsRow, BorderLayout.CENTER);

        // RECENT ACTIVITY
        JPanel bottom = new JPanel(new BorderLayout());
        bottom.setBorder(new EmptyBorder(0, 40, 40, 40));
        bottom.setBackground(new Color(245, 247, 250));

        JPanel activityCard = new JPanel(new BorderLayout());
        activityCard.setBackground(Color.WHITE);
        activityCard.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(230, 232, 236)),
                new EmptyBorder(15, 15, 15, 15)
        ));

        JLabel activityTitle = new JLabel("Activité récente");
        activityTitle.setFont(new Font("Segoe UI", Font.BOLD, 16));
        activityTitle.setForeground(new Color(30, 33, 40));
        activityCard.add(activityTitle, BorderLayout.NORTH);

        activityList = new JPanel();
        activityList.setLayout(new BoxLayout(activityList, BoxLayout.Y_AXIS));
        activityList.setBackground(Color.WHITE);

        activityCard.add(activityList, BorderLayout.CENTER);
        bottom.add(activityCard, BorderLayout.CENTER);

        center.add(bottom, BorderLayout.SOUTH);

        // SCROLL
        JScrollPane scroll = new JScrollPane(content);
        scroll.setBorder(null);
        scroll.getVerticalScrollBar().setUnitIncrement(20);
        scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        add(scroll, BorderLayout.CENTER);
    }

    // KPI CARD STYLE UPGRADE
    private JPanel createKpiCard(String title, String value, String trend) {
        JPanel card = new JPanel(new BorderLayout());
        card.setBackground(Color.WHITE);
        card.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(225, 228, 235)),
                new EmptyBorder(18, 18, 18, 18)
        ));

        JLabel lblTitle = new JLabel(title);
        lblTitle.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lblTitle.setForeground(new Color(120, 125, 130));

        JLabel lblValue = new JLabel(value);
        lblValue.setFont(new Font("Segoe UI", Font.BOLD, 26));
        lblValue.setForeground(new Color(25, 28, 35));

        JLabel lblTrend = new JLabel(trend);
        lblTrend.setFont(new Font("Segoe UI", Font.BOLD, 13));
        lblTrend.setForeground(trend.startsWith("↓") ? new Color(220, 53, 69) : new Color(40, 167, 69));

        JPanel bottom = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        bottom.setBackground(Color.WHITE);
        bottom.add(lblTrend);

        card.add(lblTitle, BorderLayout.NORTH);
        card.add(lblValue, BorderLayout.CENTER);
        card.add(bottom, BorderLayout.SOUTH);

        card.putClientProperty("valueLabel", lblValue);
        card.putClientProperty("trendLabel", lblTrend);

        return card;
    }

    private JPanel createSimpleKpiCard(String title, String value) {
        JPanel card = new JPanel(new BorderLayout());
        card.setBackground(Color.WHITE);
        card.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(225, 228, 235)),
                new EmptyBorder(18, 18, 18, 18)
        ));

        JLabel lblTitle = new JLabel(title);
        lblTitle.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lblTitle.setForeground(new Color(120, 125, 130));

        JLabel lblValue = new JLabel(value);
        lblValue.setFont(new Font("Segoe UI", Font.BOLD, 26));
        lblValue.setForeground(new Color(25, 28, 35));

        card.add(lblTitle, BorderLayout.NORTH);
        card.add(lblValue, BorderLayout.CENTER);

        card.putClientProperty("valueLabel", lblValue);
        return card;
    }

    private JPanel wrapChartPanel(JPanel chart, String title) {
        JPanel wrapper = new JPanel(new BorderLayout());
        wrapper.setBackground(Color.WHITE);
        wrapper.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(225, 228, 235)),
                new EmptyBorder(12, 12, 12, 12)
        ));

        JLabel lblTitle = new JLabel(title);
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 15));
        lblTitle.setForeground(new Color(30, 33, 40));

        wrapper.add(lblTitle, BorderLayout.NORTH);
        wrapper.add(chart, BorderLayout.CENTER);
        return wrapper;
    }

    private JPanel createActivityItem(String title, String subtitle, String date) {
        JPanel item = new JPanel(new BorderLayout());
        item.setBackground(Color.WHITE);
        item.setBorder(new EmptyBorder(10, 0, 10, 0));

        JLabel lblTitle = new JLabel(title);
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lblTitle.setForeground(new Color(30, 33, 40));

        JLabel lblSubtitle = new JLabel(subtitle);
        lblSubtitle.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        lblSubtitle.setForeground(new Color(120, 125, 130));

        JPanel textPanel = new JPanel();
        textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.Y_AXIS));
        textPanel.setBackground(Color.WHITE);
        textPanel.add(lblTitle);
        textPanel.add(lblSubtitle);

        JLabel lblDate = new JLabel(date);
        lblDate.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        lblDate.setForeground(new Color(150, 155, 160));

        item.add(textPanel, BorderLayout.CENTER);
        item.add(lblDate, BorderLayout.EAST);

        return item;
    }

    // CHART PANELS — STYLE IMPROVED
    private class BarChartPanel extends JPanel {
        private static final long serialVersionUID = 1L;

        BarChartPanel() {
            setPreferredSize(new Dimension(300, 200));
            setBackground(Color.WHITE);
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (sessionsPerDay == null || sessionsPerDay.isEmpty()) return;

            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            int width = getWidth();
            int height = getHeight();
            int padding = 35;
            int bottom = height - padding;
            int left = padding;

            int max = sessionsPerDay.stream().max(Integer::compare).orElse(1);
            int barWidth = (width - 2 * padding) / sessionsPerDay.size();

            // Grid lines
            g2.setColor(new Color(230, 230, 230));
            int gridLines = 4;
            for (int i = 0; i <= gridLines; i++) {
                int y = bottom - (height - 2 * padding) * i / gridLines;
                g2.drawLine(left, y, width - padding, y);
            }

            // Axis
            g2.setColor(new Color(200, 200, 200));
            g2.drawLine(left, bottom, width - padding, bottom);

            String[] labels = {"Lun", "Mar", "Mer", "Jeu", "Ven", "Sam", "Dim"};

            for (int i = 0; i < sessionsPerDay.size(); i++) {
                int value = sessionsPerDay.get(i);
                int barHeight = (int) ((double) value / max * (height - 2 * padding));

                int x = left + i * barWidth + 8;
                int y = bottom - barHeight;

                // Gradient bar
                GradientPaint gp = new GradientPaint(
                        x, y, new Color(0, 123, 255),
                        x, bottom, new Color(0, 123, 255, 120)
                );
                g2.setPaint(gp);
                g2.fillRoundRect(x, y, barWidth - 16, barHeight, 8, 8);

                // Value label
                g2.setColor(new Color(33, 37, 41));
                g2.setFont(new Font("Segoe UI", Font.PLAIN, 11));
                String val = String.valueOf(value);
                int valWidth = g2.getFontMetrics().stringWidth(val);
                g2.drawString(val, x + (barWidth - 16 - valWidth) / 2, y - 3);

                // X label
                String label = labels[i];
                int labelWidth = g2.getFontMetrics().stringWidth(label);
                g2.drawString(label, x + (barWidth - 16 - labelWidth) / 2,
                        bottom + g2.getFontMetrics().getAscent() + 2);
            }

            g2.dispose();
        }
    }



    private class LineChartPanel extends JPanel {
        private static final long serialVersionUID = 1L;

        LineChartPanel() {
            setPreferredSize(new Dimension(300, 200));
            setBackground(Color.WHITE);
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (revenueTrend == null || revenueTrend.isEmpty()) return;

            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            int width = getWidth();
            int height = getHeight();
            int padding = 40;
            int bottom = height - padding;
            int left = padding;

            double max = revenueTrend.stream().max(Double::compare).orElse(1.0);
            int usableWidth = width - 2 * padding;
            int usableHeight = height - 2 * padding;

            // Grid
            g2.setColor(new Color(230, 230, 230));
            int gridLines = 4;
            for (int i = 0; i <= gridLines; i++) {
                int y = bottom - usableHeight * i / gridLines;
                g2.drawLine(left, y, width - padding, y);
            }

            // Axis
            g2.setColor(new Color(200, 200, 200));
            g2.drawLine(left, bottom, width - padding, bottom);

            int n = revenueTrend.size();
            int step = (n > 1) ? usableWidth / (n - 1) : usableWidth;

            int[] xs = new int[n];
            int[] ys = new int[n];

            for (int i = 0; i < n; i++) {
                xs[i] = left + i * step;
                ys[i] = bottom - (int) (revenueTrend.get(i) / max * usableHeight);
            }

            // Area fill
            g2.setColor(new Color(0, 123, 255, 60));
            Polygon area = new Polygon();
            area.addPoint(xs[0], bottom);
            for (int i = 0; i < n; i++) area.addPoint(xs[i], ys[i]);
            area.addPoint(xs[n - 1], bottom);
            g2.fill(area);

            // Line
            g2.setColor(new Color(0, 123, 255));
            g2.setStroke(new BasicStroke(2f));
            for (int i = 0; i < n - 1; i++) {
                g2.drawLine(xs[i], ys[i], xs[i + 1], ys[i + 1]);
            }

            // Points + labels
            g2.setFont(new Font("Segoe UI", Font.PLAIN, 11));
            for (int i = 0; i < n; i++) {
                g2.setColor(Color.WHITE);
                g2.fillOval(xs[i] - 4, ys[i] - 4, 8, 8);

                g2.setColor(new Color(0, 123, 255));
                g2.drawOval(xs[i] - 4, ys[i] - 4, 8, 8);

                String val = String.format("%.1f", revenueTrend.get(i));
                int valWidth = g2.getFontMetrics().stringWidth(val);
                g2.setColor(new Color(33, 37, 41));
                g2.drawString(val, xs[i] - valWidth / 2, ys[i] - 6);
            }

            g2.dispose();
        }
    }


    private class PieChartPanel extends JPanel {
        private static final long serialVersionUID = 1L;

        PieChartPanel() {
            setPreferredSize(new Dimension(300, 200));
            setBackground(Color.WHITE);
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (zoneDistribution == null || zoneDistribution.isEmpty()) return;

            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            int width = getWidth();
            int height = getHeight();
            int size = Math.min(width, height) - 80;
            int x = (width - size) / 2;
            int y = (height - size) / 2;

            int total = zoneDistribution.stream().mapToInt(i -> i).sum();
            if (total == 0) total = 1;

            Color[] colors = {
                    new Color(220, 53, 69),
                    new Color(255, 193, 7),
                    new Color(40, 167, 69),
                    new Color(0, 123, 255),
                    new Color(108, 117, 125)
            };

            int startAngle = 0;
            for (int i = 0; i < zoneDistribution.size(); i++) {
                int angle = (int) Math.round(360.0 * zoneDistribution.get(i) / total);
                g2.setColor(colors[i % colors.length]);
                g2.fillArc(x, y, size, size, startAngle, angle);
                startAngle += angle;
            }

            // Donut hole
            int holeSize = (int) (size * 0.55);
            int hx = x + (size - holeSize) / 2;
            int hy = y + (size - holeSize) / 2;
            g2.setColor(Color.WHITE);
            g2.fillOval(hx, hy, holeSize, holeSize);

            // Legend
            g2.setFont(new Font("Segoe UI", Font.PLAIN, 11));
            int legendX = 10;
            int legendY = 10;

            String[] labels = {"Rouge", "Jaune", "Verte", "Bleue", "Orange"};

            for (int i = 0; i < zoneDistribution.size(); i++) {
                g2.setColor(colors[i]);
                g2.fillOval(legendX, legendY + i * 18, 10, 10);

                g2.setColor(new Color(33, 37, 41));
                int percent = (int) Math.round(100.0 * zoneDistribution.get(i) / total);
                g2.drawString(labels[i] + " (" + percent + "%)", legendX + 15, legendY + 9 + i * 18);
            }

            g2.dispose();
        }
    }



    // PUBLIC API (unchanged)
    public int getSelectedMonth() { return (Integer) cbMonth.getSelectedItem(); }
    public int getSelectedYear() { return (Integer) cbYear.getSelectedItem(); }

    public void setMontant(double montant) {
        lblMontantValue.setText(String.format("€%.2f", montant));
    }

    public void setSessions(int sessions) {
        lblSessionsValue.setText(String.valueOf(sessions));
    }

    public void setZoneFavorite(String zone) {
        lblZoneValue.setText(zone != null ? zone : "-");
    }

    public void setOccupation(int occupation) {
        lblOccupationValue.setText(occupation + "%");
    }

    public void setSessionsPerDay(List<Integer> values) {
        this.sessionsPerDay = values;
        repaint();
    }

    public void setRevenueTrend(List<Double> values) {
        this.revenueTrend = values;
        repaint();
    }

    public void setZoneDistribution(List<Integer> values) {
        this.zoneDistribution = values;
        repaint();
    }

    public void setRecentActivity(List<RecentActivity> list) {
        activityList.removeAll();

        for (RecentActivity a : list) {
            String duration = formatDuration(a.dureeMinutes);
            String price = String.format("%.2f€", a.tarif);
            String subtitle = a.details + " • " + duration + " • " + price;
            String date = a.date.toLocalDate().toString();

            activityList.add(createActivityItem(a.lieu, subtitle, date));
        }

        activityList.revalidate();
        activityList.repaint();
    }

    private String formatDuration(int minutes) {
        if (minutes < 60) return minutes + "min";
        int h = minutes / 60;
        int m = minutes % 60;
        return m == 0 ? h + "h" : h + "h " + m + "min";
    }

    public void addRefreshListener(ActionListener l) {
        btnRefresh.addActionListener(l);
    }

    public JButton getBtnRefresh() {
        return btnRefresh;
    }

    public void actualiserAffichage() {
        revalidate();
        repaint();
    }

    public void showError(String message) {
        JOptionPane.showMessageDialog(this, message, "Erreur", JOptionPane.ERROR_MESSAGE);
    }
}

