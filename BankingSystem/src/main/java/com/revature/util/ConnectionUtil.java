package com.revature.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtil {
	//Singleton pattern!
	//The singleton pattern involves making a private constructor
	//so no other class can instance this object
	//store a static reference to the single copy of the object made
	//we provide a public method to access that single instance
	
	//single instance of the connection util
	private static ConnectionUtil singleton = new ConnectionUtil();
	
	private Connection conn;
	
	//jdbc:postgresql:localhost:5432/postgres
	
	//private constructor so no one else can call
	private ConnectionUtil() {
		super();
		//getting secrets into a java program
		//two ways
		//read from a file, some sort of config like .xml or .properties or a .yaml
		//or we can get a value from the system environment variables
		//store our secrets in the system environment variables
		//retrieve them here and use them
		String password = System.getenv("DB_PASSWORD");
		String username = System.getenv("DB_USERNAME");
		String url = System.getenv("DB_URL");
		try {
			this.conn = DriverManager.getConnection(url, username, password);
		} catch(SQLException e){
			System.out.println("Failed to Connect to DB");
			System.out.println("Password: " + password);
			System.out.println("username: " + username);
			System.out.println("url: " + url);
			e.printStackTrace();
		}
		
	}
	
	//public getter for the static reference
	public static ConnectionUtil getConnectionUtil() {
		return singleton;
	}
	
	
	public Connection getConnection() {
		return conn;
	}

}
