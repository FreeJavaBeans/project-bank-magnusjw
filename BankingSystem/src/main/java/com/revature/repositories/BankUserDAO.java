package com.revature.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.revature.exceptions.CredentialException;
import com.revature.exceptions.UserNotFoundException;
import com.revature.models.Customer;
import com.revature.models.Employee;
import com.revature.models.User;
import com.revature.util.ConnectionUtil;

public class BankUserDAO implements BankUserRepository{
	
	private ConnectionUtil cu = ConnectionUtil.getConnectionUtil();

	@Override
	public User findUserByUsernameAndPassword(String username, String password) throws UserNotFoundException, CredentialException{
		Connection conn = cu.getConnection();
		try {
			
			String sql;
			
			if(username.substring(0, 3).equals("EMP")) {
				sql = "Select * from \"Employee\" where \"Username\" = ? and \"Password\" = ?;";
			} else {
				sql = "Select * from \"Customer\" where \"Username\" = ? and \"Password\" = ?;";
			}
			
			PreparedStatement ps = conn.prepareStatement(sql);

			ps.setString(1, username);
			ps.setString(2, password);

			ResultSet res = ps.executeQuery();
			
			if(username.substring(0, 3).equals("EMP")) { // Its an employee
				System.out.println("debug: ding");
				if(res.next()) {
					Employee E = new Employee();
					E.setEmployeeId(res.getInt("EmployeeId"));
					E.setFirstName(res.getString("FirstName"));
					E.setUsername(res.getString("Username"));
					return E;
				}else {
					throw new UserNotFoundException();
				}
			} else {
				if(res.next()) {
					Customer c = new Customer();
					c.setCustomerId(res.getInt("CustomerId"));
					c.setFirstName(res.getString("FirstName"));
					c.setUsername(res.getString("Username"));
					return c;
				}else {
					throw new UserNotFoundException();
				}
			}
		}catch(SQLException e) {
			e.printStackTrace();
			throw new CredentialException();
		}
	}

	
	@Override
	public Customer insertCustomer(String username, String password, String firstName, String lastName){
		Connection conn = cu.getConnection();
		
		try {
			
			String sql = "INSERT INTO \"Customer\" (\"Username\", \"Password\", \"FirstName\", \"LastName\") VALUES (?, ?, ?, ?);";
			
			PreparedStatement ps = conn.prepareStatement(sql);

			ps.setString(1, username);
			ps.setString(2, password);
			ps.setString(3, firstName);
			ps.setString(4, lastName);

			ps.executeUpdate();

		}catch(SQLException e) {
			e.printStackTrace();
			System.out.println("debug: DAO");
		}
		
		Customer c = new Customer(username, password, firstName, lastName);
		return c;

	}

	@Override
	public Customer findUserByUserId(int userId) {
		// TODO Auto-generated method stub
		return null;
	}
	
}