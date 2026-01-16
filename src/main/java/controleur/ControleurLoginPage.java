package controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import modele.Utilisateur;
import modele.dao.MySQLDataSource;
import utils.AuthManager;
import vue.ChoixTypeStationnement;
import vue.LoginPage;
import vue.NavigationFrame;
import vue.adminParking.Accueil;
import modele.Utilisateur.Type;

public class ControleurLoginPage implements ActionListener {
	private LoginPage vue;

	public ControleurLoginPage(LoginPage vue) {
		this.vue = vue;
		MySQLDataSource.creerAcces();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String utilisateur = this.vue.getLogin();
		String mdp = this.vue.getMdp();

		boolean ok = AuthManager.login(utilisateur, mdp);
		if (!ok) {
			JOptionPane.showMessageDialog(null, "Utilisateur ou mot de passe invalid");
			this.vue.viderChampMdp();
			return;
		}

		Utilisateur user = AuthManager.getCurrentUser();

		if (AuthManager.hasRole(Type.SYSADMIN.name(), Type.PARKINGADMIN.name())) {
			new Accueil(user.getId());
			return;
		}

		NavigationFrame.getInstance().showPage("Stationnement", () -> new ChoixTypeStationnement(user.getId()),
				"Stationnement");
	}
}
