package junitTest;

import static org.junit.Assert.*;

import org.junit.Test;

public class registrationTest {

	@Test
	public void register() {
		AssertTrue(!Register.register("Vijay", "123")); //username already exists
		AssertTrue(Register.register("Vij", "123")); //username does not exist, success
	}

}
