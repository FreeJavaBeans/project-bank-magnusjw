package com.revature.repositories;

import com.revature.models.Account;
import com.revature.models.Customer;

//Behaviors our Account database should have
public interface BankAccountRepository {
	
	public Account findCustomerAccount(Customer c);
	
	//Should I have a repo for Transactions too?

}
