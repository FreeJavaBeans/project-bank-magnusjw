package com.revature.models;

import java.util.Scanner;

import com.revature.menus.Account;


public abstract class User {

	
	//Variables
	private String username;
	private String password;
	
	
	//Constructors
	public User(String username, String password) { 
		// No default constructor to say that an account cannot be made without a user and pass
		super();
		this.username = username;
		this.password = password;
		
		String stub = username + "Account";
		Account Acc = new Account(username, 0.00); //Upon logging in, create fresh account for user
	}
	
	
	
	//Methods
	public void logIn() {
		Scanner scanner = new Scanner(System.in);
		
		
		System.out.println("Enter Username");
		String user = scanner.nextLine();
		
		System.out.println("Enter Password");
		String pass = scanner.nextLine();
		
		//Error check inputs, such as Password must be more than 7 characters
		
		//If the user and pass match, then allow the login and fwd them to either customer or employer view
		
	}
	

	//Getters and Setters
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
}