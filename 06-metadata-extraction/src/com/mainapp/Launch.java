package com.mainapp;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class Launch {

	public static void main(String[] args) {
		System.out.println("\n########## JDBC META-DATA EXTRACTION DEMO ##########\n");
		databaseMetaDataDemo();
		resultSetMetaDataDemo();
	}

	private static void databaseMetaDataDemo() {

		System.out.println("\n====== Demo of: DatabaseMetaData interface ======\n");

		Connection connection = null;

		try {

			connection = ConnectionFactory.getConnection();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ConnectionFactory.close(connection);
		}
	}

	private static void resultSetMetaDataDemo() {

		System.out.println("\n====== Demo of: ResultSetMetaData interface ======\n");

		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {

			connection = ConnectionFactory.getConnection();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ConnectionFactory.close(preparedStatement);
			ConnectionFactory.close(connection);
		}
	}

}
