package utilities;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Properties;

// For email
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import testBase.BaseTest;

public class ExtentReportManager implements ITestListener {

	private ExtentSparkReporter sparkReporter;
	private ExtentReports extent;
	private ExtentTest test;
	private String repName;

	public void onStart(ITestContext testContext) {
		// Generating timestamp for report name
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		repName = "Test-Report-" + timeStamp + ".html";
		sparkReporter = new ExtentSparkReporter(".\\reports\\" + repName);

		// Configuring the Extent report
		sparkReporter.config().setDocumentTitle("eBay Automation Report");
		sparkReporter.config().setReportName("eBay Functional Testing");
		sparkReporter.config().setTheme(Theme.DARK);

		extent = new ExtentReports();
		extent.attachReporter(sparkReporter);

		// Adding system information to the report
		extent.setSystemInfo("Application", "eBay");
		extent.setSystemInfo("Module", "Module1");
		extent.setSystemInfo("Sub Module", "SubModule1");
		extent.setSystemInfo("User Name", System.getProperty("user.name"));
		extent.setSystemInfo("Environemnt", "QA");

		// Adding test environment details to the report
		String os = testContext.getCurrentXmlTest().getParameter("os");
		extent.setSystemInfo("Operating System", os);

		String browser = testContext.getCurrentXmlTest().getParameter("browser");
		extent.setSystemInfo("Browser", browser);

		List<String> includedGroups = testContext.getCurrentXmlTest().getIncludedGroups();
		if (!includedGroups.isEmpty()) {
			extent.setSystemInfo("Groups", includedGroups.toString());
		}
	}

	public void onTestSuccess(ITestResult result) {
		// Creating test instance for successful test
		test = extent.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.PASS, result.getName() + " got successfully executed");
	}

	public void onTestFailure(ITestResult result) {
		// Creating test instance for failed test
		test = extent.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.FAIL, result.getName() + " got failed");
		test.log(Status.INFO, result.getThrowable().getMessage());

		// Capturing and attaching screenshot for failed test
		try {
			String imgPath = new BaseTest().captureScreen(result.getName());
			test.addScreenCaptureFromPath(imgPath);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}

	public void onTestSkipped(ITestResult result) {
		// Creating test instance for skipped test
		test = extent.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.SKIP, result.getName() + " got skipped");
		test.log(Status.INFO, result.getThrowable().getMessage());
	}

	public void onFinish(ITestContext testContext) {
		// Flushing the Extent report
		extent.flush();

		// Opening report on desktop
		String pathOfExtentReport = System.getProperty("user.dir") + "\\reports\\" + repName;
		File extentReport = new File(pathOfExtentReport);
		try {
			Desktop.getDesktop().browse(extentReport.toURI());
		} catch (IOException e) {
			e.printStackTrace();
		}

		// Sending email with attachment
		// sendEmail(sender email, sender password(encrypted), recipient email);
		// sendEmail("abc@gmail.com", "xyz123xyz", "xyz@gmail.com");
	}

	// Method for sending email
	public void sendEmail(String senderEmail, String senderPassword, String recipientEmail) {
		// SMTP server properties
		Properties properties = new Properties();
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.starttls.enable", "true");
		properties.put("mail.smtp.host", "smtp.gmail.com");
		properties.put("mail.smtp.port", "587");

		// Creating a Session object
		Session session = Session.getInstance(properties, new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(senderEmail, senderPassword);
			}
		});

		try {
			// Creating a MimeMessage object
			Message message = new MimeMessage(session);

			// Setting sender and recipient addresses
			message.setFrom(new InternetAddress(senderEmail));
			message.setRecipient(Message.RecipientType.TO, new InternetAddress(recipientEmail));

			// Setting the subject
			message.setSubject("Test Report with attachment");

			// Creating a MimeMultipart object
			Multipart multipart = new MimeMultipart();

			// Attaching the file
			String filePath = ".\\reports\\" + repName;
			String fileName = repName;

			MimeBodyPart attachmentPart = new MimeBodyPart();
			attachmentPart.attachFile(filePath);
			attachmentPart.setFileName(fileName);

			// Creating a MimeBodyPart for the text content
			MimeBodyPart textPart = new MimeBodyPart();
			textPart.setText("Please find the attached file.");

			// Adding the parts to the multipart
			multipart.addBodyPart(textPart);
			multipart.addBodyPart(attachmentPart);

			// Setting the content of the message
			message.setContent(multipart);

			// Sending the message
			Transport.send(message);

			System.out.println("Email sent successfully!");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
