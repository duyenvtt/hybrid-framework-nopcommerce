package pageObjects.nopCommerce.user;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.nopCommerce.user.CustomerInforPageUI;
import pageUIs.nopCommerce.user.RegisterPageUI;

public class UserCustomerInforPageObject extends BasePage {
	private WebDriver driver;
	
	public UserCustomerInforPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public boolean isCustomerInforPageDisplayed() {
		waitForAllElementVisible(driver, CustomerInforPageUI.CUSTOMER_INFOR_HEADER);
		return isElementDisplayed(driver, CustomerInforPageUI.CUSTOMER_INFOR_HEADER);
	}

//	public AddressPageObject openAddressPage() {
//		waitForElementVisible(driver, CustomerInforPageUI.ADDRESS_LINK);
//		clickToElement(driver, CustomerInforPageUI.ADDRESS_LINK);
//		return PageGeneratorManager.getAddressPage(driver);
//	}

}
