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
public class SaveServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public SaveServlet() {
    		super();
        // TODO Auto-generated constructor stub
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("gets to do get of save servlet");
		String pairString = (String) request.getSession().getAttribute("pair");	
		Pair p;
		try {
			p = Pair.fromString(pairString);
			PrintWriter pw = response.getWriter();
		    db_connection sqlDB = new db_connection();
		    System.out.println("are we here?");
		    if(sqlDB.createImage(p, (String)request.getSession().getAttribute("username"))) {
		    		pw.print("VALID_SAVE");
		    }else{
		    		pw.print("Invalid save");
		    }
		    pw.flush();
		    pw.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}


}
