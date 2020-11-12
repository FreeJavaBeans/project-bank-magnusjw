package com.revature.menus;

import java.util.ArrayList;
import java.util.List;

import com.revature.exceptions.AccountNotApproved;
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
			System.out.print("Please enter starting balance: ");
			balance = this.getInputReader().nextDouble();
			this.getInputReader().skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
			
			try {
				bai.createAccount(balance);
			} catch (CredentialException e) {
				System.out.println("Balance must be atleast $100.");
			}
		});
		MenuLine l3 = new MenuLine(2, ()->"3: Withdraw from an Account", ()->{
			int accountId;
			double balance;
			System.out.print("Which account number would you like to withdraw from: ");
			accountId = this.getInputReader().nextInt();
			System.out.print("How much would you like to withdraw: ");
			balance = this.getInputReader().nextDouble();
			this.getInputReader().skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
			
			try {
				bai.withdraw(accountId, balance);
			} catch (CredentialException e) {
				System.out.println("Balance can't go below 0");
			}
		});
		MenuLine l4 = new MenuLine(3, ()->"4: Deposit into an Account", ()->{
			int accountId;
			double balance;
			System.out.print("Which account number would you like to deposit to: ");
			accountId = this.getInputReader().nextInt();
			System.out.print("How much would you like to deposit: ");
			balance = this.getInputReader().nextDouble();
			this.getInputReader().skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
			
			try {
				bai.deposit(accountId, balance);//
			} catch (CredentialException e) {
				System.out.println("Cant deposit negative amount");
			}
		});
		
		MenuLine l5 = new MenuLine(4, ()->"5: Transfer Money to Another Account", ()->{ //503
			int accountId;
			int myAccountId;
			double amount;
			System.out.print("Which account would you like to Transfer to: ");
			accountId = this.getInputReader().nextInt();
			System.out.print("Which account would you like to Transfer from: ");
			myAccountId = this.getInputReader().nextInt();
			System.out.print("How much would you like to Transfer: ");
			amount = this.getInputReader().nextDouble();
			this.getInputReader().skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

			
			bai.transfer(accountId, myAccountId, amount);
		});
		MenuLine l6 = new MenuLine(5, ()->"6: View Pending Transfers to your Accounts", ()->{
			try {
				bai.viewPendingTransfers();
			} catch (CredentialException e) {
				System.out.println("No Pending Transfers");
			}
		});
		MenuLine l7 = new MenuLine(6, ()->"7: Approve Transfer", ()->{
			int transactionId;
			System.out.print("Enter TransferId of Transaction you want to Approve: ");
			transactionId = this.getInputReader().nextInt();
			this.getInputReader().skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
			
			bai.approveTransfer(transactionId);
		});
		MenuLine l8 = new MenuLine(7, ()->"8: Reject Transfer", ()->{
			int transactionId;
			System.out.print("Enter TransactionId of Transaction you want to Reject: ");
			transactionId = this.getInputReader().nextInt();
			this.getInputReader().skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
			
			bai.rejectTransfer(transactionId);
		});
		MenuLine l9 = new MenuLine(8, ()->"9: Exit Program", ()->{
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
		ret.add(l8);
		ret.add(l9);
		
		return ret;
	}
}