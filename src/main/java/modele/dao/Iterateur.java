package modele.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class Iterateur<T> implements Iterator<T> {
	private ResultSet curseur;
	private DaoModele<T> dao;
	private boolean hasNext;

	public Iterateur(ResultSet curseur, DaoModele<T> dao) throws SQLException {
		this.curseur = curseur;
		this.dao = dao;
		this.hasNext = this.curseur.next();
	}

	@Override
	public boolean hasNext() {
		return this.hasNext;
	}

	@Override
	public T next() {
		T instance = null;
		try {
			instance = this.dao.creerInstance(this.curseur);
			this.hasNext = this.curseur.next();
			if (this.hasNext == false) {
				throw new NoSuchElementException();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return instance;
	}

}
