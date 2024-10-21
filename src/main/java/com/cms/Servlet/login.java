package com.cms.Servlet;

import jakarta.servlet.ServletException;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import com.cms.db.dbUtil;
import com.cms.inter.cmsInt;

public class login extends HttpServlet {
	
	cmsInt user = new dbUtil();

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		if(user.userExist(email,password)) {	
			HttpSession session = request.getSession();
			int id = user.checkId(email);
			session.setAttribute("id",id);
			response.sendRedirect("./includes/contacts.jsp");		
		}else {
			response.sendRedirect("login.jsp?error=-1");
		}
	}

}
