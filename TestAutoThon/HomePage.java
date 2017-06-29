package TestAutoThon;

import java.util.LinkedHashMap;

import org.testng.annotations.Test;

import DataProvider.FormData;
import Execution.Execute;
import Report.Report;
import common.Constants;

public class HomePage {

	
	@Test(priority = 1, dataProviderClass = FormData.class,dataProvider = "ParameterData")
	public static void openURL(LinkedHashMap<String, String> data){
		
		try{
		String APP_URL = data.get("APP_URL");
		Execute.openURL(APP_URL);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	@Test(priority = 2, dataProviderClass = FormData.class,dataProvider = "MethodName")
	public void VerifyHomePage(LinkedHashMap<String, String> data) {
		

		String tcID = Report.testCaseID("HomePage", "VerifyHomePage");
		if (Report.testCaseValidation("HomePage", "VerifyHomePage")) {
			 
			Report.log("Executing test Home page ");

			
				Execute.VerifyHomePage(data);
				
			
		} else {
			Report.info("Execution flag is not enabled for test case of SrNO :" + data.get("SRNO"));
		}

	}
	
	
	
}
