package controleur;

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
}
