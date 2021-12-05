package commons;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.opera.OperaDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {
	private WebDriver driver;
	private String projectPath = System.getProperty("user.dir");

	public WebDriver getBrowserDriver(String browserName) {
		if (browserName.equals("chrome")) {
//			System.setProperty("webdriver.chrome.driver",projectPath + "\\browserDrivers\\chromedriver.exe");
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		} else if (browserName.equals("headlessChrome")) {
//			System.setProperty("webdriver.chrome.driver",projectPath + "\\browserDrivers\\chromedriver.exe");
			WebDriverManager.chromedriver().setup();
			ChromeOptions options = new ChromeOptions();
			options.addArguments("headless");
			options.addArguments("window-size=1920x1080");
			driver = new ChromeDriver(options);
		} else if (browserName.equals("edge")) {
//			System.setProperty("webdriver.edge.driver",projectPath + "\\browserDrivers\\msedgedriver.exe");
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
		} else if (browserName.equals("firefox")) {
//			System.setProperty("webdriver.gecko.driver",projectPath + "\\browserDrivers\\geckodriver.exe");
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		} else if (browserName.equals("headlessFirefox")) {
//			System.setProperty("webdriver.gecko.driver",projectPath + "\\browserDrivers\\geckodriver.exe");
			WebDriverManager.firefoxdriver().setup();
			FirefoxOptions options = new FirefoxOptions();
			options.addArguments("--headless");
			options.addArguments("window-size=1920x1080");
			driver = new FirefoxDriver(options);
		} else if (browserName.equals("ie")) {
//			System.setProperty("webdriver.gecko.driver",projectPath + "\\browserDrivers\\geckodriver.exe");
			//Tai IE 32 bit
			WebDriverManager.iedriver().arch32().setup();
			driver = new InternetExplorerDriver();
		}else if (browserName.equals("coccoc")) {
			// Coccoc - 6 version = version cua chrome drive
//			System.setProperty("webdriver.chrome.driver",projectPath + "\\browserDrivers\\chromedriver_93.exe");
			WebDriverManager.chromedriver().driverVersion("93.0.4577.63").setup();
			ChromeOptions options = new ChromeOptions();
			// Tim thu muc cai coccoc
			options.setBinary("C:\\Program Files (x86)\\CocCoc\\Browser\\Application\\browser.exe");
			driver = new ChromeDriver(options);
		} else if (browserName.equals("opera")) {
//			System.setProperty("webdriver.opera.driver", projectPath + "\\browserDrivers\\operadriver.exe");
			WebDriverManager.operadriver().setup();
			driver = new OperaDriver();
		} else if (browserName.equals("brave")) {
			// Dung trung version voi brave
//			System.setProperty("webdriver.chrome.driver",projectPath + "\\browserDrivers\\chromedriver_95.exe");
			WebDriverManager.chromedriver().driverVersion("95.0.4638.10").setup();
			ChromeOptions options = new ChromeOptions();
			// Tim thu muc cai coccoc
			options.setBinary("C:\\Program Files\\BraveSoftware\\Brave-Browser\\Application\\brave.exe");
			driver = new ChromeDriver(options);
		}

		else {
			throw new RuntimeException("Browser invalid");
		}
		
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.get("https://demo.nopcommerce.com/");
		return driver;
	}

}
