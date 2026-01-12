package controleur;

import java.awt.Color;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

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
