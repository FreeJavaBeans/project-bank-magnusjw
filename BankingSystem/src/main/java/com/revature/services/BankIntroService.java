package com.revature.services;

import com.revature.BankLauncher;
import com.revature.exceptions.CredentialException;
import com.revature.models.Customer;
import com.revature.models.User;
import com.revature.repositories.BankIntroDAO;
import com.revature.repositories.BankIntroRepository;

public class BankIntroService implements BankIntroInterface{
	
	private BankIntroRepository bad = new BankIntroDAO();

	@Override
	public User login(String username, String password){
		
		try {
			
			if(username.length() < 5 || username.length() > 20) {
				System.out.println("Username must be 5-20 Characters");
				throw new CredentialException();
			} else if(password.length() < 5 || password.length() > 20) {
				System.out.println("Password must be 5-20 Characters");
				throw new CredentialException();
			}
		} catch(CredentialException e) {
		}
		User user = bad.findUserByUsernameAndPassword(username, password);
		
		BankLauncher.Logger.info(user.getUsername() + " has logged in.");
		
		return user;

	}

	@Override
	public Customer signup(String username, String password, String confirm, String firstName, String lastName){

		try {
			if(username.substring(0, 3).equals("EMP")) {
				System.out.println("What are you up to? Bad things I'm sure.");
				throw new CredentialException();
			} else if(username.length() < 5 || username.length() > 20) {
				System.out.println("Username  must be 5-20 Characters.");
				throw new CredentialException();
			} else if(password.length() < 5 || password.length() > 20) {
				System.out.println("Password must be 5-20 Characters.");
				throw new CredentialException();
			} else if(!confirm.equals(password)) {
				System.out.println("Passwords must match");
				throw new CredentialException();
			}
		} catch (CredentialException e) {
			
		}
		
		Customer customer = bad.insertCustomer(username, password, firstName, lastName);
		
		BankLauncher.Logger.info(customer.getUsername() + " has signed up for an account.");

		return customer;
		}
}