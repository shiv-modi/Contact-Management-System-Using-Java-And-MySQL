package com.cms.db;

import java.lang.invoke.StringConcatFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.cms.inter.cmsInt;
import com.mysql.cj.Query;

import jakarta.security.auth.message.callback.PrivateKeyCallback.Request;
import jakarta.servlet.http.HttpSession;


public class dbUtil implements cmsInt{
	
	private Connection connection;
	
	
	public dbUtil() {
		try {
			this.connection = dbCon.getconnection();
			System.out.println("connection Establish");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public boolean addUser(users user) {
		String qwery = "INSERT INTO users(full_name, email, password) VALUES(?,?,?)";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(qwery);
			preparedStatement.setString(1,user.getFullname());
			preparedStatement.setString(2,user.getEmail());
			preparedStatement.setString(3,user.getPassword());
			int rowAffect = preparedStatement.executeUpdate();
			if (rowAffect>0) {
				return rowAffect>0;
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return false;
	}
	
	public  boolean userExist(String email , String password) {
		String qwery1 = "SELECT * FROM users WHERE email = ? and password = ?";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(qwery1);
			preparedStatement.setString(1,email);
			preparedStatement.setString(2,password);
			ResultSet rSet = preparedStatement.executeQuery();
			return rSet.next();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return false;
	}
	
	public int checkId(String email) {
		String qwery2 = "SELECT * FROM users WHERE email = ?";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(qwery2);
			preparedStatement.setString(1,email);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				return id;
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return -1;
	}
	
	public boolean addContact(contacts_db contact , int userId) {
		String qwery = "INSERT INTO contacts (name, phone, email, profile_photo, user_id) VALUES (?, ?, ?, ?, ?);";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(qwery);
			preparedStatement.setString(1,contact.getName());
			preparedStatement.setString(2,contact.getPhone());
			preparedStatement.setString(3,contact.getEmail());
			preparedStatement.setBytes(4,contact.getProfilePhoto());
			preparedStatement.setInt(5,userId);
			int rowAffect = preparedStatement.executeUpdate();
			if (rowAffect > 0) {
				return rowAffect > 0;
			}
		} catch (SQLException e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
		return false;
	}
	
	public List<contacts_db> getContacts(int userId){
		List<contacts_db> cont = new ArrayList<>();
		String qwery = "select * from contacts Where user_id = ?";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(qwery);
			preparedStatement.setInt(1,userId);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				contacts_db contactss = new contacts_db();
				contactss.setId(resultSet.getInt("id"));
				contactss.setName(resultSet.getString("name"));
				contactss.setEmail(resultSet.getString("email"));
				contactss.setPhone(resultSet.getString("phone"));
				contactss.setProfilePhoto(resultSet.getBytes("profile_photo"));
				cont.add(contactss);
			}
			
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return cont;
	}
	
	public boolean editContacts(contacts_db contact, int userId) {
	    String query = "UPDATE contacts SET name = ?, phone = ?, email = ? WHERE user_id = ? AND ((name = ? AND phone = ?) OR (email = ?));";
	    try {
	        PreparedStatement preparedStatement = connection.prepareStatement(query);
	        
	        preparedStatement.setString(1, contact.getName()); 
	        preparedStatement.setString(2, contact.getPhone()); 
	        preparedStatement.setString(3, contact.getEmail()); 
	        preparedStatement.setInt(4, userId); 
	        preparedStatement.setString(5, contact.getName()); 
	        preparedStatement.setString(6, contact.getPhone()); 
	        preparedStatement.setString(7, contact.getEmail()); 
	        
	        int rowAffected = preparedStatement.executeUpdate();
	        return rowAffected > 0; 
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return false; 
	}
	
	public boolean deleteContact(int contactId) {
	    String query = "DELETE FROM contacts WHERE id = ?";
	    try (
	         PreparedStatement pstmt = connection.prepareStatement(query)) {
	        pstmt.setInt(1, contactId);
	        int rowsAffected = pstmt.executeUpdate();
	        return rowsAffected > 0; // Return true if a row was deleted
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return false;
	    }
	}


}













