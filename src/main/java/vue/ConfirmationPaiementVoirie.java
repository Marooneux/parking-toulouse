package vue;

import modele.ZoneVoirie;

public class ConfirmationPaiementVoirie extends ConfirmationPaiement {

	private static final long serialVersionUID = -1044509068457353369L;
	private ZoneVoirie zone;
	private String immatriculation;
	private int duree;
	private double prix;
	private String moyenPaiement;

	public ConfirmationPaiementVoirie(ZoneVoirie zone, String immatriculation, int duree, double prix,
			String moyenPaiement) {
		super(prix, "Voir le e-ticket", null);
		this.zone = zone;
		this.immatriculation = immatriculation;
		this.duree = duree;
		this.prix = prix;
		this.moyenPaiement = moyenPaiement;
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

	public double getPrix() {
		return this.prix;
	}

	public String getMoyenPaiement() {
		return this.moyenPaiement;
	}
}
