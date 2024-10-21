package com.cms.Servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.cms.db.dbUtil;
import com.cms.db.users;
import com.cms.inter.cmsInt;


public class signUp extends HttpServlet {
	
	cmsInt adduser = new dbUtil();
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String fullname = request.getParameter("fullname");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		users user = new users();
		user.setFullname(fullname);
		user.setEmail(email);
		user.setPassword(password);
		
		if (adduser.addUser(user)) {
			response.sendRedirect("index.jsp?flag=success");
			return;
		}
		
		
		
	}

}
