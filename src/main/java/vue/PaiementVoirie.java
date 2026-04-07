package vue;

import modele.ZoneVoirie;

public class PaiementVoirie extends PaiementCarte {

	private static final long serialVersionUID = -754119077706639290L;

	private final ZoneVoirie zone;
	private final String immatriculation;
	private final int duree;

	public PaiementVoirie(ZoneVoirie zone, String immatriculation, int duree, double prix) {
		super(prix);
		this.zone = zone;
		this.immatriculation = immatriculation;
		this.duree = duree;
	}

	public ZoneVoirie getZone() {
		return this.zone;
	}

	public String getImmatriculation() {
		return this.immatriculation;
	}

	public int getDuree() {
		return this.duree;
	}
}
