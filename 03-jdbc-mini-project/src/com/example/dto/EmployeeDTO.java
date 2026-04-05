package com.example.dto;

public class EmployeeDTO {

	private String username;
	private String password;
	private String fullname;
	private String address;
	private int salary;

	public EmployeeDTO() {
		super();
	}

	public EmployeeDTO(String username, String password, String fullname, String address, int salary) {
		super();
		this.username = username;
		this.password = password;
		this.fullname = fullname;
		this.address = address;
		this.salary = salary;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getSalary() {
		return salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}

	@Override
	public String toString() {
		return "EmployeeDTO [username=" + username + ", password=" + password + ", fullname=" + fullname + ", address="
				+ address + ", salary=" + salary + "]";
	}

}
