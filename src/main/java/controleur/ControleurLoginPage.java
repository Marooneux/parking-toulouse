package controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import modele.Utilisateur;
import utils.AuthManager;
import modele.dao.MySQLDataSource;
import vue.ChoixTypeStationnement;
import vue.LoginPage;

import javax.swing.*;

public class ControleurLoginPage implements ActionListener {
	private LoginPage vue;

	public ControleurLoginPage(LoginPage vue) {
		this.vue = vue;
        MySQLDataSource.creerAcces("root", "claudio");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
        System.out.println("Action Performed!");
        String utilisateur = this.vue.getLogin();
        String mdp = this.vue.getMdp();

        boolean ok = AuthManager.login(utilisateur, mdp);
        if (!ok) {
            JOptionPane.showMessageDialog(null, "Utilisateur ou mot de passe invalid");
        } else {
            Utilisateur user = AuthManager.getCurrentUser();
            JOptionPane.showMessageDialog(null, "bienvenue " + user.getNom());
            ChoixTypeStationnement main = new ChoixTypeStationnement();
            main.setVisible(true);
        }
    }
}