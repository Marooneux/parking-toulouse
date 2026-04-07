package controleur;

import javax.swing.JOptionPane;

import vue.ChoixMoyenPaiement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ControleurChoixMoyenPaiement {
	private static final Logger LOGGER = Logger.getLogger(ControleurChoixMoyenPaiement.class.getName());

	public ControleurChoixMoyenPaiement(ChoixMoyenPaiement vue, Runnable actionCarte, Runnable actionVirement) {
		vue.addCarteListener(e -> executer(vue, actionCarte));
		vue.addVirementListener(e -> executer(vue, actionVirement));
	}

	private static void executer(ChoixMoyenPaiement vue, Runnable action) {
		try {
			action.run();
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(vue, "Erreur lors de l'ouverture du paiement.");
			LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
		}
	}
}
