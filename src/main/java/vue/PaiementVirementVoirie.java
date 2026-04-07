package vue;

import javax.swing.JPanel;

import modele.ZoneVoirie;

public class PaiementVirementVoirie extends PaiementVirement {

	private static final long serialVersionUID = 6895815859853184568L;

	private final ZoneVoirie zone;
	private final String immatriculation;
	private final int duree;

	public PaiementVirementVoirie(ZoneVoirie zone, String immatriculation, int duree, double prix) {
		super(creerRecap(zone, immatriculation, duree, prix), prix);
		this.zone = zone;
		this.immatriculation = immatriculation;
		this.duree = duree;
	}

	private static JPanel creerRecap(ZoneVoirie zone, String immatriculation, int duree, double prix) {
		JPanel recap = creerPanelRecapBase();
		recap.add(creerInfoRow("Zone", zone.getCouleur()));
		recap.add(creerInfoRow("Immatriculation", immatriculation));
		recap.add(creerInfoRow("Duree", duree + " min"));
		recap.add(creerInfoRow("Montant", String.format("%.2f €", prix)));
		return recap;
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
