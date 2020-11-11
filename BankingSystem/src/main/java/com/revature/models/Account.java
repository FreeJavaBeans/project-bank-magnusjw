package com.revature.models;

public class Account {

	private int accountId;
	private int customerId;
	private double balance;
	private boolean approval;

	public Account() {
		super();
	}
	
	public Account(Customer customer, double balance) {
		super();
		this.customerId = customer.getCustomerId();
		this.balance = balance;
		this.approval = false;
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
	public boolean isApproval() {
		return approval;
	}
	public void setApproval(boolean approval) {
		this.approval = approval;
	}
}