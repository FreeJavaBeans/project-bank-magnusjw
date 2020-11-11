package com.revature.menus;

import java.util.ArrayList;
import java.util.List;

import com.revature.models.Customer;
import com.revature.models.User;

public class MenuSelector implements Displayable{
	
	private MenuNode currentMenu;
	
	private User currentUser;
	
	private boolean exit = false;

	private static MenuSelector ms = new MenuSelector();
	
	private MenuSelector() {
		super();
		this.currentUser = new Customer();
		this.currentMenu = buildMenus();
	}
	
	@Override
	public String display() {
		return currentMenu.getValue().display();
	}
	
	public void traverse(int i) {
		if(i == -1) {
			this.currentMenu = currentMenu.backwards();
		}else {
			this.currentMenu = currentMenu.forwards(i);
		}
	}
	

	private MenuNode buildMenus() {
		
		MenuNode intro = new MenuNode(new IntroMenu(), null, null);
		MenuNode customer = new MenuNode(new CustomerMenu(), intro, null);
		MenuNode employee = new MenuNode(new EmployeeMenu(), intro, null);
		
		List<MenuNode> introChildren = new ArrayList<>();
		introChildren.add(customer);
		introChildren.add(employee);
		intro.setChildren(introChildren);
		
		return intro;
	}
	
	public void reset() {
		this.currentMenu = buildMenus();
		this.currentUser = null;
	}
	public void handleInput() {
		this.currentMenu.getValue().handleInput();
	}
	public static MenuSelector getMenuSelector() {
		return ms;
	}
	public User getCurrentUser() {
		return currentUser;
	}
	public void setCurrentUser(User currentUser) {
		this.currentUser = currentUser;
	}
	public boolean isExit() {
		return exit;
	}
	public void setExit(boolean exit) {
		this.exit = exit;
	}
}