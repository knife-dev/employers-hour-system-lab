package main.java;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import main.java.managers.DBManager;

public class TableManager {

	public static void createUserTable() {
		System.out.println("> createUserTable: start.");

		Connection connection = DBManager.getInstance().connect();
		
		try {
			String sqlCreate = "CREATE TABLE users"
			+ "  (id 	   BIGINT IDENTITY PRIMARY KEY,"
			+ "   email    VARCHAR(256) NOT NULL,"
			+ "   password VARCHAR(10) NOT NULL,"
			+ "   userType VARCHAR(256))";

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

}
