package builders;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
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
	public final static int idealImageWidth = 420;
	public final static int idealImageHeight = 240;
	public final static int collageWidth = 800;
	public final static int collageHeight = 600;

	// Final static member variables in degrees
	public final static int minRotation = -45;
	public final static int maxRotation = 45;

	// Important fixed values for drawing collages
	public final static int startingX = -80;
	public final static int startingY = -20;
	public final static int incrX = (collageWidth - 100) / 5; // 140
	public final static int incrY = (collageHeight - 120) / 6; // 80
	// Values considered "out of bounds"
	public final static int oobX = 600;
	public final static int oobY = 373;

	protected static Integer[] getForbiddenGridLocations (Character c)
	{
		// convert uppercase character to lowercase
		c = Character.toLowerCase(c);

		// initialize forbidden grid locations (5x6 grid)
		Integer[] forbiddenGridLocations = null;

		if (c == 'a') {
			forbiddenGridLocations = new Integer[] {
			        7, 8, 9, 12, 13, 14, 22, 23, 24, 27, 28, 29
			};
		}
		else if (c == 'b') {
			forbiddenGridLocations = new Integer[] {
			        5, 7, 8, 9, 15, 17, 18, 19, 22, 23, 24, 30
			};
		}
		else if (c == 'c') {
			forbiddenGridLocations = new Integer[] {
			        7, 8, 9, 10, 12, 13, 14, 15, 17, 18, 19, 20, 22, 23, 24, 25
			};
		}
		else if (c == 'd') {
			forbiddenGridLocations = new Integer[] {
			        4, 5, 7, 8, 10, 12, 13, 14, 17, 18, 19, 22, 23, 25, 29, 30
			};
		}
		else if (c == 'e') {
			forbiddenGridLocations = new Integer[] {
			        7, 8, 9, 10, 14, 15, 19, 20, 22, 23, 24, 25
			};
		}
		else if (c == 'f') {
			forbiddenGridLocations = new Integer[] {
			        7, 8, 9, 10, 14, 15, 19, 20, 22, 23, 24, 25, 27, 28, 29, 30
			};
		}
		else if (c == 'g') {
			forbiddenGridLocations = new Integer[] {
			        7, 8, 9, 10, 12, 13, 14, 15, 17, 18, 22, 23, 24
			};
		}
		else if (c == 'h') {
			forbiddenGridLocations = new Integer[] {
			        2, 3, 4, 7, 8, 9, 22, 23, 24, 27, 28, 29
			};
		}
		else if (c == 'i') {
			forbiddenGridLocations = new Integer[] {
			        6, 7, 9, 10, 11, 12, 14, 15, 16, 17, 19, 20, 21, 22, 24, 25
			};
		}
		else if (c == 'j') {
			forbiddenGridLocations = new Integer[] {
			        6, 7, 9, 10, 11, 12, 14, 15, 17, 19, 20, 22, 24, 25, 29, 30
			};
		}
		else if (c == 'k') {
			forbiddenGridLocations = new Integer[] {
			        2, 3, 4, 7, 8, 10, 14, 15, 19, 20, 22, 23, 25, 27, 28, 29
			};
		}
		else if (c == 'l') {
			forbiddenGridLocations = new Integer[] {
			        2, 3, 4, 5, 7, 8, 9, 10, 12, 13, 14, 15, 17, 18, 19, 20, 22, 23, 24, 25
			};
		}
		else if (c == 'm') {
			forbiddenGridLocations = new Integer[] {
					// TODO
			};
		}
		else if (c == 'n') {
			forbiddenGridLocations = new Integer[] {
					// TODO
			};
		}
		else if (c == 'o') {
			forbiddenGridLocations = new Integer[] {
					// TODO
			};
		}
		else if (c == 'p') {
			forbiddenGridLocations = new Integer[] {
					// TODO
			};
		}
		else if (c == 'q') {
			forbiddenGridLocations = new Integer[] {
					// TODO
			};
		}
		else if (c == 'r') {
			forbiddenGridLocations = new Integer[] {
					// TODO
			};
		}
		else if (c == 's') {
			forbiddenGridLocations = new Integer[] {
					// TODO
			};
		}
		else if (c == 't') {
			forbiddenGridLocations = new Integer[] {
			        11, 12, 14, 15, 16, 17, 19, 20, 21, 22, 24, 25, 26, 27, 29, 30
			};
		}
		else if (c == 'u') {
			forbiddenGridLocations = new Integer[] {
			        2, 3, 4, 7, 8, 9, 12, 13, 14, 17, 18, 19, 22, 23, 24
			};
		}
		else if (c == 'v') {
			forbiddenGridLocations = new Integer[] {
			        2, 3, 4, 7, 8, 9, 11, 13, 15, 16, 18, 20, 21, 22, 24, 25, 26, 27, 29, 30
			};
		}
		else if (c == 'w') {
			forbiddenGridLocations = new Integer[] {
			        2, 3, 4, 7, 8, 9, 12, 13, 14, 17, 19, 22, 24
			};
		}
		else if (c == 'x') {
			forbiddenGridLocations = new Integer[] {
			        2, 3, 4, 6, 8, 10, 11, 12, 14, 15, 16, 17, 19, 20, 21, 23, 25, 27, 28, 29
			};
		}
		else if (c == 'y') {
			forbiddenGridLocations = new Integer[] {
			        2, 3, 4, 7, 8, 9, 16, 17, 19, 20, 21, 22, 24, 25, 26, 27, 29, 30
			};
		}
		else if (c == 'z') {
			forbiddenGridLocations = new Integer[] {
			        6, 7, 8, 10, 11, 12, 14, 15, 16, 17, 19, 20, 21, 23, 24, 25
			};
		}

		return forbiddenGridLocations;
	}

	// The public interface used to create the logic to construct a collage
	public static BufferedImage buildCollage (List<BufferedImage> images, Character shape, Boolean rotate)
	{
		// formatImages is a helper function used to format images (resize, add border)
		images = formatImages(images);

		// Create a new buffer and draw the images into the new image (collage)
		BufferedImage collageImage = new BufferedImage(collageWidth, collageHeight, BufferedImage.TYPE_INT_ARGB);

		Graphics2D g2 = collageImage.createGraphics();
		Color oldColor = g2.getColor();
		// Fill background with 'white'
		g2.setPaint(Color.WHITE);
		g2.fillRect(0, 0, collageWidth, collageHeight);
		// draw image
		g2.setColor(oldColor);
		int currX = -80;
		int currY = -20;
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

			System.out.format("Images placed: %s, Grid location: %s\n", imagesPlaced, gridLocation);

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

		assert (images.size() == 30);
		g2.dispose();
		return collageImage;
	}

	/**
	 * Formatting an image is defined as: (1) resizing an image, (2) adding a border
	 * to the same image from (1)
	 * 
	 * @param images
	 * @return
	 */
	private static List<BufferedImage> formatImages (List<BufferedImage> images)
	{
		for (int i = 0; i < images.size(); ++i) {
			BufferedImage image = images.get(i);
			image = resizeImage(idealImageWidth, idealImageHeight, image);
			image = addBorderToImage(image, CollageBuilder.borderWidth);
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
