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
		BufferedImage sepiaImage = CollageBuilder.buildCollage(images, 'A', false, "sepia");
		BufferedImage sepiaImageTwo = CollageBuilder.buildCollage(images, 'A', false, "sepia");
		Assert.assertTrue(CollageBuilder.bufferedImagesEqual(sepiaImage,sepiaImageTwo));//checks if sepia and sepia are equal objects
		BufferedImage grayscaleImage = CollageBuilder.buildCollage(images, 'A', false, "grayscale");
		BufferedImage grayscaleImageTwo = CollageBuilder.buildCollage(images, 'A', false, "grayscale");
		Assert.assertTrue(CollageBuilder.bufferedImagesEqual(grayscaleImage,grayscaleImageTwo));//checks if grayscale and grayscale are equal objects
		BufferedImage bwImage = CollageBuilder.buildCollage(images, 'A', false, "blackandwhite");
		BufferedImage bwImageTwo = CollageBuilder.buildCollage(images, 'A', false, "blackandwhite");
		Assert.assertTrue(CollageBuilder.bufferedImagesEqual(bwImage,bwImageTwo));//checks if blackandwhite and blackandwhite are equal objects
	}

//	@Test
//	public void collagePixelsTest() {
//		AssertTrue(!buildCollage(images, 'A', "sepia", true, true, 799, 799)); //testing
//		AssertTrue(!buildCollage(images, 'A', "sepia", true, true, 799, 800));
//		AssertTrue(!buildCollage(images, 'A', "sepia", true, true, 800, 799));
//		AssertTrue(buildCollage(images, 'A', "sepia", true, true, 800, 800)); //success
//		AssertTrue(buildCollage(images, 'A', "sepia", true, true, 801, 801)); //success
//		AssertTrue(buildCollage(images, 'A', "sepia", true, true, 1200, 1200)); //success, max of 1200
//		AssertTrue(!buildCollage(images, 'A', "sepia", true, true, 1201, 1200)); //exceeds
//		AssertTrue(!buildCollage(images, 'A', "sepia", true, true, 1200, 1201)); //exceeds
//		AssertTrue(!buildCollage(images, 'A', "sepia", true, true, 1201, 1201)); //exceeds
//	}
//	@Test
//	public void collageRotationsTest() {
//		AssertNotEquals(buildCollage(images, 'A', "sepia", true, false, 800, 800), buildCollage(images, 'A', "sepia", true, true, 800, 800)); //should not equal each other
//		AssertEquals(buildCollage(images, 'A', "sepia", true, false, 800, 800),buildCollage(images, 'A', "sepia", true, false, 800, 800)); //should equal since rotations are both off
//	}
//	@Test
//	public void collageBordersTest() {
//		AssertNotEquals(buildCollage(images, 'A', "sepia", true, false, 800, 800), buildCollage(images, 'A', "sepia", false, false, 800, 800)); //should not equal each other
//		AssertEquals(buildCollage(images, 'A', "sepia", true, false, 800, 800),buildCollage(images, 'A', "sepia", true, false, 800, 800)); //should equal since rotations are both off
//		AssertEquals(buildCollage(images, 'A', "sepia", false, false, 800, 800),buildCollage(images, 'A', "sepia", false, false, 800, 800)); //should equal since rotations are both off
//	}
//	@Test
//	public void collageComboTest() {
//		AssertNotNull(buildCollage(images, 'A', "sepia", true, true, 800, 800));
//		AssertNotNull(buildCollage(images, 'A', "sepia", true, false, 800, 800));
//		AssertNotNull(buildCollage(images, 'A', "sepia", false, true, 800, 800));
//		AssertNotNull(buildCollage(images, 'A', "sepia", false, false, 800, 800));
//		AssertNotNull(buildCollage(images, 'A', "black and white", true, true, 800, 800));
//		AssertNotNull(buildCollage(images, 'A', "black and white", true, false, 800, 800));
//		AssertNotNull(buildCollage(images, 'A', "black and white", false, true, 800, 800));
//		AssertNotNull(buildCollage(images, 'A', "black and white", false, false, 800, 800));
//		AssertNotNull(buildCollage(images, 'A', "grayscale", true, true, 800, 800));
//		AssertNotNull(buildCollage(images, 'A', "grayscale", true, false, 800, 800));
//		AssertNotNull(buildCollage(images, 'A', "grayscale", false, true, 800, 800));
//		AssertNotNull(buildCollage(images, 'A', "grayscale", false, false, 800, 800));
//	}
	@Test
	public void collageLetterTest() {
		List<BufferedImage> allImages = new ArrayList<BufferedImage>();
		List<BufferedImage> images = new ArrayList<BufferedImage>();
		allImages = ImageService.getImages("dog");
		for(int i = 0; i<30; i++) {
			images.add(allImages.get(i));
		}
		Assert.assertNotNull(CollageBuilder.buildCollage(images, 'A', false, "sepia"));
		Assert.assertNotNull(CollageBuilder.buildCollage(images, 'B', false, "sepia"));
		Assert.assertNotNull(CollageBuilder.buildCollage(images, 'C', false, "sepia"));
		Assert.assertNotNull(CollageBuilder.buildCollage(images, 'D', false, "sepia"));
		Assert.assertNotNull(CollageBuilder.buildCollage(images, 'E', false, "sepia"));
		Assert.assertNotNull(CollageBuilder.buildCollage(images, 'F', false, "sepia"));
		Assert.assertNotNull(CollageBuilder.buildCollage(images, 'G', false, "sepia"));
		Assert.assertNotNull(CollageBuilder.buildCollage(images, 'H', false, "sepia"));
		Assert.assertNotNull(CollageBuilder.buildCollage(images, 'I', false, "sepia"));
		Assert.assertNotNull(CollageBuilder.buildCollage(images, 'J', false, "sepia"));
		Assert.assertNotNull(CollageBuilder.buildCollage(images, 'K', false, "sepia"));
		Assert.assertNotNull(CollageBuilder.buildCollage(images, 'L', false, "sepia"));
		Assert.assertNotNull(CollageBuilder.buildCollage(images, 'M', false, "sepia"));
		Assert.assertNotNull(CollageBuilder.buildCollage(images, 'N', false, "sepia"));
		Assert.assertNotNull(CollageBuilder.buildCollage(images, 'O', false, "sepia"));
		Assert.assertNotNull(CollageBuilder.buildCollage(images, 'P', false, "sepia"));
		Assert.assertNotNull(CollageBuilder.buildCollage(images, 'Q', false, "sepia"));
		Assert.assertNotNull(CollageBuilder.buildCollage(images, 'R', false, "sepia"));
		Assert.assertNotNull(CollageBuilder.buildCollage(images, 'S', false, "sepia"));
		Assert.assertNotNull(CollageBuilder.buildCollage(images, 'T', false, "sepia"));
		Assert.assertNotNull(CollageBuilder.buildCollage(images, 'U', false, "sepia"));
		Assert.assertNotNull(CollageBuilder.buildCollage(images, 'V', false, "sepia"));
		Assert.assertNotNull(CollageBuilder.buildCollage(images, 'W', false, "sepia"));
		Assert.assertNotNull(CollageBuilder.buildCollage(images, 'X', false, "sepia"));
		Assert.assertNotNull(CollageBuilder.buildCollage(images, 'Y', false, "sepia"));
		Assert.assertNotNull(CollageBuilder.buildCollage(images, 'Z', false, "sepia"));
//		AssertNotNull(CollageBuilder.buildCollage(images, 'A', "sepia", true, true, 800, 800));
//		AssertNotNull(CollageBuilderbuildCollage(images, 'B', "sepia", true, true, 800, 800));
//		AssertNotNull(CollageBuilderbuildCollage(images, 'C', "sepia", true, true, 800, 800));
//		AssertNotNull(CollageBuilderbuildCollage(images, 'D', "sepia", true, true, 800, 800));
//		AssertNotNull(CollageBuilderbuildCollage(images, 'E', "sepia", true, true, 800, 800));
//		AssertNotNull(CollageBuilderbuildCollage(images, 'F', "sepia", true, true, 800, 800));
//		AssertNotNull(CollageBuilderbuildCollage(images, 'G', "sepia", true, true, 800, 800));
//		AssertNotNull(CollageBuilderbuildCollage(images, 'H', "sepia", true, true, 800, 800));
//		AssertNotNull(CollageBuilderbuildCollage(images, 'I', "sepia", true, true, 800, 800));
//		AssertNotNull(CollageBuilderbuildCollage(images, 'J', "sepia", true, true, 800, 800));
//		AssertNotNull(CollageBuilderbuildCollage(images, 'K', "sepia", true, true, 800, 800));
//		AssertNotNull(CollageBuilderbuildCollage(images, 'L', "sepia", true, true, 800, 800));
//		AssertNotNull(CollageBuilderbuildCollage(images, 'M', "sepia", true, true, 800, 800));
//		AssertNotNull(CollageBuilderbuildCollage(images, 'N', "sepia", true, true, 800, 800));
//		AssertNotNull(CollageBuilderbuildCollage(images, 'O', "sepia", true, true, 800, 800));
//		AssertNotNull(CollageBuilderbuildCollage(images, 'P', "sepia", true, true, 800, 800));
//		AssertNotNull(CollageBuilderbuildCollage(images, 'Q', "sepia", true, true, 800, 800));
//		AssertNotNull(CollageBuilderbuildCollage(images, 'R', "sepia", true, true, 800, 800));
//		AssertNotNull(CollageBuilderbuildCollage(images, 'S', "sepia", true, true, 800, 800));
//		AssertNotNull(CollageBuilderbuildCollage(images, 'T', "sepia", true, true, 800, 800));
//		AssertNotNull(CollageBuilderbuildCollage(images, 'U', "sepia", true, true, 800, 800));
//		AssertNotNull(CollageBuilderbuildCollage(images, 'V', "sepia", true, true, 800, 800));
//		AssertNotNull(CollageBuilderbuildCollage(images, 'W', "sepia", true, true, 800, 800));
//		AssertNotNull(CollageBuilderbuildCollage(images, 'X', "sepia", true, true, 800, 800));
//		AssertNotNull(CollageBuilderbuildCollage(images, 'Y', "sepia", true, true, 800, 800));
//		AssertNotNull(CollageBuilderbuildCollage(images, 'Z', "sepia", true, true, 800, 800));
	}
}
