package com.revature.models;

import com.revature.menus.Account;

public class Customer extends User{



	//Variables
	private double balance;
	private Account account;
	
	
	//Constructors
	public Customer(String username, String password) {
		super(username, password);
		// TODO Auto-generated constructor stub
	}
	
	

	
	//Getters and Setters
	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}
}
