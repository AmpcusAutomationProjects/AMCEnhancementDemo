package com.environmentalVariables;

import static org.testng.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.internal.WrapsDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.base.Function;
import com.google.common.io.Files;

public class Library {
	
	private static WebDriver driver;
	final static Logger logger = Logger.getLogger(Library.class);
	
	static DateFormat df = new SimpleDateFormat("dd_MM_yy_HH_mm_ss");
	static Date dateobj = new Date();

	public Library(WebDriver _driver) {

		driver = _driver;

	}
	
	// Starting Chrome Browser
			public WebDriver startChromeBrowser() {
				try {
					System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
					driver = new ChromeDriver();
					driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
					driver.manage().timeouts().pageLoadTimeout(70, TimeUnit.SECONDS);
					driver.manage().window().maximize();
				} catch (Exception e) {
					logger.error("Error- ", e);
					assertTrue(false);
				}
				return driver;

			}
			
			// Starting Firefox Browser
			public WebDriver startFirefoxBrowser() {
				try {
					System.setProperty("webdriver.gecko.driver", "src/test/resources/geckodriver.exe");
					driver = new FirefoxDriver();
					driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
					driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
					driver.manage().window().maximize();
				} catch (Exception e) {
					logger.error("Error- ", e);
					assertTrue(false);
				}

				return driver;
			}
			
			// Starting Internet Explorer Browser
			@SuppressWarnings("deprecation")
			public WebDriver startInternetExplorerBrowser() {
				try {
					DesiredCapabilities cap = DesiredCapabilities.internetExplorer();
					cap.setCapability(InternetExplorerDriver.NATIVE_EVENTS, false);
					cap.setCapability(InternetExplorerDriver.ENABLE_PERSISTENT_HOVERING, false);
					cap.setCapability(InternetExplorerDriver.REQUIRE_WINDOW_FOCUS, false);
					cap.setCapability(InternetExplorerDriver.IE_ENSURE_CLEAN_SESSION, true);
					cap.setCapability(InternetExplorerDriver.IGNORE_ZOOM_SETTING, true);
					cap.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
					System.setProperty("webdriver.ie.driver",
							"C:\\Users\\joseph.quansah\\eclipse-workspace\\contineousIntegration\\src\\test\\resources\\IEDriverServer.exe");
					driver = new InternetExplorerDriver(cap);
					driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
					driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
					driver.manage().window().maximize();
				} catch (Exception e) {
					logger.error("Error- ", e);
					assertTrue(false);
				}
				return driver;
			}
			
			// Screenshot Capture
			public String captureScreenshot (String screenshotFileName, String filePath) throws IOException
			{
				String screenCaptureFile = null;
				String tempTime = df.format(dateobj);//getCurrentTime();
				if(! filePath.isEmpty())
				{
					screenCaptureFile = filePath + screenshotFileName + "_" + tempTime + ".png";
				}else {
					screenCaptureFile = "C:\\Users\\joseph.quansah\\Documents\\TestFailScreenshots" + screenshotFileName + "_" + tempTime + ".png";
				}
				 File scrFile = ((TakesScreenshot)driver). getScreenshotAs(OutputType.FILE);
				Files.copy(scrFile, new File(screenCaptureFile));
				System.out.println("Screenshot Path: " + screenCaptureFile);
				return screenCaptureFile;
				
				}
			public WebDriver startLocalBrowser() {

				String browser = "";

				if (browser.equalsIgnoreCase("IE")) {
					driver = startInternetExplorerBrowser();
					System.out.println("Starting 'IE' browser !");
					
				} else if (browser.equalsIgnoreCase("Firefox")) {
					driver = startFirefoxBrowser();
					System.out.println("Starting 'Firefox'browser !");

				} else if (browser.equalsIgnoreCase("Chrome")) {
					driver = startChromeBrowser();
					System.out.println("Starting 'Chrome' browser !");

				} else {
					driver = startInternetExplorerBrowser();
					System.out.println("Starting Default Browser... IE!");
				}

				return driver;
			}
			
			public WebElement fluentWait(By by) {
				WebElement targetElem = null;
				@SuppressWarnings("deprecation")
				Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(60, TimeUnit.SECONDS)
						.pollingEvery(3, TimeUnit.SECONDS).ignoring(NoSuchElementException.class);
				targetElem = wait.until(new Function<WebDriver, WebElement>() {
					public WebElement apply(WebDriver driver) {
						return driver.findElement(by);
					}
				});
				return targetElem;
				
			}
			
			public WebElement dynamicWait_presenceOfElementLocated(By by) {
				WebElement myDynamicElement = (new WebDriverWait(driver, 60))
						.until(ExpectedConditions.presenceOfElementLocated(by));
				return myDynamicElement;
			}
			
			public void customWait(double inSeconds) throws Exception {
				Thread.sleep((long) (inSeconds * 1000));
			}
			
			public void highlightElement(WebElement element) throws Exception {

				//if (isDemoMode == true) {
					for (int i = 0; i < 4; i++) {
						WrapsDriver wrappedElement = (WrapsDriver) element;
						JavascriptExecutor js = (JavascriptExecutor) wrappedElement.getWrappedDriver();
						customWait(0.5);
						js.executeScript("arguments[0].setAttribute('style', arguments[1]);", element,
								"color: red; border: 2px solid yellow;");
						customWait(0.5);
						js.executeScript("arguments[0].setAttribute('style', arguments[1]);", element, "");
					}
				}

			public void highlightElement(By by) throws Exception {

				WebElement element = driver.findElement(by);
				//if (isDemoMode == true) {
					for (int i = 0; i < 5; i++) {
						WrapsDriver wrappedElement = (WrapsDriver) element;
						JavascriptExecutor js = (JavascriptExecutor) wrappedElement.getWrappedDriver();
						customWait(0.5);
						js.executeScript("arguments[0].setAttribute('style', arguments[1]);", element,
								"color: red; border: 2px solid yellow;");
						customWait(0.5);
						js.executeScript("arguments[0].setAttribute('style', arguments[1]);", element, "");
					}
				}
			public WebDriver createDriverGrid(String browser) {
				String hubUrl = "http://192.168.56.1:4444/wd/hub";
				DesiredCapabilities capabilities = new DesiredCapabilities();
				System.out.println("Starting " + browser + "on grid");
				
				// Creating driver
				 switch (browser) {
				 case "chrome":
					 capabilities.setBrowserName(DesiredCapabilities.chrome().getBrowserName());
					 break;
				 case "firefox":
					 capabilities.setBrowserName(DesiredCapabilities.firefox().getBrowserName());
					 break;
				 }
				 
				 try {
					 driver.get((hubUrl));
				 }catch(Exception e) {
					 e.printStackTrace();
				 }
				
				return driver;
			}


}
