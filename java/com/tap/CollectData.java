package com.tap;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class CollectData extends HttpServlet{
	
	private Connection con;
	private PreparedStatement pstmt;
	private RequestDispatcher rd;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
			String username=req.getParameter("username");
			String email=req.getParameter("email");
			String adress=req.getParameter("adress");
			String phone=req.getParameter("phone");
			String password=req.getParameter("password");
			
			
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				con=DriverManager.getConnection("jdbc:mysql://localhost:3306/company_tap","root","Pavan@2002");
				pstmt=con.prepareStatement("insert into user (`name`,`email`,`adress`,`phone`,`password`)values(?,?,?,?,?)");
				pstmt.setString(1, username);
				pstmt.setString(2, email);
				pstmt.setString(3, adress);
				pstmt.setString(4, phone);
				pstmt.setString(5, password);
				
				int x=pstmt.executeUpdate();
				
				if(x==0) {
					rd=req.getRequestDispatcher("error.html");
					
					rd.forward(req, resp);
					
				}
				else {
					rd=req.getRequestDispatcher("success.html");
					
					rd.forward(req, resp);
					
					
				}
				
				
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
}

