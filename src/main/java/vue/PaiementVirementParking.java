package vue;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import modele.ReservationParking;
import controleur.ControleurPaiementVirementParking;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PaiementVirementParking extends JFrame {
    
    private final Color BACKGROUND_COLOR = new Color(248, 249, 250);
    private final Color BUTTON_COLOR = new Color(13, 110, 253);
    
    private ReservationParking reservation;
    private double prix;

    public PaiementVirementParking(ReservationParking reservation, double prix) {
    	this.reservation = reservation;
    	this.prix = prix;
    	
        setTitle("Virement Bancaire");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(450, 500);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBackground(BACKGROUND_COLOR);

        JPanel formCard = new JPanel();
        formCard.setLayout(new BoxLayout(formCard, BoxLayout.Y_AXIS));
        formCard.setBackground(Color.WHITE);
        formCard.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(new Color(220, 220, 220), 1),
                new EmptyBorder(40, 40, 40, 40)
        ));

        JLabel title = new JLabel("Détails du compte");
        title.setFont(new Font("Segoe UI", Font.BOLD, 22));
        title.setAlignmentX(Component.LEFT_ALIGNMENT);

        formCard.add(title);
        formCard.add(Box.createVerticalStrut(30));

        addField(formCard, "Nom et Prénom");
        addField(formCard, "IBAN");

        JButton btnPayer = new JButton("Payer " + prix + "€");
        this.btnPayer = btnPayer;
        btnPayer.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnPayer.setBackground(BUTTON_COLOR);
        btnPayer.setForeground(Color.WHITE);
        btnPayer.setFocusPainted(false);
        btnPayer.setBorder(new EmptyBorder(12, 0, 12, 0));
        btnPayer.setMaximumSize(new Dimension(Integer.MAX_VALUE, 45));
        btnPayer.setCursor(new Cursor(Cursor.HAND_CURSOR));

        btnPayer.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) { btnPayer.setBackground(BUTTON_COLOR.darker()); }
            public void mouseExited(MouseEvent e) { btnPayer.setBackground(BUTTON_COLOR); }
        });
        

        formCard.add(Box.createVerticalStrut(30));
        formCard.add(btnPayer);

        mainPanel.add(formCard);
        add(mainPanel);

        new ControleurPaiementVirementParking(this);
    }

    private JButton btnPayer;

    private void addField(JPanel panel, String labelText) {
        JLabel label = new JLabel(labelText);
        label.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        label.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        JTextField field = new JTextField();
        field.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        field.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        field.setBorder(BorderFactory.createCompoundBorder(
            new LineBorder(new Color(200, 200, 200)), 
            new EmptyBorder(5, 10, 5, 10))
        );
        field.setAlignmentX(Component.LEFT_ALIGNMENT);

        panel.add(label);
        panel.add(Box.createVerticalStrut(5));
        panel.add(field);
        panel.add(Box.createVerticalStrut(20));
    }

    public JButton getBtnPayer() {
        return this.btnPayer;
    }

    public ReservationParking getReservation() {
        return this.reservation;
    }

    public double getPrix() {
        return this.prix;
    }
}