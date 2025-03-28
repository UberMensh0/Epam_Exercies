package com.epam.rd.autotask;

public class Employee {
	private String fullName;

	public Employee(String fullName) {
		this.fullName = fullName;
	}
	public String[] splitName() {
		// split as fname lname tuple
		return fullName.split(" ");
	}
}