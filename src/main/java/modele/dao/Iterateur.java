package modele.dao;

import java.sql.ResultSet;
import java.util.Iterator;

public class Iterateur implements Iterator {
	private ResultSet curseur;

	public Iterateur(ResultSet curseur, DaoModele<?> daoModele) {
		this.curseur = curseur;
	}

	@Override
	public boolean hasNext() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Object next() {
		// TODO Auto-generated method stub
		return null;
	}

}
