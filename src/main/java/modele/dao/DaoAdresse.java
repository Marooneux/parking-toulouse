package modele.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import modele.Adresse;
import modele.dao.requetes.RequeteDeleteAdresse;
import modele.dao.requetes.RequeteInsertAdresse;
import modele.dao.requetes.RequeteSelectAdresse;
import modele.dao.requetes.RequeteSelectAdresseById;
import modele.dao.requetes.RequeteUpdateAdresse;

public class DaoAdresse extends DaoModele<Adresse> {
	@Override
	public void create(Adresse donnee) throws SQLException {
		int id = this.miseAJourAvecKeyGeneration(new RequeteInsertAdresse(), donnee);
		donnee.setId(id);
	}

	@Override
	public void update(Adresse donnee) throws SQLException {
		this.miseAJour(new RequeteUpdateAdresse(), donnee);
	}

	@Override
	public void delete(Adresse donnee) throws SQLException {
		this.miseAJour(new RequeteDeleteAdresse(), donnee);
	}

	@Override
	public List<Adresse> findAll() throws SQLException {
		return this.find(new RequeteSelectAdresse());
	}

	public Adresse findById(int id) throws SQLException {
		return this.findById(new RequeteSelectAdresseById(), String.valueOf(id));
	}

	@Override
	protected Adresse creerInstance(ResultSet curseur) throws SQLException {
		int id = curseur.getInt("id");
		int numero = curseur.getInt("numero");
		String rue = curseur.getString("rue");
		int codePostal = curseur.getInt("code_postal");
		String ville = curseur.getString("ville");

		Adresse adresse = new Adresse(numero, rue, codePostal, ville);
		adresse.setId(id);
		
		return adresse;
	}
}