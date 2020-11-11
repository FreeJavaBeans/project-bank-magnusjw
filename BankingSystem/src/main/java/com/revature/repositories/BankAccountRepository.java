package com.revature.repositories;

import java.util.Set;

import com.revature.exceptions.CredentialException;
import com.revature.models.Account;

public interface BankAccountRepository {

	public Set<Account> findCustomerAccounts(int id);
	
	public void insertAccount(int id, double balance);
	
	public double withdrawBalance(int accountId, double amount) throws CredentialException;
	
	public double depositBalance(int accountId, double amount);

}