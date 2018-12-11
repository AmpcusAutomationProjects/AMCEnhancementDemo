package com.demoTestScripts;

import org.openqa.selenium.By;
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
	demoLib.fluentWait(By.id("T:j_id__ctru15pc14")).click(); // Click on Login

	demoLib.fluentWait(By.id("username")).sendKeys(username); // Enter UserName
	Thread.sleep(4000);
	demoLib.fluentWait(By.id("password")).sendKeys(password); // Password
	Thread.sleep(4000);
	demoLib.fluentWait(By.cssSelector(".submit")).click(); // Click on Login
	Thread.sleep(10000);
	
//	demoLib.fluentWait(By.cssSelector("#T\\:toolslink > div:nth-child(2)")).click(); // Click on Tools
//	Thread.sleep(4000);
//	
//	driver.findElement(By.id("T:ti1:0:ti2:0:ti3:3:linkGenWrn")).click(); //click on amc
//	Thread.sleep(4000);
//	
//	driver.findElement(By.id("T:gWYes")).click(); //click Yes to continue
//	Thread.sleep(7000);
//	  
//	driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div/div/div[2]/div/div/button[4]")).click();//click user management tab
//	Thread.sleep(5000);
//	
//	driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div/div/div/div[4]/div/table/tbody/tr[5]/td[2]/a")).click();//click on user
//	Thread.sleep(9000);
//	
//	driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div/div/div/div[3]/div/div[2]/div/div[1]/a")).click();//highlight and click on disable user
//	Thread.sleep(9000);
//	
//	driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div/div/div/div[3]/div/div[2]/div/div[2]/div/div[1]/form/fieldset/table/tbody/tr[1]/td[5]/input")).click();//click check box
//	Thread.sleep(3000);
//	
//	driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div/div/div/div[3]/div/div[2]/div/div[2]/div/div[1]/form/fieldset/div[3]/button[3]")).click();//click remove role
//	Thread.sleep(6000);
//	
//	driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div/div/div/div[3]/div/div[9]/div/div/div[3]/button[2]")).click();//click confirm
//	Thread.sleep(10000);
//	
//	WebElement revoked = driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div/div/div/div[3]/div/div[2]/div/div[2]/div/div[1]/form/fieldset/table/tbody/tr[1]/td[4]"));
//	demoLib.highlightElement(revoked);
//	Thread.sleep(4000);
//	
//	WebElement reRequest = driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div/div/div/div[3]/div/div[2]/div/div[2]/div/div[1]/form/fieldset/div[3]/button[2]"));//
//	Thread.sleep(3000);
//	
//	if(reRequest.isEnabled()) {
//		System.out.println("Re-Request is Enabled");
//	}else {
//		System.out.println("Re-Request is disabled");
//	}
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
