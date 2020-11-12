package com.revature.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.revature.exceptions.UserNotFoundException;
import com.revature.models.Customer;
import com.revature.models.Employee;
import com.revature.models.User;
import com.revature.util.ConnectionUtil;

public class BankIntroDAO implements BankIntroRepository{
	
	private ConnectionUtil cu = ConnectionUtil.getConnectionUtil();

	@Override
	public User findUserByUsernameAndPassword(String username, String password){
		Connection conn = cu.getConnection();
		
		Employee emp = new Employee();
		Customer c = new Customer();

		boolean employee = false;
		
		if(username.substring(0, 3).equals("EMP")){
			employee = true;
		}
		
		try {
			
			String sql;
			
			if(employee) {
				sql = "Select * from \"Employee\" where \"Username\" = ? and \"Password\" = ?;";
			} else {
				sql = "Select * from \"Customer\" where \"Username\" = ? and \"Password\" = ?;";
			}
			
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, username);
			ps.setString(2, password);
			ResultSet res = ps.executeQuery();
			
			if(employee) {
				if(res.next()) {
					emp.setEmployeeId(res.getInt("EmployeeId"));
					emp.setFirstName(res.getString("FirstName"));
					emp.setUsername(res.getString("Username"));
				}else {
					throw new UserNotFoundException();
				}
			} else {
				if(res.next()) {

					c.setCustomerId(res.getInt("CustomerId"));
					c.setFirstName(res.getString("FirstName"));
					c.setUsername(res.getString("Username"));
				}else {
					throw new UserNotFoundException();
				}
			}
		} catch(UserNotFoundException e) {
			System.out.println("User Not Found");
		}catch(SQLException e) {
			System.out.println("Find Customer SQL Error");
		}
		if(employee) {
			return emp;
		} else {
			return c;
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
			System.out.println("Create Customer SQL Error");
		}
		
		Customer c = new Customer(username, password, firstName, lastName);
		return c;

	}
}