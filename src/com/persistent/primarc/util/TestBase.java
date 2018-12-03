package com.persistent.primarc.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

public class TestBase {
	
	public static WebDriver driver=null;
	public static Properties CONFIG=null;
	public static ExtentReports report=null;;
	public static ExtentTest logger=null;;
	
	
	public static void init() throws IOException {
		
		if(CONFIG==null){
			CONFIG= new Properties();
			
			FileInputStream ip =new FileInputStream(new File(System.getProperty("user.dir")+"\\src\\com\\persistent\\primarc\\config\\test.properties"));
			CONFIG.load(ip);
		}
	
	}
	
	public  static void initReport(){
		report = new ExtentReports(System.getProperty("user.dir")+"\\Reports\\report.html");
		
	}
	
	public static void main(String[] args){
		
	}
	
	

}
