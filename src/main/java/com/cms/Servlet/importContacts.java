package com.cms.Servlet;

import jakarta.security.auth.message.callback.PrivateKeyCallback.Request;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@MultipartConfig
public class importContacts extends HttpServlet {
	
	int userId;
    		
    private static final String DB_URL = "jdbc:mysql://localhost:3306/cms"; 
    private static final String DB_USER = "root"; 
    private static final String DB_PASSWORD = "38426511"; 

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
    	HttpSession session = request.getSession(false);
    	userId = (int) session.getAttribute("id");
    	
    	Part filePart = request.getPart("vcfFile"); 
        System.out.println("Received file part: " + filePart.getSubmittedFileName()); 
        if (filePart == null || filePart.getSize() == 0) {
            request.setAttribute("errorMessage", "No file uploaded.");
            request.getRequestDispatcher("error.jsp").forward(request, response);
            return;
        }

        String filePath = "C:/Users/King/eclipse-workspace/CMS/src/main/webapp/import/" + filePart.getSubmittedFileName();
        filePart.write(filePath); 

        try (BufferedReader br = new BufferedReader(new FileReader(new File(filePath)))) {
            String line;
            String name = null;
            String phoneNumber = null;
            String email = null;

            while ((line = br.readLine()) != null) {
                line = line.trim(); 
                if (line.startsWith("BEGIN:VCARD")) {
                    name = null;
                    phoneNumber = null;
                    email = null;
                } else if (line.startsWith("FN:")) {
                    name = line.substring(3).trim(); 
                } else if (line.startsWith("TEL:")) {
                    phoneNumber = line.substring(4).trim(); 
                } else if (line.startsWith("EMAIL:")) {
                    email = line.substring(6).trim(); 
                }

                if (line.isEmpty() || line.startsWith("END:VCARD")) {
                    if (name != null && phoneNumber != null) {
                        System.out.println("Saving contact: " + name + ", " + phoneNumber + ", " + email); // Debugging line
                        saveContact(name, phoneNumber, email,userId);
                    }
                }
            }
            response.sendRedirect("includes/contacts.jsp?vcf=success"); 
        } catch (IOException e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Error reading the VCF file.");
            request.getRequestDispatcher("includes/contacts.jsp?vcf=failed").forward(request, response);
        }
    }

    	
    private void saveContact(String name, String phoneNumber, String email , int userId) {
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement("INSERT INTO contacts (name, phone, email,user_id) VALUES (?, ?, ?,?)")) {

            pstmt.setString(1, name);
            pstmt.setString(2, phoneNumber);
            pstmt.setString(3, email);
            pstmt.setInt(4, userId);
            pstmt.executeUpdate();
            System.out.println("Contact saved successfully: " + name); 
        } catch (SQLException e) {
            e.printStackTrace(); 
        }
    }
}
