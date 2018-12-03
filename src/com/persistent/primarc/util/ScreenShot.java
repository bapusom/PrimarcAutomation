package com.persistent.primarc.util;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class ScreenShot extends TestBase{
	
	
	public static String captureScrenshot(WebDriver driver,String screenshotname){
		
		try{
		TakesScreenshot ts =(TakesScreenshot)driver;
		
		File source =ts.getScreenshotAs(OutputType.FILE);
		String dest = System.getProperty("user.dir")+"\\ScrenShots\\"+screenshotname+".png";
		File destination = new File(dest);
		FileUtils.copyFile(source, destination);
		System.out.println("ScreenShot Taken");
		return dest;
			
		}
		catch(Exception e)
		
		{
		
		 System.out.println("Exception message "+ e.getMessage());
		 return e.getMessage();
		 
		}
	}
			
		
	

}
