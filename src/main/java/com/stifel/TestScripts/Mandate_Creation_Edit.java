	package com.stifel.TestScripts;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import com.stifel.LEXCommons.SF_StandardLEX_Common_Navigations;
import com.stifel.LEXCommons.SF_StandardLEX_WebElementLocators;
import com.stifel.PageObjects.Companies;
import com.stifel.PageObjects.Contacts;
import com.stifel.PageObjects.Coverages;
import com.stifel.PageObjects.Mandates;
import com.stifel.corefunctions.Browser_setup;
import com.stifel.corefunctions.Common_Functions;
import com.stifel.corefunctions.Login_Page;
import com.stifel.corefunctions.Utilities;

public class Mandate_Creation_Edit extends Utilities

{
	public class PC extends Browser_setup
	{
		
		
		@Test(groups = "CreateEditMandate", enabled = true, priority = 2)
		public void CreateEditMandate() throws Exception {
			try {
			SF_StandardLEX_Common_Navigations cn= new SF_StandardLEX_Common_Navigations();
			SF_StandardLEX_WebElementLocators lwe = new SF_StandardLEX_WebElementLocators();
			Companies comp = new Companies();
			Mandates man = new Mandates();
			
			setThreadDataSheetName("TestData_Stifel.xls");
			setTheTestForRun();
			
			WebDriverWait wait = new WebDriverWait(driver, 50);
			lwe = PageFactory.initElements(driver, SF_StandardLEX_WebElementLocators.class);
			
			// Login to SalesForce
			Login_Page.loadApplicationURL(driver, folder, suit, "Login", 2);
			Login_Page.salesforceLogin(driver, folder,suit, "Login", 2);
			
//			//Create Company 
			comp.ClickCompaniesTab(driver, suit, folder);
			comp.ClickCompaniesNewbutton(driver);
	        String CompName= comp.CreateCompanies(driver, suit, folder, "Companies", 30);
			
	        //Set Company name 
	        Utilities.SetParameterFromInputSheet("Mandates", "Subject Company", 1, CompName);

	        
	        //Create Corporate Finance oppty
			
			cn.clickonObjectTab(driver, suit, folder, "Mandates");
			cn.Newbuttonclick(driver);
			man.CreateMandates(driver, suit, folder, "Mandates", 1);
			cn.ClickEditbutton(driver);
			man.EditMandates(driver, suit, folder,"Mandates", 2);
			
			
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
		
		
		@Test(groups = "MandateFeesValidation", enabled = true, priority = 2)
		public void MandateFeesTest() throws Exception{
			
			try {
				SF_StandardLEX_Common_Navigations cn= new SF_StandardLEX_Common_Navigations();
				SF_StandardLEX_WebElementLocators lwe = new SF_StandardLEX_WebElementLocators();
				Companies comp = new Companies();
				Mandates man = new Mandates();
				
				setThreadDataSheetName("TestData_Stifel.xls");
				setTheTestForRun();
				
				WebDriverWait wait = new WebDriverWait(driver, 50);
				lwe = PageFactory.initElements(driver, SF_StandardLEX_WebElementLocators.class);
				
				// Login to SalesForce
				Login_Page.loadApplicationURL(driver, folder, suit, "Login", 2);
				Login_Page.salesforceLogin(driver, folder,suit, "Login", 2);
				
//				//Create Company 
				comp.ClickCompaniesTab(driver, suit, folder);
				comp.ClickCompaniesNewbutton(driver);
		        String CompName= comp.CreateCompanies(driver, suit, folder, "Companies", 30);
				
		        //Set Company name 
		        Utilities.SetParameterFromInputSheet("Mandates", "Subject Company", 1, CompName);

		        
		        //Create and update mandate status
				cn.clickonObjectTab(driver, suit, folder, "Mandates");
				cn.Newbuttonclick(driver);
				man.CreateMandates(driver, suit, folder, "Mandates", 1);
				cn.ClickEditbutton(driver);
				man.EditMandates(driver, suit, folder,"Mandates", 6);
				cn.ClickEditbutton(driver);
				man.EditMandates(driver, suit, folder,"Mandates", 7);
				
				//Click pn Add new mandate fee button
				man.ClickAddMandateFeeButton(driver, suit, folder);
				
				//Create a New Mandate fee
				man.CreateMandateFee(driver, suit, folder, "Mandate Fee",1);
				
				//Validate the Amount
				man.ValidationofAmount(driver, suit, folder);
				
				//Edit the mandate fee
				man.EditMandateFee(driver, suit, folder, "Mandate Fee",2);
				
				//Validate the Amount
				man.ValidationofAmount(driver, suit, folder);
				
				//Delete the mandate fee
				man.DeleteMandateFee(driver, suit, folder);
				
				//Validate the Amount
				man.ValidationofAmount(driver, suit, folder);
				
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
		
		@Test(groups = "DifferentMandateFeesValidation", enabled = true, priority = 2)
		public void DifferentMandateFeesValidation() throws Exception{
			
			try {
				SF_StandardLEX_Common_Navigations cn= new SF_StandardLEX_Common_Navigations();
				SF_StandardLEX_WebElementLocators lwe = new SF_StandardLEX_WebElementLocators();
				Companies comp = new Companies();
				Mandates man = new Mandates();
				
				setThreadDataSheetName("TestData_Stifel.xls");
				setTheTestForRun();
				
				WebDriverWait wait = new WebDriverWait(driver, 50);
				lwe = PageFactory.initElements(driver, SF_StandardLEX_WebElementLocators.class);
				
				// Login to SalesForce
				Login_Page.loadApplicationURL(driver, folder, suit, "Login", 2);
				Login_Page.salesforceLogin(driver, folder,suit, "Login", 2);
				
//				//Create Company 
				comp.ClickCompaniesTab(driver, suit, folder);
				comp.ClickCompaniesNewbutton(driver);
		        String CompName= comp.CreateCompanies(driver, suit, folder, "Companies", 30);
				
		        //Set Company name 
		        Utilities.SetParameterFromInputSheet("Mandates", "Subject Company", 1, CompName);

		        //Create and update mandate status
				cn.clickonObjectTab(driver, suit, folder, "Mandates");
				cn.Newbuttonclick(driver);
				man.CreateMandates(driver, suit, folder, "Mandates", 1);
				
				//Validate the System Projected Fee
				man.ValidateSystemProjectedFees1(driver, suit, folder);
				
				//Add Manual and Projected Fee
				man.ClickAddMandateFeeButton(driver, suit, folder);
				man.CreateMandateFee(driver, suit, folder, "Mandate Fee",3);
				man.ClickAddMandateFeeButton(driver, suit, folder);
				man.CreateMandateFee(driver, suit, folder, "Mandate Fee",4);
				
				//Validate System Projected Fee again
				man.ValidateSystemProjectedFees2(driver, suit, folder);
				
				//Close the Mandate
				cn.ClickEditbutton(driver);
				man.EditMandates(driver, suit, folder,"Mandates", 6);
				cn.ClickEditbutton(driver);
				man.EditMandates(driver, suit, folder,"Mandates", 8);

				//Validate the System Projected Fee is 0 after closing
				man.ValidationofAmount(driver, suit, folder);
				
				//Validate Outstanding Fee in different Currency
				
				man.OutstandingEstimatedFees(driver, suit, folder);
				
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
		
	
	
	
	@Test(groups = "NYSF-762_MandateFeesCloneValidation1", enabled = true, priority = 2)
    public void MandateFeesCloneValidation() throws Exception{
        
        try {
            SF_StandardLEX_Common_Navigations cn= new SF_StandardLEX_Common_Navigations();
            SF_StandardLEX_WebElementLocators lwe = new SF_StandardLEX_WebElementLocators();
            Companies comp = new Companies();
            Mandates man = new Mandates();
                
            setThreadDataSheetName("TestData_Stifel.xls");
            setTheTestForRun();
            
            WebDriverWait wait = new WebDriverWait(driver, 50);
            lwe = PageFactory.initElements(driver, SF_StandardLEX_WebElementLocators.class);
            
            // Login to SalesForce
            Login_Page.loadApplicationURL(driver, folder, suit, "Login", 2);
            Login_Page.salesforceLogin(driver, folder,suit, "Login", 2);
            
//            //Create Company 
            comp.ClickCompaniesTab(driver, suit, folder);
            comp.ClickCompaniesNewbutton(driver);
            String CompName= comp.CreateCompanies(driver, suit, folder, "Companies", 30);
            
            //Set Company name 
            Utilities.SetParameterFromInputSheet("Mandates", "Subject Company", 1, CompName);
            
            //Create Corporate Finance oppty
            
            cn.clickonObjectTab(driver, suit, folder, "Mandates");
            cn.Newbuttonclick(driver);
            man.CreateMandates(driver, suit, folder, "Mandates", 1);
            String mandateUrl=driver.getCurrentUrl();
            cn.SwitchTabsinLightning(driver, suit, folder, "Fees");
            man.fillCloneForm(driver, "Mandates", 1);
            driver.get(mandateUrl);
            Thread.sleep(10000);
            cn.SwitchTabsinLightning(driver, suit, folder, "Fees");
            man.verifyCloneData(driver, suit, folder, "Mandates", 1,"Fees");
            
            
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
    
    @Test(groups = "NYSF-762_MandateFeesCloneValidation2", enabled = true, priority = 2)
    public void MandateFeesCloneValidation1() throws Exception{
        
        try {
            SF_StandardLEX_Common_Navigations cn= new SF_StandardLEX_Common_Navigations();
            SF_StandardLEX_WebElementLocators lwe = new SF_StandardLEX_WebElementLocators();
            Companies comp = new Companies();
            Mandates man = new Mandates();
                
            setThreadDataSheetName("TestData_Stifel.xls");
            setTheTestForRun();
            
            WebDriverWait wait = new WebDriverWait(driver, 50);
            lwe = PageFactory.initElements(driver, SF_StandardLEX_WebElementLocators.class);
            
            // Login to SalesForce
            Login_Page.loadApplicationURL(driver, folder, suit, "Login", 2);
            Login_Page.salesforceLogin(driver, folder,suit, "Login", 2);
            
//            //Create Company 
            comp.ClickCompaniesTab(driver, suit, folder);
            comp.ClickCompaniesNewbutton(driver);
            String CompName= comp.CreateCompanies(driver, suit, folder, "Companies", 30);
            
            //Set Company name 
            Utilities.SetParameterFromInputSheet("Mandates", "Subject Company", 1, CompName);



            
            //Create Corporate Finance oppty
            
            cn.clickonObjectTab(driver, suit, folder, "Mandates");
            cn.Newbuttonclick(driver);
            man.CreateMandates(driver, suit, folder, "Mandates", 1);
            String mandateUrl=driver.getCurrentUrl();
            cn.SwitchTabsinLightning(driver, suit, folder, "Fees");
            man.fillCloneForm(driver, "Mandates",2);
            driver.get(mandateUrl);
            Thread.sleep(10000);
            cn.SwitchTabsinLightning(driver, suit, folder, "Fees");
            man.verifyCloneData(driver, suit, folder, "Mandates", 2,"Deferred Retainers");
            
            
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
    
    @Test(groups = "NYSF-762_MandateFeesCloneValidation3", enabled = true, priority = 2)
    public void MandateFeesCloneValidation2() throws Exception{
        
        try {
            SF_StandardLEX_Common_Navigations cn= new SF_StandardLEX_Common_Navigations();
            SF_StandardLEX_WebElementLocators lwe = new SF_StandardLEX_WebElementLocators();
            Companies comp = new Companies();
            Mandates man = new Mandates();
                
            setThreadDataSheetName("TestData_Stifel.xls");
            setTheTestForRun();
            
            WebDriverWait wait = new WebDriverWait(driver, 50);
            lwe = PageFactory.initElements(driver, SF_StandardLEX_WebElementLocators.class);
            
            // Login to SalesForce
            Login_Page.loadApplicationURL(driver, folder, suit, "Login", 2);
            Login_Page.salesforceLogin(driver, folder,suit, "Login", 2);
            
//            //Create Company 
            comp.ClickCompaniesTab(driver, suit, folder);
            comp.ClickCompaniesNewbutton(driver);
            String CompName= comp.CreateCompanies(driver, suit, folder, "Companies", 30);
            
            //Set Company name 
            Utilities.SetParameterFromInputSheet("Mandates", "Subject Company", 1, CompName);



            
            //Create Corporate Finance oppty
            
            cn.clickonObjectTab(driver, suit, folder, "Mandates");
            cn.Newbuttonclick(driver);
            man.CreateMandates(driver, suit, folder, "Mandates", 1);
            String mandateUrl=driver.getCurrentUrl();
            cn.SwitchTabsinLightning(driver, suit, folder, "Fees");
            man.fillCloneForm(driver, "Mandates",3);
            driver.get(mandateUrl);
            Thread.sleep(10000);
            cn.SwitchTabsinLightning(driver, suit, folder, "Fees");
            man.verifyCloneData(driver, suit, folder, "Mandates", 3,"Capitalized Expenses");
            
            
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
