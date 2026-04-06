package vue.adminParking;
import javax.swing.*;
import java.awt.*;
import ui.theme.DefaultTheme;

public class UIDetailRow {

    public static JPanel create(String icon, String text) {
        JPanel row = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        row.setOpaque(false);

        JLabel lblIcon = new JLabel(icon);
        lblIcon.setFont(DefaultTheme.FONT_ICON_S);
        lblIcon.setPreferredSize(new Dimension(25, 20));
        lblIcon.setForeground(Color.GRAY);

        JLabel lblText = new JLabel(text);
        lblText.setFont(DefaultTheme.FONT_LABEL_S);
        lblText.setForeground(DefaultTheme.COLOR_TEXT_SECONDARY);

        row.add(lblIcon);
        row.add(lblText);

        return row;
    }
}

