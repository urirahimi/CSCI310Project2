package junitTest;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import builders.CollageBuilder;
import services.ImageService;

public class collageOptionsTest {
	
	@Test
	public void collageFiltersTest() {
		List<BufferedImage> allImages = new ArrayList<BufferedImage>();
		List<BufferedImage> images = new ArrayList<BufferedImage>();
		allImages = ImageService.getImages("dog");
		for(int i = 0; i<30; i++) {
			images.add(allImages.get(i));
		}
//		AssertNotEquals(buildCollage(images, 'A', "sepia", true, false, 800, 800), buildCollage(images, 'A', "grayscale", true, false, 800, 800));
//		AssertNotEquals(buildCollage(images, 'A', "sepia", true, false, 800, 800), buildCollage(images, 'A', "black and white", true, false, 800, 800));
//		AssertNotEquals(buildCollage(images, 'A', "grayscale", true, false, 800, 800), buildCollage(images, 'A', "black and white", true, false, 800, 800));
//		AssertEquals(buildCollage(images, 'A', "sepia", true, false, 800, 800), buildCollage(images, 'A', "sepia", true, false, 800, 800));
//		Assert.assertNotEquals(CollageBuilder.buildCollage(images, 'A', false, "sepia"), CollageBuilder.buildCollage(images, 'A', false, "grayscale")); //checks sepia and grayscale are different objects
//		Assert.assertNotNull(CollageBuilder.buildCollage(images, 'A', false, "sepia")); //checks that the object created is not null
//		Assert.assertNotEquals(CollageBuilder.buildCollage(images, 'A', false, "sepia"), CollageBuilder.buildCollage(images, 'A', false, "blackandwhite")); //checks if BW and sepia are different
//		Assert.assertNotEquals(CollageBuilder.buildCollage(images, 'A', false, "grayscale"), CollageBuilder.buildCollage(images, 'A', false, "blackandwhite")); //checks if grayscale and BW are different
		BufferedImage sepiaImage = CollageBuilder.buildCollage(images, "A", false, "sepia", false, 800, 800);
		BufferedImage sepiaImageTwo = CollageBuilder.buildCollage(images, "A", false, "sepia", false, 800, 800);
		Assert.assertTrue(CollageBuilder.bufferedImagesEqual(sepiaImage,sepiaImageTwo));//checks if sepia and sepia are equal objects
		BufferedImage grayscaleImage = CollageBuilder.buildCollage(images, "A", false, "grayscale", false, 800, 800);
		BufferedImage grayscaleImageTwo = CollageBuilder.buildCollage(images, "A", false, "grayscale", false, 800, 800);
		Assert.assertTrue(CollageBuilder.bufferedImagesEqual(grayscaleImage,grayscaleImageTwo));//checks if grayscale and grayscale are equal objects
		BufferedImage bwImage = CollageBuilder.buildCollage(images, "A", false, "blackandwhite", false, 800, 800);
		BufferedImage bwImageTwo = CollageBuilder.buildCollage(images, "A", false, "blackandwhite", false, 800, 800);
		Assert.assertTrue(CollageBuilder.bufferedImagesEqual(bwImage,bwImageTwo));//checks if blackandwhite and blackandwhite are equal objects
	}

	@Test
	public void collagePixelsTest() {
		List<BufferedImage> allImages = new ArrayList<BufferedImage>();
		List<BufferedImage> images = new ArrayList<BufferedImage>();
		allImages = ImageService.getImages("dog");
		for(int i = 0; i<30; i++) {
			images.add(allImages.get(i));
		}
		BufferedImage BufferedImageOne = CollageBuilder.buildCollage(images, "A", false, "none", false, 600, 600);
		BufferedImage BufferedImageTwo = CollageBuilder.buildCollage(images, "A", false, "none", false, 600, 600);		
		Assert.assertTrue(CollageBuilder.bufferedImagesEqual(BufferedImageOne, BufferedImageTwo)); //testing two images with same parameters and same decrease in width and height
		BufferedImageOne = CollageBuilder.buildCollage(images, "A", false, "none", false, 600, 800);
		BufferedImageTwo = CollageBuilder.buildCollage(images, "A", false, "none", false, 800, 600);
		Assert.assertFalse(CollageBuilder.bufferedImagesEqual(BufferedImageOne, BufferedImageTwo));
		BufferedImageOne = CollageBuilder.buildCollage(images, "A", false, "none", false, 800, 800);
		BufferedImageTwo = CollageBuilder.buildCollage(images, "A", false, "none", false, 850, 850);
		Assert.assertFalse(CollageBuilder.bufferedImagesEqual(BufferedImageOne, BufferedImageTwo));
		
		
//		AssertTrue(buildCollage(images, 'A', "sepia", true, true, 1200, 1200)); //success, max of 1200
//		AssertTrue(!buildCollage(images, 'A', "sepia", true, true, 1201, 1200)); //exceeds
//		AssertTrue(!buildCollage(images, 'A', "sepia", true, true, 1200, 1201)); //exceeds
//		AssertTrue(!buildCollage(images, 'A', "sepia", true, true, 1201, 1201)); //exceeds
	}
	@Test
	public void collageRotationsTest() {
		//CollageBuilder cb = new CollageBuilder();
		List<BufferedImage> allImages = new ArrayList<BufferedImage>();
		List<BufferedImage> images = new ArrayList<BufferedImage>();
		allImages = ImageService.getImages("dog");
		for(int i = 0; i<30; i++) {
			images.add(allImages.get(i));
		}
		BufferedImage BufferedImageOne = CollageBuilder.buildCollage(images, "A", true, "sepia", false, 800, 800);
		BufferedImage BufferedImageTwo = CollageBuilder.buildCollage(images, "A", false, "sepia", false, 800, 800);		
		Assert.assertNotEquals(BufferedImageOne,BufferedImageTwo); //should not equal each other
		BufferedImageOne = CollageBuilder.buildCollage(images, "A", false, "none", false, 800, 800);
		BufferedImageTwo = CollageBuilder.buildCollage(images, "A", false, "none", false, 800, 800);
		Assert.assertTrue(CollageBuilder.bufferedImagesEqual(BufferedImageOne, BufferedImageTwo)); //should equal since rotations are both off
	}
	@Test
	public void collageBordersTest() {
		List<BufferedImage> allImages = new ArrayList<BufferedImage>();
		List<BufferedImage> images = new ArrayList<BufferedImage>();
		allImages = ImageService.getImages("dog");
		for(int i = 0; i<30; i++) {
			images.add(allImages.get(i));
		}
		BufferedImage BufferedImageOne = CollageBuilder.buildCollage(images, "A", false, "sepia", true, 800, 800);
		BufferedImage BufferedImageTwo = CollageBuilder.buildCollage(images, "A", false, "sepia", false, 800, 800);		
		Assert.assertNotEquals(BufferedImageOne,BufferedImageTwo); //should not equal each other
		BufferedImageOne = CollageBuilder.buildCollage(images, "A", false, "none", false, 800, 800);
		BufferedImageTwo = CollageBuilder.buildCollage(images, "A", false, "none", false, 800, 800);
		Assert.assertTrue(CollageBuilder.bufferedImagesEqual(BufferedImageOne, BufferedImageTwo)); //should equal since borders are both off
	}
	@Test
	public void collageComboTest() {
		List<BufferedImage> allImages = new ArrayList<BufferedImage>();
		List<BufferedImage> images = new ArrayList<BufferedImage>();
		allImages = ImageService.getImages("dog");
		for(int i = 0; i<30; i++) {
			images.add(allImages.get(i));
		}
		Assert.assertNotNull(CollageBuilder.buildCollage(images, "A", true, "sepia", true, 800, 800));
		Assert.assertNotNull(CollageBuilder.buildCollage(images, "A", true, "sepia", false, 800, 800));
		Assert.assertNotNull(CollageBuilder.buildCollage(images, "A", false, "sepia", true, 800, 800));
		Assert.assertNotNull(CollageBuilder.buildCollage(images, "A", false, "sepia", false, 800, 800));
		Assert.assertNotNull(CollageBuilder.buildCollage(images, "A", true, "blackandwhite", true, 800, 800));
		Assert.assertNotNull(CollageBuilder.buildCollage(images, "A", true, "blackandwhite", false, 800, 800));
		Assert.assertNotNull(CollageBuilder.buildCollage(images, "A", false, "blackandwhite", true, 800, 800));
		Assert.assertNotNull(CollageBuilder.buildCollage(images, "A", false, "blackandwhite", false, 800, 800));
		Assert.assertNotNull(CollageBuilder.buildCollage(images, "A", true, "grayscale", true, 800, 800));
		Assert.assertNotNull(CollageBuilder.buildCollage(images, "A", true, "grayscale", false, 800, 800));
		Assert.assertNotNull(CollageBuilder.buildCollage(images, "A", false, "grayscale", true, 800, 800));
		Assert.assertNotNull(CollageBuilder.buildCollage(images, "A", false, "grayscale", false, 800, 800));
	}
	@Test
	public void collageUppercaseTest() {
		List<BufferedImage> allImages = new ArrayList<BufferedImage>();
		List<BufferedImage> images = new ArrayList<BufferedImage>();
		allImages = ImageService.getImages("dog");
		for(int i = 0; i<30; i++) {
			images.add(allImages.get(i));
		}
		Assert.assertNotNull(CollageBuilder.buildCollage(images, "CCDD", false, "sepia",false,800,800)); //test uppercase letters
	}
	
	@Test
	public void collageExcessiveInputTest() {
		List<BufferedImage> allImages = new ArrayList<BufferedImage>();
		List<BufferedImage> images = new ArrayList<BufferedImage>();
		allImages = ImageService.getImages("dog");
		for(int i = 0; i<30; i++) {
			images.add(allImages.get(i));
		}
		Assert.assertNotNull(CollageBuilder.buildCollage(images, "ddddddddddddddddffffffffffjjjjJJJJJJJJJJJJJJJmmmMMMMMMMMMMMM", false, "sepia",false,800,800)); //test excessive input
	}
	
	
	@Test
	public void collageSpacesTest() {
		List<BufferedImage> allImages = new ArrayList<BufferedImage>();
		List<BufferedImage> images = new ArrayList<BufferedImage>();
		allImages = ImageService.getImages("dog");
		for(int i = 0; i<30; i++) {
			images.add(allImages.get(i));
		}
		Assert.assertNotNull(CollageBuilder.buildCollage(images, "E j   D D", false, "sepia",false,800,800)); //test with spaces
	}
	
//	@Test
//	public void collageLowercaseTest() {
//		List<BufferedImage> allImages = new ArrayList<BufferedImage>();
//		List<BufferedImage> images = new ArrayList<BufferedImage>();
//		allImages = ImageService.getImages("dog");
//		for(int i = 0; i<30; i++) {
//			images.add(allImages.get(i));
//		}
//		Assert.assertNotNull(CollageBuilder.buildCollage(images, "bbll", false, "sepia",false,800,800)); //test lowercase letters
//	}
//	@Test
//	public void collageIndividualLowercaseLetterTest() {
//		List<BufferedImage> allImages = new ArrayList<BufferedImage>();
//		List<BufferedImage> images = new ArrayList<BufferedImage>();
//		allImages = ImageService.getImages("dog");
//		for(int i = 0; i<30; i++) {
//			images.add(allImages.get(i));
//		}
//		Assert.assertNotNull(CollageBuilder.buildCollage(images, "a", false, "sepia",false,800,800));
////		Assert.assertNotNull(CollageBuilder.buildCollage(images, "b", false, "sepia",false,800,800));
////		Assert.assertNotNull(CollageBuilder.buildCollage(images, "c", false, "sepia",false,800,800));
////		Assert.assertNotNull(CollageBuilder.buildCollage(images, "d", false, "sepia",false,800,800));
////		Assert.assertNotNull(CollageBuilder.buildCollage(images, "e", false, "sepia",false,800,800));
////		Assert.assertNotNull(CollageBuilder.buildCollage(images, "f", false, "sepia",false,800,800));
////		Assert.assertNotNull(CollageBuilder.buildCollage(images, "g", false, "sepia",false,800,800));
////		Assert.assertNotNull(CollageBuilder.buildCollage(images, "h", false, "sepia",false,800,800));
////		Assert.assertNotNull(CollageBuilder.buildCollage(images, "i", false, "sepia",false,800,800));
////		Assert.assertNotNull(CollageBuilder.buildCollage(images, "j", false, "sepia",false,800,800));
////		Assert.assertNotNull(CollageBuilder.buildCollage(images, "k", false, "sepia",false,800,800));
////		Assert.assertNotNull(CollageBuilder.buildCollage(images, "l", false, "sepia",false,800,800));
////		Assert.assertNotNull(CollageBuilder.buildCollage(images, "m", false, "sepia",false,800,800));
////		Assert.assertNotNull(CollageBuilder.buildCollage(images, "n", false, "sepia",false,800,800));
////		Assert.assertNotNull(CollageBuilder.buildCollage(images, "o", false, "sepia",false,800,800));
////		Assert.assertNotNull(CollageBuilder.buildCollage(images, "p", false, "sepia",false,800,800));
////		Assert.assertNotNull(CollageBuilder.buildCollage(images, "q", false, "sepia",false,800,800));
////		Assert.assertNotNull(CollageBuilder.buildCollage(images, "r", false, "sepia",false,800,800));
////		Assert.assertNotNull(CollageBuilder.buildCollage(images, "s", false, "sepia",false,800,800));
////		Assert.assertNotNull(CollageBuilder.buildCollage(images, "t", false, "sepia",false,800,800));
////		Assert.assertNotNull(CollageBuilder.buildCollage(images, "u", false, "sepia",false,800,800));
////		Assert.assertNotNull(CollageBuilder.buildCollage(images, "v", false, "sepia",false,800,800));
////		Assert.assertNotNull(CollageBuilder.buildCollage(images, "w", false, "sepia",false,800,800));
////		Assert.assertNotNull(CollageBuilder.buildCollage(images, "x", false, "sepia",false,800,800));
////		Assert.assertNotNull(CollageBuilder.buildCollage(images, "y", false, "sepia",false,800,800));
////		Assert.assertNotNull(CollageBuilder.buildCollage(images, "z", false, "sepia",false,800,800));
//	}
	
	@Test
	public void collageIndividualUppercaseLetterTest() {
		List<BufferedImage> allImages = new ArrayList<BufferedImage>();
		List<BufferedImage> images = new ArrayList<BufferedImage>();
		allImages = ImageService.getImages("dog");
		for(int i = 0; i<30; i++) {
			images.add(allImages.get(i));
		}
		Assert.assertNotNull(CollageBuilder.buildCollage(images, "A", false, "sepia",false,800,800));
//		Assert.assertNotNull(CollageBuilder.buildCollage(images, "B", false, "sepia",false,800,800));
//		Assert.assertNotNull(CollageBuilder.buildCollage(images, "C", false, "sepia",false,800,800));
//		Assert.assertNotNull(CollageBuilder.buildCollage(images, "D", false, "sepia",false,800,800));
//		Assert.assertNotNull(CollageBuilder.buildCollage(images, "E", false, "sepia",false,800,800));
//		Assert.assertNotNull(CollageBuilder.buildCollage(images, "F", false, "sepia",false,800,800));
//		Assert.assertNotNull(CollageBuilder.buildCollage(images, "G", false, "sepia",false,800,800));
//		Assert.assertNotNull(CollageBuilder.buildCollage(images, "H", false, "sepia",false,800,800));
//		Assert.assertNotNull(CollageBuilder.buildCollage(images, "I", false, "sepia",false,800,800));
//		Assert.assertNotNull(CollageBuilder.buildCollage(images, "J", false, "sepia",false,800,800));
//		Assert.assertNotNull(CollageBuilder.buildCollage(images, "K", false, "sepia",false,800,800));
//		Assert.assertNotNull(CollageBuilder.buildCollage(images, "L", false, "sepia",false,800,800));
//		Assert.assertNotNull(CollageBuilder.buildCollage(images, "M", false, "sepia",false,800,800));
//		Assert.assertNotNull(CollageBuilder.buildCollage(images, "N", false, "sepia",false,800,800));
//		Assert.assertNotNull(CollageBuilder.buildCollage(images, "O", false, "sepia",false,800,800));
//		Assert.assertNotNull(CollageBuilder.buildCollage(images, "P", false, "sepia",false,800,800));
//		Assert.assertNotNull(CollageBuilder.buildCollage(images, "Q", false, "sepia",false,800,800));
//		Assert.assertNotNull(CollageBuilder.buildCollage(images, "R", false, "sepia",false,800,800));
//		Assert.assertNotNull(CollageBuilder.buildCollage(images, "S", false, "sepia",false,800,800));
//		Assert.assertNotNull(CollageBuilder.buildCollage(images, "T", false, "sepia",false,800,800));
//		Assert.assertNotNull(CollageBuilder.buildCollage(images, "U", false, "sepia",false,800,800));
//		Assert.assertNotNull(CollageBuilder.buildCollage(images, "V", false, "sepia",false,800,800));
//		Assert.assertNotNull(CollageBuilder.buildCollage(images, "W", false, "sepia",false,800,800));
//		Assert.assertNotNull(CollageBuilder.buildCollage(images, "X", false, "sepia",false,800,800));
//		Assert.assertNotNull(CollageBuilder.buildCollage(images, "Y", false, "sepia",false,800,800));
//		Assert.assertNotNull(CollageBuilder.buildCollage(images, "Z", false, "sepia",false,800,800));
	}
	
	@Test
	public void collageComboTest() {
		List<BufferedImage> allImages = new ArrayList<BufferedImage>();
		List<BufferedImage> images = new ArrayList<BufferedImage>();
		allImages = ImageService.getImages("dog");
		for(int i = 0; i<30; i++) {
			images.add(allImages.get(i));
		}
		AssertNotNull(CollageBuilder.buildCollage(images, 'A', "sepia", true, true, 800, 800));
		AssertNotNull(CollageBuilder.buildCollage(images, 'B', "sepia", true, true, 800, 800));
		AssertNotNull(CollageBuilder.buildCollage(images, 'C', "sepia", true, true, 800, 800));
		AssertNotNull(CollageBuilder.buildCollage(images, 'D', "sepia", true, true, 800, 800));
		AssertNotNull(CollageBuilder.buildCollage(images, 'E', "sepia", true, true, 800, 800));
		AssertNotNull(CollageBuilder.buildCollage(images, 'F', "sepia", true, true, 800, 800));
		AssertNotNull(CollageBuilder.buildCollage(images, 'G', "sepia", true, true, 800, 800));
		AssertNotNull(CollageBuilder.buildCollage(images, 'H', "sepia", true, true, 800, 800));
		AssertNotNull(CollageBuilderbuildCollage(images, 'I', "sepia", true, true, 800, 800));
		AssertNotNull(CollageBuilderbuildCollage(images, 'J', "sepia", true, true, 800, 800));
		AssertNotNull(CollageBuilderbuildCollage(images, 'K', "sepia", true, true, 800, 800));
		AssertNotNull(CollageBuilderbuildCollage(images, 'L', "sepia", true, true, 800, 800));
		AssertNotNull(CollageBuilderbuildCollage(images, 'M', "sepia", true, true, 800, 800));
		AssertNotNull(CollageBuilderbuildCollage(images, 'N', "sepia", true, true, 800, 800));
		AssertNotNull(CollageBuilderbuildCollage(images, 'O', "sepia", true, true, 800, 800));
		AssertNotNull(CollageBuilderbuildCollage(images, 'P', "sepia", true, true, 800, 800));
		AssertNotNull(CollageBuilderbuildCollage(images, 'Q', "sepia", true, true, 800, 800));
		AssertNotNull(CollageBuilderbuildCollage(images, 'R', "sepia", true, true, 800, 800));
		AssertNotNull(CollageBuilderbuildCollage(images, 'S', "sepia", true, true, 800, 800));
		AssertNotNull(CollageBuilderbuildCollage(images, 'T', "sepia", true, true, 800, 800));
		AssertNotNull(CollageBuilderbuildCollage(images, 'U', "sepia", true, true, 800, 800));
		AssertNotNull(CollageBuilderbuildCollage(images, 'V', "sepia", true, true, 800, 800));
		AssertNotNull(CollageBuilderbuildCollage(images, 'W', "sepia", true, true, 800, 800));
		AssertNotNull(CollageBuilderbuildCollage(images, 'X', "sepia", true, true, 800, 800));
		AssertNotNull(CollageBuilderbuildCollage(images, 'Y', "sepia", true, true, 800, 800));
		AssertNotNull(CollageBuilderbuildCollage(images, 'Z', "sepia", true, true, 800, 800));
	}
}
