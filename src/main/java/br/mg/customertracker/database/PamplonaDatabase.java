package br.mg.customertracker.database;

import java.sql.Connection;
import java.sql.SQLException;

import br.mg.customertracker.common.SharedConstants;
import oracle.jdbc.pool.OracleDataSource;

public class PamplonaDatabase {

	private static PamplonaDatabase INSTANCE;
	private static OracleDataSource dataSource;

	private PamplonaDatabase() {
		try {
			dataSource = new OracleDataSource();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		dataSource.setUser(SharedConstants.USER_DB_PAMPLONA);
		dataSource.setPassword(SharedConstants.PASS_DB_PAMPLONA);
		dataSource.setURL(SharedConstants.LISTENER_DB_PAMPLONA);
	}

	public static synchronized PamplonaDatabase getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new PamplonaDatabase();
		}
		return INSTANCE;
	}

	public Connection getConnection() {
		try {
			return dataSource.getConnection();
		} catch (SQLException e) {
			return null;
		}
	}

}
