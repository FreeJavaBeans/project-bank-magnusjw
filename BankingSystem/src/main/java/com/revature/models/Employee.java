package com.revature.models;

public class Employee extends User{

	
	private int employeeId;

	public Employee() {
		super();
	}

	public int getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}

}