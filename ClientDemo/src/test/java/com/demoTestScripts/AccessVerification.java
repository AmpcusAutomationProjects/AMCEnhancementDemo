package com.demoTestScripts;

import org.openqa.selenium.By;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.environmentalVariables.ExcelRead;
import com.environmentalVariables.UtilityClass;

public class AccessVerification extends UtilityClass{
	
	@Test(dataProvider = "wordpressData")
	public void accessVerificationTest(String username, String password) throws Exception {
		
		driver.get("https://myuat.ginniemae.gov");
		
		Thread.sleep(4000);
		demoLib.fluentWait(By.id("T:j_id__ctru15pc14")).click(); // Click on Login

		demoLib.fluentWait(By.id("username")).sendKeys(username); // Enter UserName
		Thread.sleep(4000);
		demoLib.fluentWait(By.id("password")).sendKeys(password); // Password
		Thread.sleep(4000);
		demoLib.fluentWait(By.cssSelector(".submit")).click(); // Click on Login
		Thread.sleep(10000);
		 
		
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
