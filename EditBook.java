package com.techpalle;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class EditBook
 */
@WebServlet("/EditBook")
public class EditBook extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditBook() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		  response.setContentType("text/html");
	        PrintWriter out = response.getWriter();
	        try {
	             Class.forName("com.mysql.cj.jdbc.Driver");
	            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/palle", "root", "febi123");
	            int bookid = Integer.parseInt(request.getParameter("bookid"));
	            String bookname = request.getParameter("bname");
	            String edition = request.getParameter("bedition");
	            double price = Double.parseDouble(request.getParameter("bprice"));
	           String query = "UPDATE books SET bookname=?, bookedition=?, bookprice=? WHERE bookid=?";
	            PreparedStatement stmt = con.prepareStatement(query);
	            stmt.setString(1, bookname);
	            stmt.setString(2, edition);
	            stmt.setDouble(3, price);
	            stmt.setInt(4, bookid);
                 stmt.executeUpdate();
	            stmt.close();
	            con.close();      
	            out.println("<h3 style='color:green;'>Book updated successfully!</h3>");
	        } catch (ClassNotFoundException e) {
	            e.printStackTrace();      
	        } catch (SQLException e) {
	            e.printStackTrace();         
	        }
	    }      
	}



