package vue;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import controleur.ControleurChoixParking;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ChoixTypeStationnement extends JFrame {

    private final Color BACKGROUND_COLOR = new Color(248, 249, 250);
    private final Color CARD_COLOR = Color.WHITE;
    private final Color TEXT_COLOR = new Color(33, 37, 41);
    private final Color SUBTEXT_COLOR = new Color(108, 117, 125);
    private final Color BUTTON_COLOR = new Color(13, 110, 253);
    private final Color BUTTON_TEXT_COLOR = Color.WHITE;

    public ChoixTypeStationnement() {
        setTitle("Stationnement");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 600);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(BACKGROUND_COLOR);

        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setOpaque(false);
        headerPanel.setBorder(new EmptyBorder(25, 40, 5, 40));

        JLabel titleLabel = new JLabel(" Choisissez votre type de stationnement");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
        titleLabel.setForeground(TEXT_COLOR);
        titleLabel.setIcon(new IconP());
        
        JButton profileButton = new JButton("Mon Profil");
        profileButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        profileButton.setForeground(TEXT_COLOR);
        profileButton.setBackground(Color.WHITE);
        profileButton.setBorder(new LineBorder(new Color(220, 220, 220), 1));
        profileButton.setFocusPainted(false);
        profileButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        profileButton.setIcon(new IconProfile());
        profileButton.setIconTextGap(10);

        profileButton.setMargin(new Insets(5, 15, 5, 15)); 

        profileButton.addActionListener(e -> {
            // Redirection vers la page du profil
        });

        headerPanel.add(titleLabel, BorderLayout.WEST);
        headerPanel.add(profileButton, BorderLayout.EAST);

        JPanel cardsContainer = new JPanel(new GridLayout(1, 2, 30, 0));
        cardsContainer.setOpaque(false);
        cardsContainer.setBorder(new EmptyBorder(20, 40, 40, 40));

        JPanel cardParking = createCard(
                "Stationnement Parking",
                "Stationner dans un parking sécurisé au choix.",
                "Trouver un parking",
                e -> {
                    try {
                        ControleurChoixParking frameChoixParking = new ControleurChoixParking();
                        dispose();
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
        );

        JPanel cardVoirie = createCard(
                "Stationnement en Voirie",
                "Stationner en voirie dans une zone au choix.",
                "Trouver un emplacement",
                e -> {
                    try {
                        ChoixZone frameChoixZone = new ChoixZone();
                        frameChoixZone.setVisible(true);
                        dispose();
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
        );

        cardsContainer.add(cardParking);
        cardsContainer.add(cardVoirie);

        mainPanel.add(headerPanel, BorderLayout.NORTH);
        mainPanel.add(cardsContainer, BorderLayout.CENTER);

        add(mainPanel);
    }

    private JPanel createCard(String title, String subtitle, String buttonText, ActionListener action) {
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
        gbc.insets = new Insets(5, 0, 5, 0);

        JLabel lblTitle = new JLabel(title);
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lblTitle.setForeground(TEXT_COLOR);

        JLabel lblSubtitle = new JLabel(subtitle);
        lblSubtitle.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lblSubtitle.setForeground(SUBTEXT_COLOR);

        JButton btn = new JButton(buttonText);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btn.setBackground(BUTTON_COLOR);
        btn.setForeground(BUTTON_TEXT_COLOR);
        btn.setFocusPainted(false);
        btn.setBorder(new EmptyBorder(10, 20, 10, 20));
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        btn.addActionListener(action);

        btn.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                btn.setBackground(BUTTON_COLOR.darker());
            }

            public void mouseExited(MouseEvent e) {
                btn.setBackground(BUTTON_COLOR);
            }
        });

        card.add(lblTitle, gbc);
        gbc.insets = new Insets(5, 0, 20, 0);
        card.add(lblSubtitle, gbc);
        card.add(btn, gbc);

        return card;
    }

    private class IconP implements Icon {
        @Override
        public void paintIcon(Component c, Graphics g, int x, int y) {
            Graphics2D g2 = (Graphics2D) g;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(TEXT_COLOR);
            g2.setStroke(new BasicStroke(2));
            g2.drawRoundRect(x, y, 30, 30, 10, 10);
            g2.setFont(new Font("Segoe UI", Font.BOLD, 20));
            g2.drawString("P", x + 10, y + 23);
        }

        @Override
        public int getIconWidth() { return 35; }

        @Override
        public int getIconHeight() { return 35; }
    }

    private class IconProfile implements Icon {
        @Override
        public void paintIcon(Component c, Graphics g, int x, int y) {
            Graphics2D g2 = (Graphics2D) g;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(TEXT_COLOR);
            
            g2.fillOval(x + 6, y + 2, 12, 12);
            g2.fillArc(x + 2, y + 16, 20, 14, 0, 180);
        }

        @Override
        public int getIconWidth() { return 24; }

        @Override
        public int getIconHeight() { return 24; }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ChoixTypeStationnement().setVisible(true));
    }
}