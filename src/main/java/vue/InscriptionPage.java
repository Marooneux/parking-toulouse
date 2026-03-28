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
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import controleur.ControleurInscriptionPage;
import ui.theme.DefaultTheme;

public class InscriptionPage extends JPanel {

	private static final long serialVersionUID = -8961434423788071491L;
	private final TemplateSaisie nomField;
	private final TemplateSaisie prenomField;
	private final TemplateSaisie emailField;
	private final TemplateSaisie mdpField;
	private final TemplateSaisie confirmField;

	private final JButton btnCreer;
	private final JButton btnRetourConnexion;

	public InscriptionPage() {
		this.setLayout(new BorderLayout());
		this.setBackground(DefaultTheme.TEXT_COLOR);

		JPanel wrapper = new JPanel(new BorderLayout());
		wrapper.setOpaque(false);
		wrapper.setBorder(new EmptyBorder(30, 40, 30, 40));
		this.add(wrapper, BorderLayout.CENTER);

		JPanel card = new JPanel(new BorderLayout());
		card.setBackground(DefaultTheme.BACKGROUND_CARD);
		card.setBorder(BorderFactory.createCompoundBorder(
				new LineBorder(DefaultTheme.BORDER_SOFT, 1),
				new EmptyBorder(0, 0, 0, 0)));
		wrapper.add(card, BorderLayout.CENTER);

		JPanel heroPanel = new JPanel();
		heroPanel.setPreferredSize(new Dimension(320, 0));
		heroPanel.setBackground(DefaultTheme.BACKGROUND_HERO);
		heroPanel.setLayout(new BoxLayout(heroPanel, BoxLayout.Y_AXIS));
		heroPanel.setBorder(new EmptyBorder(40, 40, 40, 40));

		JLabel heroTitle = new JLabel("Rejoignez Smart Parking");
		heroTitle.setFont(DefaultTheme.FONT_HERO_TITLE);
		heroTitle.setForeground(Color.WHITE);
		heroPanel.add(heroTitle);

		heroPanel.add(Box.createVerticalStrut(10));

		JLabel heroSubtitle = new JLabel("Creez votre compte client pour reserver plus vite.");
		heroSubtitle.setFont(DefaultTheme.FONT_BODY);
		heroSubtitle.setForeground(DefaultTheme.FOREGROUND_HERO_SUBTITLE);
		heroPanel.add(heroSubtitle);

		heroPanel.add(Box.createVerticalGlue());

		JLabel heroHint = new JLabel("Gestion simple des parkings et tickets.");
		heroHint.setFont(DefaultTheme.FONT_HERO_HINT);
		heroHint.setForeground(DefaultTheme.FOREGROUND_HERO_HINT);
		heroPanel.add(heroHint);

		card.add(heroPanel, BorderLayout.WEST);

		JPanel formPanel = new JPanel();
		formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));
		formPanel.setBackground(DefaultTheme.BACKGROUND_CARD);
		formPanel.setBorder(new EmptyBorder(40, 50, 40, 50));
		card.add(formPanel, BorderLayout.CENTER);

		JLabel title = this.createLabel("Creer un compte", DefaultTheme.FONT_TITLE, DefaultTheme.TEXT_COLOR);
		title.setAlignmentX(LEFT_ALIGNMENT);
		formPanel.add(title);

		formPanel.add(Box.createVerticalStrut(6));

		JLabel subtitle = this.createLabel("Renseignez vos informations pour commencer.", DefaultTheme.FONT_BODY,
				DefaultTheme.SUBTEXT_COLOR);
		subtitle.setAlignmentX(LEFT_ALIGNMENT);
		formPanel.add(subtitle);

		formPanel.add(Box.createVerticalStrut(24));

		this.nomField = this.buildField(formPanel, "Nom", "Dupond", false);
		this.prenomField = this.buildField(formPanel, "Prenom", "Jean", false);
		this.emailField = this.buildField(formPanel, "Email", "prenom.nom@exemple.fr", false);
		this.mdpField = this.buildField(formPanel, "Mot de passe (min. 8 caracteres)", "", true);
		this.confirmField = this.buildField(formPanel, "Confirmer le mot de passe", "", true);

		formPanel.add(Box.createVerticalStrut(8));

		this.btnCreer = new JButton("Creer mon compte");
		this.btnCreer.setBackground(new Color(52, 58, 64));
		this.btnCreer.setForeground(Color.WHITE);
		this.btnCreer.setFont(DefaultTheme.FONT_BUTTON);
		this.btnCreer.setFocusPainted(false);
		this.btnCreer.setBorder(BorderFactory.createEmptyBorder(12, 20, 12, 20));
		this.btnCreer.setAlignmentX(LEFT_ALIGNMENT);
		formPanel.add(this.btnCreer);

		formPanel.add(Box.createVerticalStrut(12));

		this.btnRetourConnexion = new JButton("J'ai deja un compte");
		this.btnRetourConnexion.setBackground(DefaultTheme.BACKGROUND_CARD);
		this.btnRetourConnexion.setForeground(new Color(52, 58, 64));
		this.btnRetourConnexion.setFont(DefaultTheme.FONT_LABEL_SMALL);
		this.btnRetourConnexion.setFocusPainted(false);
		this.btnRetourConnexion.setBorder(BorderFactory.createCompoundBorder(
				new LineBorder(DefaultTheme.BORDER_BUTTON, 1),
				new EmptyBorder(10, 14, 10, 14)));
		this.btnRetourConnexion.setAlignmentX(LEFT_ALIGNMENT);
		formPanel.add(this.btnRetourConnexion);

		formPanel.add(Box.createVerticalGlue());

		ControleurInscriptionPage controleur = new ControleurInscriptionPage(this);
		this.btnCreer.addActionListener(controleur);
		this.confirmField.getField().addActionListener(controleur);

		this.btnRetourConnexion.addActionListener(
				e -> NavigationFrame.getInstance().showPage("Connexion", LoginPage::new, "Connexion"));
	}

	private TemplateSaisie buildField(JPanel container, String label, String placeholder, boolean isPassword) {
		TemplateSaisie field = new TemplateSaisie(label, placeholder, isPassword);
		field.setAlignmentX(LEFT_ALIGNMENT);
		field.getField().setFont(DefaultTheme.FONT_BODY);
		field.getField().setBorder(BorderFactory.createCompoundBorder(
				new LineBorder(DefaultTheme.BORDER_INPUT, 1, true),
				new EmptyBorder(10, 12, 10, 12)));
		field.getField().setBackground(DefaultTheme.BACKGROUND_INPUT);
		field.getField().setMaximumSize(DefaultTheme.INPUT_MAX_SIZE);
		container.add(field);
		container.add(Box.createVerticalStrut(18));
		return field;
	}

	private JLabel createLabel(String text, Font font, Color color) {
		// Centralise la creation des libelles pour coherer les styles.
		JLabel label = new JLabel(text);
		label.setFont(font);
		label.setForeground(color);
		return label;
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
