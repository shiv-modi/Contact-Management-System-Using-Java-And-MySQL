
package com.cms.Servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.invoke.StringConcatFactory;

import com.cms.db.contacts_db;
import com.cms.db.dbUtil;
import com.cms.inter.cmsInt;


@MultipartConfig
public class contacts extends HttpServlet {
	private static final long serialVersionUID = 1L;
	cmsInt addContact = new dbUtil();

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String phone = request.getParameter("phone");
		
        Part filePart = request.getPart("photo"); 
        byte[] imagedata = null;
        
        if (filePart != null && filePart.getSize() > 0) {
            InputStream fileInputStream = filePart.getInputStream();
            imagedata = fileInputStream.readAllBytes();
        } 
		
		contacts_db contact = new contacts_db();
		
		contact.setName(name);
		contact.setEmail(email);
		contact.setPhone(phone);
		contact.setProfilePhoto(imagedata);
		
		HttpSession session = request.getSession(false);
		int userId = (int) session.getAttribute("id");
		
		if (addContact.addContact(contact,userId)) {
			response.sendRedirect("includes/contacts.jsp?addContact=success");
		}else {
			response.sendRedirect("includes/contacts.jsp?addContact=failed");

		}
		
	}

}
