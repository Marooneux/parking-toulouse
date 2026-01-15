package controleur;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import java.util.function.Consumer;

import modele.Parking;
import javax.swing.JComponent;

public class ControleurParkingPanel extends MouseAdapter {
    private final JComponent panel;
    private final Parking parking;
    private final Consumer<Parking> onClick;

    private final Color normalBorder = new Color(230, 230, 230);
    private final Color hoverBorder = new Color(100, 100, 100);

    public ControleurParkingPanel(JComponent panel, Parking parking, Consumer<Parking> onClick) {
        this.panel = panel;
        this.parking = parking;
        this.onClick = onClick;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (onClick != null) {
            onClick.accept(parking);
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        panel.setBorder(BorderFactory.createCompoundBorder(
            new LineBorder(hoverBorder, 1),
            new EmptyBorder(20, 20, 20, 20)
        ));
    }

    @Override
    public void mouseExited(MouseEvent e) {
        panel.setBorder(BorderFactory.createCompoundBorder(
            new LineBorder(normalBorder, 1),
            new EmptyBorder(20, 20, 20, 20)
        ));
    }
}