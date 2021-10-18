package com.nopcommerce.user;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import commons.BasePage;
import pageObjects.HomePageObject;
import pageObjects.RegisterPageObject;

public class Level_03_Page_Object extends BasePage {
	private WebDriver driver;
	private String projectPath= System.getProperty("user.dir");
	private String firstName, lastName, emailAddress, password;
	private HomePageObject homePage;
	private RegisterPageObject registerPage;
	
	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver",projectPath + "\\browserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();
		System.out.println("Driver at class test:"+driver.toString());
		homePage=new HomePageObject(driver);
		registerPage=new RegisterPageObject(driver);
		
		firstName="DuyenQC";
		lastName="Vu";
		emailAddress=randomEmail();
		password="123456";
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.get("https://demo.nopcommerce.com/");		
	}
	
	@Test
	public void TC_01_Register_Empty_Data() {
		homePage.clickToRegisterLink();	
		registerPage.clickToRegisterButton();		
		registerPage.getErrorMessageAtFirstnameTextbox();
		
		Assert.assertEquals(registerPage.getErrorMessageAtFirstnameTextbox(), "First name is required.");
		Assert.assertEquals(registerPage.getErrorMessageAtLastnameTextbox(), "Last name is required.");
		Assert.assertEquals(registerPage.getErrorMessageAtEmailTextbox(), "Email is required.");
		Assert.assertEquals(registerPage.getErrorMessageAtPasswordTextbox(), "Password is required.");
		Assert.assertEquals(registerPage.getErrorMessageAtConfirmPasswordTextbox(), "Password is required.");
	}
	
	@Test
	public void TC_02_Register_Invalid_Email() {
		homePage.clickToRegisterLink();
		registerPage.inputToFirstNameTextbox(firstName);
		registerPage.inputToLastNameTextbox(lastName);
		registerPage.inputToEmailTextbox("InvalidEmail");
		registerPage.inputToPasswordTextbox(password);
		registerPage.inputToConfirmPasswordTextbox(password);
		
		registerPage.clickToRegisterButton();

		Assert.assertEquals(registerPage.getErrorMessageAtEmailTextbox(), "Wrong email");
	}
	
	@Test
	public void TC_03_Register_Success() {
		homePage.clickToRegisterLink();

		registerPage.inputToFirstNameTextbox(firstName);
		registerPage.inputToLastNameTextbox(lastName);
		registerPage.inputToEmailTextbox(emailAddress);
		registerPage.inputToPasswordTextbox(password);
		registerPage.inputToConfirmPasswordTextbox(password);
		registerPage.clickToRegisterButton();

		Assert.assertEquals(registerPage.getRegisterSuccessMessage(), "Your registration completed");
		registerPage.clickToLogoutLink();
	}
	
	@Test
	public void TC_04_Register_Existing_Email() {
		homePage.clickToRegisterLink();

		registerPage.inputToFirstNameTextbox(firstName);
		registerPage.inputToLastNameTextbox(lastName);
		registerPage.inputToEmailTextbox(emailAddress);
		registerPage.inputToPasswordTextbox(password);
		registerPage.inputToConfirmPasswordTextbox(password);
		registerPage.clickToRegisterButton();
		
		Assert.assertEquals(registerPage.getErrorExistingMessage(), "The specified email already exists");
		
	}
	
	@Test
	public void TC_05_Register_Password_Less_Than_6_Chars() {
		homePage.clickToRegisterLink();

		registerPage.inputToFirstNameTextbox(firstName);
		registerPage.inputToLastNameTextbox(lastName);
		registerPage.inputToEmailTextbox(emailAddress);
		registerPage.inputToPasswordTextbox("123");
		registerPage.inputToConfirmPasswordTextbox("123");
		registerPage.clickToRegisterButton();
		
		Assert.assertEquals(registerPage.getErrorMessageAtPasswordTextbox(), "Password must meet the following rules:\nmust have at least 6 characters");
		
	}
	
	@Test
	public void TC_06_Invalid_Confirm_Password() {
		homePage.clickToRegisterLink();

		registerPage.inputToFirstNameTextbox(firstName);
		registerPage.inputToLastNameTextbox(lastName);
		registerPage.inputToEmailTextbox(emailAddress);
		registerPage.inputToPasswordTextbox(password);
		registerPage.inputToConfirmPasswordTextbox(emailAddress);
		registerPage.clickToRegisterButton();
		
		Assert.assertEquals(registerPage.getErrorMessageAtConfirmPasswordTextbox(), "The password and confirmation password do not match.");

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
