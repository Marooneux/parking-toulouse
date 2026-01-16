package controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import vue.ConfirmationPaiementVoirie;
import vue.TicketVoirie;

public class ControleurConfirmationPaiementVoirie implements ActionListener {

    private final ConfirmationPaiementVoirie vue;

    public ControleurConfirmationPaiementVoirie(ConfirmationPaiementVoirie vue) {
        this.vue = vue;
        this.vue.getBtnTerminer().addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        TicketVoirie ticket = new TicketVoirie(
                vue.getZone(),
                vue.getImmatriculation(),
                vue.getDuree(),
                vue.getMoyenPaiement());
        ticket.setVisible(true);
        vue.dispose();
    }
}
