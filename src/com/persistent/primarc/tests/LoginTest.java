package com.persistent.primarc.tests;

import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.persistent.primarc.pages.Login;
import com.relevantcodes.extentreports.LogStatus;

public class LoginTest extends Login {
	
	@Test(description="verify the login functionality of normal user")
	
	public void verifyLogin(){
		
		logger=report.startTest("verify login");
		
		Login l=PageFactory.initElements(driver, Login.class);
		
		String user_name=CONFIG.getProperty("userName");
		String pass_word=CONFIG.getProperty("passWord");
		boolean b=Login.loginUser(user_name, pass_word);
		Assert.assertTrue(b, "Login is not successful");
		logger.log(LogStatus.PASS, "Login verified sucessfully");
		
		
	}

}
