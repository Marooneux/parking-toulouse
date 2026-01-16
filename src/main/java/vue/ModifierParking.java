package vue;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import modele.Parking;

public class ModifierParking extends JPanel {

    private static final long serialVersionUID = 1L;

    private final Parking parking;

    private JTextField txtNom;
    private JTextField txtAdresse;
    private JTextField txtTarif;
    private JTextField txtHauteur;
    private JTextField txtPlacesMax;

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
        setPreferredSize(new Dimension(450, 350));

        JLabel title = new JLabel("Modification du parking");
        title.setFont(new Font("Segoe UI", Font.BOLD, 18));
        add(title, BorderLayout.NORTH);

        JPanel form = new JPanel(new GridLayout(0, 2, 10, 10));

        txtNom = new JTextField();
        txtAdresse = new JTextField();
        txtTarif = new JTextField();
        txtHauteur = new JTextField();
        txtPlacesMax = new JTextField();

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

        add(form, BorderLayout.CENTER);

        btnValider = new JButton("Valider");
        btnAnnuler = new JButton("Annuler");

        JPanel actions = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        actions.add(btnAnnuler);
        actions.add(btnValider);

        add(actions, BorderLayout.SOUTH);
    }

    private void remplirChamps() {
        txtNom.setText(parking.getNom());
        txtAdresse.setText(parking.getAdresse() != null ? parking.getAdresse().getRue() : "");
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
}
