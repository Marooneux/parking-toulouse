package vue.adminParking;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import ui.theme.DefaultTheme;
import vue.TemplateSaisie;

public class AjouterParking extends JPanel {

    private static final long serialVersionUID = 1L;

    // --- Champs du formulaire ---
    private TemplateSaisie groupNom;
    
    // Adresse éclaté
    private TemplateSaisie groupNumero;
    private TemplateSaisie groupRue;
    private TemplateSaisie groupCP;
    private TemplateSaisie groupVille;

    private TemplateSaisie groupTarif;
    private TemplateSaisie groupHauteur;
    private TemplateSaisie groupPlacesMax;

    // Horaires
    private TemplateSaisie groupOuverture;
    private TemplateSaisie groupFermeture;
    
    private JCheckBox chkMoto;

    private JButton btnValider;
    private JButton btnAnnuler;

    public AjouterParking(int idAdmin) {
        initialize();
    }

    private void initialize() {
        setLayout(new BorderLayout(10, 10));
        setBorder(new EmptyBorder(15, 15, 15, 15));
        setPreferredSize(new Dimension(500, 750)); // Taille adaptée au contenu

        // --- TITRE ---
        JLabel title = new JLabel("Ajout d'un nouveau parking");
        title.setFont(DefaultTheme.FONT_TITLE_ALT);
        add(title, BorderLayout.NORTH);

        // --- FORMULAIRE ---
        JPanel form = new JPanel();
        form.setLayout(new BoxLayout(form, BoxLayout.Y_AXIS));

        // 1. Nom
        groupNom = new TemplateSaisie("Nom du parking", "");
        form.add(groupNom);
        form.add(Box.createVerticalStrut(10));

        // 2. Adresse (Ligne 1 : N° + Rue)
        JPanel rowAdresse1 = createRowPanel();
        groupNumero = new TemplateSaisie("N°", "");
        groupNumero.setMaximumSize(new Dimension(80, 60));
        
        groupRue = new TemplateSaisie("Rue", "");
        
        rowAdresse1.add(groupNumero);
        rowAdresse1.add(Box.createHorizontalStrut(10));
        rowAdresse1.add(groupRue);
        form.add(rowAdresse1);
        form.add(Box.createVerticalStrut(10));

        // 3. Adresse (Ligne 2 : CP + Ville)
        JPanel rowAdresse2 = createRowPanel();
        groupCP = new TemplateSaisie("Code Postal", "");
        groupCP.setMaximumSize(new Dimension(100, 60));
        
        groupVille = new TemplateSaisie("Ville", "");
        
        rowAdresse2.add(groupCP);
        rowAdresse2.add(Box.createHorizontalStrut(10));
        rowAdresse2.add(groupVille);
        form.add(rowAdresse2);
        form.add(Box.createVerticalStrut(10));

        // 4. Infos Techniques
        groupTarif = new TemplateSaisie("Tarif (€/h)", "");
        form.add(groupTarif);
        form.add(Box.createVerticalStrut(10));

        groupHauteur = new TemplateSaisie("Hauteur max (m)", "");
        form.add(groupHauteur);
        form.add(Box.createVerticalStrut(10));

        // Places (Max & Occupées sur la même ligne pour gagner de la place)
        JPanel rowPlaces = createRowPanel();
        groupPlacesMax = new TemplateSaisie("Places max", "");
        
        rowPlaces.add(groupPlacesMax);
        rowPlaces.add(Box.createHorizontalStrut(10));
        form.add(rowPlaces);
        form.add(Box.createVerticalStrut(10));

        // 5. Horaires (Ligne)
        JPanel rowHoraires = createRowPanel();
        groupOuverture = new TemplateSaisie("Ouverture (HH:mm)", "");
        groupFermeture = new TemplateSaisie("Fermeture (HH:mm)", "");
        
        rowHoraires.add(groupOuverture);
        rowHoraires.add(Box.createHorizontalStrut(10));
        rowHoraires.add(groupFermeture);
        form.add(rowHoraires);
        form.add(Box.createVerticalStrut(15));

        // 6. Checkbox Moto
        chkMoto = new JCheckBox("Dispose de places Moto");
        chkMoto.setFont(DefaultTheme.FONT_LABEL_SMALL);
        chkMoto.setForeground(new Color(73, 80, 87));
        chkMoto.setOpaque(false);
        chkMoto.setAlignmentX(Component.LEFT_ALIGNMENT);
        form.add(chkMoto);

        add(form, BorderLayout.CENTER);

        // --- BOUTONS ---
        btnValider = new JButton("Valider");
        btnAnnuler = new JButton("Annuler");

        JPanel actions = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        actions.add(btnAnnuler);
        actions.add(btnValider);

        add(actions, BorderLayout.SOUTH);
    }

    // Méthode utilitaire pour créer une ligne horizontale
    private JPanel createRowPanel() {
        JPanel p = new JPanel();
        p.setLayout(new BoxLayout(p, BoxLayout.X_AXIS));
        p.setAlignmentX(Component.LEFT_ALIGNMENT);
        p.setOpaque(false);
        return p;
    }

    // --- GETTERS ---

    public JButton getBtnValider() { return btnValider; }
    public JButton getBtnAnnuler() { return btnAnnuler; }

    public String getNom() { return groupNom.getText().trim(); }
    
    // Adresse éclaté
    public String getNumero() { return groupNumero.getText().trim(); }
    public String getRue() { return groupRue.getText().trim(); }
    public String getCodePostal() { return groupCP.getText().trim(); }
    public String getVille() { return groupVille.getText().trim(); }

    public String getTarif() { return groupTarif.getText().trim(); }
    public String getHauteur() { return groupHauteur.getText().trim(); }
    public String getPlacesMax() { return groupPlacesMax.getText().trim(); }
    
    // On renvoie des Strings pour les heures, le contrôleur fera le parsing (plus sûr pour la vue)
    public String getHeureOuverture() { return groupOuverture.getText().trim(); }
    public String getHeureFermeture() { return groupFermeture.getText().trim(); }
    
    public boolean isContientPlacesMoto() { return chkMoto.isSelected(); }
}