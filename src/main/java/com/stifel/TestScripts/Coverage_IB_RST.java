package com.stifel.TestScripts;

import java.util.concurrent.TimeUnit;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.testng.annotations.Test;

import com.stifel.LEXCommons.SF_StandardLEX_Common_Navigations;
import com.stifel.PageObjects.Companies;
import com.stifel.PageObjects.Coverages;
import com.stifel.corefunctions.Browser_setup;
import com.stifel.corefunctions.Common_Functions;
import com.stifel.corefunctions.Login_Page;
import com.stifel.corefunctions.Utilities;

public class Coverage_IB_RST extends Browser_setup

{
	
	@Test(groups = "IBCompanyCoverage", enabled = true)
	public void IBCompanyCoverage() throws Exception {
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
        
        cn.clickonObjectTab(driver, suit, folder, "Companies");
		comp.ClickCompaniesNewbutton(driver);
		String RelatedCompName = comp.CreateCompanies(driver, suit, folder, "Companies", 27);
		Utilities.SetParameterFromInputSheet("SponsorRelationship", "Related Company", 2, RelatedCompName);
        
//      //Create Sponsor Portfolio
        driver.get(CompanyURL);
        Thread.sleep(10000);
        comp.Create_Sponsor_Portfolio_Relationships(driver, suit, folder, "SponsorRelationship", 2);
       
        //Navigate to internal Contact
        Utilities.SetParameterFromInputSheet("Coverage", "Company", 1, InstitutionalCompany);
        String ContactURL = Utilities.getParameterFromInputSheet("Coverage", "Internal Contact URL", 1);
        driver.get(ContactURL);
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
        
        //Create Coverage
        cn.SwitchTabsinLightning(driver, suit, folder, "Coverage");
        cov.CreateCoverageforIB(driver, suit, folder, "Coverage", 1);
        
        //Navigate to company
        driver.get(CompanyURL);
        Thread.sleep(10000);
        cn.SwitchTabsinLightning(driver, suit, folder, "Coverage");
        String ContactName = Utilities.getParameterFromInputSheet("Coverage", "Internal Contact", 1);
        cov.verifyCoverageOnCompany(driver, suit, folder, "IB",ContactName );

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
	
	
	@Test(groups = "RSTCompanyCoverage", enabled = true)
	public void RSTCompanyCoverage() throws Exception {
		try{	
		SF_StandardLEX_Common_Navigations cn= new SF_StandardLEX_Common_Navigations();
		Companies comp = new Companies();
		Coverages cov = new Coverages();
		setThreadDataSheetName("TestData_Stifel.xls");
		setTheTestForRun();

		// Login to SalesForce
		Login_Page.loadApplicationURL(driver, folder, suit, "Login", 1);
		Login_Page.salesforceLogin(driver, folder,suit, "Login", 1);
		
		
		//Create  corporate company 
		comp.ClickCompaniesTab(driver, suit, folder);
		comp.ClickCompaniesNewbutton(driver);
        String Companyname = comp.CreateCompanies(driver, suit, folder, "Companies", 28);
        String CompanyURL = driver.getCurrentUrl();
        
		//Create  Individual company 
		comp.ClickCompaniesTab(driver, suit, folder);
		comp.ClickCompaniesNewbutton(driver);
        String IndiCompanyname = comp.CreateCompanies(driver, suit, folder, "Companies", 29);
        String IndiCompanyURL = driver.getCurrentUrl();
        
        //Login as Account opening user
        String accopeninguser = Utilities.getParameterFromInputSheet("Coverage", "Account Opening User", 2);
        cn.LoginasUser(driver, suit, folder, accopeninguser);
        
        //Create Coverage
        driver.get(CompanyURL);
        Thread.sleep(10000);
        cn.SwitchTabsinLightning(driver, suit, folder, "Coverage");
        cov.CreateCoverageforRST(driver, suit, folder, "Coverage", 2);
        
        cn.LogoutUser(driver, suit, folder);
        
        Login_Page.loadApplicationURL(driver, folder, suit, "Login", 1);
		Login_Page.salesforceLogin(driver, folder,suit, "Login", 1);
        
        String kbwuser = Utilities.getParameterFromInputSheet("Coverage", "Account Opening User", 3);
        cn.LoginasUser(driver, suit, folder, kbwuser);
        
        //Create Coverage
        driver.get(IndiCompanyURL);
        Thread.sleep(10000);
        cn.SwitchTabsinLightning(driver, suit, folder, "Coverage");
        cov.CreateCoverageforRST(driver, suit, folder, "Coverage", 3);
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
	
	
	@Test(groups = "CoverageEndsWithAnEndDate", enabled = true)
    public void RSTCoverageEnd() throws Exception {
        try{    
        SF_StandardLEX_Common_Navigations cn= new SF_StandardLEX_Common_Navigations();
        Companies comp = new Companies();
        Coverages cov = new Coverages();
        setThreadDataSheetName("TestData_Stifel.xls");
        setTheTestForRun();

        // Login to SalesForce
        Login_Page.loadApplicationURL(driver, folder, suit, "Login", 1);
        Login_Page.salesforceLogin(driver, folder,suit, "Login", 1);
        
        
        //Create  corporate company 
        comp.ClickCompaniesTab(driver, suit, folder);
        comp.ClickCompaniesNewbutton(driver);
        String Companyname = comp.CreateCompanies(driver, suit, folder, "Companies", 28);
        String CompanyURL = driver.getCurrentUrl();
        
        //Login as Account opening user
        String accopeninguser = Utilities.getParameterFromInputSheet("Coverage", "Account Opening User", 2);
        cn.LoginasUser(driver, suit, folder, accopeninguser);
        
        //Create Coverage
        driver.get(CompanyURL);
        Thread.sleep(10000);
        cn.SwitchTabsinLightning(driver, suit, folder, "Coverage");
        cov.CreateCoverageforRST(driver, suit, folder, "Coverage", 2);
        driver.get(CompanyURL);
        Thread.sleep(10000);
        cn.SwitchTabsinLightning(driver, suit, folder, "Coverage");
        cov.PutEndDateToCoverage(driver, suit, folder);
        driver.get(CompanyURL);
        Thread.sleep(10000);
        cov.VerifyCoverageEnds(driver, suit, folder);
        
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
	
	
	
	@Test(groups = "CoverageSecurity", enabled = true)
    public void coverageSecurity() throws Exception {
        try{    
        SF_StandardLEX_Common_Navigations cn= new SF_StandardLEX_Common_Navigations();
        Companies comp = new Companies();
        Coverages cov = new Coverages();
        setThreadDataSheetName("TestData_Stifel.xls");
        setTheTestForRun();

 

        // Login to SalesForce
        Login_Page.loadApplicationURL(driver, folder, suit, "Login", 1);
        Login_Page.salesforceLogin(driver, folder,suit, "Login", 1);
        
        
        //Create  corporate company 
        comp.ClickCompaniesTab(driver, suit, folder);
        comp.ClickCompaniesNewbutton(driver);
        String Companyname = comp.CreateCompanies(driver, suit, folder, "Companies", 28);
        String CompanyURL = driver.getCurrentUrl();
        
        //Login as SN Account opening user
        String accopeninguser = Utilities.getParameterFromInputSheet("Coverage", "Account Opening User", 2);
        cn.LoginasUser(driver, suit, folder, accopeninguser);
        
        //Create Coverage
        driver.get(CompanyURL);
        Thread.sleep(10000);
        cn.SwitchTabsinLightning(driver, suit, folder, "Coverage");
        cov.CreateCoverageforRST(driver, suit, folder, "Coverage", 2);
        //Logout as SN Account opening user
        cn.LogoutUser(driver, suit, folder);
        
     // Login to SalesForce
        Login_Page.loadApplicationURL(driver, folder, suit, "Login", 1);
        Login_Page.salesforceLogin(driver, folder,suit, "Login", 1);
        
        //Login as KBW Account Opening User
        String kbwuser = Utilities.getParameterFromInputSheet("Coverage", "Account Opening User", 5);
        cn.LoginasUser(driver, suit, folder, kbwuser);
        
        driver.get(CompanyURL);
        Thread.sleep(10000);
        cn.SwitchTabsinLightning(driver, suit, folder, "Coverage");
        cov.PutEndDateToCoverage(driver, suit, folder);
        
        //Verify Edit SN Coverage
        cov.VerifyEditSNCoverage(driver, suit, folder);
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
	
}


