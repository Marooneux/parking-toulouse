package vue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;

import javax.swing.BorderFactory;
import javax.swing.Box;
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

import modele.ReservationParking;
import ui.theme.DefaultTheme;

public class PaiementParking extends JPanel {

	private static final long serialVersionUID = -1573551118360617932L;
	private static final Color BORDER_COLOR = new Color(230, 230, 230);
	private static final Color PRIMARY_COLOR = new Color(0, 122, 255);

	private TemplateSaisie textFieldNom;
	private TemplateSaisie textFieldNumCarte;
	private TemplateSaisie textFieldExpiration;
	private TemplateSaisie textFieldCVC;
	private JButton btnPayer;

	private final ReservationParking reservation;
	private final double prix;

	public PaiementParking(ReservationParking reservation) {
		this.reservation = reservation;
		this.prix = reservation.calculerPrixTotal();

		this.setLayout(new BorderLayout(20, 20));
		this.setBackground(DefaultTheme.COLOR_BG_PAGE);
		this.setBorder(new EmptyBorder(20, 20, 20, 20));

		JPanel header = new JPanel(new FlowLayout(FlowLayout.LEFT));
		header.setBackground(DefaultTheme.COLOR_BG_PAGE);

		JLabel icon = new JLabel("💳");
		icon.setFont(DefaultTheme.FONT_ICON_L);
		header.add(icon);

		JPanel titreZone = new JPanel();
		titreZone.setOpaque(false);
		titreZone.setLayout(new BoxLayout(titreZone, BoxLayout.Y_AXIS));

		JLabel lblTitre = new JLabel("Paiement");
		lblTitre.setFont(DefaultTheme.FONT_TITLE);
		lblTitre.setForeground(DefaultTheme.COLOR_TEXT_PRIMARY);
		titreZone.add(lblTitre);

		JLabel lblSousTitre = new JLabel("Securisez votre reglement");
		lblSousTitre.setFont(DefaultTheme.FONT_BODY);
		lblSousTitre.setForeground(new Color(100, 100, 100));
		titreZone.add(lblSousTitre);

		header.add(titreZone);
		this.add(header, BorderLayout.NORTH);

		JPanel center = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 10));
		center.setOpaque(false);
		this.add(center, BorderLayout.CENTER);

		JPanel card = new JPanel();
		card.setBackground(Color.WHITE);
		card.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createLineBorder(BORDER_COLOR),
				new EmptyBorder(20, 20, 24, 20)));
		card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
		card.setPreferredSize(new Dimension(480, 520));
		card.setMaximumSize(new Dimension(520, 640));

		JPanel brands = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 4));
		brands.setOpaque(false);
		brands.add(this.createBadge("VISA", new Color(0, 86, 179), new Color(229, 239, 255)));
		brands.add(this.createBadge("Mastercard", new Color(204, 0, 0), new Color(255, 235, 235)));
		brands.add(this.createBadge("Amex", new Color(0, 102, 153), new Color(231, 243, 248)));
		brands.add(this.createBadge("Discover", new Color(255, 102, 0), new Color(255, 242, 230)));
		brands.setAlignmentX(Component.LEFT_ALIGNMENT);
		card.add(brands);
		card.add(Box.createVerticalStrut(8));

		JPanel montantRow = new JPanel(new BorderLayout(8, 0));
		montantRow.setOpaque(false);
		JLabel lblMontant = new JLabel("Montant");
		lblMontant.setFont(DefaultTheme.FONT_LABEL_S);
		lblMontant.setForeground(new Color(90, 90, 90));
		JButton pillMontant = new JButton(String.format("%.2f €", this.prix));
		pillMontant.setEnabled(false);
		pillMontant.setBackground(new Color(243, 246, 249));
		pillMontant.setForeground(new Color(40, 40, 40));
		pillMontant.setFont(DefaultTheme.FONT_BUTTON);
		pillMontant.setBorder(BorderFactory.createEmptyBorder(10, 14, 10, 14));
		montantRow.add(lblMontant, BorderLayout.WEST);
		montantRow.add(pillMontant, BorderLayout.EAST);
		montantRow.setAlignmentX(Component.LEFT_ALIGNMENT);
		card.add(montantRow);
		card.add(Box.createVerticalStrut(12));

		this.textFieldNom = new TemplateSaisie("Nom sur la carte", "Nom Prenom", false, false);
		((AbstractDocument) this.textFieldNom.getField().getDocument())
				.setDocumentFilter(new LimiteCaracteresFilter(40));
		this.styleField(this.textFieldNom.getField());
		card.add(this.creerBlocChamps("Nom sur la carte", this.textFieldNom));

		this.textFieldNumCarte = new TemplateSaisie("Numero de carte", "1234 5678 9012 3456", false, false);
		((AbstractDocument) this.textFieldNumCarte.getField().getDocument())
				.setDocumentFilter(new FiltreUniquementChiffres(16));
		this.styleField(this.textFieldNumCarte.getField());
		card.add(this.creerBlocChamps("Numero de carte", this.textFieldNumCarte));

		JPanel row = new JPanel(new GridLayout(1, 2, 12, 0));
		row.setOpaque(false);

		this.textFieldExpiration = new TemplateSaisie("Expiration", "MM/YY", false, false);
		((AbstractDocument) this.textFieldExpiration.getField().getDocument())
				.setDocumentFilter(new LimiteCaracteresFilter(5));
		this.styleField(this.textFieldExpiration.getField(), 120);
		row.add(this.creerBlocChamps("Expiration", this.textFieldExpiration));

		this.textFieldCVC = new TemplateSaisie("CVC", "123", false, false);
		((AbstractDocument) this.textFieldCVC.getField().getDocument())
				.setDocumentFilter(new FiltreUniquementChiffres(3));
		this.styleField(this.textFieldCVC.getField(), 120);
		row.add(this.creerBlocChamps("CVC", this.textFieldCVC));

		row.setAlignmentX(Component.LEFT_ALIGNMENT);
		card.add(row);
		card.add(Box.createVerticalStrut(12));

		JPanel panelBtn = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		panelBtn.setOpaque(false);
		this.btnPayer = new JButton(String.format("Payer - %.2f €", this.prix));
		this.btnPayer.setBackground(PRIMARY_COLOR);
		this.btnPayer.setForeground(Color.WHITE);
		this.btnPayer.setFont(DefaultTheme.FONT_BUTTON);
		this.btnPayer.setFocusPainted(false);
		this.btnPayer.setPreferredSize(new Dimension(200, 44));
		panelBtn.add(this.btnPayer);
		panelBtn.setAlignmentX(Component.LEFT_ALIGNMENT);
		card.add(panelBtn);

		center.add(card);
	}

	private JLabel createBadge(String text, Color fg, Color bg) {
		JLabel tag = new JLabel(text);
		tag.setOpaque(true);
		tag.setBackground(bg);
		tag.setForeground(fg);
		tag.setFont(DefaultTheme.FONT_BOLD);
		tag.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createLineBorder(new Color(220, 220, 220)),
				new EmptyBorder(6, 10, 6, 10)));
		return tag;
	}

	private JPanel creerBlocChamps(String labelText, TemplateSaisie textField) {
		JPanel bloc = new JPanel();
		bloc.setOpaque(false);
		bloc.setLayout(new BoxLayout(bloc, BoxLayout.Y_AXIS));

		JLabel lbl = new JLabel(labelText);
		lbl.setFont(DefaultTheme.FONT_BODY);
		lbl.setAlignmentX(Component.LEFT_ALIGNMENT);

		JPanel champPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		champPanel.setOpaque(false);

		textField.getField().setFont(DefaultTheme.FONT_BODY);
		textField.getField().setForeground(new Color(60, 60, 60));

		champPanel.add(textField);

		bloc.add(lbl);
		bloc.add(champPanel);

		return bloc;
	}

	private void styleField(JTextField field) {
		this.styleField(field, 280);
	}

	private void styleField(JTextField field, int prefWidth) {
		field.setPreferredSize(new Dimension(prefWidth, 34));
		field.setMaximumSize(new Dimension(Integer.MAX_VALUE, 34));
		field.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createLineBorder(new Color(220, 223, 230)),
				new EmptyBorder(6, 10, 6, 10)));
	}

	class FiltreUniquementChiffres extends DocumentFilter {
		private int maxCaracteres;

		public FiltreUniquementChiffres(int maxCaracteres) {
			this.maxCaracteres = maxCaracteres;
		}

		@Override
		public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr)
				throws BadLocationException {
			if (this.estValide(fb.getDocument().getLength(), string.length(), string)) {
				super.insertString(fb, offset, string, attr);
			}
		}

		@Override
		public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs)
				throws BadLocationException {
			int newLength = fb.getDocument().getLength() - length + text.length();
			if (newLength <= this.maxCaracteres && text.matches("\\d+")) {
				super.replace(fb, offset, length, text, attrs);
			} else {
				Toolkit.getDefaultToolkit().beep();
			}
		}

		private boolean estValide(int currentLength, int newStringLength, String text) {
			if (text == null) {
				return false;
			}
			return (currentLength + newStringLength) <= this.maxCaracteres && text.matches("\\d+");
		}
	}

	static class LimiteCaracteresFilter extends DocumentFilter {
		private int maxCaracteres;

		public LimiteCaracteresFilter(int maxCaracteres) {
			this.maxCaracteres = maxCaracteres;
		}

		@Override
		public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr)
				throws BadLocationException {
			if ((fb.getDocument().getLength() + string.length()) <= this.maxCaracteres) {
				super.insertString(fb, offset, string, attr);
			} else {
				Toolkit.getDefaultToolkit().beep();
			}
		}

		@Override
		public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs)
				throws BadLocationException {
			int newLength = fb.getDocument().getLength() - length + text.length();
			if (newLength <= this.maxCaracteres) {
				super.replace(fb, offset, length, text, attrs);
			} else {
				Toolkit.getDefaultToolkit().beep();
			}
		}
	}

	public JButton getBtnPayer() {
		return this.btnPayer;
	}

	public ReservationParking getReservation() {
		return this.reservation;
	}

	public double getPrix() {
		return this.prix;
	}

}