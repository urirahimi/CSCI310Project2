package builders;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.color.ColorSpace;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.awt.image.ColorConvertOp;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import javax.imageio.ImageIO;

/**
 * A static class used to build collages given a list of images.
 * 
 * @author
 * @since
 */
public class CollageBuilder
{
	// Every final static member variables are in pixels
	public final static int borderWidth = 3;

	public final static int collageWidth = 800;
	public final static int collageHeight = 600;

	// Final static member variables in degrees
	public final static int minRotation = -45;
	public final static int maxRotation = 45;

	// Important fixed values for drawing collages
	public final static int startingX = -80;
	public final static int startingY = -20;

	// Values considered "out of bounds"
	public final static int oobX = 600;
	public final static int oobY = 373;
	
	public static boolean bufferedImagesEqual(BufferedImage img1, BufferedImage img2) {
	    if (img1.getWidth() == img2.getWidth() && img1.getHeight() == img2.getHeight()) {
	        for (int x = 0; x < img1.getWidth(); x++) {
	            for (int y = 0; y < img1.getHeight(); y++) {
	                if (img1.getRGB(x, y) != img2.getRGB(x, y))
	                    return false;
	            }
	        }
	    } else {
	        return false;
	    }
	    return true;
	}

	protected static Integer[] getForbiddenGridLocations (Character c)
	{
		// convert uppercase character to lowercase
		c = Character.toLowerCase(c);

		// initialize forbidden grid locations (5x6 grid)
		Integer[] forbiddenGridLocations = null;

		if (c == 'A') {
			forbiddenGridLocations = new Integer[] {
			        7, 8, 9, 12, 13, 14, 22, 23, 24, 27, 28, 29
			};
		}
		else if (c == 'B') {
			forbiddenGridLocations = new Integer[] {
			        5, 7, 8, 9, 15, 17, 18, 19, 22, 23, 24, 30
			};
		}
		else if (c == 'C') {
			forbiddenGridLocations = new Integer[] {
			        7, 8, 9, 10, 12, 13, 14, 15, 17, 18, 19, 20, 22, 23, 24, 25
			};
		}
		else if (c == 'D') {
			forbiddenGridLocations = new Integer[] {
			        4, 5, 7, 8, 10, 12, 13, 14, 17, 18, 19, 22, 23, 25, 29, 30
			};
		}
		else if (c == 'E') {
			forbiddenGridLocations = new Integer[] {
			        7, 8, 9, 10, 14, 15, 19, 20, 22, 23, 24, 25
			};
		}
		else if (c == 'F') {
			forbiddenGridLocations = new Integer[] {
			        7, 8, 9, 10, 14, 15, 19, 20, 22, 23, 24, 25, 27, 28, 29, 30
			};
		}
		else if (c == 'G') {
			forbiddenGridLocations = new Integer[] {
			        7, 8, 9, 10, 12, 13, 14, 15, 17, 18, 22, 23, 24
			};
		}
		else if (c == 'H') {
			forbiddenGridLocations = new Integer[] {
			        2, 3, 4, 7, 8, 9, 22, 23, 24, 27, 28, 29
			};
		}
		else if (c == 'I') {
			forbiddenGridLocations = new Integer[] {
			        6, 7, 9, 10, 11, 12, 14, 15, 16, 17, 19, 20, 21, 22, 24, 25
			};
		}
		else if (c == 'J') {
			forbiddenGridLocations = new Integer[] {
			        6, 7, 9, 10, 11, 12, 14, 15, 17, 19, 20, 22, 24, 25, 29, 30
			};
		}
		else if (c == 'K') {
			forbiddenGridLocations = new Integer[] {
			        2, 3, 4, 7, 8, 10, 14, 15, 19, 20, 22, 23, 25, 27, 28, 29
			};
		}
		else if (c == 'L') {
			forbiddenGridLocations = new Integer[] {
			        2, 3, 4, 5, 7, 8, 9, 10, 12, 13, 14, 15, 17, 18, 19, 20, 22, 23, 24, 25
			};
		}
		else if (c == 'M') {
			forbiddenGridLocations = new Integer[] {
			        2, 3, 4, 8, 12, 14, 17, 19, 22, 23, 24, 27, 28, 29
			};
		}
		else if (c == 'N') {
			forbiddenGridLocations = new Integer[] {
			        2, 3, 4, 8, 9, 12, 14, 17, 19, 22, 23, 27, 28, 29
			};
		}
		else if (c == 'O') {
			forbiddenGridLocations = new Integer[] {
			        7, 8, 9, 12, 13, 14, 17, 18, 19, 22, 23, 24
			};
		}
		else if (c == 'P') {
			forbiddenGridLocations = new Integer[] {
			        7, 8, 9, 12, 13, 14, 22, 23, 24, 25, 27, 28, 29, 30
			};
		}
		else if (c == 'Q') {
			forbiddenGridLocations = new Integer[] {
			        7, 8, 9, 12, 13, 14, 21, 22, 23, 24, 26, 27, 28, 29
			};
		}
		else if (c == 'R') {
			forbiddenGridLocations = new Integer[] {
			        7, 8, 9, 17, 19, 20, 22, 23, 25, 27, 28, 29
			};
		}
		else if (c == 'S') {
			forbiddenGridLocations = new Integer[] {
			        7, 8, 9, 10, 11, 13, 14, 15, 16, 17, 19, 20, 21, 22, 23, 25
			};
		}
		else if (c == 'T') {
			forbiddenGridLocations = new Integer[] {
			        11, 12, 14, 15, 16, 17, 19, 20, 21, 22, 24, 25, 26, 27, 29, 30
			};
		}
		else if (c == 'U') {
			forbiddenGridLocations = new Integer[] {
			        2, 3, 4, 7, 8, 9, 12, 13, 14, 17, 18, 19, 22, 23, 24
			};
		}
		else if (c == 'V') {
			forbiddenGridLocations = new Integer[] {
			        2, 3, 4, 7, 8, 9, 11, 13, 15, 16, 18, 20, 21, 22, 24, 25, 26, 27, 29, 30
			};
		}
		else if (c == 'W') {
			forbiddenGridLocations = new Integer[] {
			        2, 3, 4, 7, 8, 9, 12, 13, 14, 17, 19, 22, 24
			};
		}
		else if (c == 'X') {
			forbiddenGridLocations = new Integer[] {
			        2, 3, 4, 6, 8, 10, 11, 12, 14, 15, 16, 17, 19, 20, 21, 23, 25, 27, 28, 29
			};
		}
		else if (c == 'Y') {
			forbiddenGridLocations = new Integer[] {
			        2, 3, 4, 7, 8, 9, 16, 17, 19, 20, 21, 22, 24, 25, 26, 27, 29, 30
			};
		}
		else if (c == 'Z') {
			forbiddenGridLocations = new Integer[] {
			        6, 7, 8, 10, 11, 12, 14, 15, 16, 17, 19, 20, 21, 23, 24, 25
			};
		}

		return forbiddenGridLocations;
	}

	// The public interface used to create the logic to construct a collage
	
	private static BufferedImage buildImage(List<BufferedImage> images, int height, int width, Character shape, Boolean rotate)
	{
		BufferedImage collageImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		int incrX = (width - 100) / 5; // 140
		int incrY = (height - 120) / 6; // 80
		Graphics2D g2 = collageImage.createGraphics();
		Color oldColor = g2.getColor();
		// Fill background with 'white'
		g2.setPaint(Color.WHITE);
		g2.fillRect(0, 0, width, height);
		// draw image
		g2.setColor(oldColor);
		int currX = startingX;
		int currY = startingY;
		Random random = new Random();

		Integer[] forbiddenLocations = new Integer[] {};
		if (shape != null) {
			forbiddenLocations = getForbiddenGridLocations(shape);
		}
		Set<Integer> forbiddenGrids = new HashSet<Integer>(Arrays.asList(forbiddenLocations));

		assert (images.size() == 30);
		int imagesPlaced = 0;
		int gridLocation = 1;

		while (imagesPlaced < images.size()) {
			// Print out current cursor location
			System.out.println("(X,Y): (" + currX + "," + currY + ")");

			if (!forbiddenGrids.contains(gridLocation)) {
				// Get the next image
				BufferedImage image = images.get(imagesPlaced);

				// Rotating the image:
				// If no rotation is requested, still rotate at 0 degrees since
				// the rotate helper function scales it to an appropriate size
				int rotationInDegrees = (rotate) ? random.nextInt(maxRotation + 1 - minRotation) + minRotation : 0;
				double rotationInRadians = rotationInDegrees * Math.PI / 180.0;
				image = rotateImage(image, rotationInRadians); // rotated image

				// Draw the image
				if (currX < 600 && currY < 400) {
					g2.drawImage(image, null, currX, currY);
					imagesPlaced++;
				}
			}


			currX += incrX;
			// if going out of bounds to the right
			if (currX > oobX) {
				// If going out of bounds towards the bottom right corner
				if (currY >= oobY) { // reset
					currX = startingX;
					currY = startingY;
					gridLocation = 0;
				}
				else {
					// shift down
					currX = startingX;
					currY += incrY;
				}
			}

			gridLocation++;
		}
		g2.dispose();
		return collageImage;
	}
	public static BufferedImage buildCollage (List<BufferedImage> images, String letters, Boolean rotate, String filter)
	{
		// formatImages is a helper function used to format images (resize, add border)
		images = formatImages(images, filter);

		// Create a new buffer and draw the images into the new image (collage)
		
		List<BufferedImage> listImages = new ArrayList<BufferedImage>();
		for(int i =0; i<letters.length();i++)
		{
			Character letter = letters.charAt(i);
			
			listImages.add(buildImage(images,collageHeight,collageWidth,Character.toUpperCase(letter),rotate));
		}
		
		for(int i =0;i<listImages.size();i++)
		{
			listImages.set(i, resizeImage(collageHeight,collageWidth/letters.length(),listImages.get(i)));
			System.out.println("made it");
		}
		BufferedImage result=listImages.get(0);
		for(int i =1;i<listImages.size();i++)
		{
			result = joinBufferedImage(result,listImages.get(i));
		}
		return result;
		//assert (images.size() == 30);

	}
    public static BufferedImage joinBufferedImage(BufferedImage img1,BufferedImage img2) {

        //do some calculate first
        int wid = img1.getWidth()+img2.getWidth();
        int height = Math.max(img1.getHeight(),img2.getHeight());
        //create a new buffer and draw two image into the new image
        BufferedImage newImage = new BufferedImage(wid,height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = newImage.createGraphics();
        Color oldColor = g2.getColor();
        //fill background
        g2.setPaint(Color.WHITE);
        g2.fillRect(0, 0, wid, height);
        //draw image
        g2.setColor(oldColor);
        g2.drawImage(img1, null, 0, 0);
        g2.drawImage(img2, null, img1.getWidth(), 0);
        g2.dispose();
        return newImage;
    }
	 public static BufferedImage toGrayScale(BufferedImage master) {
	        BufferedImage gray = new BufferedImage(master.getWidth(), master.getHeight(), BufferedImage.TYPE_INT_ARGB);

	        // Automatic converstion....
	        ColorConvertOp op = new ColorConvertOp(ColorSpace.getInstance(ColorSpace.CS_GRAY), null);
	        op.filter(master, gray);

	        return gray;
	    }

	    public static BufferedImage toSepia(BufferedImage img, int sepiaIntensity) {

	        BufferedImage sepia = new BufferedImage(img.getWidth(), img.getHeight(), BufferedImage.TYPE_INT_RGB);
	        // Play around with this.  20 works well and was recommended
	        //   by another developer. 0 produces black/white image
	        int sepiaDepth = 20;

	        int w = img.getWidth();
	        int h = img.getHeight();

	        WritableRaster raster = sepia.getRaster();

	        // We need 3 integers (for R,G,B color values) per pixel.
	        int[] pixels = new int[w * h * 3];
	        img.getRaster().getPixels(0, 0, w, h, pixels);

	        //  Process 3 ints at a time for each pixel.  Each pixel has 3 RGB
	        //    colors in array
	        for (int i = 0; i < pixels.length; i += 3) {
	            int r = pixels[i];
	            int g = pixels[i + 1];
	            int b = pixels[i + 2];

	            int gry = (r + g + b) / 3;
	            r = g = b = gry;
	            r = r + (sepiaDepth * 2);
	            g = g + sepiaDepth;

	            if (r > 255) {
	                r = 255;
	            }
	            if (g > 255) {
	                g = 255;
	            }
	            if (b > 255) {
	                b = 255;
	            }

	            // Darken blue color to increase sepia effect
	            b -= sepiaIntensity;

	            // normalize if out of bounds
	            if (b < 0) {
	                b = 0;
	            }
	            if (b > 255) {
	                b = 255;
	            }

	            pixels[i] = r;
	            pixels[i + 1] = g;
	            pixels[i + 2] = b;
	        }
	        raster.setPixels(0, 0, w, h, pixels);

	        return sepia;
	    }
	/**
	 * Formatting an image is defined as: (1) resizing an image, (2) adding a border
	 * to the same image from (1)
	 * 
	 * @param images
	 * @return
	 */
	private static List<BufferedImage> formatImages (List<BufferedImage> images, String filter)
	{
		int idealImageWidth = 420;
		int idealImageHeight = 240;
		for (int i = 0; i < images.size(); ++i) {
			BufferedImage image = images.get(i);
			image = resizeImage(idealImageWidth, idealImageHeight, image);
			image = addBorderToImage(image, CollageBuilder.borderWidth);
			if(filter.equals("grayscale"))
			{
				image=toGrayScale(image);
			}
			else if(filter.equals("sepia"))
			{
				 for(int y = 0; y < collageHeight; y++){
				      for(int x = 0; x < collageWidth; x++){
				        int p = image.getRGB(x,y);

				        int a = (p>>24)&0xff;
				        int r = (p>>16)&0xff;
				        int g = (p>>8)&0xff;
				        int b = p&0xff;

				        //calculate tr, tg, tb
				        int tr = (int)(0.393*r + 0.769*g + 0.189*b);
				        int tg = (int)(0.349*r + 0.686*g + 0.168*b);
				        int tb = (int)(0.272*r + 0.534*g + 0.131*b);

				        //check condition
				        if(tr > 255){
				          r = 255;
				        }else{
				          r = tr;
				        }

				        if(tg > 255){
				          g = 255;
				        }else{
				          g = tg;
				        }

				        if(tb > 255){
				          b = 255;
				        }else{
				          b = tb;
				        }

				        //set new RGB value
				        p = (a<<24) | (r<<16) | (g<<8) | b;

				        image.setRGB(x, y, p);
				      }
				    }

			}
			else if(filter.equals("blackandwhite"))
			{
			    BufferedImage result = new BufferedImage(collageWidth, collageHeight, BufferedImage.TYPE_BYTE_GRAY);
			    result.getGraphics().drawImage(image, 0, 0, null);
			    WritableRaster raster = result.getRaster();
			    int[] pixels = new int[image.getWidth()];
			    for (int y = 0; y < image.getHeight(); y++) {
			        raster.getPixels(0, y, image.getWidth(), 1, pixels);
			        for (int l = 0; l < pixels.length; l++) {
			            if (pixels[l] < 126) pixels[l] = 0;
			            else pixels[l] = 255;
			        }
			        raster.setPixels(0, y, image.getWidth(), 1, pixels);
			    }
			    image=result;
			}
			images.set(i, image);
			
		}
		return images;
	}

	/**
	 * Resize an image.
	 * 
	 * @param width
	 * @param height
	 * @param image
	 * @return
	 */
	private static BufferedImage resizeImage (int width, int height, BufferedImage image)
	{
		Image temp = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
		BufferedImage newImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2d = newImage.createGraphics();
		g2d.drawImage(temp, 0, 0, null);
		g2d.dispose();
		return newImage;
	}

	/**
	 * Adds a white border to the image.
	 * 
	 * @param image
	 * @param borderWidth
	 * @return
	 */
	private static BufferedImage addBorderToImage (BufferedImage image, int borderWidth)
	{
		Graphics2D g = image.createGraphics();
		int height = image.getHeight();
		int width = image.getWidth();
		int borderControl = 1;
		// set border color
		g.setColor(Color.WHITE);
		// set border thickness
		g.setStroke(new BasicStroke(borderWidth));
		// to fix issue for even numbers
		if (borderWidth % 2 == 0) {
			borderControl = 0;
		}
		g.drawLine(0, 0, 0, height);
		g.drawLine(0, 0, width, 0);
		g.drawLine(0, height - borderControl, width, height - borderControl);
		g.drawLine(width - borderControl, height - borderControl, width - borderControl, 0);

		return image;
	}

	/**
	 * Helper function to rotate an image.
	 * 
	 * @param image
	 * @param rotation
	 * @return
	 */
	private static BufferedImage rotateImage (BufferedImage image, double rotation)
	{
		AffineTransform at = new AffineTransform();

		// 4. translate it to the center of the component
		at.translate(image.getWidth() / 2, image.getHeight() / 2);

		// 3. do the actual rotation
		at.rotate(rotation * Math.PI / 4.0);

		// 2. just a scale because this image is big
		at.scale(0.375, 0.375);

		// 1. translate the object so that you rotate it around the
		// center (easier :))
		at.translate(-image.getWidth() / 2, -image.getHeight() / 2);

		// draw the image
		BufferedImage newImage = new BufferedImage(image.getWidth(), image.getHeight(), image.getType());
		Graphics2D g = (Graphics2D) newImage.getGraphics();

		Graphics2D g2d = (Graphics2D) g;
		g2d.drawImage(image, at, null);

		File outputfile = new File("rotated.png");
		try {
			ImageIO.write(newImage, "png", outputfile);
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		return newImage;
	}

	public static void main (String[] args)
	{
		File dir = new File("/Users/eddowh/Desktop/csci310images");
		String[] EXTENSIONS = new String[] {
		        "jpg", "jpeg", "gif", "png", "bmp" // and other formats you need
		};

		// filter to identify images based on their extensions
		FilenameFilter IMAGE_FILTER = new FilenameFilter() {

			@Override
			public boolean accept (final File dir, final String name)
			{
				for (final String ext : EXTENSIONS) {
					if (name.endsWith("." + ext)) {
						return (true);
					}
				}
				return (false);
			}
		};

		List<BufferedImage> images = new ArrayList<>();
		if (dir.isDirectory()) { // make sure it's a directory
			for (final File f : dir.listFiles(IMAGE_FILTER)) {
				BufferedImage img = null;

				try {
					img = ImageIO.read(f);
					images.add(img);
				}
				catch (final IOException e) {
					// handle errors here
				}
			}
		}
		assert (images.size() == 30);
		System.out.println("Done");
	}
}
