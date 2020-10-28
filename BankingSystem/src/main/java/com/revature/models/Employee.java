package com.revature.models;

public class Employee extends User{

	//Variables
	
	//Constructors
	public Employee(String username, String password) {
		super(username, password);
		// TODO Auto-generated constructor stub
	}

	//Methods
	public void approveAcc(Customer customer) {
		
	}
	
	public void rejectAcc(Customer customer) {
		
	}

	public void viewAcc(Customer customer) {
		//either syso or a different view model
		System.out.println(customer.getBalance());
	}
}
