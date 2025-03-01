package com.techpalle;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class EditForm
 */
@WebServlet("/EditForm")
public class EditForm extends HttpServlet {
	private static final long serialVersionUID = 1L;  
    public EditForm() {
        super();
        // TODO Auto-generated constructor stub
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        int bookid = Integer.parseInt(request.getParameter("bookid"));
        String name = "", edition = "";
        double price = 0;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/palle", "root", "febi123");
            String query = "SELECT * FROM books WHERE bookid=?";
            PreparedStatement stmt = con.prepareStatement(query);
            stmt.setInt(1, bookid);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                name = rs.getString("bookname");
                edition = rs.getString("bookedition");
                price = rs.getDouble("bookprice");
            }
            stmt.close();
            con.close();
        } 
        catch (ClassNotFoundException a) {
    		
        	a.printStackTrace();
        }
        catch (SQLException a) {

        a.printStackTrace();
        }

        out.println("<html><head><title>Edit Book</title></head><body>");
        out.println("<h1>Edit Book Details</h1>");
        out.println("<form action='EditBook' method='post'>");
        out.println("<input type='hidden' name='bookid' value='" + bookid + "'/>");
        out.println("Book Name: <input type='text' name='bname' value='" + name + "' required/><br><br>");
        out.println("Book Edition: <input type='text' name='bedition' value='" + edition + "' required/><br><br>");
        out.println("Book Price: <input type='number' name='bprice' value='" + price + "' required/><br><br>");
        out.println("<input type='submit' value='Save'/>");
        out.println("<input type='button' value='Cancel' onclick='window.location.href=\"ListBooks\"'/>");
        out.println("</form>");
        out.println("</body></html>");
    } 
	 protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
