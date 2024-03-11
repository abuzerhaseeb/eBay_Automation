package testCases;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.SearchPage;
import testBase.BaseTest;

public class TC_002_ProductSearchByName extends BaseTest {

	private HomePage homePage;
	private SearchPage searchPage;

	@BeforeMethod(alwaysRun = true)
	public void initializePages() {
		homePage = new HomePage(driver);
		searchPage = new SearchPage(driver);
	}

	@Test(groups = { "regression", "master" })
	public void productSearchByName() {

		// Define the product to be searched
		String productToBeSearched = p.getProperty("searchProductName");

		try {
			// Assertion for browser name, title, and URL of the home page
			Assert.assertTrue(homePage.getBrowserName().contains("Chrome"), "The browser is not Chrome");
			Assert.assertEquals(homePage.getTitle(), "Electronics, Cars, Fashion, Collectibles & More | eBay",
					"Title does not match");
			Assert.assertEquals(homePage.getUrl(), "https://www.ebay.com/", "URL does not match");

			// Search for the product
			homePage.searchProduct(productToBeSearched);
			homePage.selectAllCategoriesDropdownButton();
			homePage.selectComputersTabletAndNetworkingOption();
			homePage.clickSearch();

			// Wait for search results to load (add explicit wait if necessary)

			// Assert the text from search box
			Assert.assertEquals(searchPage.getTextFromSearchBox(), productToBeSearched,
					"The text in the search box does not match the searched product");

			// Get the first title on the search page
			String firstTitle = searchPage.getFirstTitleOnPage();

			// Assertion to verify if the first title contains the searched product name
			Assert.assertTrue(firstTitle.toLowerCase().contains(productToBeSearched.toLowerCase()),
					"The first title on the search page does not contain the searched product name");

		} catch (AssertionError e) {
			throw e;
		} catch (Exception e) {
			// Handle other exceptions
			logger.error("An unexpected error occurred: " + e.getMessage());
			Assert.fail("An unexpected error occurred: " + e.getMessage());
		}
	}
}
