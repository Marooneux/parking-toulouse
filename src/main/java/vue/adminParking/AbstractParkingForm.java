package vue.adminParking;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

import ui.theme.DefaultTheme;
import vue.TemplateSaisie;

public abstract class AbstractParkingForm extends JPanel {

    protected TemplateSaisie groupNom;
    protected TemplateSaisie groupNumero;
    protected TemplateSaisie groupRue;
    protected TemplateSaisie groupCP;
    protected TemplateSaisie groupVille;
    protected TemplateSaisie groupTarif;
    protected TemplateSaisie groupHauteur;
    protected TemplateSaisie groupPlacesMax;
    protected TemplateSaisie groupOuverture;
    protected TemplateSaisie groupFermeture;

    protected JCheckBox chkMoto;

    protected JButton btnValider;
    protected JButton btnAnnuler;

    public AbstractParkingForm(String titleText) {
        setLayout(new BorderLayout(10, 10));
        setBorder(new EmptyBorder(15, 15, 15, 15));
        setPreferredSize(new Dimension(500, 750));

        // TITLE
        JLabel title = new JLabel(titleText);
        title.setFont(DefaultTheme.FONT_TITLE_ALT);
        add(title, BorderLayout.NORTH);

        // FORM
        JPanel form = new JPanel();
        form.setLayout(new BoxLayout(form, BoxLayout.Y_AXIS));

        groupNom = new TemplateSaisie("Nom du parking", "");
        form.add(groupNom);
        form.add(Box.createVerticalStrut(10));

        // Adresse row 1
        JPanel row1 = createRow();
        groupNumero = new TemplateSaisie("N°", "");
        groupNumero.setMaximumSize(new Dimension(80, 60));
        groupRue = new TemplateSaisie("Rue", "");
        row1.add(groupNumero);
        row1.add(Box.createHorizontalStrut(10));
        row1.add(groupRue);
        form.add(row1);
        form.add(Box.createVerticalStrut(10));

        // Adresse row 2
        JPanel row2 = createRow();
        groupCP = new TemplateSaisie("Code Postal", "");
        groupCP.setMaximumSize(new Dimension(100, 60));
        groupVille = new TemplateSaisie("Ville", "");
        row2.add(groupCP);
        row2.add(Box.createHorizontalStrut(10));
        row2.add(groupVille);
        form.add(row2);
        form.add(Box.createVerticalStrut(10));

        // Infos techniques
        groupTarif = new TemplateSaisie("Tarif (€/h)", "");
        form.add(groupTarif);
        form.add(Box.createVerticalStrut(10));

        groupHauteur = new TemplateSaisie("Hauteur max (m)", "");
        form.add(groupHauteur);
        form.add(Box.createVerticalStrut(10));

        groupPlacesMax = new TemplateSaisie("Places max", "");
        form.add(groupPlacesMax);
        form.add(Box.createVerticalStrut(10));

        // Horaires
        JPanel rowHoraires = createRow();
        groupOuverture = new TemplateSaisie("Ouverture (HH:mm)", "");
        groupFermeture = new TemplateSaisie("Fermeture (HH:mm)", "");
        rowHoraires.add(groupOuverture);
        rowHoraires.add(Box.createHorizontalStrut(10));
        rowHoraires.add(groupFermeture);
        form.add(rowHoraires);
        form.add(Box.createVerticalStrut(15));

        // Checkbox
        chkMoto = new JCheckBox("Dispose de places Moto");
        chkMoto.setFont(DefaultTheme.FONT_LABEL_SMALL);
        chkMoto.setForeground(new Color(73, 80, 87));
        chkMoto.setOpaque(false);
        form.add(chkMoto);

        add(form, BorderLayout.CENTER);

        // Buttons
        btnValider = new JButton("Valider");
        btnAnnuler = new JButton("Annuler");
        JPanel actions = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        actions.add(btnAnnuler);
        actions.add(btnValider);
        add(actions, BorderLayout.SOUTH);
    }

    private JPanel createRow() {
        JPanel p = new JPanel();
        p.setLayout(new BoxLayout(p, BoxLayout.X_AXIS));
        p.setOpaque(false);
        return p;
    }
    
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


    public String getHeureOuverture() { return groupOuverture.getText().trim(); }
    public String getHeureFermeture() { return groupFermeture.getText().trim(); }

    public boolean isContientPlacesMoto() { return chkMoto.isSelected(); }

}
