package pageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SearchPage extends BasePage {

	// WebElement
	@FindBy(xpath = "//ul//li//div[@class='s-item__wrapper clearfix']//a[@class='s-item__link']")
	private List<WebElement> searchResultTitles;

	@FindBy(id = "gh-ac")
	private WebElement searchBox;

	// Constructor
	public SearchPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	// Method to get the text of the first title on the page
	public String getFirstTitleOnPage() {
		try {
			if (!searchResultTitles.isEmpty()) {
				return searchResultTitles.get(0).getText();
			} else {
				return "No titles found on the page";
			}
		} catch (Exception e) {
			// Log or handle the exception appropriately
			e.printStackTrace();
			return "Error occurred while retrieving the first title";
		}
	}

	// Method to retrieve the text entered in the search box
	public String getTextFromSearchBox() {
		try {
			return searchBox.getAttribute("value");
		} catch (Exception e) {
			// Log or handle the exception appropriately
			e.printStackTrace();
			return "Error occurred while retrieving text from the search box";
		}
	}
}
