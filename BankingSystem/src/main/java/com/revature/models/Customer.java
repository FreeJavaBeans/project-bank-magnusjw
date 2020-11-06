package com.revature.models;

public class Customer{
	
	//Variables
	private Account acc; // link this to an account in the db
	private double money;
	
	
	//Constructors
	public Customer() {
		this(0); //No money customer
	}
	public Customer(double money) {
		super();
		this.money = money;
	}
	
	
	//Getters and Setters
	public double getMoney() {
		return money;
	}
	public void setMoney(double money) {
		this.money = money;
	}
	
	
	//Methods
	public double viewBalance() {
		if(this.acc == null) {
			System.out.println("Account does not exist. Cannot view balance.");
		}
		return this.acc.getBalance();
	}
	
	public Account createAccount(double amount) {
		//Request approval from employees, adding request to approvalLog class that Employees can view

		if(acc != null) {
			System.out.println("Account already exists.");
			return null;
		}
		if(this.money < amount) {
			System.out.println("Insufficient Funds to Create Account.");
			return null;
		}
		
		acc = new Account(amount);
		this.money -= amount;
		return acc;
	}
	
	public void withdraw(double amount) {
		if(this.acc == null) {
			System.out.println("Account does not exist. Cannot withdraw.");
			return;
		}
		if(amount < 0) {
			System.out.println("Amount must be greater than 0.");
			return;
		}
		if(this.acc.getBalance() < amount) {
			System.out.println("Account does not have enough money to withdraw.");
			return;
		}
		this.acc.setBalance(this.acc.getBalance() - amount);
		this.money += amount;
	}
	
	public void deposit(double amount) {
		if(acc == null) {
			System.out.println("Account does not exist. Cannot deposit.");
			return;
		}
		if(amount < 0) {
			System.out.println("Amount must be greater than 0.");
			return;
		}
		if(this.money < amount) {
			System.out.println("Not enough money to make deposit.");
			return;
		}
		this.acc.setBalance(this.acc.getBalance() + amount);
		this.money -= amount;
	}
}