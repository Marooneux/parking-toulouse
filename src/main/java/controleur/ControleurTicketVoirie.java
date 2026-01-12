package controleur;

import java.awt.Color;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import modele.StationnementVoirie.Couleur;

public class ControleurTicketVoirie {
	private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");


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
	
	public static Color getRgb(Couleur couleur) {
		Color col = Color.black;
		if (couleur == Couleur.ROUGE) {
			col = Color.red;
		}
		if (couleur == Couleur.VERTE) {
			col = Color.green;
		}
		if (couleur == Couleur.JAUNE) {
			col = Color.yellow;
		}
		if (couleur == Couleur.ORANGE) {
			col = Color.orange;
		}
		if (couleur == Couleur.BLEU) {
			col = Color.blue;
		}
		return col;
	}
}
