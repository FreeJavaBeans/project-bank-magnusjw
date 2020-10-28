package com.revature.menus;

//import com.revature.models.Customer;

//A class to maintain the format of each log entry
public class Transaction {

	//Variables
	private String category; //Either transfer, deposit, or withdrawal
	private Account sender;
	private Account recipient;
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
		this.sender = sender;
		this.amount = amount;
		this.recipient = recipient;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public Account getSender() {
		return sender;
	}

	public void setSender(Account sender) {
		this.sender = sender;
	}

	public Account getRecipient() {
		return recipient;
	}

	public void setRecipient(Account recipient) {
		this.recipient = recipient;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}
	
	

}
