package servlets;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jdbc.JDBC;

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
	    		System.out.println("are we here after function?");
	    		//response.sendRedirect("http://www.google.com");
	    		//response.sendRedirect("/landing.html");
	    		ServletContext sc = getServletContext();
	    		try {
					sc.getRequestDispatcher("/landing.jsp").forward(request, response);
				} catch (ServletException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	    }else{
	    		pw.print("Invalid login");
		    	pw.flush();
			pw.close();
	    }
    } 
}