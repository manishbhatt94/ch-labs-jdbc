package com.mainapp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class Launch {

	public static void main(String[] args) {
		batchProcessingDemo();
	}

	private static void batchProcessingDemo() {

		System.out.println("\n### ====== BATCH PROCESSING DEMO ====== ###");
		Scanner scanner = new Scanner(System.in);

		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {

			String url = "jdbc:mysql://localhost:3306/ch_labs_jdbc_01";
			String username = "root";
			String password = "manish";

			connection = DriverManager.getConnection(url, username, password);

			connection.setAutoCommit(false);

			String sql = "UPDATE employee SET salary = ? WHERE username = ?;";
			preparedStatement = connection.prepareStatement(sql);

			System.out.print("\nEnter employee username (for salary updation): ");
			String inputUsername = scanner.nextLine();

			int ops = 3;

			while (ops > 0) {
				System.out.print("\nEnter new salary for employee [" + inputUsername + "]: ");
				int inputSalary = scanner.nextInt();

				System.out.println("Employee: [" + inputUsername + "] -- Update salary to: [" + inputSalary + "].");

				preparedStatement.setInt(1, inputSalary);
				preparedStatement.setString(2, inputUsername);

//				preparedStatement.addBatch();
//				preparedStatement.executeBatch();

				preparedStatement.executeUpdate();

				ops -= 1;
			}

			connection.commit();
			System.out.println("Committing DB updates.");

			// Reset auto-commit to true, after committing DB updates.
			connection.setAutoCommit(true);

		} catch (SQLException e) {
			e.printStackTrace();

			try {
				System.out.println("Rolling back DB updates.");
				connection.rollback();

				// Reset auto-commit to true, after rolling-back DB updates.
				connection.setAutoCommit(true);
			} catch (SQLException e1) {
				e1.printStackTrace();
			}

		} finally {
			scanner.close();
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}
