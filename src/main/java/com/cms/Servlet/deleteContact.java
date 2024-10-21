package com.cms.Servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.cms.db.dbUtil;

public class deleteContact extends HttpServlet {
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String contactIdParam = request.getParameter("id");
        
        if (contactIdParam != null && !contactIdParam.isEmpty()) {
            int contactId = Integer.parseInt(contactIdParam);
            dbUtil dbutil = new dbUtil();
            boolean isDeleted = dbutil.deleteContact(contactId); 

            if (isDeleted) {
                response.sendRedirect("includes/contacts.jsp"); 
            } else {
                response.sendRedirect("k.jsp"); 
            }
        } else {
            response.sendRedirect("erro.jsp"); 
        }
	}

}
