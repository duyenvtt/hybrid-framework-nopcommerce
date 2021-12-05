package pageObjects.nopCommerce.user;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import commons.BasePage;
import commons.PageGeneratorManager;
import pageUIs.nopCommerce.user.LoginPageUI;

public class UserLoginPageObject extends BasePage {
	private WebDriver driver;
	
	public UserLoginPageObject(WebDriver driver) {
		this.driver=driver;
	}

	public UserHomePageObject clickToLoginButton() {
		waitForElementClickable(driver, LoginPageUI.LOGIN_BUTTON);
		clickToElement(driver, LoginPageUI.LOGIN_BUTTON);
		return PageGeneratorManager.getUserHomePage(driver);
	}

	public String getErrorMessageAtEmailTextbox() {
		waitForElementVisible(driver, LoginPageUI.EMAIL_ERROR_MESSAGE);
		return getElementText(driver, LoginPageUI.EMAIL_ERROR_MESSAGE);
	}
	
	public String getErrorMessageAtLoginForm() {
		waitForElementVisible(driver, LoginPageUI.INVALID_ACCOUNT_MESSAGE);
		return getElementText(driver, LoginPageUI.INVALID_ACCOUNT_MESSAGE);
	}

	public void inputToEmailTextbox(String Email) {
		waitForElementVisible(driver, LoginPageUI.EMAIL_TEXTBOX);
		sendKeyToElement(driver, LoginPageUI.EMAIL_TEXTBOX, Email);
	}

	public void inputToPasswordTextbox(String Password) {
		waitForElementVisible(driver, LoginPageUI.PASSWORD_TEXTBOX);
		sendKeyToElement(driver, LoginPageUI.PASSWORD_TEXTBOX, Password);		
	}

	public UserHomePageObject loginAsUser(String Email, String Password) {
		inputToEmailTextbox(Email);
		inputToPasswordTextbox(Password);
		return clickToLoginButton();	
	}

}
