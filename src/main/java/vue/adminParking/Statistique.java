package vue.adminParking;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Map;

public class Statistique extends JPanel {

    private static final Color BG = new Color(245, 247, 250);
    private static final Font FONT_TITLE = new Font("Segoe UI", Font.BOLD, 26);
    private static final Font FONT_SUBTITLE = new Font("Segoe UI", Font.PLAIN, 14);
    private static final Font FONT_KPI_TITLE = new Font("Segoe UI", Font.PLAIN, 13);
    private static final Font FONT_KPI_VALUE = new Font("Segoe UI", Font.BOLD, 24);
    private static final Font FONT_SECTION_TITLE = new Font("Segoe UI", Font.BOLD, 20);
    private static final Font FONT_BODY = new Font("Segoe UI Emoji", Font.PLAIN, 14);

    private final DecimalFormat moneyFormat = new DecimalFormat("#,##0.00");

    private JComboBox<String> cbMois;
    private JButton btnRefresh;

    private JLabel lblMontant;
    private JLabel lblSessions;
    private JLabel lblOccupation;

    private BarChartPanel barChartPanel;
    private LineChartPanel lineChartPanel;

    private JPanel recentActivityPanel;

    public Statistique() {
        setLayout(new BorderLayout());
        setBackground(BG);

        add(createHeader(), BorderLayout.NORTH);
        add(createContent(), BorderLayout.CENTER);
    }

    // ---------------------------------------------------------
    // HEADER
    // ---------------------------------------------------------
    private JComponent createHeader() {
        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(BG);
        header.setBorder(new EmptyBorder(20, 40, 10, 40));

        JLabel title = new JLabel("📊 Statistiques Parking");
        title.setFont(FONT_TITLE);
        title.setForeground(new Color(30, 33, 40));

        JLabel subtitle = new JLabel("Vue d’ensemble de l’activité des parkings");
        subtitle.setFont(FONT_SUBTITLE);
        subtitle.setForeground(new Color(110, 117, 125));

        JPanel left = new JPanel();
        left.setLayout(new BoxLayout(left, BoxLayout.Y_AXIS));
        left.setBackground(BG);
        left.add(title);
        left.add(Box.createVerticalStrut(4));
        left.add(subtitle);

        cbMois = new JComboBox<>(new String[]{
                "Janvier", "Février", "Mars", "Avril", "Mai", "Juin",
                "Juillet", "Août", "Septembre", "Octobre", "Novembre", "Décembre"
        });
        cbMois.setFont(new Font("Segoe UI", Font.PLAIN, 13));

        btnRefresh = new JButton("⟳ Actualiser");
        btnRefresh.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        btnRefresh.setBackground(new Color(0, 123, 255));
        btnRefresh.setForeground(Color.WHITE);
        btnRefresh.setFocusPainted(false);
        btnRefresh.setBorder(BorderFactory.createEmptyBorder(6, 14, 6, 14));

        JPanel right = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
        right.setBackground(BG);
        right.add(cbMois);
        right.add(btnRefresh);

        header.add(left, BorderLayout.WEST);
        header.add(right, BorderLayout.EAST);

        return header;
    }

    // ---------------------------------------------------------
    // MAIN CONTENT
    // ---------------------------------------------------------
    private JComponent createContent() {
        JPanel content = new JPanel();
        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));
        content.setBackground(BG);
        content.setBorder(new EmptyBorder(0, 40, 40, 40));

        // KPI ROW
        JPanel kpiRow = new JPanel(new GridLayout(1, 3, 20, 0));
        kpiRow.setBackground(BG);

        kpiRow.add(createKpiCard("Montant du mois", "€0.00", new Color(0, 123, 255), lbl -> lblMontant = lbl));
        kpiRow.add(createKpiCard("Total sessions", "0", new Color(40, 167, 69), lbl -> lblSessions = lbl));
        kpiRow.add(createKpiCard("Occupation moyenne", "0%", new Color(255, 193, 7), lbl -> lblOccupation = lbl));

        content.add(kpiRow);
        content.add(Box.createVerticalStrut(25));

        // BAR CHART 
        barChartPanel = new BarChartPanel();
        barChartPanel.setPreferredSize(new Dimension(0, 260));
        JPanel barCard = wrapChart("Sessions par jour", barChartPanel);
        barCard.add(createLegend("#007BFF", "Sessions"), BorderLayout.SOUTH);
        content.add(barCard);
        content.add(Box.createVerticalStrut(25));

        // LINE CHART 
        lineChartPanel = new LineChartPanel();
        lineChartPanel.setPreferredSize(new Dimension(0, 260));
        JPanel lineCard = wrapChart("Revenus du mois", lineChartPanel);
        lineCard.add(createLegend("#007BFF", "Revenus (€)"), BorderLayout.SOUTH);
        content.add(lineCard);
        content.add(Box.createVerticalStrut(25));

        // RECENT ACTIVITY
        JLabel recentTitle = new JLabel("Activité récente");
        recentTitle.setFont(FONT_SECTION_TITLE);
        recentTitle.setForeground(new Color(30, 33, 40));
        recentTitle.setBorder(new EmptyBorder(0, 0, 8, 0));
        content.add(recentTitle);

        recentActivityPanel = new JPanel();
        recentActivityPanel.setLayout(new BoxLayout(recentActivityPanel, BoxLayout.Y_AXIS));
        recentActivityPanel.setBackground(BG);
        content.add(recentActivityPanel);

        JScrollPane scroll = new JScrollPane(content);
        scroll.setBorder(null);
        scroll.getViewport().setBackground(BG);
        scroll.getVerticalScrollBar().setUnitIncrement(16);

        return scroll;
    }

    // ---------------------------------------------------------
    // KPI CARD
    // ---------------------------------------------------------
    private JPanel createKpiCard(String title, String value, Color accent, java.util.function.Consumer<JLabel> ref) {
        JPanel card = new JPanel(new BorderLayout());
        card.setBackground(Color.WHITE);
        card.setBorder(new EmptyBorder(16, 18, 16, 18));

        JPanel accentPanel = new JPanel();
        accentPanel.setPreferredSize(new Dimension(4, 10));
        accentPanel.setBackground(accent);

        JLabel lblTitle = new JLabel(title);
        lblTitle.setFont(FONT_KPI_TITLE);
        lblTitle.setForeground(new Color(110, 117, 125));

        JLabel lblValue = new JLabel(value);
        lblValue.setFont(FONT_KPI_VALUE);
        lblValue.setForeground(new Color(30, 33, 40));

        ref.accept(lblValue);

        JPanel center = new JPanel();
        center.setLayout(new BoxLayout(center, BoxLayout.Y_AXIS));
        center.setBackground(Color.WHITE);
        center.add(lblTitle);
        center.add(Box.createVerticalStrut(4));
        center.add(lblValue);

        card.add(accentPanel, BorderLayout.WEST);
        card.add(center, BorderLayout.CENTER);

        return card;
    }

    private JPanel wrapChart(String title, JComponent chart) {
        JPanel card = new JPanel(new BorderLayout());
        card.setBackground(Color.WHITE);
        card.setBorder(new EmptyBorder(16, 18, 16, 18));

        JLabel lbl = new JLabel(title);
        lbl.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        lbl.setForeground(new Color(30, 33, 40));
        lbl.setBorder(new EmptyBorder(0, 0, 8, 0));

        card.add(lbl, BorderLayout.NORTH);
        card.add(chart, BorderLayout.CENTER);


        JPanel wrapper = new JPanel(new BorderLayout());
        wrapper.setBackground(BG);
        wrapper.add(card, BorderLayout.CENTER);

        return wrapper;
    }
    
    // ---------------------------------------------------------
    // Legend
    // ---------------------------------------------------------
    
    private JComponent createLegend(String colorHex, String label) {
    	JPanel legend = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 0));
    	legend.setBackground(Color.WHITE);
    	
    	JPanel colorBox =new JPanel();
    	colorBox.setBackground(Color.decode(colorHex));
    	colorBox.setPreferredSize(new Dimension(14,14));
    	colorBox.setBorder(BorderFactory.createLineBorder(new Color(200,200,200)));
    	
    	JLabel lbl = new JLabel(label);
    	lbl.setFont(new Font("Segoe UI", Font.PLAIN, 13));
    	lbl.setForeground(new Color(80,80,80));
    	
    	legend.add(colorBox);
    	legend.add(lbl);
    	return legend;
    }

    // ---------------------------------------------------------
    // PUBLIC API FOR CONTROLLER
    // ---------------------------------------------------------
    public JButton getBtnRefresh() { return btnRefresh; }

    public int getSelectedMonth() { return cbMois.getSelectedIndex() + 1; }

    public int getSelectedYear() { return java.time.Year.now().getValue(); }

    public void showError(String msg) {
        JOptionPane.showMessageDialog(this, msg, "Erreur", JOptionPane.ERROR_MESSAGE);
    }

    public void setMontant(double m) {
        lblMontant.setText("€" + moneyFormat.format(m));
    }

    public void setSessions(int s) { lblSessions.setText(String.valueOf(s)); }

    public void setOccupation(int o) { lblOccupation.setText(o + "%"); }

    public void setSessionsPerDay(List<Integer> values) { barChartPanel.setData(values); }

    public void setRevenueTrend(List<Double> values) { lineChartPanel.setData(values); }

    public void setRecentActivity(List<Map<String, Object>> list) {
        recentActivityPanel.removeAll();

        for (Map<String, Object> a : list) {
            JPanel card = new JPanel(new BorderLayout());
            card.setBackground(Color.WHITE);
            card.setBorder(new EmptyBorder(10, 14, 10, 14));
            card.setMaximumSize(new Dimension(Integer.MAX_VALUE, 52));

            JLabel lbl = new JLabel(
                    "📍 " + a.get("lieu") +
                    "   •   ⏱ " + a.get("duree") +
                    "   •   💰 " + a.get("prix") +
                    "   •   📅 " + a.get("date")
            );
            lbl.setFont(FONT_BODY);
            lbl.setForeground(new Color(60, 60, 60));

            card.add(lbl, BorderLayout.CENTER);
            recentActivityPanel.add(card);
            recentActivityPanel.add(Box.createVerticalStrut(6));
        }

        revalidate();
        repaint();
    }

    // ---------------------------------------------------------
    // BAR CHART (bigger + gridlines)
    // ---------------------------------------------------------
    private static class BarChartPanel extends JPanel {
        private List<Integer> values = java.util.Collections.emptyList();
        private final String[] labels = {"Lun", "Mar", "Mer", "Jeu", "Ven", "Sam", "Dim"};
        
        
        public void setData(List<Integer> v) { values = v; repaint(); }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (values.isEmpty()) return;

            Graphics2D g2 = (Graphics2D) g;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            int w = getWidth(), h = getHeight();
            int pad = 40;
            int max = values.stream().max(Integer::compare).orElse(1);

            // Gridlines
            g2.setColor(new Color(230, 230, 230));
            for (int i = 0; i < 5; i++) {
                int y = pad + i * (h - pad * 2) / 4;
                g2.drawLine(pad, y, w - pad, y);
            }

            int barW = Math.max(20, (w - pad * 2) / values.size());

            for (int i = 0; i < values.size(); i++) {
                int v = values.get(i);
                int bh = (int) ((double) v / max * (h - pad * 2));
                int x = pad + i * barW;
                int y = h - pad - bh;

                g2.setPaint(new GradientPaint(x, y, new Color(0, 123, 255),
                        x, y + bh, new Color(0, 123, 255, 120)));
                g2.fillRoundRect(x + 4, y, barW - 8, bh, 10, 10);
            }
            //label desous bar charte
            g2.setFont(new Font("Segoe UI", Font.PLAIN, 12));
            g2.setColor(new Color(80, 80, 80));

            for (int i = 0; i < values.size() && i < labels.length; i++) {
                int x = pad + i * barW + barW / 2;
                int y = h - pad + 18;
                String text = labels[i];

                int textWidth = g2.getFontMetrics().stringWidth(text);
                g2.drawString(text, x - textWidth / 2, y);
            }
            
            // Label de Y axis
            g2.setFont(new Font("Segoe UI", Font.PLAIN, 11));
            g2.setColor(new Color(100, 100, 100));
            for(int i = 0; i<=5; i++) {
            	double value = max * (4-i) / 4.0;
            	String label = String.format("%.0f", value);
            	int y = pad + i * (h - pad * 2) / 4;
            	g2.drawString(label, pad - 25, y+4);
            }
        }
    }

    // ---------------------------------------------------------
    // LINE CHART (bigger + gridlines)
    // ---------------------------------------------------------
    private static class LineChartPanel extends JPanel {
        private List<Double> values = java.util.Collections.emptyList();
        private List<String> xLabels = java.util.Collections.emptyList();
        
        public void setLabels(List<String> labels) {
        	this.xLabels = labels;
        	repaint();
        }
        
        public void setData(List<Double> v) { values = v; repaint(); }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (values.isEmpty()) return;

            Graphics2D g2 = (Graphics2D) g;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            int w = getWidth(), h = getHeight();
            int pad = 40;

            double max = values.stream().max(Double::compare).orElse(1.0);
            int n = values.size();
            int step = (n > 1) ? (w - pad * 2) / (n - 1) : 0;

            // Gridlines
            g2.setColor(new Color(230, 230, 230));
            for (int i = 0; i < 5; i++) {
                int y = pad + i * (h - pad * 2) / 4;
                g2.drawLine(pad, y, w - pad, y);
            }

         // Y-axis labels (€)
            g2.setFont(new Font("Segoe UI", Font.PLAIN, 11));
            g2.setColor(new Color(100, 100, 100));
            for (int i = 0; i <= 4; i++) {
                double value = max * (4 - i) / 4.0;
                String label = String.format("%.0f€", value);
                int y = pad + i * (h - pad * 2) / 4;
                g2.drawString(label, pad - 35, y + 4);
            }

            int[] xs = new int[n];
            int[] ys = new int[n];

            for (int i = 0; i < n; i++) {
                xs[i] = pad + i * step;
                ys[i] = h - pad - (int) (values.get(i) / max * (h - pad * 2));
            }

            // Area gradient
            GradientPaint gp = new GradientPaint(
                    0, pad, new Color(0, 123, 255, 90),
                    0, h - pad, new Color(0, 123, 255, 10)
            );
            g2.setPaint(gp);

            Polygon area = new Polygon();
            area.addPoint(xs[0], h - pad);
            for (int i = 0; i < n; i++) area.addPoint(xs[i], ys[i]);
            area.addPoint(xs[n - 1], h - pad);
            g2.fill(area);

            // Line
            g2.setColor(new Color(0, 123, 255));
            g2.setStroke(new BasicStroke(2.5f));
            for (int i = 0; i < n - 1; i++) {
                g2.drawLine(xs[i], ys[i], xs[i + 1], ys[i + 1]);
            }

            // Points
            for (int i = 0; i < n; i++) {
                g2.fillOval(xs[i] - 4, ys[i] - 4, 8, 8);
            }

            // X-axis labels (filtered)
            g2.setFont(new Font("Segoe UI", Font.PLAIN, 11));
            g2.setColor(new Color(80, 80, 80));

            int skip = Math.max(1, n / 7); // environ 7 labels max

            for (int i = 0; i < n && i < xLabels.size(); i++) {
                if (i % skip != 0) continue;

                String text = xLabels.get(i);
                int textWidth = g2.getFontMetrics().stringWidth(text);
                g2.drawString(text, xs[i] - textWidth / 2, h - pad + 18);
            }
        }
    }
}
