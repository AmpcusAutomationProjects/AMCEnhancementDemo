package com.craigListJob;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class ThirdOption {
	
	public static WebDriver driver;
	
	
	
	 
	
	@BeforeTest
	public WebDriver chromeDriver() throws Exception {
		System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(70, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
		driver.get("https://washingtondc.craigslist.org/nva/");
		driver.findElement(By.xpath("/html/body/div/section/div[3]/div[1]/div[2]/div/ul[1]/li[1]/a/span")).click(); // Click the automotive link
		Thread.sleep(3000);
		driver.findElement(By.className("rows"));
		return driver;
	}

		
	@Test
	public static void clickAllHyperLinksByTagName() throws Exception {
	
		int numberOfElementsFound = getNumberOfElementsFound(By.className("result-row"));
	    System.out.println(numberOfElementsFound);
	    for (int pos = 0; pos < numberOfElementsFound; pos++) {
	        getElementWithIndex(By.className("result-row"), pos).click();
	        
	        WebElement replyBtn = driver.findElement(By.xpath("/html/body/section/section/header/div[2]/button"));
	        if(replyBtn.isDisplayed()) {
	        	replyBtn.click();
	        }else {
	        	System.out.println("Reply btn is not available");
	        }
	        
			Thread.sleep(5000);

			WebElement number = driver.findElement(By.className("reply-tel-number"));
			if(number.isDisplayed()) {
				System.out.println(number.getAttribute("href"));
			}else {
				System.out.println("There is no number");
			}
			
	        
	        driver.navigate().back();
	    }
	}
	
	public static int getNumberOfElementsFound(By by) {
		return driver.findElements(by).size();
		
	}
	
	public static WebElement getElementWithIndex(By by, int pos) {
	    return driver.findElements(by).get(pos);
	}
	
	@AfterTest
	public void afterTestComplete() {
		driver.close();
	}

}
