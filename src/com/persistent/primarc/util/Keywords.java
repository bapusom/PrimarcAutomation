package com.persistent.primarc.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.Select;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.relevantcodes.extentreports.LogStatus;

public class Keywords extends TestBase {
	
	@BeforeSuite
	public static void setUP() throws IOException {
		init();
		initReport();
		openBrowser();
        navigate("TestUrl");
		
		
	}
	
	public static void openBrowser(){
		
		if(driver==null){
			if(CONFIG.getProperty("browser").equalsIgnoreCase("Chrome")){
				System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"\\chromedriver.exe");
				DesiredCapabilities caps=new DesiredCapabilities();
				caps.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
				driver=new ChromeDriver(caps);
				
			}else if(CONFIG.getProperty("browser").equalsIgnoreCase("FF")){
				ProfilesIni prof=new ProfilesIni();
				FirefoxProfile fp =prof.getProfile(CONFIG.getProperty("FirefoxProfile"));
				fp.areNativeEventsEnabled();
				fp.setAssumeUntrustedCertificateIssuer(true);
				driver=new FirefoxDriver(fp);
				
			}else if(CONFIG.getProperty("browser").equalsIgnoreCase("IE")){
				System.setProperty("webdriver.ie.driver", System.getProperty("user.dir")+"\\IEDriverServer.exe");
				DesiredCapabilities caps=new DesiredCapabilities();
				caps.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
				driver=new InternetExplorerDriver(caps);
				
			}else{
				driver=new HtmlUnitDriver();
					
				}
				
			}
		}
	
	
	public static void navigate(String key){
		driver.navigate().to(CONFIG.getProperty(key));
	}
	
	public static void navigateForward(){
		driver.navigate().forward();
	}
	
	public static void navigateBack(){
		driver.navigate().back();
	}
	
	public static void refreshPage(){
		driver.navigate().refresh();
	}
	
	public static String getPageTitle(){
		return driver.getTitle();
	}
	
	
	public static boolean verifyPageTitle(String expectedPageTitle){
		
		String actualPageTitle= getPageTitle().trim();
		System.out.println(actualPageTitle);
		if(actualPageTitle.equalsIgnoreCase(expectedPageTitle)){
			return true;
		}else{
			return false;
		}
	}
	
	
	public static List<String> getAllDropDownValues(WebElement elem){
		 Select sec= new Select(elem);
		List<WebElement> ops= sec.getOptions();
		List<String> str= new ArrayList<String>();
		Iterator<WebElement> it= ops.iterator();
		while(it.hasNext()){
			String op=it.next().getText();
			str.add(op);
		}		
		return str;
		
	}
	
	public static void selectFromDropDownbyValue(WebElement elem, String value){
		Select sec= new Select(elem);
		sec.selectByValue(value);
	}

	public static void selectFromDropDownbyIndex(WebElement elem, int index){
		Select sec= new Select(elem);
		sec.selectByIndex(index);
	}
	
	public static void selectFromDropDownbyText(WebElement elem, String text){
		Select sec= new Select(elem);
		sec.selectByVisibleText(text);
	}
	
	public static void click(WebElement elem){
		elem.click();
	}
	
	public static void write(WebElement elem, String data){
		elem.clear();
		elem.sendKeys(data);
	}
	public static String getElementText(WebElement elem){
		return elem.getText();
	}
	
	
	@AfterMethod
	public void teardown(ITestResult result){
		if(result.getStatus()==ITestResult.FAILURE){
			String screenshot_path=ScreenShot.captureScrenshot(driver, result.getName());
			String image=logger.addScreenCapture(screenshot_path);
			logger.log(LogStatus.FAIL, image);
		}
		report.endTest(logger);
		report.flush();
	}
	
	@AfterSuite
	public static void quit(){
		driver.quit();
	}
	
	
	
	
	}


