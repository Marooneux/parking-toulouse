package modele.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import modele.dao.requetes.Requete;

public abstract class DaoModele<T> implements Dao<T> {

	protected abstract T creerInstance(ResultSet curseur) throws SQLException;

	protected List<T> select(PreparedStatement prSt) throws SQLException {
		List<T> t = new LinkedList<>();
		try (prSt; ResultSet rs = prSt.executeQuery()) {
			while (rs.next()) {
				t.add(this.creerInstance(rs));
			}
		}
		return t;
	}

	public int miseAJour(Requete<T> req, T donnee) throws SQLException {
		try (PreparedStatement ps = MySQLDataSource.getConnexion().prepareStatement(req.requete())) {
			req.parametres(ps, donnee);
			return ps.executeUpdate();
		}
	}

	public int miseAJour(Requete<T> req, T donnee, String... id) throws SQLException {
		try (PreparedStatement ps = MySQLDataSource.getConnexion().prepareStatement(req.requete())) {
			req.parametres(ps, id);
			return ps.executeUpdate();
		}
	}

	public int miseAJourAvecKeyGeneration(Requete<T> req, T donnee) throws SQLException {
		Connection cn = MySQLDataSource.getConnexion();
		try (PreparedStatement ps = cn.prepareStatement(req.requete(), Statement.RETURN_GENERATED_KEYS)) {
			req.parametres(ps, donnee);
			ps.executeUpdate();
			try (ResultSet keys = ps.getGeneratedKeys()) {
				if (keys != null && keys.next()) {
					return keys.getInt(1);
				}
			}
		}
		return 0;
	}

	public List<T> find(Requete<T> req, String... id) throws SQLException {
		PreparedStatement ps = MySQLDataSource.getConnexion().prepareStatement(req.requete());
		req.parametres(ps, id);
		return this.select(ps);
	}

	public T findById(Requete<T> req, String... id) throws SQLException {
		List<T> res = this.find(req, id);
		if (res.isEmpty()) {
			return null;
		}
		return res.getFirst();
	}

	public int findCount(Requete<T> req, String... id) throws SQLException {
	    Connection cn = MySQLDataSource.getConnexion();
	    PreparedStatement ps = cn.prepareStatement(req.requete());
	    req.parametres(ps, id);
	    
	    ResultSet rs = ps.executeQuery();
	    int resultat = 0;
	    
	    if (rs.next()) {
	        resultat = rs.getInt(1);
	    }
	    
	    rs.close();
	    ps.close();
	    
	    return resultat;
	}
}
