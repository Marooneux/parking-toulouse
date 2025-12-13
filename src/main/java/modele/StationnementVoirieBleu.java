package modele;

import java.time.LocalTime;

public class StationnementVoirieBleu extends StationnementVoirie {
	private LocalTime horaireDebutStationnement2;
	private LocalTime horaireFinStationnement2;
	
	
	public StationnementVoirieBleu(Couleur couleur, double tarifHoraire, int dureeMax, LocalTime horairePayantFin, 
			LocalTime horaireDebut2, LocalTime horaireFin2) {
		super(couleur, tarifHoraire, dureeMax, horairePayantFin);
		this.horaireDebutStationnement2 = horaireDebut2;
		this.horaireFinStationnement2 = horaireFin2;
	}
	
	@Override
	public String horairesToString() {
		return (super.getHorairePayantDebut().toString() + " - " + super.getHorairePayantFin().toString() + ", " + 
				horaireDebutStationnement2.toString() + " - " + horaireFinStationnement2.toString());
	}
	
	@Override
	public String couleurZoneToString() {
		return "Zone " + (super.getCouleur().toString().toLowerCase() + " (disque requis)");
	}
}
