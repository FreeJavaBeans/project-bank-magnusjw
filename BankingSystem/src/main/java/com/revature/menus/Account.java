package com.revature.menus;

public class Account {

	//Variables
	private String owner;
	private double balance;
	
	
	//Constructors
	public Account() {
		super();
	}
	
	public Account(String owner, double balance) {
		super();
		this.owner = owner;
		this.balance = balance;
	}
	
	
	//Methods
	public void deposit(double amount) {
		//add amount to the object's account
	}
	
	public void withdraw(double amount) {
		//withdraw amount from the object's account
	}
	
}