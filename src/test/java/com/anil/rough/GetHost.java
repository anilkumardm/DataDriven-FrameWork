package com.anil.rough;

import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import com.anil.utilities.MonitoringMail;
import com.anil.utilities.TestConfig;

public class GetHost {

	public static void main(String[] args) throws UnknownHostException, AddressException, MessagingException {
		// TODO Auto-generated method stub
		MonitoringMail mail=new MonitoringMail();
		String messageBody="http://"+InetAddress.getLocalHost().getHostAddress()+":8080/job/DataDrivenFramework/Extent_20Reports/";
  //System.out.println(hostname);
  
   //  mail.sendMail(TestConfig.server, TestConfig.from, TestConfig.to, TestConfig.subject, messageBody);
	}

}
