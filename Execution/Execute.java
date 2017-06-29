/*
 * File Name : Execute.java
 * Author : Mangesh Patil
 * Comments : 
 * 
 */

package Execution;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import javax.swing.text.Document;

import org.apache.tools.ant.types.Commandline.Argument;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import Report.Report;
import common.Constants;
import init.initDriver;
import init.initTest;
import init.testExec;

public class Execute {

	
	public static WebDriver driver = initTest.driver;
	static Actions action = new Actions(driver);
	static JavascriptExecutor js = ((JavascriptExecutor) driver);
	static WebDriverWait wait = new WebDriverWait(driver, 100);
	/*public static LinkedHashMap<String,String> windows = new LinkedHashMap<String,String>();
	public static String mainWindow = null;
	public static String secondWindow = null;
	public static String previewWindow = null;*/

	public static void openURL(String APP_URL) {

		driver.get(APP_URL);
		driver.manage().window().maximize();
		//driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		//mainWindow = driver.getWindowHandle();
	}

	
	

	public static boolean LoginToApplication(LinkedHashMap<String, String> data) {
		try {
			String tcID = Report.testCaseID("Login", "LoginToApplication");
			Login(data.get("USER_ID"),data.get("PASSWORD"));
			if(testExec.executeV(driver,"LoginToApplication",data)){
				Report.takeScreenShot(driver,"LoginSuccess");
				return true;
			}
			
			else {
				
				return false;
			}
			
		} catch (Exception e) {
			Report.takeScreenShot(driver,"LoginError");
			Report.error(e.getMessage());
			return false;
		}

	}
	
	

	/** ----------------------------------- Reusable Functions ---------------------------------------- **/

	/* Is element present function */
	public static boolean isElementPresent(By by, String msg) {
		try {
			driver.findElement(by);
			Report.log(msg);
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}

	/* Wait for page to load function */
	public static void waitForPageToLoad() {
		String pageLoadStatus;
		JavascriptExecutor js;
		do {
			js = (JavascriptExecutor) driver;
			pageLoadStatus = (String) js.executeScript("return document.readyState");
			try {
				Thread.sleep(4000L);
			} catch (InterruptedException e) {

				e.printStackTrace();
			}

		} while (!pageLoadStatus.equals("complete"));

	}

	/* Is element visible method */
	public static boolean elementvisible(WebElement element, String msg) {
		try {
			boolean status = element.isDisplayed();
			Report.log(msg + "is visible");
			return status;
		} catch (NoSuchElementException e) {
			Report.log(msg + "is not visible");
			return false;
		}

	}

	

	/* For Logging in */
	public static void Login(String Uname, String Pwd) {
		driver.findElement(By.id("idUser")).clear();
		driver.findElement(By.id("idUser")).sendKeys(Uname);
		driver.findElement(By.name("password")).clear();
		driver.findElement(By.name("password")).sendKeys(Pwd);
		driver.findElement(By.id("signInBtn")).click();
		waitForPageToLoad();
	}

	/* For Log Out */
	
	/* For handling the alert */
	public static String closeAlertAndGetItsText() {
		boolean acceptNextAlert = true;
		try {
			Alert alert = driver.switchTo().alert();
			String alertText = alert.getText();
			if (acceptNextAlert) {
				alert.accept();
				Report.log(alertText);
			} else {
				alert.dismiss();
			}
			return alertText;
		} finally {
			acceptNextAlert = true;
		}
	}
	
	/* For close other windows */
	
	



	public static boolean AddOwner(LinkedHashMap<String, String> data) {
		

		try {
			String tcID = Report.testCaseID("Oweners", "AllOwners");
			driver.findElement(By.partialLinkText("Owners")).click();
			if(testExec.executeV(driver,"AllOwners",data)){
				Report.takeScreenShot(driver,"AllOwners_present");
				return true;
			}
			
			else {
				
				return false;
			}
			
		} catch (Exception e) {
			Report.takeScreenShot(driver,"AllOwners_Notpresent");
			Report.error(e.getMessage());
			return false;
		}

	
		
		
		
	}




	public static boolean VerifyHomePage(LinkedHashMap<String, String> data) {
		

		try {
			String tcID = Report.testCaseID("HomePage", "VerifyHomePage");
		//	driver.findElement(By.partialLinkText("//a[@title='home page']//span[contains(@class,'glyphicon')]")).click();
			if(testExec.executeV(driver,"VerifyHomePage",data)){
				Report.takeScreenShot(driver,"AllOwners_present");
				return true;
			}
			
			else {
				
				return false;
			}
			
		} catch (Exception e) {
			Report.takeScreenShot(driver,"AllOwners_Notpresent");
			Report.error(e.getMessage());
			return false;
		}

		
	}

	

}
