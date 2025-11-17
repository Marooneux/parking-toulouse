package VUE;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class SaisirHeureArriveParking extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField textField;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                SaisirHeureArriveParking frame = new SaisirHeureArriveParking();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public SaisirHeureArriveParking() {
        setTitle("Démarrer le Stationnement");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 600);
        setLocationRelativeTo(null);

        contentPane = new JPanel(new BorderLayout(15, 15));
        contentPane.setBorder(new EmptyBorder(20, 20, 20, 20));
        contentPane.setBackground(new Color(250, 250, 250));
        setContentPane(contentPane);

        // Header
        JPanel header = new JPanel(new FlowLayout(FlowLayout.LEFT));
        header.setBackground(new Color(250, 250, 250));

        JLabel lblIcon = new JLabel("\uD83C\uDFE2");
        lblIcon.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 28));
        header.add(lblIcon);

        JPanel texte = new JPanel();
        texte.setBackground(new Color(250, 250, 250));
        texte.setLayout(new BoxLayout(texte, BoxLayout.Y_AXIS));

        JLabel lblTitre = new JLabel("Démarrer le Stationnement");
        lblTitre.setFont(new Font("Segoe UI", Font.BOLD, 22));
        lblTitre.setForeground(new Color(40, 40, 40));
        texte.add(lblTitre);

        JLabel lblSousTitre = new JLabel("Enregistrez votre arrivée au parking");
        lblSousTitre.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lblSousTitre.setForeground(new Color(100, 100, 100));
        texte.add(lblSousTitre);

        header.add(texte);
        contentPane.add(header, BorderLayout.NORTH);

        // Corps
        JPanel body = new JPanel();
        body.setLayout(new BoxLayout(body, BoxLayout.Y_AXIS));
        body.setBackground(new Color(250, 250, 250));
        body.setBorder(new EmptyBorder(10, 0, 10, 0));
        contentPane.add(body, BorderLayout.CENTER);

        ajouterCarteDeDétails(body, "Parking Sélectionné", detailsDuParking());
        ajouterCarteDeDétails(body, "Informations du Véhicule", detailsVoiture());
        ajouterCarteDeDétails(body, "Heure d'Arrivée", detailsHeureArrive());

        // Button
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(250, 250, 250));

        JButton btnStart = new JButton("Démarrer le Stationnement");
        btnStart.setFont(new Font("Segoe UI", Font.BOLD, 16));
        btnStart.setBackground(new Color(0, 122, 255));
        btnStart.setForeground(Color.WHITE);
        btnStart.setFocusPainted(false);
        btnStart.setBorderPainted(false);
        btnStart.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnStart.setPreferredSize(new Dimension(240, 40));
        btnStart.setOpaque(true);
        buttonPanel.add(btnStart);
        contentPane.add(buttonPanel, BorderLayout.SOUTH);
    }

    private JPanel detailsDuParking() {
        JPanel p = new JPanel();
        p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
        p.setBackground(Color.WHITE);
        p.setBorder(new EmptyBorder(15, 15, 15, 15));

        JLabel lblParking = new JLabel("Parking Capitole");
        lblParking.setFont(new Font("Segoe UI", Font.BOLD, 15));
        lblParking.setForeground(new Color(50, 50, 50));
        p.add(lblParking);

        return p;
    }

    private JPanel detailsVoiture() {
        JPanel p = new JPanel();
        p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
        p.setBackground(Color.WHITE);
        p.setBorder(new EmptyBorder(15, 15, 15, 15));

        JLabel lblInfoVehicule = new JLabel("Informations du Véhicule");
        lblInfoVehicule.setFont(new Font("Segoe UI", Font.BOLD, 15));
        lblInfoVehicule.setForeground(new Color(50, 50, 50));
        p.add(lblInfoVehicule);

        JLabel plaque = new JLabel("AB-123-CD");
        plaque.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        plaque.setForeground(new Color(70, 70, 70));
        plaque.setBorder(new EmptyBorder(5, 0, 0, 0));
        p.add(plaque);

        JLabel info = new JLabel("Nécessaire pour l'entrée et la sortie automatisées");
        info.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        info.setForeground(new Color(120, 120, 120));
        info.setBorder(new EmptyBorder(5, 0, 0, 0));
        p.add(info);

        return p;
    }

    private JPanel detailsHeureArrive() {
        JPanel p = new JPanel();
        p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
        p.setBackground(Color.WHITE);
        p.setBorder(new EmptyBorder(15, 15, 15, 15));

        JLabel lblTitreHeureArrive = new JLabel("Sélectionnez votre heure d'arrivée");
        lblTitreHeureArrive.setFont(new Font("Segoe UI", Font.BOLD, 15));
        lblTitreHeureArrive.setForeground(new Color(50, 50, 50));
        p.add(lblTitreHeureArrive);

        p.add(Box.createRigidArea(new Dimension(0, 8)));

        JPanel heurePanel = new JPanel();
        heurePanel.setLayout(new BoxLayout(heurePanel, BoxLayout.X_AXIS));
        heurePanel.setBackground(Color.WHITE);

        textField = new JTextField();
        textField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        textField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 35));
        textField.setBorder(BorderFactory.createLineBorder(new Color(220, 220, 220), 1, true));
        textField.setBackground(new Color(245, 245, 245));
        heurePanel.add(textField);

        JButton btnNow = new JButton("Maintenant");
        btnNow.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        btnNow.setBackground(new Color(230, 230, 230));
        btnNow.setBorderPainted(false);
        btnNow.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnNow.setOpaque(true);
        heurePanel.add(Box.createRigidArea(new Dimension(10, 0)));
        heurePanel.add(btnNow);

        p.add(heurePanel);

        JLabel info = new JLabel("Vous pourrez quitter l'application et revenir plus tard pour enregistrer votre départ");
        info.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        info.setForeground(new Color(120, 120, 120));
        info.setBorder(new EmptyBorder(10, 0, 0, 0));
        p.add(info);

        return p;
    }

    private void ajouterCarteDeDétails(JPanel parent, String title, JPanel innerContent) {
        JPanel card = new JPanel();
        card.setLayout(new BorderLayout());
        card.setBackground(Color.WHITE);
        card.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createEmptyBorder(10, 0, 10, 0),
                BorderFactory.createLineBorder(new Color(230, 230, 230), 1, true)
        ));

        JPanel header = new JPanel(new FlowLayout(FlowLayout.LEFT));
        header.setBackground(Color.WHITE);

        JLabel lblTitle = new JLabel(title);
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lblTitle.setForeground(new Color(70, 70, 70));
        header.add(lblTitle);

        card.add(header, BorderLayout.NORTH);
        card.add(innerContent, BorderLayout.CENTER);

        parent.add(card);
        parent.add(Box.createRigidArea(new Dimension(0, 10)));    
    }
}
