package controleur;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import modele.StationnementVoirie;
import vue.ChoixZone;
import vue.SaisirDureeStationnement;

public class ControleurChoixZone extends MouseAdapter {
    private final ChoixZone vue;
    private final StationnementVoirie zone;

    public ControleurChoixZone(ChoixZone vue, StationnementVoirie zone) {
        this.vue = vue;
        this.zone = zone;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        try {
            SaisirDureeStationnement frameDureeStationnement = new SaisirDureeStationnement(zone);
            frameDureeStationnement.setVisible(true);
            this.vue.dispose();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
