package vue.adminParking;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Component;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox; // Import nécessaire
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import modele.Adresse;
import modele.Parking;
import ui.theme.DefaultTheme;
import vue.TemplateSaisie; // Assure-toi que l'import est bon

public class ModifierParking extends JPanel {

    private static final long serialVersionUID = 1L;
    private final Parking parking;

    // --- Champs existants ---
    private TemplateSaisie groupNom;
    private TemplateSaisie groupNumero;
    private TemplateSaisie groupRue;
    private TemplateSaisie groupCP;
    private TemplateSaisie groupVille;
    private TemplateSaisie groupTarif;
    private TemplateSaisie groupHauteur;
    private TemplateSaisie groupPlacesMax;

    // --- NOUVEAUX CHAMPS ---
    private TemplateSaisie groupOuverture;
    private TemplateSaisie groupFermeture;
    private JCheckBox chkMoto;

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
        setPreferredSize(new Dimension(500, 700)); // Hauteur augmentée

        JLabel title = new JLabel("Modification du parking");
        title.setFont(DefaultTheme.FONT_TITLE_ALT);
        add(title, BorderLayout.NORTH);

        JPanel form = new JPanel();
        form.setLayout(new BoxLayout(form, BoxLayout.Y_AXIS));

        // ... [Code précédent pour Nom et Adresse inchangé] ...
        
        // 1. Nom
        groupNom = new TemplateSaisie("Nom du parking", "Ex: Parking Central");
        form.add(groupNom);
        form.add(Box.createVerticalStrut(10));

        // 2. Adresse (Ligne 1)
        JPanel rowAdresse1 = new JPanel();
        rowAdresse1.setLayout(new BoxLayout(rowAdresse1, BoxLayout.X_AXIS));
        rowAdresse1.setAlignmentX(Component.LEFT_ALIGNMENT);
        groupNumero = new TemplateSaisie("N°", "10");
        groupNumero.setMaximumSize(new Dimension(80, 60));
        groupRue = new TemplateSaisie("Rue", "Rue de la République");
        rowAdresse1.add(groupNumero);
        rowAdresse1.add(Box.createHorizontalStrut(10));
        rowAdresse1.add(groupRue);
        form.add(rowAdresse1);
        form.add(Box.createVerticalStrut(10));

        // 3. Adresse (Ligne 2)
        JPanel rowAdresse2 = new JPanel();
        rowAdresse2.setLayout(new BoxLayout(rowAdresse2, BoxLayout.X_AXIS));
        rowAdresse2.setAlignmentX(Component.LEFT_ALIGNMENT);
        groupCP = new TemplateSaisie("Code Postal", "75000");
        groupCP.setMaximumSize(new Dimension(100, 60));
        groupVille = new TemplateSaisie("Ville", "Paris");
        rowAdresse2.add(groupCP);
        rowAdresse2.add(Box.createHorizontalStrut(10));
        rowAdresse2.add(groupVille);
        form.add(rowAdresse2);
        form.add(Box.createVerticalStrut(10));

        // 4. Infos techniques
        groupTarif = new TemplateSaisie("Tarif (€/h)", "0.0");
        form.add(groupTarif);
        form.add(Box.createVerticalStrut(10));

        groupHauteur = new TemplateSaisie("Hauteur max (m)", "1.90");
        form.add(groupHauteur);
        form.add(Box.createVerticalStrut(10));

        groupPlacesMax = new TemplateSaisie("Places max", "100");
        form.add(groupPlacesMax);
        form.add(Box.createVerticalStrut(10));

        // --- 5. NOUVELLE SECTION : HORAIRES ---
        JPanel rowHoraires = new JPanel();
        rowHoraires.setLayout(new BoxLayout(rowHoraires, BoxLayout.X_AXIS));
        rowHoraires.setAlignmentX(Component.LEFT_ALIGNMENT);

        groupOuverture = new TemplateSaisie("Ouverture", "08:00");
        groupFermeture = new TemplateSaisie("Fermeture", "22:00");

        rowHoraires.add(groupOuverture);
        rowHoraires.add(Box.createHorizontalStrut(10));
        rowHoraires.add(groupFermeture);
        
        form.add(rowHoraires);
        form.add(Box.createVerticalStrut(15));

        // --- 6. NOUVELLE SECTION : CHECKBOX MOTO ---
        chkMoto = new JCheckBox("Dispose de places Moto");
        chkMoto.setFont(DefaultTheme.FONT_BODY); // Même font que tes labels
        chkMoto.setForeground(new Color(73, 80, 87)); // Même couleur gris foncé
        chkMoto.setOpaque(false);
        chkMoto.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        form.add(chkMoto);

        add(form, BorderLayout.CENTER);

        // Boutons
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
            
            // Remplissage des nouveaux champs
            // Attention : .toString() dépend du type dans ton modèle (LocalTime ou String)
            if(parking.getHoraireOuverture() != null)
                groupOuverture.setText(parking.getHoraireOuverture().toString());
            
            if(parking.getHoraireFermeture() != null)
                groupFermeture.setText(parking.getHoraireFermeture().toString());
            
            chkMoto.setSelected(parking.isContientPlacesMoto());
        }
    }

    // --- Getters mis à jour ---
    public JButton getBtnValider() { return btnValider; }
    public JButton getBtnAnnuler() { return btnAnnuler; }
    public String getNom() { return groupNom.getText().trim(); }
    public String getNumero() { return groupNumero.getText().trim(); }
    public String getRue() { return groupRue.getText().trim(); }
    public String getCodePostal() { return groupCP.getText().trim(); }
    public String getVille() { return groupVille.getText().trim(); }
    
    public double getTarif() {
        try { return Double.parseDouble(groupTarif.getText().trim()); } 
        catch (Exception e) { return 0.0; }
    }
    public double getHauteur() {
        try { return Double.parseDouble(groupHauteur.getText().trim()); } 
        catch (Exception e) { return 0.0; }
    }
    public int getPlacesMax() {
        try { return Integer.parseInt(groupPlacesMax.getText().trim()); } 
        catch (Exception e) { return 0; }
    }

    // Nouveaux Getters
    public String getHeureOuverture() { return groupOuverture.getText().trim(); }
    public String getHeureFermeture() { return groupFermeture.getText().trim(); }
    public boolean isContientPlacesMoto() { return chkMoto.isSelected(); }
}