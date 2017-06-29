package TestAutoThon;

import java.util.LinkedHashMap;

import org.testng.annotations.Test;

import DataProvider.FormData;
import Execution.Execute;
import Report.Report;
import common.Constants;

public class Owner {
	
	@Test(priority = 1, dataProviderClass = FormData.class, dataProvider = "MethodName")
	public void AddOwner(LinkedHashMap<String, String> data) {
		

		String tcID = Report.testCaseID("Oweners", "LoginToApplication");
		if (Report.testCaseValidation("Oweners", "LoginToApplication")) {
			 
			Report.log("Executing test for Login to application '");

			if (data.get("EXCECUTE_FLAG").toString().trim().equalsIgnoreCase("Y")) {
				Report.log("Application is running for " + data.get("USER_ID"));
				Execute.AddOwner(data);
				
			} 
		} else {
			Report.updateTestCaseStatus(tcID, Constants.NOT_RUN, "Login functionality testing flag in No", "");
			Report.info("Execution flag is not enabled for test case of SrNO :" + data.get("SRNO"));
		}

	}

}
