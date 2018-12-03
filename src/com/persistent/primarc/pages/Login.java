package com.persistent.primarc.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.persistent.primarc.util.Keywords;
import com.relevantcodes.extentreports.LogStatus;

public class Login extends Keywords {
	
//---------------------Elements----------------------------//	

	@FindBy(id="login-form-login")
	public static WebElement unameTextBox;
	
	@FindBy(id="login-form-password")
	public static WebElement pwdTextBox;
	
	@FindBy(xpath=".//*[@id='login-form']/button")
	public static WebElement SigninBtn;
	
	
//---------------------Methods-------------------------------//
	
	public static boolean loginUser(String uname, String pwd){
		try{
			write(unameTextBox,uname);
			write(pwdTextBox,pwd);
			click(SigninBtn);
			Thread.sleep(20000);
	return verifyPageTitle("Dashboard");
	    }catch(Throwable t){
			System.out.println(t.getMessage());
			 return false;
			
		}
		
		
	}
	
	
}
