package com.revature.services;

import java.util.HashSet;
import java.util.Set;

import com.revature.exceptions.CredentialException;
import com.revature.menus.MenuSelector;
import com.revature.models.Account;
import com.revature.repositories.BankAccountDAO;
import com.revature.repositories.BankAccountRepository;

public class BankAccountService implements BankAccountInterface{
	
	private BankAccountRepository bar = new BankAccountDAO();
	
	@Override
	public void viewAccounts() throws CredentialException{
		Set<Account> accounts = new HashSet<Account>();
		
		int customerId = MenuSelector.getMenuSelector().getCurrentUser().getUserId();
		
		accounts = bar.findCustomerAccounts(customerId);
		
		if(accounts.size() == 0) {
			throw new CredentialException();
		}
		
		for(Account acc : accounts) {
			System.out.println(acc);
		}
	}
	
	@Override
	public void createAccount(Double balance) throws CredentialException{
		
		int customerId = MenuSelector.getMenuSelector().getCurrentUser().getUserId();
		
		if (balance < 100) {
			throw new CredentialException();
		}
		
		bar.insertAccount(customerId, balance);
		System.out.println("Account has been successfully made!");
	}
	
	@Override
	public void withdraw(int accountId, Double amount) throws CredentialException{

		if(amount < 0) {
			throw new CredentialException("Can't withdraw a negative amount");
		} else if(amount == 0) {
			throw new CredentialException("Can't withdraw zero dollars");
		}
		
		double result = bar.withdrawBalance(accountId, amount);
		System.out.println("Successful Withdraw!");
		System.out.println("Final balance of account " + accountId + " is $" + result);
	}
	
	@Override
	public void deposit(int accountId, Double amount) throws CredentialException{
		
		if(amount < 0) {
			throw new CredentialException("Can't deposit a negative amount");
		} else if(amount == 0) {
			throw new CredentialException("Can't deposit zero dollars");
		}
		
		double result = bar.depositBalance(accountId, amount);
		System.out.println("Successful Deposit!");
		System.out.println("Final balance of account " + accountId + " is $" + result);
	}
}
