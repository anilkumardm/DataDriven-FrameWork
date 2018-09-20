package com.anil.test;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.anil.base.BaseWebdriver;
import com.anil.utilities.TestUtils;

public class SimpleFormDemoTest extends BaseWebdriver {
	
	@Test(dataProviderClass=TestUtils.class, dataProvider="dp")
    public void inputFieldTest(String message, String valueA,String valueB) throws InterruptedException
    {	
		
		// committing with git changes.
		// commit 2
		log.debug("Inside Input Field Test");
		log.debug("Text filed"+message);
		//driver.findElement(By.xpath(OR.getProperty("Homelink"))).click();
		
		WebElement demolink=driver.findElement(By.xpath(OR.getProperty("SimpleDemoLink_XPATH")));
	     wait.until(ExpectedConditions.visibilityOf(demolink));
	    demolink.click();
		log.debug("clicked sample link");	
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,1000)");
		Thread.sleep(5000);
		type("userMassage_XPATH",message);
		log.debug(message);
		type("InputValueA_XPATH",valueA);
	//	driver.findElement(By.xpath(OR.getProperty("InputValueA"))).sendKeys(valueA);
		log.debug(valueA);
		type("InputValueB_XPATH",valueB);
		log.debug(valueB);
		click("getTotalBtn_XPATH");
		//driver.findElement(By.xpath(OR.getProperty("getTotalBtn"))).click();
		Thread.sleep(5000);
		//Assert.fail("Input text not working successfully");
		
    }
	
	@Test
	public void verifySum()
	{
		String totalSum=driver.findElement(By.xpath(OR.getProperty("TotalSum_XPATH"))).getText();
		log.debug("Total sum "+totalSum);
		Reporter.log("Verified sum of two number->"+totalSum);
	}
	
	

	
}
