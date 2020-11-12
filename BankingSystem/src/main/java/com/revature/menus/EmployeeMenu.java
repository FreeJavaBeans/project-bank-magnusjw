package com.revature.menus;

import java.util.ArrayList;
import java.util.List;

import com.revature.services.BankAccountInterface;
import com.revature.services.BankAccountService;
import com.revature.exceptions.CredentialException;

public class EmployeeMenu extends AbstractMenu{
	
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
		MenuLine l1 = new MenuLine(0, ()->"\n\n" + "--------Employee Menu--------" + "\n" + MenuSelector.getMenuSelector().getCurrentUser().getUsername() + "\n" + "1: Check Pending Approvals", ()->{
			try {
				bai.viewPendingAccounts();
			} catch (CredentialException e) {
				System.out.println("No Pending Accounts");
			}
		});
		MenuLine l2 = new MenuLine(1, ()->"2: Approve Account", ()->{
			int accountId;
			System.out.print("Enter AccountId of account you want to Approve: ");
			accountId = this.getInputReader().nextInt();
			this.getInputReader().skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
			
			bai.approveAccount(accountId);
		});
		MenuLine l3 = new MenuLine(2, ()->"3: Reject Account", ()->{
			int accountId;
			System.out.print("Enter AccountId of account you want to Reject: ");
			accountId = this.getInputReader().nextInt();
			this.getInputReader().skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
			
			bai.rejectAccount(accountId);
		});
		MenuLine l4 = new MenuLine(3, ()->"4: View a Customer's Accounts ", ()->{
			int customerId;
			System.out.print("Enter CustomerId to View: ");
			customerId = this.getInputReader().nextInt();
			this.getInputReader().skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
			
			try {
				bai.viewAccounts(customerId);
			} catch (CredentialException e) {
				System.out.println("Currently No Accounts");
			}
		});
		MenuLine l5 = new MenuLine(4, ()->"5: View Transaction Log", ()->{
			try {
				bai.viewLogs();
			} catch (CredentialException e) {
				System.out.println("No Transactions in Log");
			}
		});
		MenuLine l6 = new MenuLine(5, ()->"6: Exit Program", ()->{
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
		return ret;
		
	}
}