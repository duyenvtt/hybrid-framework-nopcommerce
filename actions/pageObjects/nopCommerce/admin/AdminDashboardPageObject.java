package pageObjects.nopCommerce.admin;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.nopCommerce.admin.AdminDashboardPageUI;
import pageUIs.nopCommerce.admin.AdminLoginPageUI;

public class AdminDashboardPageObject extends BasePage {
	private WebDriver driver;
	
	public AdminDashboardPageObject(WebDriver driver) {
		this.driver=driver;
	}

	public boolean isDashboardHeaderDisplayed() {
		waitForElementVisible(driver, AdminDashboardPageUI.DASHBOARD_HEADER);
		return isElementDisplayed(driver,AdminDashboardPageUI.DASHBOARD_HEADER);
	}
}
