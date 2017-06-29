package Execution;

import java.util.LinkedHashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import com.sun.corba.se.spi.orbutil.fsm.Action;

import Report.Report;
import Report.datatable;
import init.initDriver;

public class verifyPoints {
	
	public static WebDriver driver = Execute.driver;
	
	 public static void highlightFail(WebElement element) {
	        for (int i = 0; i <2; i++) {
	            JavascriptExecutor js = (JavascriptExecutor) driver;
	            js.executeScript("arguments[0].setAttribute('style', arguments[1]);", element, "color: yellow; border: 4px solid red;");
	            }
	        }
	
	 public static void highlightPass(WebElement element) {
	        for (int i = 0; i <2; i++) {
	            JavascriptExecutor js = (JavascriptExecutor) driver;
	            js.executeScript("arguments[0].setAttribute('style', arguments[1]);", element, "border: 4px solid green;");
	            }
	        }
	 
	public static boolean isWelcomeTextPresent(){
		if(isElementPresent(By.xpath("//*[contains(text(),'Welcome to Dr. Dolittle Petclinic']"), "Home Page text is Present"))
		{	
		Report.log("Home Page text is Present");
		highlightPass(driver.findElement(By.id("brandLogo")));
		return true;
		}
		else 
		{
			Report.log("Home Page text is not Present");
			return false;
		}
	}
	
	
	public static boolean isHomeImagePresent(){
		if(isElementPresent(By.xpath("//*[@alt,'pets logo']"), "Home Page application Logo is Present"))
		{	
		Report.log("Home Page application Logo is Present");
		highlightPass(driver.findElement(By.xpath("//*[@alt,'pets logo']")));
		return true;
		}
		else 
		{
			Report.log("Home Page application Logo is not Present");
			return false;
		}
	}
	

	public static boolean isElementPresent(By by, String msg) {
		try {
			driver.findElement(by);
			Report.log(msg);
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}
	
	
	
	
	/* Is element visible method */	
	public static boolean elementvisible(WebElement element, String msg) {
		try {
			boolean status = element.isDisplayed();
			Report.log(msg + "is visible");
			return status;
		} catch (NoSuchElementException e) {
			System.out.println(msg + "is not visible");
			return false;
		}

	}
	
		
	}

