package com.revature.menus;

public class Homepage {
	
	
	
	public String display(){
		StringBuilder sb = new StringBuilder();
		sb.append("Johnny's Banking Application.");
		sb.append("1. Login");
		sb.append("2. Signup");
		
		return sb.toString();
		
	}
}
