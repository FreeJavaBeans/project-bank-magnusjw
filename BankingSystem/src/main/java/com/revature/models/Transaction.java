package com.revature.models;

public class Transaction {

	private int recipientId;
	private int senderId;
	private int type; // 0=deposit, 1=withdraw, 2=transfer
	private double amount;

	public Transaction() {
		super();
	}
	public Transaction(int recipientId, int senderId, int type, double amount) {
		super();
		this.senderId = senderId;
		this.type = type;
		this.amount = amount;
		this.recipientId = recipientId;
	}
	
	@Override
	public String toString() {
		if(type == 0) {
			return "DEPOSIT: $" + amount + ", AccountId: " + recipientId + "]";
		} else if (type == 1) {
			return "WITHDRAW: $" + amount + ", AccountId: " + recipientId + "]";
		} else {
			return "TRANSFER: $" + amount + ", from " + senderId + ", recipientId=" + recipientId + "]";
		}
	}
	
	public int getRecipientId() {
		return recipientId;
	}
	public void setRecipientId(int recipientId) {
		this.recipientId = recipientId;
	}
	public int getSenderId() {
		return senderId;
	}
	public void setSenderId(int senderId) {
		this.senderId = senderId;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
}