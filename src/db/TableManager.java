package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TableManager {
	/**
	 * Clase Temporal para crear la DB
	 */

	public static void createUserTable() {
		System.out.println("> createUserTable: start.");

		Connection connection = DBManager.getInstance().connect();
		
		try {
			String sqlCreate = "CREATE TABLE users"
			+ "  (id 	   BIGINT IDENTITY PRIMARY KEY,"
			+ "   email    VARCHAR(256) NOT NULL,"
			+ "   password VARCHAR(10) NOT NULL )";

			PreparedStatement ps = null;
			ps = connection.prepareStatement( sqlCreate );
			boolean created = ps.execute();
			connection.commit();
			System.out.println("> createUserTable: created? " + created);
		} catch (SQLException e) {
			try {
				connection.rollback();
				e.printStackTrace();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println("> createUserTable: end.");

	}

	public static void createTaskTable() {
		System.out.println("> createTaskTable: start.");

		Connection connection = DBManager.getInstance().connect();
		
		try {
			String sqlCreate = "CREATE TABLE tasks"
			+ "  (id		BIGINT IDENTITY PRIMARY KEY,"
			+ "   userId	BIGINT NOT NULL,"
			+ "   hours		FLOAT NOT NULL,"
			+ "   date		VARCHAR(256),"
			+ "	  FOREIGN KEY (userId) REFERENCES users(id) ON DELETE SET NULL ON UPDATE CASCADE)";

			PreparedStatement ps = null;
			ps = connection.prepareStatement( sqlCreate );
			boolean created = ps.execute();
			connection.commit();
			System.out.println("> createTaskTable: created? " + created);
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println("> createTaskTable: end.");
	}

}

// CREATE TABLE child(c1 INTEGER, c2 VARCHAR, FOREIGN KEY (c1, c2) REFERENCES parent(p1, p2));


