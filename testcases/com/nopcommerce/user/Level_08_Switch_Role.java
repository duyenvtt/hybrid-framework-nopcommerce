package com.nopcommerce.user;

import java.util.Random;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import commons.GlobalConstants;
import commons.PageGeneratorManager;
import pageFactory.HomePageObject;
import pageObjects.nopCommerce.admin.AdminDashboardPageObject;
import pageObjects.nopCommerce.admin.AdminLoginPageObject;
import pageObjects.nopCommerce.user.UserCustomerInforPageObject;
import pageObjects.nopCommerce.user.UserHomePageObject;
import pageObjects.nopCommerce.user.UserLoginPageObject;

public class Level_08_Switch_Role extends BaseTest {
	private WebDriver driver;
	private UserHomePageObject userHomePage;
	private UserLoginPageObject userLoginPage;
	private UserCustomerInforPageObject userCustomerInforPage;
	private AdminLoginPageObject adminLoginPage;
	private AdminDashboardPageObject adminDashboardPage;

	private String userEmailAddress, userPassword, adminEmailAddress, adminPassword;

	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browserName) {
		driver = getBrowserDriver(browserName);
//		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
//		driver.get("https://demo.nopcommerce.com/");

		// 1
		userHomePage = PageGeneratorManager.getUserHomePage(driver);
		userEmailAddress = "abc@gmail.com";
		userPassword = "123456";
		adminEmailAddress = "admin@yourstore.com";
		adminPassword = "admin";
	}

	@Test
	public void Role_01_User_To_Admin() {
		// Khoi tao trang login
		userLoginPage = userHomePage.openLoginPage();

		// Login as User role
		userHomePage = userLoginPage.loginAsUser(userEmailAddress, userPassword);

		Assert.assertTrue(userHomePage.isMyAccountDisplayed());
			
		userCustomerInforPage = userHomePage.openMyAccountPage();
		userHomePage = userCustomerInforPage.clickToLogoutLinkAtUserPage(driver);

		// User Home page --> Open Admin Page --> Login Page
		userHomePage.openPageUrl(driver, GlobalConstants.ADMIN_PAGE_URL);
		adminLoginPage = PageGeneratorManager.getAdminLoginPage(driver);

		adminDashboardPage = adminLoginPage.loginAsAdmin(adminEmailAddress, adminPassword);
		Assert.assertTrue(adminDashboardPage.isDashboardHeaderDisplayed());

		// Logout Dashboard Admin --> Login page
		adminLoginPage = adminDashboardPage.clickToLogoutLinkAtAdminPage(driver);

	}

	@Test
	public void Role_02_Admin_To_User() {
		adminLoginPage.openPageUrl(driver, GlobalConstants.USER_PAGE_URL);
		userHomePage = PageGeneratorManager.getUserHomePage(driver);

		// Khoi tao trang login
		userLoginPage = userHomePage.openLoginPage();

		// Login as User role
		userHomePage = userLoginPage.loginAsUser(userEmailAddress, userPassword);

		Assert.assertTrue(userHomePage.isMyAccountDisplayed());

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
