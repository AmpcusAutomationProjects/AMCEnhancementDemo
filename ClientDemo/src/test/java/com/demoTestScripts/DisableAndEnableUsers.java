package com.demoTestScripts;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.environmentalVariables.ExcelRead;
import com.environmentalVariables.UtilityClass;

public class DisableAndEnableUsers extends UtilityClass{
	
	@Test(dataProvider = "wordpressData")
	public void disable_And_Enable_User(String username, String password) throws Exception {
	
	driver.get("https://myuat.ginniemae.gov");
	Thread.sleep(4000);
	 
	driver.findElement(By.id("T:j_id__ctru15pc14")).click(); // Click on Login
	Thread.sleep(4000);

	 
	driver.findElement(By.id("username")).sendKeys(username); // Enter UserName
	Thread.sleep(4000);
	 
	driver.findElement(By.id("password")).sendKeys(password); // Password
	Thread.sleep(4000); 
	
	driver.findElement(By.xpath("/html/body/div/div[3]/div/div[2]/form/fieldset/input[6]")).click(); //Click Login
	Thread.sleep(10000);
	
	demoLib.fluentWait(By.cssSelector("#T\\:toolslink > div:nth-child(2)")).click(); // Click on Tools
	Thread.sleep(4000);
	
	driver.findElement(By.id("T:ti1:0:ti2:0:ti3:3:linkGenWrn")).click(); //click on amc
	Thread.sleep(4000);
	
	driver.findElement(By.id("T:gWYes")).click(); //click Yes to continue
	Thread.sleep(10000);
	  
	driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div/div/div[2]/div/div/button[4]")).click();//click user management tab
	Thread.sleep(9000);
	
	driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div/div/div/div[4]/div/table/tbody/tr[9]/td[2]/a")).click();//click on user Harris Wood
	Thread.sleep(20000);
	
	((JavascriptExecutor)driver).executeScript("scroll(0,200)"); // Scroll down
	Thread.sleep(3000);
	
	driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div/div/div/div[3]/div/div[1]/div/div[2]/div/form/div/button[4]")).click(); // Click the Disable button
	Thread.sleep(9000);
	
	WebElement confirmBtn1 = driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div/div/div/div[3]/div/div[6]/div/div/div[3]/button[2]")); // Highlight confirm btn and click
	demoLib.highlightElement(confirmBtn1);
	confirmBtn1.click();
	Thread.sleep(20000);
	
	driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div/div/div/div[2]/div[2]/div/button[2]")).click(); //Click the Back btn
	Thread.sleep(9000);
	
	driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div/div/div/div[4]/div/table/tbody/tr[9]/td[2]/a")).click(); // Click on Harris Wood to Enable User
	Thread.sleep(9000);
	
	((JavascriptExecutor)driver).executeScript("scroll(0,500)"); // Scroll down
	Thread.sleep(3000);
	
	driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div/div/div/div[3]/div/div[1]/div/div[2]/div/form/div/button[3]")).click(); // Click on Enable
	Thread.sleep(9000);
	
	WebElement confirmBtn2 = driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div/div/div/div[3]/div/div[7]/div/div/div[3]/button[2]")); // Highlight confirm btn and click
	demoLib.highlightElement(confirmBtn2);
	confirmBtn2.click();
	Thread.sleep(20000);
	
	driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div/div/div/div[2]/div[2]/div/button[2]")).click(); //Click the Back btn
	Thread.sleep(9000);
	}
	
	
	@DataProvider(name = "wordpressData")
	public Object[][] passData()

	{
		ExcelRead merl = new ExcelRead("C:\\Users\\joseph.quansah\\git\\AMCEnhancementDemoRepo\\ClientDemo\\DataProvider\\data.xlsx");

		int rows = merl.getRollCount(0);

		Object[][] data = new Object[rows][2];

		for (int i = 0; i < rows; i++) {
			data[i][0] = merl.getData(0, i, 0);
			data[i][1] = merl.getData(0, i, 1);
		}

		return data;

	}
}
