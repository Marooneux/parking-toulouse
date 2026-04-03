package vue.adminParking;

import javax.swing.*;
import java.awt.*;
import ui.theme.DefaultTheme;

public class UIButtons {

    public static JButton primary(String text) {
        JButton btn = new JButton(text);
        btn.setBackground(new Color(0, 128, 255));
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setBorderPainted(false);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.setFont(DefaultTheme.FONT_BODY);
        return btn;
    }

    public static JButton danger(String text) {
        JButton btn = new JButton(text);
        btn.setBackground(new Color(220, 53, 69));
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setBorderPainted(false);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.setFont(DefaultTheme.FONT_BODY);
        return btn;
    }

    public static JButton sidebar(String text) {
        JButton btn = new JButton(text);
        btn.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        btn.setBackground(new Color(52, 58, 64));
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setBorderPainted(false);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.setFont(DefaultTheme.FONT_BODY);
        return btn;
    }
}
