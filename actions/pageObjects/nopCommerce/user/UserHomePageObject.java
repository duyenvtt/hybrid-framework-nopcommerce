package pageObjects.nopCommerce.user;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import commons.PageGeneratorManager;
import pageUIs.nopCommerce.user.HomePageUI;
import pageUIs.nopCommerce.user.LoginPageUI;

public class UserHomePageObject extends BasePage {
	private WebDriver driver;
	
	public UserHomePageObject(WebDriver driver) {
		this.driver=driver;
		System.out.println("Driver at HomePageObject:"+driver.toString());

	}

	public UserRegisterPageObject openRegisterPage() {
		// TODO Auto-generated method stub
		waitForElementClickable(driver, HomePageUI.REGISTER_LINK);
		clickToElement(driver, HomePageUI.REGISTER_LINK);
		return PageGeneratorManager.getUserRegisterPage(driver);	
	}
	
	public UserLoginPageObject openLoginPage() {
		waitForElementVisible(driver, HomePageUI.LOGIN_LINK);
		clickToElement(driver,  HomePageUI.LOGIN_LINK);
		return PageGeneratorManager.getUserLoginPage(driver);
	}

	public boolean isMyAccountDisplayed() {
		waitForElementVisible(driver, HomePageUI.MY_ACCOUNT_LINK);
		return isElementDisplayed(driver, HomePageUI.MY_ACCOUNT_LINK);
	}

	public UserCustomerInforPageObject openMyAccountPage() {
		waitForElementVisible(driver, HomePageUI.MY_ACCOUNT_LINK);
		clickToElement(driver,  HomePageUI.MY_ACCOUNT_LINK);
		return PageGeneratorManager.getUserCustomerInforPage(driver);
	}


}
