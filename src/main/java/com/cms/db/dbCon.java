package com.cms.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class dbCon {
	private static final String url = "jdbc:mysql://localhost:3306/cms";
	private static final String username = "YOUR_DB_USERNAME";
	private static final String password = "YOUR_PASSWORD";
	
	static {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("Driver Loaded Successfully");
		} catch (ClassNotFoundException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public static Connection getconnection() throws SQLException {
		return DriverManager.getConnection(url,username,password);
	}
}
