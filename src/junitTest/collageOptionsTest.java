package junitTest;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class collageOptionsTest {
	List<BufferedImage> images = new ArrayList<BufferedImage>();

	@Test
	public void collagePixelsTest() {
		AssertTrue(!buildCollage(images, 'A', "sepia", true, true, 799, 799)); //testing
		AssertTrue(!buildCollage(images, 'A', "sepia", true, true, 799, 800));
		AssertTrue(!buildCollage(images, 'A', "sepia", true, true, 800, 799));
		AssertTrue(buildCollage(images, 'A', "sepia", true, true, 800, 800)); //success
		AssertTrue(buildCollage(images, 'A', "sepia", true, true, 801, 801)); //success
		AssertTrue(buildCollage(images, 'A', "sepia", true, true, 1200, 1200)); //success, max of 1200
		AssertTrue(!buildCollage(images, 'A', "sepia", true, true, 1201, 1200)); //exceeds
		AssertTrue(!buildCollage(images, 'A', "sepia", true, true, 1200, 1201)); //exceeds
		AssertTrue(!buildCollage(images, 'A', "sepia", true, true, 1201, 1201)); //exceeds
	}
	public void collageRotationsTest() {
		AssertNotEquals(buildCollage(images, 'A', "sepia", true, false, 800, 800), buildCollage(images, 'A', "sepia", true, true, 800, 800)); //should not equal each other
		AssertEquals(buildCollage(images, 'A', "sepia", true, false, 800, 800),buildCollage(images, 'A', "sepia", true, false, 800, 800)); //should equal since rotations are both off
	}
	public void collageBordersTest() {
		AssertNotEquals(buildCollage(images, 'A', "sepia", true, false, 800, 800), buildCollage(images, 'A', "sepia", false, false, 800, 800)); //should not equal each other
		AssertEquals(buildCollage(images, 'A', "sepia", true, false, 800, 800),buildCollage(images, 'A', "sepia", true, false, 800, 800)); //should equal since rotations are both off
		AssertEquals(buildCollage(images, 'A', "sepia", false, false, 800, 800),buildCollage(images, 'A', "sepia", false, false, 800, 800)); //should equal since rotations are both off
	}
	public void collageFiltersTest() {		
		AssertNotEquals(buildCollage(images, 'A', "sepia", true, false, 800, 800), buildCollage(images, 'A', "grayscale", true, false, 800, 800));
		AssertNotEquals(buildCollage(images, 'A', "sepia", true, false, 800, 800), buildCollage(images, 'A', "black and white", true, false, 800, 800));
		AssertNotEquals(buildCollage(images, 'A', "grayscale", true, false, 800, 800), buildCollage(images, 'A', "black and white", true, false, 800, 800));
		AssertEquals(buildCollage(images, 'A', "sepia", true, false, 800, 800), buildCollage(images, 'A', "sepia", true, false, 800, 800));
	}
	public void collageComboTest() {
		AssertNotNull(buildCollage(images, 'A', "sepia", true, true, 800, 800));
		AssertNotNull(buildCollage(images, 'A', "sepia", true, false, 800, 800));
		AssertNotNull(buildCollage(images, 'A', "sepia", false, true, 800, 800));
		AssertNotNull(buildCollage(images, 'A', "sepia", false, false, 800, 800));
		AssertNotNull(buildCollage(images, 'A', "black and white", true, true, 800, 800));
		AssertNotNull(buildCollage(images, 'A', "black and white", true, false, 800, 800));
		AssertNotNull(buildCollage(images, 'A', "black and white", false, true, 800, 800));
		AssertNotNull(buildCollage(images, 'A', "black and white", false, false, 800, 800));
		AssertNotNull(buildCollage(images, 'A', "grayscale", true, true, 800, 800));
		AssertNotNull(buildCollage(images, 'A', "grayscale", true, false, 800, 800));
		AssertNotNull(buildCollage(images, 'A', "grayscale", false, true, 800, 800));
		AssertNotNull(buildCollage(images, 'A', "grayscale", false, false, 800, 800));
	}
	public void collageLetterTest() {
		AssertNotNull(buildCollage(images, 'A', "sepia", true, true, 800, 800));
		AssertNotNull(buildCollage(images, 'B', "sepia", true, true, 800, 800));
		AssertNotNull(buildCollage(images, 'C', "sepia", true, true, 800, 800));
		AssertNotNull(buildCollage(images, 'D', "sepia", true, true, 800, 800));
		AssertNotNull(buildCollage(images, 'E', "sepia", true, true, 800, 800));
		AssertNotNull(buildCollage(images, 'F', "sepia", true, true, 800, 800));
		AssertNotNull(buildCollage(images, 'G', "sepia", true, true, 800, 800));
		AssertNotNull(buildCollage(images, 'H', "sepia", true, true, 800, 800));
		AssertNotNull(buildCollage(images, 'I', "sepia", true, true, 800, 800));
		AssertNotNull(buildCollage(images, 'J', "sepia", true, true, 800, 800));
		AssertNotNull(buildCollage(images, 'K', "sepia", true, true, 800, 800));
		AssertNotNull(buildCollage(images, 'L', "sepia", true, true, 800, 800));
		AssertNotNull(buildCollage(images, 'M', "sepia", true, true, 800, 800));
		AssertNotNull(buildCollage(images, 'N', "sepia", true, true, 800, 800));
		AssertNotNull(buildCollage(images, 'O', "sepia", true, true, 800, 800));
		AssertNotNull(buildCollage(images, 'P', "sepia", true, true, 800, 800));
		AssertNotNull(buildCollage(images, 'Q', "sepia", true, true, 800, 800));
		AssertNotNull(buildCollage(images, 'R', "sepia", true, true, 800, 800));
		AssertNotNull(buildCollage(images, 'S', "sepia", true, true, 800, 800));
		AssertNotNull(buildCollage(images, 'T', "sepia", true, true, 800, 800));
		AssertNotNull(buildCollage(images, 'U', "sepia", true, true, 800, 800));
		AssertNotNull(buildCollage(images, 'V', "sepia", true, true, 800, 800));
		AssertNotNull(buildCollage(images, 'W', "sepia", true, true, 800, 800));
		AssertNotNull(buildCollage(images, 'X', "sepia", true, true, 800, 800));
		AssertNotNull(buildCollage(images, 'Y', "sepia", true, true, 800, 800));
		AssertNotNull(buildCollage(images, 'Z', "sepia", true, true, 800, 800));
	}
}
