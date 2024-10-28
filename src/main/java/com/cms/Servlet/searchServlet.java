package com.cms.Servlet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.lang.invoke.StringConcatFactory;
import java.nio.channels.NonReadableChannelException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import com.cms.db.contacts_db;
import com.cms.db.dbCon;
import com.cms.db.dbUtil;

/**
 * Servlet implementation class searchServlet
 */
public class searchServlet extends HttpServlet {
	
	dbUtil db = new dbUtil();
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		int userId = (int)session.getAttribute("id");
		String searchname = request.getParameter("search");
		
		
		
		if(db.search(userId, searchname)!=null) {
			HttpSession session2 = request.getSession(false);
            List<?> searchResults = db.search(userId, searchname);
			session2.setAttribute("search",searchResults);
			response.sendRedirect("includes/contacts.jsp?search=success");
		}else {
			response.sendRedirect("includes/contacts.jsp?search=failed");
		}
		
	}
}
