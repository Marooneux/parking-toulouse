package main.java.modele.dao;

import java.sql.Connection;
import java.sql.SQLException;

import com.mysql.cj.jdbc.MysqlDataSource;

public class AdminMySQLDataSource extends MysqlDataSource {

	public AdminMySQLDataSource() throws SQLException {
		super();
		this.setURL("jdbc:mysql://mysql-wacker.alwaysdata.net:3306/wacker_sae_parking");
		this.setUser("wacker_admin");
		this.setPassword("saeadmin");
	}

	public static void main(String[] args) throws SQLException {
		AdminMySQLDataSource bd = new AdminMySQLDataSource();
		Connection cn = bd.getConnection();

		if (cn.isValid(10)) {
			System.out.println("Connexion réussie");
		}

		cn.close();

	}

}
