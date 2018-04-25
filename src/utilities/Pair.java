package utilities;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.io.ObjectInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.*;
import java.util.*;
import java.util.Base64;

public class Pair implements Serializable {
	 private static final long serialVersionUID = -2745291100752366675L;
	  private String topic;
	  private String image;

	  public Pair(String topic, String image) {
	    this.topic = topic;
	    this.image = image;
	  }

	  public String getTopic() { return topic; }
	  public String getImage() { return image; }
	
	  public void saveImageToFile(String filePath) {
//			CollageBuilder collage = new CollageBuilder();
			try {
				FileOutputStream outputFile = new FileOutputStream(filePath);
				ObjectOutputStream out = new ObjectOutputStream(outputFile);
				out.writeObject(this);
				out.close();	
			}catch(IOException ioe) {
				System.out.println(ioe);
			}
	  }
	  public static Pair fromString( String s ) throws IOException , ClassNotFoundException {
		byte [] data = Base64.getDecoder().decode( s );
		ObjectInputStream ois = new ObjectInputStream( 
		new ByteArrayInputStream(  data ) );
		Pair o  = (Pair)ois.readObject();
		ois.close();
		return o;
		}
		
		/** Write the object to a Base64 string. */
		public String toString() {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			try {
				ObjectOutputStream oos = new ObjectOutputStream( baos );
				oos.writeObject(this);
				oos.close();	
			}catch(Exception e) {
				
			}
		return Base64.getEncoder().encodeToString(baos.toByteArray()); 
		}
}