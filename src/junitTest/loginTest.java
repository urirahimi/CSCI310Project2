package junitTest;

import static org.junit.Assert.*;

import org.junit.Test;

public class loginTest {

	@Test
	public void loginUsernameTest() {
		AssertTrue(Login.login("devin", "123")); //right username AND password
		AssertTrue(!Login.login("dev", "123")); //wrong username and right password
	}
	
	public void loginPasswordTest() {
		AssertTrue(!Login.login("devin", "1234567")); //wrong password
	}

}
