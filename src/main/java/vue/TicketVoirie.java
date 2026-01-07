package vue;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JButton;
import javax.swing.border.LineBorder;

import modele.StationnementVoirie;
import modele.StationnementVoirie.Couleur;

import java.awt.GridLayout;
import java.awt.RenderingHints;
import java.awt.Cursor;
import java.awt.Dimension;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.time.LocalTime;
import java.awt.event.ActionEvent;
import controleur.ControleurTicketVoirie;

public class TicketVoirie extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private StationnementVoirie zone;
    private String immatriculation; 
    private int duree;
    private String moyenPaiement;
    private JLabel nomZone;

    public TicketVoirie(StationnementVoirie zone, String immatriculation, int duree, String moyenPaiement) {
    	this.zone = zone;
    	this.immatriculation = immatriculation;
    	this.duree = duree;
    	this.moyenPaiement = moyenPaiement;
    	this.nomZone = new JLabel(zone.couleurZoneToString());
    	nomZone.setForeground(ControleurTicketVoirie.getRgb(zone.getCouleur()));
    	
    	
    	
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 750, 600);
        setTitle("Ticket de voirie");
        
        contentPane = new JPanel();
        contentPane.setBackground(new Color(255, 255, 255));
        contentPane.setBorder(new EmptyBorder(20, 20, 20, 20));
        setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout(0, 0));

        JPanel panelHeader = new JPanel();
        panelHeader.setBackground(new Color(255, 255, 255));
        contentPane.add(panelHeader, BorderLayout.NORTH);
        panelHeader.setLayout(new BorderLayout(20, 0));

        iconCircle.setOpaque(false);
        iconCircle.setFont(new Font("Segoe UI", Font.BOLD, 30));
        iconCircle.setBounds(25, 25, 40, 40);
        iconCircle.setPreferredSize(new Dimension(60, 60));
        panelHeader.add(iconCircle, BorderLayout.WEST);

        JPanel panelTextHeader = new JPanel();
        panelTextHeader.setBackground(Color.WHITE);
        panelHeader.add(panelTextHeader, BorderLayout.CENTER);
        panelTextHeader.setLayout(new GridLayout(2, 1, 0, 0));

        JLabel lblTitre = new JLabel("Récapitulatif de stationnement");
        lblTitre.setForeground(new Color(33, 37, 41));
        lblTitre.setFont(new Font("Segoe UI", Font.BOLD, 24));
        panelTextHeader.add(lblTitre);

        JLabel lblSousTitre = new JLabel("Veuillez conserver ce récapitulatif jusqu'à votre départ");
        lblSousTitre.setForeground(new Color(108, 117, 125));
        lblSousTitre.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        panelTextHeader.add(lblSousTitre);

        JPanel panelCenterContainer = new JPanel();
        panelCenterContainer.setBackground(new Color(255, 255, 255));
        panelCenterContainer.setBorder(new EmptyBorder(20, 80, 10, 80));
        contentPane.add(panelCenterContainer, BorderLayout.CENTER);
        panelCenterContainer.setLayout(new BorderLayout(0, 0));

        JPanel panelCard = new JPanel();
        panelCard.setBorder(new LineBorder(new Color(222, 226, 230), 1, true));
        panelCard.setBackground(new Color(255, 255, 255));
        panelCenterContainer.add(panelCard);
        panelCard.setLayout(new BorderLayout(0, 0));

        JPanel panelInfoGrid = new JPanel();
        panelInfoGrid.setBackground(new Color(255, 255, 255));
        panelInfoGrid.setBorder(new EmptyBorder(20, 30, 20, 30));
        panelCard.add(panelInfoGrid, BorderLayout.CENTER);
        panelInfoGrid.setLayout(new GridLayout(6, 1, 0, 10));
        
        createInfoRow(panelInfoGrid, "Numéro de Ticket :", new JLabel("#V-00001"));
        createInfoRow(panelInfoGrid, "Zone :", nomZone);
        createInfoRow(panelInfoGrid, "Immatriculation :", new JLabel(immatriculation));
        createInfoRow(panelInfoGrid, "Heure d'arrivée :", new JLabel(ControleurTicketVoirie.getHeureActuelle()));
        createInfoRow(panelInfoGrid, "Heure départ max :", new JLabel(ControleurTicketVoirie.calculerHeureDepart(duree)));
        createInfoRow(panelInfoGrid, "Moyen de paiement :", new JLabel(moyenPaiement));

        
        JPanel panelFooter = new JPanel();
        panelFooter.setBackground(new Color(255, 255, 255));
        panelFooter.setBorder(new EmptyBorder(10, 0, 10, 0));
        contentPane.add(panelFooter, BorderLayout.SOUTH);
        panelFooter.setLayout(new GridLayout(2, 1, 0, 10));

        JLabel lblWarning = new JLabel("Lorsque vous souhaitez partir, appuyer sur le bouton suivant");
        lblWarning.setHorizontalAlignment(SwingConstants.CENTER);
        lblWarning.setForeground(new Color(33, 37, 41));
        lblWarning.setFont(new Font("Segoe UI", Font.BOLD, 13));
        panelFooter.add(lblWarning);
        
        JPanel panelButtonContainer = new JPanel();
        panelButtonContainer.setBackground(Color.WHITE);
        panelFooter.add(panelButtonContainer);

        JButton btnPaiement = new JButton("Confirmer le départ");
        panelButtonContainer.add(btnPaiement);
        btnPaiement.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnPaiement.setForeground(Color.WHITE);
        btnPaiement.setFont(new Font("Segoe UI", Font.BOLD, 16));
        btnPaiement.setBackground(new Color(0, 123, 255));
        btnPaiement.setFocusPainted(false);
        btnPaiement.setBorderPainted(false);
        btnPaiement.setPreferredSize(new Dimension(200, 45));
        btnPaiement.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            System.exit(0);
        }
    });
        
    }

    private void createInfoRow(JPanel parent, String label, JLabel lblVal) {
        JPanel row = new JPanel();
        row.setBackground(Color.WHITE);
        row.setLayout(new BorderLayout());
        
        JLabel lblKey = new JLabel(label);
        lblKey.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        lblKey.setForeground(new Color(100, 100, 100));
        

        lblVal.setFont(new Font("Segoe UI", Font.BOLD, 16));

        lblVal.setHorizontalAlignment(SwingConstants.RIGHT);
        
        row.add(lblKey, BorderLayout.WEST);
        row.add(lblVal, BorderLayout.EAST);
        
        JPanel separator = new JPanel();
        separator.setPreferredSize(new Dimension(10, 1));
        separator.setBackground(new Color(245, 245, 245));
        row.add(separator, BorderLayout.SOUTH);
        
        parent.add(row);
    }

    JPanel iconCircle = new JPanel() {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g); 
            
            Graphics2D g2 = (Graphics2D) g;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(ControleurTicketVoirie.getRgb(zone.getCouleur()));
            g2.fillOval(0, 0, getWidth(), getHeight());
            g2.setColor(Color.WHITE);
            g2.setFont(new Font("Segoe UI", Font.BOLD, 25));
            String texte = "P";
            FontMetrics metrics = g2.getFontMetrics();

            int x = (getWidth() - metrics.stringWidth(texte)) / 2;
            int y = ((getHeight() - metrics.getHeight()) / 2) + metrics.getAscent();

            g2.drawString(texte, x, y);
        }
    };

}
