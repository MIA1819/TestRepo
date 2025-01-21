package com.stifel.TestScripts;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.testng.annotations.Test;

import com.stifel.LEXCommons.SF_StandardLEX_Common_Navigations;
import com.stifel.PageObjects.Companies;
import com.stifel.PageObjects.Contacts;
import com.stifel.PageObjects.Coverages;
import com.stifel.PageObjects.Mandates;
import com.stifel.PageObjects.Opportunities;
import com.stifel.corefunctions.Browser_setup;
import com.stifel.corefunctions.Common_Functions;
import com.stifel.corefunctions.Login_Page;
import com.stifel.corefunctions.Utilities;

public class WorkingGroup extends Utilities

{
	public class PC extends Browser_setup
	{
		

		public String compname=null;
		
		
		@Test(groups = "OpportunityWorkingGroup", enabled = true, priority = 1)
		public void OpportunityWorkingGroup() throws Exception {
			
			SF_StandardLEX_Common_Navigations cn= new SF_StandardLEX_Common_Navigations();
			Companies comp = new Companies();
			Opportunities opp = new Opportunities();

			
		try {
			setThreadDataSheetName("TestData_Stifel.xls");
			setTheTestForRun();

			// Login to salesforce
			Login_Page.loadApplicationURL(driver, folder, suit, "Login", 2);
			Login_Page.salesforceLogin(driver, folder,suit, "Login", 2);
			
			
//			//Create Company 
			comp.ClickCompaniesTab(driver, suit, folder);
			comp.ClickCompaniesNewbutton(driver);
	        String CompName= comp.CreateCompanies(driver, suit, folder, "Companies", 31);
			
	        //Set Company name 
	        Utilities.SetParameterFromInputSheet("Opportunities", "Company Name", 9, CompName);

			cn.clickonObjectTab(driver, suit, folder, "Opportunities");
			cn.Newbuttonclick(driver);
			opp.CreateOpportunites(driver, suit, folder, "Opportunities", 9);

			cn.SwitchTabsinLightning(driver, suit, folder, "Working Group");
			opp.CreateWorkingGroup_ForOppty(driver, suit, folder, "Working Group", 1);

			}
			 catch (Exception e) {
					String className = this.getClass().getSimpleName();
					String functionName = new Object() {
					}.getClass().getEnclosingMethod().getName();
					System.out.println("---***Start***---" + "\n" + functionSuit + "\n" + className + "-" + functionName + "\n"
							+ ExceptionUtils.getFullStackTrace(e) + "\n" + "---***End***---");
					Common_Functions.ResultFail(driver, suit, folder, className, className + "." + functionName,
							"failed");
				}
		}
		
		
		
		

			
			
			@Test(groups = "MandateWorkingGroup", enabled = true, priority = 1)
			public void MandateWorkingGroup() throws Exception {
				
			try {
				SF_StandardLEX_Common_Navigations cn= new SF_StandardLEX_Common_Navigations();
				Companies comp = new Companies();
				Mandates man = new Mandates();
				
				
				setThreadDataSheetName("TestData_Stifel.xls");
				setTheTestForRun();
				
				// Login to SalesForce
				Login_Page.loadApplicationURL(driver, folder, suit, "Login", 2);
				Login_Page.salesforceLogin(driver, folder,suit, "Login", 2);
				
//				//Create Company 
				comp.ClickCompaniesTab(driver, suit, folder);
				comp.ClickCompaniesNewbutton(driver);
		        String CompName= comp.CreateCompanies(driver, suit, folder, "Companies", 30);
				
		        //Set Company name 
		        Utilities.SetParameterFromInputSheet("Mandates", "Subject Company", 3, CompName);

		        
		        //Create Corporate Finance oppty
				
				cn.clickonObjectTab(driver, suit, folder, "Mandates");
				cn.Newbuttonclick(driver);
				man.CreateMandates(driver, suit, folder, "Mandates", 3);

				
				cn.SwitchTabsinLightning(driver, suit, folder, "Working Group");
				man.CreateWorkingGroup_ForMandate(driver, suit, folder, "Working Group", 2);

				}
				 catch (Exception e) {
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
}
