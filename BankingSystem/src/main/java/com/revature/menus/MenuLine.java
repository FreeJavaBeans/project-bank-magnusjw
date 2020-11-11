package com.revature.menus;

public class MenuLine implements Displayable{
	
	private int lineNumber;
	
	private Displayable item;
	
	private Actionable action;

	public String display() {
		return item.display();
	}
	
	public void doAction() {
		action.action();
	}
	
	public MenuLine(int lineNumber, Displayable item, Actionable action) {
		super();
		this.lineNumber = lineNumber;
		this.action = action;
		this.item = item;
	}

	public int getLineNumber() {
		return lineNumber;
	}

	public void setLineNumber(int lineNumber) {
		this.lineNumber = lineNumber;
	}
	

}
