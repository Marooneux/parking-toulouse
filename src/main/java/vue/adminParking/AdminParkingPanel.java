package vue.adminParking;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Ellipse2D;
import java.util.function.Consumer;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import modele.Parking;
import ui.theme.DefaultTheme;

public class AdminParkingPanel extends JPanel {
    private static final long serialVersionUID = 1L;

    private final Parking parking;
    private final Consumer<Parking> onClick;

    private final Color normalBorder = new Color(230, 230, 230);
    private final Color hoverBorder = new Color(52, 58, 64);

    public AdminParkingPanel(Parking parking, Consumer<Parking> onClick) {
        this.parking = parking;
        this.onClick = onClick;

        setLayout(new BorderLayout());
        setBackground(Color.WHITE);
        setPreferredSize(new Dimension(300, 260));

        setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(normalBorder, 1),
                new EmptyBorder(20, 20, 20, 20)
        ));

        setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                if (AdminParkingPanel.this.onClick != null) {
                    AdminParkingPanel.this.onClick.accept(AdminParkingPanel.this.parking);
                }
            }

            @Override
            public void mouseEntered(java.awt.event.MouseEvent e) {
                setBorder(BorderFactory.createCompoundBorder(
                        new LineBorder(hoverBorder, 2),
                        new EmptyBorder(19, 19, 19, 19)
                ));
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent e) {
                setBorder(BorderFactory.createCompoundBorder(
                        new LineBorder(normalBorder, 1),
                        new EmptyBorder(20, 20, 20, 20)
                ));
            }
        });

        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        topPanel.setOpaque(false);

        CircleIcon pIcon = new CircleIcon("P");
        topPanel.add(pIcon);

        JLabel space = new JLabel();
        space.setPreferredSize(new Dimension(15, 1));
        topPanel.add(space);

        JLabel lblName = new JLabel("<html>" + this.parking.getNom() + "</html>");
        lblName.setFont(DefaultTheme.FONT_TITLE_ALT);
        lblName.setForeground(DefaultTheme.TEXT_COLOR);
        topPanel.add(lblName);

        add(topPanel, BorderLayout.NORTH);

        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.setOpaque(false);
        centerPanel.setBorder(new EmptyBorder(15, 0, 15, 0));

        centerPanel.add(createDetailRow("📍", this.parking.getAdresse() != null ? this.parking.getAdresse().getRue() : ""));
        centerPanel.add(Box.createVerticalStrut(8));

        String horaireText = (this.parking.getHeureOuverture().equals(this.parking.getHeureFermeture()))
                ? "24h / 24"
                : this.parking.getHeureOuverture() + " - " + this.parking.getHeureFermeture();
        centerPanel.add(createDetailRow("🕒", horaireText));

        centerPanel.add(Box.createVerticalStrut(8));
        centerPanel.add(createDetailRow("🚗", (parking.getCapacite() - parking.getNbPlacesOccupees()) + "/" + parking.getCapacite() + " places"));

        centerPanel.add(Box.createVerticalStrut(8));
        centerPanel.add(createDetailRow("📏", "Max " + (this.parking.getHauteur()) + "m"));

        add(centerPanel, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.setOpaque(false);

        JPanel borderTop = new JPanel();
        borderTop.setBackground(new Color(240, 240, 240));
        borderTop.setPreferredSize(new Dimension(100, 1));
        bottomPanel.add(borderTop, BorderLayout.NORTH);

        JLabel lblPrice = new JLabel(String.format("%.2f€/h", this.parking.getTarif()));
        lblPrice.setFont(DefaultTheme.FONT_BUTTON);
        lblPrice.setForeground(Color.GRAY);
        lblPrice.setBorder(new EmptyBorder(10, 0, 0, 0));

        bottomPanel.add(lblPrice, BorderLayout.WEST);

        add(bottomPanel, BorderLayout.SOUTH);
    }

    private JPanel createDetailRow(String icon, String text) {
        JPanel row = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        row.setOpaque(false);

        JLabel lblIcon = new JLabel(icon);
        lblIcon.setFont(DefaultTheme.FONT_ICON_SMALL);
        lblIcon.setPreferredSize(new Dimension(25, 20));
        lblIcon.setForeground(Color.GRAY);

        JLabel lblText = new JLabel(text);
        lblText.setFont(DefaultTheme.FONT_LABEL_SMALL);
        lblText.setForeground(DefaultTheme.LABEL_COLOR);

        row.add(lblIcon);
        row.add(lblText);
        return row;
    }

    class CircleIcon extends JComponent {
        private static final long serialVersionUID = 1L;
        private final String text;

        public CircleIcon(String text) {
            this.text = text;
            setPreferredSize(new Dimension(45, 45));
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            g2.setColor(new Color(52, 58, 64));
            g2.fill(new Ellipse2D.Double(0, 0, 45, 45));

            g2.setColor(Color.WHITE);
            g2.setFont(DefaultTheme.FONT_HERO_TITLE);
            FontMetrics fm = g2.getFontMetrics();
            int x = (getWidth() - fm.stringWidth(text)) / 2;
            int y = ((getHeight() - fm.getHeight()) / 2) + fm.getAscent();
            g2.drawString(text, x, y - 2);
        }
    }
}