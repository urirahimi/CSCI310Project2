package services;
import java.awt.image.BufferedImage;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import builders.CollageBuilder;


public class ImageSaver implements Serializable {
	private static final long serialVersionUID = -2745291100752366675L;
	public char letter;
	public BufferedImage allImages;
	public boolean isRotated;
	public boolean hasBorders;
	public String filter;
	
	public ImageSaver(BufferedImage img, Character shape, Boolean rotate, String filterType, boolean borders) {
		letter = shape;
		isRotated = rotate;
		hasBorders = borders;
		filter = filterType;
		allImages = img;
	}
	
	public BufferedImage getImage() {
		return allImages;
	}
	
	public void saveImageToFile(String filePath) {
//		CollageBuilder collage = new CollageBuilder();
		try {
			FileOutputStream outputFile = new FileOutputStream(filePath);
			ObjectOutputStream out = new ObjectOutputStream(outputFile);
			out.writeObject(this);
			out.close();	
		}catch(IOException ioe) {
			System.out.println(ioe);
		}
	}
}
