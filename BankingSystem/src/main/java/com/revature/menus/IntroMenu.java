package com.revature.menus;

import java.util.ArrayList;
import java.util.List;

import com.revature.models.Customer;
import com.revature.services.BankIntroInterface;
import com.revature.services.BankIntroService;

public class IntroMenu extends AbstractMenu{
	
	private BankIntroInterface bii = new BankIntroService();
	
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

		MenuLine l1 = new MenuLine(0, ()->"\n\n" + "--------Intro Menu--------" + "\n" + "1: Login", ()->{
			String username;
			String password;
			System.out.print("Please Enter your Username: ");
			username = this.getInputReader().nextLine();
			
			System.out.print("Please Enter your Password: ");
			password = this.getInputReader().nextLine();
				
			MenuSelector.getMenuSelector().setCurrentUser(bii.login(username, password));
			if(MenuSelector.getMenuSelector().getCurrentUser() instanceof Customer) {
				MenuSelector.getMenuSelector().traverse(0); //Customer
			} else {
				MenuSelector.getMenuSelector().traverse(1); //Employee
			}
	
		});
		
		MenuLine l2 = new MenuLine(1, ()->"2: Signup", ()->{
			String username;
			String password;
			String confirm;
			String firstName;
			String lastName;
			System.out.print("Please Enter your Username: ");
			username = this.getInputReader().nextLine();

			System.out.print("Please Enter your Password: ");
			password = this.getInputReader().nextLine();

			System.out.print("Please Confirm Password: ");
			confirm = this.getInputReader().nextLine();
			
			System.out.print("Please Enter your First Name: ");
			firstName = this.getInputReader().nextLine();
			
			System.out.print("Please Enter your Last Name: ");
			lastName = this.getInputReader().nextLine();
	
			MenuSelector.getMenuSelector().setCurrentUser(bii.signup(username, password, confirm, firstName, lastName));
			MenuSelector.getMenuSelector().traverse(0);
		});
		
		MenuLine l3 = new MenuLine(2, ()->"3: Exit Program", ()->{
			System.out.println("Exitting. Thank you.");
			MenuSelector.getMenuSelector().setExit(true);
		});
		
		List<MenuLine> ret = new ArrayList<MenuLine>();
		ret.add(l1);
		ret.add(l2);
		ret.add(l3);
		
		return ret;
	}

}
