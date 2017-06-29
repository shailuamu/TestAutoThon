package init;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.Reader;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Hashtable;
import java.util.Properties;
import java.util.Random;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import java.util.*;

import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;

import org.apache.log4j.Appender;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.FileAppender;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.io.FileHandler;
import org.testng.SkipException;

import Report.Report;
import common.Constants;

public class Function {
	
	


public static String readProperty(String filepath, String propertyName){
	String xpath = null;
	try{
		FileInputStream fs = new FileInputStream(filepath);
		Properties prop = new Properties();
		prop.load(fs);
		
		 xpath = prop.getProperty(propertyName);
		
		 
	}
	catch(Exception e){
		e.printStackTrace();
	}
	
	return xpath;
	
}

// Send mail with attachments


public static String readParameter(String filepath, String propertyName){
	String paramname = null;
	try{
		
		FileInputStream fs = new FileInputStream(filepath);
		Properties prop = new Properties();
		prop.load(fs);
		
		paramname = prop.getProperty(propertyName);
		 
	}

	catch(Exception e){
		e.printStackTrace();
	}
	
	return paramname;
	
}


public static void zip(String filepath){
 	try
 	{
 		File inFolder=new File(filepath);
 		File outFolder=new File("Reports.zip");
 		ZipOutputStream out = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(outFolder)));
 		BufferedInputStream in = null;
 		byte[] data  = new byte[1000];
 		String files[] = inFolder.list();
 		for (int i=0; i<files.length; i++)
 		{
 			in = new BufferedInputStream(new FileInputStream
 			(inFolder.getPath() + "/" + files[i]), 1000);  
 			out.putNextEntry(new ZipEntry(files[i])); 
 			int count;
 			while((count = in.read(data,0,1000)) != -1)
 			{
 				out.write(data, 0, count);
 			}
 			out.closeEntry();
}
out.flush();
out.close();
 	
}
catch(Exception e)
{
  e.printStackTrace();
} 
}	

public static String now(String dateFormat) {
    Calendar cal = Calendar.getInstance();
    SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
    return sdf.format(cal.getTime());
   
}






}
