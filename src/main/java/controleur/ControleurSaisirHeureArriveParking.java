package controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import javax.swing.JOptionPane;

import vue.SaisirHeureArriveParking;
import vue.TicketParking;

public class ControleurSaisirHeureArriveParking implements ActionListener {

    public enum Etat {
        ATTENTE_HEURE, DEMARRER_STATIONNEMENT
    }

    private Etat etat;
    private SaisirHeureArriveParking vue;

    public ControleurSaisirHeureArriveParking(SaisirHeureArriveParking vue) {
        this.vue = vue;
        this.etat = Etat.ATTENTE_HEURE;

        vue.getBtnPayment().addActionListener(this);
        vue.getBtnMaintenant().addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object src = e.getSource();

        switch (etat) {
            case ATTENTE_HEURE:
			btnMaintenant(src);

                if (src == vue.getBtnPayment()) {
                    if (!verifierPlaque() || !verifierHeure()) {
                        return; 
                    }

                    etat = Etat.DEMARRER_STATIONNEMENT;
                    ouvrirTicket();
                }
                break;

            case DEMARRER_STATIONNEMENT:
                if (src == vue.getBtnPayment()) {
                    if (!verifierPlaque() || !verifierHeure()) {
                        return;
                    }
                    ouvrirTicket();
                }
                break;
        }
    }

	private void btnMaintenant(Object src) {
		if (src == vue.getBtnMaintenant()) {
		    LocalTime now = LocalTime.now();
		    vue.getTextField().setText(now.format(DateTimeFormatter.ofPattern("HH:mm")));
		    return;
		}
	}

    private boolean verifierHeure() {
        String txt = vue.getTextField().getText().trim();
        LocalTime saisie;

        try {
            saisie = LocalTime.parse(txt, DateTimeFormatter.ofPattern("HH:mm"));
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(vue, "Format invalide. Exemple : 14:30");
            return false;
        }

        if (saisie.isAfter(LocalTime.now())) {
            JOptionPane.showMessageDialog(vue, "L'heure ne peut pas être supérieure à maintenant.");
            return false;
        }

        return true; 
    }
    
    private boolean verifierPlaque() {
        String plaque = vue.getPlaque().getText().trim(); 

        if (plaque.isEmpty()) {
            JOptionPane.showMessageDialog(vue, "La plaque ne peut pas être vide.");
            return false;
        }

        if (!plaque.matches("(?i)[A-Z]{2}-\\d{3}-[A-Z]{2}")) {
            JOptionPane.showMessageDialog(vue, "Format de plaque invalide. Exemple : AB-123-CD");
            return false;
        }
        return true;
    }

    private void ouvrirTicket() {
        new TicketParking().setVisible(true);
        vue.dispose();
    }
    
    

}
