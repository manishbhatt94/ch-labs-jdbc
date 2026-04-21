package com.mainapp;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Launch {

	public static void main(String[] args) {
		System.out.println("\n########## JDBC META-DATA EXTRACTION DEMO ##########\n");
		databaseMetaDataDemo();
		resultSetMetaDataDemo();
	}

	private static void databaseMetaDataDemo() {

		System.out.println("\n====== Demo of: DatabaseMetaData interface ======\n");

		Connection connection = null;
		ResultSet resultSet = null;

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

			/**
			 * Retrieves the catalog names available in this database. The results are
			 * ordered by catalog name (<code>TABLE_CAT</code>).
			 */
			resultSet = metaData.getCatalogs();
			System.out.println("Database Catalogs:");
			while (resultSet.next()) {
				System.out.println("• " + resultSet.getString("TABLE_CAT"));
			}
			System.out.println();

			/**
			 * Retrieves the schema names available in this database. The results are
			 * ordered by <code>TABLE_CATALOG</code> and <code>TABLE_SCHEM</code>. *
			 *
			 * <P>
			 * The schema columns are:
			 * <OL>
			 * <LI><B>TABLE_SCHEM</B> String {@code =>} schema name</LI>
			 * <LI><B>TABLE_CATALOG</B> String {@code =>} catalog name (may be
			 * <code>null</code>)</LI>
			 * </OL>
			 */
			resultSet = metaData.getSchemas();
			System.out.println("Database Schemas:");
			while (resultSet.next()) {
				System.out.print("• ");
				System.out.print("TABLE_SCHEM [" + resultSet.getString("TABLE_SCHEM") + "]; ");
				System.out.print("TABLE_CATALOG [" + resultSet.getString("TABLE_CATALOG") + "]; ");
				System.out.println();
			}
			System.out.println();

			/**
			 * Retrieves a description of the tables available in the given catalog. Only
			 * table descriptions matching the catalog, schema, table name and type criteria
			 * are returned. They are ordered by <code>TABLE_TYPE</code>,
			 * <code>TABLE_CAT</code>, <code>TABLE_SCHEM</code> and <code>TABLE_NAME</code>.
			 * <P>
			 * Each table description has the following columns:
			 * <OL>
			 * <LI><B>TABLE_CAT</B> String {@code =>} table catalog (may be
			 * <code>null</code>)</LI>
			 * <LI><B>TABLE_SCHEM</B> String {@code =>} table schema (may be
			 * <code>null</code>)</LI>
			 * <LI><B>TABLE_NAME</B> String {@code =>} table name</LI>
			 * <LI><B>TABLE_TYPE</B> String {@code =>} table type. Typical types are
			 * "TABLE", "VIEW", "SYSTEM TABLE", "GLOBAL TEMPORARY", "LOCAL TEMPORARY",
			 * "ALIAS", "SYNONYM".</LI>
			 * <LI>Other columns...</LI>
			 * </OL>
			 */
			// 3rd Argument (tableNamePattern) - "product%" - All tables with name starting
			// with 'product':
			resultSet = metaData.getTables("jfsseptkart", null, "product%", null);
			// 3rd Argument (tableNamePattern) - null - All tables:
			// resultSet = metaData.getTables("jfsseptkart", null, null, null);
			System.out.println("Database Catalogs:");
			while (resultSet.next()) {
				System.out.print("• ");
				System.out.print("TABLE_CAT [" + resultSet.getString("TABLE_CAT") + "]; ");
				System.out.print("TABLE_SCHEM [" + resultSet.getString("TABLE_SCHEM") + "]; ");
				System.out.print("TABLE_NAME [" + resultSet.getString("TABLE_NAME") + "]; ");
				System.out.print("TABLE_TYPE [" + resultSet.getString("TABLE_TYPE") + "]; ");
				System.out.println();
			}
			System.out.println();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ConnectionFactory.close(resultSet);
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
