package com.demoTestScripts;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.environmentalVariables.ExcelRead;
import com.environmentalVariables.UtilityClass;

public class AccessVerification extends UtilityClass {

	@Test(dataProvider = "wordpressData")
	public void accessVerificationTest(String username, String password) throws Exception {

		driver.get("https://myuat.ginniemae.gov"); // GinnieMae Portal
		Thread.sleep(1000);
		demoLib.fluentWait(By.id("T:j_id__ctru15pc14")).click(); // Click on Login

		demoLib.fluentWait(By.id("username")).sendKeys(username); // Enter UserName
		demoLib.fluentWait(By.id("password")).sendKeys(password); // Password
		demoLib.fluentWait(By.cssSelector(".submit")).click(); // Click on Login
		demoLib.fluentWait(By.xpath("/html/body/div[1]/div/form/div[2]/div[1]/div[1]/div/div/div[2]/a/div[2]")).click(); // Click
																															// on
																															// Tools
		Thread.sleep(4000);

		try {
			WebElement element = driver.findElement(By.id("T:ti1:0:ti2:0:ti3:3:linkGenWrn"));

			if (element.isDisplayed() && element.isEnabled()) {
				element.click();
			}
		} catch (Exception e) {
			System.out.println("element not found");
		}

		demoLib.fluentWait(By.cssSelector("#T\\:toolslink > div:nth-child(2)")).click(); // Click on Tools
		Thread.sleep(4000);

		demoLib.fluentWait(By.xpath("/html/body/div[1]/div/form/div[2]/div[1]/div[2]/div[2]/div[6]/div/a")).click();
		Thread.sleep(2000);
		demoLib.fluentWait(
				By.xpath("/html/body/div[1]/div/form/div[2]/div[1]/div[2]/div[2]/div[6]/div/div/div[2]/input[2]"))
				.click();

	}

	@DataProvider(name = "wordpressData")
	public Object[][] passData()

	{
		ExcelRead merl = new ExcelRead("");

		int rows = merl.getRollCount(0);

		Object[][] data = new Object[rows][2];

		for (int i = 0; i < rows; i++) {
			data[i][0] = merl.getData(0, i, 0);
			data[i][1] = merl.getData(0, i, 1);
		}

		return data;

	}

}
