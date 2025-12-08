package modele;

import java.util.HashSet;
import java.util.Set;

public class EnsembleParking {
	private Set<Parking> parkings = new HashSet<Parking>();

	public EnsembleParking() {
		this.parkings = new HashSet<Parking>();
	}

	public Set<Parking> getParkings() {
		return this.parkings;
	}

	public void ajouterParking(Parking parking) {
		this.parkings.add(parking);
	}

	public void retirerParking(Parking parking) {
		this.parkings.remove(parking);
	}
}
