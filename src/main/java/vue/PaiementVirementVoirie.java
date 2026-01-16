package vue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

import modele.ZoneVoirie;

public class PaiementVirementVoirie extends JPanel {

	private static final long serialVersionUID = 1L;
	private static final Color BACKGROUND_COLOR = new Color(248, 249, 250);
	private static final Color BORDER_COLOR = new Color(230, 230, 230);
	private static final Color PRIMARY_COLOR = new Color(0, 122, 255);

	private final ZoneVoirie zone;
	private final String immatriculation;
	private final int duree;
	private final double prix;
	private JButton btnPayer;
	private JTextField textFieldNom;
	private JTextField textFieldIban;

	public PaiementVirementVoirie(ZoneVoirie zone2, String immatriculation, int duree, double prix) {
		this.zone = zone2;
		this.immatriculation = immatriculation;
		this.duree = duree;
		this.prix = prix;

		this.setLayout(new BorderLayout(20, 20));
		this.setBackground(BACKGROUND_COLOR);
		this.setBorder(new EmptyBorder(20, 20, 20, 20));

		JPanel header = new JPanel(new FlowLayout(FlowLayout.LEFT));
		header.setBackground(BACKGROUND_COLOR);

		JLabel icon = new JLabel("💳");
		icon.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 32));
		header.add(icon);

		JPanel titreZone = new JPanel();
		titreZone.setOpaque(false);
		titreZone.setLayout(new BoxLayout(titreZone, BoxLayout.Y_AXIS));

		JLabel lblTitre = new JLabel("Virement bancaire");
		lblTitre.setFont(new Font("Segoe UI", Font.BOLD, 22));
		lblTitre.setForeground(new Color(33, 37, 41));
		titreZone.add(lblTitre);

		JLabel lblSousTitre = new JLabel("Securisez votre reglement");
		lblSousTitre.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblSousTitre.setForeground(new Color(100, 100, 100));
		titreZone.add(lblSousTitre);

		header.add(titreZone);
		this.add(header, BorderLayout.NORTH);

		JPanel center = new JPanel();
		center.setOpaque(false);
		center.setLayout(new GridLayout(1, 2, 20, 0));
		this.add(center, BorderLayout.CENTER);

		JPanel recap = new JPanel();
		recap.setBackground(Color.WHITE);
		recap.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createLineBorder(BORDER_COLOR),
				new EmptyBorder(16, 16, 16, 16)));
		recap.setLayout(new GridLayout(0, 1, 8, 8));

		recap.add(creerInfoRow("Zone", zone.getCouleur()));
		recap.add(creerInfoRow("Immatriculation", immatriculation));
		recap.add(creerInfoRow("Duree", duree + " min"));
		recap.add(creerInfoRow("Montant", String.format("%.2f €", prix)));

		center.add(recap);

		JPanel card = new JPanel();
		card.setBackground(Color.WHITE);
		card.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createLineBorder(BORDER_COLOR),
				new EmptyBorder(18, 18, 18, 18)));
		card.setLayout(new GridLayout(0, 1, 12, 12));

		this.textFieldNom = new PlaceholderTextField("Nom Prenom", 60);
		this.textFieldNom.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		((AbstractDocument) this.textFieldNom.getDocument()).setDocumentFilter(new LimiteCaracteresFilter(60));
		card.add(creerBlocChamps("Nom et prenom", this.textFieldNom));

		this.textFieldIban = new PlaceholderTextField("FR76 3000 6000 0112 3456 7890 189", 34);
		this.textFieldIban.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		((AbstractDocument) this.textFieldIban.getDocument()).setDocumentFilter(new LimiteCaracteresFilter(34));
		card.add(creerBlocChamps("IBAN", this.textFieldIban));

		JPanel panelBtn = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		panelBtn.setOpaque(false);
		btnPayer = new JButton(String.format("Payer - %.2f €", prix));
		btnPayer.setBackground(PRIMARY_COLOR);
		btnPayer.setForeground(Color.WHITE);
		btnPayer.setFont(new Font("Segoe UI", Font.BOLD, 15));
		btnPayer.setFocusPainted(false);
		btnPayer.setPreferredSize(new Dimension(180, 40));
		panelBtn.add(btnPayer);
		card.add(panelBtn);

		center.add(card);
	}

	private JPanel creerInfoRow(String label, String value) {
		JPanel row = new JPanel(new BorderLayout());
		row.setBackground(Color.WHITE);
		row.setBorder(BorderFactory.createEmptyBorder(4, 4, 4, 4));

		JLabel lbl = new JLabel(label);
		lbl.setFont(new Font("Segoe UI", Font.BOLD, 13));
		lbl.setForeground(new Color(80, 80, 80));

		JLabel val = new JLabel(value);
		val.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		val.setForeground(new Color(40, 40, 40));
		val.setHorizontalAlignment(JLabel.RIGHT);

		row.add(lbl, BorderLayout.WEST);
		row.add(val, BorderLayout.EAST);
		return row;
	}

	private JPanel creerBlocChamps(String labelText, JTextField textField) {
		JPanel bloc = new JPanel();
		bloc.setOpaque(false);
		bloc.setLayout(new BoxLayout(bloc, BoxLayout.Y_AXIS));

		JLabel lbl = new JLabel(labelText);
		lbl.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lbl.setAlignmentX(Component.LEFT_ALIGNMENT);

		textField.setForeground(new Color(120, 120, 120));

		JPanel champPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		champPanel.setOpaque(false);
		champPanel.add(textField);

		bloc.add(lbl);
		bloc.add(champPanel);

		return bloc;
	}

	static class LimiteCaracteresFilter extends DocumentFilter {
		private int maxCaracteres;

		public LimiteCaracteresFilter(int maxCaracteres) {
			this.maxCaracteres = maxCaracteres;
		}

		@Override
		public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr)
				throws BadLocationException {
			if (string == null) {
				return;
			}
			if ((fb.getDocument().getLength() + string.length()) <= this.maxCaracteres) {
				super.insertString(fb, offset, string, attr);
			}
		}

		@Override
		public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs)
				throws BadLocationException {
			if (text == null) {
				return;
			}
			int newLength = fb.getDocument().getLength() - length + text.length();
			if (newLength <= this.maxCaracteres) {
				super.replace(fb, offset, length, text, attrs);
			}
		}
	}

	public JButton getBtnPayer() {
		return this.btnPayer;
	}

	public ZoneVoirie getZone() {
		return this.zone;
	}

	public String getImmatriculation() {
		return this.immatriculation;
	}

	public int getDuree() {
		return this.duree;
	}

	public double getPrix() {
		return this.prix;
	}
}