package com.revature.menus;

import java.util.ArrayList;
import java.util.List;

public class EmployeeMenu extends AbstractMenu{

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
			System.out.println("503 Not Implemented");
		});
		MenuLine l2 = new MenuLine(1, ()->"2: View a Customer's Accounts ", ()->{
			System.out.println("503 Not Implemented");
		});
		MenuLine l3 = new MenuLine(2, ()->"3: View Transaction Log", ()->{
			System.out.println("503 Not Implemented");
		});
		MenuLine l4 = new MenuLine(3, ()->"4: Exit Program", ()->{
			System.out.println("Exitting. Thank you.");
			MenuSelector.getMenuSelector().setExit(true);
		});
		
		List<MenuLine> ret = new ArrayList<MenuLine>();
		ret.add(l1);
		ret.add(l2);
		ret.add(l3);
		ret.add(l4);
		return ret;
		
	}
}


/*
log of all transactions for employers to view

	//Variables
	private List<Transaction> log;
	
	
	//Methods
	public void addTransaction(Transaction entry) {
		//called when a transaction is made, so that it can be entered into the logs
	}
	
	public void viewLog() {
		//some sort of display method
	}
*/