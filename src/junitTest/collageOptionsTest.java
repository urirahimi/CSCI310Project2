package junitTest;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class collageOptionsTest {

	@Test
	public void collagePixelsTest() {
		List<BufferedImage> images = new ArrayList<BufferedImage>();
		AssertTrue(!buildCollage(images, 'A', "sepia", true, true, 799, 799));
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
	
}
