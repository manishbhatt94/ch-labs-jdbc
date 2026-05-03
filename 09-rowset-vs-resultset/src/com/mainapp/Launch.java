package com.mainapp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.RowSetProvider;

public class Launch {

	public static void main(String[] args) {
		System.out.println("\n########## RESULTSET VS ROWSET DEMO ##########\n");
		resultSetOnlineDataManipulationDemo();
		rowSetOfflineDataManipulationDemo();
	}

	private static void resultSetOnlineDataManipulationDemo() {

		System.out.println("\n\n====== Demo of: ResultSet interface ======\n");

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {

			connection = ConnectionFactory.getConnection();

			String sql = "SELECT * FROM employee;";

			/*
			 * To be allowed to update record(s) directly from ResultSet object, the
			 * ResultSet object must be obtained from a Statement / PreparedStatement that
			 * on creation specified ResultSet.CONCUR_UPDATABLE as the resultSetConcurrency.
			 *
			 * Otherwise, if we don't do above, and call any ResultSet method that updates a
			 * record, such as resultSet.deleteRow() etc., then we get the following MySQL
			 * driver exception is received:
			 *
			 * com.mysql.cj.jdbc.exceptions.NotUpdatable: Result Set not updatable. This
			 * result set must come from a statement that was created with a result set type
			 * of ResultSet.CONCUR_UPDATABLE, the query must select only one table, can not
			 * use functions and must select all primary keys from that table.
			 */

			// prepareStatement(...) Signature of Overloaded method used:
			// prepareStatement(String sql, int resultSetType, int resultSetConcurrency);
			preparedStatement = connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_UPDATABLE);

			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				int sn = resultSet.getInt("sn");
				String username = resultSet.getString("username");
				String fullname = resultSet.getString("fullname");
				System.out.printf("▶️ Employee Record: #%d [%s / %s].%n", sn, username, fullname);

				if (username.equalsIgnoreCase("kalu123")) {
					System.out.printf("\t☠️ Deleting -- Employee Record: #%d.%n", sn);
					resultSet.deleteRow();
					System.out.printf(
							"\tℹ️ Employee [%s] - Deleted from Database too. ResultSet does ONLINE data updates.%n",
							username);
				} else {
					System.out.printf("\t✅ Skipping ahead -- Employee Record: #%d.%n", sn);
				}
			}

			System.out.println("\nRe-print records from ResultSet object (after above data modification):\n");

			reprintData(resultSet);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ConnectionFactory.close(resultSet);
			ConnectionFactory.close(preparedStatement);
			ConnectionFactory.close(connection);
		}
	}

	private static void rowSetOfflineDataManipulationDemo() {

		System.out.println("\n\n====== Demo of: RowSet interface ======\n");

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		CachedRowSet cachedRowSet = null;

		try {

			connection = ConnectionFactory.getConnection();

			String sql = "SELECT * FROM employee;";
			preparedStatement = connection.prepareStatement(sql);

			resultSet = preparedStatement.executeQuery();

			cachedRowSet = RowSetProvider.newFactory()
					.createCachedRowSet();
			cachedRowSet.populate(resultSet); // Cache memory / Offline (not connected)

			while (cachedRowSet.next()) {
				int sn = cachedRowSet.getInt("sn");
				String username = cachedRowSet.getString("username");
				String fullname = cachedRowSet.getString("fullname");
				System.out.printf("▶️ Employee Record: #%d [%s / %s].%n", sn, username, fullname);

				if (username.equalsIgnoreCase("raut123")) {
					System.out.printf("\t☠️ Deleting -- Employee Record: #%d.%n", sn);
					cachedRowSet.deleteRow();
					System.out.printf("\tℹ️ Employee [%s] - Only deleted from RowSet, not from Database.%n", username);
				} else {
					System.out.printf("\t✅ Skipping ahead -- Employee Record: #%d.%n", sn);
				}
			}

			System.out.println(
					"\nRe-print records from RowSet (i.e. CachedRowSet) object (after above data modification):\n");
			reprintData(cachedRowSet);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ConnectionFactory.close(cachedRowSet);
			ConnectionFactory.close(resultSet);
			ConnectionFactory.close(preparedStatement);
			ConnectionFactory.close(connection);
		}
	}

	private static void reprintData(ResultSet resultSet) {

		if (resultSet == null) {
			System.out.println("reprintData: null value ResultSet received. Exiting.");
			return;
		}

		try {
			resultSet.beforeFirst();

			while (resultSet.next()) {
				int sn = resultSet.getInt("sn");
				String username = resultSet.getString("username");
				String fullname = resultSet.getString("fullname");
				System.out.printf("📌 Employee Record: #%d [%s / %s].%n", sn, username, fullname);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
