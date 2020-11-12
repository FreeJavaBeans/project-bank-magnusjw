package com.revature.services;

import com.revature.exceptions.CredentialException;

public interface BankAccountInterface {
	
	public void viewAccounts() throws CredentialException;
	
	public void viewAccounts(int id) throws CredentialException;

	public void createAccount(Double balance) throws CredentialException;
	
	public void withdraw(int accountId, Double amount) throws CredentialException;
	
	public void deposit(int accountId, Double amount) throws CredentialException;
	
	public void viewLogs() throws CredentialException;

	public void viewPendingAccounts() throws CredentialException;
	
	public void approveAccount(int accountId);
	
	public void rejectAccount(int accountId);
	
	public void viewPendingTransfers() throws CredentialException;
	
	public void approveTransfer(int TransactionId);
	
	public void rejectTransfer(int TransactionId);

	public void transfer(int accountId, int myAccountId, double amount);

}
