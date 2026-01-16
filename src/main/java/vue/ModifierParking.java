package vue;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import modele.Parking;
import modele.Adresse; 

public class ModifierParking extends JPanel {

    private static final long serialVersionUID = 1L;

    private final Parking parking;

    private TemplateSaisie groupNom;
    
    private TemplateSaisie groupNumero;
    private TemplateSaisie groupRue;
    private TemplateSaisie groupCP;
    private TemplateSaisie groupVille;

    private TemplateSaisie groupTarif;
    private TemplateSaisie groupHauteur;
    private TemplateSaisie groupPlacesMax;

    private JButton btnValider;
    private JButton btnAnnuler;

    public ModifierParking(Parking parking) {
        this.parking = parking;
        initialize();
        remplirChamps();
    }

    private void initialize() {
        setLayout(new BorderLayout(10, 10));
        setBorder(new EmptyBorder(15, 15, 15, 15));
        setPreferredSize(new Dimension(500, 600)); 

        JLabel title = new JLabel("Modification du parking");
        title.setFont(new Font("Segoe UI", Font.BOLD, 18));
        add(title, BorderLayout.NORTH);

        JPanel form = new JPanel();
        form.setLayout(new BoxLayout(form, BoxLayout.Y_AXIS));

        groupNom = new TemplateSaisie("Nom du parking", "Ex: Parking Central");
        form.add(groupNom);
        form.add(Box.createVerticalStrut(10));

        JPanel rowAdresse1 = new JPanel();
        rowAdresse1.setLayout(new BoxLayout(rowAdresse1, BoxLayout.X_AXIS));
        rowAdresse1.setAlignmentX(LEFT_ALIGNMENT);
        
        groupNumero = new TemplateSaisie("N°", "Ex: 10");
        groupNumero.setMaximumSize(new Dimension(80, 60)); 
        
        groupRue = new TemplateSaisie("Rue", "Ex: Rue de la République");
        
        rowAdresse1.add(groupNumero);
        rowAdresse1.add(Box.createHorizontalStrut(10));
        rowAdresse1.add(groupRue);
        
        form.add(rowAdresse1);
        form.add(Box.createVerticalStrut(10));

        JPanel rowAdresse2 = new JPanel();
        rowAdresse2.setLayout(new BoxLayout(rowAdresse2, BoxLayout.X_AXIS));
        rowAdresse2.setAlignmentX(LEFT_ALIGNMENT);

        groupCP = new TemplateSaisie("Code Postal", "Ex: 75000");
        groupCP.setMaximumSize(new Dimension(100, 60));
        
        groupVille = new TemplateSaisie("Ville", "Ex: Paris");

        rowAdresse2.add(groupCP);
        rowAdresse2.add(Box.createHorizontalStrut(10));
        rowAdresse2.add(groupVille);

        form.add(rowAdresse2);
        form.add(Box.createVerticalStrut(10));

        // 4. Autres infos
        groupTarif = new TemplateSaisie("Tarif (€/h)", "");
        form.add(groupTarif);
        form.add(Box.createVerticalStrut(10));

        groupHauteur = new TemplateSaisie("Hauteur max (m)", "");
        form.add(groupHauteur);
        form.add(Box.createVerticalStrut(10));

        groupPlacesMax = new TemplateSaisie("Places max", "");
        form.add(groupPlacesMax);

        add(form, BorderLayout.CENTER);

        // --- Boutons ---
        btnValider = new JButton("Valider");
        btnAnnuler = new JButton("Annuler");

        JPanel actions = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        actions.add(btnAnnuler);
        actions.add(btnValider);

        add(actions, BorderLayout.SOUTH);
    }

    private void remplirChamps() {
        if (parking != null) {
            groupNom.setText(parking.getNom());
            
            Adresse adr = parking.getAdresse();
            if (adr != null) {
                groupNumero.setText(String.valueOf(adr.getNumero())); 
                groupRue.setText(adr.getRue());
                groupCP.setText(String.valueOf(adr.getCodePostal()));
                groupVille.setText(adr.getVille());
            }

            groupTarif.setText(String.valueOf(parking.getTarif()));
            groupHauteur.setText(String.valueOf(parking.getHauteur()));
            groupPlacesMax.setText(String.valueOf(parking.getNbPlacesMax()));
        }
    }

    // --- Getters ---

    public JButton getBtnValider() { return btnValider; }
    public JButton getBtnAnnuler() { return btnAnnuler; }

    public String getNom() { return groupNom.getText().trim(); }
    public String getNumero() { return groupNumero.getText().trim(); }
    public String getRue() { return groupRue.getText().trim(); }
    public String getCodePostal() { return groupCP.getText().trim(); }
    public String getVille() { return groupVille.getText().trim(); }

    public double getTarif() {
        try { return Double.parseDouble(groupTarif.getText().trim()); } 
        catch (NumberFormatException e) { return 0.0; }
    }

    public double getHauteur() {
        try { return Double.parseDouble(groupHauteur.getText().trim()); }
        catch (NumberFormatException e) { return 0.0; }
    }

    public int getPlacesMax() {
        try { return Integer.parseInt(groupPlacesMax.getText().trim()); }
        catch (NumberFormatException e) { return 0; }
    }
}