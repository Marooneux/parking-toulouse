package vue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.AbstractDocument;
import javax.swing.text.JTextComponent;

import ui.theme.DefaultTheme;
import vue.PaiementCarte.LimiteCaracteresFilter;

public abstract class SaisirStationnementBase extends JPanel {

	private static final long serialVersionUID = 1L;

	protected TemplateSaisie textFieldPlaque;
	protected JButton btnConfirmer;

	protected void buildUI(String sousTitre, String boutonLabel) {
		this.setLayout(new BorderLayout(15, 15));
		this.setBorder(new EmptyBorder(20, 20, 20, 20));
		this.setBackground(new Color(250, 250, 250));

		this.btnConfirmer = new JButton(boutonLabel);

		JPanel header = new JPanel(new FlowLayout(FlowLayout.LEFT));
		header.setBackground(new Color(250, 250, 250));
		JLabel lblIcon = new JLabel("\uD83C\uDFE2");
		lblIcon.setFont(DefaultTheme.FONT_ICON);
		header.add(lblIcon);
		JPanel texte = new JPanel();
		texte.setBackground(new Color(250, 250, 250));
		texte.setLayout(new BoxLayout(texte, BoxLayout.Y_AXIS));
		JLabel lblTitre = new JLabel("Démarrer le Stationnement");
		lblTitre.setFont(DefaultTheme.FONT_TITLE);
		lblTitre.setForeground(new Color(40, 40, 40));
		texte.add(lblTitre);
		JLabel lblSousTitre = new JLabel(sousTitre);
		lblSousTitre.setFont(DefaultTheme.FONT_BODY);
		lblSousTitre.setForeground(new Color(100, 100, 100));
		texte.add(lblSousTitre);
		header.add(texte);
		this.add(header, BorderLayout.NORTH);

		JPanel body = new JPanel();
		body.setLayout(new BoxLayout(body, BoxLayout.Y_AXIS));
		body.setBackground(new Color(250, 250, 250));
		body.setBorder(new EmptyBorder(10, 0, 10, 0));
		this.ajouterCarteDeDetails(body, this.getTitreObjet(), this.buildDetailsObjet());
		this.ajouterCarteDeDetails(body, "Informations du véhicule", this.buildDetailsVoiture());
		this.ajouterCarteDeDetails(body, this.getTitreInput(), this.buildDetailsInput());
		this.add(body, BorderLayout.CENTER);

		JPanel buttonPanel = new JPanel();
		buttonPanel.setBackground(new Color(250, 250, 250));
		this.btnConfirmer.setFont(DefaultTheme.FONT_TITLE_S);
		this.btnConfirmer.setBackground(new Color(0, 122, 255));
		this.btnConfirmer.setForeground(Color.WHITE);
		this.btnConfirmer.setFocusPainted(false);
		this.btnConfirmer.setBorderPainted(false);
		this.btnConfirmer.setCursor(new Cursor(Cursor.HAND_CURSOR));
		this.btnConfirmer.setMinimumSize(new Dimension(250, 50));
		this.btnConfirmer.setOpaque(true);
		buttonPanel.add(this.btnConfirmer);
		this.add(buttonPanel, BorderLayout.SOUTH);
	}

	protected abstract String getTitreObjet();
	protected abstract JPanel buildDetailsObjet();
	protected abstract String getTitreInput();
	protected abstract JPanel buildDetailsInput();

	protected JPanel buildDetailsVoiture() {
		JPanel p = new JPanel();
		p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
		p.setBackground(Color.WHITE);
		p.setBorder(new EmptyBorder(15, 15, 15, 15));

		JLabel lblInfo = new JLabel("Entrez la plaque d'immatriculation de votre véhicule avec le format suivant");
		lblInfo.setFont(DefaultTheme.FONT_LABEL);
		lblInfo.setForeground(new Color(50, 50, 50));
		p.add(lblInfo);

		this.textFieldPlaque = new TemplateSaisie("Plaque", "AB-001-CD", false, false);
		((AbstractDocument) this.textFieldPlaque.getField().getDocument())
				.setDocumentFilter(new LimiteCaracteresFilter(20));
		this.textFieldPlaque.getField().setPreferredSize(new Dimension(250, 30));
		p.add(this.textFieldPlaque);

		JLabel lblAvertissement = new JLabel(
				"Vous serez susceptible de recevoir une amende si la plaque indiquée n'est pas la bonne");
		lblAvertissement.setFont(DefaultTheme.FONT_BODY_S);
		lblAvertissement.setForeground(new Color(120, 120, 120));
		lblAvertissement.setBorder(new EmptyBorder(5, 0, 0, 0));
		p.add(lblAvertissement);

		return p;
	}

	protected void ajouterCarteDeDetails(JPanel parent, String title, JPanel innerContent) {
		JPanel card = new JPanel();
		card.setLayout(new BorderLayout());
		card.setBackground(Color.WHITE);
		card.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createEmptyBorder(10, 0, 10, 0),
				BorderFactory.createLineBorder(new Color(230, 230, 230), 1, true)));

		JPanel cardHeader = new JPanel(new FlowLayout(FlowLayout.LEFT));
		cardHeader.setBackground(Color.WHITE);
		JLabel lblTitle = new JLabel(title);
		lblTitle.setFont(DefaultTheme.FONT_BUTTON);
		lblTitle.setForeground(new Color(70, 70, 70));
		cardHeader.add(lblTitle);

		card.add(cardHeader, BorderLayout.NORTH);
		card.add(innerContent, BorderLayout.CENTER);

		parent.add(card);
		parent.add(Box.createRigidArea(new Dimension(0, 10)));
	}

	public JButton getBtnConfirmer() {
		return this.btnConfirmer;
	}

	public JTextComponent getPlaque() {
		return this.textFieldPlaque.getField();
	}

	public String getImmatriculation() {
		return this.textFieldPlaque.getText();
	}

	public void addConfirmerListener(ActionListener listener) {
		this.btnConfirmer.addActionListener(listener);
	}
}
