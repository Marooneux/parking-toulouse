package modele.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import modele.dao.requetes.Requete;

public abstract class DaoModele<T> implements Dao<T> {

	protected abstract T creerInstance(ResultSet curseur) throws SQLException;

	protected List<T> select(PreparedStatement prSt) throws SQLException {
		List<T> t = new LinkedList<>();
		ResultSet rs = prSt.executeQuery();
		while (rs.next()) {
			t.add(this.creerInstance(rs));
		}
		prSt.close();
		rs.close();
		return t;
	}

	public int miseAJour(Requete<T> req, T donnee) throws SQLException {
		PreparedStatement ps = MySQLDataSource.getConnexion().prepareStatement(req.requete());
		req.parametres(ps, donnee);
		return ps.executeUpdate();
	}

	public int miseAJourAvecKeyGeneration(Requete<T> req, T donnee) throws SQLException {
		int generatedId = -1;
		Connection cn = MySQLDataSource.getConnexion();
		PreparedStatement ps = cn.prepareStatement(req.requete(), java.sql.Statement.RETURN_GENERATED_KEYS);
		req.parametres(ps, donnee);
		ps.executeUpdate();
		ResultSet keys = ps.getGeneratedKeys();
		if (keys != null && keys.next()) {
			generatedId = keys.getInt(1);
		}
		return generatedId;
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

}
