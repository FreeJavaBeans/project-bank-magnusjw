package com.revature.repositories;

import java.util.Set;

import com.revature.models.Account;

public interface BankAccountRepository {

	public Set<Account> findCustomerAccounts(int id); //503
	
	public void changeAccountBalance(double amount);
	
	public void insertAccount(int id, double balance);

}