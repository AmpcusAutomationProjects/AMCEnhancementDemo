package com.craigListJob;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import com.environmentalVariables.UtilityClass;

public class CraigListVA extends UtilityClass {

	@Test(enabled = true)
	public void getVAData() throws Exception {
		String url = "https://washingtondc.craigslist.org/nva/";
		driver.get(url);
		driver.findElement(By.xpath("/html/body/div/section/div[3]/div[1]/div[2]/div/ul[1]/li[1]/a/span")).click();
		Thread.sleep(3000);

		WebElement userLink = driver.findElement(By.xpath("/html/body/section/form/div[4]/ul/li[1]/p/a"));
		userLink.click(); // Click user link
		Thread.sleep(3000);

		driver.findElement(By.xpath("/html/body/section/section/header/div[2]/button")).click(); // Click reply button
		Thread.sleep(5000);

		WebElement contactName = driver.findElement(By.xpath("/html/body/section/section/header/div[2]/div/aside/ul/li[1]/p")); ///html/body/section/section/header/div[2]/div/aside/ul/li[1]/p
		if(contactName.isDisplayed()) { 
			
			System.out.println("Contact Name: "+contactName.getText());
			
		}else {
			
			System.out.println("There is no contact name");
		}
		
		
		WebElement number = driver.findElement(By.xpath("/html/body/section/section/header/div[2]/div/aside/ul/li[2]/h1/a[1]")); 
		
		if(number.isDisplayed()) {
			
			System.out.println(number.getAttribute("href"));
			
		}else {
			
			System.out.println("There is no number");
		}
		

		

	}

	@Test(enabled = false)
	public void loopThroughData() throws Exception {
		String url = "https://washingtondc.craigslist.org/nva/";
		driver.get(url);
		driver.findElement(By.xpath("/html/body/div/section/div[3]/div[1]/div[2]/div/ul[1]/li[1]/a/span")).click(); // Click the automotive link
		Thread.sleep(3000);

		WebElement table = driver.findElement(By.className("rows"));
		
		List<WebElement> links = table.findElements(By.className("result-row"));
		 
		System.out.println("Users " + links.size());
		
		for (int i = 0; i <= links.size(); i++) {
			
			System.out.println(links.get(i).getAttribute("data-pid"));
		}
		
		driver.findElement(By.className("phone")).click(); // Click reply button																							
		Thread.sleep(5000);

		WebElement number = driver.findElement(By.className("reply-tel-number"));
		System.out.println(number.getAttribute("href"));
	}

}
