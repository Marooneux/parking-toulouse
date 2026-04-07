package vue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

import ui.theme.DefaultTheme;

public abstract class PaiementVirement extends JPanel {

	private static final long serialVersionUID = 1L;
	private static final Color BACKGROUND_COLOR = DefaultTheme.BACKGROUND_COLOR;
	private static final Color BORDER_COLOR = new Color(230, 230, 230);
	private static final Color PRIMARY_COLOR = new Color(0, 122, 255);

	private JButton btnPayer;
	private final double prix;

	protected PaiementVirement(JPanel recap, double prix) {
		this.prix = prix;

		this.setLayout(new BorderLayout(20, 20));
		this.setBackground(BACKGROUND_COLOR);
		this.setBorder(new EmptyBorder(20, 20, 20, 20));

		JPanel header = new JPanel(new FlowLayout(FlowLayout.LEFT));
		header.setBackground(BACKGROUND_COLOR);

		JLabel icon = new JLabel("💳");
		icon.setFont(DefaultTheme.FONT_ICON);
		header.add(icon);

		JPanel titreZone = new JPanel();
		titreZone.setOpaque(false);
		titreZone.setLayout(new BoxLayout(titreZone, BoxLayout.Y_AXIS));

		JLabel lblTitre = new JLabel("Virement bancaire");
		lblTitre.setFont(DefaultTheme.FONT_HERO_TITLE);
		lblTitre.setForeground(DefaultTheme.TEXT_COLOR);
		titreZone.add(lblTitre);

		JLabel lblSousTitre = new JLabel("Securisez votre reglement");
		lblSousTitre.setFont(DefaultTheme.FONT_BODY);
		lblSousTitre.setForeground(new Color(100, 100, 100));
		titreZone.add(lblSousTitre);

		header.add(titreZone);
		this.add(header, BorderLayout.NORTH);

		JPanel center = new JPanel();
		center.setOpaque(false);
		center.setLayout(new GridLayout(1, 2, 20, 0));
		this.add(center, BorderLayout.CENTER);

		center.add(recap);

		JPanel card = new JPanel();
		card.setBackground(Color.WHITE);
		card.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createLineBorder(BORDER_COLOR),
				new EmptyBorder(18, 18, 18, 18)));
		card.setLayout(new GridLayout(0, 1, 12, 12));

		TemplateSaisie textFieldNom = new TemplateSaisie("Nom et prenom", "Nom Prenom", false, false);
		textFieldNom.getField().setFont(DefaultTheme.FONT_BODY);
		((AbstractDocument) textFieldNom.getField().getDocument())
				.setDocumentFilter(new LimiteCaracteresFilter(60));
		card.add(this.creerBlocChamps("Nom et prenom", textFieldNom));

		TemplateSaisie textFieldIban = new TemplateSaisie("IBAN", "FR76 3000 6000 0112 3456 7890 189", false, false);
		textFieldIban.getField().setFont(DefaultTheme.FONT_BODY);
		((AbstractDocument) textFieldIban.getField().getDocument())
				.setDocumentFilter(new LimiteCaracteresFilter(34));
		card.add(this.creerBlocChamps("IBAN", textFieldIban));

		JPanel panelBtn = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		panelBtn.setOpaque(false);
		this.btnPayer = new JButton(String.format("Payer - %.2f €", prix));
		this.btnPayer.setBackground(PRIMARY_COLOR);
		this.btnPayer.setForeground(Color.WHITE);
		this.btnPayer.setFont(DefaultTheme.FONT_BUTTON_2);
		this.btnPayer.setFocusPainted(false);
		this.btnPayer.setPreferredSize(new Dimension(180, 40));
		panelBtn.add(this.btnPayer);
		card.add(panelBtn);

		center.add(card);
	}

	protected static JPanel creerPanelRecapBase() {
		JPanel recap = new JPanel();
		recap.setBackground(Color.WHITE);
		recap.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createLineBorder(new Color(230, 230, 230)),
				new EmptyBorder(16, 16, 16, 16)));
		recap.setLayout(new GridLayout(0, 1, 8, 8));
		return recap;
	}

	protected static JPanel creerInfoRow(String label, String value) {
		JPanel row = new JPanel(new BorderLayout());
		row.setBackground(Color.WHITE);
		row.setBorder(BorderFactory.createEmptyBorder(4, 4, 4, 4));

		JLabel lbl = new JLabel(label);
		lbl.setFont(DefaultTheme.FONT_BUTTON_ALT);
		lbl.setForeground(new Color(80, 80, 80));

		JLabel val = new JLabel(value);
		val.setFont(DefaultTheme.FONT_LABEL_SMALL);
		val.setForeground(new Color(40, 40, 40));
		val.setHorizontalAlignment(SwingConstants.RIGHT);

		row.add(lbl, BorderLayout.WEST);
		row.add(val, BorderLayout.EAST);
		return row;
	}

	private JPanel creerBlocChamps(String labelText, TemplateSaisie textField) {
		JPanel bloc = new JPanel();
		bloc.setOpaque(false);
		bloc.setLayout(new BoxLayout(bloc, BoxLayout.Y_AXIS));

		JLabel lbl = new JLabel(labelText);
		lbl.setFont(DefaultTheme.FONT_BODY);
		lbl.setAlignmentX(Component.LEFT_ALIGNMENT);

		textField.getField().setForeground(new Color(120, 120, 120));

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

	public double getPrix() {
		return this.prix;
	}
}
