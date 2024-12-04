package com.tap;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class forgetPassword
 */
@WebServlet("/forgetServlet")
public class forgetPassword extends HttpServlet {
    
    private static final String DB_URL = "jdbc:mysql://localhost:3306/company_tap";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "Pavan@2002";

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection con = null;
        PreparedStatement pstmt = null;

        try {
            // Step 1: Load JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Step 2: Establish connection
            con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            con.setAutoCommit(false);

            // Step 3: Prepare SQL query
            String query = "UPDATE user SET password = ? WHERE email = ?";
            pstmt = con.prepareStatement(query);

            // Step 4: Set parameters from the request
            String email = req.getParameter("email");
            String password = req.getParameter("password");
            pstmt.setString(1, password);
            pstmt.setString(2, email);

            // Step 5: Execute update
            int rowsUpdated = pstmt.executeUpdate();

            if (rowsUpdated == 1) {
                con.commit();
                resp.sendRedirect("passwordchange.html"); // Redirect to success page
            } else {
                con.rollback();
                resp.sendRedirect("error.html"); // Redirect to error page
            }
        } catch (Exception e) {
            e.printStackTrace();
            if (con != null) {
                try {
                    con.rollback();
                } catch (SQLException rollbackEx) {
                    rollbackEx.printStackTrace();
                }
            }
            resp.sendRedirect("error.html");
        } finally {
            // Step 6: Close resources
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
