package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CellPhonesAndAccessoriesPage extends BasePage {

	// Constructor
	public CellPhonesAndAccessoriesPage(WebDriver driver) {
		super(driver);
	}

	// WebElements
	@FindBy(xpath = "//a[text()='Cell Phones & Smartphones']")
	private WebElement cellPhonesAndSmartphonesLink;

	// Clicks on the Cell Phones & Smartphones link and opens the filter page
	public void clickCellPhonesAndSmartphonesLink() {
		try {
			cellPhonesAndSmartphonesLink.click();
		} catch (Exception e) {
			// Log or handle the exception appropriately
			e.printStackTrace();
		}
	}
}
