package com.mainapp;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Launch {

	public static void main(String[] args) {
		callableStatementDemo();
		transactionHandlingDemo();
	}

	private static void transactionHandlingDemo() {

		System.out.println("\n### ====== TRANSACTION HANDLING DEMO ====== ###");
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

	private static void callableStatementDemo() {

		System.out.println("\n### ====== CALLABLE STATEMENT DEMO ====== ###");

		Connection connection = null;
		CallableStatement callableStatement = null;
		ResultSet resultSet = null;

		try {

			String url = "jdbc:mysql://localhost:3306/ch_labs_jdbc_01";
			String username = "root";
			String password = "manish";

			connection = DriverManager.getConnection(url, username, password);

			// MySQL command to invoke the stored procedure using CALL statement:
			// CALL employees_nameStartsWith_salaryGte('ka', 12000);

			//
			// Prepare a call to the stored procedure 'employees_nameStartsWith_salaryGte'
			// with two parameters
			//
			// Notice the use of JDBC-escape syntax ({CALL ...})
			//

			// Notice that you have to use JDBC escape syntax - i.e. the curly brace pair,
			// and that the parentheses surrounding the parameter placeholders are not
			// optional:

			callableStatement = connection
					.prepareCall("{CALL employees_nameStartsWith_salaryGte(?, ?)}");

			callableStatement.setString(1, "ka");
			callableStatement.setInt(2, 12000);

			resultSet = callableStatement.executeQuery();

			while (resultSet.next()) {
				printRecord(resultSet);
				System.out.println();
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (resultSet != null) {
					resultSet.close();
				}
				if (callableStatement != null) {
					callableStatement.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

	private static void printRecord(ResultSet resultSet) throws SQLException {
		int snValue = resultSet.getInt("sn");
		String usernameValue = resultSet.getString("username");
		String passwordValue = resultSet.getString("password");
		String fullnameValue = resultSet.getString("fullname");
		String addressValue = resultSet.getString("address");
		int salaryValue = resultSet.getInt("salary");
		System.out.println("sn: " + snValue);
		System.out.println("username: " + usernameValue);
		System.out.println("password: " + passwordValue);
		System.out.println("fullname: " + fullnameValue);
		System.out.println("address: " + addressValue);
		System.out.println("salary: " + salaryValue);
	}

}
