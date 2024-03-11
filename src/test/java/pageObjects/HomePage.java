package pageObjects;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {

	// JavascriptExecutor instance
	private JavascriptExecutor js;

	// Constructor
	public HomePage(WebDriver driver) {
		super(driver); // Initialize the WebDriver instance
		this.js = (JavascriptExecutor) driver; // Initialize the JavascriptExecutor instance
	}

	// Declare WebElements
	@FindBy(id = "gh-shop-a")
	private WebElement shopByCategoryDropdownButton;

	@FindBy(xpath = "//a[text()='Cell phones & accessories']")
	private WebElement cellPhonesAndAccessoriesOption;

	@FindBy(id = "gh-ac")
	private WebElement searchBox;

	@FindBy(id = "gh-btn")
	private WebElement searchButton;

	@FindBy(id = "gh-cat")
	private WebElement allCategoriesDropdownButton;

	@FindBy(xpath = "//option[text()='Computers/Tablets & Networking']")
	private WebElement computersTabletAndNetworkingOption;

	// Selects the Cell Phones and Accessories category from the shop by category
	// dropdown
	public void clickShopByCategory() {
		try {
			shopByCategoryDropdownButton.click();
		} catch (Exception e) {
			// Log or handle the exception appropriately
			e.printStackTrace();
		}
	}

	public void clickCellPhonesAndAccessoriesOption() {
		try {
			cellPhonesAndAccessoriesOption.click();
		} catch (Exception e) {
			// Log or handle the exception appropriately
			e.printStackTrace();
		}
	}

	// Enters the product name in the search box
	public void searchProduct(String productName) {
		try {
			searchBox.sendKeys(productName);
		} catch (Exception e) {
			// Log or handle the exception appropriately
			e.printStackTrace();
		}
	}

	public void selectAllCategoriesDropdownButton() {
		try {
			allCategoriesDropdownButton.click();
		} catch (Exception e) {
			// Log or handle the exception appropriately
			e.printStackTrace();
		}
	}

	public void selectComputersTabletAndNetworkingOption() {
		try {
			computersTabletAndNetworkingOption.click();
		} catch (Exception e) {
			// Log or handle the exception appropriately
			e.printStackTrace();
		}
	}

	// Clicks the search button
	public void clickSearch() {
		try {
			searchButton.click();
		} catch (Exception e) {
			// Log or handle the exception appropriately
			e.printStackTrace();
		}
	}

	// Returns the title of the current web page
	public String getTitle() {
		try {
			return driver.getTitle();
		} catch (Exception e) {
			// Log or handle the exception appropriately
			e.printStackTrace();
			return null;
		}
	}

	// Returns the URL of the current web page
	public String getUrl() {
		try {
			return driver.getCurrentUrl();
		} catch (Exception e) {
			// Log or handle the exception appropriately
			e.printStackTrace();
			return null;
		}
	}

	// Returns the name of the browser being used
	public String getBrowserName() {
		try {
			js = (JavascriptExecutor) driver;
			return (String) js.executeScript("return navigator.userAgent;");
		} catch (Exception e) {
			// Log or handle the exception appropriately
			e.printStackTrace();
			return null;
		}
	}
}
