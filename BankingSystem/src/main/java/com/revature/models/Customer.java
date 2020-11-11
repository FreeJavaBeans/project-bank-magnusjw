package com.revature.models;

import java.util.Set;

public class Customer extends User{
	

	public Customer() {
		super();
	}
	public Customer(String username, String password, String firstName, String lastName) {
		super();
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
	}
	
	@Override
	public String toString() {
		return "Customer [customerId=" + userId + ", username=" + username + ", password=" + password
				+ ", firstName=" + firstName + ", lastName=" + lastName + "]";
	}
	
	public int getCustomerId() {
		return userId;
	}
	public void setCustomerId(int userId) {
		this.userId = userId;
	}
}