package controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import modele.Parking;
import utils.AuthManager;
import vue.ModifierParking;

public class ControleurParkingPanelModifier implements ActionListener {
    private final Parking parking;

    public ControleurParkingPanelModifier(Parking parking) {
        this.parking = parking;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (!AuthManager.ensureAuthorized("sysadmin", "parkingadmin")) {
            return;
        }
        ModifierParking modifier = new ModifierParking(parking);
        modifier.setVisible(true);
    }
}