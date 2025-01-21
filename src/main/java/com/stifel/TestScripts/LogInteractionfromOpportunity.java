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
import com.stifel.PageObjects.Opportunities;
import com.stifel.corefunctions.Browser_setup;
import com.stifel.corefunctions.Common_Functions;
import com.stifel.corefunctions.Login_Page;
import com.stifel.corefunctions.Utilities;

public class LogInteractionfromOpportunity extends Browser_setup{
	
	@Test(groups = "LogInteractionfromOpportunity", enabled = true, priority = 2)
	public void LogInteractionfromOpportunity() throws Exception {
		
		try {
			SF_StandardLEX_Common_Navigations cn= new SF_StandardLEX_Common_Navigations();
			SF_StandardLEX_WebElementLocators lwe = new SF_StandardLEX_WebElementLocators();
			Companies comp = new Companies();
			Opportunities opp = new Opportunities();
			LogInteraction LI = new LogInteraction();
			
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
	        String CompName = comp.CreateCompanies(driver, suit, folder, "Companies", 38);
	        
	        //String CompName = wait.until(ExpectedConditions.visibilityOf(lwe.CompanyName)).getText();
	        
	        //Set Company name 
	        Utilities.SetParameterFromInputSheet("Opportunities", "Company Name", 12, CompName);
	        
	        //Create Corporate Finance oppty
			cn.clickonObjectTab(driver, suit, folder, "Opportunities");
			cn.Newbuttonclick(driver);
			opp.CreateOpportunites(driver, suit, folder, "Opportunities", 12);
			String OpptyURL = driver.getCurrentUrl();
			
			//Log Interaction
			LI.LogInteractions(driver, suit, folder, "Interactions", 4, OpptyURL);
			
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
