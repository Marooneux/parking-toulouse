package vue;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;

public class TemplateSaisie extends JPanel {

    private static final Font LABEL_FONT = new Font("Segoe UI", Font.PLAIN, 13);
    private static final Color LABEL_COLOR = new Color(73, 80, 87);
    private static final Font FIELD_FONT = new Font("Segoe UI", Font.PLAIN, 14);
    private static final Color FIELD_BORDER_COLOR = new Color(206, 212, 218);
    private static final Color FIELD_BG_COLOR = new Color(251, 252, 253);

    private JTextField textField;

    /**
     * Constructeur simplifié pour champ texte standard
     */
    public TemplateSaisie(String labelText, String placeholder) {
        this(labelText, placeholder, false);
    }

    /**
     * Constructeur complet
     * @param labelText Le titre du champ
     * @param placeholder Le texte fantôme
     * @param isPassword Si true, le champ masquera les caractères
     */
    public TemplateSaisie(String labelText, String placeholder, boolean isPassword) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setAlignmentX(Component.LEFT_ALIGNMENT);
        setOpaque(false);

        JLabel label = new JLabel(labelText);
        label.setFont(LABEL_FONT);
        label.setForeground(LABEL_COLOR);
        label.setAlignmentX(Component.LEFT_ALIGNMENT);

        if (isPassword) {
            this.textField = new JPasswordField(20);
        } else {
            this.textField = new PlaceholderTextField(placeholder, 20);
        }

        styleField(this.textField);

        add(label);
        add(Box.createVerticalStrut(6));
        add(this.textField);
    }

    private void styleField(JTextField field) {
        field.setFont(FIELD_FONT);
        field.setBackground(FIELD_BG_COLOR);
        field.setMaximumSize(new Dimension(Integer.MAX_VALUE, 46));
        field.setAlignmentX(Component.LEFT_ALIGNMENT);
        field.setBorder(new CompoundBorder(
                new LineBorder(FIELD_BORDER_COLOR, 1, true),
                new EmptyBorder(10, 12, 10, 12)
        ));
    }


    public String getText() {
        return textField.getText();
    }
    
    public void setText(String input) {
    	this.textField.setText(input);
    }
    
    public char[] getPassword() {
        if (textField instanceof JPasswordField) {
            return ((JPasswordField) textField).getPassword();
        }
        return textField.getText().toCharArray();
    }

    public JTextField getField() {
        return textField;
    }
}