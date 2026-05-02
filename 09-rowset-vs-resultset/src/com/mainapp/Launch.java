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
