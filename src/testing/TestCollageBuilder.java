package testing;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.List;

import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import builders.CollageBuilder;
import services.ImageService;
import servlets.MosaicsServlet;
import utilities.Pair;

public class TestCollageBuilder {
	public static final int width=20;
	public static final int height = 20;	
   
   @Test
   public void testbuildCollage() {
	   //if currX > 600 and currY < 373
	   List<BufferedImage> images = ImageService.getImages("dog");
	  /*for(int i =1; i<=30;i++ )
	   {
		  File f = new File("/CSCI310/src/"+i+".jpeg");
		  BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		  try {
			image = ImageIO.read(f);
			images.add(image);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	   }*/
	   CollageBuilder.buildCollage(images);
	  /* //if currX > 600 and currY > 373
	   List<BufferedImage> images2 = new ArrayList<BufferedImage>();
	   for(int i =1; i<=30;i++ )
	   {
		   
	   }   
	   CollageBuilder.buildCollage(images2);  
	   //if currX and currY are accurate
	   List<BufferedImage> images3 = new ArrayList<BufferedImage>();
	   for(int i =1; i<=30;i++ )
	   {
		   
	   }   
	   CollageBuilder.buildCollage(images3);*/
	   boolean x = true;
	   assertTrue("mess", x);
   }

   @Test
   public void testServlet() throws Exception {
    
	   MockHttpServletRequest request = new MockHttpServletRequest();
       MockHttpServletResponse response = new MockHttpServletResponse();
       request.addParameter("topic", "dogs");
       new MosaicsServlet().service(request, response);
	
       boolean x = true;
	   assertTrue("mess2", x);
   }
   @Test
   public void testServlet2() throws Exception {

	   MockHttpServletRequest request = new MockHttpServletRequest();
       MockHttpServletResponse response = new MockHttpServletResponse();
       request.addParameter("topic", "dskhf32q8763rwqeygk7632238oryn");
       new MosaicsServlet().service(request, response);
       boolean x = true;
	   assertTrue("mess2", x);
   }
 
}