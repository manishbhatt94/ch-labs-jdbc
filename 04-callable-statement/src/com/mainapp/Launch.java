package com.mainapp;

import java.sql.Connection;
import java.sql.DriverManager;

public class Launch {

	public static void main(String[] args) {
		printJvmInfo();
		runDemo();
	}

	private static void runDemo() {

		try {
			// Load & Register MySQL driver:
			// (Note: Below line throws ClassNotFoundException checked exception)
			// Class.forName("com.mysql.cj.jdbc.Driver"); // <- This is optional now.
			// Manually loading the Driver is optional since JDBC Specification version 4.0+
			// which was supported starting Java SE 6 (in year 2003) onwards.

			String url = "jdbc:mysql://localhost:3306";
			String username = "root";
			String password = "manish";

			// (Note: Below line throws SQLException checked exception)
			Connection connection = DriverManager.getConnection(url, username, password);
			// <- DriverManager.getConnection is a static factory method which gives
			// an object of an implementation class of the Connection interface.
			// Here we see the FACTORY DESIGN PATTERN being used!

			System.out.println(connection); // Prints: com.mysql.cj.jdbc.ConnectionImpl@2f943d71
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private static void printJvmInfo() {
		/**
		 * Prints something like this:
		 *
		 * Java Version: 1.8.0_482 Java Vendor: Temurin Java Home:
		 * C:\Users\Manish\AppData\Local\javm\jdk\temurin@8.0.482\jre
		 */
		System.out.println("Java Version: " + System.getProperty("java.version"));
		System.out.println("Java Vendor:  " + System.getProperty("java.vendor"));
		System.out.println("Java Home:    " + System.getProperty("java.home"));
		System.out.println();
	}

}
