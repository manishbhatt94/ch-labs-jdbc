package com.mainapp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Scanner;

public class Launch {

	private static final String DB_URL = "jdbc:mysql://localhost:3306/ch_labs_jdbc_01";
	private static final String DB_USERNAME = "root";
	private static final String DB_PASSWORD = "manish";

	public static void main(String[] args) {
		batchProcessingDemo();
	}

	private static void batchProcessingDemo() {

		System.out.println("\n### ====== BATCH PROCESSING DEMO ====== ###");
		Scanner scanner = new Scanner(System.in);

		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {

			connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);

			connection.setAutoCommit(false);

			String sql = "INSERT INTO employee (username, password, fullname, address, salary)\n"
					+ " VALUES (?, ?, ?, ?, ?);";
			preparedStatement = connection.prepareStatement(sql);

			String inputMore = "Y";
			int recordsInput = 0;

			while (inputMore.equalsIgnoreCase("Y")) {
				System.out.println("\n## Enter data for new employee record ##");
				System.out.print("ENTER USERNAME: ");
				String username = scanner.next();
				System.out.print("ENTER PASSWORD: ");
				String password = scanner.next();
				scanner.nextLine();
				System.out.print("ENTER FULLNAME: ");
				String fullname = scanner.nextLine();
				System.out.print("ENTER ADDRESS: ");
				String address = scanner.nextLine();
				System.out.print("ENTER SALARY: ");
				int salary = scanner.nextInt();

				recordsInput += 1;

				preparedStatement.setString(1, username);
				preparedStatement.setString(2, password);
				preparedStatement.setString(3, fullname);
				preparedStatement.setString(4, address);
				preparedStatement.setInt(5, salary);

				preparedStatement.addBatch();

				System.out.print("\nDo you want to input more records? (Y/N): ");
				inputMore = scanner.next()
						.trim()
						.toUpperCase();

			}

			System.out.println("\nRecords input: " + recordsInput);
			System.out.println("\nExecuting batch containing " + recordsInput + " records...");

			int[] updateCounts = preparedStatement.executeBatch();
			System.out.println("\nExecuted batch!\nUpdate Counts: " + Arrays.toString(updateCounts));

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
