package modele;

import java.util.ArrayList;
import java.util.List;

public class BaseDeDonnees {

	public static List<Object[]> getListeDonnées(String table) {
		List<Object[]> result = new ArrayList<>();

		if ("parkings".equalsIgnoreCase(table)) {
			result.add(new Object[] { "Place Capitole", "Centre Ville, Toulouse", "09-18", "59/100", "10", "Ouvert" });
			result.add(new Object[] { "Place Wilson", "Centre Ville, Toulouse", "08-20", "80/100", "12", "Ouvert" });
			result.add(
					new Object[] { "Place Saint-Georges", "Quartier Saint-Georges", "09-19", "20/50", "8", "Fermé" });
		}

		return result;
	}
}
