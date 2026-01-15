package controleur;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;

public class ControleurButtonHover extends MouseAdapter {
    private final JButton button;
    private final Color baseColor;

    public ControleurButtonHover(JButton button, Color baseColor) {
        this.button = button;
        this.baseColor = baseColor;
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        button.setBackground(baseColor.darker());
    }

    @Override
    public void mouseExited(MouseEvent e) {
        button.setBackground(baseColor);
    }
}