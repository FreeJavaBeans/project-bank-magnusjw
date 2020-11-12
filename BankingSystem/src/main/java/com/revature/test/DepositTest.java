package com.revature.test;

import static org.junit.Assert.assertEquals;

import com.revature.models.Customer;
import com.revature.repositories.BankAccountDAO;
import com.revature.repositories.BankAccountRepository;
import com.revature.services.BankIntroInterface;
import com.revature.services.BankIntroService;

public class DepositTest {
	
	private Customer c;
	
	private BankIntroInterface bii = new BankIntroService();
	private BankAccountRepository bar = new BankAccountDAO();
	
	public DepositTest() {
		
		bii.signup("CoolGuy","Monster", "Monster", "John", "Magnuson");//Create Customer
		bar.insertAccount(c.getCustomerId(), 1000); //Create Account
		
		
		
	}

	@org.junit.Test
	public void testDeposit() {
		try {
			assertEquals(bar.depositBalance(1, 500), 500);
		} catch (Exception e) {
			System.out.println("hello");
		}
	}
	
}
