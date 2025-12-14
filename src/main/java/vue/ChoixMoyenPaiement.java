package vue;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import modele.StationnementVoirie;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ChoixMoyenPaiement extends JFrame {

    private final Color BACKGROUND_COLOR = new Color(248, 249, 250);
    private final Color CARD_COLOR = Color.WHITE;
    private final Color TEXT_COLOR = new Color(33, 37, 41);
    private final Color SUBTEXT_COLOR = new Color(108, 117, 125);
    private final Color BUTTON_COLOR = new Color(13, 110, 253);
    private final Color BUTTON_TEXT_COLOR = Color.WHITE;
    private StationnementVoirie zone;
    private int duree;

    public ChoixMoyenPaiement(StationnementVoirie zone, int duree) {
    	this.zone = zone;
    	this.duree = duree;
    	
        setTitle("Moyen de Paiement");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(850, 550);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(BACKGROUND_COLOR);

        JPanel headerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 30));
        headerPanel.setOpaque(false);
        JLabel titleLabel = new JLabel("Choisissez votre moyen de paiement");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 26));
        titleLabel.setForeground(TEXT_COLOR);
        headerPanel.add(titleLabel);

        JPanel cardsContainer = new JPanel(new GridLayout(1, 2, 40, 0));
        cardsContainer.setOpaque(false);
        cardsContainer.setBorder(new EmptyBorder(20, 60, 60, 60));

        JPanel cardCB = createCard(
                "Carte Bancaire",
                "Paiement immédiat par carte.",
                "Payer par carte",
                new IconCard(),
                e -> {
                    try {
                        PaiementVoirie pagePaiementCB = new PaiementVoirie(zone, duree);
                        pagePaiementCB.setVisible(true);
                        dispose();
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
        );

        JPanel cardVirement = createCard(
                "Virement Bancaire",
                "Saisir IBAN pour prélèvement SEPA.",
                "Payer par virement",
                new IconBank(),
                e -> {
                    try {
                        PaiementVirement pageVirement = new PaiementVirement(zone, duree);
                        pageVirement.setVisible(true);
                        dispose();
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
        );

        cardsContainer.add(cardCB);
        cardsContainer.add(cardVirement);

        mainPanel.add(headerPanel, BorderLayout.NORTH);
        mainPanel.add(cardsContainer, BorderLayout.CENTER);

        add(mainPanel);
    }

    private JPanel createCard(String title, String subtitle, String buttonText, Icon icon, ActionListener action) {
        JPanel card = new JPanel();
        card.setLayout(new GridBagLayout());
        card.setBackground(CARD_COLOR);
        card.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(new Color(220, 220, 220), 1),
                new EmptyBorder(30, 30, 30, 30)
        ));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(10, 0, 10, 0);

        JLabel lblIcon = new JLabel(icon);
        
        JLabel lblTitle = new JLabel(title);
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 20));
        lblTitle.setForeground(TEXT_COLOR);

        JLabel lblSubtitle = new JLabel(subtitle);
        lblSubtitle.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lblSubtitle.setForeground(SUBTEXT_COLOR);

        JButton btn = new JButton(buttonText);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btn.setBackground(BUTTON_COLOR);
        btn.setForeground(BUTTON_TEXT_COLOR);
        btn.setFocusPainted(false);
        btn.setBorder(new EmptyBorder(12, 25, 12, 25));
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.addActionListener(action);

        btn.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) { btn.setBackground(BUTTON_COLOR.darker()); }
            public void mouseExited(MouseEvent e) { btn.setBackground(BUTTON_COLOR); }
        });

        card.add(lblIcon, gbc);
        card.add(lblTitle, gbc);
        gbc.insets = new Insets(0, 0, 25, 0);
        card.add(lblSubtitle, gbc);
        gbc.insets = new Insets(10, 0, 0, 0);
        card.add(btn, gbc);

        return card;
    }

    private class IconCard implements Icon {
        @Override
        public void paintIcon(Component c, Graphics g, int x, int y) {
            Graphics2D g2 = (Graphics2D) g;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(TEXT_COLOR);
            g2.fillRoundRect(x, y + 10, 50, 35, 8, 8);
            g2.setColor(Color.WHITE);
            g2.fillRect(x, y + 18, 50, 6);
            g2.fillRect(x + 5, y + 32, 10, 6);
        }
        @Override public int getIconWidth() { return 50; }
        @Override public int getIconHeight() { return 60; }
    }

    private class IconBank implements Icon {
        @Override
        public void paintIcon(Component c, Graphics g, int x, int y) {
            Graphics2D g2 = (Graphics2D) g;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(TEXT_COLOR);
            int[] xPoints = {x + 25, x, x + 50};
            int[] yPoints = {y, y + 15, y + 15};
            g2.fillPolygon(xPoints, yPoints, 3);
            g2.fillRect(x + 5, y + 18, 40, 5);
            g2.fillRect(x + 8, y + 25, 6, 20); 
            g2.fillRect(x + 22, y + 25, 6, 20); 
            g2.fillRect(x + 36, y + 25, 6, 20); 
            g2.fillRect(x + 2, y + 47, 46, 5);
        }
        @Override public int getIconWidth() { return 50; }
        @Override public int getIconHeight() { return 60; }
    }


}