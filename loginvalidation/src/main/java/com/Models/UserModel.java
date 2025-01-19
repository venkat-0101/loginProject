package com.Models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.Entity.User;
import com.config.DatabaseConfig;

//This Model class is used for interacting with the database and doing database operations
//3 steps involved
//step 1: establish database connection
//step 2: create the query
//step 3: execute the query

public class UserModel {

	private final String dbUrl = "jdbc:mysql://127.0.0.1:3306/loginvalidation?useSSL=false";
	private final String dbUsername = "Balaji";
	private final String dbPassword = "IncorrectPassword@123";

	// step 1 : connection object initialization
	Connection con = DatabaseConfig.getConnection(dbUrl, dbUsername, dbPassword);
	String query = null;
	PreparedStatement preparestmt = null;
	Statement stmt = null;
	ResultSet rs = null;
	
	
	
	public List<User> dispUsers() {
		List<User> userList = new ArrayList<>();

		

		// step 2 : creation of query
		query = "Select * from user";
		try {
			stmt = con.createStatement();
			// step 3: execution of statement
			rs = stmt.executeQuery(query);
			while (rs.next()) {
				userList.add(new User(rs.getInt("user_id"), rs.getString("user_name"), rs.getString("user_email"),
						rs.getString("user_password"), rs.getString("user_retypepassword")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return userList;
	}

	public void addUser(User user) {
		query = "insert into user (user_name, user_email, user_password, user_retypepassword) values (?, ?, ?, ?);";
		

		try {
			preparestmt = con.prepareStatement(query);
			preparestmt.setString(1, user.getUsername());
			preparestmt.setString(2, user.getUseremail());
			preparestmt.setString(3, user.getUserpassword());
			preparestmt.setString(4, user.getUserretypepassword());

			preparestmt.execute();
			preparestmt.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	
	public Object[] checkUser(User user)
	{
		Object[] resultObject = new Object[2];
		boolean result = false;
		String usernameFromQuery = null;
		String passwordFromQuery = null;
		query = "Select * from user where user_email=?;";
		
		//block of statements to validate the user with username and password
		try {
			preparestmt = con.prepareStatement(query);
			preparestmt.setString(1, user.getUseremail());
			
			rs = preparestmt.executeQuery();
			
			//If the result set returned any data and the cursor moved to the first row the method continues 
			//else the result variable is assigned to false 
			//considering no such record in DB
			if(!(rs.next()))
			{
				result = false;
			}
			else {
				usernameFromQuery = rs.getString("user_name");
				passwordFromQuery = rs.getString("user_password");
				
				result = passwordFromQuery.equals(user.getUserpassword()) ? true : false;
			}
		
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		resultObject[0] = result;
		resultObject[1] = usernameFromQuery;
		return resultObject;
	}
}
