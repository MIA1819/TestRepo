package com.stifel.TestScripts;

import java.util.concurrent.TimeUnit;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.testng.annotations.Test;

import com.stifel.LEXCommons.SF_StandardLEX_Common_Navigations;
import com.stifel.PageObjects.Companies;
import com.stifel.PageObjects.Contacts;
import com.stifel.corefunctions.Browser_setup;
import com.stifel.corefunctions.Common_Functions;
import com.stifel.corefunctions.Login_Page;
import com.stifel.corefunctions.Utilities;

public class Contact_Create_Edit extends Browser_setup

{
	
		@Test(groups = "CreateEDitContacts_RST", enabled = true, priority = 2)
		public void CreateContactsExt_RST() throws Exception {
			
			SF_StandardLEX_Common_Navigations cn= new SF_StandardLEX_Common_Navigations();
			Contacts cont = new Contacts();
			Companies comp = new Companies();
			
			try {
			setThreadDataSheetName("TestData_Stifel.xls");
			setTheTestForRun();
			
			// Login to SalesForce
			Login_Page.loadApplicationURL(driver, folder, suit, "Login", 1);
			Login_Page.salesforceLogin(driver, folder,suit, "Login", 1);
			
			//Create Company 
			comp.ClickCompaniesTab(driver, suit, folder);
			comp.ClickCompaniesNewbutton(driver);
	        String CompName=comp.CreateCompanies(driver, suit, folder, "Companies", 22);
			
	        //Set Company name 
	        Utilities.SetParameterFromInputSheet("Contacts", "Company Name", 1, CompName);
	        Utilities.SetParameterFromInputSheet("Contacts", "Company Name", 3, CompName);
	        
	        //Create and Edit External Contact
			cn.clickonObjectTab(driver, suit, folder, "Contacts");
			cn.Newbuttonclick(driver);
		    cont.CreateExternalContact(driver, suit, folder, "Contacts", 1);
		    cont.validateDescriptionFieldLabel(driver, suit, folder);
		    driver.navigate().refresh();
		    Thread.sleep(10000);
		    //driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
	        cont.ClickContactEditbutton(driver);
	        cont.EditContact(driver, suit, folder, "Contacts", 2);
	        
	        
	        //Create and Edit Internal Contact
			cn.clickonObjectTab(driver, suit, folder, "Contacts");
			cn.Newbuttonclick(driver);
		    cont.CreateInternalContact(driver, suit, folder, "Contacts", 3);
		    cont.validateDescriptionFieldLabel(driver, suit, folder);
			driver.navigate().refresh();
			Thread.sleep(10000);
	        //driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
	        cont.ClickContactEditbutton(driver);
	        cont.ValidateSEBAinEmployeeEntity(driver, suit, folder);
	        cont.EditContact(driver, suit, folder, "Contacts", 4);
	        
		
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
		
		@Test(groups = "CreateEditContactsExt_IB", enabled = true, priority = 4)
		public void EditContactsExt_RST() throws Exception {
			
			SF_StandardLEX_Common_Navigations cn= new SF_StandardLEX_Common_Navigations();
			Contacts cont = new Contacts();
			Companies comp = new Companies();
			try {
				setThreadDataSheetName("TestData_Stifel.xls");
				setTheTestForRun();
				
				// Login to SalesForce
				Login_Page.loadApplicationURL(driver, folder, suit, "Login", 2);
				Login_Page.salesforceLogin(driver, folder,suit, "Login", 2);
				
				//Create Company 
				comp.ClickCompaniesTab(driver, suit, folder);
				comp.ClickCompaniesNewbutton(driver);
		        comp.CreateCompanies(driver, suit, folder, "Companies", 21);
				
		        //Set Company name 
		        Utilities.SetParameterFromInputSheet("Contacts", "Company Name", 5, comp.CompanyName);
		        Utilities.SetParameterFromInputSheet("Contacts", "Company Name", 7, comp.CompanyName);
		        
		        //Create and Edit External Contact
				cn.clickonObjectTab(driver, suit, folder, "Contacts");
				cn.Newbuttonclick(driver);
				cont.CreateExternalContact(driver, suit, folder, "Contacts", 5);
				driver.navigate().refresh();
		        Thread.sleep(10000);
		        cont.ClickContactEditbutton(driver);
		        cont.EditContact(driver, suit, folder, "Contacts", 6);
		        
		        
		        //Create and Edit Internal Contact
				cn.clickonObjectTab(driver, suit, folder, "Contacts");
				cn.Newbuttonclick(driver);
				cont.CreateInternalContact(driver, suit, folder, "Contacts", 7);
				driver.navigate().refresh();
			    Thread.sleep(10000);
		        cont.ClickContactEditbutton(driver);
		        cont.EditContact(driver, suit, folder, "Contacts", 8);
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
			        
				
	
		
		@Test(groups = "ResearchProfileFieldsValidation_For_Contact", enabled = true)
        public void researchProfileFieldsValidation_For_Contact() throws Exception {
           
            SF_StandardLEX_Common_Navigations cn= new SF_StandardLEX_Common_Navigations();
            Contacts cont = new Contacts();
            Companies comp = new Companies();
           
            try {
            setThreadDataSheetName("TestData_Stifel.xls");
            setTheTestForRun();
           
            // Login to SalesForce
            Login_Page.loadApplicationURL(driver, folder, suit, "Login", 1);
            Login_Page.salesforceLogin(driver, folder,suit, "Login", 1);
            //Create External Contact
            cn.clickonObjectTab(driver, suit, folder, "Contacts");
            cn.Newbuttonclick(driver);
            cont.CreateExternalContact(driver, suit, folder, "Contacts", 1);
            cont.validateResearchProfileForContact(driver, suit, folder);
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
		
		
		@Test(groups = "NYSF-2210_Add_RemoveContactToStaticListFromExternalContact", enabled = true)
        public void addRemoveContactToStaticList() throws Exception {
          
            SF_StandardLEX_Common_Navigations cn= new SF_StandardLEX_Common_Navigations();
            Contacts cont = new Contacts();
            Companies comp = new Companies();
          
            try {
            setThreadDataSheetName("TestData_Stifel.xls");
            setTheTestForRun();
          
            // Login to SalesForce
            Login_Page.loadApplicationURL(driver, folder, suit, "Login", 1);
            Login_Page.salesforceLogin(driver, folder,suit, "Login", 1);
            //Create External Contact
            cn.clickonObjectTab(driver, suit, folder, "Contacts");
            cn.Newbuttonclick(driver);
            cont.CreateExternalContact(driver, suit, folder, "Contacts", 1);
            cn.SwitchTabsinLightning(driver, suit, folder, "Static Lists");
            cont.addContactToStaticListFromExternalContact(driver, suit, folder);
            cont.removeContactfromStaticListFromExternalContact(driver, suit, folder);
           
           
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
       
       
        @Test(groups = "NYSF-2210_StaticListFromAvailableListValidation", enabled = true)
        public void staticListFromAvailableListValidation() throws Exception {
          
            SF_StandardLEX_Common_Navigations cn= new SF_StandardLEX_Common_Navigations();
            Contacts cont = new Contacts();
            Companies comp = new Companies();
          
            try {
            setThreadDataSheetName("TestData_Stifel.xls");
            setTheTestForRun();
          
            // Login to SalesForce
            Login_Page.loadApplicationURL(driver, folder, suit, "Login", 1);
            Login_Page.salesforceLogin(driver, folder,suit, "Login", 1);
            //Create External Contact
            cn.clickonObjectTab(driver, suit, folder, "Contacts");
            cn.Newbuttonclick(driver);
            cont.CreateExternalContact(driver, suit, folder, "Contacts", 1);
            cn.SwitchTabsinLightning(driver, suit, folder, "Static Lists");
            cont.validateStaticListUnderAvailableStaticList(driver, suit, folder);
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
       
        @Test(groups = "NYSF-2210_SharedListFromAvailableListValidation", enabled = true)
        public void sharedListFromAvailableListValidation() throws Exception {
          
            SF_StandardLEX_Common_Navigations cn= new SF_StandardLEX_Common_Navigations();
            Contacts cont = new Contacts();
            Companies comp = new Companies();
          
            try {
            setThreadDataSheetName("TestData_Stifel.xls");
            setTheTestForRun();
          
            // Login to SalesForce
            Login_Page.loadApplicationURL(driver, folder, suit, "Login", 1);
            Login_Page.salesforceLogin(driver, folder,suit, "Login", 1);
            //Create External Contact
            cn.clickonObjectTab(driver, suit, folder, "Contacts");
            cn.Newbuttonclick(driver);
            cont.CreateExternalContact(driver, suit, folder, "Contacts", 1);
            cn.SwitchTabsinLightning(driver, suit, folder, "Static Lists");
            cont.validateSharedListUnderAvailableStaticList(driver, suit, folder);
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
        
        @Test(groups = "NYSF-3228_LongTextAreaValidation", enabled = true)
        public void longTextAreaValidation() throws Exception {
          
            SF_StandardLEX_Common_Navigations cn= new SF_StandardLEX_Common_Navigations();
            Contacts cont = new Contacts();
            Companies comp = new Companies();
          
            try {
            setThreadDataSheetName("TestData_Stifel.xls");
            setTheTestForRun();
          
            // Login to SalesForce
            Login_Page.loadApplicationURL(driver, folder, suit, "Login", 1);
            Login_Page.salesforceLogin(driver, folder,suit, "Login", 1);
            //Create Internal Contact
            cn.clickonObjectTab(driver, suit, folder, "Contacts");
            cn.Newbuttonclick(driver);
            cont.CreateInternalContact(driver, suit, folder, "Contacts", 3);
            cn.SwitchTabsinLightning(driver, suit, folder, "Votes");
            cont.validateLongTextArea(driver, suit, folder, "Contacts", 1);
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
