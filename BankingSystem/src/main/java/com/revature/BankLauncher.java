package com.revature;

import java.sql.Connection;
import java.sql.SQLException;

import com.revature.menus.MenuSelector;
import com.revature.util.ConnectionUtil;

public class BankLauncher {
	
	public static void main(String[] args) throws SQLException{
		
		ConnectionUtil cu = ConnectionUtil.getConnectionUtil();
		Connection conn = cu.getConnection();
		
		if(conn != null && !conn.isClosed()) {
			System.out.println("Connected to Database");
		}

		MenuSelector ms = MenuSelector.getMenuSelector();

		while(true) {
			System.out.println(ms.display());
			ms.handleInput();
			if(MenuSelector.getMenuSelector().isExit() == true) {
				return;
			}
		}
	}
}