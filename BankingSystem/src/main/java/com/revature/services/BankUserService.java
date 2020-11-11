package com.revature.services;

import com.revature.exceptions.CredentialException;
import com.revature.exceptions.UserNotFoundException;
import com.revature.models.Customer;
import com.revature.models.User;
import com.revature.repositories.BankUserDAO;
import com.revature.repositories.BankUserRepository;

public class BankUserService implements BankUserInterface{
	
	private BankUserRepository bad = new BankUserDAO();

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
	public Customer signup(String username, String password, String confirm, String firstName, String lastName) throws UserNotFoundException, CredentialException {

		if(username.length() < 5 || username.length() > 20) {
			throw new CredentialException();
		} else if(password.length() < 5 || password.length() > 20) {
			throw new CredentialException();
		} else if(confirm.equals("\n" + password)) {
			throw new CredentialException();
		}
		
		System.out.println("debug: signup end");
		Customer customer = bad.insertCustomer(username, password, firstName, lastName);

		return customer;
		}

	@Override
	public User findUserByUserId(int userId) {
		// TODO Auto-generated method stub
		return null;
	}
}