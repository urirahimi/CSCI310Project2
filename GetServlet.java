package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jdbc.db_connection;
import utilities.Pair;

/**
 * Servlet implementation class SaveServlet
 */
public class GetServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public GetServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("gets to do get of get servlet");
		PrintWriter pw = response.getWriter();
		db_connection sqlDB = new db_connection();
		System.out.println("are we here?");
		
		for(Pair p: sqlDB.getImages()) {
				pw.print(p.toString());
		}
		pw.flush();
		pw.close();
	
	}


}
