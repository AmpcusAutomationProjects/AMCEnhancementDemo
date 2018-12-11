package com.environmentalVariables;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.MultiPartEmail;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class UtilityClass {
	
	public static WebDriver driver;
	protected static Library demoLib;
	protected static String browser;

	static DateFormat df = new SimpleDateFormat("dd_MM_yy_HH_mm_ss");
	static Date dateobj = new Date();

	static ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter("C:\\a\\extent.html");
	static ExtentReports extent = new ExtentReports();
	static ExtentTest test;
	
	@BeforeTest(alwaysRun = true)
	public void beforeTest() throws Exception  {
 
		demoLib = new Library(driver);
		driver = demoLib.startLocalBrowser();

	}
	@AfterTest(alwaysRun = true)
	public void afterAllTestComplete(ITestResult result) throws Exception {
		extent.attachReporter(htmlReporter);

		// Creates a toggle for the given test, adds all log events under it
		test = extent.createTest("Test Name is.. " + result.getName());

		if (result.getStatus() == ITestResult.FAILURE) {
			test.log(Status.FAIL, result.getName() + "Test Failed!" + result.getThrowable());
			System.out.println("Test Failed");
			// log(Status, details)
			test.log(Status.INFO, "This step shows usage of log(status, details)");
			 
			String temp = demoLib.captureScreenshot("AMC Enhancement", "");
			System.out.println("Attaching Screenshot to extent report....");
			test.fail(result.getThrowable().getMessage(), MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
			System.out.println("Screenshot attached successfully!");

		} else if (result.getStatus() == ITestResult.SKIP) {
			test.log(Status.SKIP, result.getName() + "Test Skipped..." + result.getThrowable());

		} else if (result.getStatus() == ITestResult.SUCCESS) {
			test.log(Status.PASS, result.getName() + " Passed Successfully!");
			System.out.println("Test Passed");

		}
  
		extent.flush();
		System.out.println("Exiting");
		System.out.println("Email started sending...");

		// Create the attachment
		EmailAttachment attachment = new EmailAttachment();
		attachment.setPath("C:\\a\\extent.html");
		attachment.setDisposition(EmailAttachment.ATTACHMENT);
		attachment.setDescription("Test Report");
		attachment.setName("HTML Report");

		// Create the email message
		MultiPartEmail emails = new MultiPartEmail();
		emails.setHostName("smtp.googlemail.com");
		emails.setSmtpPort(465);
		emails.setAuthenticator(new DefaultAuthenticator("dar.korkolis@gmail.com", "5100000008"));
		emails.setSSLOnConnect(true);
		emails.setFrom("dar.korkolis@gmail.com", "Joseph");
		emails.setSubject("Test Report");
		emails.setMsg("HTML Report with a link to download test video");
		emails.addTo("joseph.quansah@ampcus.com", "Joseph");
		// emails.addBcc("fatema.akhtar@ampcus.com", "Fatema");
		// emails.addBcc("veena.pradeep@ampcus.com", "Veena");
		// emails.addBcc("rohit.lal@ampcus.com", "Rohit");
		// emails.addBcc("oma.taiga@ampcus.com", "Oma");
		// add the attachment
		emails.attach(attachment);
		// send the email
		emails.send();
		System.out.println("Email is sent!");

		driver.close();

	}
		
	}


