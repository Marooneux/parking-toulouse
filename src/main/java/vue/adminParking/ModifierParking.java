package vue.adminParking;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.time.LocalTime;

import modele.Parking;

public class ModifierParking extends JFrame {

    private static final long serialVersionUID = 1L;

    private JTextField txtNom;
    private JTextField txtAdresse;
    private JTextField txtTarif;
    private JTextField txtHauteur;
    private JTextField txtPlacesMax;
    private JTextField txtHeureOuverture;
    private JTextField txtHeureFermeture;
    private JCheckBox chkMoto;


    private JButton btnValider;
    private JButton btnAnnuler;

    private Parking parking;

    public ModifierParking(Parking parking) {
        this.parking = parking;
        initialize();
        remplirChamps();
    }

    private void initialize() {
        setTitle("Modifier un parking");
        setSize(450, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel content = new JPanel(new BorderLayout(10, 10));
        content.setBorder(new EmptyBorder(15, 15, 15, 15));
        setContentPane(content);

        JLabel title = new JLabel("Modification du parking");
        title.setFont(new Font("Segoe UI", Font.BOLD, 18));
        content.add(title, BorderLayout.NORTH);

        JPanel form = new JPanel(new GridLayout(0, 2, 10, 10));

        txtNom = new JTextField();
        txtAdresse = new JTextField();
        txtTarif = new JTextField();
        txtHauteur = new JTextField();
        txtPlacesMax = new JTextField();
        
        txtHeureOuverture = new JTextField(parking.getHeureOuverture().toString());
        txtHeureFermeture = new JTextField(parking.getHeureFermeture().toString());

        chkMoto = new JCheckBox("Places de motos ?");
        chkMoto.setSelected(false);
        
        form.add(new JLabel("Nom"));
        form.add(txtNom);

        form.add(new JLabel("Adresse"));
        form.add(txtAdresse);

        form.add(new JLabel("Tarif (€/h)"));
        form.add(txtTarif);

        form.add(new JLabel("Hauteur max (m)"));
        form.add(txtHauteur);

        form.add(new JLabel("Places max"));
        form.add(txtPlacesMax);
        
        form.add(new JLabel("Horaire d'ouverture (hh:mm:ss)"));
        form.add(txtHeureOuverture);
        
        form.add(new JLabel("Horaire de fermeture (hh:mm:ss)"));
        form.add(txtHeureFermeture);

        form.add(chkMoto);

        content.add(form, BorderLayout.CENTER);

        btnValider = new JButton("Valider");
        btnAnnuler = new JButton("Annuler");

        JPanel actions = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        actions.add(btnAnnuler);
        actions.add(btnValider);

        content.add(actions, BorderLayout.SOUTH);
    }

    private void remplirChamps() {
        txtNom.setText(parking.getNom());
        txtAdresse.setText(parking.getAdresse());
        txtTarif.setText(String.valueOf(parking.getTarif()));
        txtHauteur.setText(String.valueOf(parking.getHauteur()));
        txtPlacesMax.setText(String.valueOf(parking.getNbPlacesMax()));
    }

    public JButton getBtnValider() {
        return btnValider;
    }

    public JButton getBtnAnnuler() {
        return btnAnnuler;
    }

    public String getNom() {
        return txtNom.getText().trim();
    }

    public String getAdresse() {
        return txtAdresse.getText().trim();
    }

    public double getTarif() {
        return Double.parseDouble(txtTarif.getText().trim());
    }

    public double getHauteur() {
        return Double.parseDouble(txtHauteur.getText().trim());
    }

    public int getPlacesMax() {
        return Integer.parseInt(txtPlacesMax.getText().trim());
    }
    
    public Parking getParking() {
        return parking;
    }
    
    public LocalTime getHeureOuverture() {
        return LocalTime.parse(txtHeureOuverture.getText());
    }

    public LocalTime getHeureFermeture() {
        return LocalTime.parse(txtHeureFermeture.getText());
    }
    
    public boolean isContientPlacesMoto() {
        return chkMoto.isSelected();
    }


}
