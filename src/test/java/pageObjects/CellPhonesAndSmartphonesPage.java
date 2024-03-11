package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CellPhonesAndSmartphonesPage extends BasePage {

	// Constructor
	public CellPhonesAndSmartphonesPage(WebDriver driver) {
		super(driver);
	}

	// WebElements
	@FindBy(css = "button[aria-label='All Filters']")
	private WebElement allFilters;

	// Clicks on the All Filters button to open the filter page
	public void clickAllFilters() {
		try {
			allFilters.click();
		} catch (Exception e) {
			// Log or handle the exception appropriately
			e.printStackTrace();
		}
	}
}
