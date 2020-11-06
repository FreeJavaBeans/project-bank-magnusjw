package com.revature.models;


//A class to maintain the format of each log entry
//The controller between Model and View (Customer/Employee and Account? or log)
public class Transaction {

	//Variables
	//private Customer = new Customer();
	
	
	private String category; //Either transfer, deposit, or withdrawal
	private double amount;
	
	
	//Constructors
	public Transaction(String category, double amount, Account recipient) {
		//deposit
		this(category, null, amount, recipient);
	}
	
	public Transaction(String category, Account sender, double amount) {
		//withdrawal
		this(category, sender, amount, null);
	}
	
	public Transaction(String category, Account sender, double amount, Account recipient) {
		//Transfer between accounts
		super();
		this.category = category;
		this.amount = amount;
	}

	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}


	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}
	
	

}
