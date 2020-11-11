package com.revature.models;

public class Account {

	private int accountId;
	private int customerId;
	//private String title;
	private double balance;
	private String status;

	@Override
	public String toString() {
		return "Account: " + accountId + " --- " + "Balance: " + balance + " --- " + status;
	}

	public Account() {
		super();
	}
	
	public Account(int customerId, double balance) {
		super();
		this.customerId = customerId;
		this.balance = balance;
		this.status = "PENDING";
	}
	
	public int getAccountId() {
		return accountId;
	}
	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}
	public int getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
}