package testCases;

import java.util.Arrays;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import pageObjects.CellPhonesAndAccessoriesPage;
import pageObjects.CellPhonesAndSmartphonesPage;
import pageObjects.FilterPage;
import pageObjects.HomePage;
import testBase.BaseTest;

public class TC_001_ProductSearchByMultipleFilters extends BaseTest {

	private HomePage homePage;
	private CellPhonesAndAccessoriesPage cellPhonesAndAccessoriesPage;
	private CellPhonesAndSmartphonesPage cellPhonesAndSmartphonesPage;
	private FilterPage filterPage;

	@BeforeMethod(alwaysRun = true)
	public void setUpPages() {
		homePage = new HomePage(driver);
		cellPhonesAndAccessoriesPage = new CellPhonesAndAccessoriesPage(driver);
		cellPhonesAndSmartphonesPage = new CellPhonesAndSmartphonesPage(driver);
		filterPage = new FilterPage(driver);
	}

	@Test(groups = { "regression", "master" })
	// Test case for searching products using multiple filters.
	public void productSearchByMultipleFilters() throws InterruptedException {

		try {
			// Assertion for browser name, title, and URL of the home page
			String expectedBrowser = "Chrome";
			Assert.assertTrue(homePage.getBrowserName().contains(expectedBrowser),
					"The browser is not " + expectedBrowser);
			Assert.assertEquals(homePage.getTitle(), "Electronics, Cars, Fashion, Collectibles & More | eBay",
					"Title does not match");
			Assert.assertEquals(homePage.getUrl(), "https://www.ebay.com/", "URL does not match");

			// Navigate to Cell Phones and Accessories category
			homePage.clickShopByCategory();
			homePage.clickCellPhonesAndAccessoriesOption();

			// Navigate to Cell Phones and Smartphones section
			cellPhonesAndAccessoriesPage.clickCellPhonesAndSmartphonesLink();

			// Navigate to filters
			cellPhonesAndSmartphonesPage.clickAllFilters();

			// Select new condition and assert
			filterPage.scrollTillConditionAllFilterOption();
			filterPage.selectConditionAllFilterOption();
			filterPage.selectNewConditionOptions();
			Assert.assertTrue(filterPage.getNewConditionOption().isSelected(),
					"New condition checkbox is deselected when it should be selected");

			// Select price range and assert
			filterPage.clickPriceAllFilterOption();
			filterPage.setMinPrice("10");
			filterPage.setMaxPrice("20");
			Assert.assertTrue(filterPage.getMinPriceTextbox().isEnabled(), "Minimum price textbox is not enabled");
			Assert.assertTrue(filterPage.getMaxPriceTextbox().isEnabled(), "Maximum price textbox is not enabled");

			// Select worldwide location and assert
			filterPage.selectLocationAllFilterOption();
			filterPage.selectWorldwideLocation();
			filterPage.scrollBy50();
			Assert.assertTrue(filterPage.getWorldwideLocation().isSelected(),
					"Worldwide radiobutton is deselected when it should be selected");

			// Define expected filters and get applied filters
			String[] expectedFilters = { "New", "$10 - $20", "Worldwide" };
			List<String> appliedFiltersFound = filterPage.getAppliedFilters();

			// Convert expected filters to a list for comparison
			List<String> expectedFiltersList = Arrays.asList(expectedFilters);

			// Assertions for applied filters
			Assert.assertEquals(appliedFiltersFound.size(), expectedFilters.length);
			Assert.assertTrue(appliedFiltersFound.containsAll(expectedFiltersList),
					"Applied filters do not match expected filters");

			// Apply filters and verify results
			filterPage.applyFilters();

		} catch (AssertionError e) {
			throw e;
		} catch (Exception e) {
			// Handle other exceptions
			logger.error("An unexpected error occurred: " + e.getMessage());
			Assert.fail("An unexpected error occurred: " + e.getMessage());
		}
	}
}
