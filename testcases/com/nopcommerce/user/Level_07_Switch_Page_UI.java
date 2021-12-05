package com.nopcommerce.user;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import commons.PageGeneratorManager;
import pageObjects.nopCommerce.user.UserAddressPageObject;
import pageObjects.nopCommerce.user.UserCustomerInforPageObject;
import pageObjects.nopCommerce.user.UserHomePageObject;
import pageObjects.nopCommerce.user.UserLoginPageObject;
import pageObjects.nopCommerce.user.UserMyProductReviewPageObject;
import pageObjects.nopCommerce.user.UserRegisterPageObject;
import pageObjects.nopCommerce.user.UserRewardPointPageObject;

public class Level_07_Switch_Page_UI extends BaseTest {
	private WebDriver driver;
	private UserHomePageObject homePage;
	private UserRegisterPageObject registerPage;
	private UserLoginPageObject loginPage;
	private UserCustomerInforPageObject customerInforPage;
	private UserAddressPageObject addressPage;
	private UserMyProductReviewPageObject myProductReviewPage;
	private UserRewardPointPageObject rewardPointPage;

	private String firstName, lastName, emailAdress, validPassword;

	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browserName) {
		driver = getBrowserDriver(browserName);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.get("https://demo.nopcommerce.com/");

		// 1
		homePage = PageGeneratorManager.getUserHomePage(driver);

		firstName = "DuyenQC";
		lastName = "Vu";
		emailAdress = randomEmail();
		validPassword = "123456";	
	}

	@Test
	public void User_01_Register() {
		// Click Register link mo ra trang register
		registerPage = homePage.openRegisterPage();

		registerPage.inputToFirstNameTextbox(firstName);
		registerPage.inputToLastNameTextbox(lastName);
		registerPage.inputToEmailTextbox(emailAdress);
		registerPage.inputToPasswordTextbox(validPassword);
		registerPage.inputToConfirmPasswordTextbox(validPassword);
		registerPage.clickToRegisterButton();

		Assert.assertEquals(registerPage.getRegisterSuccessMessage(), "Your registration completed");
		// Click Logout ve mat bussiness quay ve trang Home nen khoi tao trang Home
		homePage = registerPage.clickToLogoutLink();
	}
	
	@Test
	public void User_02_Login() {
		// Khoi tao trang login
		loginPage = homePage.openLoginPage();

		loginPage.inputToEmailTextbox(emailAdress);
		loginPage.inputToPasswordTextbox(validPassword);

 		loginPage.clickToLoginButton();

		// Login thanh cong qua trang homepage nen khoi tao Homepage len
		homePage = new UserHomePageObject(driver);

		Assert.assertTrue(homePage.isMyAccountDisplayed());
	}

	@Test
	public void User_03_Customer_Infor() {
		customerInforPage=homePage.openMyAccountPage();
		Assert.assertTrue(customerInforPage.isCustomerInforPageDisplayed());		
	}

	@Test
	public void User_04_Switch_Page() {
		//1 page A khi mo page B can goi ham mo page B
		//Customer Infor -> Address
		addressPage= customerInforPage.openAddressPage(driver);
		
		//Address -> My product review
		myProductReviewPage= addressPage.openMyProductReviewPage(driver);
		
		//My product review -> Reward Point
		rewardPointPage = myProductReviewPage.openRewardPointPage(driver);
		
		//Reward Point -> Address
		addressPage = rewardPointPage.openAddressPage(driver);
		
		
		//Address -> reward point
		rewardPointPage= addressPage.openRewardPointPage(driver);
		
		//Reward point -> my product review
		myProductReviewPage= rewardPointPage.openMyProductReviewPage(driver);
		
		myProductReviewPage.openCustomerInforPage(driver);
	}
	
	@Test
	public void User_05_Switch_Role() {
	
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
