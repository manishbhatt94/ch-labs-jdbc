package com.mainapp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Launch {

	public static void main(String[] args) {

		// String dbUrl = "jdbc:mysql://localhost:3306/ch_labs_jdbc_01";
		String dbUrl = "jdbc:mysql://localhost:3306/jfsseptkart";
		String dbUser = "root";
		String dbPassword = "manish";

		Connection connection = null;

		while (true) {
			// =========== LOOP STARTS ===========

			try {

				connection = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
				System.out.println("Connection object: " + connection);
				System.out.println("Connection with database: " + connection.getCatalog());
				System.out.println();

				Thread.sleep(2000);

			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if (connection != null) {
					try {
						connection.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			}

			// =========== LOOP ENDS ===========
		}

	}

}
