package junitTest;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import services.ImageService;

public class ImagesTest {
	List<BufferedImage> images = new ArrayList<BufferedImage>();
	Image img = new Image();
	
	@Test
	public void deleteImageTest() { //saves an image, then deletes it and checks if it deleted from database by calling a function get image
		ImageService.saveImage(img);
		ImageService.deleteImage(img);
		AssertNotTrue(ImageService.getImage(img));
	}
	public void exportImageTest() { //makes sure the exported file name from method is same as hardcoded one
		AssertEquals(ImageService.exportImage(img, "png"), "collage.png");
		AssertEquals(ImageService.exportImage(img, "pdf"), "collage.pdf");
	}
	public void saveImageTest() { //saves an image then calls function that checks database for image then deletes image
		ImageService.saveImage(img);
		AssertNotNull(ImageService.getImage());
		ImageService.deleteImage(img);
	}
	public void getImageTest() {
		AssertNotNull(ImageService.getImage()); //calls function that pulls from database
	}
}
