package modele.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
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
    }

    @Override
    public void delete(Association donnees) throws SQLException {
    }

    @Override
    public List<Association> findAll() throws SQLException {
        return null; 
    }

    @Override
    protected Association creerInstance(ResultSet curseur) throws SQLException {        
        int idUser = curseur.getInt("id_utilisateur");
        int idPark = curseur.getInt("id_parking");
        
        return new Association(idUser, idPark);
    }
}