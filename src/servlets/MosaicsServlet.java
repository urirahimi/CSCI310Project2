package servlets;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;

import javax.imageio.ImageIO;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.javafx.geom.Shape;

import builders.CollageBuilder;
import services.ImageService;
import utilities.Pair;

/**
 * Servlet implementation class MosaicsServlet
 * Servlet takes a topic and returns a Collage of said topic
 */
@WebServlet("/search")
public class MosaicsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		response.setContentType("image/png");
		if(request.getSession().getAttribute("list") == null) {
			ArrayList<Pair> list;
			list = new ArrayList<Pair>();
			request.getSession().setAttribute("list", list);
		}
//		else {
//			list = (HashMap<String, String>)(request.getSession().getAttribute("list"));
//		}
		long startTime = System.currentTimeMillis();
		String query = request.getParameter("topic");
		String letterShape = request.getParameter("shape");
		String filter = request.getParameter("filter");
		Character letter = null;
		if (letterShape != null && !letterShape.isEmpty()) {
			letter = letterShape.charAt(0);
		}

		List<BufferedImage> images = null; 
		BufferedImage collage = null;
		
		if (query != null)
		{
			images = ImageService.getImages(query);
		}
		
		
		// If valid collage
		if (images != null && images.size() >= 30) {
			// if no shape is inputed, letter == null
			// therefore no forbidden grids in the 5x6 grid
			collage = CollageBuilder.buildCollage(images, letter, true, filter);
			request.getSession().setAttribute("collage", collage);
			request.getSession().setAttribute("query", query);
			request.getSession().setAttribute("error", false);
	     

	  
		} else {
			request.getSession().setAttribute("error", true);
		}
        RequestDispatcher dispatch = getServletContext().getRequestDispatcher("/CollageDisplay.jsp");
        dispatch.forward(request,response);
//		long endTime = System.currentTimeMillis();
//
//        long duration = (endTime - startTime);
//        
//        System.out.println("Duration: " + duration / 1000.0 + " seconds");
	}

}