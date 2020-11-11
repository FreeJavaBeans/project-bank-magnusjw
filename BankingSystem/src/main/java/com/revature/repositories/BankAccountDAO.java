package com.revature.repositories;

import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.revature.models.Account;
import com.revature.models.Customer;
import com.revature.util.ConnectionUtil;

public class BankAccountDAO implements BankAccountRepository{
	
	private ConnectionUtil cu = ConnectionUtil.getConnectionUtil();
	
	public Set<Customer> findAllCustomers() {
		
		Connection conn = cu.getConnection();
		
		Set<Customer> allCustomers = new HashSet<Customer>();
		
		try {
			Statement statementObject = conn.createStatement();
			
			String query = "select * from \"Customer\"";
			
			ResultSet results = statementObject.executeQuery(query);
			
			while(results.next()) {
				Customer c = new Customer();
				c.setUsername(results.getString("Username"));
				c.setPassword(results.getString("Password"));
				c.setFirstName(results.getString("FirstName"));
				c.setLastName(results.getString("LastName"));
				allCustomers.add(c);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Get all Customers Failed");
		}
		
		return allCustomers;
	}

	public Account findCustomerAccount(int id) {
		
		Connection conn = cu.getConnection();
		
		try {
			
			PreparedStatement ps = conn.prepareStatement("select * from \"Account\" where \"CustomerId\" is "
					+ "(select \"CustomerId\" from \"Customer\" where \"Customerd\" = ?;");
			
			ps.setInt(1, id); // put int id into statement
			
			ResultSet rs = ps.executeQuery(); // execute Statement

			if(rs.next()) { //Process result set to a food object
				Account acc = new Account();
				acc.setBalance(rs.getDouble("Balance"));
				return acc;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Find Customer Account Failed");
		}
		System.out.println("Find Customer Account Failed 2");
		return null;
	}


}