package br.mg.customertracker.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class PamplonaDatabaseAcessor {

	public static ResultSet executeQuery(String sql) {

		Connection c = PamplonaDatabase.getInstance().getConnection();
		Statement stat = null;
		ResultSet rs = null;
		try {
			stat = c.createStatement();
			rs = stat.executeQuery(sql);
		} catch (SQLException e) {

		} finally {
			try {
				rs.close();
				stat.close();
				c.close();
			} catch (SQLException e) {
			}

		}

		return rs;

	}

}
