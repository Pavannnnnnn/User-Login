package com.tap;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


@WebServlet("/LoginCheck")
public class LoginCheck extends HttpServlet {
	

	private Connection con;
	private PreparedStatement pstmt;
	private ResultSet rs;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String email=req.getParameter("email");
		String password=req.getParameter("password");
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/company_tap","root","Pavan@2002");
			pstmt=con.prepareStatement("select * from user where email=?");
			pstmt.setString(1,email);
			
			rs=pstmt.executeQuery();
			
			if(rs.next()) {
				if(password.equals(rs.getString("password"))) {
					
//					HttpSession session = ;
					req.getSession().setAttribute("name",rs.getString("name"));
					resp.sendRedirect("home.jsp");
					
					
				}
				else {
					resp.sendRedirect("Wrong.html");
				}
			}
			else {
				resp.sendRedirect("error.html");
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
