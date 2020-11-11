package com.revature.repositories;

import java.util.HashSet;
import java.util.Set;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
					+ "(select \"CustomerId\" from \"Customer\" where \"CustomerId\" = ?);");
			
			
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
	public void changeAccountBalance(double amount) {
		
		Connection conn = cu.getConnection();
		
		String sql = "Update \"Account\" set \"balance\" = ? where \"AccountId\" = ?;";
		
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setDouble(1, amount);
			ps.setInt(2, id);
			
			ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Get all Customers Failed");
		}
		
	}
}