package vue;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

import controleur.ControleurChoixParking;

import java.awt.*;
import java.time.LocalTime;
import java.util.function.Consumer;

import modele.Parking;

public class ChoixParking extends JFrame {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel gridPanel;


    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ChoixParking().setVisible(true));
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
        
    }

    public void addParking(Parking parking, Consumer<Parking> onSelect) {
        ParkingPanel card = new ParkingPanel(parking, onSelect);
        gridPanel.add(card);
    }
    
    public void populateDefaultParkings(Consumer<Parking> onSelect) {
        addParking(new Parking("Parking Centre-Ville","12 Rue de la République",2.5, 120,2.5,LocalTime.of(0, 0),LocalTime.of(0, 0),false), onSelect);

        addParking(new Parking("Gare Saint-Roch","Place de la Gare",1.2, 45,3.10,LocalTime.of(5, 0),LocalTime.of(1, 0),true), onSelect);

        addParking(new Parking("Victor Hugo","Bd Victor Hugo", 1.7, 12,1.80,LocalTime.of(8, 0),LocalTime.of(20, 0),false), onSelect);

        addParking(new Parking("Les Halles","Rue du Marché",1, 230,2.20, LocalTime.of(6, 0),LocalTime.of(22, 0),false), onSelect);

        addParking(new Parking("Polygone","Av. des États du Languedoc",1.5, 1500,2.00,LocalTime.of(9, 0),LocalTime.of(21, 0),true), onSelect);

        addParking(new Parking("Antigone","Place du Nombre d'Or",1.9, 80,1.50,LocalTime.of(7, 0),LocalTime.of(23, 0),false), onSelect);
    }

}

