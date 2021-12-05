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
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BasePage;
import commons.BaseTest;
import pageObjects.nopCommerce.user.UserHomePageObject;
import pageObjects.nopCommerce.user.UserLoginPageObject;
import pageObjects.nopCommerce.user.UserRegisterPageObject;

public class Level_06_Page_Generator_Manager_I extends BaseTest {
	private WebDriver driver;
	private UserHomePageObject homePage;
	private UserRegisterPageObject registerPage;
	private UserLoginPageObject loginPage;

	private String invalidEmail, notFoundEmail, invalidPassword;
	private String firstName, lastName, existingEmail, validPassword;

	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browserName) {
		driver = getBrowserDriver(browserName);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.get("https://demo.nopcommerce.com/");
		firstName="DuyenQC";
		lastName="Vu";
		
		existingEmail=randomEmail();
		invalidEmail = "test@gmail.com@gmail";
		notFoundEmail = randomEmail();
		validPassword="123456";
		invalidPassword="invalidPass";
		
		homePage=new UserHomePageObject(driver);
		
		System.out.println("Login Precondition: Register member successfully");
		homePage.openRegisterPage();
		
		//Click Register link mo ra trang register
		registerPage=new UserRegisterPageObject(driver);

		registerPage.inputToFirstNameTextbox(firstName);
		registerPage.inputToLastNameTextbox(lastName);
		registerPage.inputToEmailTextbox(existingEmail);
		registerPage.inputToPasswordTextbox(validPassword);
		registerPage.inputToConfirmPasswordTextbox(validPassword);
		registerPage.clickToRegisterButton();

		Assert.assertEquals(registerPage.getRegisterSuccessMessage(), "Your registration completed");
		registerPage.clickToLogoutLink();
		//Click Logout ve mat bussiness quay ve trang Home nen khoi tao trang Home
		homePage=new UserHomePageObject(driver);
	}

	@Test
	public void Login_01_Empty_Data() {
		System.out.println("Login 01 - Step 01: Click to open Login");
		homePage.openLoginPage();
		
		//Khoi tao trang login
		loginPage=new UserLoginPageObject(driver);

		System.out.println("Login 01 - Step 02: Click to Login Button");
		loginPage.clickToLoginButton();

		System.out.println("Login 01 - Step 03: Verify Error Message displayed");
		Assert.assertEquals(loginPage.getErrorMessageAtEmailTextbox(), "Please enter your email");
	}

	public void Login_02_Invalid_Email() {
		System.out.println("Login 02 - Step 01: Click to open Login");
		homePage.openLoginPage();
		
		//Khoi tao trang login
		loginPage=new UserLoginPageObject(driver);

		System.out.println("Login 02 - Step 02: Input data");
		loginPage.inputToEmailTextbox(invalidEmail);

		System.out.println("Login 02 - Step 03: Click to Login Button");
		loginPage.clickToLoginButton();

		System.out.println("Login 02 - Step 04: Verify Error Message displayed");
		Assert.assertEquals(loginPage.getErrorMessageAtEmailTextbox(), "Wrong email");
	}

	@Test
	public void Login_03_Email_Not_Found() {
		System.out.println("Login 03 - Step 01: Click to open Login");
		homePage.openLoginPage();
		
		//Khoi tao trang login
		loginPage=new UserLoginPageObject(driver);

		System.out.println("Login 03 - Step 02: Input data");
		loginPage.inputToEmailTextbox(notFoundEmail);
		loginPage.inputToPasswordTextbox(validPassword);

		System.out.println("Login 03 - Step 03: Click to Login Button");
		loginPage.clickToLoginButton();

		System.out.println("Login 03 - Step 04: Verify Error Message displayed");
		Assert.assertEquals(loginPage.getErrorMessageAtLoginForm(),
				"Login was unsuccessful. Please correct the errors and try again.\nNo customer account found");
	}

	@Test
	public void Login_04_Valid_Email_Empty_Password() {
		System.out.println("Login 04 - Step 01: Click to open Login");
		homePage.openLoginPage();
		
		//Khoi tao trang login
		loginPage=new UserLoginPageObject(driver);

		System.out.println("Login 04 - Step 02: Input data");
		loginPage.inputToEmailTextbox(existingEmail);

		System.out.println("Login 04 - Step 03: Click to Login Button");
		loginPage.clickToLoginButton();

		System.out.println("Login 04 - Step 04: Verify Error Message displayed");
		Assert.assertEquals(loginPage.getErrorMessageAtLoginForm(),
				"Login was unsuccessful. Please correct the errors and try again.\nThe credentials provided are incorrect");
	}

	@Test
	public void Login_05_Valid_Email_Invalid_Password() {
		System.out.println("Login 05 - Step 01: Click to open Login");
		homePage.openLoginPage();
		
		//Khoi tao trang login
		loginPage=new UserLoginPageObject(driver);

		System.out.println("Login 05 - Step 02: Input data");
		loginPage.inputToEmailTextbox(existingEmail);
		loginPage.inputToPasswordTextbox(invalidPassword);

		System.out.println("Login 05 - Step 03: Click to Login Button");
		loginPage.clickToLoginButton();

		System.out.println("Login 05 - Step 04: Verify Error Message displayed");
		Assert.assertEquals(loginPage.getErrorMessageAtLoginForm(),
				"Login was unsuccessful. Please correct the errors and try again.\nThe credentials provided are incorrect");
	}

	@Test
	public void Login_06_Valid_Empty_Valid_Password() {
		System.out.println("Login 06 - Step 01: Click to open Login");
		homePage.openLoginPage();
		
		//Khoi tao trang login
		loginPage=new UserLoginPageObject(driver);

		System.out.println("Login 06 - Step 02: Input data");
		loginPage.inputToEmailTextbox(existingEmail);
		loginPage.inputToPasswordTextbox(validPassword);

		System.out.println("Login 06 - Step 03: Click to Login Button");
		loginPage.clickToLoginButton();
		
		//Login thanh cong qua trang homepage nen khoi tao Homepage len
		homePage=new UserHomePageObject(driver);

		System.out.println("Login 06 - Step 04: Verify login successfully");
		Assert.assertTrue(homePage.isMyAccountDisplayed());
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
