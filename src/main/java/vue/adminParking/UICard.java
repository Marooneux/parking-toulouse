package vue.adminParking;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import ui.theme.DefaultTheme;

public class UICard {
    public static JPanel create(String title, JPanel content) {
        JPanel card = new JPanel(new BorderLayout());
        card.setBackground(Color.WHITE);
        card.setBorder(BorderFactory.createCompoundBorder(
                new EmptyBorder(10, 0, 10, 0),
                BorderFactory.createLineBorder(new Color(230, 230, 230), 1, true)
        ));

        JPanel header = new JPanel(new FlowLayout(FlowLayout.LEFT));
        header.setBackground(Color.WHITE);

        JLabel lblTitle = new JLabel(title);
        lblTitle.setFont(DefaultTheme.FONT_BUTTON);
        lblTitle.setForeground(new Color(70, 70, 70));
        header.add(lblTitle);

        card.add(header, BorderLayout.NORTH);
        card.add(content, BorderLayout.CENTER);

        return card;
    }
}
