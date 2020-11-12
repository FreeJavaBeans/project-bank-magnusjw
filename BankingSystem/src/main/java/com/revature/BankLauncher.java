package com.revature;

import java.sql.Connection;

import java.sql.SQLException;
import com.revature.menus.MenuSelector;
import com.revature.util.ConnectionUtil;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class BankLauncher {
	
	public static Logger Logger = LogManager.getLogger("com.revature.bank");
	
	public static void main(String[] args){
		
		ConnectionUtil cu = ConnectionUtil.getConnectionUtil();
		Connection conn = cu.getConnection();
		
		try {
			if(conn != null && !conn.isClosed()) {
				BankLauncher.Logger.info("Connection to Database");
				System.out.println("Connected to Database");
			}
		} catch (SQLException e) {
			System.out.println("Failed to Connect to Database");
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