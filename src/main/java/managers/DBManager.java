package main.java.managers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DBManager {
	private static DBManager instance;

	private String DB_DRIVER = "org.hsqldb.jdbcDriver";
	private String DB_URL = "jdbc:hsqldb:file:sql/testdb;shutdown=true;hsqldb.default_table_type=cached";
	private String DB_USERNAME = "sa";
	private String DB_PASSWORD = "";

	public static DBManager getInstance() {
		if(instance == null)
			instance = new DBManager();
		return instance;
	}

	public Connection connect() {
		Connection c = null;
		try {
			Class.forName(DB_DRIVER);
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
		try {
			c = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
			c.setAutoCommit(false);
		} catch (SQLException e) {
			e.printStackTrace();
			System.exit(0);
		}

		return c;
	}

	public void shutdown() throws Exception {
		Connection c = getInstance().connect();
		Statement s = c.createStatement();
		s.execute("SHUTDOWN");
		c.close();
	}

}
