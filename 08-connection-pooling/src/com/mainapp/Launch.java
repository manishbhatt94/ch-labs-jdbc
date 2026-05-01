package com.mainapp;

import java.io.FileInputStream;
import java.sql.Connection;
import java.util.Properties;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class Launch {

	private static final String CONFIG_FILE_PATH = "myconfig.properties";

	public static void main(String[] args) {

		HikariDataSource hikariDataSource = null;

		try {

			// Load DB configuration in Properties object
			System.out.println("Reading configuration properties from .properties file...");
			FileInputStream fileInputStream = new FileInputStream(CONFIG_FILE_PATH);
			Properties properties = new Properties();
			properties.load(fileInputStream);
			System.out.println("Loaded Properties object -- " + properties);
			System.out.println();

			System.out.println("Creating HikariConfig object using loaded configuration properties...");
			// Provide DB configuration to HikariConfig object from Properties object
			HikariConfig hikariConfig = new HikariConfig();
			hikariConfig.setJdbcUrl(properties.getProperty("mysql.url"));
			hikariConfig.setUsername(properties.getProperty("mysql.user"));
			hikariConfig.setPassword(properties.getProperty("mysql.password"));
			System.out.println("Added configuration to HikariConfig object -- " + hikariConfig);
			System.out.println();

			System.out.println("Creating HikarDataSource object using the HikariConfig object...");
			hikariDataSource = new HikariDataSource(hikariConfig); // Pool ready.
			System.out.println("Constructed (with config) new HikariDataSource object -- " + hikariDataSource);
			System.out.println();

			System.out.println("Getting a Connection object from the pool managed by HikariDataSource...");
			Connection connection = hikariDataSource.getConnection(); // Get connection from the pool.

			System.out.println("Connection object: " + connection);
			System.out.println("Connection with database: " + connection.getCatalog());
			System.out.println();

			connection.close(); // Connection goes back to the pool.
			System.out.println("Returned Connection object back to the pool.");

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (hikariDataSource != null) {
				// HikariDataSource's close() method
				// --> Closes the complete pool.
				hikariDataSource.close();
			}
		}

	}

}
