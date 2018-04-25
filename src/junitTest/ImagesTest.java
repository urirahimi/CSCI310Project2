package junitTest;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import jdbc.db_connection;
import services.ImageService;
import utilities.Pair;

public class ImagesTest {
	List<BufferedImage> images = new ArrayList<BufferedImage>();
//	Image img = new Image();
	
//	@Test
//	public void getImageTest() {
//		Assert.assertNotNull(ImageService.getImages("dog")); //calls function that pulls from database
//	}
//	@Test
//	public void grabImageFromDB() {
//		db_connection dbTest = new db_connection();
//		Assert.assertNotNull(dbTest.retrieveImages("dog.png")); //should return dog image
//		Assert.assertNull(dbTest.retrieveImages("catttt.png")); //should return no image
//	}
//	@Test
//	public void deleteImageTest() { //saves an image, then deletes it and checks if it deleted from database by calling a function get image
//		ImageService.saveImage(img);
//		ImageService.deleteImage(img);
//		Assert.assertFalse(ImageService.getImages("dog"));
//	}
//	@Test
//	public void exportImageTest() { //makes sure the exported file name from method is same as hardcoded one
//		AssertEquals(ImageService.exportImage(img, "png"), "collage.png");
//		//AssertEquals(ImageService.exportImage(img, "pdf"), "collage.pdf");
//	}
//	@Test
//	public void saveImageTest() { //saves an image then calls function that checks database for image then deletes image
//		ImageService.saveImage(img);
//		AssertNotNull(ImageService.getImages());
//		ImageService.deleteImage(img);
//	}
	@Test
	public void createImageTest() { //tests the createImage function in db_connection
		Pair p = new Pair("dog","/Users/dmdaher/Desktop");
		db_connection dbTest = new db_connection();
		Assert.assertTrue(dbTest.createImage(p));
	}
}
