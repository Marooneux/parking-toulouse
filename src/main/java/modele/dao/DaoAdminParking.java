package modele.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import modele.dao.requetes.RequeteInsertAdminParking;
import modele.dao.requetes.RequeteInsertAdminParking.Association;

public class DaoAdminParking extends DaoModele<Association> {

    @Override
    public void create(Association donnees) throws SQLException {
        this.miseAJour(new RequeteInsertAdminParking(), donnees);
    }

    @Override
    public void update(Association donnees) throws SQLException {
        // Update non supporté pour les associations admin-parking
    }

    @Override
    public void delete(Association donnees) throws SQLException {
        // Delete non supporté pour les associations admin-parking
    }

    @Override
    public List<Association> findAll() throws SQLException {
        return Collections.emptyList();
    }

    @Override
    protected Association creerInstance(ResultSet curseur) throws SQLException {        
        int idUser = curseur.getInt("id_utilisateur");
        int idPark = curseur.getInt("id_parking");
        
        return new Association(idUser, idPark);
    }
}