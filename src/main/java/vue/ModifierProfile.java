package vue;

import java.awt.Dimension;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPasswordField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import modele.Utilisateur;

public class ModifierProfile extends JPanel {

    private JTextField txtNom;
    private JTextField txtPrenom;
    private JTextField txtEmail;
    private JPasswordField txtAncienMdp;
    private JPasswordField txtNouveauMdp;
    private JButton btnEnregistrer;
    private JButton btnAnnuler;

    public ModifierProfile() {
        setBorder(new EmptyBorder(10, 10, 10, 10));
        setLayout(null);
        setPreferredSize(new Dimension(500, 350));

        // --- NOM ---
        JLabel lblNom = new JLabel("Nom :");
        lblNom.setBounds(30, 40, 160, 14); // Label élargi
        add(lblNom);

        txtNom = new JTextField();
        txtNom.setBounds(200, 37, 200, 20); // Champ décalé à droite
        add(txtNom);
        txtNom.setColumns(10);

        // --- PRENOM ---
        JLabel lblPrenom = new JLabel("Prénom :");
        lblPrenom.setBounds(30, 80, 160, 14);
        add(lblPrenom);

        txtPrenom = new JTextField();
        txtPrenom.setBounds(200, 77, 200, 20);
        add(txtPrenom);
        txtPrenom.setColumns(10);

        // --- EMAIL ---
        JLabel lblEmail = new JLabel("Email :");
        lblEmail.setBounds(30, 120, 160, 14);
        add(lblEmail);

        txtEmail = new JTextField();
        txtEmail.setBounds(200, 117, 200, 20);
        add(txtEmail);
        txtEmail.setColumns(10);
        
        // --- ANCIEN MDP ---
        JLabel lblAncienMdp = new JLabel("Ancien mot de passe :");
        lblAncienMdp.setBounds(30, 160, 160, 14);
        add(lblAncienMdp);
        
        txtAncienMdp = new JPasswordField();
        txtAncienMdp.setBounds(200, 157, 200, 20);
        add(txtAncienMdp);

        // --- NOUVEAU MDP ---
        JLabel lblNouveauMdp = new JLabel("Nouveau mot de passe :");
        lblNouveauMdp.setBounds(30, 200, 160, 14);
        add(lblNouveauMdp);

        txtNouveauMdp = new JPasswordField();
        txtNouveauMdp.setBounds(200, 197, 200, 20);
        add(txtNouveauMdp);

        // --- BOUTONS ---
        btnEnregistrer = new JButton("Enregistrer");
        btnEnregistrer.setBounds(200, 260, 110, 23);
        add(btnEnregistrer);
        
        btnAnnuler = new JButton("Annuler");
        btnAnnuler.setBounds(320, 260, 80, 23);
        add(btnAnnuler);
    }

    // --- Méthodes pour le Contrôleur ---

    // Pré-remplit les champs (sauf les mots de passe pour sécurité)
    public void afficherUtilisateur(Utilisateur user) {
        txtNom.setText(user.getNom());
        txtPrenom.setText(user.getPrenom());
        txtEmail.setText(user.getEmail());
        txtAncienMdp.setText("");
        txtNouveauMdp.setText("");
    }

    // Getters
    public String getNomInput() { return txtNom.getText(); }
    public String getPrenomInput() { return txtPrenom.getText(); }
    public String getEmailInput() { return txtEmail.getText(); }
    public String getAncienMdpInput() { return new String(txtAncienMdp.getPassword()); }
    public String getNouveauMdpInput() { return new String(txtNouveauMdp.getPassword()); }

    // Listeners
    public void addEnregistrerListener(ActionListener action) {
        btnEnregistrer.addActionListener(action);
    }
    
    public void addAnnulerListener(ActionListener action) {
        btnAnnuler.addActionListener(action);
    }

    public void afficherMessage(String msg) {
        JOptionPane.showMessageDialog(this, msg);
    }
}