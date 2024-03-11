package pageObjects;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class FilterPage extends BasePage {

	// JavascriptExecutor instance
	protected JavascriptExecutor js;

	// Constructor
	public FilterPage(WebDriver driver) {
		super(driver); // Initialize the WebDriver instance
		this.js = (JavascriptExecutor) driver; // Initialize the JavascriptExecutor instance
	}

	// WebElements
	@FindBy(css = "div[class='x-overlay__wrapper--right']")
	private WebElement overlayLeft;

	@FindBy(css = "div[id*='mainPanel-condition']")
	private WebElement conditionAllFilterOption;

	@FindBy(css = "input[id*='ItemCondition_New_cbx']")
	private WebElement newConditionOption;

	@FindBy(css = "div[id*='mainPanel-price']")
	private WebElement priceAllFilterOption;

	@FindBy(css = "input[aria-label='Minimum Value, US Dollar']")
	private WebElement minPriceTextbox;

	@FindBy(css = "input[aria-label='Maximum Value, US Dollar']")
	private WebElement maxPriceTextbox;

	@FindBy(css = "div[id*='mainPanel-location']")
	private WebElement locationAllFilterOption;

	@FindBy(css = "input[aria-label='Worldwide']")
	private WebElement worldwideLocation;

	@FindBy(css = "div[id*='tray-tray'] span")
	private List<WebElement> appliedFilters;

	@FindBy(css = "button[aria-label='Apply']")
	private WebElement applyFilterButton;

	// Scrolls until the conditionAllFilterOption is visible
	public void scrollTillConditionAllFilterOption() {
		try {
			waitForWebElementToAppear(overlayLeft); // Wait for overlayLeft element to appear
			waitForWebElementToAppear(conditionAllFilterOption); // Wait for conditionAllFilterOption element to appear
			scrollTillElement(driver, overlayLeft, conditionAllFilterOption); // Scroll to conditionAllFilterOption
																				// element
		} catch (Exception e) {
			// Log or handle the exception appropriately
			e.printStackTrace();
		}
	}

	// Selects the conditionAllFilterOption
	public void selectConditionAllFilterOption() {
		try {
			conditionAllFilterOption.click();
		} catch (Exception e) {
			// Log or handle the exception appropriately
			e.printStackTrace();
		}
	}

	// Selects the newConditionOption
	public void selectNewConditionOptions() {
		try {
			newConditionOption.click();
		} catch (Exception e) {
			// Log or handle the exception appropriately
			e.printStackTrace();
		}
	}

	// Retrieves the newConditionOption WebElement
	public WebElement getNewConditionOption() {
		return newConditionOption;
	}

	// Clicks on the priceAllFilterOption
	public void clickPriceAllFilterOption() {
		try {
			priceAllFilterOption.click();
		} catch (Exception e) {
			// Log or handle the exception appropriately
			e.printStackTrace();
		}
	}

	// Sets the minimum price
	public void setMinPrice(String minPrice) {
		try {
			minPriceTextbox.sendKeys(minPrice);
		} catch (Exception e) {
			// Log or handle the exception appropriately
			e.printStackTrace();
		}
	}

	// Sets the maximum price
	public void setMaxPrice(String maxPrice) {
		try {
			maxPriceTextbox.sendKeys(maxPrice);
		} catch (Exception e) {
			// Log or handle the exception appropriately
			e.printStackTrace();
		}
	}

	// Retrieves the minPriceTextbox WebElement
	public WebElement getMinPriceTextbox() {
		return minPriceTextbox;
	}

	// Retrieves the maxPriceTextbox WebElement
	public WebElement getMaxPriceTextbox() {
		return maxPriceTextbox;
	}

	// Selects the locationAllFilterOption
	public void selectLocationAllFilterOption() {
		try {
			locationAllFilterOption.click();
		} catch (Exception e) {
			// Log or handle the exception appropriately
			e.printStackTrace();
		}
	}

	// Selects the worldwideLocation
	public void selectWorldwideLocation() {
		try {
			worldwideLocation.click();
		} catch (Exception e) {
			// Log or handle the exception appropriately
			e.printStackTrace();
		}
	}

	// Scrolls the page by 50 pixels
	public void scrollBy50() {
		try {
			js.executeScript("window.scrollTo(0,50);");
		} catch (Exception e) {
			// Log or handle the exception appropriately
			e.printStackTrace();
		}
	}

	// Retrieves the worldwideLocation WebElement
	public WebElement getWorldwideLocation() {
		return worldwideLocation;
	}

	// Applies the selected filters
	public void applyFilters() {
		try {
			applyFilterButton.click();
		} catch (Exception e) {
			// Log or handle the exception appropriately
			e.printStackTrace();
		}
	}

	// Retrieves the list of applied filters
	public List<String> getAppliedFilters() {
		List<String> list = new ArrayList<String>();
		for (WebElement filter : appliedFilters) {
			list.add(filter.getText());
		}
		return list;
	}

	// Scrolls to the specified element
	public void scrollTillElement(WebDriver driver, WebElement parentElement, WebElement childElement) {
		try {
			if (parentElement != null && childElement != null) {
				js.executeScript("arguments[0].scrollTop = arguments[1].offsetTop", parentElement, childElement);
			} else {
				System.out.println("Parent or child element is null.");
			}
		} catch (Exception e) {
			System.out.println("Error occurred while scrolling: " + e.getMessage());
		}
	}
}
