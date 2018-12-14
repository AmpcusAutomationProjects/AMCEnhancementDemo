package com.demoTestScripts;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.environmentalVariables.ExcelRead;
import com.environmentalVariables.UtilityClass;

public class DisableAndEnableUsers extends UtilityClass {

	@Test(dataProvider = "wordpressData")
	public void disable_And_Enable_User(String username, String password) throws Exception {

		driver.get("https://myuat.ginniemae.gov");
		Thread.sleep(4000);

		driver.findElement(By.id("T:j_id__ctru15pc14")).click(); // Click on Login
		Thread.sleep(4000);

		driver.findElement(By.id("username")).sendKeys(username); // Enter UserName
		Thread.sleep(2000);

		driver.findElement(By.id("password")).sendKeys(password); // Password
		Thread.sleep(2000);

		demoLib.fluentWait(By.xpath("/html/body/div/div[3]/div/div[2]/form/fieldset/input[6]")).click(); // Click Login
																											// btn
		Thread.sleep(20000);

		demoLib.fluentWait(By.cssSelector("#T\\:toolslink > div:nth-child(2)")).click(); // Click on Tools
		Thread.sleep(2000);

		driver.findElement(By.id("T:ti1:0:ti2:0:ti3:3:linkGenWrn")).click(); // click on amc
		Thread.sleep(2000);

		demoLib.fluentWait(By.id("T:gWYes")).click(); // click Yes to continue
		Thread.sleep(60000);

		demoLib.fluentWait(By.cssSelector("button.btn:nth-child(4)")).click();// click user management tab
		Thread.sleep(15000);

		driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div/div/div/div[4]/div/table/tbody/tr[9]/td[2]/a"))
				.click();// click on user Harris Wood
		Thread.sleep(20000);

		JavascriptExecutor je = (JavascriptExecutor) driver;

		WebElement disableElem = driver.findElement(By.xpath(
				"/html/body/div[2]/div/div/div/div/div/div/div[3]/div/div[1]/div/div[2]/div/form/div/button[4]")); // Click
																													// the
																													// Disable
																													// button
		je.executeScript("arguments[0].scrollIntoView(true);", disableElem);
		disableElem.click();
		Thread.sleep(9000);

		WebElement confirmBtn1 = driver.findElement(By.cssSelector(
				"#modalex-disable > div:nth-child(1) > div:nth-child(1) > div:nth-child(3) > button:nth-child(2)")); // Highlight
																														// confirm
																														// btn
																														// and
																														// click
		demoLib.highlightElement(confirmBtn1);
		confirmBtn1.click();
		Thread.sleep(20000);

		driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div/div/div/div[2]/div[2]/div/button[2]")).click(); // Click
																														// the
																														// Back
																														// btn
		Thread.sleep(9000);

		// Org. Admin Logs out
		driver.findElement(By.cssSelector("#link-menu > span:nth-child(2)")).click();// click on links
		Thread.sleep(2000);

		driver.findElement(By.cssSelector(
				"li.dropdown:nth-child(2) > ul:nth-child(2) > li:nth-child(1) > a:nth-child(1) > span:nth-child(2)"))
				.click();// click on MyGinnieMae Portal
		Thread.sleep(3000);

		driver.findElement(By.cssSelector(".fa-icon-lock")).click();// Click on the PadLock
		demoLib.customWait(3);

		driver.findElement(By.cssSelector(".lock")).click();// Click on the Logout button
		demoLib.customWait(5);
		

	 
	}

	@DataProvider(name = "wordpressData")
	public Object[][] passData()

	{
		ExcelRead merl = new ExcelRead(
				"");

		int rows = merl.getRollCount(0);

		Object[][] data = new Object[rows][2];

		for (int i = 0; i < rows; i++) {
			data[i][0] = merl.getData(0, i, 0);
			data[i][1] = merl.getData(0, i, 1);
		}

		return data;

	}
}
