package com.revature.repositories;

import com.revature.exceptions.CredentialException;
import com.revature.exceptions.UserNotFoundException;
import com.revature.models.Customer;
import com.revature.models.User;

public interface BankIntroRepository {
	
	public User findUserByUsernameAndPassword(String username, String password) throws UserNotFoundException, CredentialException;
	
	public Customer insertCustomer(String username, String password, String firstName, String lastName);
}