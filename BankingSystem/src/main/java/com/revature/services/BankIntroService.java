package com.revature.services;

import com.revature.exceptions.CredentialException;
import com.revature.exceptions.UserNotFoundException;
import com.revature.models.Customer;
import com.revature.models.User;
import com.revature.repositories.BankIntroDAO;
import com.revature.repositories.BankIntroRepository;

public class BankIntroService implements BankIntroInterface{
	
	private BankIntroRepository bad = new BankIntroDAO();

	@Override
	public User login(String username, String password) throws UserNotFoundException, CredentialException {
		
		if(username.length() < 5 || username.length() > 20) {
			throw new CredentialException();
		} else if(password.length() < 5 || password.length() > 20) {
			throw new CredentialException();
		}
		User user = bad.findUserByUsernameAndPassword(username, password);

		return user;
	}

	@Override
	public Customer signup(String username, String password, String confirm, String firstName, String lastName) throws CredentialException {

		if(username.substring(0, 3).equals("EMP")) {
			System.out.println("Bad username");
			throw new CredentialException();
		} else if(username.length() < 5 || username.length() > 20) {
			System.out.println("Username and Password must be 5-20 Characters.");
			throw new CredentialException();
		} else if(password.length() < 5 || password.length() > 20) {
			System.out.println("Username and Password must be 5-20 Characters.");
			throw new CredentialException();
		} else if(!confirm.equals(password)) {
			System.out.println("Passwords must match");
			throw new CredentialException();
		}
		
		Customer customer = bad.insertCustomer(username, password, firstName, lastName);

		return customer;
		}
}