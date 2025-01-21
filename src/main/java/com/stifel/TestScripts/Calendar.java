package com.stifel.TestScripts;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.testng.annotations.Test;

import com.stifel.LEXCommons.SF_StandardLEX_Common_Navigations;
import com.stifel.PageObjects.CalendarPageObject;
import com.stifel.PageObjects.Companies;
import com.stifel.PageObjects.Contacts;
import com.stifel.corefunctions.Browser_setup;
import com.stifel.corefunctions.Common_Functions;
import com.stifel.corefunctions.Login_Page;

public class Calendar extends Browser_setup{
	
	@Test(groups = "NYSF-2337_FilterCalenderByEntitySNEL", enabled = true, priority = 2)
    public void FilterCalenderSNEL() throws Exception {
       
        SF_StandardLEX_Common_Navigations cn= new SF_StandardLEX_Common_Navigations();
        CalendarPageObject cal = new CalendarPageObject();
       
        try {
        setThreadDataSheetName("TestData_Stifel.xls");
        setTheTestForRun();
       
        // Login to SalesForce
        Login_Page.loadApplicationURL(driver, folder, suit, "Login", 1);
        Login_Page.salesforceLogin(driver, folder,suit, "Login", 1);
       
        cn.clickonObjectTab(driver, suit, folder, "Calendar");
        cal.SelectListFromDropdown(driver, "Entity" ,"SNEL","Test with Sarah =)",suit, folder );
        //comp.verifyEntityForSNEL(driver, suit, folder, "SNEL");
       
   
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
   
    @Test(groups = "NYSF-2337_FilterCalenderByEntitySNC", enabled = true, priority = 2)
    public void FilterCalenderSNC() throws Exception {
       
        SF_StandardLEX_Common_Navigations cn= new SF_StandardLEX_Common_Navigations();
        CalendarPageObject cal = new CalendarPageObject();
       
        try {
        setThreadDataSheetName("TestData_Stifel.xls");
        setTheTestForRun();
       
        // Login to SalesForce
        Login_Page.loadApplicationURL(driver, folder, suit, "Login", 1);
        Login_Page.salesforceLogin(driver, folder,suit, "Login", 1);
       
        cn.clickonObjectTab(driver, suit, folder, "Calendar");
        cal.SelectListFromDropdown(driver, "Entity" ,"SNC","test event",suit, folder );
       
   
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
   
    @Test(groups = "NYSF-2337_FilterCalenderByEntitySEBA", enabled = true, priority = 2)
    public void FilterCalenderSEBA() throws Exception {
       
        SF_StandardLEX_Common_Navigations cn= new SF_StandardLEX_Common_Navigations();
        CalendarPageObject cal = new CalendarPageObject();
       
        try {
        setThreadDataSheetName("TestData_Stifel.xls");
        setTheTestForRun();
       
        // Login to SalesForce
        Login_Page.loadApplicationURL(driver, folder, suit, "Login", 1);
        Login_Page.salesforceLogin(driver, folder,suit, "Login", 1);
       
        cn.clickonObjectTab(driver, suit, folder, "Calendar");
        cal.SelectListFromDropdown(driver, "Entity" ,"SEBA","Alpha Tango-France",suit, folder );
       
   
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
   
    @Test(groups = "NYSF-2337_FilterCalenderByEntityKBWI", enabled = true, priority = 2)
    public void FilterCalenderKBWI() throws Exception {
       
        SF_StandardLEX_Common_Navigations cn= new SF_StandardLEX_Common_Navigations();
        CalendarPageObject cal = new CalendarPageObject();
       
        try {
        setThreadDataSheetName("TestData_Stifel.xls");
        setTheTestForRun();
       
        // Login to SalesForce
        Login_Page.loadApplicationURL(driver, folder, suit, "Login", 1);
        Login_Page.salesforceLogin(driver, folder,suit, "Login", 1);
       
        cn.clickonObjectTab(driver, suit, folder, "Calendar");
        cal.SelectListFromDropdown(driver, "Entity" ,"KBWI","ASML_EVENT'",suit, folder );
       
   
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

