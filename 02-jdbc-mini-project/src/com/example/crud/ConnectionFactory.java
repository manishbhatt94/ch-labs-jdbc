package com.example.crud;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionFactory {

	private static final String DB_URL = "jdbc:mysql://localhost:3306/ch_labs_jdbc_01";
	private static final String DB_USER = "root";
	private static final String DB_PASSWORD = "manish";

	public static Connection getConnection() {
		Connection connection = null;
		try {
			connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return connection;
	}

	public static void close(AutoCloseable resource) {
		if (resource != null) {
			try {
				resource.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
