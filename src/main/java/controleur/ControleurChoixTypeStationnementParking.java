package controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import vue.ChoixParking;
import vue.ChoixTypeStationnement;

public class ControleurChoixTypeStationnementParking implements ActionListener {
    private final ChoixTypeStationnement vue;

    public ControleurChoixTypeStationnementParking(ChoixTypeStationnement vue) {
        this.vue = vue;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            new ControleurChoixParking(new ChoixParking());
            vue.dispose();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}