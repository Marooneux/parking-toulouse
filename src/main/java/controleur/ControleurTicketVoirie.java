package controleur;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import javax.swing.JOptionPane;

import vue.TicketVoirie;

public class ControleurTicketVoirie implements ActionListener {
	private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
	private final TicketVoirie vue;

	public ControleurTicketVoirie(TicketVoirie vue) {
		this.vue = vue;
		this.vue.getBtnConfirmer().addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JOptionPane.showMessageDialog(vue, "Stationnement terminé. Merci.");
	}

	public static String calculerHeureDepart(int duree) {
		LocalTime heureDepart = LocalTime.now().plusMinutes(duree);
		String str = heureDepart.format(formatter);
		return str;
	}

	public static String getHeureActuelle() {
		LocalTime heureDepart = LocalTime.now();
		String str = heureDepart.format(formatter);
		return str;
	}

	public static Color getRgb(String couleur) {
		Color col = Color.black;
		if (couleur == "rouge") {
			col = Color.red;
		}
		if (couleur == "verte") {
			col = Color.green;
		}
		if (couleur == "jaune") {
			col = Color.yellow;
		}
		if (couleur == "orange") {
			col = Color.orange;
		}
		if (couleur == "bleu") {
			col = Color.blue;
		}
		return col;
	}
}
