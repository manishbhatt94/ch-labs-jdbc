package com.example.crud;

import java.sql.Connection;
import java.sql.PreparedStatement;
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
		PreparedStatement preparedStatement = null;
		try {
			connection = ConnectionFactory.getConnection();
			// @formatter:off
			String sql = "INSERT INTO employee (username, password, fullname, address, salary)\n"
					+ " VALUES (?, ?, ?, ?, ?);";
			// @formatter:on
			preparedStatement = connection.prepareStatement(sql); // <- Parameterized SQL string compiled here.

			preparedStatement.setString(1, employeeDTO.getUsername());
			preparedStatement.setString(2, employeeDTO.getPassword());
			preparedStatement.setString(3, employeeDTO.getFullname());
			preparedStatement.setString(4, employeeDTO.getAddress());
			preparedStatement.setInt(5, employeeDTO.getSalary());

			preparedStatement.executeUpdate();
			System.out.println("DATA INSERTED");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionFactory.close(preparedStatement);
			ConnectionFactory.close(connection);
		}
	}

	public void read(String username, String password) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			connection = ConnectionFactory.getConnection();
			// @formatter:off
			String sql = "SELECT * FROM employee WHERE username = ? AND password = ?;";
			System.out.println("Running SQL:--> " + sql);

			/* Sample Input For SQL Injection: */
			// username: raju123
			// password: notsure' OR 'abc' = 'abc

			// @formatter:on
			preparedStatement = connection.prepareStatement(sql);

			preparedStatement.setString(1, username);
			preparedStatement.setString(2, password);

			resultSet = preparedStatement.executeQuery();

			readResult(resultSet);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionFactory.close(resultSet);
			ConnectionFactory.close(preparedStatement);
			ConnectionFactory.close(connection);
		}
	}

	private void readResult(ResultSet resultSet) throws SQLException {
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
	}

	public void update(String username, int salary) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			connection = ConnectionFactory.getConnection();
			// @formatter:off
			String sql = "UPDATE employee SET salary = ? WHERE username = ?;";
			// @formatter:on
			preparedStatement = connection.prepareStatement(sql);

			preparedStatement.setInt(1, salary);
			preparedStatement.setString(2, username);

			int affectedRowCount = preparedStatement.executeUpdate();
			if (affectedRowCount > 0) {
				System.out.println("DATA UPDATED");
			} else {
				System.out.println("USER NOT FOUND");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionFactory.close(preparedStatement);
			ConnectionFactory.close(connection);
		}
	}

	public void delete(String username) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			connection = ConnectionFactory.getConnection();
			// @formatter:off
			String sql = "DELETE FROM employee WHERE username = ?;";
			// @formatter:on
			preparedStatement = connection.prepareStatement(sql);

			preparedStatement.setString(1, username);

			int affectedRowCount = preparedStatement.executeUpdate();
			if (affectedRowCount > 0) {
				System.out.println("DATA DELETED");
			} else {
				System.out.println("USER NOT FOUND");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionFactory.close(preparedStatement);
			ConnectionFactory.close(connection);
		}
	}

	public void test(String sql) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			connection = ConnectionFactory.getConnection();
			preparedStatement = connection.prepareStatement(sql);
			System.out.println("Compiled unknown dynamic Query: " + sql);

			boolean hasResultSet = preparedStatement.execute();
			System.out.println("Executed this Query using execute() method of PreparedStatement interface.");

			if (hasResultSet) {
				System.out.println("This Query produces a result set.");
				resultSet = preparedStatement.getResultSet();
				readResult(resultSet);
				System.out.println("Finished reading this Query's result set.");
			} else {
				System.out.println("This Query did not produce any results.");
				int updateCount = preparedStatement.getUpdateCount();
				System.out.println("This Query performed an update count of " + updateCount + ".");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionFactory.close(resultSet);
			ConnectionFactory.close(preparedStatement);
			ConnectionFactory.close(connection);
		}
	}

}
