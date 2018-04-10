package junitTest;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

import utilities.SizeOfArray;

public class arraySizeTest {

	@Test
	public void arraySizeTest() {
		SizeOfArray collageArr = new SizeOfArray();
		int arraySize = collageArr.getArraySize();
		assertEquals(arraySize, 10); //switch the 10 to number of collages?
	}
	

}
