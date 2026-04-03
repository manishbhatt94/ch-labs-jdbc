package com.example.logic;

import java.util.Scanner;

import com.example.crud.Crud;
import com.example.dto.EmployeeDTO;

public class Logic {

	private final int INSERT_DATA = 1;
	private final int READ_DATA = 2;
	private final int UPDATE_DATA = 3;
	private final int DELETE_DATA = 4;
	private final int EXIT = 5;
	private static final int MAX_ATTEMPTS = 3;
	private String username;
	private String password;
	private String fullname;
	private String address;
	private int salary;
	private Crud crud;

	public Logic() {
		crud = new Crud();
	}

	public void doStart() {

		Scanner scanner = new Scanner(System.in);
		int attempt = 0;

		while (true) {
			System.out.println("\n=========MENU=========");
			System.out.println("Press-" + INSERT_DATA + " : INSERT DATA");
			System.out.println("Press-" + READ_DATA + " : READ DATA");
			System.out.println("Press-" + UPDATE_DATA + " : UPDATE DATA");
			System.out.println("Press-" + DELETE_DATA + " : DELETE DATA");
			System.out.println("Press-" + EXIT + " : EXIT\n");
			System.out.print("Enter Your Choice: ");
			int choice = 0;

			try {
				choice = scanner.nextInt();
			} catch (Exception e) { // <- non-integer input entered.
				System.out.print("Invalid Input! Please take a number from 1 to 5.\n");
				scanner.nextLine(); // <- Clear input buffer.
				attempt++;
				if (attempt >= MAX_ATTEMPTS) {
					System.out.print("You have reached the limit");
					scanner.close();
					return;
				}
				continue;
			}
			if (choice < 1 || choice > 5) { // <- Range of choice entered is invalid.
				System.out.print("Invalid Input! Please take a value from 1 to 5.\n");
				attempt++;
				if (attempt >= MAX_ATTEMPTS) {
					System.out.print("You have reached the limit");
					scanner.close();
					return;
				}
			}

			// Here, choice is an integer in the range [1, 5].
			switch (choice) {

			case INSERT_DATA:
				System.out.println("*************INSERT DATA************");
				System.out.println("ENTER USERNAME");
				username = scanner.next();
				System.out.println("ENTER PASSWORD");
				password = scanner.next();
				scanner.nextLine();
				System.out.println("ENTER FULLNAME");
				fullname = scanner.nextLine();
				System.out.println("ENTER ADDRESS");
				address = scanner.nextLine();
				System.out.println("ENTER SALARY");
				salary = scanner.nextInt();
				EmployeeDTO employeeDTO = new EmployeeDTO(username, password, fullname, address, salary);
				crud.insert(employeeDTO);
				break;

			case READ_DATA:
				System.out.println("******************READ DATA********************");
				System.out.println("ENTER USERNAME");
				username = scanner.next();
				System.out.println("ENTER PASSWORD");
				password = scanner.next();
				crud.read(username, password);
				break;

			case UPDATE_DATA:
				System.out.println("*********************UPDATE DATA*****************");
				System.out.println("ENTER USERNAME");
				username = scanner.next();
				System.out.println("ENTER SALARY");
				salary = scanner.nextInt();
				crud.update(username, salary);
				break;

			case DELETE_DATA:
				System.out.println("*********************DELETE DATA*****************");
				System.out.println("ENTER USERNAME");
				username = scanner.next();
				crud.delete(username);
				break;

			case EXIT:
				System.out.println("EXIT");
				scanner.close();
				return;

			}
		}

	}

}
