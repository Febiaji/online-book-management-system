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
 * Servlet implementation class RegBook
 */
@WebServlet("/RegBook")
public class RegBook extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegBook() {
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
		String bname = request.getParameter("bname");
		String bedition = request.getParameter("bedition");
		double bprice = Double.parseDouble(request.getParameter("bprice"));
		out.println("<h1> Bookname is : "+bname+" </h1>");
		out.println("<h1> Bookedition is :" + bedition +" </h1>");
		out.println("<h1> Bookprice  is :" + bprice +" </h1>");
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/palle", "root", "febi123");
			String query = "INSERT INTO books (bookname, bookedition, bookprice) VALUES (?, ?, ?)"; 
            PreparedStatement stmt = con.prepareStatement(query);
            stmt.setString(1, bname);
            stmt.setString(2, bedition);
            stmt.setDouble(3, bprice);
            stmt.executeUpdate();
            stmt.close();
             con.close();	
			 }
      catch (ClassNotFoundException a) {
				
			a.printStackTrace();
		}
        catch (SQLException a) {
	
	a.printStackTrace();
	response.getWriter().println("Error: " + a.getMessage());
	
}
	}

}
