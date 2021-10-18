package com.nopcommerce.user;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Level_01_Register_DRY {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		driver = new ChromeDriver();
		System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		driver.get("https://demo.nopcommerce.com/");
	}

	@Test
	public void TC_01_Register_Empty_Data() {
		

	}

	@Test
	public void TC_02() {
	}

	@AfterClass
	public void afterClass() {
	}
	

}
