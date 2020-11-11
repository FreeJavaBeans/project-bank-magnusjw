package com.revature.models;

public class Employee extends User{

	public Employee() {
		super();
	}

	public int getEmployeeId() {
		return userId;
	}
	public void setEmployeeId(int userId) {
		this.userId = userId;
	}

}