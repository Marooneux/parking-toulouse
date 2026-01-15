package vue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.border.LineBorder;

import modele.StationnementVoirie;

import java.awt.GridLayout;
import java.awt.Cursor;
import java.awt.Dimension;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ConfirmationPaiementVoirie extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private StationnementVoirie zone;
    private String immatriculation;
    private int duree;
    private double prix;
    private String moyenPaiement;


    public ConfirmationPaiementVoirie(StationnementVoirie zone, String immatriculation, int duree, double prix, String moyenPaiement) {
    	this.zone = zone;
    	this.immatriculation = immatriculation;
    	this.duree = duree;
    	this.prix = prix;
    	this.moyenPaiement = moyenPaiement;
    	
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 750, 550);
        setTitle("Paiement validé");
        
        contentPane = new JPanel();
        contentPane.setBackground(new Color(255, 255, 255));
        contentPane.setBorder(new EmptyBorder(20, 20, 20, 20));
        setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout(0, 0));

        JPanel panelCenterContainer = new JPanel();
        panelCenterContainer.setBackground(new Color(255, 255, 255));
        panelCenterContainer.setBorder(new EmptyBorder(40, 100, 40, 100));
        contentPane.add(panelCenterContainer, BorderLayout.CENTER);
        panelCenterContainer.setLayout(new BorderLayout(0, 0));

        JPanel panelCard = new JPanel();
        panelCard.setBorder(new LineBorder(new Color(222, 226, 230), 1, true));
        panelCard.setBackground(new Color(255, 255, 255));
        panelCenterContainer.add(panelCard);
        panelCard.setLayout(new BorderLayout(0, 0));

        JPanel panelInnerContent = new JPanel();
        panelInnerContent.setBackground(Color.WHITE);
        panelInnerContent.setBorder(new EmptyBorder(30, 20, 30, 20));
        panelCard.add(panelInnerContent, BorderLayout.CENTER);
        panelInnerContent.setLayout(new GridLayout(5, 1, 0, 10));

        JLabel lblIconSuccess = new JLabel("✔");
        lblIconSuccess.setForeground(new Color(40, 167, 69));
        lblIconSuccess.setFont(new Font("Segoe UI Symbol", Font.BOLD, 50));
        lblIconSuccess.setHorizontalAlignment(SwingConstants.CENTER);
        panelInnerContent.add(lblIconSuccess);

        JLabel lblTitre = new JLabel("Paiement Validé !");
        lblTitre.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitre.setForeground(new Color(40, 167, 69));
        lblTitre.setFont(new Font("Segoe UI", Font.BOLD, 26));
        panelInnerContent.add(lblTitre);

        JLabel lblMerci = new JLabel("Merci de votre visite");
        lblMerci.setHorizontalAlignment(SwingConstants.CENTER);
        lblMerci.setForeground(new Color(100, 100, 100));
        lblMerci.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        panelInnerContent.add(lblMerci);

        JLabel lblMontant = new JLabel("Montant réglé : " + String.format("%.2f €", prix));
        lblMontant.setHorizontalAlignment(SwingConstants.CENTER);
        lblMontant.setForeground(new Color(33, 37, 41));
        lblMontant.setFont(new Font("Segoe UI", Font.BOLD, 20));
        panelInnerContent.add(lblMontant);

        JPanel panelFooter = new JPanel();
        panelFooter.setBackground(new Color(255, 255, 255));
        panelFooter.setBorder(new EmptyBorder(10, 0, 20, 0));
        contentPane.add(panelFooter, BorderLayout.SOUTH);
        
        JButton btnTerminer = new JButton("Voir le e-ticket");
        btnTerminer.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnTerminer.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    try {
                    	TicketVoirie framePaiement = new TicketVoirie(zone, immatriculation, duree, moyenPaiement);
                        framePaiement.setVisible(true);
                        dispose();
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            });
        btnTerminer.setForeground(Color.WHITE);
        btnTerminer.setFont(new Font("Segoe UI", Font.BOLD, 16));
        btnTerminer.setBackground(new Color(0, 123, 255));
        btnTerminer.setFocusPainted(false);
        btnTerminer.setBorderPainted(false);
        btnTerminer.setPreferredSize(new Dimension(250, 45));
        
        panelFooter.add(btnTerminer);
    }
}