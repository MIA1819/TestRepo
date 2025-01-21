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

public class Bids_Counterparties_Test extends Browser_setup

{
		
		@Test(groups = "CreateBidsCounterparties_IB", enabled = true, priority = 1)
		public void CreateBidsCounterparties_IB() throws Exception {
			
			SF_StandardLEX_Common_Navigations cn= new SF_StandardLEX_Common_Navigations();
			Mandates mand = new Mandates();
			Companies comp = new Companies();
		try {
			setThreadDataSheetName("TestData_Stifel.xls");
			setTheTestForRun();

			// Login to salesforce
			Login_Page.loadApplicationURL(driver, folder, suit, "Login", 2);
			Login_Page.salesforceLogin(driver, folder,suit, "Login", 2);
			Thread.sleep(10000);
			
			
			  cn.clickonObjectTab(driver, suit, folder, "Companies");
			  cn.Newbuttonclick(driver); 
			  String CompName1 = comp.CreateCompanies(driver, suit, folder,"Companies", 39);
			  
				
			  cn.clickonObjectTab(driver, suit, folder, "Companies");
			  cn.Newbuttonclick(driver); 
			  String CompName2 = comp.CreateCompanies(driver, suit, folder,"Companies", 40);
		      
              
			  Utilities.SetParameterFromInputSheet("Mandates", "Subject Company", 5, CompName1);
			  cn.clickonObjectTab(driver, suit, folder, "Mandates");
			  cn.Newbuttonclick(driver);
	          mand.CreateMandates(driver, suit, folder, "Mandates", 5);
	          String MandateURl = driver.getCurrentUrl();
	          cn.SwitchTabsinLightning(driver, suit, folder, "Counterparties");
			
			  driver.get(MandateURl);
			  Thread.sleep(10000);
			  cn.SwitchTabsinLightning(driver, suit, folder, "Counterparties");
	          
			  mand.clickonAddNewCounterPartybutton(driver);
	          mand.CreateCounterParty(driver, suit, folder, CompName1);
	          mand.CreateCounterParty(driver, suit, folder, CompName2);
	          driver.get(MandateURl);
			  Thread.sleep(10000);
	          mand.ValidateCounterpartiesAddedonMandate(driver, suit, folder, CompName1);
	          mand.ValidateCounterpartiesAddedonMandate(driver, suit, folder, CompName2);
	          
	          
	          cn.SwitchTabsinLightning(driver, suit, folder, "Bids");
	          mand.CreateBid(driver, suit, folder, CompName1, CompName2);
              mand.ValidateCompanypresentinBid(driver, suit, folder, CompName1);
              mand.ValidateCompanypresentinBid(driver, suit, folder, CompName2);
              mand.RemoveCompanyfromBid(driver, suit, folder, CompName1);
              mand.ValidateCompanyremovedfromBid(driver, suit, folder, CompName1);
              mand.AddCounterpartyfromBids(driver, suit, folder, CompName1);
              mand.ValidateCompanypresentinBid(driver, suit, folder, CompName1);
	        			
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
