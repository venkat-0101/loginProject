package com.controllers;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.Entity.User;
import com.Models.UserModel;

public class Home extends HttpServlet {
	
	UserModel userModel = new UserModel();
	User user;
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		
		switch(action)
		{
		case "register":
			addUser(request, response);
			break;
			
		case "login":
			validateUser(request, response);
			break;
			
		case "display":
			displayUser(request, response);
			break;
		}
	}
	
	protected void addUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		//getting the post request values and assigning them to respective variables
		String user_name = request.getParameter("user_name");
		String user_email = request.getParameter("user_email");
		String user_password = request.getParameter("user_password");
		String user_retypepassword = request.getParameter("user_retypepassword");
		
		//creating a user entity object to assign the values to entity
		user = new User(user_name, user_email, user_password, user_retypepassword);
		userModel.addUser(user);
		RequestDispatcher rd =  request.getRequestDispatcher("welcomePage.jsp");
		System.out.println("User added successfully. Login to continue");
		rd.forward(request, response);
	}
	
	protected void validateUser(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException
	{
		PrintWriter pw = response.getWriter();
		String email = request.getParameter("user_email");
		String password = request.getParameter("user_password");
		String username = null; //username to be displayed on the welcome page
		String messageString = null; //to display any inline error messages
		user = new User(email, password);	
		Object[] resultObjectArray = userModel.checkUser(user);
		boolean userValidationStatus = (boolean) resultObjectArray[0];
		String page = null;
		
		if(resultObjectArray[1] == null)
		{
			page = "welcomePage.jsp";
			System.out.println("Invalid User. Please register before logging in");
		}
		else
		{
		 username = resultObjectArray[1].toString();
		 if(userValidationStatus)
			{
				HttpSession session;
				session = request.getSession(true);
				System.out.println("logged in successfully");
				System.out.println("New session created that lasts for 10 seconds"+session);
				session.setAttribute("title", "home");
				session.setAttribute("name", username);
				page="homePage.jsp";
				session.setMaxInactiveInterval(10);
				session.invalidate();
				System.out.println("Returning session after invalidating"+session);
			}
			else
			{
				username = null;
				HttpSession session;
				session = request.getSession(false);
				session.invalidate();
				page = "welcomePage.jsp";
				System.out.println("Invalid user credentials");
			}
		}
		pw.println(messageString);
		response.sendRedirect(page+"?user="+username);
	}
	
	protected void displayUser(HttpServletRequest request, HttpServletResponse respone)throws ServletException, IOException
	{
		List<User> userList = new ArrayList<>();
		userList = new UserModel().dispUsers();
		request.setAttribute("users", userList);
		request.setAttribute("title", "User List");
		request.getRequestDispatcher("displayUser.jsp").forward(request, respone);
	}
	
	

}
