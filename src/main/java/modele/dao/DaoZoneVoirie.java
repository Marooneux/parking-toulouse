package modele.dao;

import modele.Zone;
import modele.dao.requetes.RequeteSelectZonesVoirie;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class DaoZoneVoirie extends DaoModele<Zone> {
    @Override
    public void create(Zone donnees) throws SQLException {}

    @Override
    public void update(Zone donnees) throws SQLException {}

    @Override
    public void delete(Zone donnees) throws SQLException {}

    @Override
    public List<Zone> findAll() throws SQLException {
        return this.find(new RequeteSelectZonesVoirie());
    }

    @Override
    protected Zone creerInstance(ResultSet curseur) throws SQLException {
        int id = curseur.getInt("id");
        String nom = curseur.getString("nom");
        double tarif = curseur.getDouble("tarif_horaire");
        return new Zone(id, nom, tarif);
    }
}