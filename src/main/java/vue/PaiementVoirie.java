package vue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

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
				PaiementVoirie frame = new PaiementVoirie();
				frame.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	public PaiementVoirie() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(520, 480);
		this.setLocationRelativeTo(null);

		this.contentPane = new JPanel();
		this.contentPane.setLayout(new BorderLayout(20, 20));
		this.contentPane.setBackground(Color.WHITE);
		this.contentPane.setBorder(new EmptyBorder(20, 20, 20, 20));
		this.setContentPane(this.contentPane);

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
		this.contentPane.add(header, BorderLayout.NORTH);

		// CARD CENTRAL
		JPanel card = new JPanel();
		card.setBackground(Color.WHITE);
		card.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createLineBorder(new Color(220, 220, 220)),
				new EmptyBorder(20, 20, 20, 20)));
		card.setLayout(new GridLayout(4, 1, 15, 15));
		this.contentPane.add(card, BorderLayout.CENTER);

		// --- CHAMP NOM ---
		this.textFieldNom = new PlaceholderTextField("Nom Prénom", 20);
		((AbstractDocument) this.textFieldNom.getDocument()).setDocumentFilter(new LimiteCaracteresFilter(20));
		this.textFieldNom.setPreferredSize(new Dimension(250, 30));

		JPanel blocNom = this.criarBlocChamp("Numéro de carte", this.textFieldNom);
		card.add(blocNom);

		// --- NUMÉRO DE CARTE ---
		this.textFieldNumCarte = new PlaceholderTextField("1234 5678 9012 3456", 20);
		((AbstractDocument) this.textFieldNumCarte.getDocument()).setDocumentFilter(new FiltreUniquementChiffres(16));

		this.textFieldNumCarte.setPreferredSize(new Dimension(250, 30));

		JPanel blocCarte = this.criarBlocChamp("Numéro de carte", this.textFieldNumCarte);
		card.add(blocCarte);

		// --- CHAINE EXPIRATION + CVC ---
		JPanel row = new JPanel(new GridLayout(1, 2, 20, 0));
		row.setOpaque(false);

		this.textFieldExpiration = new PlaceholderTextField("MM/YY", 10);
		((AbstractDocument) this.textFieldExpiration.getDocument()).setDocumentFilter(new LimiteCaracteresFilter(5));
		this.textFieldExpiration.setPreferredSize(new Dimension(100, 28));
		JPanel blocExp = this.criarBlocChamp("Date d'expiration", this.textFieldExpiration);

		this.textFieldCVC = new PlaceholderTextField("123", 8);
		((AbstractDocument) this.textFieldCVC.getDocument()).setDocumentFilter(new FiltreUniquementChiffres(3));
		this.textFieldCVC.setPreferredSize(new Dimension(100, 28));
		JPanel blocCVC = this.criarBlocChamp("CVC", this.textFieldCVC);

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
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					ConfirmationPaiementVoirie frameConfirmationPaiement = new ConfirmationPaiementVoirie(15);
					frameConfirmationPaiement.setVisible(true);
					PaiementVoirie.this.dispose();
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

		textField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		textField.setForeground(new Color(120, 120, 120));

		champPanel.add(textField);

		bloc.add(lbl);
		bloc.add(champPanel);

		return bloc;
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
}