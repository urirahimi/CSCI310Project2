package servlets;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jdbc.db_connection;

/**
 * Servlet implementation class Validation
 */
@WebServlet("/Validation")
public class Validation extends HttpServlet {
	private static final long serialVersionUID = 1L;
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
	    	String password = request.getParameter("password");
	    	String email = request.getParameter("email");
	    	request.getSession().setAttribute("username", email);
	    	PrintWriter pw = response.getWriter();
	    db_connection sqlDB = new db_connection();
	    System.out.println("are we here?");
	    if(sqlDB.login(email, password)) {
	    		pw.print("VALID_LOGIN");
	    }else{
	    		pw.print("Invalid login");
	    }
	    pw.flush();
	    pw.close();
    } 
}