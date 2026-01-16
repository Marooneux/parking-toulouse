package vue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import controleur.ControleurInscriptionPage;

public class InscriptionPage extends JPanel {

    private static final long serialVersionUID = 1L;

    private final PlaceholderTextField nomField;
    private final PlaceholderTextField prenomField;
    private final PlaceholderTextField emailField;
    private final JPasswordField mdpField;
    private final JPasswordField confirmField;

    private final JButton btnCreer;
    private final JButton btnRetourConnexion;

    public InscriptionPage() {
        this.setLayout(new BorderLayout());
        this.setBackground(new Color(248, 249, 250));

        JPanel wrapper = new JPanel(new BorderLayout());
        wrapper.setOpaque(false);
        wrapper.setBorder(new EmptyBorder(30, 40, 30, 40));
        this.add(wrapper, BorderLayout.CENTER);

        JPanel card = new JPanel(new BorderLayout());
        card.setBackground(Color.WHITE);
        card.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(new Color(226, 232, 240), 1),
                new EmptyBorder(0, 0, 0, 0)));
        wrapper.add(card, BorderLayout.CENTER);

        JPanel heroPanel = new JPanel();
        heroPanel.setPreferredSize(new Dimension(320, 0));
        heroPanel.setBackground(new Color(33, 37, 41));
        heroPanel.setLayout(new BoxLayout(heroPanel, BoxLayout.Y_AXIS));
        heroPanel.setBorder(new EmptyBorder(40, 40, 40, 40));

        JLabel heroTitle = new JLabel("Rejoignez Smart Parking");
        heroTitle.setFont(new Font("Segoe UI", Font.BOLD, 22));
        heroTitle.setForeground(Color.WHITE);
        heroPanel.add(heroTitle);

        heroPanel.add(Box.createVerticalStrut(10));

        JLabel heroSubtitle = new JLabel("Creez votre compte client pour reserver plus vite.");
        heroSubtitle.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        heroSubtitle.setForeground(new Color(222, 226, 230));
        heroPanel.add(heroSubtitle);

        heroPanel.add(Box.createVerticalGlue());

        JLabel heroHint = new JLabel("Gestion simple des parkings et tickets.");
        heroHint.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        heroHint.setForeground(new Color(173, 181, 189));
        heroPanel.add(heroHint);

        card.add(heroPanel, BorderLayout.WEST);

        JPanel formPanel = new JPanel();
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));
        formPanel.setBackground(Color.WHITE);
        formPanel.setBorder(new EmptyBorder(40, 50, 40, 50));
        card.add(formPanel, BorderLayout.CENTER);

        JLabel title = new JLabel("Creer un compte");
        title.setFont(new Font("Segoe UI", Font.BOLD, 26));
        title.setForeground(new Color(33, 37, 41));
        title.setAlignmentX(LEFT_ALIGNMENT);
        formPanel.add(title);

        formPanel.add(Box.createVerticalStrut(6));

        JLabel subtitle = new JLabel("Renseignez vos informations pour commencer.");
        subtitle.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        subtitle.setForeground(new Color(108, 117, 125));
        subtitle.setAlignmentX(LEFT_ALIGNMENT);
        formPanel.add(subtitle);

        formPanel.add(Box.createVerticalStrut(24));

        this.nomField = buildField(formPanel, "Nom", "Dupond");
        this.prenomField = buildField(formPanel, "Prenom", "Jean");
        this.emailField = buildField(formPanel, "Email", "prenom.nom@exemple.fr");

        JLabel mdpLabel = new JLabel("Mot de passe (min. 8 caracteres)");
        mdpLabel.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        mdpLabel.setForeground(new Color(73, 80, 87));
        mdpLabel.setAlignmentX(LEFT_ALIGNMENT);
        formPanel.add(mdpLabel);

        formPanel.add(Box.createVerticalStrut(6));

        this.mdpField = new JPasswordField();
        this.mdpField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        this.mdpField.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(new Color(206, 212, 218), 1, true),
                new EmptyBorder(10, 12, 10, 12)));
        this.mdpField.setBackground(new Color(251, 252, 253));
        this.mdpField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 46));
        this.mdpField.setAlignmentX(LEFT_ALIGNMENT);
        formPanel.add(this.mdpField);

        formPanel.add(Box.createVerticalStrut(18));

        JLabel confirmLabel = new JLabel("Confirmer le mot de passe");
        confirmLabel.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        confirmLabel.setForeground(new Color(73, 80, 87));
        confirmLabel.setAlignmentX(LEFT_ALIGNMENT);
        formPanel.add(confirmLabel);

        formPanel.add(Box.createVerticalStrut(6));

        this.confirmField = new JPasswordField();
        this.confirmField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        this.confirmField.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(new Color(206, 212, 218), 1, true),
                new EmptyBorder(10, 12, 10, 12)));
        this.confirmField.setBackground(new Color(251, 252, 253));
        this.confirmField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 46));
        this.confirmField.setAlignmentX(LEFT_ALIGNMENT);
        formPanel.add(this.confirmField);

        formPanel.add(Box.createVerticalStrut(26));

        this.btnCreer = new JButton("Creer mon compte");
        this.btnCreer.setBackground(new Color(52, 58, 64));
        this.btnCreer.setForeground(Color.WHITE);
        this.btnCreer.setFont(new Font("Segoe UI", Font.BOLD, 14));
        this.btnCreer.setFocusPainted(false);
        this.btnCreer.setBorder(BorderFactory.createEmptyBorder(12, 20, 12, 20));
        this.btnCreer.setAlignmentX(LEFT_ALIGNMENT);
        formPanel.add(this.btnCreer);

        formPanel.add(Box.createVerticalStrut(12));

        this.btnRetourConnexion = new JButton("J'ai deja un compte");
        this.btnRetourConnexion.setBackground(Color.WHITE);
        this.btnRetourConnexion.setForeground(new Color(52, 58, 64));
        this.btnRetourConnexion.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        this.btnRetourConnexion.setFocusPainted(false);
        this.btnRetourConnexion.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(new Color(222, 226, 230), 1),
                new EmptyBorder(10, 14, 10, 14)));
        this.btnRetourConnexion.setAlignmentX(LEFT_ALIGNMENT);
        formPanel.add(this.btnRetourConnexion);

        formPanel.add(Box.createVerticalGlue());

        ControleurInscriptionPage controleur = new ControleurInscriptionPage(this);
        this.btnCreer.addActionListener(controleur);
        this.confirmField.addActionListener(controleur);

        this.btnRetourConnexion.addActionListener(e -> NavigationFrame.getInstance().showPage("Connexion", LoginPage::new, "Connexion"));
    }

    private PlaceholderTextField buildField(JPanel container, String label, String placeholder) {
        JLabel jlabel = new JLabel(label);
        jlabel.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        jlabel.setForeground(new Color(73, 80, 87));
        jlabel.setAlignmentX(LEFT_ALIGNMENT);
        container.add(jlabel);

        container.add(Box.createVerticalStrut(6));

        PlaceholderTextField field = new PlaceholderTextField(placeholder, 20);
        field.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        field.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(new Color(206, 212, 218), 1, true),
                new EmptyBorder(10, 12, 10, 12)));
        field.setBackground(new Color(251, 252, 253));
        field.setMaximumSize(new Dimension(Integer.MAX_VALUE, 46));
        field.setAlignmentX(LEFT_ALIGNMENT);
        container.add(field);

        container.add(Box.createVerticalStrut(18));
        return field;
    }

    public String getNom() {
        return this.nomField.getText().trim();
    }

    public String getPrenom() {
        return this.prenomField.getText().trim();
    }

    public String getEmail() {
        return this.emailField.getText().trim();
    }

    public String getMotDePasse() {
        return new String(this.mdpField.getPassword());
    }

    public String getConfirmationMotDePasse() {
        return new String(this.confirmField.getPassword());
    }
}
