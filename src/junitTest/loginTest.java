package junitTest;

import org.junit.Assert;
import org.junit.Test;
import jdbc.db_connection;

public class loginTest {
	db_connection test = new db_connection();
	@Test
	public void loginUsernameTest() {
		Assert.assertTrue(test.login("daher", "devin2")); //right username AND password
		Assert.assertTrue(!test.login("dev", "devin2")); //wrong username and right password
	}
	@Test
	public void loginPasswordTest() {
		Assert.assertTrue(!test.login("daher", "1234567")); //wrong password
	}

}
