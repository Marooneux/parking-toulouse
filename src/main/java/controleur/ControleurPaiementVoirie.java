package controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import modele.ZoneVoirie;
import vue.ConfirmationPaiementVoirie;
import vue.NavigationFrame;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ControleurPaiementVoirie implements ActionListener {
	private static final Logger LOGGER = Logger.getLogger(ControleurPaiementVoirie.class.getName());

	private final JPanel vue;
	private final ZoneVoirie zone;
	private final String immatriculation;
	private final int duree;
	private final double prix;
	private final String moyenPaiement;

	public ControleurPaiementVoirie(JPanel vue, JButton btnPayer, ZoneVoirie zone, String immatriculation, int duree, double prix, String moyenPaiement) {
		this.vue = vue;
		this.zone = zone;
		this.immatriculation = immatriculation;
		this.duree = duree;
		this.prix = prix;
		this.moyenPaiement = moyenPaiement;
		btnPayer.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			ConfirmationPaiementVoirie confirmation = new ConfirmationPaiementVoirie(
					zone,
					immatriculation,
					duree,
					prix,
					moyenPaiement);
			new ControleurConfirmationPaiementVoirie(confirmation);
			String key = "voirie-confirmation-" + immatriculation + "-" + duree;
			NavigationFrame.getInstance().showPage(key, () -> confirmation, "Paiement validé", true);
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(vue, "Impossible d'ouvrir la confirmation de paiement.");
			LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
		}
	}
}
