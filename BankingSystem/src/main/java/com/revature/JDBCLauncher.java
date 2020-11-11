package com.revature;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Set;

import com.revature.models.Customer;
import com.revature.repositories.BankAccountDAO;
import com.revature.repositories.BankAccountRepository;
import com.revature.util.ConnectionUtil;

public class JDBCLauncher {

	public static void main(String[] args) throws SQLException {
		
		
		
		ConnectionUtil cu = ConnectionUtil.getConnectionUtil();
		Connection conn = cu.getConnection();
		
		if(conn != null && !conn.isClosed()) {
			System.out.println("We did it!!");
		}
		
		BankAccountRepository rm = new BankAccountDAO();
		
		Set<Customer> allCustomers = rm.findAllCustomers();
		
		for(Customer c : allCustomers) {
			System.out.println(c);
		}
		
	}
}
