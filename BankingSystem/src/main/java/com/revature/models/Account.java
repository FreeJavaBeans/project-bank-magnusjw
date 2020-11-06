package com.revature.models;

public class Account {

	//Variables
	private double balance;
	
	
	//Constructors
	public Account() {
		this(0.0); //Zero dollar account
	}
	
	public Account(double balance) {
		super();
		this.balance = balance;
	}
	
	//Getters and Setters
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
}

//interface -> service -> dao -> service -> interface

//interface = front end
//service = logic, figuring out where and if a request is possible
//actual data stored