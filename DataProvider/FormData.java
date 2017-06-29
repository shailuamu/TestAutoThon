package DataProvider;

import java.lang.reflect.Method;

import org.testng.annotations.DataProvider;

import Report.Report;

public class FormData {

	@DataProvider(name = "MethodName")
	public static Object[][] getBrandsData(Method m) {
		String testCaseName = m.getName();
		return Report.getData(testCaseName);
	}

	@DataProvider(name = "ParameterData")
	public static Object[][] importParameters() {
		return Report.importParameter();
	}

	
}
