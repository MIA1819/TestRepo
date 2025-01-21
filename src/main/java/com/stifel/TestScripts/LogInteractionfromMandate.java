package com.stifel.TestScripts;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import com.stifel.LEXCommons.SF_StandardLEX_Common_Navigations;
import com.stifel.LEXCommons.SF_StandardLEX_WebElementLocators;
import com.stifel.PageObjects.Companies;
import com.stifel.PageObjects.LogInteraction;
import com.stifel.PageObjects.Mandates;
import com.stifel.PageObjects.Opportunities;
import com.stifel.corefunctions.Browser_setup;
import com.stifel.corefunctions.Common_Functions;
import com.stifel.corefunctions.Login_Page;
import com.stifel.corefunctions.Utilities;

public class LogInteractionfromMandate extends Browser_setup{
	
	@Test(groups = "LogInteractionfromMandate_IB", enabled = true, priority = 2)
	public void LogInteractionfromOpportunity() throws Exception {
		
		try {
			SF_StandardLEX_Common_Navigations cn= new SF_StandardLEX_Common_Navigations();
			SF_StandardLEX_WebElementLocators lwe = new SF_StandardLEX_WebElementLocators();
			Companies comp = new Companies();
			Opportunities opp = new Opportunities();
			LogInteraction LI = new LogInteraction();
			Mandates man = new Mandates();
			
			setThreadDataSheetName("TestData_Stifel.xls");
			setTheTestForRun();
			
			WebDriverWait wait = new WebDriverWait(driver, 50);
			lwe = PageFactory.initElements(driver, SF_StandardLEX_WebElementLocators.class);
			
			// Login to SalesForce
			Login_Page.loadApplicationURL(driver, folder, suit, "Login", 2);
			Login_Page.salesforceLogin(driver, folder,suit, "Login", 2);
			
			//Create Company 
			comp.ClickCompaniesTab(driver, suit, folder);
			comp.ClickCompaniesNewbutton(driver);
	        String CompName = comp.CreateCompanies(driver, suit, folder, "Companies", 36);
	        
	       // String CompName = wait.until(ExpectedConditions.visibilityOf(lwe.CompanyName)).getText();
	        
	        //Set Company name 
	        Utilities.SetParameterFromInputSheet("Mandates", "Subject Company", 4, CompName);
	        
	        //Create Corporate Finance oppty
	        cn.clickonObjectTab(driver, suit, folder, "Mandates");
			cn.Newbuttonclick(driver);
			man.CreateMandates(driver, suit, folder, "Mandates", 4);
			String mandateURL = driver.getCurrentUrl();
			
			//Log Interaction
			LI.LogInteractions(driver, suit, folder, "Interactions", 4, mandateURL);
			
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
	
	
	
	@Test(groups = "NYSF-1303_SplitPastInteractionsMandate", enabled = true, priority = 2)
	public void SplitPastInteractionsMandate() throws Exception {
		
		try {
			SF_StandardLEX_Common_Navigations cn= new SF_StandardLEX_Common_Navigations();
			SF_StandardLEX_WebElementLocators lwe = new SF_StandardLEX_WebElementLocators();
			Companies comp = new Companies();
			Opportunities opp = new Opportunities();
			LogInteraction LI = new LogInteraction();
			Mandates man = new Mandates();
			
			setThreadDataSheetName("TestData_Stifel.xls");
			setTheTestForRun();
			
			WebDriverWait wait = new WebDriverWait(driver, 50);
			lwe = PageFactory.initElements(driver, SF_StandardLEX_WebElementLocators.class);
			
			// Login to SalesForce
			Login_Page.loadApplicationURL(driver, folder, suit, "Login", 2);
			Login_Page.salesforceLogin(driver, folder,suit, "Login", 2);
			
			//Create Company
			comp.ClickCompaniesTab(driver, suit, folder);
			comp.ClickCompaniesNewbutton(driver);
	        String CompName = comp.CreateCompanies(driver, suit, folder, "Companies", 36);
	        
	        //Set Company name 
	        Utilities.SetParameterFromInputSheet("Mandates", "Subject Company", 4, CompName);
	        
	        //Create Mandate1
	        cn.clickonObjectTab(driver, suit, folder, "Mandates");
			cn.Newbuttonclick(driver);
			man.CreateMandates(driver, suit, folder, "Mandates", 4);
			String URL1 = driver.getCurrentUrl();
			
			//Create Mandate2
	        cn.clickonObjectTab(driver, suit, folder, "Mandates");
			cn.Newbuttonclick(driver);
			String Mandate2 = man.CreateMandates(driver, suit, folder, "Mandates", 4);
			String URL2 = driver.getCurrentUrl();
			driver.navigate().refresh();
			Thread.sleep(10000);
			
			//Make the Mandate 2 as counterparty
			cn.SwitchTabsinLightning(driver, suit, folder, "Counterparties");
			man.clickonAddNewCounterPartybutton(driver);
	        man.CreateCounterParty(driver, suit, folder, CompName);
	        
	        //Log Interaction in Mandate1
	        Utilities.SetParameterFromInputSheet("Interactions", "Mandates", 1, Mandate2);
	        LI.LogInteractions(driver, suit, folder, "Interactions", 4, URL1);
	        
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
