package com.revature.repositories;

import java.util.Set;

import com.revature.models.Account;
import com.revature.models.Customer;

//Behaviors our Account database should have
public interface BankAccountRepository {
	
	//Testing
	public Set<Customer> findAllCustomers();
	
	//public Account applyForAccount();
	
	public Account findCustomerAccount(int id);
	
	//Should I have a repo for Transactions too?

}
