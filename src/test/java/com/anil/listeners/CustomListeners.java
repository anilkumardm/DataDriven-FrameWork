package com.anil.listeners;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.SkipException;

import com.anil.base.BaseWebdriver;
import com.anil.utilities.MonitoringMail;
import com.anil.utilities.TestConfig;
import com.anil.utilities.TestUtils;
import com.relevantcodes.extentreports.LogStatus;



public class CustomListeners extends BaseWebdriver implements ITestListener,ISuiteListener{
	String messageBody;
	public void onFinish(ITestContext arg0) {
		// TODO Auto-generated method stub
		
	}

	public void onStart(ITestContext arg0) {
		// TODO Auto-generated method stub
		
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult arg0) {
		// TODO Auto-generated method stub
		
	}

	public void onTestFailure(ITestResult arg0) {
		// TODO Auto-generated method stub
		System.setProperty("org.uncommons.reportng.escape-output", "false");
		try {
			TestUtils.captureScreenshot();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		test.log(LogStatus.FAIL,arg0.getName().toUpperCase()+"Failed with exception "+arg0.getThrowable());
		test.log(LogStatus.FAIL,test.addScreenCapture(TestUtils.screenshotName));
			
		Reporter.log("click to continue screen shot");
		Reporter.log("<a target=\"_blank\" href="+TestUtils.screenshotName+">Screenshot</a>");
		Reporter.log("</br>");
		Reporter.log("<a target=\"_blank\" href="+TestUtils.screenshotName+"><img src="+TestUtils.screenshotName+" width=200 height=200 </a>");	
		rep.endTest(test);
		rep.flush();

	}

	public void onTestSkipped(ITestResult arg0) {
		// TODO Auto-generated method stub
		test.log(LogStatus.SKIP, "test case skipped as runmode No ");
		rep.endTest(test);
		rep.flush();
	}

	public void onTestStart(ITestResult arg0) {
		// TODO Auto-generated method stub
		test=rep.startTest(arg0.getName().toUpperCase());
		if(!TestUtils.isTestRunnable(arg0.getName(), excel))
		{
			throw new SkipException("Test case skipped due to runnable mode NO");
		}
	}

	public void onTestSuccess(ITestResult arg0) {
		test.log(LogStatus.PASS, arg0.getName().toUpperCase()+"PASS");
	    rep.endTest(test);
	    rep.flush();
		
	}

	public void onFinish(ISuite arg0) {
		// TODO Auto-generated method stub
		MonitoringMail mail=new MonitoringMail();
		
		try {
			messageBody = "http://"+InetAddress.getLocalHost().getHostAddress()+":8080/job/DataDrivenFramework/Extent_20Reports/";
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
  //System.out.println(hostname);
  
    try {
		mail.sendMail(TestConfig.server, TestConfig.from, TestConfig.to, TestConfig.subject, messageBody);
	} catch (AddressException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (MessagingException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		
	}

	public void onStart(ISuite arg0) {
		// TODO Auto-generated method stub
		
	}

}
