package com.cms.Servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class exportContacts extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private static final String DB_URL = "jdbc:mysql://localhost:3306/cms";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "38426511";

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/csv");
        response.setHeader("Content-Disposition", "attachment; filename=\"contacts.csv\"");

        HttpSession session = request.getSession();
        Integer id = (Integer) session.getAttribute("id");  
        
        if (id == null) {
            request.setAttribute("errorMessage", "User ID not found in session.");
            request.getRequestDispatcher("error.jsp").forward(request, response);
            return;
        }

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement("SELECT name, phone, email FROM contacts WHERE user_id = ?")) {

            pstmt.setInt(1, id);  
            
            try (ResultSet rs = pstmt.executeQuery(); 
            	
            	PrintWriter writer = response.getWriter()) {

            	writer.println("Name,Phone,Email");

                while (rs.next()) {
                    String name = rs.getString("name");
                    String phone = rs.getString("phone");
                    String email = rs.getString("email");

                    writer.println(name + "," + phone + "," + email);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Error exporting contacts to CSV.");
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }
}
