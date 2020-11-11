package com.revature.services;

import com.revature.exceptions.CredentialException;
import com.revature.exceptions.UserNotFoundException;
import com.revature.models.Customer;
import com.revature.models.User;

public interface BankUserInterface {

	public User login(String username, String password) throws UserNotFoundException, CredentialException;

	public Customer signup(String username, String password, String confirm, String firstName, String lastName) throws UserNotFoundException, CredentialException;
	
	public User findUserByUserId(int userId);

}