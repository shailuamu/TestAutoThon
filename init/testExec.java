package init;

import java.lang.reflect.Method;
import java.util.LinkedHashMap;
import java.util.Map;

import org.openqa.selenium.WebDriver;

import Report.Report;

public class testExec {

	
	public static boolean executeVerifyKeyword(String verifyKey, LinkedHashMap<String, String> data) {

		boolean status = false;
		Method myMethod = null;
		Class<?> c = null;
		try {
			c = Class.forName("Execution.verifyPoints");
			Object createInstance = c.newInstance();
			Method[] h = c.getMethods();

			for (Method r : h) {
				if (r.getName().equalsIgnoreCase(verifyKey)) {
					int a = r.getParameterCount();

					if (a > 0) {
						myMethod = c.getDeclaredMethod(verifyKey, LinkedHashMap.class);
						status = (Boolean) myMethod.invoke(createInstance, data);
					} else {
						myMethod = c.getDeclaredMethod(verifyKey);
						status = (Boolean) myMethod.invoke(createInstance);
					}
				}
			}

		} catch (Exception e) {
			Report.log("unable to run keyword");

		}

		if (status) {
			return true;
		} else {
			return false;
		}

	}
	
	public static boolean executeV(WebDriver driver,String tckey,LinkedHashMap<String, String> data){
		LinkedHashMap<String,String> vReports = new LinkedHashMap<String,String>();
		int cc=0,ff=0;
		String srNo = data.get("SRNO");
		if(Report.isVerify(tckey))
		{
			Report.addVerifyPoints(tckey,srNo);
			LinkedHashMap<String,String> vKeys=Report.getVerifyStatus(tckey);
			try{
				
				for(Map.Entry m:vKeys.entrySet()){
					
					String vk = m.getKey().toString();
					boolean status =executeVerifyKeyword(vk,data);
					if(status){
						Report.updateVerifyStatus(tckey, vk, "PASS",srNo);
						vReports.put(vk, "PASS");
					}else{
						
						Report.updateVerifyStatus(tckey, vk, "FAIL",srNo);
						vReports.put(vk, "FAIL");
					}
				}
				
			}catch(Exception e){
				Report.log(e.getMessage());
				
			}
			finally{
				
				for(Map.Entry c:vReports.entrySet()){
					
					String statusC = c.getValue().toString();
					if(statusC.equalsIgnoreCase("PASS")){
						cc++;
					}
					if(statusC.equalsIgnoreCase("FAIL")){
						ff++;
					}
				}
				if(ff>0){
					
					data.replace("STATUS", "FAIL");
					data.replace("COMMENT", "Verify points fail");
					String screenshotName = tckey + "_" + srNo;
					data.put("SCREENSHOT", screenshotName);
					Report.takeScreenShot(driver,screenshotName);
					
				}
				
				else{
					
					data.replace("STATUS", "PASS");
					data.replace("COMMENT", "Verify points pass");
					String screenshotName = tckey + "_" + srNo;
					data.put("SCREENSHOT", screenshotName);
					Report.takeScreenShot(driver,screenshotName);
				}
				
				Report.updateFormStatus(tckey, data);
				
			}
			
		}
		if(ff>0){
			return false;
		}
		else {
			return true;
		}
		
	}
}
