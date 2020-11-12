package com.revature.repositories;

import java.util.Set;

import com.revature.exceptions.AccountNotApproved;
import com.revature.exceptions.CredentialException;
import com.revature.models.Account;
import com.revature.models.Transaction;

public interface BankAccountRepository {

	public Set<Account> findCustomerAccounts(int id);
	
	public void insertAccount(int id, double balance);
	
	public double withdrawBalance(int accountId, double amount) throws CredentialException, AccountNotApproved;
	
	public double depositBalance(int accountId, double amount) throws AccountNotApproved;

	public Set<Transaction> viewTransactions();

	public Set<Account> viewPendingAccounts();
	
	public void approveAccount(int accountId);
	
	public void rejectAccount(int accountId);
	
	public Set<Transaction> viewPendingTransactions();
	
	public void approveTransaction(int transactionId);
	
	public void rejectTransaction(int transactionId);
	
	public void transfer(int accountId, int myAccountId, double amount) throws CredentialException;

}