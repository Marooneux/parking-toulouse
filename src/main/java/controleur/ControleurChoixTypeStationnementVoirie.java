package controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import vue.ChoixTypeStationnement;
import vue.ChoixZone;

public class ControleurChoixTypeStationnementVoirie implements ActionListener {
    private final ChoixTypeStationnement vue;

    public ControleurChoixTypeStationnementVoirie(ChoixTypeStationnement vue) {
        this.vue = vue;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            ChoixZone frameChoixZone = new ChoixZone();
            frameChoixZone.setVisible(true);
            vue.dispose();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}