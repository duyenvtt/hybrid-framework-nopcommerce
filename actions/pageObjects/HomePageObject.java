package pageObjects;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.HomePageUI;

public class HomePageObject extends BasePage {
	private WebDriver driver;
	
	public HomePageObject(WebDriver driver) {
		this.driver=driver;
		System.out.println("Driver at HomePageObject:"+driver.toString());

	}

	public void clickToRegisterLink() {
		// TODO Auto-generated method stub
		waitForElementClickable(driver, HomePageUI.REGISTER_LINK);
		clickToElement(driver, HomePageUI.REGISTER_LINK);
		
	}

}
