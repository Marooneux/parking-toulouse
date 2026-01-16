package controleur;

import java.sql.SQLException;
import java.util.List;

import modele.ZoneVoirie;
import modele.dao.DaoZoneVoirie;
import modele.dao.MySQLDataSource;
import vue.ChoixZone;

public class ControleurChoixZone {
	
	private ChoixZone vue;
	private DaoZoneVoirie dao;
	
	
	public ControleurChoixZone(ChoixZone vue) {
		this.vue = vue;
		this.dao = new DaoZoneVoirie();
		
		MySQLDataSource.creerAcces();
	}

	
	public List<ZoneVoirie> recupererZones() {
        try {
			return dao.findAll();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
    }
}
