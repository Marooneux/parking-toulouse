package vue;

import java.awt.Component;
import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import ui.theme.DefaultTheme;

public class TemplateSaisie extends JPanel implements Cloneable {

	private static final long serialVersionUID = 4151555613608984332L;
	private final String labelText;
	private final String placeholder;
	private final boolean isPassword;
	private final boolean showLabel;
	private final JLabel label;
	private JTextField textField;

	/**
	 * Constructeur simplifié pour champ texte standard
	 */
	public TemplateSaisie(String labelText, String placeholder) {
		this(labelText, placeholder, false, true);
	}

	/**
	 * Constructeur avec gestion d'affichage du libellé
	 */
	public TemplateSaisie(String labelText, String placeholder, boolean isPassword, boolean showLabel) {
		this.labelText = labelText;
		this.placeholder = placeholder;
		this.isPassword = isPassword;
		this.showLabel = showLabel;
		this.label = new JLabel(labelText);
		this.buildUi();
	}

	/**
	 * Constructeur complet
	 * 
	 * @param labelText   Le titre du champ
	 * @param placeholder Le texte fantôme
	 * @param isPassword  Si true, le champ masquera les caractères
	 */
	public TemplateSaisie(String labelText, String placeholder, boolean isPassword) {
		this(labelText, placeholder, isPassword, true);
	}

	private void buildUi() {
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.setAlignmentX(Component.LEFT_ALIGNMENT);
		this.setOpaque(false);

		this.label.setFont(DefaultTheme.FONT_LABEL_S);
		this.label.setForeground(DefaultTheme.COLOR_TEXT_SECONDARY);
		this.label.setAlignmentX(Component.LEFT_ALIGNMENT);
		this.label.setVisible(this.showLabel);

		if (this.isPassword) {
			this.textField = new JPasswordField(20);
		} else {
			this.textField = new PlaceholderTextField(this.placeholder, 20);
		}

		this.styleField(this.textField);

		this.add(this.label);
		this.add(Box.createVerticalStrut(6));
		this.add(this.textField);
	}

	private void styleField(JTextField field) {
		field.setFont(DefaultTheme.FONT_BODY);
		field.setBackground(DefaultTheme.COLOR_BG_INPUT);
		field.setMaximumSize(new Dimension(Integer.MAX_VALUE, 46));
		field.setAlignmentX(Component.LEFT_ALIGNMENT);
		field.setBorder(new CompoundBorder(
				new LineBorder(DefaultTheme.COLOR_BORDER, 1, true),
				new EmptyBorder(10, 12, 10, 12)));
	}

	public String getText() {
		return this.textField.getText();
	}

	public void setText(String input) {
		this.textField.setText(input);
	}

	public char[] getPassword() {
		if (this.textField instanceof JPasswordField) {
			return ((JPasswordField) this.textField).getPassword();
		}
		return this.textField.getText().toCharArray();
	}

	public JTextField getField() {
		return this.textField;
	}

	@Override
	public TemplateSaisie clone() {
		// Cree une nouvelle instance avec la meme configuration et le meme contenu.
		TemplateSaisie copie = new TemplateSaisie(this.labelText, this.placeholder, this.isPassword, this.showLabel);
		if (this.isPassword) {
			copie.setText(new String(this.getPassword()));
		} else {
			copie.setText(this.getText());
		}
		return copie;
	}
}