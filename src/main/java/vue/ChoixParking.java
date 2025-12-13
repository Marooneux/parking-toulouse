package vue;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;
import java.time.LocalTime;
import java.util.function.Consumer;

import modele.Parking;

public class ChoixParking extends JFrame {

    private JPanel gridPanel;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                ChoixParking frame = new ChoixParking();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public ChoixParking() {
        initialize();
    }

    private void initialize() {
        setTitle("Stationnement");
        setBounds(100, 100, 1200, 750);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(new Color(248, 249, 250));
        getContentPane().setLayout(new BorderLayout(0, 0));

        JPanel headerPanel = new JPanel();
        headerPanel.setLayout(new BoxLayout(headerPanel, BoxLayout.Y_AXIS));
        headerPanel.setBackground(new Color(248, 249, 250));
        headerPanel.setBorder(new EmptyBorder(40, 50, 30, 50));

        JPanel titleContainer = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        titleContainer.setBackground(new Color(248, 249, 250));
        
        JPanel subtitleContainer = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        subtitleContainer.setBackground(new Color(248, 249, 250));

        JLabel iconCar = new JLabel("\uD83C\uDD7F\uFE0F"); 
        iconCar.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 32));
        
        JLabel lblTitle = new JLabel("Démarrer le Stationnement");
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 26));
        lblTitle.setForeground(new Color(33, 37, 41));

        titleContainer.add(iconCar);
        titleContainer.add(lblTitle);

        JLabel lblSousTitre = new JLabel("Sélectionnez le parking souhaité.");
        lblSousTitre.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lblSousTitre.setForeground(new Color(108, 117, 125));
        lblSousTitre.setAlignmentX(Component.LEFT_ALIGNMENT);
        lblSousTitre.setBorder(new EmptyBorder(10, 0, 0, 0));
        subtitleContainer.add(lblSousTitre);
        
        headerPanel.add(titleContainer);
        headerPanel.add(subtitleContainer);
        
        getContentPane().add(headerPanel, BorderLayout.NORTH);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBorder(null);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        scrollPane.getViewport().setBackground(new Color(248, 249, 250));

        gridPanel = new JPanel();
        gridPanel.setBackground(new Color(248, 249, 250));
        gridPanel.setLayout(new GridLayout(0, 3, 25, 25));
        gridPanel.setBorder(new EmptyBorder(0, 50, 50, 50));

        scrollPane.setViewportView(gridPanel);
        getContentPane().add(scrollPane, BorderLayout.CENTER);

        addParking(new Parking("Parking Centre-Ville", "12 Rue de la République", 2.5, 120, 190, 
                LocalTime.of(0, 0), LocalTime.of(0, 0)));

        addParking(new Parking("Gare Saint-Roch", "Place de la Gare", 3.10, 45, 210, 
                LocalTime.of(5, 0), LocalTime.of(1, 0)));

        addParking(new Parking("Victor Hugo", "Bd Victor Hugo", 1.80, 12, 180, 
                LocalTime.of(8, 0), LocalTime.of(20, 0)));

        addParking(new Parking("Les Halles", "Rue du Marché", 2.20, 230, 200, 
                LocalTime.of(6, 0), LocalTime.of(22, 0)));
        
        addParking(new Parking("Polygone", "Av. des États du Languedoc", 2.00, 1500, 220, 
                LocalTime.of(9, 0), LocalTime.of(21, 0)));
        
        addParking(new Parking("Antigone", "Place du Nombre d'Or", 1.50, 80, 190, 
                LocalTime.of(7, 0), LocalTime.of(23, 0)));
    }

    private void addParking(Parking parking) {
        ParkingCard card = new ParkingCard(parking, p -> {
            try {
                SaisirHeureArriveParking frameSaisirHeureArrive = new SaisirHeureArriveParking();
                frameSaisirHeureArrive.setVisible(true);                
                dispose();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
        gridPanel.add(card);
    }
}