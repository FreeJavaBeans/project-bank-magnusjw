package com.revature.models;

import java.util.Scanner;

import com.revature.menus.Homepage;


public class User {

	
	//Variables
	private String username;
	private String password;
	private Homepage home;
	private Scanner input = new Scanner(System.in);
	
	
	public void display() {


		return sb.toString();
		
	}
	
	//Methods
	public void signUp() {
		
	}
	
	public void logIn() {
		
		System.out.println("Enter Username");
		String user = input.next();
		
		if(user.length() < 4) {
			System.out.println("Username must be atleast 4 characters");
			System.out.println("Exiting...");
			return;
		}
		
		System.out.println("Enter Password");
		String pass = input.next();
		
		if(pass.length() < 7) {
			System.out.println("Password must be atleast 7 characters.");
			System.out.println("Exiting...");
			return;
		}
		
		//Check db if user/pass exists in either customer or employee table
		//If the user and pass match, then allow the login
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







	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}
	
	
}