package com.revature.models;

public class Transaction {

	private int transactionId;
	private int recipientId;
	private int senderId;
	private int type; // 0=deposit, 1=withdraw, 2=transfer
	private double amount;
	private String status;

	public Transaction() {
		super();
	}
	
	@Override
	public String toString() {
		if(type == 0) {
			return "Id " + transactionId + ": DEPOSIT: $" + amount + ", AccountId: " + recipientId +", Status: " + status;
		} else if (type == 1) {
			return "Id " + transactionId + ": WITHDRAWEL: $" + amount + ", AccountId: " + recipientId +", Status: " + status;
		} else {
			return "Id " + transactionId + ": TRANSFER: $" + amount + ", from Account " + senderId + ", to Account " + recipientId +", Status: " + status;
		}
	}
	
	
	public int getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(int transactionId) {
		this.transactionId = transactionId;
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
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
}