package controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import vue.TicketVoirie;

public class ControleurTicketVoirieDepart implements ActionListener {

    private final TicketVoirie vue;

    public ControleurTicketVoirieDepart(TicketVoirie vue) {
        this.vue = vue;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.exit(0);
    }
}
