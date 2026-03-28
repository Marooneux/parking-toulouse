package controleur;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

import modele.ZoneVoirie;
import modele.dao.DaoZoneVoirie;
import modele.dao.MySQLDataSource;
import vue.ChoixZone;

public class ControleurChoixZone {
	private final DaoZoneVoirie dao;
	
	public ControleurChoixZone(ChoixZone vue) {
		this.dao = new DaoZoneVoirie();
		MySQLDataSource.creerAcces();
	}

	
	public List<ZoneVoirie> recupererZones() {
        try {
			return dao.findAll();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return Collections.emptyList();
    }
}
