package com.revature.repositories;

import java.util.HashSet;
import java.util.Set;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.revature.exceptions.CredentialException;
import com.revature.models.Account;
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
	public double withdrawBalance(int accountId, double amount) throws CredentialException{
		
		Connection conn = cu.getConnection();
		
		String sql = "select \"Balance\" from \"Account\" where \"AccountId\" = ?;";
		String sql2 = "Update \"Account\" set \"Balance\" = ? where \"AccountId\" = ?;";
		
		try {
			
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setInt(1,  accountId);
			
			ResultSet rs = ps.executeQuery();
		
			if(rs.next()) {
				double balance = (rs.getDouble("Balance"));
				if(balance < amount) {
					throw new CredentialException("Account does not have enough money to withdraw");
				}
				amount -= balance;
			}
			
			PreparedStatement ps2 = conn.prepareStatement(sql2);
			
			ps2.setDouble(1, amount);
			ps2.setInt(2, accountId);
			
			ps2.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Get all Customers Failed");
		}
		
		return amount;
		
	}

	@Override
	public double depositBalance(int accountId, double amount){
		Connection conn = cu.getConnection();
		
		String sql = "select \"Balance\" from \"Account\" where \"AccountId\" = ?;";
		String sql2 = "Update \"Account\" set \"Balance\" = ? where \"AccountId\" = ?;";
		
		try {
			
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setInt(1,  accountId);
			
			ResultSet rs = ps.executeQuery();

			if(rs.next()) {
				double balance = (rs.getDouble("Balance"));
				amount += balance;
			}
			
			PreparedStatement ps2 = conn.prepareStatement(sql2);
			
			ps2.setDouble(1, amount);
			ps2.setInt(2, accountId);
			
			ps2.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Get all Customers Failed");
		}
		return amount;
	}
}