package com.mainapp;

public class Launch {

	public static void main(String[] args) {
		printJvmInfo();
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
	}

}
