package junitTest;
import org.junit.Assert;
import org.junit.Test;

import jdbc.db_connection;

public class registrationTest {

	@Test
	public void register() {
		db_connection sqlDB = new db_connection();
		Assert.assertTrue(!sqlDB.signup("daher", "devin2")); //username already exists
		Assert.assertTrue(sqlDB.signup("Vijayyyyy", "123")); //username does not exist, success
	}

}
