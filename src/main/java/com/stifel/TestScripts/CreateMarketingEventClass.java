package com.stifel.TestScripts;

import java.util.ArrayList;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import com.stifel.LEXCommons.SF_StandardLEX_Common_Navigations;
import com.stifel.LEXCommons.SF_StandardLEX_WebElementLocators;
import com.stifel.PageObjects.Companies;
import com.stifel.PageObjects.Contacts;
import com.stifel.PageObjects.LogInteraction;
import com.stifel.PageObjects.MarketingEvents;
import com.stifel.corefunctions.Browser_setup;
import com.stifel.corefunctions.Common_Functions;
import com.stifel.corefunctions.Login_Page;
import com.stifel.corefunctions.Utilities;

public class CreateMarketingEventClass extends Browser_setup{
	
	@Test(groups = "MarketingEvent_Event1", enabled = true)
	public void MarketingEvent_Event1() throws Exception{
		
		try {
			SF_StandardLEX_Common_Navigations cn= new SF_StandardLEX_Common_Navigations();
			SF_StandardLEX_WebElementLocators lwe = new SF_StandardLEX_WebElementLocators();
			
			MarketingEvents ME = new MarketingEvents();
			
			
			setThreadDataSheetName("TestData_Stifel.xls");
			setTheTestForRun();
			
			WebDriverWait wait = new WebDriverWait(driver, 50);
			
			// Login to salesforce
			Login_Page.loadApplicationURL(driver, folder, suit, "Login", 1);
			Login_Page.salesforceLogin(driver, folder,suit, "Login", 1);
			
			//Click On Marketing Event Tab
			cn.clickonObjectTab(driver, suit, folder, "Marketing Events");
			
			//Create a Marketing Event
			ME.ClickMarketingEventNewButton(driver, suit, folder);
			ME.CreateMarketingEvent(driver, suit, folder, "Marketing Events", 1);
			
			//Create an Event Meeting
			ME.CreateEventMeeting(driver, suit, folder, "Event Meeting", 1);
			
			//Add Attendee
			ME.AddAttendee(driver, suit, folder, "Add Attendee", 1);
			ME.AddAttendee(driver, suit, folder, "Add Attendee", 2);
			ME.AddAttendee(driver, suit, folder, "Add Attendee", 3);
			
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
	
	
	@Test(groups = "MarketingEvent_Event2", enabled = true)
	public void MarketingEvent_Event2() throws Exception{
		
		try {
			SF_StandardLEX_Common_Navigations cn= new SF_StandardLEX_Common_Navigations();
			SF_StandardLEX_WebElementLocators lwe = new SF_StandardLEX_WebElementLocators();
			
			MarketingEvents ME = new MarketingEvents();
			
			
			setThreadDataSheetName("TestData_Stifel.xls");
			setTheTestForRun();
			
			WebDriverWait wait = new WebDriverWait(driver, 50);
			
			// Login to salesforce
			Login_Page.loadApplicationURL(driver, folder, suit, "Login", 1);
			Login_Page.salesforceLogin(driver, folder,suit, "Login", 1);
			
			//Click On Marketing Event Tab
			cn.clickonObjectTab(driver, suit, folder, "Marketing Events");
			
			//Create a Marketing Event
			ME.ClickMarketingEventNewButton(driver, suit, folder);
			ME.CreateMarketingEvent(driver, suit, folder, "Marketing Events", 1);
			
			//Create an Event Meeting
			ME.CreateEventMeeting(driver, suit, folder, "Event Meeting", 1);
			
			//Add Attendee
			ME.AddAttendee(driver, suit, folder, "Add Attendee", 4);
			ME.AddAttendee(driver, suit, folder, "Add Attendee", 5);
			ME.AddAttendee(driver, suit, folder, "Add Attendee", 6);
			
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
	
	
	@Test(groups = "Error_Same_Attendee", enabled = true)
	public void Error_Same_Attendee() throws Exception{
		
		try {
			SF_StandardLEX_Common_Navigations cn= new SF_StandardLEX_Common_Navigations();
			SF_StandardLEX_WebElementLocators lwe = new SF_StandardLEX_WebElementLocators();
			
			MarketingEvents ME = new MarketingEvents();
			
			
			setThreadDataSheetName("TestData_Stifel.xls");
			setTheTestForRun();
			
			WebDriverWait wait = new WebDriverWait(driver, 50);
			
			// Login to salesforce
			Login_Page.loadApplicationURL(driver, folder, suit, "Login", 1);
			Login_Page.salesforceLogin(driver, folder,suit, "Login", 1);
			
			//Click On Marketing Event Tab
			cn.clickonObjectTab(driver, suit, folder, "Marketing Events");
			
			//Create a Marketing Event
			ME.ClickMarketingEventNewButton(driver, suit, folder);
			ME.CreateMarketingEvent(driver, suit, folder, "Marketing Events", 1);
			
			//Create an Event Meeting
			ME.CreateEventMeeting(driver, suit, folder, "Event Meeting", 1);
			
			//Add Attendee
			ME.AddAttendee(driver, suit, folder, "Add Attendee", 1);
			ME.AddDuplicateAttendee_Error(driver, suit, folder, "Add Attendee", 1);
			
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
	
	
	@Test(groups = "DeleteAttendee", enabled = true)
	public void DeleteAttendee() throws Exception{
		
		try {
			SF_StandardLEX_Common_Navigations cn= new SF_StandardLEX_Common_Navigations();
			SF_StandardLEX_WebElementLocators lwe = new SF_StandardLEX_WebElementLocators();
			
			MarketingEvents ME = new MarketingEvents();
			
			
			setThreadDataSheetName("TestData_Stifel.xls");
			setTheTestForRun();
			
			WebDriverWait wait = new WebDriverWait(driver, 50);
			
			// Login to salesforce
			Login_Page.loadApplicationURL(driver, folder, suit, "Login", 1);
			Login_Page.salesforceLogin(driver, folder,suit, "Login", 1);
			
			//Click On Marketing Event Tab
			cn.clickonObjectTab(driver, suit, folder, "Marketing Events");
			
			//Create a Marketing Event
			ME.ClickMarketingEventNewButton(driver, suit, folder);
			ME.CreateMarketingEvent(driver, suit, folder, "Marketing Events", 1);
			
			//Create an Event Meeting
			ME.CreateEventMeeting(driver, suit, folder, "Event Meeting", 1);
			
			//Add Attendee
			ME.AddAttendee(driver, suit, folder, "Add Attendee", 4);
			
			//Delete the added attendee
			ME.DeleteAnAttendee(driver, suit, folder, "Add Attendee", 1);
			
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
	
	
	
	@Test(groups = "ChangeStatus", enabled = true)
	public void ChangeStatusofAttendee() throws Exception{
		
		try {
			SF_StandardLEX_Common_Navigations cn= new SF_StandardLEX_Common_Navigations();
			SF_StandardLEX_WebElementLocators lwe = new SF_StandardLEX_WebElementLocators();
			
			MarketingEvents ME = new MarketingEvents();
			
			
			setThreadDataSheetName("TestData_Stifel.xls");
			setTheTestForRun();
			
			WebDriverWait wait = new WebDriverWait(driver, 50);
			
			// Login to salesforce
			Login_Page.loadApplicationURL(driver, folder, suit, "Login", 1);
			Login_Page.salesforceLogin(driver, folder,suit, "Login", 1);
			
			//Click On Marketing Event Tab
			cn.clickonObjectTab(driver, suit, folder, "Marketing Events");
			
			//Create a Marketing Event
			ME.ClickMarketingEventNewButton(driver, suit, folder);
			ME.CreateMarketingEvent(driver, suit, folder, "Marketing Events", 1);
			
			//Create an Event Meeting
			ME.CreateEventMeeting(driver, suit, folder, "Event Meeting", 1);
			
			//Add Attendee
			ME.AddAttendee(driver, suit, folder, "Add Attendee", 1);
			
			//Change status of attendee
			ME.ChangeStatusofApprovedAttendee(driver, suit, folder, "Add Attendee", 7);
			
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
	
	@Test(groups = "addAttendeeFromLittleManIcon", enabled = true, priority = 0)
	public void addAttendeeFromLittleManIcon() throws Exception {
			try{
			SF_StandardLEX_Common_Navigations cn= new SF_StandardLEX_Common_Navigations();
			Companies comp = new Companies();
			MarketingEvents mevent=new MarketingEvents();
			
			setThreadDataSheetName("TestData_Stifel.xls");
			setTheTestForRun();

			// Login to salesforce
			Login_Page.loadApplicationURL(driver, folder, suit, "Login", 1);
			Login_Page.salesforceLogin(driver, folder,suit, "Login", 1);
			cn.clickonObjectTab(driver, suit, folder, "Marketing Events");
			mevent.ClickMarketingEventNewButton(driver, suit, folder);
			int sheetVal=1;
			mevent.CreateMarketingEvent(driver, suit, folder, "Marketing Events", sheetVal);
			mevent.CreateEventMeeting(driver, suit, folder, "Event Meeting", sheetVal);
			mevent.AddAttendeeFromPeopleIcon(driver, suit, folder, "Add Attendee", 3);
			
			}catch(Exception e){
				String className = this.getClass().getSimpleName();
				String functionName = new Object() {
				}.getClass().getEnclosingMethod().getName();
				System.out.println("---***Start***---" + "\n" + functionSuit + "\n" + className + "-" + functionName + "\n"
						+ ExceptionUtils.getFullStackTrace(e) + "\n" + "---***End***---");
				Common_Functions.ResultFail(driver, suit, folder, className, className + "." + functionName, "failed");
			}
		}
	
	//Ankur: Add company interaction and check on the record 
	@Test(groups = "CheckInteractionsTabForEvents", enabled = true, priority = 0)
    public void CheckInteractionsTabForEvents() throws Exception {
            try{
                SF_StandardLEX_Common_Navigations cn= new SF_StandardLEX_Common_Navigations();
                SF_StandardLEX_WebElementLocators lwe = new SF_StandardLEX_WebElementLocators();
                Companies comp=new Companies();
                Contacts cont=new Contacts();
                LogInteraction logint=new LogInteraction();
               
                MarketingEvents ME = new MarketingEvents();
               
               
                setThreadDataSheetName("TestData_Stifel.xls");
                setTheTestForRun();
               
                WebDriverWait wait = new WebDriverWait(driver, 50);
               
                // Login to salesforce
                Login_Page.loadApplicationURL(driver, folder, suit, "Login", 1);
                Login_Page.salesforceLogin(driver, folder,suit, "Login", 1);
               
              //  comp.ClickCompaniesTab(driver, suit, folder);
              //  comp.ClickCompaniesNewbutton(driver);
              //  comp.CreateCompanies(driver, suit, folder, "Companies", 28);
                
              //  String compURL=driver.getCurrentUrl();
//                   logint.LogInteractions(driver, suit, folder, "Interactions", 2, compURL);
//                      
                //Set Company name
              //  Utilities.SetParameterFromInputSheet("Contacts", "Company Name", 11, comp.CompanyName);
//                Utilities.SetParameterFromInputSheet("Contacts", "Company Name", 3, CompName);
               
                //Create and Edit External Contact
              //  cn.clickonObjectTab(driver, suit, folder, "Contacts");
              //  cn.Newbuttonclick(driver);
              //  cont.CreateExternalContact(driver, suit, folder, "Contacts", 11);
              //  String contName=cont.ContactName;
              //  Utilities.SetParameterFromInputSheet("Add Attendee", "Contact", 8, contName);
               
                //Click On Marketing Event Tab
                cn.clickonObjectTab(driver, suit, folder, "Marketing Events");
               
                //Create a Marketing Event
                ME.ClickMarketingEventNewButton(driver, suit, folder);
                driver.navigate().refresh();
                String EventName=ME.CreateMarketingEvent(driver, suit, folder, "Marketing Events", 1);
               
                //Create an Event Meeting
                ME.CreateEventMeeting(driver, suit, folder, "Event Meeting", 1);
               
                //Add Attendee
                ME.AddApprovedAttendee(driver, suit, folder, "Add Attendee", 6);
               
//                driver.get(compURL);
//               
////                    driver.get("https://stifel-rst--rstfull.lightning.force.com/lightning/r/Account/0012g00000PvpM6AAJ/view");
//                cn.SwitchTabsinLightning(driver, suit, folder, "Interactions");
//                Thread.sleep(10000);
                comp.validateInteraction1(driver, suit, folder, EventName);
               
            }catch(Exception e){
                String className = this.getClass().getSimpleName();
                String functionName = new Object() {
                }.getClass().getEnclosingMethod().getName();
                System.out.println("---***Start***---" + "\n" + functionSuit + "\n" + className + "-" + functionName + "\n"
                        + ExceptionUtils.getFullStackTrace(e) + "\n" + "---***End***---");
                Common_Functions.ResultFail(driver, suit, folder, className, className + "." + functionName, "failed");
            }
        }
	
	@Test(groups = "Conference")
	public void Conference() throws Exception{
		
		try {
			SF_StandardLEX_Common_Navigations cn= new SF_StandardLEX_Common_Navigations();
			SF_StandardLEX_WebElementLocators lwe = new SF_StandardLEX_WebElementLocators();
			
			MarketingEvents ME = new MarketingEvents();
			
			
			setThreadDataSheetName("TestData_Stifel.xls");
			setTheTestForRun();
			
			WebDriverWait wait = new WebDriverWait(driver, 50);
			
			// Login to salesforce
			Login_Page.loadApplicationURL(driver, folder, suit, "Login", 1);
			Login_Page.salesforceLogin(driver, folder,suit, "Login", 1);
			
			//Click On Marketing Event Tab
			cn.clickonObjectTab(driver, suit, folder, "Marketing Events");
			
			//Create a Marketing Event
			ME.ClickMarketingEventNewButton(driver, suit, folder);
			ME.CreateMarketingEvent(driver, suit, folder, "Marketing Events", 2);
			//lwe.ShowMoreButton.click();
			
			//Create an Event Meeting
			cn.clickonShowMore(driver);
			ME.CreateEventMeeting(driver, suit, folder, "Event Meeting", 1);
			
			//Validate Conference page layout
			ME.ConferencePageLayoutValidation(driver, suit, folder);
			
			//Add Attendee
			ME.AddAttendee(driver, suit, folder, "Add Attendee", 1);
			
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
	
	
	@Test(groups = "MarketingEvent_Event_IndustryExpert", enabled = true)
    public void MarketingEvent_Event_IndustryExpert() throws Exception{
        
        try {
            SF_StandardLEX_Common_Navigations cn= new SF_StandardLEX_Common_Navigations();
            SF_StandardLEX_WebElementLocators lwe = new SF_StandardLEX_WebElementLocators();
            
            MarketingEvents ME = new MarketingEvents();
            
            
            setThreadDataSheetName("TestData_Stifel.xls");
            setTheTestForRun();
            
            WebDriverWait wait = new WebDriverWait(driver, 50);
            
            // Login to salesforce
            Login_Page.loadApplicationURL(driver, folder, suit, "Login", 1);
            Login_Page.salesforceLogin(driver, folder,suit, "Login", 1);
            
            //Click On Marketing Event Tab
            cn.clickonObjectTab(driver, suit, folder, "Marketing Events");
            
            //Create a Industry Expert Marketing Event
            ME.ClickMarketingEventNewButton(driver, suit, folder);
            ME.CreateMarketingEvent(driver, suit, folder, "Marketing Events", 3);
            ME.CreateEventMeeting(driver, suit, folder, "Event Meeting", 1);
            ME.validateLablechagesForEntityToTopLabelEntity(driver, suit, folder);

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
	
	
	@Test(groups = "CheckInteractionRecordThroughMarketingEvent", enabled = true, priority = 0)
    public void CheckInteractionRecordThroughMarketingEvent() throws Exception {
            try{
                SF_StandardLEX_Common_Navigations cn= new SF_StandardLEX_Common_Navigations();
                SF_StandardLEX_WebElementLocators lwe = new SF_StandardLEX_WebElementLocators();
                Companies comp=new Companies();
                Contacts cont=new Contacts();
                LogInteraction logint=new LogInteraction();
               
                MarketingEvents ME = new MarketingEvents();
               
               
                setThreadDataSheetName("TestData_Stifel.xls");
                setTheTestForRun();
               
                WebDriverWait wait = new WebDriverWait(driver, 50);
               
                // Login to salesforce
                Login_Page.loadApplicationURL(driver, folder, suit, "Login", 1);
                Login_Page.salesforceLogin(driver, folder,suit, "Login", 1);
               
                //Click On Marketing Event Tab
                cn.clickonObjectTab(driver, suit, folder, "Marketing Events");
               
                //Create a Marketing Event
                ME.ClickMarketingEventNewButton(driver, suit, folder);
                driver.navigate().refresh();
                String EventName=ME.CreateMarketingEvent(driver, suit, folder, "Marketing Events", 1);
               
                //Create an Event Meeting
                ME.CreateEventMeeting(driver, suit, folder, "Event Meeting", 1);
               
                //Add Attendee
                ME.AddApprovedAttendee(driver, suit, folder, "Add Attendee", 6);
               
                comp.validateInteraction1(driver, suit, folder, EventName);
               
           }
           catch(Exception e){
                String className = this.getClass().getSimpleName();
                String functionName = new Object() {
                }.getClass().getEnclosingMethod().getName();
                System.out.println("---***Start***---" + "\n" + functionSuit + "\n" + className + "-" + functionName + "\n"
                        + ExceptionUtils.getFullStackTrace(e) + "\n" + "---***End***---");
                Common_Functions.ResultFail(driver, suit, folder, className, className + "." + functionName, "failed");
           }

	}
	
	
	@Test(groups = "NYSF-3100_CheckRateCardDetails", enabled = true, priority = 0)
	public void CheckRateCardDetails() throws Exception {
		
		try {
			SF_StandardLEX_Common_Navigations cn= new SF_StandardLEX_Common_Navigations();
            SF_StandardLEX_WebElementLocators lwe = new SF_StandardLEX_WebElementLocators();
            Companies comp=new Companies();
            Contacts cont=new Contacts();
			
            MarketingEvents ME = new MarketingEvents();
            
            setThreadDataSheetName("TestData_Stifel.xls");
            setTheTestForRun();
           
            WebDriverWait wait = new WebDriverWait(driver, 50);
            
         // Login to salesforce
            Login_Page.loadApplicationURL(driver, folder, suit, "Login", 1);
            Login_Page.salesforceLogin(driver, folder,suit, "Login", 1);
            
		 //Create a Marketing Event
		    cn.clickonObjectTab(driver, suit, folder, "Marketing Events");
			ME.ClickMarketingEventNewButton(driver, suit, folder);
			ME.CreateMarketingEvent(driver, suit, folder, "Marketing Events", 1);
			
		 //Create an Event Meeting
			ME.CreateEventMeeting(driver, suit, folder, "Event Meeting", 1);
			
		 //Add Attendee
			ME.AddAttendeeFromLittleManIcon(driver, suit, folder, "Add Attendee", 8);
			
		 //Verify Rate Card text
			ME.VerifyRateCardDetails(driver, suit, folder);
			
		 //Add attendees from request attendance
			ME.AddContactfromRequestAttendance(driver, suit, folder, "Add Attendee", 8);
			
		 //Verify Rate Card text
			ME.VerifyRateCardDetails(driver, suit, folder);
			
		}
		catch(Exception e){
            String className = this.getClass().getSimpleName();
            String functionName = new Object() {
            }.getClass().getEnclosingMethod().getName();
            System.out.println("---***Start***---" + "\n" + functionSuit + "\n" + className + "-" + functionName + "\n"
                    + ExceptionUtils.getFullStackTrace(e) + "\n" + "---***End***---");
            Common_Functions.ResultFail(driver, suit, folder, className, className + "." + functionName, "failed");
       }
			
	}
	
	
	@Test(groups = "NYSF-1351First", enabled = true, priority = 0)
	public void ValidateSuggestedRateField() throws Exception {
		try {
			
			SF_StandardLEX_Common_Navigations cn= new SF_StandardLEX_Common_Navigations();
            SF_StandardLEX_WebElementLocators lwe = new SF_StandardLEX_WebElementLocators();
            Companies comp=new Companies();
            Contacts cont=new Contacts();
			
            MarketingEvents ME = new MarketingEvents();
            
            setThreadDataSheetName("TestData_Stifel.xls");
            setTheTestForRun();
           
            WebDriverWait wait = new WebDriverWait(driver, 50);
            
         // Login to salesforce
            Login_Page.loadApplicationURL(driver, folder, suit, "Login", 1);
            Login_Page.salesforceLogin(driver, folder,suit, "Login", 1);
            
         // Click On Marketing Event Tab
			cn.clickonObjectTab(driver, suit, folder, "Marketing Events");
			
		 // Create a Marketing Event of type Event
			ME.ClickMarketingEventNewButton(driver, suit, folder);
			ME.CreateMarketingEvent(driver, suit, folder, "Marketing Events", 1);
			
			String MEURL = driver.getCurrentUrl();
			
		 // Login as corporate access user
			cn.LoginasUser(driver, suit, folder, "Carlynn Finn");
			driver.get(MEURL);
			
		 // Validate the suggested rate field
			ME.ValidateSuggestedRateFieldEditbutton(driver, suit, folder);
			ME.EditSuggestedRate(driver, suit, folder);
			
		 // Logout as corporate access user
			cn.LogoutasUser(driver, suit, folder, "Carlynn Finn");
			
		 //	Login as equity sales user
			driver.get(MEURL);
			cn.LoginasUser(driver, suit, folder, "William Andrews");
			
		 // Validate the Suggested Rate field availability
			driver.get(MEURL);
			ME.ValidateSuggestedRateFieldEditbutton(driver, suit, folder);
			
		 // Logout as equity sales user
			cn.LogoutasUser(driver, suit, folder, "William Andrews");
			driver.get(MEURL);
			
		// Create a Marketing Event of type Event
			cn.clickonObjectTab(driver, suit, folder, "Marketing Events");
			ME.ClickMarketingEventNewButton(driver, suit, folder);
			ME.CreateMarketingEvent(driver, suit, folder, "Marketing Events", 2);
						
			MEURL = driver.getCurrentUrl();
						
		// Login as corporate access user
			cn.LoginasUser(driver, suit, folder, "Carlynn Finn");
			driver.get(MEURL);
						
		// Validate the suggested rate field
			ME.ValidateSuggestedRateFieldEditbutton(driver, suit, folder);
			ME.EditSuggestedRate(driver, suit, folder);
						
		// Logout as corporate access user
			cn.LogoutasUser(driver, suit, folder, "Carlynn Finn");
						
		//	Login as equity sales user
			driver.get(MEURL);
			cn.LoginasUser(driver, suit, folder, "William Andrews");
						
		// Validate the Suggested Rate field availability
			driver.get(MEURL);
			ME.ValidateSuggestedRateFieldEditbutton(driver, suit, folder);
						
		// Logout as equity sales user
			cn.LogoutasUser(driver, suit, folder, "William Andrews");
			
		}
		catch(Exception e){
            String className = this.getClass().getSimpleName();
            String functionName = new Object() {
            }.getClass().getEnclosingMethod().getName();
            System.out.println("---***Start***---" + "\n" + functionSuit + "\n" + className + "-" + functionName + "\n"
                    + ExceptionUtils.getFullStackTrace(e) + "\n" + "---***End***---");
            Common_Functions.ResultFail(driver, suit, folder, className, className + "." + functionName, "failed");
       }
	}
	
	
	@Test(groups = "NYSF-1351Second", enabled = true, priority = 0)
	public void NegotiatedRateandRateCardDetailsValidation() throws Exception {
		try {
			
			SF_StandardLEX_Common_Navigations cn= new SF_StandardLEX_Common_Navigations();
            SF_StandardLEX_WebElementLocators lwe = new SF_StandardLEX_WebElementLocators();
            Companies comp=new Companies();
            Contacts cont=new Contacts();
			
            MarketingEvents ME = new MarketingEvents();
            
            setThreadDataSheetName("TestData_Stifel.xls");
            setTheTestForRun();
           
            WebDriverWait wait = new WebDriverWait(driver, 50);
            
         // Login to salesforce
            Login_Page.loadApplicationURL(driver, folder, suit, "Login", 1);
            Login_Page.salesforceLogin(driver, folder,suit, "Login", 1);
            
         // Click On Marketing Event Tab
			cn.clickonObjectTab(driver, suit, folder, "Marketing Events");
			
		 // Create a Marketing Event of type Event
			ME.ClickMarketingEventNewButton(driver, suit, folder);
			ME.CreateMarketingEvent(driver, suit, folder, "Marketing Events", 1);
			
			String MEURL = driver.getCurrentUrl();
			
		 // Navigate to 'Attendees' tab
			cn.SwitchTabsinLightning(driver, suit, folder, "Attendees");
			
		 // Validate the presence of Negotiated Rate tab
			ME.ValidateNegotiatedRateColumninAttendeestab(driver, suit, folder);
			
		 // Create an Event Meeting
			ME.CreateEventMeeting(driver, suit, folder, "Event Meeting", 1);
			
		 //	Login as SEBA Sales Liason User
			cn.LoginasUser(driver, suit, folder, "Christian Berger");
			driver.get(MEURL);
			
		 //Add attendees from request attendance
			ME.AddContactfromRequestAttendance(driver, suit, folder, "Add Attendee", 8);
			
		 // Navigate to 'Attendees' tab
			cn.SwitchTabsinLightning(driver, suit, folder, "Attendees");
			
		 
		 // Validate Negotiated Rate in Attendance Requests section
			ME.EditAttendeeswithSalesLiason(driver, suit, folder, "Add Attendee", 9);
			
			
			
			
		}
		catch(Exception e){
            String className = this.getClass().getSimpleName();
            String functionName = new Object() {
            }.getClass().getEnclosingMethod().getName();
            System.out.println("---***Start***---" + "\n" + functionSuit + "\n" + className + "-" + functionName + "\n"
                    + ExceptionUtils.getFullStackTrace(e) + "\n" + "---***End***---");
            Common_Functions.ResultFail(driver, suit, folder, className, className + "." + functionName, "failed");
       }
	}
	
	
	@Test(groups = "NYSF-367_ValidateEmployeeEntityField", enabled = true, priority = 0)
	public void ValidateEmployeeEntityField() throws Exception{
		try {
			
			SF_StandardLEX_Common_Navigations cn= new SF_StandardLEX_Common_Navigations();
            SF_StandardLEX_WebElementLocators lwe = new SF_StandardLEX_WebElementLocators();
            MarketingEvents ME = new MarketingEvents();
            
            setThreadDataSheetName("TestData_Stifel.xls");
            setTheTestForRun();
           
            WebDriverWait wait = new WebDriverWait(driver, 50);
            
         // Login to salesforce
            Login_Page.loadApplicationURL(driver, folder, suit, "Login", 1);
            Login_Page.salesforceLogin(driver, folder,suit, "Login", 1);
            
         // Verify Entity Field
            ME.ValidateEntityfield(driver, suit, folder, "Marketing Events", 4);
            
         //Create an Event Meeting
			ME.CreateEventMeeting(driver, suit, folder, "Event Meeting", 1);
			
		 //Add Attendee
			ME.AddAttendee(driver, suit, folder, "Add Attendee", 1);
			ME.AddAttendee(driver, suit, folder, "Add Attendee", 2);
			ME.AddAttendee(driver, suit, folder, "Add Attendee", 3);
		   
		}
		catch(Exception e){
            String className = this.getClass().getSimpleName();
            String functionName = new Object() {
            }.getClass().getEnclosingMethod().getName();
            System.out.println("---***Start***---" + "\n" + functionSuit + "\n" + className + "-" + functionName + "\n"
                    + ExceptionUtils.getFullStackTrace(e) + "\n" + "---***End***---");
            Common_Functions.ResultFail(driver, suit, folder, className, className + "." + functionName, "failed");
       }
	}
	
	
	@Test(groups = "NYSF-3651_RSTtoIBSync", enabled = true, priority = 0)
	public void RSTtoIBSync() throws Exception{
		
		try {
			
			SF_StandardLEX_Common_Navigations cn= new SF_StandardLEX_Common_Navigations();
            SF_StandardLEX_WebElementLocators lwe = new SF_StandardLEX_WebElementLocators();
            MarketingEvents ME = new MarketingEvents();
            
            setThreadDataSheetName("TestData_Stifel.xls");
            setTheTestForRun();
           
            WebDriverWait wait = new WebDriverWait(driver, 50);
            
            lwe = PageFactory.initElements(driver, SF_StandardLEX_WebElementLocators.class);
            
         // Login to salesforce
            Login_Page.loadApplicationURL(driver, folder, suit, "Login", 1);
            Login_Page.salesforceLogin(driver, folder,suit, "Login", 1);
            
         // Click On Marketing Event Tab
         	cn.clickonObjectTab(driver, suit, folder, "Marketing Events");
         			
         // Create a Marketing Event of type Event
         	ME.ClickMarketingEventNewButton(driver, suit, folder);
         	String MENameRST = ME.CreateMarketingEvent(driver, suit, folder, "Marketing Events", 1);
         			
         	String MEURL = driver.getCurrentUrl();
         	
         //Create an Event Meeting
			ME.CreateEventMeeting(driver, suit, folder, "Event Meeting", 1);
			
			String EventMeetingNameRST = wait.until(ExpectedConditions.visibilityOf(lwe.MeetingNameLink)).getText();
         	
         //Add Attendee
			ME.AddAttendee(driver, suit, folder, "Add Attendee", 1);
			ME.AddAttendee(driver, suit, folder, "Add Attendee", 2);
			ME.AddAttendee(driver, suit, folder, "Add Attendee", 3);
			ME.AddAttendee(driver, suit, folder, "Add Attendee", 11);
			
		 //Open a new window
			((JavascriptExecutor) driver).executeScript("window.open()");
			ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
			driver.switchTo().window(tabs.get(1));
			
         // Login to IB
         	Login_Page.loadApplicationURL(driver, folder, suit, "Login", 2);
            Login_Page.salesforceLogin(driver, folder,suit, "Login", 2);
            
          //Search the marketing event
            cn.SearchwithObjectType(driver, suit, folder, MENameRST, "Campaigns"); 	
            
              
          //Validation of event name
            
            JavascriptExecutor js = (JavascriptExecutor)driver;
            js.executeScript("arguments[0].click()", wait.until(ExpectedConditions.visibilityOf(lwe.MeetingNameLink)));
            String EventMeetingNameIB = wait.until(ExpectedConditions.visibilityOf(lwe.MeetingNameLink)).getText();
            
          //Comparison of Event meeting names
            if(EventMeetingNameRST.equalsIgnoreCase(EventMeetingNameIB)) {
            	Common_Functions.ResultPass(driver, suit, folder, "Comparison of Event meeting names", "The Event meeting names should be same", "The event meeting names are same");
            }
            else {
            	Common_Functions.ResultFail(driver, suit, folder, "Comparison of Event meeting names", "The Event meeting names should be same", "The event meeting names are not same");
            }
            
          //Open the event meetings and compare the attendees
            
            ME.CompareRequestedAttendeesIBRST(driver, suit, folder, tabs);
            ME.CompareApprovedAttendeesIBRST(driver, suit, folder, tabs);
            ME.CompanyApprovedAttendeesIBRST(driver, suit, folder, tabs);
            ME.StifelApprovedAttendeesIBRST(driver, suit, folder, tabs);
		}
		catch(Exception e){
            String className = this.getClass().getSimpleName();
            String functionName = new Object() {
            }.getClass().getEnclosingMethod().getName();
            System.out.println("---***Start***---" + "\n" + functionSuit + "\n" + className + "-" + functionName + "\n"
                    + ExceptionUtils.getFullStackTrace(e) + "\n" + "---***End***---");
            Common_Functions.ResultFail(driver, suit, folder, className, className + "." + functionName, "failed");
       }
	}
	
}
