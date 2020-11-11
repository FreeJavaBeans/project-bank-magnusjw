package com.revature.services;

import com.revature.exceptions.CredentialException;

public interface BankAccountInterface {
	
	public void viewAccounts() throws CredentialException;

	public void createAccount(Double balance) throws CredentialException;
	
	public void withdraw(int accountId, Double amount) throws CredentialException;
	
	public void deposit(int accountId, Double amount) throws CredentialException;
}
