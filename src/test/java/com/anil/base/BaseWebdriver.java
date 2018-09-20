package com.anil.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import com.anil.utilities.ExcelReader;
import com.anil.utilities.ExtentManager;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

/*
 * Initialise web driver --Done
 * Properties file - done
 * Logs-- need Log4j property file, Logger class, 
 *  Excel file
 * Database
 * extent report
 */

public class BaseWebdriver {

	public static WebDriver driver;
	public static Properties config = new Properties();
	public static Properties OR = new Properties();
	public static FileInputStream fis;
	public static Logger log=Logger.getLogger("devpinoyLogger");
	public static String currentDirectory = System.getProperty("user.dir");
	public static String browser;
	public static String testUrl;
	public static WebDriverWait wait;
	public static ExcelReader excel=new ExcelReader(currentDirectory +"\\src\\test\\resources\\excels\\TestData.xlsx");
    public ExtentReports rep=ExtentManager.getInstance();
    public static ExtentTest test;
	@BeforeSuite
	public void setUp() throws IOException {
		fis = new FileInputStream(currentDirectory + "\\src\\test\\resources\\properties\\config.properties");
		config.load(fis);
		log.debug("Config file loaded ");
		fis=new FileInputStream(currentDirectory + "\\src\\test\\resources\\properties\\OR.properties");
		OR.load(fis);
		log.debug("OR file loaded ");
		if(System.getenv("Browser")!=null&& !System.getenv("Browser").isEmpty())
		{
			browser=System.getenv("Browser");
		}
		else
		{
			browser = config.getProperty("Browser");
		}
		config.setProperty("Browser", browser);
		testUrl = config.getProperty("TestUrl");
		if (driver == null) {
			if (browser.equals("chroma")) {
				System.setProperty("webdriver.chrome.driver",
						currentDirectory + "\\src\\test\\resources\\executables\\chromedriver.exe");
				driver = new ChromeDriver();
				log.debug("Chroma Launched");

			} else if (browser.equals("firefox")) {
				System.setProperty("webdriver.gecko.driver",
						currentDirectory + "\\src\\test\\resources\\executables\\geckodriver.exe");
				driver = new FirefoxDriver();
				log.debug("firfox Launched");

			} else if (browser.equals("ie")) {
				System.setProperty("webdriver.ie.driver",
						currentDirectory + "\\src\\test\\resources\\executables\\IEDriverServer.exe");
				driver = new InternetExplorerDriver();
				log.debug("Ie Launched");
			}
		}
		driver.get(testUrl);
		log.debug("Application Launched->"+testUrl);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Integer.parseInt(config.getProperty("implicit.wait")),
				TimeUnit.SECONDS);
		wait=new WebDriverWait(driver, 5);

	}

	@AfterSuite
	public void tearDown() {
		if(driver!=null)
		{
		driver.quit();
		}
	}
	
	public void click(String locator)
	{
		if(locator.endsWith("_XPATH"))
				{
			driver.findElement(By.xpath(OR.getProperty(locator))).click();
			test.log(LogStatus.INFO,"clicking on "+locator);
				}
		else if(locator.endsWith("_CSS"))
		{
			driver.findElement(By.cssSelector(OR.getProperty(locator))).click();
			test.log(LogStatus.INFO,"clicking on "+locator);
		}
		else if(locator.endsWith("_ID"))
		{
			driver.findElement(By.id(OR.getProperty(locator))).click();
			test.log(LogStatus.INFO,"clicking on "+locator);
		}
		
		else if(locator.endsWith("_NAME"))
		{
			driver.findElement(By.name(OR.getProperty(locator))).click();
			test.log(LogStatus.INFO,"clicking on "+locator);
		}
		
	}
	public void type(String locator, String value)
	{
				
		if(locator.endsWith("_XPATH"))
		{
	driver.findElement(By.xpath(OR.getProperty(locator))).sendKeys(value);
	test.log(LogStatus.INFO,"Entered Value:"+value+" in "+locator);
		}
else if(locator.endsWith("_CSS"))
{
	driver.findElement(By.cssSelector(OR.getProperty(locator))).sendKeys(value);
	test.log(LogStatus.INFO,"Entered Value:"+value+" in "+locator);
}
else if(locator.endsWith("_ID"))
{
	driver.findElement(By.id(OR.getProperty(locator))).sendKeys(value);
	test.log(LogStatus.INFO,"Entered Value:"+value+" in "+locator);
}

else if(locator.endsWith("_NAME"))
{
	driver.findElement(By.name(OR.getProperty(locator))).sendKeys(value);
	test.log(LogStatus.INFO,"Entered Value:"+value+" in "+locator);
}
	}
	
	
	
	public boolean isElementPresent(By by)
	{
		try{
			
		driver.findElement(by);
		return true ;

		}
		catch(NoSuchElementException e)
		{
			return false;
			
		}
	}

}
