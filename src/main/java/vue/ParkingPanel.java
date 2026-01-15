package vue;

import javax.swing.*; 
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import modele.Parking;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.util.function.Consumer;

class ParkingPanel extends JPanel {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Parking parking;
    private Consumer<Parking> onClick;
    private JButton btnModifier;

    private Color normalBorder = new Color(230, 230, 230);

    public ParkingPanel(Parking parking, Consumer<Parking> onClick) {
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

        addMouseListener(new controleur.ControleurParkingPanel(this, parking, onClick));

        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        topPanel.setOpaque(false);
        
        CircleIcon pIcon = new CircleIcon("P");
        topPanel.add(pIcon);
        
        JLabel space = new JLabel();
        space.setPreferredSize(new Dimension(15, 1));
        topPanel.add(space);

        JLabel lblName = new JLabel("<html>" + parking.getNom() + "</html>");
        lblName.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lblName.setForeground(new Color(33, 37, 41));
        topPanel.add(lblName);

        add(topPanel, BorderLayout.NORTH);

        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.setOpaque(false);
        centerPanel.setBorder(new EmptyBorder(15, 0, 15, 0));

        centerPanel.add(createDetailRow("📍", parking.getAdresse()));
        centerPanel.add(Box.createVerticalStrut(8));
        
        String horaireText = (parking.getHeureOuverture().equals(parking.getHeureFermeture())) 
                ? "24h / 24" 
                : parking.getHeureOuverture() + " - " + parking.getHeureFermeture();
        centerPanel.add(createDetailRow("🕒", horaireText));
        
        centerPanel.add(Box.createVerticalStrut(8));
        centerPanel.add(createDetailRow("🚗", (parking.getNbPlacesMax() - parking.getNbPlacesOccupees()) + " places"));
        
        centerPanel.add(Box.createVerticalStrut(8));
        centerPanel.add(createDetailRow("📏", "Max " + (parking.getHauteur()) + "m"));

        add(centerPanel, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.setOpaque(false);
        
        JPanel borderTop = new JPanel();
        borderTop.setBackground(new Color(240, 240, 240));
        borderTop.setPreferredSize(new Dimension(100, 1));
        bottomPanel.add(borderTop, BorderLayout.NORTH);
        
        JLabel lblTarifLabel = new JLabel("Tarif");
        lblTarifLabel.setFont(new Font("Segoe UI", Font.BOLD, 12));
        lblTarifLabel.setForeground(Color.GRAY);
        lblTarifLabel.setBorder(new EmptyBorder(10, 0, 0, 0));
        
        JLabel lblPrice = new JLabel(String.format("%.2f€/h", parking.getTarif()));
        lblPrice.setFont(new Font("Segoe UI", Font.BOLD, 20));
        lblPrice.setForeground(new Color(33, 37, 41));
        lblPrice.setBorder(new EmptyBorder(5, 0, 0, 0));

        bottomPanel.add(lblTarifLabel, BorderLayout.WEST);
        bottomPanel.add(lblPrice, BorderLayout.EAST);

        btnModifier = new JButton("Modifier");
        btnModifier.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        btnModifier.setFocusPainted(false);
        btnModifier.setBackground(new Color(240, 240, 240));
        btnModifier.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        JPanel actionsPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 0, 0));
        actionsPanel.setOpaque(false);
        actionsPanel.add(btnModifier);

        btnModifier.addActionListener(new controleur.ControleurParkingPanelModifier(parking));

        bottomPanel.add(actionsPanel, BorderLayout.SOUTH);

        add(bottomPanel, BorderLayout.SOUTH);
    }

    private JPanel createDetailRow(String icon, String text) {
        JPanel row = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        row.setOpaque(false);
        
        JLabel lblIcon = new JLabel(icon);
        lblIcon.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 14));
        lblIcon.setPreferredSize(new Dimension(25, 20));
        lblIcon.setForeground(Color.GRAY);
        
        JLabel lblText = new JLabel(text);
        lblText.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        lblText.setForeground(new Color(73, 80, 87));
        
        row.add(lblIcon);
        row.add(lblText);
        return row;
    }
    
    class CircleIcon extends JComponent {
        private String text;
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
            g2.setFont(new Font("Segoe UI", Font.BOLD, 22));
            FontMetrics fm = g2.getFontMetrics();
            int x = (getWidth() - fm.stringWidth(text)) / 2;
            int y = ((getHeight() - fm.getHeight()) / 2) + fm.getAscent();
            g2.drawString(text, x, y - 2);
        }
    }
    
    public JButton getBtnModifier() {
        return btnModifier;
    }
}
