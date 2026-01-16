package modele.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import modele.Utilisateur;
import modele.Vehicule;
import modele.Vehicule.TypeVehicule;
import modele.dao.requetes.RequeteDeleteVehicule;
import modele.dao.requetes.RequeteInsertVehicule;
import modele.dao.requetes.RequeteSelectVehiculeById;
import modele.dao.requetes.RequeteSelectVehicules;
import modele.dao.requetes.RequeteSelectVehiculesByUserId;
import modele.dao.requetes.RequeteUpdateVehicule;

public class DaoVehicule extends DaoModele<Vehicule> {

    private final DaoUtilisateur daoUtilisateur = new DaoUtilisateur();

    @Override
    public void create(Vehicule donnee) throws SQLException {
        int id = this.miseAJourAvecKeyGeneration(new RequeteInsertVehicule(), donnee);
        donnee.setId(id);
    }

    @Override
    public void update(Vehicule donnee) throws SQLException {
        this.miseAJour(new RequeteUpdateVehicule(), donnee);
    }

    @Override
    public void delete(Vehicule donnee) throws SQLException {
        this.miseAJour(new RequeteDeleteVehicule(), donnee);
    }

    @Override
    public List<Vehicule> findAll() throws SQLException {
        return this.find(new RequeteSelectVehicules());
    }

    public List<Vehicule> findByUserId(int userId) throws SQLException {
        return this.find(new RequeteSelectVehiculesByUserId(), String.valueOf(userId));
    }

    public Vehicule findById(int id) throws SQLException {
        return this.findById(new RequeteSelectVehiculeById(), String.valueOf(id));
    }

    @Override
    protected Vehicule creerInstance(ResultSet curseur) throws SQLException {
        int id = curseur.getInt("id");
        String immatriculation = curseur.getString("immatriculation");
        String type = curseur.getString("type_vehicule");
        int idUtilisateur = curseur.getInt("id_utilisateur");

        Utilisateur utilisateur = daoUtilisateur.findById(idUtilisateur);
        return new Vehicule(id, immatriculation, TypeVehicule.valueOf(type.toUpperCase()), utilisateur);
    }
}
