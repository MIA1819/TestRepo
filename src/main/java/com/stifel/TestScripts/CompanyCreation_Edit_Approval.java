package com.stifel.TestScripts;

import java.util.concurrent.TimeUnit;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import com.stifel.LEXCommons.SF_StandardLEX_ActionOnWebElements;
import com.stifel.LEXCommons.SF_StandardLEX_Common_Navigations;
import com.stifel.PageObjects.Companies;
import com.stifel.PageObjects.Contacts;
import com.stifel.PageObjects.Coverages;
import com.stifel.corefunctions.Browser_setup;
import com.stifel.corefunctions.Common_Functions;
import com.stifel.corefunctions.Login_Page;
import com.stifel.corefunctions.Utilities;

public class CompanyCreation_Edit_Approval extends Browser_setup

{
		@Test(groups = "RST_CompanyCreation", enabled = true)
		public void RST_accountCreation() throws Exception {
			try{	
			SF_StandardLEX_Common_Navigations cn= new SF_StandardLEX_Common_Navigations();
			Companies comp = new Companies();
			setThreadDataSheetName("TestData_Stifel.xls");
			setTheTestForRun();

			// Login to SalesForce
			Login_Page.loadApplicationURL(driver, folder, suit, "Login", 1);
			Login_Page.salesforceLogin(driver, folder,suit, "Login", 1);
            
			//Create Institutional company 
			comp.ClickCompaniesTab(driver, suit, folder);
			comp.ClickCompaniesNewbutton(driver);
	        comp.CreateCompanies(driver, suit, folder, "Companies", 1);
	        String InstitutionalURL = driver.getCurrentUrl();
	        
	        //Edit Institutional company 
	        driver.get(InstitutionalURL);
	        comp.ClickCompaniesEditbutton(driver);
	        comp.EditCompanies(driver, suit, folder, "Companies", 2);
	        
	        //Create Corporate company 
			cn.clickonObjectTab(driver, suit, folder, "Companies");
			comp.ClickCompaniesNewbutton(driver);
			comp.CreateCompanies(driver, suit, folder, "Companies", 4); 
			String CorporateURL = driver.getCurrentUrl();
			
	        //Edit Corporate company 
	        driver.get(CorporateURL);
	        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
	        comp.ClickCompaniesEditbutton(driver);
	        comp.EditCompanies(driver, suit, folder, "Companies", 5);
	               
	        //Create Other company 
	   	     cn.clickonObjectTab(driver, suit, folder, "Companies");
	   	     comp.ClickCompaniesNewbutton(driver);
	   	     comp.CreateCompanies(driver, suit, folder, "Companies", 7); 
	   	     String OtherURL=driver.getCurrentUrl();
	   	      
	   	     //Edit Other company 
	   	     driver.get(OtherURL);
	   	     driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
	   	     comp.ClickCompaniesEditbutton(driver);
	   	     comp.EditCompanies(driver, suit, folder, "Companies", 8);
	   	     
//	   	     
	   	     //Login as Governance user
	   	     String GovUser = Utilities.getParameterFromInputSheet("Companies", "Governance User", 3);
	   	     cn.LoginasUser(driver, suit, folder, GovUser);
	   	    
	   	     //Approve Institutional company 
	   	     driver.get(InstitutionalURL);
	   	     driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
	   	     comp.ClickCompaniesEditbutton(driver);
	   	     comp.EditCompanies(driver, suit, folder, "Companies", 3);

	   	     //Approve Corporate company 
	   	     driver.get(CorporateURL);
	   	     driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
	   	     comp.ClickCompaniesEditbutton(driver);
	   	     comp.EditCompanies(driver, suit, folder, "Companies", 6);
	   	     
	   	     //Approve Other company 
	   	     driver.get(OtherURL);
	   	     driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
	   	     comp.ClickCompaniesEditbutton(driver);
	   	     comp.EditCompanies(driver, suit, folder, "Companies", 9);
	        
			}
			
			catch (Exception e) 
			{
			    String className = this.getClass().getSimpleName();
				String functionName = new Object() {
				}.getClass().getEnclosingMethod().getName();
				System.out.println("---***Start***---" + "\n" + functionSuit + "\n" + className + "-" + functionName + "\n"
						+ ExceptionUtils.getFullStackTrace(e) + "\n" + "---***End***---");
				Common_Functions.ResultFail(driver, suit, folder, className, className + "." + functionName,
						"failed");
			}
		}
		
		
		
		@Test(groups = "IB_CompanyCreation", enabled = true)
		public void IB_accountCreation() throws Exception {
			try{	
			SF_StandardLEX_Common_Navigations cn= new SF_StandardLEX_Common_Navigations();
			Companies comp = new Companies();
			setThreadDataSheetName("TestData_Stifel.xls");
			setTheTestForRun();

			// Login to SalesForce
			Login_Page.loadApplicationURL(driver, folder, suit, "Login", 2);
			Login_Page.salesforceLogin(driver, folder,suit, "Login", 2);
            
			//Create Institutional company 
			comp.ClickCompaniesTab(driver, suit, folder);
			comp.ClickCompaniesNewbutton(driver);
	        comp.CreateCompanies(driver, suit, folder, "Companies", 10);
	        String InstitutionalURL = driver.getCurrentUrl();
	        
	      //Edit Institutional company 
	        driver.get(InstitutionalURL);
	        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
	        comp.ClickCompaniesEditbutton(driver);
	        comp.EditCompanies(driver, suit, folder, "Companies", 11);
	        
	      //Create Corporate company 
			cn.clickonObjectTab(driver, suit, folder, "Companies");
			comp.ClickCompaniesNewbutton(driver);
			comp.CreateCompanies(driver, suit, folder, "Companies", 13); 
			String CorporateURL=driver.getCurrentUrl();
					
	        
	        //Edit Corporate company 
	        driver.get(CorporateURL);
	        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
	        comp.ClickCompaniesEditbutton(driver);
	        comp.EditCompanies(driver, suit, folder, "Companies", 14);
	        
	        
	        //Create Other company 
	   	     cn.clickonObjectTab(driver, suit, folder, "Companies");
	   	     comp.ClickCompaniesNewbutton(driver);
	   	     comp.CreateCompanies(driver, suit, folder, "Companies", 16); 
	   	     
	   	      String OtherURL=driver.getCurrentUrl();
	   	     //Edit Other company 
	   	     driver.get(OtherURL);
	   	     driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
	   	     comp.ClickCompaniesEditbutton(driver);
	   	     comp.EditCompanies(driver, suit, folder, "Companies", 17);
	   	     
//	   	     
	   	     //Login as Governance user
	   	     String GovUser = Utilities.getParameterFromInputSheet("Companies", "Governance User", 12);
	   	     cn.LoginasUser(driver, suit, folder, GovUser);
	   	    
	   	     //Approve Institutional company 
	   	     driver.get(InstitutionalURL);
	   	     driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
	   	     comp.ClickCompaniesEditbutton(driver);
	   	     comp.EditCompanies(driver, suit, folder, "Companies", 12);

	   	     //Approve Corporate company 
	   	     driver.get(CorporateURL);
	   	     driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
	   	     comp.ClickCompaniesEditbutton(driver); 
	   	     comp.EditCompanies(driver, suit, folder, "Companies", 15);
	   	     
	   	     //Approve Other company 
	   	     driver.get(OtherURL);
	   	     driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
	   	     comp.ClickCompaniesEditbutton(driver);
	   	     comp.EditCompanies(driver, suit, folder, "Companies", 18);
	        
			}
			
			catch (Exception e) 
			{
					String className = this.getClass().getSimpleName();
				String functionName = new Object() {
				}.getClass().getEnclosingMethod().getName();
				System.out.println("---***Start***---" + "\n" + functionSuit + "\n" + className + "-" + functionName + "\n"
						+ ExceptionUtils.getFullStackTrace(e) + "\n" + "---***End***---");
				Common_Functions.ResultFail(driver, suit, folder, className, className + "." + functionName,
						"failed");
			}
		}
		
			
		@Test(groups = "RST_TradingCommissions", enabled = true)
		public void RST_TradingCommissions() throws Exception {
			try{
				
				SF_StandardLEX_Common_Navigations cn= new SF_StandardLEX_Common_Navigations();
				Companies comp = new Companies();
				setThreadDataSheetName("TestData_Stifel.xls");
				setTheTestForRun();

				// Login to SalesForce
				Login_Page.loadApplicationURL(driver, folder, suit, "Login", 1);
				Login_Page.salesforceLogin(driver, folder,suit, "Login", 1);
				
				//Create Corporate company 
				cn.clickonObjectTab(driver, suit, folder, "Companies");
				comp.ClickCompaniesNewbutton(driver);
				comp.CreateCompanies(driver, suit, folder, "Companies", 4); 
				String CorporateURL = driver.getCurrentUrl();
				
				
				//Validate Commission tab header
				WebDriverWait wait = new WebDriverWait(driver, 30);
				JavascriptExecutor js = (JavascriptExecutor) driver;
				js.executeScript("arguments[0].click();", wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//li/a[text()='Revenues']"))));
				Thread.sleep(10000);
				
				comp.ValidateTradingCommissionUSD(driver, suit, folder);
				comp.ValidateTradingCommissionGBP(driver, suit, folder);
				comp.ValidateTradingCommissionEUR(driver, suit, folder);
				comp.ValidateTradingCommissionCAD(driver, suit, folder);
				comp.ValidateTradingCommissionTableOrder(driver, suit, folder);
				
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
		
		
		
		@Test(groups = "RST_RadioButtoninCommissionSummary", enabled = true)
		public void RST_RadioButtoninCommissionSummary() throws Exception {
			try{
				
				SF_StandardLEX_Common_Navigations cn= new SF_StandardLEX_Common_Navigations();
				Companies comp = new Companies();
				setThreadDataSheetName("TestData_Stifel.xls");
				setTheTestForRun();

				// Login to SalesForce
				Login_Page.loadApplicationURL(driver, folder, suit, "Login", 1);
				Login_Page.salesforceLogin(driver, folder,suit, "Login", 1);
				
				//Create Institutional company1 
				comp.ClickCompaniesTab(driver, suit, folder);
				comp.ClickCompaniesNewbutton(driver);
		        comp.CreateCompanies(driver, suit, folder, "Companies", 1);
		        String InstitutionalURL1 = driver.getCurrentUrl();
		        
		        //Select Commission Summary currency
		        comp.SelectCommissionSummaryCurrency(driver, suit, folder, "USD");
		        
		        //Create Institutional company2
				comp.ClickCompaniesTab(driver, suit, folder);
				comp.ClickCompaniesNewbutton(driver);
		        comp.CreateCompanies(driver, suit, folder, "Companies", 1);
		        String InstitutionalURL2 = driver.getCurrentUrl();
		        
		        //Verify Selected Radio button
		        comp.ValidateSelectedCommissionSummaryCurrency(driver, suit, folder, "USD");
		        
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
		
		
		
		@Test(groups = "RST_BracketedValuesClientandTradingStatus", enabled = true)
		public void RST_BracketedValuesClientandTradingStatus() throws Exception {
			try{
				
				SF_StandardLEX_Common_Navigations cn= new SF_StandardLEX_Common_Navigations();
				Companies comp = new Companies();
				setThreadDataSheetName("TestData_Stifel.xls");
				setTheTestForRun();

				// Login to SalesForce
				Login_Page.loadApplicationURL(driver, folder, suit, "Login", 1);
				Login_Page.salesforceLogin(driver, folder,suit, "Login", 1);
				
				//Create Institutional company1 
				comp.ClickCompaniesTab(driver, suit, folder);
				comp.ClickCompaniesNewbutton(driver);
		        comp.CreateCompanies(driver, suit, folder, "Companies", 1);
		        String InstitutionalURL1 = driver.getCurrentUrl();
		        
		        //Validate Bracketed Values
		        comp.ValidateBrackettedValues(driver, suit, folder);
		        
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
		
		
		@Test(groups = "CheckTradingStatus", enabled = true)
		public void CheckTradingStatus() throws Exception {
			
			try {
				SF_StandardLEX_Common_Navigations cn= new SF_StandardLEX_Common_Navigations();
				Companies comp = new Companies();
				setThreadDataSheetName("TestData_Stifel.xls");
				setTheTestForRun();

				// Login to SalesForce
				Login_Page.loadApplicationURL(driver, folder, suit, "Login", 1);
				Login_Page.salesforceLogin(driver, folder,suit, "Login", 1);
				
				//Create Institutional company1 
				comp.ClickCompaniesTab(driver, suit, folder);
				comp.ClickCompaniesNewbutton(driver);
		        comp.CreateCompanies(driver, suit, folder, "Companies", 1);
		        String InstitutionalURL1 = driver.getCurrentUrl();
		        
		        //Update Client Status fields
		        comp.ClickCompaniesEditbutton(driver);
		        comp.EditCompanies(driver, suit, folder, "Companies", 41);
		        
		        //Verify Trading Status Fields
		        comp.VerifyTradingStatus(driver, suit, folder);
				
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
		
		
		@Test(groups = "ResearchProfileFieldsValidation_For_Company", enabled = true)
        public void researchProfileFieldsValidation_For_Company() throws Exception {
            try{    
            SF_StandardLEX_Common_Navigations cn= new SF_StandardLEX_Common_Navigations();
            Companies comp = new Companies();
            setThreadDataSheetName("TestData_Stifel.xls");
            setTheTestForRun();

            // Login to SalesForce
            Login_Page.loadApplicationURL(driver, folder, suit, "Login", 1);
            Login_Page.salesforceLogin(driver, folder,suit, "Login", 1);
            
            //Create Institutional company 
            comp.ClickCompaniesTab(driver, suit, folder);
            comp.ClickCompaniesNewbutton(driver);
            comp.CreateCompanies(driver, suit, folder, "Companies", 1);
            String InstitutionalURL = driver.getCurrentUrl();
            
            //Validation Of Research Profile
            comp.validateResearchProfilefieldsForCompany(driver, suit, folder);
          
            }
                
            catch (Exception e) 
            {
                    String className = this.getClass().getSimpleName();
                    String functionName = new Object() {
                    }.getClass().getEnclosingMethod().getName();
                    System.out.println("---***Start***---" + "\n" + functionSuit + "\n" + className + "-" + functionName + "\n"
                            + ExceptionUtils.getFullStackTrace(e) + "\n" + "---***End***---");
                    Common_Functions.ResultFail(driver, suit, folder, className, className + "." + functionName,
                            "failed");
            }
        }
		
		
		@Test(groups = "CreateShortNames", enabled = true)
		 public void CreateShortNames() throws Exception {
            try{
            	
            	SF_StandardLEX_Common_Navigations cn= new SF_StandardLEX_Common_Navigations();
                Companies comp = new Companies();
                Coverages cov = new Coverages();
                setThreadDataSheetName("TestData_Stifel.xls");
                setTheTestForRun();

                // Login to SalesForce
                Login_Page.loadApplicationURL(driver, folder, suit, "Login", 1);
                Login_Page.salesforceLogin(driver, folder,suit, "Login", 1);
                
              //Create Institutional company 
                comp.ClickCompaniesTab(driver, suit, folder);
                comp.ClickCompaniesNewbutton(driver);
                comp.CreateCompanies(driver, suit, folder, "Companies", 1);
                String InstitutionalURL = driver.getCurrentUrl();
                
                //Login as account opening user
                String accopeninguser = Utilities.getParameterFromInputSheet("Coverage", "Account Opening User", 2);
                cn.LoginasUser(driver, suit, folder, accopeninguser);
                
                //Navigate to Coverage tab
                driver.get(InstitutionalURL);
                cn.SwitchTabsinLightning(driver, suit, folder, "Coverage");
                
                //Create Short Name and assign rep
                cov.CreateCoverageforRST(driver, suit, folder,"Coverage", 6);
                
                //Compare Coverage Employees with Rep Code Employees
                comp.CompareRepCodeEmployeeswithCoverage(driver, suit, folder,InstitutionalURL);
             
            }
            
            catch (Exception e) 
            {
                    String className = this.getClass().getSimpleName();
                    String functionName = new Object() {
                    }.getClass().getEnclosingMethod().getName();
                    System.out.println("---***Start***---" + "\n" + functionSuit + "\n" + className + "-" + functionName + "\n"
                            + ExceptionUtils.getFullStackTrace(e) + "\n" + "---***End***---");
                    Common_Functions.ResultFail(driver, suit, folder, className, className + "." + functionName,
                            "failed");
            }
		}
		
		
		
		
		
		@Test(groups = "EditShortNames", enabled = true)
		 public void EditShortNames() throws Exception {
           try{
           	
           	SF_StandardLEX_Common_Navigations cn= new SF_StandardLEX_Common_Navigations();
               Companies comp = new Companies();
               Coverages cov = new Coverages();
               setThreadDataSheetName("TestData_Stifel.xls");
               setTheTestForRun();

               // Login to SalesForce
               Login_Page.loadApplicationURL(driver, folder, suit, "Login", 1);
               Login_Page.salesforceLogin(driver, folder,suit, "Login", 1);
               
             //Create Institutional company 
               comp.ClickCompaniesTab(driver, suit, folder);
               comp.ClickCompaniesNewbutton(driver);
               comp.CreateCompanies(driver, suit, folder, "Companies", 1);
               String InstitutionalURL = driver.getCurrentUrl();
               
               //Login as account opening user
               String accopeninguser = Utilities.getParameterFromInputSheet("Coverage", "Account Opening User", 2);
               cn.LoginasUser(driver, suit, folder, accopeninguser);
               
               //Navigate to Coverage tab
               driver.get(InstitutionalURL);
               cn.SwitchTabsinLightning(driver, suit, folder, "Coverage");
               
               //Create Short Name and assign rep
               cov.CreateCoverageforRST(driver, suit, folder,"Coverage", 6);
               
               String ShortNameURL = driver.getCurrentUrl();
               
               //Logout as user
               cn.LogoutasUser(driver, suit, folder, accopeninguser);
               
               //Login as account opening user
               driver.get(InstitutionalURL);
               String accopeninguser2 = Utilities.getParameterFromInputSheet("Coverage", "Account Opening User", 5);
               cn.LoginasUser(driver, suit, folder, accopeninguser2);
               
               //Edit the SN
               driver.get(ShortNameURL);
               cov.EditShortNames(driver, suit, folder, "Coverage", 5);
            
           }
           
           catch (Exception e) 
           {
                   String className = this.getClass().getSimpleName();
                   String functionName = new Object() {
                   }.getClass().getEnclosingMethod().getName();
                   System.out.println("---***Start***---" + "\n" + functionSuit + "\n" + className + "-" + functionName + "\n"
                           + ExceptionUtils.getFullStackTrace(e) + "\n" + "---***End***---");
                   Common_Functions.ResultFail(driver, suit, folder, className, className + "." + functionName,
                           "failed");
           }
		}
		
		
		@Test(groups = "CompanyAffiliationOfPortfolioCompanyStatus", enabled = true)
        public void CompanyAffiliationOfPortfolioCompanyStatus() throws Exception {
            try{    
    
                SF_StandardLEX_Common_Navigations cn= new SF_StandardLEX_Common_Navigations();
                Companies comp = new Companies();
                Coverages cov = new Coverages();
                setThreadDataSheetName("TestData_Stifel.xls");
                setTheTestForRun();

 

                // Login to SalesForce
                Login_Page.loadApplicationURL(driver, folder, suit, "Login", 2);
                Login_Page.salesforceLogin(driver, folder,suit, "Login", 2);
                
                //Create  company 
                comp.ClickCompaniesTab(driver, suit, folder);
                comp.ClickCompaniesNewbutton(driver);
                String InstitutionalCompany = comp.CreateCompanies(driver, suit, folder, "Companies", 26);
                String CompanyURL = driver.getCurrentUrl();
                
                //Navigate to internal Contact
                Utilities.SetParameterFromInputSheet("Coverage", "Company", 1, InstitutionalCompany);
                String ContactURL = Utilities.getParameterFromInputSheet("Coverage", "Internal Contact URL", 1);
                driver.get(ContactURL);
                driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
                
              //Create Coverage
                cn.SwitchTabsinLightning(driver, suit, folder, "Coverage");
                cov.CreateCoverageforIB(driver, suit, folder, "Coverage", 1);
              
                cn.clickonObjectTab(driver, suit, folder, "Companies");
                comp.ClickCompaniesNewbutton(driver);
                String RelatedCompName = comp.CreateCompanies(driver, suit, folder, "Companies", 27);
                Utilities.SetParameterFromInputSheet("SponsorRelationship", "Related Company", 2, RelatedCompName);
                
//              //Create Sponsor Portfolio
                driver.get(CompanyURL);
                Thread.sleep(10000);
                comp.Create_Sponsor_Portfolio_Relationships(driver, suit, folder, "SponsorRelationship", 2);
               
               //InActive the portfolio
                driver.get(CompanyURL);
                Thread.sleep(10000);
                cn.SwitchTabsinLightning(driver, suit, folder, "Contacts & Relationships");
                comp.InActivePortfolio(driver, suit, folder);
                
                //Validate Related Company Coverage Status
                comp.ValidateRelateCompanyCoverageStatus(driver, suit, folder);
          
            }
                
                catch (Exception e) 
                {
                    String className = this.getClass().getSimpleName();
                    String functionName = new Object() {
                    }.getClass().getEnclosingMethod().getName();
                    System.out.println("---***Start***---" + "\n" + functionSuit + "\n" + className + "-" + functionName + "\n"
                            + ExceptionUtils.getFullStackTrace(e) + "\n" + "---***End***---");
                    Common_Functions.ResultFail(driver, suit, folder, className, className + "." + functionName,
                            "failed");
                }
            }
		
		
		@Test(groups = "NYSF-2078_CompanyResearchProfileFieldEnhancements", enabled = true)
		public void CompanyResearchProfileFieldEnhancements() throws Exception{
			
			try {
				
				SF_StandardLEX_Common_Navigations cn= new SF_StandardLEX_Common_Navigations();
                Companies comp = new Companies();
                setThreadDataSheetName("TestData_Stifel.xls");
                setTheTestForRun();
                
                
             // Login to SalesForce
                Login_Page.loadApplicationURL(driver, folder, suit, "Login", 2);
                Login_Page.salesforceLogin(driver, folder,suit, "Login", 2);
                
              //Create  company 
                comp.ClickCompaniesTab(driver, suit, folder);
                comp.ClickCompaniesNewbutton(driver);
                String InstitutionalCompany = comp.CreateCompanies(driver, suit, folder, "Companies", 26);
                
               //Switch tabs to research profile
                WebDriverWait wait = new WebDriverWait(driver, 30);
                JavascriptExecutor jse = (JavascriptExecutor)driver;
                jse.executeScript("arguments[0].click()", wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//button[@title='More Tabs'])[1]"))));
                Thread.sleep(3000);
                jse.executeScript("arguments[0].click()", wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a/span[text()='Research Profile']"))));

                
                //Validation of fields
                comp.ValidateResearchProfileFieldEnhancements(driver, suit, folder);
                
			}
			catch (Exception e) 
            {
                String className = this.getClass().getSimpleName();
                String functionName = new Object() {
                }.getClass().getEnclosingMethod().getName();
                System.out.println("---***Start***---" + "\n" + functionSuit + "\n" + className + "-" + functionName + "\n"
                        + ExceptionUtils.getFullStackTrace(e) + "\n" + "---***End***---");
                Common_Functions.ResultFail(driver, suit, folder, className, className + "." + functionName,
                        "failed");
            }
		}
		
		
		
		
		@Test(groups = "NYSF-3168 2726_CorporateSolutionInformation", enabled = true)
		public void CorporateSolutionInformation() throws Exception{
			
			try {
				
				SF_StandardLEX_Common_Navigations cn= new SF_StandardLEX_Common_Navigations();
                Companies comp = new Companies();
                setThreadDataSheetName("TestData_Stifel.xls");
                setTheTestForRun();
                
                
             // Login to SalesForce
                Login_Page.loadApplicationURL(driver, folder, suit, "Login", 1);
                Login_Page.salesforceLogin(driver, folder,suit, "Login", 1);
                
              //Create  company 
                comp.ClickCompaniesTab(driver, suit, folder);
                comp.ClickCompaniesNewbutton(driver);
                comp.CreateCompanies(driver, suit, folder, "Companies", 4);
                String CorpCompany = driver.getCurrentUrl();
                
                //Validation of Corporate Solution Information section and it's fields for SN
                cn.LoginasUser(driver, suit, folder, "Nicholas Agresti");
                driver.get(CorpCompany);
                Thread.sleep(10000);
                comp.ValidateCorporateSolutionsInformation(driver, suit, folder, "SN");
                cn.LogoutasUser(driver, suit, folder, "Nicholas Agresti");
                
                //Validation of Corporate Solution Information section and it's fields for KBW
                driver.get(CorpCompany);
                cn.LoginasUser(driver, suit, folder, "Michele Ballatore");
                driver.get(CorpCompany);
                Thread.sleep(10000);
                comp.ValidateCorporateSolutionsInformation(driver, suit, folder, "KBW");
                cn.LogoutasUser(driver, suit, folder, "Michele Ballatore");
                
                //Check if designated sponsorship is editable
                driver.get(CorpCompany);
                Thread.sleep(10000);
                comp.CheckPresenceofDesignatedSponsorshipEditButton(driver, suit, folder);
                
			}
			catch (Exception e) 
            {
                String className = this.getClass().getSimpleName();
                String functionName = new Object() {
                }.getClass().getEnclosingMethod().getName();
                System.out.println("---***Start***---" + "\n" + functionSuit + "\n" + className + "-" + functionName + "\n"
                        + ExceptionUtils.getFullStackTrace(e) + "\n" + "---***End***---");
                Common_Functions.ResultFail(driver, suit, folder, className, className + "." + functionName,
                        "failed");
            }
		}
		
		
		
		@Test(groups = "NYSF-2904", enabled = true)
		public void CorporateSolutionCoverage() throws Exception{
			
			try {
				
				SF_StandardLEX_Common_Navigations cn= new SF_StandardLEX_Common_Navigations();
                Companies comp = new Companies();
                setThreadDataSheetName("TestData_Stifel.xls");
                setTheTestForRun();
                
             // Login to SalesForce
                Login_Page.loadApplicationURL(driver, folder, suit, "Login", 1);
                Login_Page.salesforceLogin(driver, folder,suit, "Login", 1);
                
              //Create  company 
                comp.ClickCompaniesTab(driver, suit, folder);
                comp.ClickCompaniesNewbutton(driver);
                comp.CreateCompanies(driver, suit, folder, "Companies", 4);
                String CorpCompany = driver.getCurrentUrl();
                
              //Login as account opening user
                cn.LoginasUser(driver, suit, folder, "Simona Castagno");
            
              //Add and validate corporate solutions coverage
                driver.get(CorpCompany);
                comp.AddCorporteSolutionCoverage(driver, suit, folder, "Coverage", 7, CorpCompany);
			
			}
			catch (Exception e) 
            {
                String className = this.getClass().getSimpleName();
                String functionName = new Object() {
                }.getClass().getEnclosingMethod().getName();
                System.out.println("---***Start***---" + "\n" + functionSuit + "\n" + className + "-" + functionName + "\n"
                        + ExceptionUtils.getFullStackTrace(e) + "\n" + "---***End***---");
                Common_Functions.ResultFail(driver, suit, folder, className, className + "." + functionName,
                        "failed");
            }
		}
		
		
		@Test(groups = "BudgetTrackingForLastYear", enabled = true)
        public void BudgetTrackingForLastYear() throws Exception {
            try{    
            SF_StandardLEX_Common_Navigations cn= new SF_StandardLEX_Common_Navigations();
            Companies comp = new Companies();
            setThreadDataSheetName("TestData_Stifel.xls");
            setTheTestForRun();

 

            // Login to SalesForce
            Login_Page.loadApplicationURL(driver, folder, suit, "Login", 1);
            Login_Page.salesforceLogin(driver, folder,suit, "Login", 1);
            
            //Create Institutional company 
            comp.ClickCompaniesTab(driver, suit, folder);        
            
          //Login as SN Executive user
            String SNExecutive = Utilities.getParameterFromInputSheet("Coverage", "Account Opening User", 7);
            cn.LoginasUser(driver, suit, folder, SNExecutive);
            
          //open institutional company
            comp.openExistingCompany(driver, "CreateBudget", 1);
           
          //Create new Budget
            comp.createNewBudget(driver, "CreateBudget", 1);
            
            //Verify Budget
            comp.validateLastYearBudget(driver, suit, folder, "CreateBudget", 1);
            
          
            }
                
            catch (Exception e) 
            {
                  String className = this.getClass().getSimpleName();
                  String functionName = new Object() {
                  }.getClass().getEnclosingMethod().getName();
                  System.out.println("---***Start***---" + "\n" + functionSuit + "\n" + className + "-" + functionName + "\n"
                            + ExceptionUtils.getFullStackTrace(e) + "\n" + "---***End***---");
                  Common_Functions.ResultFail(driver, suit, folder, className, className + "." + functionName,
                            "failed");
                }
            }
        
        @Test(groups = "BudgetTrackingForCurrentYear", enabled = true)
        public void BudgetTrackingForCurrentYear() throws Exception {
            try{    
            SF_StandardLEX_Common_Navigations cn= new SF_StandardLEX_Common_Navigations();
            Companies comp = new Companies();
            setThreadDataSheetName("TestData_Stifel.xls");
            setTheTestForRun();

 

            // Login to SalesForce
            Login_Page.loadApplicationURL(driver, folder, suit, "Login", 1);
            Login_Page.salesforceLogin(driver, folder,suit, "Login", 1);
            
            //Create Institutional company 
            comp.ClickCompaniesTab(driver, suit, folder);
            
          //Login as SN Executive user
            String SNExecutive = Utilities.getParameterFromInputSheet("Coverage", "Account Opening User", 7);
            cn.LoginasUser(driver, suit, folder, SNExecutive);
            
          //open institutional company
            comp.openExistingCompany(driver, "CreateBudget", 1);
           
          //Create new Budget
            comp.createNewBudget(driver, "CreateBudget", 2);
            
            //Verify Budget
            comp.validateCurrentYearBudget(driver, suit, folder, "CreateBudget", 2);
            
          
            }
                
                catch (Exception e) 
                {
                    String className = this.getClass().getSimpleName();
                    String functionName = new Object() {
                    }.getClass().getEnclosingMethod().getName();
                    System.out.println("---***Start***---" + "\n" + functionSuit + "\n" + className + "-" + functionName + "\n"
                            + ExceptionUtils.getFullStackTrace(e) + "\n" + "---***End***---");
                    Common_Functions.ResultFail(driver, suit, folder, className, className + "." + functionName,
                            "failed");
                }
            }
        
        @Test(groups = "BudgetTrackingForNextYear", enabled = true)
        public void BudgetTrackingForNextYear() throws Exception {
            try{    
            SF_StandardLEX_Common_Navigations cn= new SF_StandardLEX_Common_Navigations();
            Companies comp = new Companies();
            setThreadDataSheetName("TestData_Stifel.xls");
            setTheTestForRun();

 

            // Login to SalesForce
            Login_Page.loadApplicationURL(driver, folder, suit, "Login", 1);
            Login_Page.salesforceLogin(driver, folder,suit, "Login", 1);
            
            //Create Institutional company 
            comp.ClickCompaniesTab(driver, suit, folder);
            
          //Login as SN Executive user
            String SNExecutive = Utilities.getParameterFromInputSheet("Coverage", "Account Opening User", 7);
            cn.LoginasUser(driver, suit, folder, SNExecutive);
            
          //open institutional company
            comp.openExistingCompany(driver, "CreateBudget", 1);
           
          //Create new Budget
            comp.createNewBudget(driver, "CreateBudget", 3);
            
            //Verify Budget
            comp.validateNextYearBudget(driver, suit, folder, "CreateBudget", 3);
            
          
            }
                
            catch (Exception e) 
            {
                 String className = this.getClass().getSimpleName();
                 String functionName = new Object() {
                 }.getClass().getEnclosingMethod().getName();
                 System.out.println("---***Start***---" + "\n" + functionSuit + "\n" + className + "-" + functionName + "\n"
                            + ExceptionUtils.getFullStackTrace(e) + "\n" + "---***End***---");
                 Common_Functions.ResultFail(driver, suit, folder, className, className + "." + functionName,
                            "failed");
             }
         }
        
        
        @Test(groups = "NYSF-1997_ValidateRSAFields", enabled = true)
        public void ValidateRSAFieldsinResearchProfile() throws Exception {
        	
        	try {
        		SF_StandardLEX_Common_Navigations cn= new SF_StandardLEX_Common_Navigations();
                Companies comp = new Companies();
                setThreadDataSheetName("TestData_Stifel.xls");
                setTheTestForRun();
        		
             // Login to SalesForce
                Login_Page.loadApplicationURL(driver, folder, suit, "Login", 1);
                Login_Page.salesforceLogin(driver, folder,suit, "Login", 1);
                
             // Create Institutional Company
                comp.ClickCompaniesTab(driver, suit, folder);
                comp.ClickCompaniesNewbutton(driver);
                comp.CreateCompanies(driver, suit, folder, "Companies", 1);
                String CompanyURL = driver.getCurrentUrl();
        		
             // Navigate to Research Profile tab
                cn.SwitchTabsinLightning(driver, suit, folder, "Research Profile");
                
             // Validate the RSA fields
                comp.ValidatePresenceofRSAfields(driver, suit, folder);	
                comp.SetRSAContractingRole(driver, suit, folder, "Companies", 42);
        		
        	}
        	catch (Exception e) 
            {
                String className = this.getClass().getSimpleName();
                String functionName = new Object() {
                }.getClass().getEnclosingMethod().getName();
                System.out.println("---***Start***---" + "\n" + functionSuit + "\n" + className + "-" + functionName + "\n"
                        + ExceptionUtils.getFullStackTrace(e) + "\n" + "---***End***---");
                Common_Functions.ResultFail(driver, suit, folder, className, className + "." + functionName,
                        "failed");
            }
        }
        
        
        
        @Test(groups = "NYSF-1348_ValidateRateCardFieldsinCompanyProfile", enabled = true)
        public void ValidateRateCardFieldsinCompanyProfile() throws Exception {
        	
        	try {
        		SF_StandardLEX_Common_Navigations cn= new SF_StandardLEX_Common_Navigations();
                Companies comp = new Companies();
                setThreadDataSheetName("TestData_Stifel.xls");
                setTheTestForRun();
        		
             // Login to SalesForce
                Login_Page.loadApplicationURL(driver, folder, suit, "Login", 1);
                Login_Page.salesforceLogin(driver, folder,suit, "Login", 1);
                
             // Create Institutional Company
                comp.ClickCompaniesTab(driver, suit, folder);
                comp.ClickCompaniesNewbutton(driver);
                comp.CreateCompanies(driver, suit, folder, "Companies", 1);
                String CompanyURL = driver.getCurrentUrl();
        		
             // Check The Rate Card fields
                comp.VerifyandEditRateCardFields(driver, suit, folder, "Companies", 42);
        		
        	}
        	catch (Exception e) 
            {
                String className = this.getClass().getSimpleName();
                String functionName = new Object() {
                }.getClass().getEnclosingMethod().getName();
                System.out.println("---***Start***---" + "\n" + functionSuit + "\n" + className + "-" + functionName + "\n"
                        + ExceptionUtils.getFullStackTrace(e) + "\n" + "---***End***---");
                Common_Functions.ResultFail(driver, suit, folder, className, className + "." + functionName,
                        "failed");
            }
        }

        
        @Test(groups = "NYSF-2669_EuroFieldsUpdateOnCommisionTab", enabled = true)
        public void EuroFieldsUpdated() throws Exception {
            try{    
            SF_StandardLEX_Common_Navigations cn= new SF_StandardLEX_Common_Navigations();
            Companies comp = new Companies();
            setThreadDataSheetName("TestData_Stifel.xls");
            setTheTestForRun();


            // Login to SalesForce
            Login_Page.loadApplicationURL(driver, folder, suit, "Login", 1);
            Login_Page.salesforceLogin(driver, folder,suit, "Login", 1);
            
            //Create Institutional company 
            comp.ClickCompaniesTab(driver, suit, folder);
            comp.ClickCompaniesNewbutton(driver);
            comp.CreateCompanies(driver, suit, folder, "Companies", 1);
            
            //Click on subtab
            cn.SwitchTabsinLightning(driver, suit, folder, "Commissions");
            
            //validate Euro Update on Commissions Tab
            comp.VerifyEuroUpdateOnCommissionsTab(driver, suit, folder);
            }
                
            catch (Exception e) 
            {
                    String className = this.getClass().getSimpleName();
                    String functionName = new Object() {
                    }.getClass().getEnclosingMethod().getName();
                    System.out.println("---***Start***---" + "\n" + functionSuit + "\n" + className + "-" + functionName + "\n"
                            + ExceptionUtils.getFullStackTrace(e) + "\n" + "---***End***---");
                    Common_Functions.ResultFail(driver, suit, folder, className, className + "." + functionName,
                            "failed");
            }
        }
        
        
        
        @Test(groups = "NYSF-2726_SponsoredCheckbox", enabled = true)
        public void SponsoredCheckbox() throws Exception{
        	try {
        		SF_StandardLEX_Common_Navigations cn= new SF_StandardLEX_Common_Navigations();
                Companies comp = new Companies();
                setThreadDataSheetName("TestData_Stifel.xls");
                setTheTestForRun();
                
                // Login to SalesForce
                Login_Page.loadApplicationURL(driver, folder, suit, "Login", 1);
                Login_Page.salesforceLogin(driver, folder,suit, "Login", 1);
                
           //   Create a Corporate Company
                comp.ClickCompaniesTab(driver, suit, folder);
                comp.ClickCompaniesNewbutton(driver);
                String CompanyName = comp.CreateCompanies(driver, suit, folder, "Companies", 4);
                String CompURL = driver.getCurrentUrl();
                Utilities.SetParameterFromInputSheet("Security Master", "Company", 1, CompanyName);
                
                //Create a new Ticker
                cn.SearchandClickObjectTab(driver, suit, folder, "Security Master/Interest");
                JavascriptExecutor js = (JavascriptExecutor) driver;
        		js.executeScript("arguments[0].click();", driver.findElement(By.xpath("//div[text()='New Interest']/parent::a")));
        		Thread.sleep(10000);
                comp.CreateSecurityMaster(driver, suit, folder, "Security Master", 1);
                
                //Validate Sponsored Checkbox
                driver.get(CompURL);
                Thread.sleep(15000);
        		
                comp.ValidateSponsoredCheckbox(driver, suit, folder);
        		
        	}
        	catch (Exception e) 
            {
                    String className = this.getClass().getSimpleName();
                    String functionName = new Object() {
                    }.getClass().getEnclosingMethod().getName();
                    System.out.println("---***Start***---" + "\n" + functionSuit + "\n" + className + "-" + functionName + "\n"
                            + ExceptionUtils.getFullStackTrace(e) + "\n" + "---***End***---");
                    Common_Functions.ResultFail(driver, suit, folder, className, className + "." + functionName,
                            "failed");
            }
        }
        
        
        @Test(groups = "NYSF-1656_ResearchProfileFields", enabled = true)
        public void ResearchProfileFields() throws Exception{
        	
        	try {
        		SF_StandardLEX_Common_Navigations cn= new SF_StandardLEX_Common_Navigations();
                Companies comp = new Companies();
                setThreadDataSheetName("TestData_Stifel.xls");
                setTheTestForRun();
                
                // Login to SalesForce
                Login_Page.loadApplicationURL(driver, folder, suit, "Login", 1);
                Login_Page.salesforceLogin(driver, folder,suit, "Login", 1);

                // Create an Institutional company
                comp.ClickCompaniesTab(driver, suit, folder);
                comp.ClickCompaniesNewbutton(driver);
                String CompanyName = comp.CreateCompanies(driver, suit, folder, "Companies", 1);
                String CompURL = driver.getCurrentUrl();
                
                // Validate Research Profile fields
                cn.SwitchTabsinLightning(driver, suit, folder, "Research Profile");
        		comp.ValidateResearchProfilefields(driver, suit, folder);
        		driver.navigate().refresh();
        		Thread.sleep(10000);
        		
        		//Validate Interaction Reporting Profile
        		cn.SwitchTabsinLightning(driver, suit, folder, "Interaction Reporting Profile");
        		comp.ValidateInteractionReportingProfilefields(driver, suit, folder);
        		
        		
        	}
        	catch (Exception e) 
            {
                    String className = this.getClass().getSimpleName();
                    String functionName = new Object() {
                    }.getClass().getEnclosingMethod().getName();
                    System.out.println("---***Start***---" + "\n" + functionSuit + "\n" + className + "-" + functionName + "\n"
                            + ExceptionUtils.getFullStackTrace(e) + "\n" + "---***End***---");
                    Common_Functions.ResultFail(driver, suit, folder, className, className + "." + functionName,
                            "failed");
            }
        }
        
        
        @Test(groups = "NYSF-2688_ResearchServiceLevelNoteErrMsg", enabled = true)
        public void ResearchServiceLevelNoteErrMsg() throws Exception{
        	
        	try {
        		SF_StandardLEX_Common_Navigations cn= new SF_StandardLEX_Common_Navigations();
                Companies comp = new Companies();
                setThreadDataSheetName("TestData_Stifel.xls");
                setTheTestForRun();
                
                // Login to SalesForce
                Login_Page.loadApplicationURL(driver, folder, suit, "Login", 1);
                Login_Page.salesforceLogin(driver, folder,suit, "Login", 1);

                // Create an Institutional company
                comp.ClickCompaniesTab(driver, suit, folder);
                comp.ClickCompaniesNewbutton(driver);
                String CompanyName = comp.CreateCompanies(driver, suit, folder, "Companies", 1);
                String CompURL = driver.getCurrentUrl();
                
                // Validate the error message for Canada Research Service Level(SN)
                cn.SwitchTabsinLightning(driver, suit, folder, "Research Profile");
                comp.ValidateErrorMsgResearchServiceLevelNotes(driver, suit, folder);

        	}
        	catch (Exception e) 
            {
                    String className = this.getClass().getSimpleName();
                    String functionName = new Object() {
                    }.getClass().getEnclosingMethod().getName();
                    System.out.println("---***Start***---" + "\n" + functionSuit + "\n" + className + "-" + functionName + "\n"
                            + ExceptionUtils.getFullStackTrace(e) + "\n" + "---***End***---");
                    Common_Functions.ResultFail(driver, suit, folder, className, className + "." + functionName,
                            "failed");
            }
        }
        
        
        @Test(groups = "NYSF-2564_MyTerritoriesException", enabled = true)
        public void MyTerritoriesException() throws Exception {
            try{    
            SF_StandardLEX_Common_Navigations cn= new SF_StandardLEX_Common_Navigations();
            SF_StandardLEX_ActionOnWebElements aw=new SF_StandardLEX_ActionOnWebElements();
            Companies comp = new Companies();
            setThreadDataSheetName("TestData_Stifel.xls");
            setTheTestForRun();


            // Login to SalesForce
            Login_Page.loadApplicationURL(driver, folder, suit, "Login", 1);
            Login_Page.salesforceLogin(driver, folder,suit, "Login", 1);
            
            //Create Corporate company 
            cn.clickonObjectTab(driver, suit, folder, "Companies");
            comp.ClickCompaniesNewbutton(driver);
            comp.CreateCompanies(driver, suit, folder, "Companies", 4); 
            String CorporateURL = driver.getCurrentUrl();
            
            //open user details page
            cn.OpenUserDetailPage(driver, suit, folder, "Vaishali Jain");
            String territories=comp.getTerritories(driver);
            
          //Edit Corporate company 
            driver.get(CorporateURL);
            driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
            comp.ClickCompaniesEditbutton(driver);
            aw.selectLookupValue1(driver, "Companies", "Territory (KBW)", 2);
            aw.selectLookupValue1(driver, "Companies", "Territory (SN)", 2);
            //aw.selectLookupValue1(driver, "Companies", "Territory (KBW)", 1);
            
          //verify My territories
            comp.verifyMyterritoriesException(driver, suit, folder,territories);
            }
                
            catch (Exception e) 
            {
                    String className = this.getClass().getSimpleName();
                    String functionName = new Object() {
                    }.getClass().getEnclosingMethod().getName();
                    System.out.println("---***Start***---" + "\n" + functionSuit + "\n" + className + "-" + functionName + "\n"
                            + ExceptionUtils.getFullStackTrace(e) + "\n" + "---***End***---");
                    Common_Functions.ResultFail(driver, suit, folder, className, className + "." + functionName,
                            "failed");
            }
          }
        
        
        
        
        
        @Test(groups = "NYSF-3170_Company_Contact_CompactLayOut", enabled = true, priority = 2)
        public void CompactLayout_RST() throws Exception {
           
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
            String CompName=comp.CreateCompanies(driver, suit, folder, "Companies", 1);
            String CompanyURL = driver.getCurrentUrl();
           
            //Set Company name
            Utilities.SetParameterFromInputSheet("Contacts", "Company Name", 1, CompName);
            Utilities.SetParameterFromInputSheet("Contacts", "Company Name", 3, CompName);
           
            //Create and Edit External Contact
//            cn.clickonObjectTab(driver, suit, folder, "Contacts");
//            cn.Newbuttonclick(driver);
//            cont.CreateExternalContact(driver, suit, folder, "Contacts", 1);
//            String ContactURL = driver.getCurrentUrl();
//            driver.navigate().refresh();
//            Thread.sleep(10000);
//           
//            driver.get(CompanyURL);
            Thread.sleep(10000);
            cn.SwitchTabsinLightning(driver, suit, folder, "Research Profile");
           
            comp.addServiceLevel(driver, "Companies");
            comp.verifyFlag(driver, suit, folder);
           
            //driver.get(ContactURL);
            //Thread.sleep(10000);
            //comp.verifyFlag(driver, suit, folder);
           
       
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


