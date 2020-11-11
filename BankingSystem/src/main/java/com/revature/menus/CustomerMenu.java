package com.revature.menus;

import java.util.ArrayList;
import java.util.List;

public class CustomerMenu extends AbstractMenu{

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
		MenuLine l1 = new MenuLine(0, ()->"\n\n" + "--------Customer Menu--------" + "\n" + MenuSelector.getMenuSelector().getCurrentUser().getUsername() + "\n" + "1: View Accounts", ()->{
			System.out.println("503 Not Implemented");
		});
		MenuLine l2 = new MenuLine(1, ()->"2: Apply for an Account", ()->{
			System.out.println("503 Not Implemented");
		});
		MenuLine l3 = new MenuLine(2, ()->"3: Withdraw from an Account", ()->{
			System.out.println("503 Not Implemented");
		});
		MenuLine l4 = new MenuLine(3, ()->"4: Deposit into an Account", ()->{
			System.out.println("503 Not Implemented");
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