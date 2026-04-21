package com.mainapp;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
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

			// Get DatabaseMetaData reference object from connection object:
			/**
			 * Retrieves a DatabaseMetaData object that contains metadata about the database
			 * to which this Connection object represents a connection. The metadata
			 * includes information about the database's tables, its supported SQL grammar,
			 * its stored procedures, the capabilities of this connection, and so on.
			 */
			DatabaseMetaData metaData = connection.getMetaData(); // Factory Design Pattern.

			/**
			 * Retrieves the URL for this DBMS, or null if it cannot be generated.
			 */
			String jdbcUrl = metaData.getURL();
			System.out.println("JDBC URL: " + jdbcUrl + "\n");

			/**
			 * Retrieves the user name as known to this database. That is, it returns the
			 * database user name.
			 */
			String databaseUserName = metaData.getUserName();
			System.out.println("Database UserName: " + databaseUserName + "\n");

			/**
			 * Print Database Product Name and Version information:
			 */

			String databaseProductName = metaData.getDatabaseProductName();
			System.out.println("Database Product Name: " + databaseProductName + "\n");

			String databaseProductVersion = metaData.getDatabaseProductVersion();
			System.out.println("Database Product Version: " + databaseProductVersion + "\n");

			int databaseMajorVersion = metaData.getDatabaseMajorVersion();
			System.out.println("Database Major Version: " + databaseMajorVersion + "\n");

			int databaseMinorVersion = metaData.getDatabaseMinorVersion();
			System.out.println("Database Minor Version: " + databaseMinorVersion + "\n");

			/**
			 * Print JDBC Driver Product Name and Version information:
			 */

			String driverName = metaData.getDriverName();
			System.out.println("Driver Name: " + driverName + "\n");

			String driverVersion = metaData.getDriverVersion();
			System.out.println("Driver Version: " + driverVersion + "\n");

			int driverMajorVersion = metaData.getDriverMajorVersion();
			System.out.println("Driver Major Version: " + driverMajorVersion + "\n");

			int driverMinorVersion = metaData.getDriverMinorVersion();
			System.out.println("Driver Minor Version: " + driverMinorVersion + "\n");

			/**
			 * Retrieves the maximum number of characters this database allows in a user
			 * name. A result of zero means that there is no limit or the limit is not
			 * known.
			 */
			int maxUserNameLength = metaData.getMaxUserNameLength();
			System.out.println("Database Max UserName Length: " + maxUserNameLength + "\n");

			/**
			 * Retrieves the maximum number of columns this database allows in a table. A
			 * result of zero means that there is no limit or the limit is not known.
			 */
			int maxColumnsInTable = metaData.getMaxColumnsInTable();
			System.out.println("Database Max Columns in Table: " + maxColumnsInTable + "\n");

			/**
			 * Retrieves the maximum number of bytes this database allows in a single row. A
			 * result of zero means that there is no limit or the limit is not known.
			 */
			int maxRowSize = metaData.getMaxRowSize();
			System.out.println("Database Max Row Size (bytes): " + maxRowSize + "\n");

			/**
			 * Retrieves the maximum number of columns this database allows in a SELECT
			 * list. A result of zero means that there is no limit or the limit is not
			 * known.
			 */
			int maxColumnsInSelect = metaData.getMaxColumnsInSelect();
			System.out.println("Database Max Columns in Select: " + maxColumnsInSelect + "\n");

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
