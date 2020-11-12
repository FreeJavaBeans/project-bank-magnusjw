package com.revature.repositories;

import java.util.HashSet;
import java.util.Set;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.revature.exceptions.AccountNotApproved;
import com.revature.exceptions.CredentialException;
import com.revature.exceptions.TransactionNotFound;
import com.revature.menus.MenuSelector;
import com.revature.models.Account;
import com.revature.models.Transaction;
import com.revature.exceptions.AccountNotFound;
import com.revature.util.ConnectionUtil;

public class BankAccountDAO implements BankAccountRepository{
	
	private ConnectionUtil cu = ConnectionUtil.getConnectionUtil();

	@Override
	public Set<Account> findCustomerAccounts(int id){
		
		Connection conn = cu.getConnection();
		
		Set<Account> accounts = new HashSet<Account>();
		
		try {
			
			PreparedStatement ps = conn.prepareStatement("select * from \"Account\" where \"CustomerId\" in "
					+ "(select \"CustomerId\" from \"Customer\" where \"CustomerId\" = ?) order by \"AccountId\";");
			
			ps.setInt(1, id);
			
			ResultSet rs = ps.executeQuery();

			while(rs.next()) {
				Account acc = new Account();
				acc.setAccountId(rs.getInt("AccountId"));
				acc.setCustomerId(rs.getInt("CustomerId"));
				acc.setBalance(rs.getDouble("Balance"));
				acc.setStatus(rs.getString("Status"));
				
				accounts.add(acc);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return accounts;
	}

	@Override
	public void insertAccount(int id, double balance) {
		
		Connection conn = cu.getConnection();
		
		String sql = "INSERT INTO \"Account\" (\"CustomerId\", \"Balance\") VALUES (?,?);";
		
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setInt(1, id);
			ps.setDouble(2, balance);
			
			ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Get all Customers Failed");
		}
	}

	@Override
	public double withdrawBalance(int accountId, double amount) throws CredentialException, AccountNotApproved{
		
		Connection conn = cu.getConnection();
		
		String sql = "select * from \"Account\" where \"AccountId\" = ?;";
		String sql2 = "Update \"Account\" set \"Balance\" = ? where \"AccountId\" = ?;";
		
		String log = "insert into \"Transaction\" (\"RecipientId\", \"Type\", \"Amount\", \"Status\") values (?, 1, ?, 'APPROVED');";
		
		double balance = 0;
		
		try {
			
			PreparedStatement psLog = conn.prepareStatement(log);
			psLog.setInt(1, accountId);
			psLog.setDouble(2, amount);
			
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1,  accountId);
			
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				balance = (rs.getDouble("Balance"));
				String status = (rs.getString("Status"));
				if(!status.equals("APPROVED")) {
					throw new AccountNotApproved();
				}
				if(balance < amount) {
					throw new CredentialException("Account does not have enough money to withdraw");
				}
				balance -= amount;
				
				PreparedStatement ps2 = conn.prepareStatement(sql2);
				ps2.setDouble(1, balance);
				ps2.setInt(2, accountId);
				
				ps2.executeUpdate(); //change balance if ok
				
				psLog.executeUpdate(); //update into log
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Get all Customers Failed");
		}
		return balance;
	}

	@Override
	public double depositBalance(int accountId, double amount) throws AccountNotApproved{
		Connection conn = cu.getConnection();
		
		String sql = "select * from \"Account\" where \"AccountId\" = ?;";
		String sql2 = "Update \"Account\" set \"Balance\" = ? where \"AccountId\" = ?;";
		String log = "insert into \"Transaction\" (\"RecipientId\", \"Type\", \"Amount\", \"Status\") values (?, 0, ?, 'APPROVED');";
		try {
			
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1,  accountId);
			ResultSet rs = ps.executeQuery();
			
			PreparedStatement psLog = conn.prepareStatement(log);
			psLog.setInt(1, accountId);
			psLog.setDouble(2, amount);

			if(rs.next()) {
				String status = (rs.getString("Status"));
				if(!status.equals("APPROVED")) {
					throw new AccountNotApproved();
				}
				double balance = (rs.getDouble("Balance"));
				amount += balance;
			}
			
			PreparedStatement ps2 = conn.prepareStatement(sql2);
			
			ps2.setDouble(1, amount);
			ps2.setInt(2, accountId);
			
			ps2.executeUpdate();
			
			psLog.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Get all Customers Failed");
		}
		return amount;
	}
	
	@Override
	public Set<Transaction> viewTransactions() {
		Connection conn = cu.getConnection();
		
		Set<Transaction> transactions = new HashSet<Transaction>();
		
		try {
			
			PreparedStatement ps = conn.prepareStatement("select * from \"Transaction\";");
			
			ResultSet rs = ps.executeQuery();

			while(rs.next()) {
				Transaction tr = new Transaction();
				tr.setTransactionId(rs.getInt("TransactionId"));
				tr.setRecipientId(rs.getInt("RecipientId"));
				tr.setSenderId(rs.getInt("SenderId"));
				tr.setType(rs.getInt("Type"));
				tr.setAmount(rs.getDouble("Amount"));
				tr.setStatus(rs.getString("Status"));
				
				transactions.add(tr);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return transactions;
		
	}

	@Override
	public Set<Account> viewPendingAccounts(){
		Connection conn = cu.getConnection();
		
		Set<Account> accounts = new HashSet<Account>();
		
		String sql = "select * from \"Account\" where \"Status\" = 'PENDING' order by \"AccountId\";";

		try {
		
			Statement s = conn.createStatement();
			
			ResultSet rs = s.executeQuery(sql);

			while(rs.next()) {
				Account acc = new Account();
				acc.setAccountId(rs.getInt("AccountId"));
				acc.setCustomerId(rs.getInt("CustomerId"));
				acc.setBalance(rs.getDouble("Balance"));
				acc.setStatus(rs.getString("Status"));
				
				accounts.add(acc);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return accounts;
		
	}
	

	
	@Override
	public void approveAccount(int accountId) {
		Connection conn = cu.getConnection();
		
		String sql = "Update \"Account\" set \"Status\" = 'APPROVED' where \"AccountId\" = ?;";
		
		try {
			
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setInt(1, accountId);
			
			ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	
	@Override
	public void rejectAccount(int accountId){
		Connection conn = cu.getConnection();
		
		String sql = "Update \"Account\" set \"Status\" = 'REJECTED' where \"AccountId\" = ? and \"Status\" = 'PENDING';";
		
		try {
			
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setInt(1, accountId);
			
			ps.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println("SQL Error Reject Account");
		}
		
	}

	
	@Override
	public Set<Transaction> viewPendingTransactions(){
		Connection conn = cu.getConnection();
		
		int customerId = MenuSelector.getMenuSelector().getCurrentUser().getUserId();
		
		Set<Transaction> transactions = new HashSet<Transaction>();
		
		String sql = "select * from \"Transaction\" where \"Status\" = 'PENDING' and \"RecipientId\" in (select \"AccountId\" from \"Account\" where \"CustomerId\" = ?);";

		try {
		
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, customerId);
			
			ResultSet rs = ps.executeQuery();

			while(rs.next()) {
				Transaction tr = new Transaction();
				tr.setTransactionId(rs.getInt("TransactionId"));
				tr.setRecipientId(rs.getInt("RecipientId"));
				tr.setSenderId(rs.getInt("SenderId"));
				tr.setType(rs.getInt("Type"));
				tr.setAmount(rs.getDouble("Amount"));
				tr.setStatus(rs.getString("Status"));
				
				transactions.add(tr);
			}
			
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return transactions;
		
	}
	

	@Override
	public void approveTransaction(int transactionId){
		Connection conn = cu.getConnection();
		
		String sqlTr = "select * from \"Transaction\" where \"TransactionId\" = ?;";
		Transaction tr = new Transaction();
		Account recAcc = new Account();
		Account senAcc = new Account();
		
		try {
			
			PreparedStatement s = conn.prepareStatement(sqlTr);
			ResultSet rs = s.executeQuery();
			
			if(rs.next()) { //This is the Transaction in Question
				tr.setTransactionId(rs.getInt("TransactionId"));
				tr.setRecipientId(rs.getInt("RecipientId"));
				tr.setSenderId(rs.getInt("SenderId"));
				tr.setType(rs.getInt("Type"));
				if(tr.getType() != 2) {
					System.out.println("Transaction has wrong type");
					return;
				}
				tr.setAmount(rs.getDouble("Amount"));
				tr.setStatus(rs.getString("Status"));
			} else {
				throw new TransactionNotFound();
			}
			
			String sqlAcc = "select * from \"Account\" where \"AccountId\" = ?;"; //Account selection sql
			PreparedStatement ps2 = conn.prepareStatement(sqlAcc); //Confirming recipient account
			ps2.setInt(1, tr.getRecipientId());
			ResultSet rs2 = ps2.executeQuery(sqlAcc);
			
			if(rs2.next()) {
				recAcc.setAccountId(rs2.getInt("AccountId"));
				recAcc.setBalance(rs2.getDouble("Balance"));
				recAcc.setCustomerId(rs2.getInt("CustomerId"));
				recAcc.setStatus(rs2.getString("Status"));
				if(recAcc.getStatus().equals("Approved")) {
					throw new AccountNotApproved();
				}
				System.out.println("Recipient Account found!");
			} else {
				throw new AccountNotFound();
			}
			
			PreparedStatement ps3 = conn.prepareStatement(sqlAcc); //Confirming sender account
			ps3.setInt(1, tr.getSenderId());
			ResultSet rs3 = ps3.executeQuery(sqlAcc);
			
			if(rs3.next()) {
				senAcc.setAccountId(rs3.getInt("AccountId"));
				senAcc.setBalance(rs3.getDouble("Balance"));
				senAcc.setCustomerId(rs3.getInt("CustomerId"));
				senAcc.setStatus(rs3.getString("Status"));
				if(senAcc.getStatus().equals("Approved")) {
					throw new AccountNotApproved();
				}
				System.out.println("Recipient Account found!");
			} else {
				throw new AccountNotFound();
			}
			
			if(senAcc.getBalance() < tr.getAmount()) { //Checking if sender has enough funds
				System.out.println("Not enough funds to send");
				return;
			}
			
			String sqlUpdate = "Update \"Transaction\" set \"Status\" = 'APPROVED' where \"TransactionId\" = " + tr.getTransactionId() + ";"; // Approve the Transaction itself
			Statement sTr = conn.createStatement();
			sTr.executeUpdate(sqlUpdate);
			
			String sqlUpdateAcc = "Update \"Account\" set \"Balance\" = ? where \"AccountId\" = ?;"; // Add funds to recipient account
			PreparedStatement ps4 = conn.prepareStatement(sqlUpdateAcc);
			ps4.setDouble(1, (recAcc.getBalance() + tr.getAmount()));
			ps4.setInt(2, recAcc.getAccountId());
			ps4.executeUpdate();
			
			System.out.println("Transaction has been Approved");
			
		} catch (TransactionNotFound e){
			System.out.println("Transaction not Found");
		} catch (AccountNotFound e){
			System.out.println("Account not Found");
		} catch (AccountNotApproved e){
			System.out.println("Account not Found");
		} catch (SQLException e) {
			System.out.println("Approve Transaction SQL Error");
		}
	}
	

	@Override
	public void rejectTransaction(int transactionId){
		Connection conn = cu.getConnection();
		
		String sqlTr = "select * from \"Transaction\" where \"TransactionId\" = ?;";
		Transaction tr = new Transaction();
		Account senAcc = new Account();
		
		try {
			
			PreparedStatement s = conn.prepareStatement(sqlTr);
			ResultSet rs = s.executeQuery();
			
			if(rs.next()) { //This is the Transaction in Question
				tr.setTransactionId(rs.getInt("TransactionId"));
				tr.setRecipientId(rs.getInt("RecipientId"));
				tr.setSenderId(rs.getInt("SenderId"));
				tr.setType(rs.getInt("Type"));
				if(tr.getType() != 2) {
					System.out.println("Transaction has wrong type");
					return;
				}
				tr.setAmount(rs.getDouble("Amount"));
				tr.setStatus(rs.getString("Status"));
			} else {
				throw new TransactionNotFound();
			}
			
			String sqlAcc = "select * from \"Account\" where \"AccountId\" = ?;"; //Account selection sql

			PreparedStatement ps3 = conn.prepareStatement(sqlAcc); //Confirming sender account
			ps3.setInt(1, tr.getSenderId());
			ResultSet rs3 = ps3.executeQuery(sqlAcc);
			
			if(rs3.next()) {
				senAcc.setAccountId(rs3.getInt("AccountId"));
				senAcc.setBalance(rs3.getDouble("Balance"));
				senAcc.setCustomerId(rs3.getInt("CustomerId"));
				senAcc.setStatus(rs3.getString("Status"));
				if(senAcc.getStatus().equals("Approved")) {
					throw new AccountNotApproved();
				}
				System.out.println("Recipient Account found!");
			} else {
				throw new AccountNotFound();
			}
			
			String sqlUpdate = "Update \"Transaction\" set \"Status\" = 'REJECTED' where \"TransactionId\" = " + tr.getTransactionId() + ";"; // Reject the Transaction itself
			Statement sTr = conn.createStatement();
			sTr.executeUpdate(sqlUpdate);
			
			String sqlUpdateAcc = "Update \"Account\" set \"Balance\" = ? where \"AccountId\" = ?;"; // Return funds to Sender account
			PreparedStatement ps4 = conn.prepareStatement(sqlUpdateAcc);
			ps4.setDouble(1, (senAcc.getBalance() + tr.getAmount()));
			ps4.setInt(2, senAcc.getAccountId());
			ps4.executeUpdate();
			
			System.out.println("Transaction has been Rejected");
			
		} catch (TransactionNotFound e){
			System.out.println("Transaction not Found");
		} catch (AccountNotFound e){
			System.out.println("Account not Found");
		} catch (AccountNotApproved e){
			System.out.println("Account not Found");
		} catch (SQLException e) {
			System.out.println("Approve Transaction SQL Error");
		}
	}

	
	@Override
	public void transfer(int accountId, int myAccountId, double amount) throws CredentialException{
		Connection conn = cu.getConnection();
		
		Account recAcc = new Account();
		Account senAcc = new Account();
		
		try {
			
			String sqlAcc = "select * from \"Account\" where \"AccountId\" = ?;"; //Account selection sql
			PreparedStatement ps2 = conn.prepareStatement(sqlAcc); //Confirming recipient account
			ps2.setInt(1, accountId);
			ResultSet rs2 = ps2.executeQuery(sqlAcc);
			
			if(rs2.next()) {
				recAcc.setAccountId(rs2.getInt("AccountId"));
				recAcc.setBalance(rs2.getDouble("Balance"));
				recAcc.setCustomerId(rs2.getInt("CustomerId"));
				recAcc.setStatus(rs2.getString("Status"));
				if(recAcc.getStatus().equals("Approved")) {
					throw new AccountNotApproved();
				}
				System.out.println("Recipient Account found!");
			} else {
				throw new AccountNotFound();
			}
			
			PreparedStatement ps3 = conn.prepareStatement(sqlAcc); //Confirming sender account
			ps3.setInt(1, myAccountId);
			ResultSet rs3 = ps3.executeQuery(sqlAcc);
			
			if(rs3.next()) {
				senAcc.setAccountId(rs3.getInt("AccountId"));
				senAcc.setBalance(rs3.getDouble("Balance"));
				if(senAcc.getBalance() < amount) {
					throw new CredentialException();
				}
				senAcc.setCustomerId(rs3.getInt("CustomerId"));
				senAcc.setStatus(rs3.getString("Status"));
				if(senAcc.getStatus().equals("Approved")) {
					throw new AccountNotApproved();
				}
				System.out.println("Recipient Account found!");
			} else {
				throw new AccountNotFound();
			}
			
			String sqlTr = "insert into \"Transaction\" (\"RecipientId\", \"SenderId\", \"Type\", \"Amount\") values (" + accountId+ ", " + myAccountId + ", " + 2 + ", " + amount + ");";
			Statement sTr = conn.createStatement();
			sTr.executeUpdate(sqlTr);
			
			String sqlUpdateAcc = "Update \"Account\" set \"Balance\" = ? where \"AccountId\" = ?;"; // Remove funds from sender account
			PreparedStatement ps4 = conn.prepareStatement(sqlUpdateAcc);
			ps4.setDouble(1, (senAcc.getBalance() - amount));
			ps4.setInt(2, senAcc.getAccountId());
			ps4.executeUpdate();
			
		} catch (AccountNotFound e){
			System.out.println("Account not Found");
		} catch (AccountNotApproved e){
			System.out.println("Account not Found");
		} catch (SQLException e) {
			System.out.println("Approve Transaction SQL Error");
		}
	}
}