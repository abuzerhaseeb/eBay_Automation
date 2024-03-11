package testBase;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class BaseTest {

	static public WebDriver driver;
	public Logger logger;
	public Properties p;

	// Method to set up WebDriver instance before test class execution
	@BeforeClass(alwaysRun = true)
	@Parameters({ "os", "browser" })
	public void setup(String os, String br) throws IOException {

		// Loading properties file
		FileReader file = new FileReader(".//src//test//resources//config.properties");
		p = new Properties();
		p.load(file);

		// Loading log4j for logging
		logger = LogManager.getLogger(this.getClass());

		if (p.getProperty("execution_env").equalsIgnoreCase("remote")) {
			// Remote WebDriver setup
			DesiredCapabilities capabilities = new DesiredCapabilities();

			// Setting OS capabilities
			if (os.equalsIgnoreCase("windows")) {
				capabilities.setPlatform(org.openqa.selenium.Platform.WINDOWS);
			} else if (os.equalsIgnoreCase("mac")) {
				capabilities.setPlatform(org.openqa.selenium.Platform.MAC);
			} else {
				logger.error("No matching os..");
				return;
			}

			// Setting browser capabilities
			switch (br.toLowerCase()) {
			case "chrome":
				capabilities.setBrowserName("chrome");
				break;
			case "edge":
				capabilities.setBrowserName("MicrosoftEdge");
				break;
			default:
				logger.error("No matching browser..");
				return;
			}

			// Creating RemoteWebDriver instance
			driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capabilities);

		} else if (p.getProperty("execution_env").equalsIgnoreCase("local")) {
			// Local WebDriver setup
			switch (br.toLowerCase()) {
			case "chrome":
				driver = new ChromeDriver();
				break;
			case "edge":
				driver = new EdgeDriver();
				break;
			default:
				logger.error("No matching browser..");
				return;
			}
		}

		// Setting common configurations for WebDriver
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));

		// Navigating to application URL
		driver.get(p.getProperty("appURL"));
		driver.manage().window().maximize();
	}

	// Method to tear down WebDriver instance after test class execution
	@AfterClass(alwaysRun = true)
	public void tearDown() {
	    if (driver != null) {
	        driver.quit();
	    }
	}

	// Method to capture screenshot
	public String captureScreen(String tname) {
		try {
			// Generating timestamp for file name
			String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());

			// Taking screenshot
			TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
			File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);

			// Setting target file path
			String targetFilePath = System.getProperty("user.dir") + "\\screenshots\\" + tname + "_" + timeStamp
					+ ".png";
			File targetFile = new File(targetFilePath);

			// Renaming source file to target file
			sourceFile.renameTo(targetFile);

			// Returning file path of captured screenshot
			return targetFilePath;
		} catch (Exception e) {
			logger.error("Failed to capture screenshot: " + e.getMessage());
			return null;
		}
	}
}
