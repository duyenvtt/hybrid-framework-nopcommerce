package pageFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import commons.BasePageFactory;
import pageUIs.nopCommerce.user.RegisterPageUI;

public class RegisterPageObject extends BasePageFactory {
	private WebDriver driver;

	public RegisterPageObject(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(id="FirstName")
	private WebElement firstNameTextbox;
	
	@FindBy(id="LastName")
	private WebElement lastNameTextbox;
	
	@FindBy(id="Email")
	private WebElement emailTextbox;
	
	@FindBy(id="Password")
	private WebElement passwordTextbox;
	
	@FindBy(id="ConfirmPassword")
	private WebElement confirmPasswordTextbox;
	
	@FindBy(id="register-button")
	private WebElement registerButton;
	
	@FindBy(id="FirstName-error")
	private WebElement firstNameErrorMessage;
	
	@FindBy(id="LastName-error")
	private WebElement lastNameErrorMessage;
	
	@FindBy(id="Email-error")
	private WebElement emailErrorMessage;
	
	@FindBy(id="Password-error")
	private WebElement passwordErrorMessage;
	
	@FindBy(id="ConfirmPassword-error")
	private WebElement confirmPasswordErrorMessage;
	
	@FindBy(xpath="//div[@class='result']")
	private WebElement registerSuccessMessage;
	
	@FindBy(xpath="//a[@class='ico-logout']")
	private WebElement logoutLink;
	
	@FindBy(xpath="//div[contains(@class,'message-error')]")
	private WebElement existingEmailErrorMessage;
	
	//Actions
	public void clickToRegisterButton() {
		waitForElementClickable(driver, registerButton);
		clickToElement(driver, registerButton);
	}

	public String getErrorMessageAtFirstnameTextbox() {
		waitForElementVisible(driver, firstNameErrorMessage);
		return getElementText(driver, firstNameErrorMessage);
	}

	public String getErrorMessageAtLastnameTextbox() {
		waitForElementVisible(driver, lastNameErrorMessage);
		return getElementText(driver, lastNameErrorMessage);
	}

	public String getErrorMessageAtEmailTextbox() {
		waitForElementVisible(driver, emailErrorMessage);
		return getElementText(driver, emailErrorMessage);
	}

	public String getErrorMessageAtPasswordTextbox() {
		waitForElementVisible(driver, passwordErrorMessage);
		return getElementText(driver, passwordErrorMessage);
	}

	public String getErrorMessageAtConfirmPasswordTextbox() {
		waitForElementVisible(driver, confirmPasswordErrorMessage);
		return getElementText(driver, confirmPasswordErrorMessage);
	}

	public void inputToFirstNameTextbox(String firstName) {
		waitForElementVisible(driver, firstNameTextbox);
		sendKeyToElement(driver, firstNameTextbox, firstName);
	}

	public void inputToLastNameTextbox(String lastName) {
		waitForElementVisible(driver, lastNameTextbox);
		sendKeyToElement(driver, lastNameTextbox, lastName);

	}

	public void inputToEmailTextbox(String email) {
		waitForElementVisible(driver, emailTextbox);
		sendKeyToElement(driver, emailTextbox, email);

	}

	public void inputToPasswordTextbox(String password) {
		waitForElementVisible(driver, passwordTextbox);
		sendKeyToElement(driver, passwordTextbox, password);
	}

	public void inputToConfirmPasswordTextbox(String confirmPassword) {
		waitForElementVisible(driver, confirmPasswordTextbox);
		sendKeyToElement(driver, confirmPasswordTextbox, confirmPassword);
	}

	public void clickToLogoutLink() {
		waitForElementClickable(driver, logoutLink);
		clickToElement(driver, logoutLink);
	}

	public String getRegisterSuccessMessage() {
		waitForElementVisible(driver, registerSuccessMessage);
		return getElementText(driver, registerSuccessMessage);
	}

	public String getErrorExistingMessage() {
		waitForElementVisible(driver, existingEmailErrorMessage);
		return getElementText(driver, existingEmailErrorMessage);
	}

	
}
