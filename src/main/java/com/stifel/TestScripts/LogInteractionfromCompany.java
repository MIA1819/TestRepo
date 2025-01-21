package com.stifel.TestScripts;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.testng.annotations.Test;

import com.stifel.LEXCommons.SF_StandardLEX_Common_Navigations;
import com.stifel.PageObjects.Companies;
import com.stifel.PageObjects.LogInteraction;
import com.stifel.PageObjects.Mandates;
import com.stifel.corefunctions.Browser_setup;
import com.stifel.corefunctions.Common_Functions;
import com.stifel.corefunctions.Login_Page;
import com.stifel.corefunctions.Utilities;

public class LogInteractionfromCompany extends Browser_setup{
	
	@Test(groups = "LogInteractionfromCompany_IB", enabled = true)
	public void LogInteractionfromCompany_IB() throws Exception {
		
		try {
			
			SF_StandardLEX_Common_Navigations cn= new SF_StandardLEX_Common_Navigations();
			LogInteraction LI = new LogInteraction();
			Companies comp = new Companies();
			setThreadDataSheetName("TestData_Stifel.xls");
			setTheTestForRun();
			
			// Login to salesforce
			Login_Page.loadApplicationURL(driver, folder, suit, "Login", 2);
			Login_Page.salesforceLogin(driver, folder,suit, "Login", 2);
			
			// Create Company
			comp.ClickCompaniesTab(driver, suit, folder);
			comp.ClickCompaniesNewbutton(driver);
			 comp.CreateCompanies(driver, suit, folder, "Companies", 33);
			 String companyURL = driver.getCurrentUrl();
			//String companyURL = "https://stifel-ib--uat.lightning.force.com/lightning/r/Account/001V000000kUgRPIA0/view";
	        
			//Log Interaction on the Company
	        LI.LogInteractions(driver, suit, folder, "Interactions", 1, companyURL);
			
		}
		catch(Exception e){
			String className = this.getClass().getSimpleName();
			String functionName = new Object() {
			}.getClass().getEnclosingMethod().getName();
			System.out.println("---***Start***---" + "\n" + functionSuit + "\n" + className + "-" + functionName + "\n"
					+ ExceptionUtils.getFullStackTrace(e) + "\n" + "---***End***---");
			Common_Functions.ResultFail(driver, suit, folder, className, className + "." + functionName,
					"failed");
		}
	}
	
	
	@Test(groups = "CADCurrencyPicklistValidation", enabled = true)
    public void CADCurrencyPicklistValidation() throws Exception {
       
        try {
           
            SF_StandardLEX_Common_Navigations cn= new SF_StandardLEX_Common_Navigations();
            LogInteraction LI = new LogInteraction();
            Companies comp = new Companies();
            setThreadDataSheetName("TestData_Stifel.xls");
            setTheTestForRun();
           
            // Login to salesforce
            Login_Page.loadApplicationURL(driver, folder, suit, "Login", 1);
            Login_Page.salesforceLogin(driver, folder,suit, "Login", 1);
           
            // Create Company
            comp.ClickCompaniesTab(driver, suit, folder);
            comp.ClickCompaniesNewbutton(driver);
             comp.CreateCompanies(driver, suit, folder, "Companies", 22);
             String companyURL = driver.getCurrentUrl();
            //String companyURL = "https://stifel-ib--uat.lightning.force.com/lightning/r/Account/001V000000kUgRPIA0/view";
           
            //currency type CAD added to picklist validation
            LI.currencyValidationCAD(driver, suit, folder, "Interactions", 1, companyURL);
           
        }
        catch(Exception e){
            String className = this.getClass().getSimpleName();
            String functionName = new Object() {
            }.getClass().getEnclosingMethod().getName();
            System.out.println("---***Start***---" + "\n" + functionSuit + "\n" + className + "-" + functionName + "\n"
                    + ExceptionUtils.getFullStackTrace(e) + "\n" + "---***End***---");
            Common_Functions.ResultFail(driver, suit, folder, className, className + "." + functionName,
                    "failed");
        }
    }
	
	
	@Test(groups = "NYSF-1367_NewMandateDateFormat", enabled = true)
	public void NewMandateDateFormat_IB() throws Exception {
		
		try {
			
			SF_StandardLEX_Common_Navigations cn= new SF_StandardLEX_Common_Navigations();
			LogInteraction LI = new LogInteraction();
			Companies comp = new Companies();
			Mandates man = new Mandates();
			setThreadDataSheetName("TestData_Stifel.xls");
			setTheTestForRun();
			
			// Login to salesforce
			Login_Page.loadApplicationURL(driver, folder, suit, "Login", 2);
			Login_Page.salesforceLogin(driver, folder,suit, "Login", 2);
			
			//Create Company 
			comp.ClickCompaniesTab(driver, suit, folder);
			comp.ClickCompaniesNewbutton(driver);
	        String CompName = comp.CreateCompanies(driver, suit, folder, "Companies", 36);
			
	      //Set Company name 
	        Utilities.SetParameterFromInputSheet("Mandates", "Subject Company", 4, CompName);
	        
	        //Create Corporate Finance oppty
	        cn.clickonObjectTab(driver, suit, folder, "Mandates");
			cn.Newbuttonclick(driver);
			String MandateName = man.CreateMandates(driver, suit, folder, "Mandates", 4);
			String mandateURL = driver.getCurrentUrl();
	        
			//Log Interaction on the Company
	        LI.NewMandateDateFormat(driver, suit, folder, "Interactions", 1, mandateURL, MandateName);
			
		}
		catch(Exception e){
			String className = this.getClass().getSimpleName();
			String functionName = new Object() {
			}.getClass().getEnclosingMethod().getName();
			System.out.println("---***Start***---" + "\n" + functionSuit + "\n" + className + "-" + functionName + "\n"
					+ ExceptionUtils.getFullStackTrace(e) + "\n" + "---***End***---");
			Common_Functions.ResultFail(driver, suit, folder, className, className + "." + functionName,
					"failed");
		}
	}
	
	
	@Test(groups = "LogInteractionfromCompany_RST_AR Ticker and other interest trigger handler", enabled = true)
    public void ARTickerandOtherInterestTriggerHandler() throws Exception {
       
        try {
           
            SF_StandardLEX_Common_Navigations cn= new SF_StandardLEX_Common_Navigations();
            LogInteraction LI = new LogInteraction();
            Companies comp = new Companies();
            setThreadDataSheetName("TestData_Stifel.xls");
            setTheTestForRun();
           
            // Login to salesforce
            Login_Page.loadApplicationURL(driver, folder, suit, "Login", 1);
            Login_Page.salesforceLogin(driver, folder,suit, "Login", 1);
           
            //Create Corporate company
            cn.clickonObjectTab(driver, suit, folder, "Companies");
            comp.ClickCompaniesNewbutton(driver);
            comp.CreateCompanies(driver, suit, folder, "Companies", 4);
            String CorporateURL = driver.getCurrentUrl();
           
            //Log Interaction on the Company
            LI.LogInteractions(driver, suit, folder, "Interactions", 3, CorporateURL);
            LI.addIntrestAndVerify(driver, suit, folder);
           
        }
        catch(Exception e){
            String className = this.getClass().getSimpleName();
            String functionName = new Object() {
            }.getClass().getEnclosingMethod().getName();
            System.out.println("---***Start***---" + "\n" + functionSuit + "\n" + className + "-" + functionName + "\n"
                    + ExceptionUtils.getFullStackTrace(e) + "\n" + "---***End***---");
            Common_Functions.ResultFail(driver, suit, folder, className, className + "." + functionName,
                    "failed");
        }
    }
	
	
	@Test(groups = "NYSF-3549LogInteractionfromCompany_NewModelRequestSub-Type_Working", enabled = true)
    public void NewModelRequestSubTypeWokingModel() throws Exception {
       
        try {
           
            SF_StandardLEX_Common_Navigations cn= new SF_StandardLEX_Common_Navigations();
            LogInteraction LI = new LogInteraction();
            Companies comp = new Companies();
            setThreadDataSheetName("TestData_Stifel.xls");
            setTheTestForRun();
           
            // Login to salesforce
            Login_Page.loadApplicationURL(driver, folder, suit, "Login", 1);
            Login_Page.salesforceLogin(driver, folder,suit, "Login", 1);
           
            //Create Corporate company
            cn.clickonObjectTab(driver, suit, folder, "Companies");
            comp.ClickCompaniesNewbutton(driver);
            comp.CreateCompanies(driver, suit, folder, "Companies", 4);
            String CorporateURL = driver.getCurrentUrl();
           
            //Log Interaction on the Company
            LI.SelectInteractionType(driver, suit, folder, "Interactions", 5, CorporateURL);
            LI.verifyNewsubTypesDefaultValidation(driver, suit, folder, "Interactions", 5);
        }
        catch(Exception e){
            String className = this.getClass().getSimpleName();
            String functionName = new Object() {
            }.getClass().getEnclosingMethod().getName();
            System.out.println("---***Start***---" + "\n" + functionSuit + "\n" + className + "-" + functionName + "\n"
                    + ExceptionUtils.getFullStackTrace(e) + "\n" + "---***End***---");
            Common_Functions.ResultFail(driver, suit, folder, className, className + "." + functionName,
                    "failed");
        }
    }
   
    @Test(groups = "NYSF-3549LogInteractionfromCompany_NewModelRequestSub-Types_Basic", enabled = true)
    public void NewModelRequestSubTypeBasicModel() throws Exception {
       
        try {
           
            SF_StandardLEX_Common_Navigations cn= new SF_StandardLEX_Common_Navigations();
            LogInteraction LI = new LogInteraction();
            Companies comp = new Companies();
            setThreadDataSheetName("TestData_Stifel.xls");
            setTheTestForRun();
           
            // Login to salesforce
            Login_Page.loadApplicationURL(driver, folder, suit, "Login", 1);
            Login_Page.salesforceLogin(driver, folder,suit, "Login", 1);
           
            //Create Corporate company
            cn.clickonObjectTab(driver, suit, folder, "Companies");
            comp.ClickCompaniesNewbutton(driver);
            comp.CreateCompanies(driver, suit, folder, "Companies", 4);
            String CorporateURL = driver.getCurrentUrl();
           
            //Log Interaction on the Company
            LI.SelectInteractionType(driver, suit, folder, "Interactions", 6, CorporateURL);
            LI.verifyNewsubTypesDefaultValidation(driver, suit, folder, "Interactions", 6);
           
        }
        catch(Exception e){
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
