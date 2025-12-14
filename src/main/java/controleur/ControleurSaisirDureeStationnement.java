package controleur;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;

import javax.swing.JOptionPane;

import modele.StationnementVoirie;
import modele.StationnementVoirie.Couleur;
import vue.ChoixMoyenPaiement;
import vue.PaiementVoirie;
import vue.SaisirDureeStationnement;
import vue.TicketVoirie;

public class ControleurSaisirDureeStationnement implements ActionListener {

	public enum Etat {
		ATTENTE_DUREE, PAIEMENT
	}

	private Etat etat;
	private SaisirDureeStationnement vue;

	public ControleurSaisirDureeStationnement(SaisirDureeStationnement vue) {
		this.vue = vue;
		this.etat = Etat.ATTENTE_DUREE;
		vue.getBtnConfirmer().addActionListener(this);
	}

	@Override
    public void actionPerformed(ActionEvent e) {
        switch (etat) {
            case ATTENTE_DUREE :
                if (!verifierDuree()) return;
                etat = Etat.PAIEMENT;
                //ouvrirPaiement();
                break;
            case PAIEMENT :
            	//ouvrirPaiement();
            	break;
        }
    }

	public static double calculerPrixTotal(StationnementVoirie zone, int duree) {
		LocalTime actuel = LocalTime.of(6, 0);
		if ((actuel.isBefore(zone.getHorairePayantFin()) && actuel.isAfter(zone.getHorairePayantDebut())) || LocalDate.now().getDayOfWeek() == DayOfWeek.SUNDAY) {
			return 0;
		}
		
		double prixTotal = 0;
    	int heures = duree / 60;
    	int minutes = duree % 60;
    	if (minutes > 0) {
    		prixTotal += zone.getTarifHoraire();
    	}
    	prixTotal += heures*zone.getTarifHoraire();
    	if (zone.getCouleur() == Couleur.ORANGE) {
    		if (duree > 180 && duree < 240) {
    			prixTotal = 4;
    		} else if (duree > 240) {
    			prixTotal = 6;
    		}
    	}
    	return prixTotal;
	}
	
	
    private boolean verifierDuree() {
        String duree = vue.getTextField().getText().trim();
        if (duree.isEmpty()) {
            JOptionPane.showMessageDialog(vue, "Veuillez saisir une durée avant de payer.");
            return false;
        }
        return true;
    }

    public static void ouvrirPaiement(StationnementVoirie zone, String immatriculation, int intDuree, double prix) {
		ChoixMoyenPaiement frameChoixPaiementVoirie = new ChoixMoyenPaiement(zone, immatriculation, intDuree, prix);
        frameChoixPaiementVoirie.setVisible(true);
    }
    
    public static void ouvrirTicket(StationnementVoirie zone, String immatriculation, int intDuree) {
    	TicketVoirie frameTicketVoirie = new TicketVoirie(zone, immatriculation , intDuree, "Gratuit");
        frameTicketVoirie.setVisible(true);
    }
}
