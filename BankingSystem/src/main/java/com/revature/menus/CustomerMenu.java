package com.revature.menus;

import java.util.ArrayList;
import java.util.List;

import com.revature.exceptions.CredentialException;
import com.revature.services.BankAccountInterface;
import com.revature.services.BankAccountService;

public class CustomerMenu extends AbstractMenu{

	private BankAccountInterface bai = new BankAccountService();
	
	@Override
	public void handleInput() {
		
		String input = this.getInputReader().nextLine();
		try {
			int choice = Integer.parseInt(input) - 1;
			//in bounds
			if(choice >=0 && choice < this.getLines().size()) {
				this.getLines().get(choice).doAction();
			}else {
				System.out.println("Please Make a Valid Choice");
			}
		}catch (NumberFormatException e) {
			System.out.println("Please Make a Valid Choice");
		}
	}
	
	@Override
	public List<MenuLine> buildMenu() {
		MenuLine l1 = new MenuLine(0, ()->"\n\n" + "--------Customer Menu--------" + "\n" + MenuSelector.getMenuSelector().getCurrentUser().getUsername() + "\n" + "1: View my Accounts", ()->{
			try {
				bai.viewAccounts();
			} catch (CredentialException e) {
				System.out.println("Currently No Accounts");
			}
		});
		MenuLine l2 = new MenuLine(1, ()->"2: Apply for an Account", ()->{
			double balance;
			System.out.println("Please enter starting balance: ");
			balance = this.getInputReader().nextDouble();
			
			try {
				bai.createAccount(balance);
			} catch (CredentialException e) {
				System.out.println("Balance must be atleast $100.");
			}
		});
		MenuLine l3 = new MenuLine(2, ()->"3: Withdraw from an Account", ()->{
			double balance;
			System.out.println("Please enter Amount you wish to withdraw: ");
			balance = this.getInputReader().nextDouble();
			
			try {
				bai.withdraw(account, amount); //
			} catch (CredentialException e) {
				System.out.println("Balance can't go below 0");
			}
		});
		MenuLine l4 = new MenuLine(3, ()->"4: Deposit into an Account", ()->{
			double balance;
			System.out.println("Please enter amount you wish to deposit ");
			balance = this.getInputReader().nextDouble();
			
			try {
				bai.deposit(account, amount)//
			} catch (CredentialException e) {
				System.out.println("Cant deposit negative amount");
			}
		});
		MenuLine l5 = new MenuLine(4, ()->"5: Transfer Money to Another Account", ()->{
			System.out.println("503 Not Implemented");
		});
		MenuLine l6 = new MenuLine(5, ()->"6: Accept Money Transfer from Another Account", ()->{
			System.out.println("503 Not Implemented");
		});
		MenuLine l7 = new MenuLine(6, ()->"7: Exit Program", ()->{
			System.out.println("Exitting. Thank you.");
			MenuSelector.getMenuSelector().setExit(true);
		});
		
		List<MenuLine> ret = new ArrayList<MenuLine>();
		ret.add(l1);
		ret.add(l2);
		ret.add(l3);
		ret.add(l4);
		ret.add(l5);
		ret.add(l6);
		ret.add(l7);
		
		return ret;
	}
}