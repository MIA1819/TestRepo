package com.stifel.TestScripts;

import java.util.List;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import com.stifel.LEXCommons.SF_StandardLEX_Common_Navigations;
import com.stifel.PageObjects.Companies;
import com.stifel.PageObjects.Contacts;
import com.stifel.PageObjects.Coverages;
import com.stifel.PageObjects.MarketingEvents;
import com.stifel.PageObjects.Opportunities;
import com.stifel.corefunctions.Browser_setup;
import com.stifel.corefunctions.Common_Functions;
import com.stifel.corefunctions.Login_Page;
import com.stifel.corefunctions.Utilities;

public class Opportunity_Creation_Edit extends Browser_setup

{
		
		
		@Test(groups = "CreateEditOpportunities", enabled = true, priority = 2)
		public void CreateContactsExt_RST() throws Exception {
			try {
			SF_StandardLEX_Common_Navigations cn= new SF_StandardLEX_Common_Navigations();
			Companies comp = new Companies();
			Opportunities opp = new Opportunities();
			
			
			setThreadDataSheetName("TestData_Stifel.xls");
			setTheTestForRun();
			
			// Login to SalesForce
			Login_Page.loadApplicationURL(driver, folder, suit, "Login", 2);
			Login_Page.salesforceLogin(driver, folder,suit, "Login", 2);
			
//			//Create Company 
			comp.ClickCompaniesTab(driver, suit, folder);
			comp.ClickCompaniesNewbutton(driver);
	        String CompName= comp.CreateCompanies(driver, suit, folder, "Companies", 20);
			
	        //Set Company name 
	        Utilities.SetParameterFromInputSheet("Opportunities", "Company Name", 1, CompName);
	        Utilities.SetParameterFromInputSheet("Opportunities", "Company Name", 3, CompName);
	        Utilities.SetParameterFromInputSheet("Opportunities", "Company Name", 5, CompName);
	        Utilities.SetParameterFromInputSheet("Opportunities", "Company Name", 7, CompName);
	        
	        //Create Corporate Finance oppty
			
			cn.clickonObjectTab(driver, suit, folder, "Opportunities");
			cn.Newbuttonclick(driver);
			opp.CreateOpportunites(driver, suit, folder, "Opportunities", 1);
			cn.ClickEditbutton(driver);
			opp.EditOpportunites(driver, suit, folder,"Opportunities", 2);
			
	        //Create  Advisory Oppty
			cn.clickonObjectTab(driver, suit, folder, "Opportunities");
			cn.Newbuttonclick(driver);
			opp.CreateOpportunites(driver, suit, folder, "Opportunities", 3);
			cn.ClickEditbutton(driver);
			opp.EditOpportunites(driver, suit, folder,"Opportunities", 4);
				
//		    //Create Leveraged Finance Oppty
			cn.clickonObjectTab(driver, suit, folder, "Opportunities");
			cn.Newbuttonclick(driver);
			opp.CreateOpportunites(driver, suit, folder, "Opportunities", 5);
			cn.ClickEditbutton(driver);
			opp.EditOpportunites(driver, suit, folder,"Opportunities", 6);
	       
		    //Create Private Placement Oppty
			cn.clickonObjectTab(driver, suit, folder, "Opportunities");
			cn.Newbuttonclick(driver);
			opp.CreateOpportunites(driver, suit, folder, "Opportunities", 7);
			cn.ClickEditbutton(driver);
			opp.EditOpportunites(driver, suit, folder,"Opportunities", 8);
	        
		
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
		
		@Test(groups = "OpptyToMandate", enabled = true, priority = 0)
		public void OpptyToMandate() throws Exception {
				try{
				SF_StandardLEX_Common_Navigations cn= new SF_StandardLEX_Common_Navigations();
				Companies comp = new Companies();
				MarketingEvents mevent=new MarketingEvents();
				Opportunities opp=new Opportunities();
				setThreadDataSheetName("TestData_Stifel.xls");
				setTheTestForRun();

				// Login to salesforce
				Login_Page.loadApplicationURL(driver, folder, suit, "Login", 2);
				Login_Page.salesforceLogin(driver, folder,suit, "Login", 2);
				
				cn.clickonObjectTab(driver, suit, folder, "Companies");
				Thread.sleep(5000);
				comp.ClickCompaniesNewbutton(driver);
				int sheetVal=32;
				String CompanyName = comp.CreateCompanies(driver, suit, folder, "Companies", sheetVal);
				Utilities.SetParameterFromInputSheet("Opportunities", "Company Name", 10, CompanyName);
				opp.ClickOpportunitiesTabs(driver, suit, folder);
				opp.ClickOpportunitiesNewbutton(driver, suit, folder);
				opp.CreateOpportunites(driver, suit, folder, "Opportunities", 10);
				cn.ClickEditbutton(driver);
				opp.EditOpportunites(driver, suit, folder,"Opportunities", 14);
				
				//opp.ChangeOpptyStage(driver, "Initial Contact", 11);
				//opp.clickButton(driver, suit,folder, "Mark as Current Stage");
				//opp.verifyStageChange(driver, suit, folder, "Initial Contact");
				
				//opp.ChangeOpptyStage(driver,  "In Discussion with Clients", 11);
				//opp.clickButton(driver, suit,folder, "Mark as Current Stage");
				//opp.verifyStageChange(driver, suit, folder, "In Discussion with Clients");
				
				//opp.ChangeOpptyStage(driver, "Pitch (Competitive)", 11);
				//opp.clickButton(driver, suit,folder, "Mark as Current Stage");
				//opp.verifyStageChange(driver, suit, folder, "Pitch (Competitive)");
				
				//opp.ChangeOpptyStage(driver, "Pitch (Proprietary)", 11);
				//opp.clickButton(driver, suit,folder, "Mark as Current Stage");
				//opp.verifyStageChange(driver, suit, folder, "Pitch (Proprietary)");
				
				//opp.ChangeOpptyStage(driver, "Deferred/On Hold", 11);
				//opp.clickButton(driver, suit,folder, "Mark as Current Stage");
				//opp.verifyStageChange(driver, suit, folder, "Deferred/On Hold");
				
				//opp.ChangeOpptyStage(driver, "Closed", 11);
				//opp.clickButton(driver, suit,folder, "Select Closed Stage");
				//opp.verifyStageChange(driver, suit, folder, "Closed");

				//opp.selectStageChangeDependencies(driver, suit, folder, "Opportunities" , "Stage", 11);
				//opp.selectStageChangeDependencies(driver, suit, folder, "Opportunities" , "Win/Loss Reason", 11);
				//to update the sub type value
				//opp.clickButton(driver, suit,folder, "Done");
				Thread.sleep(10000);					
				opp.CompareOpportunityandMandate(driver, suit, folder);
				
				}catch(Exception e){
					String className = this.getClass().getSimpleName();
					String functionName = new Object() {
					}.getClass().getEnclosingMethod().getName();	
					System.out.println("---***Start***---" + "\n" + functionSuit + "\n" + className + "-" + functionName + "\n"
							+ ExceptionUtils.getFullStackTrace(e) + "\n" + "---***End***---");
					Common_Functions.ResultFail(driver, suit, folder, className, className + "." + functionName, "failed");
				}
			}
		
		
		
		@Test(groups = "DualTrackOpportunityConversion", enabled = true, priority = 0)
		public void DualTrackOpportunityConversion() throws Exception {
				try{
				SF_StandardLEX_Common_Navigations cn= new SF_StandardLEX_Common_Navigations();
				Companies comp = new Companies();
				Opportunities opp=new Opportunities();
				setThreadDataSheetName("TestData_Stifel.xls");
				setTheTestForRun();

				// Login to salesforce
				Login_Page.loadApplicationURL(driver, folder, suit, "Login", 2);
				Login_Page.salesforceLogin(driver, folder,suit, "Login", 2);
				
				cn.clickonObjectTab(driver, suit, folder, "Companies");
				Thread.sleep(5000);
				comp.ClickCompaniesNewbutton(driver);
				int sheetVal=32;
				String CompanyName = comp.CreateCompanies(driver, suit, folder, "Companies", sheetVal);
				Utilities.SetParameterFromInputSheet("Opportunities", "Company Name", 13, CompanyName);
				opp.ClickOpportunitiesTabs(driver, suit, folder);
				opp.ClickOpportunitiesNewbutton(driver, suit, folder);
				opp.CreateOpportunites(driver, suit, folder, "Opportunities", 13);
				cn.ClickEditbutton(driver);
				opp.EditOpportunites(driver, suit, folder,"Opportunities", 11);
				Thread.sleep(60000);
				
				driver.navigate().refresh();
				Thread.sleep(10000);
				List<WebElement> MandateURLs = driver.findElements(By.xpath("//span[contains(text(),'New Mandate')]"));
				
				if(MandateURLs.size()==2) {
					Common_Functions.ResultPass(driver, suit, folder, "Validation if mandates are created successfully", "Mandates should be created successfully", "Mandates are created successfully");	
				}
				else {
					Common_Functions.ResultFail(driver, suit, folder, "Validation if mandates are created successfully", "Mandates should be created successfully", "Mandates are not created successfully");
				}
				
				}catch(Exception e){
					String className = this.getClass().getSimpleName();
					String functionName = new Object() {
					}.getClass().getEnclosingMethod().getName();
					System.out.println("---***Start***---" + "\n" + functionSuit + "\n" + className + "-" + functionName + "\n"
							+ ExceptionUtils.getFullStackTrace(e) + "\n" + "---***End***---");
					Common_Functions.ResultFail(driver, suit, folder, className, className + "." + functionName, "failed");
				}
			}
		
		
		@Test(groups = "OpportunitiesToMandateVisibility", enabled = true, priority = 2)
        public void MandateVisibility() throws Exception {
            try {
            SF_StandardLEX_Common_Navigations cn= new SF_StandardLEX_Common_Navigations();
            Companies comp = new Companies();
            Opportunities opp = new Opportunities();
            
            
            setThreadDataSheetName("TestData_Stifel.xls");
            setTheTestForRun();
            
            // Login to SalesForce
            Login_Page.loadApplicationURL(driver, folder, suit, "Login", 2);
            Login_Page.salesforceLogin(driver, folder,suit, "Login", 2);
            
//            //Create Company 
            comp.ClickCompaniesTab(driver, suit, folder);
            comp.ClickCompaniesNewbutton(driver);
            String CompName= comp.CreateCompanies(driver, suit, folder, "Companies", 20);
            
            //Set Company name 
            Utilities.SetParameterFromInputSheet("Opportunities", "Company Name", 1, CompName);
            Utilities.SetParameterFromInputSheet("Opportunities", "Company Name", 3, CompName);
            Utilities.SetParameterFromInputSheet("Opportunities", "Company Name", 5, CompName);
            Utilities.SetParameterFromInputSheet("Opportunities", "Company Name", 7, CompName);
            
          //Login as Stifel Banker user
                String SnBanker = Utilities.getParameterFromInputSheet("Companies", "Stifel Banker", 3);
                cn.LoginasUser(driver, suit, folder, "John Lanza");
            
            //Create Corporate Finance oppty
            
            cn.clickonObjectTab(driver, suit, folder, "Opportunities");
            cn.Newbuttonclick(driver);
            opp.CreateOpportunites(driver, suit, folder, "Opportunities", 1);

 

            //Verify Stage Change Message Without Lead industry banker
            
            opp.verifyStageChangeMessage(driver, suit, folder);
            cn.SwitchTabsinLightning(driver, suit, folder, "Working Group");
            opp.EditOpportunityInternalTeamMember(driver, suit, folder);
            
            //verify Message for Add Pitch date
            opp.verifyStageChangeMessageForPitch(driver, suit, folder);
            opp.AddPitchDate(driver, suit, folder);
            
            //mark current stage as complete
            opp.clickButton(driver, suit,folder, "Mark Stage as Complete");
            opp.clickButton(driver, suit,folder, "Mark Stage as Complete");
            opp.clickButton(driver, suit,folder, "Mark Stage as Complete");
            
            //Close Opportunity convert it to Mandate
            opp.clickButton(driver, suit,folder, "Mark Stage as Complete");
            opp.clickButton(driver, suit,folder, "Mark Stage as Complete");
            opp.CloseOpportunityConvertItToMandate(driver, suit, folder);
            
            
            
            //verify cost center pick list values
            opp.verifyCostCenterPickListValues(driver, suit, folder);
            
            //Add_Est_Actual_TransSize
            opp.Add_Est_Actual_TransSize(driver, suit, folder);
            
            //verify message for Engaged/Passed CC Committee stage
            opp.verifyStageChangeMessageForEngaged_Passed_CC_Committee(driver, suit, folder);
            
            //verify closed Mandate Error Message
            opp.verifyStageChangeMessageForClosed(driver, suit, folder);
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
