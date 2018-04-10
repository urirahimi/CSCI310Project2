package builders;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Random;

import javax.imageio.ImageIO;

/*
 * A static class used to build collages given a list of images.
 */
public class CollageBuilder {
	// The public interface exposed to the public. Used to create the logic to construct a collage
	public static BufferedImage buildCollage(List<BufferedImage> images) {
		
		//30 Images, width and height, divided into 3 by 3 blocks. Determine number of images per block,
		// then determine number of images per block, then determine height and width of images, then place
		// images in their appropriate blocks.
		
		// formatImages is a helper function used to format images (resize, add border)
		images = formatImages(images);
        int wid = 800;
        int height = 600;
        //create a new buffer and draw two image into the new image
        BufferedImage newImage = new BufferedImage(wid,height, BufferedImage.TYPE_INT_ARGB);

        Graphics2D g2 = newImage.createGraphics();
        Color oldColor = g2.getColor();
        //fill background
        g2.setPaint(Color.WHITE);
        g2.fillRect(0, 0, wid, height);
        //draw image
        g2.setColor(oldColor);
        int currX = -80;
        int currY = -20;
        Random random = new Random();

        for (int i = 0; i < 30; ++i) {
            // random number between -45 to 45
//            int number = random.nextInt(max + 1 -min) + min;
            int rotation = random.nextInt(45 + 1 - (-45)) - 45;
            double rotationRadians = rotation * Math.PI / 180.0;
            if (currX < 600 && currY < 400) {
                g2.drawImage(rotateImage(images.get(i), rotationRadians), null, currX, currY);
            }

            currX += 140;
            // if going out of bounds to the right
            if (currX > 600) {
                // if going out of bound to the bottom
                if (currY > 373) {
                    // reset
                    currX = -80;
                    currY = -24;
                } else {
                    // shift down
                    currX = -80;
                    currY += 80;
                }
            }
        }
        g2.dispose();
        return newImage;
	}
	
	// Helper function to apply resizing and border to image
	private static List<BufferedImage> formatImages(List<BufferedImage> images) {
		for (int i = 0; i < images.size(); ++i) {
			images.set(i, resizeImage(420, 240, images.get(i)));
			images.set(i, addBorderToImage(images.get(i), 3));
		}
		return images;
	}
	
	// Helper function to resize image
	private static BufferedImage resizeImage(int width, int height, BufferedImage image) {
		Image temp = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
		BufferedImage newImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2d = newImage.createGraphics();
		g2d.drawImage(temp, 0, 0, null);
		g2d.dispose();
		return newImage;
	}
	
	// Helper function to add a white border to image
	private static BufferedImage addBorderToImage(BufferedImage image, int borderWidth) {
		Graphics2D g = image.createGraphics();
        int height = image.getHeight();
        int width = image.getWidth();
        int borderControl = 1;
        //set border color
        g.setColor(Color.WHITE);
        //set border thickness
        g.setStroke(new BasicStroke(borderWidth));
        //to fix issue for even numbers
        if(borderWidth % 2 == 0){
            borderControl = 0;
        }
        g.drawLine(0, 0, 0, height);
        g.drawLine(0, 0, width, 0);
        g.drawLine(0, height - borderControl, width, height - borderControl);
        g.drawLine(width - borderControl, height - borderControl, width - borderControl, 0);

        return image;
	}
	
	// Helper function to rotate an image
	private static BufferedImage rotateImage(BufferedImage image, double rotation) {
        AffineTransform at = new AffineTransform();

        // 4. translate it to the center of the component
        at.translate(image.getWidth() / 2, image.getHeight() / 2);

        // 3. do the actual rotation
        at.rotate(rotation * Math.PI/4.0);

        // 2. just a scale because this image is big
        at.scale(0.5, 0.5);

        // 1. translate the object so that you rotate it around the
        //    center (easier :))
        at.translate(-image.getWidth()/2, -image.getHeight()/2);

        // draw the image
        BufferedImage newImage = new BufferedImage(image.getWidth(), image.getHeight(), image.getType());
        Graphics2D g = (Graphics2D) newImage.getGraphics();

        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(image, at, null);

        File outputfile = new File("rotated.png");
        try {
            ImageIO.write(newImage, "png", outputfile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return newImage;
    }
}
