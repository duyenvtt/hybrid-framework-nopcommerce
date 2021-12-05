package pageObjects.nopCommerce.admin;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import commons.PageGeneratorManager;
import pageUIs.nopCommerce.admin.AdminLoginPageUI;

public class AdminLoginPageObject extends BasePage {
	private WebDriver driver;
	
	public AdminLoginPageObject(WebDriver driver) {
		this.driver=driver;
	}
	
	public void inputToUsernameTextbox(String emailAddress) {		
		waitForElementVisible(driver, AdminLoginPageUI.EMAIL_TEXTBOX);
		sendKeyToElement(driver, AdminLoginPageUI.EMAIL_TEXTBOX, emailAddress);
	}
	
	public void inputToPasswordTextbox(String passWord) {		
		waitForElementVisible(driver, AdminLoginPageUI.PASSWORD_TEXTBOX);
		sendKeyToElement(driver, AdminLoginPageUI.PASSWORD_TEXTBOX, passWord);
	}
	
	public AdminDashboardPageObject clickToLoginButton() {	
		waitForElementClickable(driver, AdminLoginPageUI.LOGIN_BUTTON);
		clickToElement(driver, AdminLoginPageUI.LOGIN_BUTTON);
		return PageGeneratorManager.getAdminDashboardPage(driver);
	}
	
	public AdminDashboardPageObject loginAsAdmin(String emailAddress, String passWord) {
		inputToUsernameTextbox(emailAddress);
		inputToPasswordTextbox(passWord);
		return clickToLoginButton();
	}
	
	


}
