package driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class LaunchingChrome {
	public static void main(String[] args) {
		String exePath = "//Users//dmdaher//Desktop//Chrome Driver//chromedriver";
		System.setProperty("webdriver.chrome.driver", exePath);
		WebDriver driver = new ChromeDriver();
		System.out.println("hey");
		driver.get("http://toolsqa.wpengine.com/automation-practice-form/");
	}
}
