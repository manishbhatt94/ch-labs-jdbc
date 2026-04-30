package com.mainapp;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class Launch {

	/**
	 * Sample contents of config file:
	 *
	 * <pre>
	 * # mysql.url=jdbc:mysql://localhost:3306/ch_labs_jdbc_01
	 * mysql.url=jdbc:mysql://localhost:3306/jfsseptkart
	 * mysql.user=root
	 * mysql.password=manish
	 * </pre>
	 */
	// private static final String CONFIG_FILE_PATH =
	// "C:\\Users\\Manish\\Dev\\dummy-files-dump\\myconfig.properties";
	private static final String CONFIG_FILE_PATH = "myconfig.properties"; // No full path required when file is placed
																			// directly inside the project directory.

	public static void main(String[] args) {

		Connection connection = null;
		FileInputStream fileInputStream = null;

		while (true) {
			// =========== LOOP STARTS ===========

			try {

				fileInputStream = new FileInputStream(CONFIG_FILE_PATH);

				Properties properties = new Properties();
				properties.load(fileInputStream);

				String dbUrl = properties.getProperty("mysql.url");
				String dbUser = properties.getProperty("mysql.user");
				String dbPassword = properties.getProperty("mysql.password");

				connection = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
				System.out.println("Connection object: " + connection);
				System.out.println("Connection with database: " + connection.getCatalog());
				System.out.println();

				Thread.sleep(2000);

			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					if (fileInputStream != null) {
						fileInputStream.close();
					}
					if (connection != null) {
						connection.close();
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

			// =========== LOOP ENDS ===========
		}

	}

}
