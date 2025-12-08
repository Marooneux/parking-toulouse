package modele.dao;

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
