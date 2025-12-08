package controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import vue.LoginPage;

public class ControleurLoginPage implements ActionListener {
	private LoginPage vue;
	private ModeleUtilisateur modele;

	public ControleurLoginPage(LoginPage vue) {
		this.vue = vue;
		this.vue.setActifBoutonValider(false);
		this.modele = new ModeleUtilisateur();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Boolean estSaisieValide = !this.vue.getLogin().isEmpty() && !this.vue.getMdp().isEmpty();
		this.vue.setActifBoutonValider(estSaisieValide);

		if (this.modele.estLoginValide(this.vue.getLogin(), this.vue.getMdp())) {
			this.vue.dispose();
			return new ControleurChoixTypeStationnement();
		}
	}
}
