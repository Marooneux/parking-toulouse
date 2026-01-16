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

import controleur.ControleurLoginPage;

public class LoginPage extends JPanel {
	private static final long serialVersionUID = 1L;
	private JButton btnValider;
	TemplateSaisie saisieLogin;
	TemplateSaisie saisieMdp;

	public LoginPage() {
		this.saisieLogin = new TemplateSaisie("Identifiant", "prenom.nom@exemple.fr");
		this.saisieMdp = new TemplateSaisie("Mot de passe", "", true); 
		
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

		JLabel heroTitle = new JLabel("Smart Parking");
		heroTitle.setFont(new Font("Segoe UI", Font.BOLD, 24));
		heroTitle.setForeground(Color.WHITE);
		heroPanel.add(heroTitle);

		heroPanel.add(Box.createVerticalStrut(10));

		JLabel heroSubtitle = new JLabel("Gérez vos stationnements avec fluidité.");
		heroSubtitle.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		heroSubtitle.setForeground(new Color(222, 226, 230));
		heroPanel.add(heroSubtitle);

		heroPanel.add(Box.createVerticalGlue());

		JLabel heroHint = new JLabel("Vos parkings, vos réservations, en un seul endroit.");
		heroHint.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		heroHint.setForeground(new Color(173, 181, 189));
		heroPanel.add(heroHint);

		card.add(heroPanel, BorderLayout.WEST);

		JPanel formPanel = new JPanel();
		formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));
		formPanel.setBackground(Color.WHITE);
		formPanel.setBorder(new EmptyBorder(40, 50, 40, 50));
		card.add(formPanel, BorderLayout.CENTER);

		JLabel title = new JLabel("Connexion");
		title.setFont(new Font("Segoe UI", Font.BOLD, 26));
		title.setForeground(new Color(33, 37, 41));
		title.setAlignmentX(LEFT_ALIGNMENT);
		formPanel.add(title);

		formPanel.add(Box.createVerticalStrut(6));

		JLabel subtitle = new JLabel("Accédez à votre espace de gestion de stationnement.");
		subtitle.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		subtitle.setForeground(new Color(108, 117, 125));
		subtitle.setAlignmentX(LEFT_ALIGNMENT);
		formPanel.add(subtitle);

		formPanel.add(Box.createVerticalStrut(28));


		formPanel.add(saisieLogin);
		formPanel.add(saisieMdp);

		formPanel.add(Box.createVerticalStrut(24));

		this.btnValider = new JButton("Se connecter");
		this.btnValider.setBackground(new Color(52, 58, 64));
		this.btnValider.setForeground(Color.WHITE);
		this.btnValider.setFont(new Font("Segoe UI", Font.BOLD, 14));
		this.btnValider.setFocusPainted(false);
		this.btnValider.setBorder(BorderFactory.createEmptyBorder(12, 20, 12, 20));
		this.btnValider.setAlignmentX(LEFT_ALIGNMENT);
		formPanel.add(this.btnValider);

		formPanel.add(Box.createVerticalStrut(12));

		JButton btnInscription = new JButton("Creer un compte");
		btnInscription.setBackground(Color.WHITE);
		btnInscription.setForeground(new Color(52, 58, 64));
		btnInscription.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		btnInscription.setFocusPainted(false);
		btnInscription.setBorder(BorderFactory.createCompoundBorder(
				new LineBorder(new Color(222, 226, 230), 1),
				new EmptyBorder(10, 14, 10, 14)));
		btnInscription.setAlignmentX(LEFT_ALIGNMENT);
		btnInscription.addActionListener(e -> NavigationFrame.getInstance().showPage("Inscription", InscriptionPage::new, "Creer un compte"));
		formPanel.add(btnInscription);

		formPanel.add(Box.createVerticalGlue());

		ControleurLoginPage controleur = new ControleurLoginPage(this);
		this.btnValider.addActionListener(controleur);
		saisieMdp.getField().addActionListener(controleur);
	}
	
	public javax.swing.JButton getBtnConnexion() {
	    return btnValider; // ou le nom de ta variable bouton
	}

	public String getLogin() {
		return this.saisieLogin.getText();
	}

	public String getMdp() {
		return new String(this.saisieMdp.getPassword());
	}

	public void setActifBoutonValider(Boolean b) {
		this.btnValider.setEnabled(b);
	}

	public void viderChampMdp() {
		this.saisieMdp.setText("");
	}
}
