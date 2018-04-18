package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jdbc.db_connection;

/**
 * Servlet implementation class Registration
 */
public class Registration extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { 
	    	String password = request.getParameter("password");
	    	String email = request.getParameter("email");
	    	PrintWriter pw = response.getWriter();
	    db_connection sqlDB = new db_connection();
	    System.out.println("are we here?");
	    if(sqlDB.signup(email, password)) {
	    		pw.print("VALID_REGISTRATION");
	    }else{
	    		pw.print("Invalid Registration");
	   }
	    pw.flush();
	    pw.close();
	}
}
