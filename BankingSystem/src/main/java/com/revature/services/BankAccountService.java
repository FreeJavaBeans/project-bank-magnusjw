package com.revature.services;

import java.util.HashSet;
import java.util.Set;

import com.revature.exceptions.AccountNotApproved;
import com.revature.exceptions.CredentialException;
import com.revature.menus.MenuSelector;
import com.revature.models.Account;
import com.revature.models.Transaction;
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
	public void viewAccounts(int id) throws CredentialException{
		Set<Account> accounts = new HashSet<Account>();
		
		accounts = bar.findCustomerAccounts(id);
		
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
		
	}
	
	@Override
	public void withdraw(int accountId, Double amount) throws CredentialException{

		if(amount < 0) {
			throw new CredentialException("Can't withdraw a negative amount");
		} else if(amount == 0) {
			throw new CredentialException("Can't withdraw zero dollars");
		}
		try {
			double result = bar.withdrawBalance(accountId, amount);
			System.out.println("Successful Withdraw!");
			System.out.println("Final balance of account " + accountId + " is $" + result);
		} catch (AccountNotApproved e) {
			System.out.println("Account is not approved");
		}

	}
	
	@Override
	public void deposit(int accountId, Double amount) throws CredentialException{

		if(amount < 0) {
			throw new CredentialException("Can't deposit a negative amount");
		} else if(amount == 0) {
			throw new CredentialException("Can't deposit zero dollars");
		}
		
		try {
			double result = bar.depositBalance(accountId, amount);
			System.out.println("Successful Deposit!");
			System.out.println("Final balance of account " + accountId + " is $" + result);
		} catch (AccountNotApproved e) {
			System.out.println("Account is not approved");
		}
	}
	
	@Override
	public void viewLogs() throws CredentialException{
		Set<Transaction> transactions = new HashSet<Transaction>();
		
		transactions = bar.viewTransactions();
		
		if(transactions.size() == 0) {
			throw new CredentialException();
		}
		
		for(Transaction tr : transactions) {
			System.out.println(tr);
		}
	}

	@Override
	public void viewPendingAccounts() throws CredentialException {
		Set<Account> accounts = new HashSet<Account>();
		
		accounts = bar.viewPendingAccounts();
		
		if(accounts.size() == 0) {
			throw new CredentialException();
		}
		
		for(Account acc : accounts) {
			System.out.println(acc);
		}
	}

	@Override
	public void approveAccount(int accountId){
		bar.approveAccount(accountId);
	}

	@Override
	public void rejectAccount(int accountId){
		bar.rejectAccount(accountId);
	}

	@Override
	public void viewPendingTransfers() throws CredentialException {
		Set<Transaction> transactions = new HashSet<Transaction>();
		
		transactions = bar.viewPendingTransactions();
		
		if(transactions.size() == 0) {
			throw new CredentialException();
		}
		
		for(Transaction tr : transactions) {
			System.out.println(tr);
		}
	}

	@Override
	public void approveTransfer(int TransactionId){
		bar.approveTransaction(TransactionId);
	}

	@Override
	public void rejectTransfer(int TransactionId){
		bar.rejectTransaction(TransactionId);
	}

	
	@Override
	public void transfer(int accountId, int myAccountId, double amount){
		try {
			if(amount < 0) {
				throw new CredentialException();
			} else if(amount == 0) {
				throw new CredentialException();
			}
		
			bar.transfer(accountId, myAccountId, amount);
		} catch(CredentialException e) {
			System.out.println("Requested negative amount or Account has insufficient funds");
		}
	}
}
