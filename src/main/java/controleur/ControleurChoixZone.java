package controleur;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

import modele.ZoneVoirie;
import modele.dao.DaoZoneVoirie;
import modele.dao.MySQLDataSource;
import vue.ChoixZone;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ControleurChoixZone {
	private static final Logger LOGGER = Logger.getLogger(ControleurChoixZone.class.getName());

	private final DaoZoneVoirie dao;
	
	public ControleurChoixZone(ChoixZone vue) {
		this.dao = new DaoZoneVoirie();
		MySQLDataSource.creerAcces();
	}

	
	public List<ZoneVoirie> recupererZones() {
        try {
			return dao.findAll();
		} catch (SQLException e) {
			LOGGER.log(Level.SEVERE, e.getMessage(), e);
		}
		return Collections.emptyList();
    }
}
