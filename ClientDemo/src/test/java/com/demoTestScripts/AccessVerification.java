package com.demoTestScripts;

import org.openqa.selenium.By;

import com.environmentalVariables.UtilityClass;

public class AccessVerification extends UtilityClass{
	
	public void accessVerificationTest() {
		
		driver.get("https://myuat.ginniemae.gov");
		driver.findElement(By.id(""));
		driver.findElement(By.xpath(""));
		
	}

}
