package com.revature.services;

import com.revature.exceptions.CredentialException;
import com.revature.models.Account;

public interface BankAccountInterface {
	
	public void viewAccounts() throws CredentialException;

	public void createAccount(Double balance) throws CredentialException;
	
	public Account withdraw(Account account, Double amount); //503
	
	public Account deposit(Account account, Double amount); //503
}
