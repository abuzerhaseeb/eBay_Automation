package pageObjects;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {

	// WebDriver instance
	protected WebDriver driver;

	// Default timeout
	private Duration defaultTimeout = Duration.ofSeconds(5);

	// Constructor
	public BasePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// Waits for a WebElement to appear on the page with the default timeout
	public void waitForWebElementToAppear(WebElement webElement) {
		waitForWebElementToAppear(webElement, defaultTimeout);
	}

	// Waits for a WebElement to appear on the page with a specified timeout
	public void waitForWebElementToAppear(WebElement webElement, Duration timeout) {
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		wait.until(ExpectedConditions.visibilityOf(webElement));
	}

}
