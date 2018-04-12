package servlets;
import jdbc.*;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Validation
 */
@WebServlet("/Validation")
public class Validation extends HttpServlet {
	private static final long serialVersionUID = 1L;
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException{
	    	String password = request.getParameter("password");
	    	String email = request.getParameter("email");
	    	PrintWriter pw = response.getWriter();
	    JDBC sqlDB = new JDBC();
	    System.out.println("are we here?");
	    if(sqlDB.login(email, password)) {
	    		System.out.println("are we here?");
	    		response.sendRedirect("http://www.google.com");
	    }else{
	    		pw.print("Invalid login");
		    	pw.flush();
			pw.close();
	    }
    } 
}