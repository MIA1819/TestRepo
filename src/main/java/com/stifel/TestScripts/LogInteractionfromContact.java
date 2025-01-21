package com.stifel.TestScripts;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import com.stifel.LEXCommons.SF_StandardLEX_Common_Navigations;
import com.stifel.LEXCommons.SF_StandardLEX_WebElementLocators;
import com.stifel.PageObjects.Companies;
import com.stifel.PageObjects.Contacts;
import com.stifel.PageObjects.LogInteraction;
import com.stifel.corefunctions.Browser_setup;
import com.stifel.corefunctions.Common_Functions;
import com.stifel.corefunctions.Login_Page;
import com.stifel.corefunctions.Utilities;

public class LogInteractionfromContact extends Browser_setup{
	
	@Test(groups = "LogInteractionfromContact_IB", enabled = true)
	public void LogInteractionfromContact_IB() throws Exception{
		
		try {
			SF_StandardLEX_Common_Navigations cn= new SF_StandardLEX_Common_Navigations();
			SF_StandardLEX_WebElementLocators lwe = new SF_StandardLEX_WebElementLocators();

			LogInteraction LI = new LogInteraction();
			Companies comp = new Companies();
			Contacts cont = new Contacts();
			
			setThreadDataSheetName("TestData_Stifel.xls");
			setTheTestForRun();
			
			WebDriverWait wait = new WebDriverWait(driver, 50);
			lwe = PageFactory.initElements(driver, SF_StandardLEX_WebElementLocators.class);
			
			// Login to salesforce
			Login_Page.loadApplicationURL(driver, folder, suit, "Login", 2);
			Login_Page.salesforceLogin(driver, folder,suit, "Login", 2);
			
			// Create Company
		//	comp.ClickCompaniesTab(driver, suit, folder);
		//	comp.ClickCompaniesNewbutton(driver);
		//	String CompanyName = comp.CreateCompanies(driver, suit, folder, "Companies", 34);
		//	Utilities.SetParameterFromInputSheet("Contacts", "Company Name",11, CompanyName);
			
			
			//Create Contact
		//	cn.clickonObjectTab(driver, suit, folder, "Contacts");
		//	cn.Newbuttonclick(driver);
		//	cont.CreateExternalContact(driver, suit, folder, "Contacts", 11);
			String ContactURL = "https://stifel-ib--uat.lightning.force.com/lightning/r/Contact/0037b00000fmclsAAA/view";
			driver.get(ContactURL); 
			
			//Log Interaction
			LI.LogInteractions(driver, suit, folder, "Interactions", 2, ContactURL);
			
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
	
	
	@Test(groups = "LogInteractionfromContact_RST", enabled = true)
	public void LogInteractionfromContact_RST() throws Exception{
		
		try {
			
			SF_StandardLEX_Common_Navigations cn= new SF_StandardLEX_Common_Navigations();
			SF_StandardLEX_WebElementLocators lwe = new SF_StandardLEX_WebElementLocators();
			
			LogInteraction LI = new LogInteraction();
			Companies comp = new Companies();
			Contacts cont = new Contacts();
			
			String SheetNameCompany = "Companies";
			String SheetNameContact = "Contacts";
			int SheetValCompany;
			int SheetValContact;
			
			SheetValCompany=22;
			SheetValContact=1;

			setThreadDataSheetName("TestData_Stifel.xls");
			setTheTestForRun();
			
			WebDriverWait wait = new WebDriverWait(driver, 50);
			
			// Login to salesforce
			Login_Page.loadApplicationURL(driver, folder, suit, "Login", 1);
			Login_Page.salesforceLogin(driver, folder,suit, "Login", 1);
			
			// Create Company
			comp.ClickCompaniesTab(driver, suit, folder);
			comp.ClickCompaniesNewbutton(driver);
			String CompanyName = comp.CreateCompanies(driver, suit, folder, SheetNameCompany, SheetValCompany);
			Utilities.SetParameterFromInputSheet(SheetNameContact, "Company Name", SheetValContact, CompanyName);
			
			//Create Contact
			cn.clickonObjectTab(driver, suit, folder, "Contacts");
			cont.ClickContactNewButton(driver, suit, folder);
			cont.CreateExternalContact(driver, suit, folder, SheetNameContact, SheetValContact);
			String ContactURL = driver.getCurrentUrl();
			
			//Log Interaction
			LI.LogInteractions(driver, suit, folder, "Interactions", 3, ContactURL);
			
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
