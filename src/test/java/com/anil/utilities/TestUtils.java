package com.anil.utilities;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Date;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.annotations.DataProvider;
import org.apache.commons.io.FileUtils;

import com.anil.base.BaseWebdriver;

public class TestUtils extends BaseWebdriver{
	public static String screenshotPath;
	public static String screenshotName;

	public static void captureScreenshot() throws IOException {

		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

		Date d = new Date();
		screenshotName = d.toString().replace(":", "_").replace(" ", "_") + ".jpg";
   
		FileUtils.copyFile(scrFile,
				new File(System.getProperty("user.dir") + "\\target\\surefire-reports\\html\\" + screenshotName));

	}
	@DataProvider(name="dp")
	public Object[][] getData(Method m	)
		{
		log.debug("Inside Data provider");
		String sheetName=m.getName();
		int rows=excel.getRowCount(sheetName);
		int cols=excel.getColumnCount(sheetName);
		log.debug("Rowcount-->"+rows);
		log.debug("column count"+cols);
		
		Object[][] data=new Object[rows-1][cols];
		for(int rowNum=2;rowNum<=rows;rowNum++)
		{
			for (int colsCount=0;colsCount<cols;colsCount++)
			{
				data[rowNum-2][colsCount]=excel.getCellData(sheetName,colsCount,rowNum);
		      
			}
		}
		return data;

	}
	
	public static Boolean isTestRunnable(String testName, ExcelReader reader)
	{
		String sheetName="Test_Suite";
		int rows=reader.getRowCount(sheetName);
		for (int rwNum=2;rwNum<=rows;rwNum++)
		{
		String testCase=reader.getCellData(sheetName, "TCID", rwNum);
		if(testName.equalsIgnoreCase(testCase))
		{
			String runMode=reader.getCellData(sheetName, "Runmode", rwNum);
			if(runMode.equalsIgnoreCase("Y"))
			{
				return true;
			}
			else
			{
				return false;
			}
		}
		
	}
		return false;
	}


}
