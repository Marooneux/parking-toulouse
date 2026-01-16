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
	private PlaceholderTextField loginField;
	private JPasswordField passwdField;
	private JButton btnValider;

	public LoginPage() {
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

		JLabel loginLabel = new JLabel("Identifiant");
		loginLabel.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		loginLabel.setForeground(new Color(73, 80, 87));
		loginLabel.setAlignmentX(LEFT_ALIGNMENT);
		formPanel.add(loginLabel);

		formPanel.add(Box.createVerticalStrut(6));

		this.loginField = new PlaceholderTextField("prenom.nom@exemple.fr", 20);
		this.loginField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		this.loginField.setBorder(BorderFactory.createCompoundBorder(
				new LineBorder(new Color(206, 212, 218), 1, true),
				new EmptyBorder(10, 12, 10, 12)));
		this.loginField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 46));
		this.loginField.setBackground(new Color(251, 252, 253));
		this.loginField.setAlignmentX(LEFT_ALIGNMENT);
		formPanel.add(this.loginField);

		formPanel.add(Box.createVerticalStrut(18));

		JLabel passwdLabel = new JLabel("Mot de passe");
		passwdLabel.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		passwdLabel.setForeground(new Color(73, 80, 87));
		passwdLabel.setAlignmentX(LEFT_ALIGNMENT);
		formPanel.add(passwdLabel);

		formPanel.add(Box.createVerticalStrut(6));

		this.passwdField = new JPasswordField();
		this.passwdField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		this.passwdField.setBorder(BorderFactory.createCompoundBorder(
				new LineBorder(new Color(206, 212, 218), 1, true),
				new EmptyBorder(10, 12, 10, 12)));
		this.passwdField.setBackground(new Color(251, 252, 253));
		this.passwdField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 46));
		this.passwdField.setAlignmentX(LEFT_ALIGNMENT);
		formPanel.add(this.passwdField);

		formPanel.add(Box.createVerticalStrut(24));

		this.btnValider = new JButton("Se connecter");
		this.btnValider.setBackground(new Color(52, 58, 64));
		this.btnValider.setForeground(Color.WHITE);
		this.btnValider.setFont(new Font("Segoe UI", Font.BOLD, 14));
		this.btnValider.setFocusPainted(false);
		this.btnValider.setBorder(BorderFactory.createEmptyBorder(12, 20, 12, 20));
		this.btnValider.setAlignmentX(LEFT_ALIGNMENT);
		formPanel.add(this.btnValider);

		formPanel.add(Box.createVerticalGlue());

		ControleurLoginPage controleur = new ControleurLoginPage(this);
		this.btnValider.addActionListener(controleur);
		this.passwdField.addActionListener(controleur);
	}

	public String getLogin() {
		return this.loginField.getText();
	}

	public String getMdp() {
		return new String(this.passwdField.getPassword());
	}

	public void setActifBoutonValider(Boolean b) {
		this.btnValider.setEnabled(b);
	}

	public void viderChampMdp() {
		this.passwdField.setText("");
	}
}
