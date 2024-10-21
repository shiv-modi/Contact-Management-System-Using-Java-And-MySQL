package com.cms.Servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import com.cms.db.contacts_db;
import com.cms.db.dbUtil;
import com.cms.inter.cmsInt;

public class editContact extends HttpServlet {
	cmsInt editContacts = new dbUtil();
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    String editName = request.getParameter("editName");
	    String editPhone = request.getParameter("editPhone");
	    String editEmail = request.getParameter("editEmail");
	    
	    contacts_db contact = new contacts_db();
	    contact.setName(editName);
	    contact.setEmail(editEmail);
	    contact.setPhone(editPhone);
	    
	    HttpSession session = request.getSession(false);
	    
	    if (session != null) { 
	        Integer id = (Integer) session.getAttribute("id");
	        System.out.println(id);
	        
	        if (id != null) { 
	            if (editContacts.editContacts(contact, id)) {
	                response.sendRedirect("includes/contacts.jsp?editContact=success");
	            } else {
	                response.sendRedirect("includes/contacts.jsp?editContact=failed");
	            }
	        } else {
	            response.sendRedirect("index.jsp?error=missingId");
	        }
	    } else {
	        response.sendRedirect("index.jsp?error=sessionExpired");
	    }
	}


}
