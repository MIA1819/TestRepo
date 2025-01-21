package com.stifel.TestScripts;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.testng.annotations.Test;

import com.stifel.LEXCommons.SF_StandardLEX_Common_Navigations;
import com.stifel.PageObjects.Companies;
import com.stifel.PageObjects.SecurityMaster_Interest;
import com.stifel.corefunctions.Browser_setup;
import com.stifel.corefunctions.Common_Functions;
import com.stifel.corefunctions.Login_Page;

public class DemoclassSecurityMaster extends Browser_setup

{
		@Test(groups = "abc", enabled = true)
		public void test1() throws Exception {
			try{	
			SF_StandardLEX_Common_Navigations cn= new SF_StandardLEX_Common_Navigations();
			SecurityMaster_Interest sec = new SecurityMaster_Interest();
			setThreadDataSheetName("TestData_Stifel.xls");
			setTheTestForRun();

			// Login to salesforce
			Login_Page.loadApplicationURL(driver, folder, suit, "Login", 1);
			Login_Page.salesforceLogin(driver, folder,suit, "Login", 1);
			sec.ClickSecurityMasterTab(driver, suit,folder);
		    sec.ClickSecurityMasterNewbutton(driver);
		    sec.CreateSecurityMasterInterest(driver, suit, folder, "Security Master Interest", 1);	


			}
			
			catch (Exception e) 
			{
					String className = this.getClass().getSimpleName();
				String functionName = new Object() {
				}.getClass().getEnclosingMethod().getName();
				System.out.println("---***Start***---" + "\n" + functionSuit + "\n" + className + "-" + functionName + "\n"
						+ ExceptionUtils.getFullStackTrace(e) + "\n" + "---***End***---");
				Common_Functions.ResultFail(driver, suit, folder, className, className + "." + functionName,
						"failed");
			}
		}
}


