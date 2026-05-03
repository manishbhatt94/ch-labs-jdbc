package com.mainapp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Launch {

	public static void main(String[] args) {
		System.out.println("\n########## RESULTSET VS ROWSET DEMO ##########\n");
		resultSetOnlineDataManipulationDemo();
		rowSetOfflineDataManipulationDemo();
	}

	private static void resultSetOnlineDataManipulationDemo() {

		System.out.println("\n====== Demo of: ResultSet interface ======\n");

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
				System.out.printf("Employee Record: #%d [%s / %s].%n", sn, username, fullname);

				if (username.equalsIgnoreCase("kapu123")) {
					System.out.printf("Deleting -- Employee Record: #%d.%n", sn);
					resultSet.deleteRow();
				} else {
					System.out.printf("Skipping ahead -- Employee Record: #%d.%n", sn);
				}
			}

			System.out.println();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ConnectionFactory.close(resultSet);
			ConnectionFactory.close(preparedStatement);
			ConnectionFactory.close(connection);
		}
	}

	private static void rowSetOfflineDataManipulationDemo() {

		System.out.println("\n====== Demo of: RowSet interface ======\n");

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {

			connection = ConnectionFactory.getConnection();

			String sql = "SELECT * FROM employee;";
			preparedStatement = connection.prepareStatement(sql);

			resultSet = preparedStatement.executeQuery();

			System.out.println();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ConnectionFactory.close(resultSet);
			ConnectionFactory.close(preparedStatement);
			ConnectionFactory.close(connection);
		}
	}

}
