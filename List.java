package com.techpalle;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Login
 */
@WebServlet("/List")
public class List extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public List() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
	    PrintWriter out = response.getWriter();
	    out.println("<html><head><title>Book List</title></head><body>");
        out.println("<h1>Book List</h1>");
	    out.println("<table border='1'>");
	    out.println("<tr><th>BookId</th><th>Book Name</th><th>Edition</th><th>Price</th><th>Edit</th><th>Delete</th></tr>");
try {
	Class.forName("com.mysql.cj.jdbc.Driver");
	Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/palle", "root", "febi123");
	Statement s = con.createStatement();
	String q = "select * from books"; 
	ResultSet r = s.executeQuery(q);
	while(r.next()== true)
	{   out.println("<tr>");
    out.println("<td>" + r.getInt(1) + "</td>");
    out.println("<td>" + r.getString(2) + "</td>");
    out.println("<td>" + r.getString(3) + "</td>");
    out.println("<td>" + r.getDouble(4) + "</td>");
    out.println("<td><a href='EditForm?bookid=" + r.getInt(1) + "'>Edit</a></td>");
    out.println("<td><a href='DeleteBook?bookid=" + r.getInt(1) + "'>Delete</a></td>");
    out.println("</tr>");
	}

	s.close();
	 con.close();
	 
} catch (ClassNotFoundException e) {
	
	
	e.printStackTrace();
}
catch (SQLException e) {

e.printStackTrace();
}
out.println("</table>");
out.println("</body></html>");
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
