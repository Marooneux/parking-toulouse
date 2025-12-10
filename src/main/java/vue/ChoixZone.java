package vue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagLayout;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;

public class ChoixZone extends JFrame {

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    ChoixZone frame = new ChoixZone();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public ChoixZone() {
        setBounds(100, 100, 1100, 850);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(new BorderLayout(0, 0));

        JPanel header = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 20));
        header.setBackground(new Color(250, 250, 250));
        header.setBorder(new javax.swing.border.MatteBorder(0, 0, 1, 0, new Color(230, 230, 230)));

        JLabel lblIcon = new JLabel("\uD83D\uDE97"); 
        lblIcon.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 36));
        header.add(lblIcon);

        JPanel texte = new JPanel();
        texte.setBackground(new Color(250, 250, 250));
        texte.setLayout(new BoxLayout(texte, BoxLayout.Y_AXIS));

        JLabel lblTitre = new JLabel("Démarrer le Stationnement");
        lblTitre.setFont(new Font("Segoe UI", Font.BOLD, 22));
        lblTitre.setForeground(new Color(40, 40, 40));
        texte.add(lblTitre);
        
        JLabel lblSousTitre = new JLabel("Sélectionnez votre zone de stationnement. La sélection de la mauvaise zone entrainera une amende.");
        lblSousTitre.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lblSousTitre.setForeground(Color.GRAY);
        texte.add(lblSousTitre);

        header.add(texte);
        getContentPane().add(header, BorderLayout.NORTH);

        JPanel mainCenterPanel = new JPanel();
        mainCenterPanel.setBackground(new Color(245, 247, 250));
        mainCenterPanel.setLayout(new GridBagLayout()); 
        
        JPanel gridContainer = new JPanel();
        gridContainer.setOpaque(false);
        gridContainer.setLayout(new BoxLayout(gridContainer, BoxLayout.Y_AXIS));

        JPanel row1 = new JPanel(new FlowLayout(FlowLayout.CENTER, 30, 0));
        row1.setOpaque(false);
        row1.add(createCard("Zone jaune", new Color(255, 204, 0), "Lundi au samedi", "9h00 - 20h00", 150, "1.50€/h"));
        row1.add(createCard("Zone orange", new Color(255, 149, 0), "Lundi au samedi", "9h00 - 19h00", 300, "1.00€/h"));
        row1.add(createCard("Zone rouge", new Color(255, 59, 48), "Lundi au samedi", "9h00 - 19h00", 180, "1.00€/h"));
        
        gridContainer.add(row1);
        gridContainer.add(Box.createVerticalStrut(30)); 

        JPanel row2 = new JPanel(new FlowLayout(FlowLayout.CENTER, 30, 0));
        row2.setOpaque(false);
        row2.add(createCard("Zone verte", new Color(0, 128, 0), "Lundi au samedi", "9h00 - 20h00", 300, "0.50€/h"));
        row2.add(createCard("Zone bleu (disque requis)", new Color(0, 122, 255), "Lundi au samedi",
        					"9h00 - 12h00, 14h00 - 19h00", 90, "Gratuit"));
        
        gridContainer.add(row2);
        
        mainCenterPanel.add(gridContainer);
        getContentPane().add(mainCenterPanel, BorderLayout.CENTER);
    }

    private JPanel createCard(String couleurZone, Color themeColor, String jours, String horaires, int dureeMax, String tarif) {
        
        JPanel card = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(Color.WHITE);
                g2.fillRoundRect(0, 0, getWidth()-1, getHeight()-1, 20, 20);
                g2.setColor(new Color(230, 230, 230));
                g2.drawRoundRect(0, 0, getWidth()-1, getHeight()-1, 20, 20);
            }
        };
        
        card.setPreferredSize(new Dimension(300, 330));
        card.setMaximumSize(new Dimension(300, 330));
        card.setMinimumSize(new Dimension(300, 330));
        
        card.setOpaque(false);
        card.setLayout(null);
        card.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        JPanel iconCircle = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(themeColor);
                g2.fillOval(0, 0, getWidth(), getHeight());
                g2.setColor(Color.WHITE);
                g2.setFont(new Font("Segoe UI", Font.BOLD, 20));
                g2.drawString("P", 14, 27);
            }
        };
        iconCircle.setBounds(25, 25, 40, 40);
        iconCircle.setOpaque(false);
        card.add(iconCircle);

        JLabel lblTitre = new JLabel(couleurZone);
        lblTitre.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lblTitre.setForeground(new Color(33, 33, 33));
        lblTitre.setBounds(25, 80, 250, 25);
        card.add(lblTitre);

        JLabel iconClock = new JLabel("🕒"); 
        iconClock.setForeground(new Color(150, 150, 150));
        iconClock.setBounds(25, 120, 20, 20);
        card.add(iconClock);

        JLabel lblHorairesTitle = new JLabel("Horaires payants");
        lblHorairesTitle.setFont(new Font("Segoe UI", Font.BOLD, 12));
        lblHorairesTitle.setForeground(Color.GRAY);
        lblHorairesTitle.setBounds(50, 120, 120, 15);
        card.add(lblHorairesTitle);
        
        JLabel lblJoursVal = new JLabel(jours);
        lblJoursVal.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lblJoursVal.setForeground(new Color(50, 50, 50));
        lblJoursVal.setBounds(50, 138, 150, 20);
        card.add(lblJoursVal);

        JLabel lblHorairesVal = new JLabel(horaires);
        lblHorairesVal.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lblHorairesVal.setForeground(new Color(50, 50, 50));
        lblHorairesVal.setBounds(50, 158, 180, 20);
        card.add(lblHorairesVal);

        JLabel iconSand = new JLabel("⏳"); 
        iconSand.setForeground(new Color(150, 150, 150));
        iconSand.setBounds(25, 190, 20, 20);
        card.add(iconSand);

        JLabel lblDureeTitle = new JLabel("Durée max");
        lblDureeTitle.setFont(new Font("Segoe UI", Font.BOLD, 12));
        lblDureeTitle.setForeground(Color.GRAY);
        lblDureeTitle.setBounds(50, 190, 120, 15);
        card.add(lblDureeTitle);

        JLabel lblDureeVal = new JLabel(dureeMaxToString(dureeMax));
        lblDureeVal.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lblDureeVal.setForeground(new Color(50, 50, 50));
        lblDureeVal.setBounds(50, 208, 150, 20);
        card.add(lblDureeVal);
        
        JSeparator separator = new JSeparator();
        separator.setForeground(new Color(240, 240, 240));
        separator.setBounds(25, 250, 250, 2);
        card.add(separator);

        JLabel lblTarifTitle = new JLabel("Tarif");
        lblTarifTitle.setFont(new Font("Segoe UI", Font.BOLD, 13));
        lblTarifTitle.setForeground(Color.GRAY);
        lblTarifTitle.setBounds(25, 270, 100, 20);
        card.add(lblTarifTitle);

        JLabel lblPrix = new JLabel(tarif);
        lblPrix.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lblPrix.setHorizontalAlignment(SwingConstants.RIGHT);
        lblPrix.setBounds(175, 270, 100, 20);
        card.add(lblPrix);

        card.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    SaisirDureeStationnement frameDureeStationnement = new SaisirDureeStationnement(couleurZone, dureeMax);
                    frameDureeStationnement.setVisible(true);
                    ChoixZone.this.dispose(); 
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        return card;
    }
    
    public String dureeMaxToString(int dureeMax) {
    	String duree = " heures";
    	int heures = dureeMax / 60;
    	int minutes = dureeMax % 60;
    	if (heures <= 1) {
    		duree = duree.substring(0, duree.length() - 1);
    	}
    	if (minutes > 0) {
    		duree = duree + " " + minutes + " minutes";
    	}
    	return (heures + duree);
    }
}