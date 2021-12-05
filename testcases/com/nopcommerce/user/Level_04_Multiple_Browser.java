package com.nopcommerce.user;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BasePage;
import commons.BaseTest;
import pageObjects.nopCommerce.user.UserHomePageObject;
import pageObjects.nopCommerce.user.UserRegisterPageObject;

public class Level_04_Multiple_Browser extends BaseTest {
	private WebDriver driver;
	private String firstName, lastName, emailAddress, password;
	private UserHomePageObject homePage;
	private UserRegisterPageObject registerPage;
	
	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browserName) {
		driver = getBrowserDriver(browserName);
		System.out.println("Run on " + browserName);		
		System.out.println("Driver at class test:"+ driver.toString());
		homePage=new UserHomePageObject(driver);
		
		firstName="DuyenQC";
		lastName="Vu";
		emailAddress=randomEmail();
		password="123456";
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.get("https://demo.nopcommerce.com/");		
	}
	
	@Test
	public void Register_01_Empty_Data() {
		System.out.println("Register_01 - Step 01: Click to Register link");
		homePage.openRegisterPage();	
		
		//Click Register link qua trang Register nen can khoi tao tai day
		registerPage=new UserRegisterPageObject(driver);
		
		System.out.println("Register_01 - Step 02: Click to Register button");
		registerPage.clickToRegisterButton();		
		
		System.out.println("Register_01 - Step 03: Verify error message displayed");
		Assert.assertEquals(registerPage.getErrorMessageAtFirstnameTextbox(), "First name is required.");
		Assert.assertEquals(registerPage.getErrorMessageAtLastnameTextbox(), "Last name is required.");
		Assert.assertEquals(registerPage.getErrorMessageAtEmailTextbox(), "Email is required.");
		Assert.assertEquals(registerPage.getErrorMessageAtPasswordTextbox(), "Password is required.");
		Assert.assertEquals(registerPage.getErrorMessageAtConfirmPasswordTextbox(), "Password is required.");
	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	
	public String randomEmail() {
		Random rand = new Random();
		return "duyenVu" + rand.nextInt() + "@gmail.com";
	}


}
