package VUE;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class PaiementVoirie extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField textFieldNom;
    private JTextField textFieldNumCarte;
    private JTextField textFieldExpiration;
    private JTextField textFieldCVC;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                Paiement frame = new Paiement();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public PaiementVoirie() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(520, 430);
        setLocationRelativeTo(null);

        contentPane = new JPanel();
        contentPane.setLayout(new BorderLayout(20, 20));
        contentPane.setBackground(Color.WHITE);
        contentPane.setBorder(new EmptyBorder(20, 20, 20, 20));
        setContentPane(contentPane);

        // HEADER
        JPanel header = new JPanel(new FlowLayout(FlowLayout.LEFT));
        header.setBackground(Color.WHITE);

        JLabel icon = new JLabel("💳");
        icon.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 32));
        header.add(icon);

        JPanel titreZone = new JPanel();
        titreZone.setBackground(Color.WHITE);
        titreZone.setLayout(new BoxLayout(titreZone, BoxLayout.Y_AXIS));

        JLabel lblTitre = new JLabel("Paiement");
        lblTitre.setFont(new Font("Segoe UI", Font.BOLD, 22));
        lblTitre.setForeground(new Color(40, 40, 40));
        titreZone.add(lblTitre);

        JLabel lblSousTitre = new JLabel("Entrez les informations de votre carte");
        lblSousTitre.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lblSousTitre.setForeground(new Color(100, 100, 100));
        titreZone.add(lblSousTitre);

        header.add(titreZone);
        contentPane.add(header, BorderLayout.NORTH);

        // CARD CENTRAL
        JPanel card = new JPanel();
        card.setBackground(Color.WHITE);
        card.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(220, 220, 220)),
                new EmptyBorder(20, 20, 20, 20)
        ));
        card.setLayout(new GridLayout(4, 1, 15, 15));
        contentPane.add(card, BorderLayout.CENTER);

        // --- CHAMP NOM ---
        JPanel blocNom = criarBlocChamp("Nom", textFieldNom = new JTextField("Nom Prénom"));
        card.add(blocNom);

        // --- NUMÉRO DE CARTE ---
        JPanel blocCarte = criarBlocChamp("Numéro de carte", textFieldNumCarte = new JTextField("1234 5678 9012 3456"));
        card.add(blocCarte);

        // --- CHAINE EXPIRATION + CVC ---
        JPanel row = new JPanel(new GridLayout(1, 2, 20, 0));
        row.setOpaque(false);

        JPanel blocExp = criarBlocChamp("Date d'expiration", textFieldExpiration = new JTextField("MM/YY"));
        JPanel blocCVC = criarBlocChamp("CVC", textFieldCVC = new JTextField("123"));

        row.add(blocExp);
        row.add(blocCVC);
        card.add(row);

        // --- BOUTON ---
        JPanel panelBtn = new JPanel();
        panelBtn.setBackground(Color.WHITE);

        JButton btnPayer = new JButton("Payer - 15€");
        btnPayer.setBackground(new Color(0, 128, 255));
        btnPayer.setForeground(Color.WHITE);
        btnPayer.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        btnPayer.setFocusPainted(false);
        btnPayer.setPreferredSize(new Dimension(160, 40));
        panelBtn.add(btnPayer);
        btnPayer.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                	ConfirmationPaiementVoirie framePaiement = new ConfirmationPaiementVoirie(15);
                    framePaiement.setVisible(true);
                    dispose();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        card.add(panelBtn);
    }

    private JPanel criarBlocChamp(String labelText, JTextField textField) {
        JPanel bloc = new JPanel();
        bloc.setOpaque(false);
        bloc.setLayout(new BoxLayout(bloc, BoxLayout.Y_AXIS));

        JLabel lbl = new JLabel(labelText);
        lbl.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lbl.setAlignmentX(Component.LEFT_ALIGNMENT);

        JPanel champPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        champPanel.setOpaque(false);

        textField.setPreferredSize(new Dimension(250, 28));
        textField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        textField.setForeground(new Color(120, 120, 120));

        champPanel.add(textField);

        bloc.add(lbl);
        bloc.add(champPanel);

        return bloc;
    }
}
