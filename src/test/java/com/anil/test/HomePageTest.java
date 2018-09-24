package com.anil.test;

import org.openqa.selenium.By;
import org.testng.Reporter;
import org.testng.annotations.Test;

import com.anil.base.BaseWebdriver;



public class HomePageTest extends BaseWebdriver {

	@Test
	//Add comment
	public void clickStartLink() {
		log.debug("Inside start Link");
		try {
		click("Homelink_XPATH");
		log.debug("start Link clicked successfully");
		Thread.sleep(5000);
		Reporter.log("Start link clicked successfully");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		//Reporter.log("a href=\"H:\\Training WorkSpace\\DataDrivenFramework1\\src\\test\\resources\\screenshot\\a.jpg\">Screenshot</a>");
		}
		
	    log.debug("Displayed Header Text->"+driver.findElement(By.xpath(OR.getProperty("headerText_XPATH"))).getText());
 
	
	}
	
	

}
