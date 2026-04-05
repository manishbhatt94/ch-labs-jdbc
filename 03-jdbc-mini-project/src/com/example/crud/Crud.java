package com.example.crud;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.example.dto.EmployeeDTO;

public class Crud {

	public Crud() {
		createTable();
	}

	/**
	 * Three (3) methods in Statement interface for executing SQL queries:<br>
	 *
	 * <ol>
	 *
	 * <li>executeQuery:<br>
	 * <ul>
	 * <li>Signature:
	 * {@code ResultSet executeQuery(String sql) throws SQLException;}</li>
	 * <li>Executes the given SQL statement, which returns a single
	 * <code>ResultSet</code> object.</li>
	 * </ul>
	 * </li>
	 *
	 * <li>executeUpdate:<br>
	 * <ul>
	 * <li>Signature:
	 * {@code int executeUpdate(String sql) throws SQLException;}</li>
	 * <li>Executes the given SQL statement, which may be an <code>INSERT</code>,
	 * <code>UPDATE</code>, or <code>DELETE</code> statement or an SQL statement
	 * that returns nothing, such as an SQL DDL statement.</li>
	 * <li>Returns the count of rows affected for DML queries, and zero for queries
	 * that return nothing & don't affect any rows like DDL queries.</li>
	 * </ul>
	 * </li>
	 *
	 * <li>execute:<br>
	 * <ul>
	 * <li>Signature: {@code boolean execute(String sql) throws SQLException;}</li>
	 * <li>For the case when you are dynamically executing an unknown SQL string.
	 * And you don't know whether the query returns result(s) or if it returns rows
	 * affected count or if it returns nothing.</li>
	 * <li>Returns <code>true</code> if the first result is a <code>ResultSet</code>
	 * object; <code>false</code> if it is an update count or there are no
	 * results</li>
	 * </ul>
	 * </li>
	 *
	 * </ol>
	 */

	private void createTable() {
		// Columns: SN, USER, PASSWORD, FULLNAME, ADDRESS, SALARY
		Connection connection = null;
		Statement statement = null;
		try {
			// WATER LEAK? -> PANI WASTE
			// MEMORY LEAK? -> NO USE
			connection = ConnectionFactory.getConnection(); // CONNECTION MANAGE
			// @formatter:off
			String sql = "CREATE TABLE IF NOT EXISTS employee (\n"
					+ "		sn INT AUTO_INCREMENT PRIMARY KEY,\n"
					+ "		username VARCHAR(30) NOT NULL UNIQUE,\n"
					+ "		password VARCHAR(30),\n"
					+ "		fullname VARCHAR(100),\n"
					+ "		address VARCHAR(200),\n"
					+ "		salary INT\n"
					+ ");";
			// @formatter:on
			statement = connection.createStatement();
			statement.executeUpdate(sql);
			System.out.println("TABLE CREATED");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionFactory.close(statement);
			ConnectionFactory.close(connection);
		}
	}

	public void insert(EmployeeDTO employeeDTO) {
		Connection connection = null;
		Statement statement = null;
		try {
			connection = ConnectionFactory.getConnection();
			// @formatter:off
			String sql = "INSERT INTO employee (\n"
					+ "username, password, fullname, address, salary\n"
					+ ") VALUES (\n"
					+ "'" + employeeDTO.getUsername() + "',\n"
					+ "'" + employeeDTO.getPassword() + "',\n"
					+ "'" + employeeDTO.getFullname() + "',\n"
					+ "'" + employeeDTO.getAddress() + "',\n"
					+ "" + employeeDTO.getSalary() + "\n"
					+ ");";
			// @formatter:on
			statement = connection.createStatement();
			statement.executeUpdate(sql);
			System.out.println("DATA INSERTED");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionFactory.close(statement);
			ConnectionFactory.close(connection);
		}
	}

	public void read(String username, String password) {
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		try {
			connection = ConnectionFactory.getConnection();
			// @formatter:off
			String sql = "SELECT * FROM employee WHERE username = '" + username + "' AND password = '" + password + "';";
			System.out.println("Running SQL:--> " + sql);

			/* Sample Input For SQL Injection: */
			// username: raju123
			// password: notsure' OR 'abc' = 'abc

			// @formatter:on
			statement = connection.createStatement();
			resultSet = statement.executeQuery(sql);

			if (resultSet.next()) {
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
			} else {
				System.out.println("USER NOT FOUND");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionFactory.close(resultSet);
			ConnectionFactory.close(statement);
			ConnectionFactory.close(connection);
		}
	}

	public void update(String username, int salary) {
		Connection connection = null;
		Statement statement = null;
		try {
			connection = ConnectionFactory.getConnection();
			// @formatter:off
			String sql = "UPDATE employee SET salary = " + salary + " WHERE username ='" + username + "';";
			// @formatter:on
			statement = connection.createStatement();
			int affectedRowCount = statement.executeUpdate(sql);
			if (affectedRowCount > 0) {
				System.out.println("DATA UPDATED");
			} else {
				System.out.println("USER NOT FOUND");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionFactory.close(statement);
			ConnectionFactory.close(connection);
		}
	}

	public void delete(String username) {
		Connection connection = null;
		Statement statement = null;
		try {
			connection = ConnectionFactory.getConnection();
			// @formatter:off
			String sql = "DELETE FROM employee WHERE username ='" + username + "';";
			// @formatter:on
			statement = connection.createStatement();
			int affectedRowCount = statement.executeUpdate(sql);
			if (affectedRowCount > 0) {
				System.out.println("DATA DELETED");
			} else {
				System.out.println("USER NOT FOUND");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionFactory.close(statement);
			ConnectionFactory.close(connection);
		}
	}

}
