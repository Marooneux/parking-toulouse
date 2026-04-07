package vue.adminParking;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import ui.theme.DefaultTheme;

public class UIForm {

    public static JPanel row(String label, JComponent component) {
        JPanel row = new JPanel(new BorderLayout(10, 0));
        row.setBackground(DefaultTheme.COLOR_BG_PAGE);
        row.setMaximumSize(new Dimension(Integer.MAX_VALUE, 60));
        row.setBorder(new EmptyBorder(5, 0, 5, 0));

        JLabel lbl = new JLabel(label);
        lbl.setFont(DefaultTheme.FONT_BODY);
        lbl.setPreferredSize(new Dimension(150, 30));

        row.add(lbl, BorderLayout.WEST);
        row.add(component, BorderLayout.CENTER);

        return row;
    }
}
