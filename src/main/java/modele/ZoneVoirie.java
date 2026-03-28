package modele;

import java.awt.Color;
import java.io.Serializable;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class ZoneVoirie implements Serializable {
	private static final long serialVersionUID = 1L;

	private int id;
	private String couleur;
	private double tarifHoraire;
	private int dureeMax;
	private LocalTime debutAm, finAm, debutPm, finPm;

	public ZoneVoirie(int id, String couleur, double tarifHoraire, int dureeMax, LocalTime debutAm, LocalTime finAm,
			LocalTime debutPm, LocalTime finPm) {
		this.id = id;
		this.couleur = couleur;
		this.tarifHoraire = tarifHoraire;
		this.dureeMax = dureeMax;
		this.debutAm = debutAm;
        this.finAm = finAm;
        this.debutPm = debutPm;
        this.finPm = finPm;
	}
	
	
	public String minsToHeures() {
		int heures = dureeMax/60;
		int minutes = dureeMax%60;
		if (minutes != 0) {
			return heures + " heures " + minutes + " minutes";
		} 
		return heures + " heures";
	}
	
	
	
	public String getHorairesAffiches() {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("HH'h'mm");
        StringBuilder sb = new StringBuilder();
        
        if (debutAm != null && finAm != null) {
            sb.append(debutAm.format(fmt)).append("-").append(finAm.format(fmt));
        }
        
        if (debutPm != null && finPm != null) {
            if (!sb.isEmpty()) sb.append(" / ");
            sb.append(debutPm.format(fmt)).append("-").append(finPm.format(fmt));
        }
        return !sb.isEmpty() ? sb.toString() : "Gratuit";
    }
	
	
	
	public Color convertirCouleur() {
		if (couleur == null) return Color.GRAY;
		
		if (couleur.startsWith("#")) {
			try {
				return Color.decode(couleur);
			} catch (NumberFormatException e) {
				return Color.GRAY;
			}
		}
		
		switch (couleur.toLowerCase()) {
			case "jaune": return new Color(255, 204, 0);
			case "orange": return new Color(255, 149, 0);
			case "rouge": return new Color(255, 59, 48);
			case "verte": return new Color(0, 128, 0);
			case "bleue": return new Color(0, 122, 255);
			default: return Color.GRAY;
		}
	}
	

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCouleur() {
		return this.couleur;
	}

	public void setCouleur(String couleur) {
		this.couleur = couleur;
	}

	public double getTarifHoraire() {
		return this.tarifHoraire;
	}

	public void setTarifHoraire(double tarifHoraire) {
		this.tarifHoraire = tarifHoraire;
	}

	public int getDureeMax() {
		return this.dureeMax;
	}

	public void setDureeMax(int dureeMax) {
		this.dureeMax = dureeMax;
	}


	public LocalTime getDebutAm() {
		return debutAm;
	}


	public void setDebutAm(LocalTime debutAm) {
		this.debutAm = debutAm;
	}


	public LocalTime getFinAm() {
		return finAm;
	}


	public void setFinAm(LocalTime finAm) {
		this.finAm = finAm;
	}


	public LocalTime getDebutPm() {
		return debutPm;
	}


	public void setDebutPm(LocalTime debutPm) {
		this.debutPm = debutPm;
	}


	public LocalTime getFinPm() {
		return finPm;
	}


	public void setFinPm(LocalTime finPm) {
		this.finPm = finPm;
	}

}
